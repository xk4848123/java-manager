package com.nidecai.managerndc.mapper;

import org.apache.ibatis.annotations.Param;

import com.nidecai.managerndc.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	
	int updateIntegral(@Param("addIntegral") Integer addIntegral,@Param("uid") Integer uid);
}