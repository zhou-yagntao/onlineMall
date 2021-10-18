package com.zyt.service.seckillProductScheduleManagerService;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.seckillProductScheduleManagerService
 * @ClassName: SeckillProductScheduleService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品定时任务服务上架功能服务层
 * @Date: 13:54 2021/3/12
 * @Version: 1.0
 */
public interface SeckillProductScheduleService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:上架最新三天的秒杀商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 13:59
      * @Param: * @param null
      * @Return:
      */
     public void uploadSeckillProductLatestThreeDay();


}
