package com.zyt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: RabbitMQConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: 消息队列客户端服务的消息确认机制配置文件
 * @Date: 19:50 2021/3/3
 * @Version: 1.0
 */
@Configuration
public class RabbitMQConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Method: messageConverter()
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:使用json序列化转化机制进行消息转换
     * @Return:
     * @Exception:
     * @Date: 2021/3/3 16:33
     * @Param: * @param null
     * @Return:
     */
    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    /*
    *自己定制RabbitTempleate
    *1.服务端接收到消息就进行回调
    *   1.1.spring.rabbirmq.publisher-confirms = true
    *   1.2.设置确认回调confirmCallBack
    * 2.消息正确抵达队列进行回调
    *   2.1.spring.rabbitmq.publisher-return = true
    *       spring.rabbitmq.template.mandatory = true;
    *   2.2.设置确认回调returnCallBack
    *
    * 消费者端确认(保证每一个消费被正确消费，此时可以broker删除这个消息）
    *      1.默认是自动确认  只要消息接收到 客户端自动确认 服务的就会移除这个消息
    *          问题;
    *              当我们收到很多消息。 自动恢复服务器ack  只有一个 消息会成功处理  宕机中  发生消息丢失
    *              消费者 手动确认模式  只要我们没有明确告诉mq  没有ack 消息就会一直unack状态
    *
    *
    *
    * **/

    @PostConstruct //RabbitMQConfig创建完成之后执行该方法
    public void initRabbitTemplate(){
        //设置确认回调
        this.rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
             /**
              * @Method: initRabbitTemplate
              * @Author: zhou_yangtao@yeah.net
              * @Version  1.0
              * @Description:
              * @Return: void
              * @Exception:
              * @Date: 2021/3/3 20:16
              * @Param: * @param correlationData 当前消息的唯一关联数据(这个是消息的唯一id)
              *                  ack  消息是否成功收到
              *                  cause  失败原因
              * @Return: void
              */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //TODO 做好消息确认机制  每一个发送的消息在数据库做好记录 定期将失败的消息再重新发一次
                //服务器收到
                  logger.info("confirm....confirmCallBack"+ack+"=>"+cause);
            }
        });

        //设置消息抵达队列的回调确认函数
        this.rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * @Method: returnedMessage
             * @Author: zhou_yangtao@yeah.net
             * @Version  1.0
             * @Description:只要消息没有投递到指定的队列 则触发这个失败回调
             * @Return: void
             * @Exception:
             * @Date: 2021/3/3 20:26
             * @Param: * @param message 投递失败的详细信息
             * @param relayCode  护恢复的状态码
             * @param relayText  恢复的文本的内容
             * @param exchange   当时这个消息发送给那个交换机
             * @param routingKey 当时这个消息用哪个路由键进行发送消息
             * @Return: void
             */
            @Override
            public void returnedMessage(Message message, int relayCode, String relayText, String exchange, String routingKey) {
                //报错  重新发
               logger.info("fail message"+message+"\t\t"+relayCode+"\t\t"+relayText+"\t\t"+exchange+"\t\t"+routingKey);
            }
        });
    }


}
