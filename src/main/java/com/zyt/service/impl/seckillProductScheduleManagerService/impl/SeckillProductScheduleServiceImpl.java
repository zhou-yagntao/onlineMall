package com.zyt.service.impl.seckillProductScheduleManagerService.impl;

import com.zyt.constant.SecondKillConstant;
import com.zyt.entity.Product;
import com.zyt.entity.SecKillProdRelation;
import com.zyt.entity.SecKillSession;
import com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo;
import com.zyt.entity.vo.ProductDetailsVo;
import com.zyt.entity.vo.SeckillProductAndSessionInfoVo;
import com.zyt.service.productService.ProductDetailsInfoService;
import com.zyt.service.seckillProductManagerService.SeckillProductService;
import com.zyt.service.seckillProductScheduleManagerService.SeckillProductScheduleService;
import com.zyt.utils.ChangeJsonTools;
import com.zyt.utils.RedisUtil;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.seckillProductScheduleManagerService.impl
 * @ClassName: SeckillProductScheduleServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品定时上架任务服务层实现模块
 * @Date: 13:54 2021/3/12
 * @Version: 1.0

 */
@Service(value = "seckillProductScheduleService")
public class SeckillProductScheduleServiceImpl implements SeckillProductScheduleService {

    private Logger logger = LoggerFactory.getLogger(SeckillProductScheduleServiceImpl.class);

