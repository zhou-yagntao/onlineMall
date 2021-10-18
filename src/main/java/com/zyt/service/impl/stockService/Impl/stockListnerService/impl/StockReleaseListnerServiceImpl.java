package com.zyt.service.impl.stockService.Impl.stockListnerService.impl;

import com.rabbitmq.client.Channel;
import com.zyt.entity.Order;
import com.zyt.entity.OrderTaskDetail;
import com.zyt.entity.orderEnum.OrderStatusEnum;
import com.zyt.entity.to.mq.OrderInfoTo;
import com.zyt.entity.to.mq.StockLockTo;
import com.zyt.mapper.StockMapper;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskDetailsService;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskService;
import com.zyt.service.stockService.StockManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.stockService.Impl.stockListnerService.impl
 * @ClassName: StockReleaseListnerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 16:06 2021/3/3
 * @Version: 1.0
 */
@Service(value = "stockReleaseListnerService")
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListnerServiceImpl {

    private Logger logger = LoggerFactory.getLogger(StockReleaseListnerServiceImpl.class);

    @Autowired
    private StockManagerService stockManagerService;


    /**
     * @Method: handleStockLockRelease
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理监听释放库存锁操作
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 13:56
     * @Param: * @param stockLockTo
     * @Return: void
     * 下单成功：库存锁定成功  接下来的业务是业务调用失败 导致回滚
     *
     * 只要解锁库存失败  一定告诉服务器解锁失败
     */

    public void handleStockLockRelease(StockLockTo stockLockTo, Message message, Channel channel) throws IOException {
        logger.info("收到解锁库存的消息");
        try {
            this.stockManagerService.unLockStock(stockLockTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }


     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:监听订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 17:31
      * @Param: * @param null
      * @Return:
      */
     @Transactional
     public void handleOrderCloseRelease(OrderInfoTo orderInfoTo, Message message, Channel channel) throws IOException {
        logger.info("订单关闭,准备解锁库存");
        try {
            this.stockManagerService.unLockStock(orderInfoTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }

}
