package com.nidecai.managerndc.service.impl;

import org.springframework.stereotype.Service;

import com.nidecai.managerndc.common.codeutil.JedisClient;
import com.nidecai.managerndc.common.codeutil.RandomUtil;
import com.nidecai.managerndc.service.SupportService;

@Service
public class SupportServiceImpl implements SupportService{

	@Override
	public String getVerifyCodeByPhone(String phone) {
		String code = JedisClient.get("verify_" + phone);
		if (code == null) {
			code = RandomUtil.generateCode();
			JedisClient.set("verify_" + phone, code,60 * 5);
		}
		return code;
	}

}
