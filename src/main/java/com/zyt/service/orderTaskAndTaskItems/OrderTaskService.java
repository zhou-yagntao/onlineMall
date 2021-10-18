package com.zyt.service.orderTaskAndTaskItems;

import com.zyt.entity.OrderTask;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.orderTaskAndTaskItems
 * @ClassName: OrderTaskService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单工作单信息
 * @Date: 17:23 2021/3/2
 * @Version: 1.0
 */
public interface OrderTaskService {

     /**
      * @Method: SaveOrderTask
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加订单工作单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 14:17
      * @Param: * @param null
      * @Return:
      */
    public void SaveOrderTask(OrderTask orderTask);

     /**
      * @Method: getOrderSnByOrderTaskId
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据id获得订单信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 14:17
      * @Param: * @param null
      * @Return:
      */
    public String getOrderSnByOrderTaskId(Integer id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得最新的订单工作单id
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 19:15
      * @Param: * @param null
      * @Return:
      */
    public OrderTask  getOrderTaskDetailsByOrderSn(String order_sn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据订单编号获得订单工作单id
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 16:04
      * @Param: * @param null
      * @Return:
      */
    public int getOrderTaskIdByOrderSn(String orderSn);
}
