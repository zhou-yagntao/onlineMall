package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: ProdPopularity
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品热度实体类
 * @Date: 14:55 2021/2/5
 * @Version: 1.0
 */
public class ProdPopularity {
    public Integer ppoId;
    public Integer pprodId;
    public  Integer popolarity;
    public  Integer pstoreId;

    public ProdPopularity(){super();}

    public ProdPopularity(Integer ppoId, Integer pprodId, Integer popolarity, Integer pstoreId) {
        this.ppoId = ppoId;
        this.pprodId = pprodId;
        this.popolarity = popolarity;
        this.pstoreId = pstoreId;
    }

    public Integer getPpoId() {
        return ppoId;
    }

    public void setPpoId(Integer ppoId) {
        this.ppoId = ppoId;
    }

    public Integer getPprodId() {
        return pprodId;
    }

    public void setPprodId(Integer pprodId) {
        this.pprodId = pprodId;
    }

    public Integer getPopolarity() {
        return popolarity;
    }

    public void setPopolarity(Integer popolarity) {
        this.popolarity = popolarity;
    }

    public Integer getPstoreId() {
        return pstoreId;
    }

    public void setPstoreId(Integer pstoreId) {
        this.pstoreId = pstoreId;
    }
}
