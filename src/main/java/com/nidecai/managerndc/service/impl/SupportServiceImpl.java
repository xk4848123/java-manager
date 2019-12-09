package com.nidecai.managerndc.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nidecai.managerndc.common.codeutil.JedisClient;
import com.nidecai.managerndc.common.codeutil.LoggingUtil;
import com.nidecai.managerndc.common.codeutil.RandomUtil;
import com.nidecai.managerndc.entity.Order;
import com.nidecai.managerndc.entity.RiderOrder;
import com.nidecai.managerndc.mapper.OrderMapper;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.service.SupportService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SupportServiceImpl implements SupportService{

	@Autowired
	private RiderOrderMapper riderOrderMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getVerifyCodeByPhone(String phone) {
		String code = JedisClient.get("verify_" + phone);
		if (code == null) {
			code = RandomUtil.generateCode();
			JedisClient.set("verify_" + phone, code,60 * 5);
		}
		return code;
	}

	@Override
	public String getTodayUserRegisterByAreaName(String areaName) {
		return null;
	}

	
	@Override
	public Integer getUidByPhone(String phone) {
		String sql = "SELECT id FROM hm_user where phone = ?";
		Integer uid = 0;
		try {
			uid = jdbcTemplate.queryForObject(sql, new Object[] { phone },
					Integer.class);
		} catch (Exception e) {
		}
		return uid;
	}

}
