package com.nidecai.managerndc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.common.codeutil.LoggingUtil;
import com.nidecai.managerndc.common.mongoentity.PromotionRider;
import com.nidecai.managerndc.entity.RiderUser;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.mapper.RiderUserMapper;
import com.nidecai.managerndc.service.RiderPromotionService;

import tk.mybatis.mapper.entity.Example;

@Service
public class RiderPromotionServiceImpl implements RiderPromotionService {

	@Autowired
	@Qualifier("other1")
	private MongoTemplate mongoTemplate;

	@Autowired
	private RiderUserMapper riderUserMapper;

	@Autowired
	private RiderOrderMapper riderOrderMapper;

	@Override
	public List<Map<String, Object>> getPromotionNum(Integer days, Integer rid, String beginDate,String enddate) {
		List<Integer> rids = new ArrayList<Integer>();
		if (rid == null) {
			Example example = new Example(RiderUser.class);
			example.createCriteria().andCondition("state=", 1).andCondition("status=", 1);
			List<RiderUser> allOpenRiderUser = riderUserMapper.selectByExample(example);
			for (RiderUser riderUser : allOpenRiderUser) {
				rids.add(riderUser.getRid());
			}
		} else {
			rids.add(rid);
		}
		Criteria[] criterias = new Criteria[rids.size()];
		for (int i = 0; i < rids.size(); i++) {
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
			} else {
				newUserList = new HashSet<Integer>();
				newUserList.add(promotionRider.getNewUid());
				intermediateResult.put(promotionRider.getRid(), newUserList);
			}
		}
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		int ctime = getCtime(days, beginDate);
		if (ctime != 0) {
			for (Integer ridforStatas : intermediateResult.keySet()) {// keySet获取map集合key的集合  然后在遍历key即可
				Set<Integer> userListOfRid = intermediateResult.get(ridforStatas);//
				List<Integer> oldUser = riderOrderMapper.getOldUser(ctime, userListOfRid);
				userListOfRid.removeAll(oldUser);
				Integer newUserNum = 0;
				Integer oldUserNum = 0;
				if (!userListOfRid.isEmpty()) {
					newUserNum = riderOrderMapper.getNewSumOfRidByCtime(ctime, userListOfRid);
					if (newUserNum == null) {
						newUserNum = 0;
					}
				}
				if (!oldUser.isEmpty()) {
					oldUserNum = riderOrderMapper.getOldSumOfRidByCtime(ctime, oldUser);
					if (oldUserNum == null) {
						oldUserNum = 0;
					}
				}

				Integer num = newUserNum + oldUserNum;
				Map<String, Object> every = new HashMap<String, Object>();
				every.put("rid", ridforStatas);
				RiderUser riderUser = riderUserMapper.selectByPrimaryKey(ridforStatas);
				every.put("riderName", riderUser.getName());
				every.put("riderPhone", riderUser.getPhone());
				every.put("num", num);
				resList.add(every);
			}
		} else {
			for (Integer ridforStatas : intermediateResult.keySet()) {// keySet获取map集合key的集合  然后在遍历key即可
				Set<Integer> userListOfRid = intermediateResult.get(ridforStatas);//
				Integer num = 0;
				if (!userListOfRid.isEmpty()) {
					num = riderOrderMapper.getAllSumOfRid(userListOfRid);
					if (num == null) {
						num = 0;
					}
				}

				Map<String, Object> every = new HashMap<String, Object>();
				every.put("rid", ridforStatas);
				RiderUser riderUser = riderUserMapper.selectByPrimaryKey(ridforStatas);
				every.put("riderName", riderUser.getName());
				every.put("riderPhone", riderUser.getPhone());
				every.put("num", num);
				resList.add(every);
			}
		}
		if (resList.size() != 0) {
			Collections.sort(resList, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					Integer o1Num = (Integer) o1.get("num");
					Integer o2Num = (Integer) o2.get("num");
					return o2Num - o1Num;
				}
			});
		}
		return resList;

	}

	private int getCtime(Integer days, String beginDate) {
		if (beginDate != null) {
			return DateUtil.getUnixTime(beginDate);
		}
		if (days != null) {
			return DateUtil.getTimeBeforeDay(days);
		}
		return 0;
	}

	@Override
	public HashMap<String, Integer> getEveryRiderPromotion(String beginDate, String endDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria criteria = Criteria.where("order").gte(beginDate)
				.lt(endDate);
		Query query = new Query(criteria);
		List<PromotionRider> promotionRiders = mongoTemplate.find(query, PromotionRider.class);
		HashMap<String, Integer> result = null;
		if (!promotionRiders.isEmpty()) {
			result = new HashMap<String, Integer>();
		}
		LoggingUtil.i(promotionRiders.size() + "");
		for (int i = 0; i < promotionRiders.size(); i++) {
			PromotionRider e = promotionRiders.get(i);
			String crname = e.getName();
			if (result.get(crname) == null) {
				result.put(crname, 1);
			}else {
				result.put(crname, result.get(crname) + 1);
			}
		}
	    return result;
	}
	

}
