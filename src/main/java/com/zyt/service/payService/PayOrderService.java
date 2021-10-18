package com.zyt.service.payService;

import com.alipay.api.AlipayApiException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.payService
 * @ClassName: PayOrderService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付管理服务层
 * @Date: 12:29 2021/3/4
 * @Version: 1.0
 */
public interface PayOrderService {

    public String  pay(String orderSn) throws AlipayApiException;

}
