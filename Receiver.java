package com.qhit.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author name
 * @date 2019-05-17 9:12
 *
 * 消息接收
 * @author  Administrator
 */


@Component
@RabbitListener(queues="我是一条测试消息")
public class Receiver  {
    private static int i=0;

    /**
     * 接收消息的方法。采用消息队列的监听机制
     * @param msg  消息
     */
    @RabbitHandler
    private static   void  process(String msg){
        System.out.println(i++);
        System.out.println("Receiver"+msg);
    }
}
