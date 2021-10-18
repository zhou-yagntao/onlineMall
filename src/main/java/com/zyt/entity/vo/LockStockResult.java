package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: LockStockResult
 * @Author: zhou_yangtao@yeah.net
 * @Description: 库存锁定结果封装
 * @Date: 18:46 2021/2/27
 * @Version: 1.0
 */
public class LockStockResult {

    private Integer prodId;

    private Integer num;

    private Boolean locked;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean isLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
