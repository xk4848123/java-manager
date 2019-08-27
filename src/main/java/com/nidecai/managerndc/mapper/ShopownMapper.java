package com.nidecai.managerndc.mapper;

import com.nidecai.managerndc.entity.Shopown;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopownMapper extends Mapper<Shopown>{

    @Select("SELECT *  FROM  hm_shopown WHERE marketid = #{hmMarketId}")
    List<Shopown> selectShown(Integer hmMarketId);

    @Update("UPDATE hm_shopown  SET status = #{status}")
    int updateStatus(Integer status);

}