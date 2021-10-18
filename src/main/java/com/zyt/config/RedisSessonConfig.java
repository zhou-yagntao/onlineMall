package com.zyt.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: RedisSessionConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: redis session相关配置
 * @Date: 17:53 2021/3/12
 * @Version: 1.0
 */
@Configuration
public class RedisSessonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        //创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //根据config创建出redission()实例
        RedissonClient redissonClient = Redisson.create(config);
        return  redissonClient;
    }
}
