package com.zyt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: DrinkOnlineMallSessionConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商城缓存配置中心类
 * @Date: 14:19 2021/2/22
 * @Version: 1.0
 */
@Configuration
public class DrinkOnlineMallSessionConfig {

     @Bean
     public CookieSerializer cookieSerializer(){
         DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
         //放大作用域
         defaultCookieSerializer.setDomainName("127.0.0.1");
         //更改缓存名字
         defaultCookieSerializer.setCookieName("DRINKMALLSESSION");
         return defaultCookieSerializer;
     }

     //将保存到redis中的数转换为json格式
     @Bean
     public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
         return  new GenericJackson2JsonRedisSerializer();
     }

}
