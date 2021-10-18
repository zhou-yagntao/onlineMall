package com.zyt.mapper;

import com.zyt.entity.OrderTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: OrdeTaskMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单工作单持久层
 * @Date: 17:14 2021/3/2
 * @Version: 1.0
 */
@Mapper
@Repository(value = "ordeTaskMapper")
public interface OrdeTaskMapper {

     /**
      * @Method: saveOrderTask
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存订单工作单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 15:14
      * @Param: * @param null
      * @Return:
      */
    public void saveOrderTask(@Param("orderTask") OrderTask orderTask);

     /**
      * @Method: getOrderSnByOrderTaskId
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得订单号
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 15:21
      * @Param: * @param null
      * @Return:
      */
    public String getOrderSnByOrderTaskId(@Param("orderTaskId") Integer orderTaskId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得订单工作单详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 19:18
      * @Param: * @param null
      * @Return:
      */
    public OrderTask getOrderTaskDetailsByOrderSn(@Param("orderSn") String orderSn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得订单工作单id
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 16:05
      * @Param: * @param null
      * @Return:
      */
    public int getOrderTaskIdByOrderSn(@Param("orderSn") String orderSn);
}
