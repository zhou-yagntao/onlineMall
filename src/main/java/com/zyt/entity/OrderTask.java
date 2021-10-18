package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: OrdeTask
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 17:11 2021/3/2
 * @Version: 1.0
 */
public class OrderTask {

    private Integer id;
    private Integer order_id;
    private String order_sn ;
    private String consignee;
    private String consignee_tel;
    private String delivery_address;
    private String order_comment;
    private String payment_way;
    private Integer task_status;
    private String order_body;
    private Date create_time ;
    private String task_comment;

    public OrderTask() {
        super();
    }

    public OrderTask(Integer id, Integer order_id, String order_sn, String consignee, String consignee_tel, String delivery_address, String order_comment, String payment_way, Integer task_status, String order_body, Date create_time, String task_comment) {
        this.id = id;
        this.order_id = order_id;
        this.order_sn = order_sn;
        this.consignee = consignee;
        this.consignee_tel = consignee_tel;
        this.delivery_address = delivery_address;
        this.order_comment = order_comment;
        this.payment_way = payment_way;
        this.task_status = task_status;
        this.order_body = order_body;
        this.create_time = create_time;
        this.task_comment = task_comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee_tel() {
        return consignee_tel;
    }

    public void setConsignee_tel(String consignee_tel) {
        this.consignee_tel = consignee_tel;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getOrder_comment() {
        return order_comment;
    }

    public void setOrder_comment(String order_comment) {
        this.order_comment = order_comment;
    }

    public String getPayment_way() {
        return payment_way;
    }

    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
    }

    public Integer getTask_status() {
        return task_status;
    }

    public void setTask_status(Integer task_status) {
        this.task_status = task_status;
    }

    public String getOrder_body() {
        return order_body;
    }

    public void setOrder_body(String order_body) {
        this.order_body = order_body;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getTask_comment() {
        return task_comment;
    }

    public void setTask_comment(String task_comment) {
        this.task_comment = task_comment;
    }
}
