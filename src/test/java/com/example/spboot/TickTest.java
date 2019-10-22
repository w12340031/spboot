package com.example.spboot;

import com.example.spboot.model.SwApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpbootApplication.class)
public class TickTest {
    private int count=1000;
    //private Lock lock=new ReentrantLock();
    @Resource(name = "mysqlLock")
    private Lock lock;
    @Test
    public void testTick() throws InterruptedException {
        TickRunnable tr = new TickRunnable();
        Thread t1 = new Thread(tr, "窗口1");
        Thread t2 = new Thread(tr, "窗口2");
        Thread t3 = new Thread(tr, "窗口3");
        Thread t4 = new Thread(tr, "窗口4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.currentThread().join();

    }
    public class TickRunnable implements Runnable{
        @Override
        public void run() {
            while (count>0){
                lock.lock();
                if(count>0){
                    System.out.println(Thread.currentThread().getName()+"售出第("+count--+")张票");
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {//避免死锁，异常时释放锁
                   lock.unlock();
                }
            }
        }
    }
    
//    @Test
//    public void testHash() throws InterruptedException {
//        SwApp s1 = new SwApp();
//        SwApp s2 = new SwApp();
//        s1.setParaCd("a1");
//        s1.setParaTypeCd("b");
//        s1.setUserNm("c");
//        s2.setParaCd("a1");
//        s2.setParaTypeCd("b");
//        s2.setUserNm("c");
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());
//        Thread.currentThread().join();
//    }
}
