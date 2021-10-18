package com.zyt.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: StockrRabbitMqConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 16:31 2021/3/2
 * @Version: 1.0
 */
@Configuration
public class StockrRabbitMqConfig {

    @Bean
    public Exchange stockEventExchange(){
        return  new TopicExchange("stock-event-exchange",true,false);
    }

    @Bean
    public Queue stockReleaseQueue(){
        return new Queue("stock.release.stock.queue",true,false,false);
    }

    @Bean
    public Queue stockDelayQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","stock-event-exchange");
        map.put("x-dead-letter-routing-key","stock.release");
        map.put("x-message-ttl",180000);
        return  new Queue("stock.delay.queue",true,false,false,map);
    }

    @Bean
    public Binding stockReleaseBinding(){
        return  new Binding("stock.release.stock.queue",
                            Binding.DestinationType.QUEUE,
                            "stock-event-exchange",
                             "stock.release.#",
                              null);
    }

    @Bean
    public Binding stockLockBinding(){
        return  new Binding("stock.delay.queue",
                Binding.DestinationType.QUEUE,
                "stock-event-exchange",
                "stock.locked",
                null);
    }
}
