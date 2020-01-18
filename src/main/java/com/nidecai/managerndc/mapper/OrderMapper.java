package com.nidecai.managerndc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nidecai.managerndc.entity.Order;
import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order> {
	
	int updatePayStatus(@Param("payStatus") Integer payStatus,@Param("roid") Integer roid);
	
	List<Integer> getOrderId(@Param("roid") Integer roid);
	
	int updateRiderStatus(@Param("riderStatus") Integer riderStatus,@Param("roid") Integer roid);
}