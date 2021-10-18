package com.zyt.service.impl.paymentService.impl;

import com.zyt.entity.PaymentInfo;
import com.zyt.entity.vo.PayAsyncVo;
import com.zyt.mapper.PaymentInfoMapper;
import com.zyt.service.paymentService.PayMentInfoService;
import com.zyt.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.paymentService.impl
 * @ClassName: PayMentInfoServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单流失信息服务层实现模块
 * @Date: 14:31 2021/3/4
 * @Version: 1.0
 */
@Service(value = "payMentInfoService")
public class PayMentInfoServiceImpl implements PayMentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public void savePayMentInfo(PayAsyncVo payAsyncVo) {
        PaymentInfo paymentInfo  = new PaymentInfo();
        //封装结果
        //支付宝流水号
        paymentInfo.setAlipay_trade_no(payAsyncVo.getTrade_no());
        //创建时间
        try {
            paymentInfo.setCreate_time(payAsyncVo.getNotify_time());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //订单号
        paymentInfo.setOrder_sn(payAsyncVo.getOut_trade_no());
        //交易状态
        paymentInfo.setPayment_status(payAsyncVo.getTrade_status());
        //交易金额
        paymentInfo.setTotal_amount(Double.parseDouble(payAsyncVo.getBuyer_pay_amount()));
        this.paymentInfoMapper.savePayMentInfo(paymentInfo);
    }
}
