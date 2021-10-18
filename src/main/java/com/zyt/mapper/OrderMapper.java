package com.zyt.mapper;

import com.zyt.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: OrderMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单持久层模块
 * @Date: 15:47 2021/2/25
 * @Version: 1.0
 */
@Mapper
@Repository(value = "orderMapper")
public interface OrderMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 17:26
      * @Param: * @param null
      * @Return:
      */
    public void saveOrderInfo(@Param("order") Order order);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据订单号查询当前订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 14:23
      * @Param: * @param null
      * @Return:
      */
    public int getOrderStatusOfCurrOrderSn(@Param("orderSn") String orderSn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据id获得订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 16:41
      * @Param: * @param null
      * @Return:
      */
    public Order getOrderInfoByOrderId(@Param("orderId") Integer orderId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改订单支付状态
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 16:48
      * @Param: * @param null
      * @Return:
      */
    public int updateOrderInfoByOrderById(@Param("orderId") Integer orderId, @Param("code") Integer code);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得准备支付的订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 12:38
      * @Param: * @param null
      * @Return:
      */
    public Order getOrderPayInfoByOrderSn(@Param("orderSn") String orderSn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改当前订单的订单状态信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 14:56
      * @Param: * @param null
      * @Return:
      */
    public void handleUpdateOrderStatus(@Param("outTradeNo") String outTradeNo, @Param("code") int code);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的所有订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 15:33
      * @Param: * @param null
      * @Return:
      */
    public Order getOrderInfoOfCurrentUser(@Param("userName") String userName,@Param("code")int code);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前订单的用户名信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 15:06
      * @Param: * @param null
      * @Return:
      */
    public String getUserMemberNameOfCurrOrderSn(@Param("orderSn") String orderSn);
}
