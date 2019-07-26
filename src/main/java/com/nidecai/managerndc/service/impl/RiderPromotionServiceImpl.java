package com.nidecai.managerndc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nidecai.managerndc.common.mongoentity.PromotionRider;
import com.nidecai.managerndc.service.RiderPromotionService;

@Service
public class RiderPromotionServiceImpl implements RiderPromotionService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void getPromotionNum(String startTime, String endTime, Integer rid) {
		List<Integer> rids = new ArrayList<Integer>();
		if (rid == null) {
			rids.add(290);
			rids.add(196);
		} else {
			rids.add(rid);
		}
		Criteria[] criterias = new Criteria[rids.size()];
		for(int i = 0 ; i < rids.size() ; i++){
			criterias[i] = Criteria.where("rid").is(rids.get(i));
		}
		Criteria orOperator = new Criteria().orOperator(criterias);
		Query queryRiderPromotion = new Query();
		queryRiderPromotion.addCriteria(orOperator);
		List<PromotionRider> promotionRiders = mongoTemplate.find(queryRiderPromotion, PromotionRider.class);
		Map<Integer, Set<Integer>> intermediateResult = new HashMap<Integer, Set<Integer>>(); 
		for (PromotionRider promotionRider : promotionRiders) {
			Set<Integer> newUserList = intermediateResult.get(promotionRider.getRid());
			if (newUserList != null) {
				newUserList.add(promotionRider.getNewUid());
			}else {
				newUserList = new HashSet<Integer>();
				newUserList.add(promotionRider.getNewUid());
				intermediateResult.put(promotionRider.getRid(),newUserList);
			}
		}
		if (startTime == null) {
			
		} else {

		}

	}
	
}
