package com.nidecai.managerndc.mapper;


import com.nidecai.managerndc.entity.Market;
import com.nidecai.managerndc.entity.Shopown;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MarketMapper extends Mapper<Market> {

    @Select("SELECT  * FROM  hm_market WHERE state = #{state} ")
    List<Market>  selectMarket(Byte state);

    @Select("SELECT  id FROM  hm_market WHERE state = #{state} ")
    List<Integer>  selectMarketId(Byte state);

}