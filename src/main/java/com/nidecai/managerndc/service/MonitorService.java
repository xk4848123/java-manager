package com.nidecai.managerndc.service;

import java.util.Map;
import java.util.List;

public interface MonitorService {

	List<Map<String, Object>> getOnlineRider();
	
	List<Map<String, Object>> getClickFarming(Integer marketid);
	
}
