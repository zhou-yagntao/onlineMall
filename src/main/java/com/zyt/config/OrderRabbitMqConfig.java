package com.zyt.config;

import com.zyt.entity.Order;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: RabbitMqConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: 消息队列信息配置层
 * @Date: 15:27 2021/3/2
 * @Version: 1.0
 */
@Configuration
public class OrderRabbitMqConfig {


    @Bean //容器中的组件Binding,Queue,Exchange会自动创建
    public Queue orderDelayQueue(){
        Map<String,Object> map = new HashMap<>();
        //设置路由
        map.put("x-dead-letter-exchange","order-event-exchange");
        map.put("x-dead-letter-routing-key","order.relase.order");
        map.put("x-message-ttl",60000);
        Queue queue =  new Queue("order.delay.queue",true,false,false,map);
        return  queue;


    }

    @Bean
    public Queue orderRelaseOrderQueue(){
        Queue queue  = new Queue("order.release.order.queue",true,false,false);
        return  queue;
    }

    @Bean
    public Exchange orderEventExchange(){
        return  new TopicExchange("order-event-exchange",true,false);
    }

    @Bean
    public Binding orderCreateOrder(){
        return  new Binding("order.delay.queue",
                      Binding.DestinationType.QUEUE,
                     "order-event-exchange",
                     "order.create.order",
                      null);
    }

    @Bean
    public Binding orderReleaseOrder(){
        return  new Binding("order.release.order.queue",
                             Binding.DestinationType.QUEUE,
                            "order-event-exchange",
                            "order.release.order",
                            null);
    }


    //创建订单与库存直接的联通关系
    @Bean
    public Binding orderReleaseOtherBinding(){
        return  new Binding("stock.release.stock.queue",
                             Binding.DestinationType.QUEUE,
                            "order-event-exchange",
                            "order.release.other.#",
                            null);
    }



}
