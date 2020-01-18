package com.nidecai.managerndc.mapper;

import com.nidecai.managerndc.entity.Shopown;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopownMapper extends Mapper<Shopown>{

    @Select("SELECT *  FROM  hm_shopown WHERE marketid = #{hmMarketId}")
    List<Shopown> selectShown(Integer hmMarketId);

    @Update("UPDATE hm_shopown  SET status = 1 where status = 0 and marketid = #{marketid} and type = 1 and is_show = 1 and examine = 1")
    int updateStatus(@Param("marketid") Integer marketId);
    
    @Select("SELECT type,balance from hm_shopown where pid = #{pid} for update")
    Map<String, Object> getPidInfos(@Param("pid") Integer pid);

    @Update("update hm_shopown set balance = #{balance} where pid = #{pid}")
    int updateShopBalance(@Param("pid") Integer pid,@Param("balance") BigDecimal balance);
}