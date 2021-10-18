package com.zyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Order
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单详情实体类
 * @Date: 10:10 2021/2/7
 * @Version: 1.0
 */
public class Order {

    private Integer order_id;

    private Integer member_id;

    private String order_sn;

    private Integer coupon_id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date create_time;

    private String member_username;

    private Double total_amount;

    private Double pay_amount;

    private Double freight_amount;

    private Double promotion_amount;

    private Integer integration_amount;

    private Integer coupon_amount;

    private Double discount_amount;

    private String pay_type;

    private Integer source_type;

    private Integer order_status;

    private String delivery_company;

    private Integer auto_confirm_time;

    private Integer integration;

    private Integer growth;

    private Integer bill_type;

    private String bill_header;

    private String bill_content;

    private String bill_receiver_phone;

    private String bill_receiver_email;

    private String receiver_name;

    private String receiver_phone;

    private String receiver_province;

    private String receiver_city;

    private String receiver_region;

    private String receiver_detail_address;

    private String note;

    private  Integer confirm_status;

    private  Integer delete_status;

    private  Integer use_integration;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private  Date payment_time;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date delivery_time;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date receive_time;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date comment_time;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT+8")
    private Date modify_time;

    public  Order(){super();}

    public Order(Integer order_id, Integer member_id, String order_sn, Integer coupon_id, Date create_time, String member_username, Double total_amount, Double pay_amount, Double freight_amount, Double promotion_amount, Integer integration_amount, Integer coupon_amount, Double discount_amount, String pay_type, Integer source_type, Integer order_status, String delivery_company, Integer auto_confirm_time, Integer integration, Integer growth, Integer bill_type, String bill_header, String bill_content, String bill_receiver_phone, String bill_receiver_email, String receiver_name, String receiver_phone, String receiver_province, String receiver_city, String receiver_region, String receiver_detail_address, String note, Integer confirm_status, Integer delete_status, Integer use_integration, Date payment_time, Date delivery_time, Date receive_time, Date comment_time, Date modify_time) {
        this.order_id = order_id;
        this.member_id = member_id;
        this.order_sn = order_sn;
        this.coupon_id = coupon_id;
        this.create_time = create_time;
        this.member_username = member_username;
        this.total_amount = total_amount;
        this.pay_amount = pay_amount;
        this.freight_amount = freight_amount;
        this.promotion_amount = promotion_amount;
        this.integration_amount = integration_amount;
        this.coupon_amount = coupon_amount;
        this.discount_amount = discount_amount;
        this.pay_type = pay_type;
        this.source_type = source_type;
        this.order_status = order_status;
        this.delivery_company = delivery_company;
        this.auto_confirm_time = auto_confirm_time;
        this.integration = integration;
        this.growth = growth;
        this.bill_type = bill_type;
        this.bill_header = bill_header;
        this.bill_content = bill_content;
        this.bill_receiver_phone = bill_receiver_phone;
        this.bill_receiver_email = bill_receiver_email;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_province = receiver_province;
        this.receiver_city = receiver_city;
        this.receiver_region = receiver_region;
        this.receiver_detail_address = receiver_detail_address;
        this.note = note;
        this.confirm_status = confirm_status;
        this.delete_status = delete_status;
        this.use_integration = use_integration;
        this.payment_time = payment_time;
        this.delivery_time = delivery_time;
        this.receive_time = receive_time;
        this.comment_time = comment_time;
        this.modify_time = modify_time;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getMember_username() {
        return member_username;
    }

    public void setMember_username(String member_username) {
        this.member_username = member_username;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(Double pay_amount) {
        this.pay_amount = pay_amount;
    }

    public Double getFreight_amount() {
        return freight_amount;
    }

    public void setFreight_amount(Double freight_amount) {
        this.freight_amount = freight_amount;
    }

    public Double getPromotion_amount() {
        return promotion_amount;
    }

    public void setPromotion_amount(Double promotion_amount) {
        this.promotion_amount = promotion_amount;
    }

    public Integer getIntegration_amount() {
        return integration_amount;
    }

    public void setIntegration_amount(Integer integration_amount) {
        this.integration_amount = integration_amount;
    }

    public Integer getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(Integer coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public Double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(Double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getSource_type() {
        return source_type;
    }

    public void setSource_type(Integer source_type) {
        this.source_type = source_type;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public String getDelivery_company() {
        return delivery_company;
    }

    public void setDelivery_company(String delivery_company) {
        this.delivery_company = delivery_company;
    }

    public Integer getAuto_confirm_time() {
        return auto_confirm_time;
    }

    public void setAuto_confirm_time(Integer auto_confirm_time) {
        this.auto_confirm_time = auto_confirm_time;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getBill_type() {
        return bill_type;
    }

    public void setBill_type(Integer bill_type) {
        this.bill_type = bill_type;
    }

    public String getBill_header() {
        return bill_header;
    }

    public void setBill_header(String bill_header) {
        this.bill_header = bill_header;
    }

    public String getBill_content() {
        return bill_content;
    }

    public void setBill_content(String bill_content) {
        this.bill_content = bill_content;
    }

    public String getBill_receiver_phone() {
        return bill_receiver_phone;
    }

    public void setBill_receiver_phone(String bill_receiver_phone) {
        this.bill_receiver_phone = bill_receiver_phone;
    }

    public String getBill_receiver_email() {
        return bill_receiver_email;
    }

    public void setBill_receiver_email(String bill_receiver_email) {
        this.bill_receiver_email = bill_receiver_email;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_region() {
        return receiver_region;
    }

    public void setReceiver_region(String receiver_region) {
        this.receiver_region = receiver_region;
    }

    public String getReceiver_detail_address() {
        return receiver_detail_address;
    }

    public void setReceiver_detail_address(String receiver_detail_address) {
        this.receiver_detail_address = receiver_detail_address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getConfirm_status() {
        return confirm_status;
    }

    public void setConfirm_status(Integer confirm_status) {
        this.confirm_status = confirm_status;
    }

    public Integer getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(Integer delete_status) {
        this.delete_status = delete_status;
    }

    public Integer getUse_integration() {
        return use_integration;
    }

    public void setUse_integration(Integer use_integration) {
        this.use_integration = use_integration;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Date getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(Date receive_time) {
        this.receive_time = receive_time;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", member_id=" + member_id +
                ", order_sn='" + order_sn + '\'' +
                ", coupon_id=" + coupon_id +
                ", create_time=" + create_time +
                ", member_username='" + member_username + '\'' +
                ", total_amount=" + total_amount +
                ", pay_amount=" + pay_amount +
                ", freight_amount=" + freight_amount +
                ", promotion_amount=" + promotion_amount +
                ", integration_amount=" + integration_amount +
                ", coupon_amount=" + coupon_amount +
                ", discount_amount=" + discount_amount +
                ", pay_type='" + pay_type + '\'' +
                ", source_type=" + source_type +
                ", order_status=" + order_status +
                ", delivery_company='" + delivery_company + '\'' +
                ", auto_confirm_time=" + auto_confirm_time +
                ", integration=" + integration +
                ", growth=" + growth +
                ", bill_type=" + bill_type +
                ", bill_header='" + bill_header + '\'' +
                ", bill_content='" + bill_content + '\'' +
                ", bill_receiver_phone='" + bill_receiver_phone + '\'' +
                ", bill_receiver_email='" + bill_receiver_email + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_phone='" + receiver_phone + '\'' +
                ", receiver_province='" + receiver_province + '\'' +
                ", receiver_city='" + receiver_city + '\'' +
                ", receiver_region='" + receiver_region + '\'' +
                ", receiver_detail_address='" + receiver_detail_address + '\'' +
                ", note='" + note + '\'' +
                ", confirm_status=" + confirm_status +
                ", delete_status=" + delete_status +
                ", use_integration=" + use_integration +
                ", payment_time=" + payment_time +
                ", delivery_time=" + delivery_time +
                ", receive_time=" + receive_time +
                ", comment_time=" + comment_time +
                ", modify_time=" + modify_time +
                '}';
    }
}
