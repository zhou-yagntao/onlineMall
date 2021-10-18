package com.zyt.entity.vo;

import com.zyt.entity.Order;
import com.zyt.entity.OrderItems;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: SubmitOrderResponse
 * @Author: zhou_yangtao@yeah.net
 * @Description: 提交订单信息返回结果
 * @Date: 11:05 2021/2/26
 * @Version: 1.0
 */
public class SubmitOrderResponseTo {

    private Order order;

    private Integer code;

    public SubmitOrderResponseTo(){super();}


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SubmitOrderResponseTo{" +
                "order=" + order +
                ", code=" + code +
                '}';
    }
}
