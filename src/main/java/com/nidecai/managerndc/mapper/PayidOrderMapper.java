package com.nidecai.managerndc.mapper;

import com.nidecai.managerndc.entity.PayidOrder;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author river
 * @title: PayidOrderMapper
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:28
 */
public interface PayidOrderMapper extends Mapper<PayidOrder> {
    @Select("SELECT  out_trade_no  FROM  hm_payid_order WHERE pay_id = #{id}")
    List<String> selectListPayOrder(String id);
    
    
    @Select("SELECT hpo.out_trade_no FROM hm_pay_statistic hpsa,hm_payid_order hpo WHERE  hpsa.uid_count > 3 AND  hpo.pay_id =hpsa.id")
    List<String> queryStore();
}
