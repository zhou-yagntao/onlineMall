package com.zyt.config;

import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: MyThreadConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description:线程池
 * @Date: 14:42 2021/2/25
 * @Version: 1.0
 */
@Configuration
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadConfigPropites threadConfigPropites){
        return  new ThreadPoolExecutor(threadConfigPropites.getCoreSize(),
                                       threadConfigPropites.getMaxSize(),
                                       threadConfigPropites.getKeepAliveTim(),
                                       TimeUnit.SECONDS,new LinkedBlockingDeque<>(100000),
                                       Executors.defaultThreadFactory(),
                                       new ThreadPoolExecutor.AbortPolicy());
    }}