    @Autowired
    private SeckillProductService seckillProductService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProductDetailsInfoService productDetailsInfoService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Method: uploadSeckillProductLatestThreeDay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理定时任务
     * @Return: void
     * @Exception:
     * @Date: 2021/3/12 14:00
     * @Param: * @param
     * @Return: void
     */
    @Override
    public void uploadSeckillProductLatestThreeDay() {
        List<SecKillSession> session = this.seckillProductService.handleUploadSeckillProductLatestThreeDay();
        List<SeckillProductAndSessionInfoVo> seckillProductAndSessionInfoVoList = null;
        if (session != null && session.size() > 0){
            seckillProductAndSessionInfoVoList = session.stream().map(sessions ->{
                SeckillProductAndSessionInfoVo seckillProductAndSessionInfoVo = new SeckillProductAndSessionInfoVo();
                seckillProductAndSessionInfoVo.setSec_kill_id(sessions.getSec_kill_id());
                seckillProductAndSessionInfoVo.setSec_kill_name(sessions.getSec_kill_name());
                seckillProductAndSessionInfoVo.setStart_time(sessions.getStart_time());
                seckillProductAndSessionInfoVo.setEnd_time(sessions.getEnd_time());
                seckillProductAndSessionInfoVo.setEnable_status(sessions.getEnable_status());
                seckillProductAndSessionInfoVo.setCreate_time(sessions.getCreate_time());
                seckillProductAndSessionInfoVo.setStore_id(sessions.getStore_id());
                seckillProductAndSessionInfoVo.setSecKillProdRelationList(sessions.getSecKillProdRelationList());
                return seckillProductAndSessionInfoVo;
            }).collect(Collectors.toList());
        }else{
            seckillProductAndSessionInfoVoList = null;
        }
        logger.info("封装的秒杀相关的信息为:"+seckillProductAndSessionInfoVoList.toString());
        //TODO 1.缓存活动信息
        saveSessionInfos(seckillProductAndSessionInfoVoList);
        //TODO 2.缓存当前活动关联的商品信息
        saveCurrSessionRelativeProductInfo(seckillProductAndSessionInfoVoList);
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存活动信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 16:23
      * @Param: * @param seckillProductAndSessionInfoVos
      * @Return:
      */
    private void saveSessionInfos(List<SeckillProductAndSessionInfoVo> seckillProductAndSessionInfoVos){
        seckillProductAndSessionInfoVos.stream().forEach(seckillProductAndSessionInfoVo ->{
           Long startTime = seckillProductAndSessionInfoVo.getStart_time().getTime();
           Long endTime = seckillProductAndSessionInfoVo.getEnd_time().getTime();
           String key = startTime+"_"+endTime;
           //判断当前是否已经存在key
           Boolean hasKey  = this.redisTemplate.hasKey(SecondKillConstant.SECOND_KILL_SESSION_CHACHE_PREFIX+key);
           //TODO 如果不存当前key  则保存相关场次信息到缓存
           if (!hasKey){
               //获得当前场次下所有相关商品的id
               List<String> collect = seckillProductAndSessionInfoVo.getSecKillProdRelationList().stream().map(item ->item.getPromotion_session_id().toString()+"-"+item.getProd_id().toString()).collect(Collectors.toList());
               //以当前场次的开始时间与结束时间的毫秒数为key  保存当前场次下的商品信息
               this.redisUtil.setList(SecondKillConstant.SECOND_KILL_SESSION_CHACHE_PREFIX+key,collect);
           }
        });
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存当前活动对应的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 16:26
      * @Param: * @param seckillProductAndSessionInfoVos
      * @Return:
      */
    private  void saveCurrSessionRelativeProductInfo(List<SeckillProductAndSessionInfoVo> seckillProductAndSessionInfoVos){
        //遍历所有活动场次
        seckillProductAndSessionInfoVos.stream().forEach(seckillProductAndSessionInfoVo -> {
            //遍历当前活动场次下的商品信息
            seckillProductAndSessionInfoVo.getSecKillProdRelationList().stream().forEach(secKillProdRelation -> {
                //生成随机码
                String prod_token = (UUID.randomUUID().toString().replaceAll("-",""));
                //TODO one.判断当前缓存中是否已经保存了相关的缓存商品信息  未保存则执行以下代码  否则不执行
                Boolean has_Key = this.redisTemplate.hasKey(secKillProdRelation.getPromotion_session_id().toString()+"-"+secKillProdRelation.getProd_id().toString());
                if (!has_Key){
                    SecondKillProdDetailsRedisTo secondKillProdDetailsRedisTo = new SecondKillProdDetailsRedisTo();
                    ProductDetailsVo productDetailsVo = new ProductDetailsVo();
                    //TODO 1.根据id去查询当前商品的详细信息
                    Product product = this.productDetailsInfoService.getProductInfoOfCurrSecKillProdByProdId(secKillProdRelation.getProd_id());
                    if (product == null){
                        //若查到的商品信息为空 则将封装商品信息实体类置空
                        productDetailsVo = null;
                    }else{
                        //否则进行数据的对拷
                        BeanUtils.copyProperties(product,productDetailsVo);
                    }
                    secondKillProdDetailsRedisTo.setProductDetailsVo(productDetailsVo);
                    //TODO 2.对拷秒杀商品相关信息  通过对拷拷贝基本信息
                    BeanUtils.copyProperties(secKillProdRelation,secondKillProdDetailsRedisTo);
                    //TODO 3.设置当前秒杀商品秒杀开始结束时间
                    secondKillProdDetailsRedisTo.setStartTime(seckillProductAndSessionInfoVo.getStart_time().getTime());
                    secondKillProdDetailsRedisTo.setEndTime(seckillProductAndSessionInfoVo.getEnd_time().getTime());
                    //TODO 4.为商品设置一个随机码 防止恶意攻击参与秒杀
                    secondKillProdDetailsRedisTo.setProdRandomCode(prod_token);
                    //TODO 5.为商品添加库存锁定信号量 ---引入redis分布式事务锁(限流)
                    //设置信号量之前判断缓存中是否已经保存了当前商品的信号量
                    Boolean has_Keys = this.redisTemplate.hasKey(SecondKillConstant.SECOND_KILL_PRODUCT_STOCK_SEMAPHORE+prod_token);
                    //如若没有则进行信号量设置
                    if (!has_Keys){
                        RSemaphore rSemaphore =  this.redissonClient.getSemaphore(SecondKillConstant.SECOND_KILL_PRODUCT_STOCK_SEMAPHORE+prod_token);
                        //商品参与秒杀的件数作为信号量
                        rSemaphore.trySetPermits(secKillProdRelation.getSeckill_count());
                    }
                    //TODO 6. 缓存商品信息  将商品信息数据已hashMap的方式进行保存  其中键为每件商品的id 值为每件商品信息
                    Map map = new HashMap<>();
                    String secondKillProdDetailsRedisTJsonStr =  ChangeJsonTools.objToStringPretty(secondKillProdDetailsRedisTo);
                    map.put(secKillProdRelation.getPromotion_session_id()+"-"+secKillProdRelation.getProd_id(),secondKillProdDetailsRedisTJsonStr);
                    this.redisUtil.setMap(SecondKillConstant.SECOND_KILL_PRODUCT_CHACHE_PREFIX,map);
                }
            });
        });
    }

}
