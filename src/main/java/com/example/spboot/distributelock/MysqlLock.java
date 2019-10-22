package com.example.spboot.distributelock;

import com.example.spboot.dao.TUserMapper;
import com.example.spboot.model.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Service
public class MysqlLock implements Lock {
    @Resource
    private TUserMapper mapper;
    private TUser user = new TUser(1, "lison", 12l, 23);

    @Override
    public void lock() {
        while (true){
            if(tryLock()){
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
    //非阻塞式加锁
    @Override
    public boolean tryLock() {
        try {
            mapper.insert(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
//解锁
    @Override
    public void unlock() {
mapper.deleteByPrimaryKey(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
