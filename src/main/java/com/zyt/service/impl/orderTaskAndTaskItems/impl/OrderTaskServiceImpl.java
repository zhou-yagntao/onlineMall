package com.zyt.service.impl.orderTaskAndTaskItems.impl;


import com.zyt.entity.OrderTask;
import com.zyt.mapper.OrdeTaskMapper;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.orderTaskAndTaskItems.impl
 * @ClassName: OrderTaskServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单工作单服务层实现模块
 * @Date: 17:24 2021/3/2
 * @Version: 1.0
 */
@Service(value = "orderTaskService")
public class OrderTaskServiceImpl implements OrderTaskService {

    @Autowired
    private OrdeTaskMapper orderTaskMapper;

    /**
     * @Method: SaveOrderTask
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:订单实现层控制模块
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 11:04
     * @Param: * @param orderTask
     * @Return: void
     */
    @Override
    public void SaveOrderTask(OrderTask orderTask) {
         this.orderTaskMapper.saveOrderTask(orderTask);
    }

    /**
     * @Method: getOrderSnByOrderTaskId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据订单id获得订单详细信息
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/3/3 14:18
     * @Param: * @param id
     * @Return: java.lang.String
     */
    @Override
    public String getOrderSnByOrderTaskId(Integer id) {
        return this.orderTaskMapper.getOrderSnByOrderTaskId(id) != null ? this.orderTaskMapper.getOrderSnByOrderTaskId(id) : null;
    }

    /**
     * @Method: getOrderTaskDetailsByOrderSn
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前订单的最新工作单信息
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 19:16
     * @Param: * @param order_sn
     * @Return: void
     */
    @Override
    public OrderTask getOrderTaskDetailsByOrderSn(String order_sn) {
        return this.orderTaskMapper.getOrderTaskDetailsByOrderSn(order_sn) != null ? this.orderTaskMapper.getOrderTaskDetailsByOrderSn(order_sn) : null;

    }

    /**
     * @Method: getOrderTaskIdByOrderSn
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据订单编号获得订单工作单id
     * @Return: int
     * @Exception:
     * @Date: 2021/3/5 16:04
     * @Param: * @param orderSn
     * @Return: int
     */
    @Override
    public int getOrderTaskIdByOrderSn(String orderSn) {
        return this.orderTaskMapper.getOrderTaskIdByOrderSn(orderSn) != 0 ? this.orderTaskMapper.getOrderTaskIdByOrderSn(orderSn) : 0;

    }
}
