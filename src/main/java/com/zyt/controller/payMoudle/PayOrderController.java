package com.zyt.controller.payMoudle;

import com.alipay.api.AlipayApiException;
import com.sun.org.apache.regexp.internal.RE;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.payService.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.payMoudle
 * @ClassName: PayOrderController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单支付controller
 * @Date: 11:22 2021/3/4
 * @Version: 1.0
 */
@Controller
@RequestMapping("/payOrder")
public class PayOrderController {

    private Logger logger = LoggerFactory.getLogger(PayOrderListnerController.class);

    @Autowired
    private PayOrderService payOrderService;

    /**
     * @Method: Pay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:订单支付模块
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/4 11:24
     * @Param: * @param orderSn
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value = "/pay")
    @ResponseBody
    public Result Pay(String orderSn) throws AlipayApiException {
        logger.info("获得的订单编号wei:"+orderSn);
        String pay = this.payOrderService.pay(orderSn);
        return  pay != null ? Result.success(ResultCode.SUCCESS,pay) : Result.failure(ResultCode.FAILURE);
    }
}
