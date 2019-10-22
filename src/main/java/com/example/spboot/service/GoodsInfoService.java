package com.example.spboot.service;

import com.example.spboot.model.TGoodsInfo;

public interface GoodsInfoService {
    public void addGoods(TGoodsInfo tGoodsInfo);
    public void deleteGoods(String code);
    public int getAmout(String code);
    public boolean updateAmout(String code,int buys);
}
