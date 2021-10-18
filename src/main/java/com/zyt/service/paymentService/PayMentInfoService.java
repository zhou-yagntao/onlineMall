package com.zyt.service.paymentService;

import com.zyt.entity.vo.PayAsyncVo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.paymentService
 * @ClassName: PayMentInfoService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付宝流水信息
 * @Date: 14:29 2021/3/4
 * @Version: 1.0
 */
public interface PayMentInfoService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存订单流失信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 14:30
      * @Param: * @param null
      * @Return:
      */
    public void savePayMentInfo(PayAsyncVo payAsyncVo);
}
