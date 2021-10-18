package com.zyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;



//添加springboot支持的事务注解
//@EnableAspectJAutoProxy(exposeProxy = true) //开启aspectj动态代理功能
//添加rabbitmq启动注解
@EnableRabbit
//添加springboot启动注解
@SpringBootApplication
//添加事务启动注解
@EnableTransactionManagement
//整合redis作为session服务
@EnableRedisHttpSession
//扫描持久层
@MapperScan("com.zyt.mapper")
public class  OnlineDrinkMallApplication{
    public static void main( String[] args ){
        SpringApplication.run(OnlineDrinkMallApplication.class,args);
    }
}
