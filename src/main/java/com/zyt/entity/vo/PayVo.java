package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: PayVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付页面信息
 * @Date: 22:31 2021/3/3
 * @Version: 1.0
 */
public class PayVo {
    private String out_trade_no; // 商户订单号 必填

    private String subject; // 订单名称 必填

    private String total_amount;  // 付款金额 必填

    private String body; // 商品描述 可空

    public PayVo(){super();}

    public PayVo(String out_trade_no, String subject, String total_amount, String body) {
        this.out_trade_no = out_trade_no;
        this.subject = subject;
        this.total_amount = total_amount;
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
