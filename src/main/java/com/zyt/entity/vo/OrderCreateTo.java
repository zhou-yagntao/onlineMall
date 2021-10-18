package com.zyt.entity.vo;

import com.zyt.entity.Order;
import com.zyt.entity.OrderItems;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: OrderCreateTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单创建实体类
 * @Date: 11:09 2021/2/27
 * @Version: 1.0
 */
public class OrderCreateTo {

    private Order order;

    private List<OrderItems> orderItemsList;

    //应付价格
    private Double payPrice;

    private Double payFree;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Double getPayFree() {
        return payFree;
    }

    public void setPayFree(Double payFree) {
        this.payFree = payFree;
    }
}
