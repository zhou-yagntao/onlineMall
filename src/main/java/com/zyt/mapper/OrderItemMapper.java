package com.zyt.mapper;

import com.zyt.entity.OrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: OrderItemMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 该订单下商品信息持久层
 * @Date: 15:48 2021/2/25
 * @Version: 1.0
 */
@Mapper
@Repository(value = "orderItemMapper")
public interface OrderItemMapper {
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加订单项数据
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 17:39
      * @Param: * @param null
      * @Return:
      */
    public void saveOrderItemInfos(List<OrderItems> orderItems);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前订单下即将支付的订单项信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/4 12:48
     * @Param: * @param null
     * @Return:
     */
    public List<OrderItems> getOrderItemsPayInfoByOrderSn(@Param("orderSn") String orderSn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:通过订单号查询当前订单的数据信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 16:06
      * @Param: * @param null
      * @Return:
      */
    public List<OrderItems> getOrderItemsInfoByOrderSn(@Param("orderSn") String orderSn);
}
