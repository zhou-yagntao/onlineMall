package com.zyt.service.impl.seckillProductManagerService.impl;

import com.alibaba.fastjson.JSON;
import com.zyt.constant.SecondKillConstant;
import com.zyt.entity.SecKillSession;
import com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo;
import com.zyt.entity.vo.SeckillProductAndSessionInfoVo;
import com.zyt.service.secKillSessionManagerService.SecKillSessionManagerService;
import com.zyt.service.seckillProductManagerService.SeckillProductService;
import com.zyt.utils.ChangeJsonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.seckillProductService.impl
 * @ClassName: SeckillProductServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品服务实现层
 * @Date: 14:07 2021/3/12
 * @Version: 1.0
 */
@Service(value = "seckillProductService")
public class SeckillProductServiceImpl implements SeckillProductService {

     private Logger logger = LoggerFactory.getLogger(SeckillProductServiceImpl.class);


     @Autowired
     private SecKillSessionManagerService secKillSessionManagerService;

     @Autowired
     private RedisTemplate redisTemplate;


    /**
     * @Method: handleUploadSeckillProductLatestThreeDay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理上架最近三条的商品信息
     * @Return: void
     * @Exception:
     * @Date: 2021/3/12 14:11
     * @Param: * @param
     * @Return: void
     */
    @Override
    public List<SecKillSession> handleUploadSeckillProductLatestThreeDay() {
         //扫描最近三天的所有的秒杀活动
         List<SecKillSession> sessions = this.secKillSessionManagerService.getSessionsInfoOfLatestThreeDay();
         if (sessions != null && sessions.size() > 0){
             return  sessions;
         }else{
             return null;
         }
    }

    /**
     * @Method: getSecondKillProdInfoOfCurrentTime
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前秒杀系统时间下的商品信息
     * @Return: java.util.List<com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo>
     * @Exception:
     * @Date: 2021/3/12 20:26
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo>
     */
    @Override
    public List<SecondKillProdDetailsRedisTo> getSecondKillProdInfoOfCurrentTime() {
        //计算当前时间属于哪个场次
         Date date = new Date();
         Long time = date.getTime();
        //获得该场次的商品信息
        //获取缓存中存储的所有的key
        Set<String> keys_set = this.redisTemplate.keys(SecondKillConstant.SECOND_KILL_SESSION_CHACHE_PREFIX+"*");
        for(String key : keys_set){
            String replace_key = key.replace(SecondKillConstant.SECOND_KILL_SESSION_CHACHE_PREFIX,"");
            String [] key_value = replace_key.split("_");
            Long start = Long.parseLong(key_value[0]);
            Long end = Long.parseLong(key_value[1]);
            if (time >= start && time <= end){
                //表示当前时间存在秒杀场 则进行获取获取当前秒杀场次的商品信息
                List<String> range = this.redisTemplate.opsForList().range(key,-100,100);
                BoundHashOperations<String,String,String> boundHashOperations = this.redisTemplate.boundHashOps(SecondKillConstant.SECOND_KILL_PRODUCT_CHACHE_PREFIX);
                List<String> list = boundHashOperations.multiGet(range);
                if (list != null && list.size() > 0){
                    List<SecondKillProdDetailsRedisTo> secondKillProdDetailsRedisToList = list.stream().map(item ->{
                        SecondKillProdDetailsRedisTo secondKillProdDetailsRedisTo =  ChangeJsonTools.stringToObj(item,SecondKillProdDetailsRedisTo.class);
                        logger.info("封装的商品信息为:"+secondKillProdDetailsRedisTo);
                        return secondKillProdDetailsRedisTo;
                    }).collect(Collectors.toList());
                    logger.info("服务层拿到当前时间场次商品信息为:"+secondKillProdDetailsRedisToList);
                    return secondKillProdDetailsRedisToList;
                }
            }
        }
        return null;

    }

    /**
     * @Method: getIsJoinSecondKillOfCurrentProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:判断当前商品是否参与秒杀
     * @Return: com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo
     * @Exception:
     * @Date: 2021/3/13 14:43
     * @Param: * @param prodId
     * @Return: com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo
     */
    @Override
    public SecondKillProdDetailsRedisTo getIsJoinSecondKillOfCurrentProduct(String prodId) {
        //找到所有参与秒杀商品的key
        BoundHashOperations<String,String,String> boundHashOperations = this.redisTemplate.boundHashOps(SecondKillConstant.SECOND_KILL_PRODUCT_CHACHE_PREFIX);
        Set<String> keys = boundHashOperations.keys();
       if (keys != null && keys.size() > 0){
           //正则表达式
           String regx_key = "\\d-"+prodId;
           for (String key : keys){
              if (Pattern.matches(regx_key,key)){
                  String json_key_value = boundHashOperations.get(key);
                  SecondKillProdDetailsRedisTo secondKillProdDetailsRedisTo = ChangeJsonTools.stringToObj(json_key_value, SecondKillProdDetailsRedisTo.class);
                  //处理随机码信息是否显示问题
                  Date date = new Date();
                  Long currentTime = date.getTime();
                  if( currentTime>= secondKillProdDetailsRedisTo.getStartTime() && currentTime <= secondKillProdDetailsRedisTo.getEndTime()){

                  }else {
                      secondKillProdDetailsRedisTo.setProdRandomCode(null);
                  }
                  return  secondKillProdDetailsRedisTo;
              }
           }
       }
        return null;

    }
}
