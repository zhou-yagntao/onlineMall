package com.zyt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: ScheduleConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: 定时任务相关配置
 * @Date: 13:46 2021/3/12
 * @Version: 1.0
 *
 * 定时任务应该不会阻塞 默认阻塞 ----解决办法：
 *   1. 让业务代码异步执行
 *   2.定时任务线程
 *   3.使用异步任务执行定时任务
 */
@Configuration
@EnableAsync //异步执行任务
@EnableScheduling //定时任务
public class ScheduleConfig {

}
