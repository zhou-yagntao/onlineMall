package com.zyt.service.orderTaskAndTaskItems;

import com.zyt.entity.OrderTaskDetail;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.orderTaskAndTaskItems
 * @ClassName: OrderTaskDetailsService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单工作单详情信息
 * @Date: 17:23 2021/3/2
 * @Version: 1.0
 */
public interface OrderTaskDetailsService {

    //保存订单工作单详情信息
    public void saveOrderTaskDetails(OrderTaskDetail orderTaskDetail);

    //查询订单工作单信息
    public OrderTaskDetail getOrderTaskDetailsById(int detailsId);

    //修改库存状态信息
    public void updateOrderStockStatus(Integer id, int status);

    //获得当前订单工作单中未解锁的订单信息
    public List<OrderTaskDetail> getOrderTaskDetailsByTaskIdAndStatus(Integer orderDetailsId, int status);
}
