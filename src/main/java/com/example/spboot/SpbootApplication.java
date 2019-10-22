package com.example.spboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.spboot.dao")
@ComponentScan("com.example.spboot.service")
@ComponentScan(basePackages = "com.example.spboot.*")
@EnableTransactionManagement
public class SpbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbootApplication.class, args);
    }

}
