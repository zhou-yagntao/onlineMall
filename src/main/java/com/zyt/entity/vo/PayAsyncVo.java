package com.zyt.entity.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: PayAsyncVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付宝支付成功返回的异步数据
 * @Date: 14:06 2021/3/4
 * @Version: 1.0
 */
public class PayAsyncVo {

    private String gmt_create;

    private String charset;

    private String gmt_payment;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date notify_time;

    private String subject;

    private String sign;

    private String buyer_id;//支付者的id

    private String body;//订单的信息

    private String invoice_amount;//支付金额

    private String version;

    private String notify_id;//通知id

    private String fund_bill_list;

    private String notify_type;//通知类型； trade_status_sync

    private String out_trade_no;//订单号

    private String total_amount;//支付的总额

    private String trade_status;//交易状态  TRADE_SUCCESS

    private String trade_no;//流水号

    private String auth_app_id;

    private String receipt_amount;//商家收到的款

    private String point_amount;

    private String app_id;//应用id

    private String buyer_pay_amount;//最终支付的金额

    private String sign_type;//签名类型

    private String seller_id;//商家的id


    public  PayAsyncVo(){super();}

    public PayAsyncVo(String gmt_create, String charset, String gmt_payment, Date notify_time, String subject, String sign, String buyer_id, String body, String invoice_amount, String version, String notify_id, String fund_bill_list, String notify_type, String out_trade_no, String total_amount, String trade_status, String trade_no, String auth_app_id, String receipt_amount, String point_amount, String app_id, String buyer_pay_amount, String sign_type, String seller_id) {
        this.gmt_create = gmt_create;
        this.charset = charset;
        this.gmt_payment = gmt_payment;
        this.notify_time = notify_time;
        this.subject = subject;
        this.sign = sign;
        this.buyer_id = buyer_id;
        this.body = body;
        this.invoice_amount = invoice_amount;
        this.version = version;
        this.notify_id = notify_id;
        this.fund_bill_list = fund_bill_list;
        this.notify_type = notify_type;
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.trade_status = trade_status;
        this.trade_no = trade_no;
        this.auth_app_id = auth_app_id;
        this.receipt_amount = receipt_amount;
        this.point_amount = point_amount;
        this.app_id = app_id;
        this.buyer_pay_amount = buyer_pay_amount;
        this.sign_type = sign_type;
        this.seller_id = seller_id;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }


    public Date getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(Date notify_time) {
        this.notify_time = notify_time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getFund_bill_list() {
        return fund_bill_list;
    }

    public void setFund_bill_list(String fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    @Override
    public String toString() {
        return "PayAsyncVo{" +
                "gmt_create='" + gmt_create + '\'' +
                ", charset='" + charset + '\'' +
                ", gmt_payment='" + gmt_payment + '\'' +
                ", notify_time='" + notify_time + '\'' +
                ", subject='" + subject + '\'' +
                ", sign='" + sign + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", body='" + body + '\'' +
                ", invoice_amount='" + invoice_amount + '\'' +
                ", version='" + version + '\'' +
                ", notify_id='" + notify_id + '\'' +
                ", fund_bill_list='" + fund_bill_list + '\'' +
                ", notify_type='" + notify_type + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", trade_status='" + trade_status + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", auth_app_id='" + auth_app_id + '\'' +
                ", receipt_amount='" + receipt_amount + '\'' +
                ", point_amount='" + point_amount + '\'' +
                ", app_id='" + app_id + '\'' +
                ", buyer_pay_amount='" + buyer_pay_amount + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", seller_id='" + seller_id + '\'' +
                '}';
    }
}
