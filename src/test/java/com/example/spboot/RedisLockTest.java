package com.example.spboot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.UUID;

public class RedisLockTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testLock(){

        return ;

    }
}
