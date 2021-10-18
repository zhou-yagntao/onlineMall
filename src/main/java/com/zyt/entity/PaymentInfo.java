package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: PaymentInfo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付宝支付流水信息
 * @Date: 14:19 2021/3/4
 * @Version: 1.0
 */
public class PaymentInfo {
    private Integer id;
    private String order_sn;
    private Integer order_id;
    private String alipay_trade_no;
    private Double total_amount;
    private String  pay_subject;
    private String  payment_status;
    private Date create_time;
    private Date confirm_time;
    private String callback_content;
    private Date callback_time;

    public PaymentInfo(){super();}

    public PaymentInfo(Integer id, String order_sn, Integer order_id, String alipay_trade_no, Double total_amount, String pay_subject, String payment_status, Date create_time, Date confirm_time, String callback_content, Date callback_time) {
        this.id = id;
        this.order_sn = order_sn;
        this.order_id = order_id;
        this.alipay_trade_no = alipay_trade_no;
        this.total_amount = total_amount;
        this.pay_subject = pay_subject;
        this.payment_status = payment_status;
        this.create_time = create_time;
        this.confirm_time = confirm_time;
        this.callback_content = callback_content;
        this.callback_time = callback_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getAlipay_trade_no() {
        return alipay_trade_no;
    }

    public void setAlipay_trade_no(String alipay_trade_no) {
        this.alipay_trade_no = alipay_trade_no;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getPay_subject() {
        return pay_subject;
    }

    public void setPay_subject(String pay_subject) {
        this.pay_subject = pay_subject;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getCallback_content() {
        return callback_content;
    }

    public void setCallback_content(String callback_content) {
        this.callback_content = callback_content;
    }

    public Date getCallback_time() {
        return callback_time;
    }

    public void setCallback_time(Date callback_time) {
        this.callback_time = callback_time;
    }
}
