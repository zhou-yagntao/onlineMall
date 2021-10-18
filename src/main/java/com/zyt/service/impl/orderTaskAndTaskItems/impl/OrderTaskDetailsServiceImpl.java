package com.zyt.service.impl.orderTaskAndTaskItems.impl;

import com.zyt.entity.OrderTaskDetail;
import com.zyt.mapper.OrderTaskDetailMapper;
import com.zyt.service.orderTaskAndTaskItems.OrderTaskDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.orderTaskAndTaskItems.impl
 * @ClassName: OrderTaskDetailsServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单工作单明细表服务层实现模块
 * @Date: 17:25 2021/3/2
 * @Version: 1.0
 */
@Service(value = "orderTaskDetailsService")
public class OrderTaskDetailsServiceImpl implements OrderTaskDetailsService {

    @Autowired
    private OrderTaskDetailMapper orderTaskDetailMapper;

    /**
     * @Method: saveOrderTaskDetails
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 11:08
     * @Param: * @param orderTaskDetail
     * @Return: void
     */
    @Override
    public void saveOrderTaskDetails(OrderTaskDetail orderTaskDetail) {
        this.orderTaskDetailMapper.saveOrderTaskDetails(orderTaskDetail);
    }

    /**
     * @Method: getOrderTaskDetailsById
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id查询订单工作单信息
     * @Return: com.zyt.entity.OrderTaskDetail
     * @Exception:
     * @Date: 2021/3/3 14:02
     * @Param: * @param detailsId
     * @Return: com.zyt.entity.OrderTaskDetail
     */
    @Override
    public OrderTaskDetail getOrderTaskDetailsById(int detailsId) {
        return this.orderTaskDetailMapper.getOrderTaskDetailsById(detailsId) != null ? this.orderTaskDetailMapper.getOrderTaskDetailsById(detailsId) : null;
    }

    /**
     * @Method: updateOrderStockStatus
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改订单锁定状态信息
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 15:39
     * @Param: * @param id
     * @param status
     * @Return: void
     */
    @Override
    public void updateOrderStockStatus(Integer id, int status) {
        this.orderTaskDetailMapper.updateOrderStockStatus(id,status);
    }

    /**
     * @Method: getOrderTaskDetailsByTaskIdAndStatus
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前订单未解所的所有订单信息
     * @Return: java.util.List<com.zyt.entity.OrderTaskDetail>
     * @Exception:
     * @Date: 2021/3/3 19:23
     * @Param: * @param orderDetailsId
     * @param status
     * @Return: java.util.List<com.zyt.entity.OrderTaskDetail>
     */
    @Override
    public List<OrderTaskDetail> getOrderTaskDetailsByTaskIdAndStatus(Integer orderDetailsId, int status) {
        return this.orderTaskDetailMapper.getOrderTaskDetailsByTaskIdAndStatus(orderDetailsId,status) != null ? this.orderTaskDetailMapper.getOrderTaskDetailsByTaskIdAndStatus(orderDetailsId,status) :null;

    }
}
