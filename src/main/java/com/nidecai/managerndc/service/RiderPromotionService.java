package com.nidecai.managerndc.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RiderPromotionService {

	
	List<Map<String, Object>> getPromotionNum(Integer days,Integer rid,String beginDate,String enddate);
	
	HashMap<String, Integer> getEveryRiderPromotion(String beginDate,String endDate) throws ParseException;
	
}
