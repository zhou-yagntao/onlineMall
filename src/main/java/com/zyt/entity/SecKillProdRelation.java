package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SecKillProdRelation
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀场次关联的商品信息实体类
 * @Date: 15:14 2021/3/8
 * @Version: 1.0
 */
public class SecKillProdRelation {
    private Integer relation_id;
    private Integer  promotion_session_id;
    private Integer  prod_id;
    private Integer  seckill_price;
    private Integer  seckill_count;
    private Integer seckill_limit;
    private Integer seckill_sort;
    private  Integer store_id;

    public SecKillProdRelation(){super();}

    public SecKillProdRelation(Integer relation_id, Integer promotion_session_id, Integer prod_id, Integer seckill_price, Integer seckill_count, Integer seckill_limit, Integer seckill_sort, Integer store_id) {
        this.relation_id = relation_id;
        this.promotion_session_id = promotion_session_id;
        this.prod_id = prod_id;
        this.seckill_price = seckill_price;
        this.seckill_count = seckill_count;
        this.seckill_limit = seckill_limit;
        this.seckill_sort = seckill_sort;
        this.store_id = store_id;
    }

    public Integer getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(Integer relation_id) {
        this.relation_id = relation_id;
    }

    public Integer getPromotion_session_id() {
        return promotion_session_id;
    }

    public void setPromotion_session_id(Integer promotion_session_id) {
        this.promotion_session_id = promotion_session_id;
    }

    public Integer getProd_id() {
        return prod_id;
    }

    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }

    public Integer getSeckill_price() {
        return seckill_price;
    }

    public void setSeckill_price(Integer seckill_price) {
        this.seckill_price = seckill_price;
    }

    public Integer getSeckill_count() {
        return seckill_count;
    }

    public void setSeckill_count(Integer seckill_count) {
        this.seckill_count = seckill_count;
    }

    public Integer getSeckill_limit() {
        return seckill_limit;
    }

    public void setSeckill_limit(Integer seckill_limit) {
        this.seckill_limit = seckill_limit;
    }

    public Integer getSeckill_sort() {
        return seckill_sort;
    }

    public void setSeckill_sort(Integer seckill_sort) {
        this.seckill_sort = seckill_sort;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "SecKillProdRelation{" +
                "relation_id=" + relation_id +
                ", promotion_session_id=" + promotion_session_id +
                ", prod_id=" + prod_id +
                ", seckill_price=" + seckill_price +
                ", seckill_count=" + seckill_count +
                ", seckill_limit=" + seckill_limit +
                ", seckill_sort=" + seckill_sort +
                ", store_id=" + store_id +
                '}';
    }
}
