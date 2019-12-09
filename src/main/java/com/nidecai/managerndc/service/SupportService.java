package com.nidecai.managerndc.service;

public interface SupportService {

	
	public String getVerifyCodeByPhone(String phone);
	
	public Integer getUidByPhone(String phone);
	
	public String getTodayUserRegisterByAreaName(String areaName);
}
