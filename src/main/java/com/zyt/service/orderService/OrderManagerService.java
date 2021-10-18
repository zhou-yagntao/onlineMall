package com.zyt.service.orderService;

import com.zyt.entity.Order;
import com.zyt.entity.vo.OrderSnAndOrderTotalAccountVo;
import com.zyt.entity.vo.PayAsyncVo;
import com.zyt.entity.vo.SubmitOrderResponseTo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.orderService
 * @ClassName: OrderManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单管理服务层
 * @Date: 16:14 2021/2/25
 * @Version: 1.0
 */
public interface OrderManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:生成防重复提交令牌
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 19:45
      * @Param: * @param null
      * @Return:
      */
    public String setAntiDuplicationTokenOfCurrentUser(String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:订单验证
      * @Return:
      * @Exception:
      * @Date: 2021/2/26 11:07
      * @Param: * @param null
      * @Return:
      */
    public SubmitOrderResponseTo getSubmitOrderInfos(String loginUserName,String antiDuplicationToken, String leaveMessage, String delivery, String packingCharge, String delayAddress, String acceptUser, String contactNum, String shouldPayPrice, String paymentMethods, Boolean isUseCouple, String integralValue, Boolean isUseIntegral, String coupleId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得订单的状态
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 14:22
      * @Param: * @param null
      * @Return:
      */
    public int getOrderStatusOfCurrOrderSn(String orderSn);

     /**
      * @Method: closeOrder
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:关闭订单服务层
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 16:16
      * @Param: * @param null
      * @Return:
      */
    public void closeOrder(Order order);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取准备支付的订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 12:36
      * @Param: * @param null
      * @Return:
      */
    public Order getOrderPayInfoByOrderSn(String orderSn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:处理修改订单信息数据
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 14:12
      * @Param: * @param null
      * @Return:
      */
    public String handleFinishedPayResult(PayAsyncVo payAsyncVo);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:封装支付界面信息数据
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 15:29
      * @Param: * @param null
      * @Return:
      */
    public OrderSnAndOrderTotalAccountVo getOrderSnAndOrderTotalAccountOfCurrentUser(String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前订单的用户名信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 15:04
      * @Param: * @param null
      * @Return:
      */
    public String getUserMemberNameOfCurrOrderSn(String orderSn);
}
