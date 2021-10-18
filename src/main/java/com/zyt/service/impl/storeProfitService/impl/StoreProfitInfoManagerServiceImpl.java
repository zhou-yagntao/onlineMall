package com.zyt.service.impl.storeProfitService.impl;

import com.zyt.entity.OrderItems;
import com.zyt.entity.StoreProfit;
import com.zyt.mapper.OrderItemMapper;
import com.zyt.mapper.StoreProfitMapper;
import com.zyt.service.storeProfitService.StoreProfitInfoManagerService;
import com.zyt.service.storeService.NewStoreSettledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.storeProfitService.impl
 * @ClassName: StoreProfitInfoManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺收益信息服务实现层
 * @Date: 16:00 2021/3/4
 * @Version: 1.0
 */
@Service(value = "storeProfitInfoManagerService")
public class StoreProfitInfoManagerServiceImpl implements StoreProfitInfoManagerService {

    private Logger logger  = LoggerFactory.getLogger(StoreProfitInfoManagerServiceImpl.class);

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private NewStoreSettledService storeService;

    @Autowired
    private StoreProfitMapper storeProfitMapper;

    /**
     * @Method: handleStoreProfitInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理当前店铺的收益信息
     * @Return: void
     * @Exception:
     * @Date: 2021/3/4 16:01
     * @Param: * @param orderSn
     * @Return: void
     */
    @Override
    @Transactional
    public void handleStoreProfitInfo(String orderSn) {
        List<OrderItems> orderItemsList  = this.orderItemMapper.getOrderItemsInfoByOrderSn(orderSn);
        List<StoreProfit> storeProfits = orderItemsList.stream().map(orderItems -> {
          StoreProfit storeProfit = new StoreProfit();
          //根据店铺名称查询当前店铺id号
          int storeId = this.storeService.getStoreIdByStoreName(orderItems.getProd_Store());
          storeProfit.setStore_Id(storeId);
          storeProfit.setProfit(Double.valueOf(orderItems.getProd_price() * orderItems.getProd_quantity()));
          storeProfit.setGetProfitDate(new Date());
          return storeProfit;
        }).collect(Collectors.toList());
        logger.info("店铺收益为:"+storeProfits);
        //进行店铺收益汇总
        for (StoreProfit storeProfit:storeProfits){
            //判断当前店铺是否已经有收益
            StoreProfit storeProfitInfo  = this.storeProfitMapper.getStoreProfitInfoOfCurrStoreId(storeProfit.getStore_Id());
            if(storeProfit == null){
                //为空 则向数据库添加
                this.storeProfitMapper.saveStoreProfitInfoOfCurrStoreId(storeProfit);
            }else{
                //表示当前店铺已经存在收益 则进行修改
                this.storeProfitMapper.updateStoreProfitInfoOfCurrStoreId(storeProfit);
            }
        }
    }
}
