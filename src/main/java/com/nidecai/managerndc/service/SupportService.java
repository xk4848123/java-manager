package com.nidecai.managerndc.service;

public interface SupportService {

	
	public String getVerifyCodeByPhone(String phone);
	
	public String getTodayUserRegisterByAreaName(String areaName);
	
	public void copyOrder(Integer sCtime,Integer eCtime,Integer days, Integer orderNum);
}
