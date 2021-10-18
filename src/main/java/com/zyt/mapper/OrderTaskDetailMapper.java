package com.zyt.mapper;

import com.zyt.entity.OrderTaskDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: OrderTaskDetailMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 17:04 2021/3/2
 * @Version: 1.0
 */
@Mapper
@Repository(value = "orderTaskDetailMapper")
public interface OrderTaskDetailMapper {

     /**
      * @Method: saveOrderTaskDetails
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存订单工作单详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 15:23
      * @Param: * @param null
      * @Return:
      */
    public void saveOrderTaskDetails(@Param("orderTaskDetail") OrderTaskDetail orderTaskDetail);

     /**
      * @Method: getOrderTaskDetailsById
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据id获得订单工作单详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 15:28
      * @Param: * @param null
      * @Return:
      */
    public OrderTaskDetail getOrderTaskDetailsById(@Param("detailsId") int detailsId);

     /**
      * @Method: updateOrderStockStatus
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改锁定 状态信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 15:39
      * @Param: * @param null
      * @Return:
      */
    public void updateOrderStockStatus(@Param("detailsId") Integer detailsId, @Param("status") int status);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前订单工作单未解锁的订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 19:24
      * @Param: * @param null
      * @Return:
      */
    public  List<OrderTaskDetail> getOrderTaskDetailsByTaskIdAndStatus(@Param("orderDetailsId") Integer orderDetailsId, @Param("status") int status);
}
