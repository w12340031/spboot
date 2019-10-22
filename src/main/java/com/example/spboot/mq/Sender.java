package com.example.spboot.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class Sender {
    @Resource
    private AmqpTemplate amqpTemplate;
    public void sendMsg(){
        amqpTemplate.convertAndSend("lison","你好老师");
    }

}
