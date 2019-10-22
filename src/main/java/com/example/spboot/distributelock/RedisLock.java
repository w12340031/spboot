package com.example.spboot.distributelock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
@Service
public class RedisLock implements Lock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void lock() {
        while (true){
           if(tryLock()){
               return;
           };
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
//非阻塞式加锁
    @Override
    public boolean tryLock() {
        String value = UUID.randomUUID().toString();
        RedisConnection redisConnection = stringRedisTemplate.getConnectionFactory().getConnection();
       //  redisConnection.set("KEYLOCK".getBytes(), 1000l, value.getBytes());
        Boolean flag = redisConnection.setNX("key1".getBytes(),value.getBytes());
        return flag;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
