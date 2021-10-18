package com.zyt.config;

import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: SpringUtils
 * @Author: zhou_yangtao@yeah.net
 * @Description: spring工具类
 * @Date: 12:10 2021/2/5
 * @Version: 1.0
 */
@Component
public class SpringUtils  implements ApplicationContextAware {
       private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if ( null == SpringUtils.applicationContext){
            SpringUtils.applicationContext = applicationContext;
        }
    }

    //获得上下文
    public static ApplicationContext getApplicationContext(){
        return  applicationContext;
    }

    //根据name获得bean
    public  static  Object getBean(String name){
        return  getApplicationContext().getBean(name);
    }

    //根据class获得bean
    public static <T> T getBean(Class<T> tClass){
        return  getApplicationContext().getBean(tClass);
    }

    //通过name.class获得bean
    public static  <T> T getBean(String name, Class<T> tClass){
        return  getApplicationContext().getBean(name,tClass);
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        return new InternalResourceViewResolver();
    }
}
