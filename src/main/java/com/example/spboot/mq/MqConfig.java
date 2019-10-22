package com.example.spboot.mq;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.amqp.core.Queue;
import java.util.Collection;
import java.util.Iterator;

@Configuration
public class MqConfig {
    @Bean
    public Queue first(){
        return new Queue("lison");
    }
}
