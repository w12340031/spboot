package com.example.spboot.service;

import com.example.spboot.dao.TGoodsInfoMapper;
import com.example.spboot.model.TGoodsInfo;
import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;
import io.swagger.models.auth.In;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.smartcardio.SunPCSC;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Resource
    private TGoodsInfoMapper mapper;
    @Autowired
    private MemCachedClient memCachedClient;

    @Override
    public void addGoods(TGoodsInfo tGoodsInfo) {
        //mapper.insert(tGoodsInfo);
        memCachedClient.set(tGoodsInfo.getCode(),tGoodsInfo.getAmout());
    }

    @Override
    public void deleteGoods(String code) {
        //mapper.delete(code);
        memCachedClient.delete(code);
    }

    @Override
    public int getAmout(String code) {
//        TGoodsInfo info = mapper.getAmoutByCode(code);
//        if(info==null){
//            return 0;
//        }
//        return info.getAmout();
        Object info= memCachedClient.get(code);
        if(info==null){
            return 0;
        }
        return Integer.valueOf(info.toString().trim());
    }

    @Override
    @Transactional
    public boolean updateAmout(String code, int buys) {
//        TGoodsInfo info = mapper.getAmoutByCode(code);
//        Integer version = info.getVersion();
//        Integer amout = info.getAmout();
//        if(amout<buys){
//            return false;
//        }
//        //按版本更新
//        return mapper.updateAmoutByVersion(code,buys,version)>0?true:false;
        MemcachedItem item = (MemcachedItem) memCachedClient.get(code);
        int amout = Integer.valueOf(item.getValue().toString().trim());
        long casUnique = item.getCasUnique();
        if(amout<buys){
            return false;
        }
        if(memCachedClient.cas(code,amout-buys,casUnique)){
            return true;
        }
        waitForSomeTime();
        return  updateAmout(code,buys);

    }

    private void waitForSomeTime() {
        try {
            Thread.sleep(new Random().nextInt()+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
