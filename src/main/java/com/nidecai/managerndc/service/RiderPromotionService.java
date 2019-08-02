package com.nidecai.managerndc.service;

import java.util.List;
import java.util.Map;

public interface RiderPromotionService {

	
	List<Map<String, Object>> getPromotionNum(Integer days,Integer rid,String beginDate);
	
}
