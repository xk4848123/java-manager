package com.nidecai.managerndc.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.nidecai.managerndc.entity.RiderOrder;
import tk.mybatis.mapper.common.Mapper;

public interface RiderOrderMapper extends Mapper<RiderOrder> {
	
	Integer getAllSumOfRid(@Param("set") Set<Integer> idList);
	
	Integer getNewSumOfRidByCtime(@Param("ctime") Integer ctime,@Param("set") Set<Integer> idList);
	
	List<Integer> getOldUser(@Param("ctime") Integer ctime,@Param("set") Set<Integer> idList);
	
	Integer getOldSumOfRidByCtime(@Param("ctime") Integer ctime,@Param("list") List<Integer> idList);
	
	Integer getNewUserOrderNum(@Param("ctime") Integer ctime);
}