package com.zyt.service.storeProfitService;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.storeProfitService
 * @ClassName: StoreProfitInfoManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺收益信息管理服务层
 * @Date: 15:59 2021/3/4
 * @Version: 1.0
 */
public interface StoreProfitInfoManagerService {

    //处理店铺收益信息
    public void  handleStoreProfitInfo(String orderSn);
}
