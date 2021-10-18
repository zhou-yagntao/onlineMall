package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: OrderItemVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装锁定库存的结果
 * @Date: 18:53 2021/2/27
 * @Version: 1.0
 */
public class OrderItemLockVo {

    private Integer prodId;

    private Integer lockCount;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }
}
