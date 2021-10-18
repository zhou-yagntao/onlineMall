package com.zyt.service.seckillProductManagerService;

import com.zyt.entity.SecKillSession;
import com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo;

import java.util.List;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.seckillProductService
 * @ClassName: SeckillProductService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品服务层
 * @Date: 14:07 2021/3/12
 * @Version: 1.0
 */
public interface SeckillProductService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:处理上架最近三条的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 14:10
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillSession> handleUploadSeckillProductLatestThreeDay();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前时间下所有的秒杀商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 20:25
      * @Param: * @param null
      * @Return:
      */
    public List<SecondKillProdDetailsRedisTo> getSecondKillProdInfoOfCurrentTime();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 判断当前商品是否参与秒杀
      * @Return:
      * @Exception:
      * @Date: 2021/3/13 14:40
      * @Param: * @param null
      * @Return:
      */
    public SecondKillProdDetailsRedisTo getIsJoinSecondKillOfCurrentProduct(String prodId);
}
