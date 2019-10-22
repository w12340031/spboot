package com.example.spboot;

import com.example.spboot.model.TGoodsInfo;
import com.example.spboot.service.GoodsInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = SpbootApplication.class)
public class OptimiLockTest {
    //用户人数
    private static final int USER_NUM=50;
    //产品code
    private static final String GOODS_CODE="xiaomi6";
    //产品库存数量
    private static final int GOODS_COUNT=100;
    //模拟高并发，
    private static CountDownLatch latch= new CountDownLatch(USER_NUM);
    private static int successPerson=0;
    private static int saleNum=0;
    @Resource
    private GoodsInfoService goodsInfoService;

    @Before
    public void init(){
        goodsInfoService.deleteGoods(GOODS_CODE);
        TGoodsInfo info = new TGoodsInfo();
        info.setAmout(GOODS_COUNT);
        info.setCode(GOODS_CODE);
        info.setName("小米");
        info.setVersion(0);
        goodsInfoService.addGoods(info);
    }

    @Test
    public void testUpdate() throws InterruptedException {
        for (int i = 0; i <USER_NUM ; i++) {
            new Thread(new UserRequest(GOODS_CODE,15)).start();
            latch.countDown();
        }
        Thread.sleep(2000);
        System.out.println("成功购买的人数："+successPerson);
        System.out.println("已经售出的商品个数："+saleNum);
        System.out.println("商品剩余的个数："+goodsInfoService.getAmout(GOODS_CODE));
    }
    public class UserRequest implements Runnable{
        private String code;
        private int buys;

        public UserRequest(String code, int buys) {
            this.code = code;
            this.buys = buys;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(goodsInfoService.updateAmout(code,buys)){
                synchronized (latch){
                    successPerson++;
                    saleNum+=buys;
                }
            }
        }
    }

}
