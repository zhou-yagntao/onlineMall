package com.zyt.service.impl.stockService.Impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.zyt.entity.OrderTask;
import com.zyt.entity.OrderTaskDetail;
import com.zyt.entity.orderEnum.OrderStatusEnum;
import com.zyt.entity.to.mq.OrderInfoTo;
import com.zyt.entity.to.mq.OrderStockDetailsLockTo;
import com.zyt.entity.to.mq.StockLockTo;
import com.zyt.entity.vo.OrderItemLockVo;
import com.zyt.exception.NoStockException;
import com.zyt.mapper.StockMapper;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskDetailsService;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskService;
import com.zyt.service.stockService.StockManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.stockService.Impl
 * @ClassName: StockManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 12:53 2021/2/28
 * @Version: 1.0
 */
@Service(value = "stockManagerService")
public class StockManagerServiceImpl implements StockManagerService {

    private Logger logger = LoggerFactory.getLogger(StockManagerServiceImpl.class);

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderTaskService orderTaskService;

    @Autowired
    private OrderTaskDetailsService orderTaskDetailsService;

    @Autowired
    private OrderManagerService orderManagerService;

    @Autowired
    private StockManagerService stockManagerService;


    @Override
    @Transactional
    public Boolean orderLockStock(String orderSn, List<OrderItemLockVo> orderItemLockVos){
        OrderTask orderTask = new OrderTask();
        orderTask.setOrder_sn(orderSn);
        this.orderTaskService.SaveOrderTask(orderTask);
        for (OrderItemLockVo orderItemLockVo:orderItemLockVos){
            logger.info("进入锁库存操作");
            Boolean prodStock = false;
            int id = orderItemLockVo.getProdId();
            int stockCount = this.stockMapper.selectStockOfCurrProdId(id);
            int stockLock = this.stockMapper.selectStockCountsOfCurrProdId(id);
            if(stockCount - stockLock <= 0){
                throw  new NoStockException(id);
            }
            int result = this.stockMapper.updateStockOfCurrProduct(id,orderItemLockVo.getLockCount());
            //若商品锁定成功 则将当前商品的日志发送给rabbitMq
            if (result == 1){
                prodStock = true;
                //库存锁定成功 则告诉mq锁定成功
                int orderTaskId = this.orderTaskService.getOrderTaskIdByOrderSn(orderSn);
                OrderTaskDetail orderTaskDetail = new OrderTaskDetail(null,orderItemLockVo.getProdId(),null,orderItemLockVo.getLockCount(),orderTaskId,1);
                this.orderTaskDetailsService.saveOrderTaskDetails(orderTaskDetail);
                StockLockTo stockLockTo = new StockLockTo();
                stockLockTo.setId(orderTask.getId());
                //由于不是一个对象 所以需要将数据集对拷一下  即使用BeanUtills.copy()即可
                OrderStockDetailsLockTo orderStockDetailsLockTo = new OrderStockDetailsLockTo();
                BeanUtils.copyProperties(orderTaskDetail,orderStockDetailsLockTo);
                //传对象而不使用id是为了防止回滚而查不到数据
                stockLockTo.setOrderStockDetailsLockTo(orderStockDetailsLockTo);
                rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked",stockLockTo);
            }else{}
            if(prodStock == false){
                throw  new NoStockException();
            }
        }
        return true;
    }

    /**
     * @Method: unLockStock
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理解锁库存操作
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 19:12
     * @Param: * @param stockLockTo
     * @Return: void
     */
    @Override
    @Transactional
    public void unLockStock(StockLockTo stockLockTo) {

        //解锁
        Integer detailId = stockLockTo.getOrderStockDetailsLockTo().getId();
        //去数据库查询是否有关于这一订单的锁定库存信息
        OrderTaskDetail orderTaskDetail = this.orderTaskDetailsService.getOrderTaskDetailsById(detailId);
        //若有  证明库存锁定成功
        // 1.没有订单  则必须解锁
        // 2.由此订单
        if(orderTaskDetail != null){
            //解锁
            //库存工作单的消息
            Integer id = stockLockTo.getId();
            //根据订单工作单id查询订单号
            String orderSn = this.orderTaskService.getOrderSnByOrderTaskId(id);
            int orderStatus = this.orderManagerService.getOrderStatusOfCurrOrderSn(orderSn);
            if(orderStatus >= 0){
                //若当前订单已经取消
                if(orderStatus == OrderStatusEnum.CANCLED.getCode()){
                    this.unLockStock(stockLockTo.getOrderStockDetailsLockTo().getProd_id(),stockLockTo.getOrderStockDetailsLockTo().getProd_num(),stockLockTo.getId());
                    //当前库单已锁定未解锁状态
                    if(stockLockTo.getOrderStockDetailsLockTo().getLock_stauts() == 1){
                        //更新订单状态  锁定=>解锁
                        this.orderTaskDetailsService.updateOrderStockStatus(stockLockTo.getId(),2);
                    }
                }

            }else{
            }
        }else{
        }
    }

    /**
     * @Method: unLockStock
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据订单状态判断是否解锁库存操作  防止系统网络卡顿  库存消息先到期
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 19:12
     * @Param: * @param orderInfoTo
     * @Return: void
     */
    @Override
    @Transactional
    public void unLockStock(OrderInfoTo orderInfoTo) {
        //查询最新的库存状态
        OrderTask orderTask = this.orderTaskService.getOrderTaskDetailsByOrderSn(orderInfoTo.getOrder_sn());
        Integer orderDetailsId = orderTask.getId();
        //查询所有没有解锁的库存信息，进行解锁
        List<OrderTaskDetail> orderTaskDetailList = this.orderTaskDetailsService.getOrderTaskDetailsByTaskIdAndStatus(orderDetailsId,1);
       //遍历获得的数组  进行解锁即可
        for (OrderTaskDetail orderTaskDetail:orderTaskDetailList){
            this.unLockStock(orderTaskDetail.getProd_id(),orderTaskDetail.getProd_num(),orderTaskDetail.getTask_id());
        }
    }

    /**
     * @Method: unLockStock
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理库存解锁逻辑
     * @Return:
     * @Exception:
     * @Date: 2021/3/3 14:36
     * @Param: * @param null
     * @Return:
     */
    private void unLockStock(Integer prodId,Integer count,Integer taskDeatailsId){
        this.stockMapper.unLockStock(prodId,count);
    }
}
