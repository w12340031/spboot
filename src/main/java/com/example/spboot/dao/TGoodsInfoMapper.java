package com.example.spboot.dao;

import com.example.spboot.model.TGoodsInfo;
import org.apache.ibatis.annotations.Param;

public interface TGoodsInfoMapper {
    int insert(TGoodsInfo record);
    int delete(String code);
    int insertSelective(TGoodsInfo record);
    TGoodsInfo getAmoutByCode(@Param("code") String code);
    int updateAmoutByVersion(@Param("code") String code,@Param("buys") int buys,@Param("version") Integer version);
}