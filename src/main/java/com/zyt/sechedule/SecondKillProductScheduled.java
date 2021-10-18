package com.zyt.sechedule;

import com.zyt.constant.SecondKillConstant;
import com.zyt.service.seckillProductScheduleManagerService.SeckillProductScheduleService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.sechedule
 * @ClassName: SecondKillProductScheduled
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品秒杀上线定时任务
 * @Date: 18:34 2021/3/12
 * @Version: 1.0
 *
 *
 * 秒杀商品定时上架
 *   每天晚上1:00进行商品下架 上架需要上架最近三天的秒杀商品
 */
@Service(value = "secondKillProductScheduled")
public class SecondKillProductScheduled {

    private Logger logger =  LoggerFactory.getLogger(SecondKillProductScheduled.class);

    @Autowired
    private SeckillProductScheduleService seckillProductScheduleService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @Method: uploadSeckillProductLatestThreeDay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理秒杀商品上线业务
     * @Return: void
     * @Exception:
     * @Date: 2021/3/12 18:38
     * @Param: * @param
     * @Return: void
     *  //TODO 如何解决重复插入数据到缓存 ---幂等性
     */
    @Scheduled(cron = "0 * * * * ?") //每天晚上一点执行该任务
    @Async
    public void uploadSeckillProductLatestThreeDay(){
        logger.info("上架秒杀商品信息了。。。。。。");
        //TODO  考虑到分布式环境下部署了多台服务器进行秒杀商品上架模块  只需其中某个进行上架即可 而不需所有的服务来处理这一模块 ---为此，可考虑使用分布式锁进行解决
        RLock lock = this.redissonClient.getLock(SecondKillConstant.SECOND_KILL_UPLOAD_LOCK);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            this.seckillProductScheduleService.uploadSeckillProductLatestThreeDay();
        }finally {
            lock.unlock();
        }
    }
}
