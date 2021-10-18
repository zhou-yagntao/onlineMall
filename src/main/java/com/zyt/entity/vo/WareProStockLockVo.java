package com.zyt.entity.vo;

import com.zyt.entity.OrderItems;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: WareProStockLockVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品锁库存封装数据
 * @Date: 18:39 2021/2/27
 * @Version: 1.0
 */
public class WareProStockLockVo {

    private String orderSn;

    private List<OrderItems> locks;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public List<OrderItems> getLocks() {
        return locks;
    }

    public void setLocks(List<OrderItems> locks) {
        this.locks = locks;
    }
}
