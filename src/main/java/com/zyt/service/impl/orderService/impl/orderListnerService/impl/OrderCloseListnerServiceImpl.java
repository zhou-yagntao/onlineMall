package com.zyt.service.impl.orderService.impl.orderListnerService.impl;

import com.rabbitmq.client.Channel;
import com.zyt.entity.Order;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.orderService.orderListnerService.OrderCloseListnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.orderService.impl.orderListnerService.impl
 * @ClassName: OrderCloseListnerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单关闭监听服务层
 * @Date: 15:57 2021/3/3
 * @Version: 1.0
 */
@Service(value = "orderCloseListnerService")
@RabbitListener(queues = "order.release.order.queue")
public class OrderCloseListnerServiceImpl implements OrderCloseListnerService {

    private Logger logger = LoggerFactory.getLogger(OrderCloseListnerServiceImpl.class);

    @Autowired
    private OrderManagerService orderManagerService;

    /**
     * @Method: listener
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:关单处理业务层
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 16:14
     * @Param: * @param order
     * @param channel
     * @param message
     * @Return: void
     */
    public void listener(Order order, Channel channel, Message message) throws IOException {
        logger.info("收到过期的订单消息,准备关闭订单"+order.getOrder_sn());
        try {
            this.orderManagerService.closeOrder(order);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
