package com.qhit.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author name
 * @date 2019-05-16 21:01
 * 创建消息队列
 * @author  Administrator
 */
@Configuration
public class QuereConfig {


    @Bean
    public Queue createQuere(){
        return new Queue("hello-Quere");
    }
}
