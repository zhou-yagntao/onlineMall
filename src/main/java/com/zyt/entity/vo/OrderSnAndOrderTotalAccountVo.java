package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: OrderSnAndOrderTotalAccount
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装订单支付页面数据
 * @Date: 15:28 2021/3/4
 * @Version: 1.0
 */
public class OrderSnAndOrderTotalAccountVo {

    private String OrderSn;

    private  Double totalAccount;

    public String getOrderSn() {
        return OrderSn;
    }

    public void setOrderSn(String orderSn) {
        OrderSn = orderSn;
    }

    public Double getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(Double totalAccount) {
        this.totalAccount = totalAccount;
    }
}
