package com.zyt.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyt.entity.SecKillProdRelation;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: SeckillProductAndSessionInfoVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品场次以及当前场次商品信息
 * @Date: 15:28 2021/3/12
 * @Version: 1.0
 */
public class SeckillProductAndSessionInfoVo {
    private Integer sec_kill_id;

    private String sec_kill_name;

    private Date start_time;

    private Date end_time;

    private Boolean enable_status;

    private Date create_time;

    private Integer store_id;

    private List<SecKillProdRelation> secKillProdRelationList;

    public SeckillProductAndSessionInfoVo(){super();}

    public SeckillProductAndSessionInfoVo(Integer sec_kill_id, String sec_kill_name, Date start_time, Date end_time, Boolean enable_status, Date create_time, Integer store_id, List<SecKillProdRelation> secKillProdRelationList) {
        this.sec_kill_id = sec_kill_id;
        this.sec_kill_name = sec_kill_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.enable_status = enable_status;
        this.create_time = create_time;
        this.store_id = store_id;
        this.secKillProdRelationList = secKillProdRelationList;
    }

    public Integer getSec_kill_id() {
        return sec_kill_id;
    }

    public void setSec_kill_id(Integer sec_kill_id) {
        this.sec_kill_id = sec_kill_id;
    }

    public String getSec_kill_name() {
        return sec_kill_name;
    }

    public void setSec_kill_name(String sec_kill_name) {
        this.sec_kill_name = sec_kill_name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Boolean getEnable_status() {
        return enable_status;
    }

    public void setEnable_status(Boolean enable_status) {
        this.enable_status = enable_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public List<SecKillProdRelation> getSecKillProdRelationList() {
        return secKillProdRelationList;
    }

    public void setSecKillProdRelationList(List<SecKillProdRelation> secKillProdRelationList) {
        this.secKillProdRelationList = secKillProdRelationList;
    }

    @Override
    public String toString() {
        return "SeckillProductAndSessionInfoVo{" +
                "sec_kill_id=" + sec_kill_id +
                ", sec_kill_name='" + sec_kill_name + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", enable_status=" + enable_status +
                ", create_time=" + create_time +
                ", store_id=" + store_id +
                ", secKillProdRelationList=" + secKillProdRelationList +
                '}';
    }
}
