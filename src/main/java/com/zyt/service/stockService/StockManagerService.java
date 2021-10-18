package com.zyt.service.stockService;

import com.zyt.entity.to.mq.OrderInfoTo;
import com.zyt.entity.to.mq.StockLockTo;
import com.zyt.entity.vo.OrderItemLockVo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.stockService
 * @ClassName: StockManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 库存服务层
 * @Date: 12:53 2021/2/28
 * @Version: 1.0
 */
public interface StockManagerService {

    public Boolean orderLockStock(String orderSn, List<OrderItemLockVo> orderItemLockVos);

    public void unLockStock(StockLockTo stockLockTo);

    public void unLockStock(OrderInfoTo orderInfoTo);
}
