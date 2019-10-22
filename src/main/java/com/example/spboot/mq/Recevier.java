package com.example.spboot.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="lison")
public class Recevier {
    @RabbitHandler
    public void process(String msg){
        System.out.println("receive"+msg);
    }
}
