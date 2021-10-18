package com.zyt.controller.payMoudle;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zyt.config.AlipayConfig;
import com.zyt.constant.AlipayConstant;
import com.zyt.entity.vo.PayAsyncVo;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.storeProfitService.StoreProfitInfoManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.payMoudle
 * @ClassName: PayOrderListnerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单支付成功响应监听器
 * @Date: 13:48 2021/3/4
 * @Version: 1.0
 */
@RestController
public class PayOrderListnerController {

    private Logger logger = LoggerFactory.getLogger(PayOrderListnerController.class);

    @Autowired
    private OrderManagerService orderManagerService;

    @Autowired
    private AlipayConfig alipayConfig;


    /**
     * @Method: HandleSuccessedAlipayNotify
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理订单支付成功的结果
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/3/4 14:10
     * @Param: * @param payAsyncVo
     * @param request
     * @Return: java.lang.String
     */
    @PostMapping("/payOrderListner/handleSuccessedAlipayNotify")
    public String HandleSuccessedAlipayNotify(PayAsyncVo payAsyncVo, HttpServletRequest request) {
        logger.info("即将准备结果");
        logger.info("http://127.0.0.1:9000/payOrderListner/handleSuccessedAlipayNotify");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        PayAsyncVo payAsyncVoInfo = new PayAsyncVo();

        Iterator<String> iter = requestParams.keySet().iterator();
        while(iter.hasNext()){
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
                logger.info("支付宝返回结果为:"+"name:"+name+"value:"+valueStr);
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //验签
//        boolean signVerfied = false;
//        try {
//            signVerfied = AlipaySignature.rsaCertCheckV1(params, AlipayConstant.ALIPAY_PUBLIC_KEY,AlipayConstant.ALIPAY_CHAR_SET,AlipayConstant.ALIPAY_SIGN_TYPE);
//            //signVerfied = AlipaySignature.rsaCertCheckV1(params,this.alipayConfig.getAlipay_public_key(),this.alipayConfig.getCharset(),"RSA2");
//        } catch (AlipayApiException e) {
//            logger.error("错误信息:"+e.getMessage());
//            e.printStackTrace();
//        }
        /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
        */

        //验签成功则处理后续逻辑
//        if (signVerfied){
//            logger.info("返回订单状态了");
//            //由于支付宝支付是采用了最大努力尝试的方法将支付成功的结果进行返回
//            //TODO 只要我们收到了支付宝给我们的异步通知 告诉我们订单支付成功 返回success 支付宝服务器才不会发通知告诉我们未成功支付
//            String result = this.orderManagerService.handleFinishedPayResult(payAsyncVo);
//            logger.info("成功完成订单支付结果返回结果:"+result);
//            return  result;
//        }else{
//            return "error";
//        }
            logger.info("返回订单状态了");
            //由于支付宝支付是采用了最大努力尝试的方法将支付成功的结果进行返回
            //TODO 只要我们收到了支付宝给我们的异步通知 告诉我们订单支付成功 返回success 支付宝服务器才不会发通知告诉我们未成功支付
            String result = this.orderManagerService.handleFinishedPayResult(payAsyncVo);
            logger.info("成功完成订单支付结果返回结果:"+result);
            return  result;
    }
}
