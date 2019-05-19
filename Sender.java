package com.qhit.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author name
 * @date 2019-05-17 9:06
 * 消息发送者
 * @author  Administrator
 *
 */
 @Component
public class Sender {

     @Autowired
    private AmqpTemplate  amqpTemplate;
     /*
      * 发送消息方法
      */
     public  void  send(String msg){
         //向消息队列发送消息
         //参数一消息名称
         //参数二消息
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
         this.amqpTemplate.convertAndSend("我是一条测试消息",msg);
     }
}
