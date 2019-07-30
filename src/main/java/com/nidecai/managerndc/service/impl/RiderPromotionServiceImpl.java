package com.nidecai.managerndc.service.impl;

import java.util.ArrayList;
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
	public List<Map<String, Object>> getPromotionNum(Integer days, Integer rid) {
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
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		if (days != null) {
			for (Integer ridforStatas : intermediateResult.keySet()) {// keySet获取map集合key的集合  然后在遍历key即可
				Set<Integer> userListOfRid = intermediateResult.get(ridforStatas);//
			int ctime = DateUtil.getTimeBeforeDay(days);	
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
			List<Map<String, Object>> lMaps = new ArrayList<Map<String,Object>>();
			for (int j = 0; j < 3; j++) {
				Integer max = -1;
				Integer maxRid = 0;
				String maxRidName = "";
				String maxRidPhone = "";
//				List<Integer> ridsNeedToClear = new ArrayList<Integer>();
				for (int i = 0; i < resList.size(); i++) {
					Map<String, Object> cres = resList.get(i);
					Integer numInteger = (Integer) cres.get("num");
					Integer crid = (Integer) cres.get("rid");
					String riderName = (String) cres.get("riderName");
					String riderPhone = (String) cres.get("riderPhone");
					if (numInteger > max) {
						max = numInteger;
						maxRid = crid;
						maxRidName = riderName;
						maxRidPhone = riderPhone;
					}
				}
				//最大的已经出来了
				Map<String, Object> maxMap = new HashMap<String, Object>();
				maxMap.put("num", max);
				maxMap.put("rid", maxRid);
				maxMap.put("riderName", maxRidName);
				maxMap.put("riderPhone", maxRidPhone);
				lMaps.add(maxMap);
				resList.remove(maxMap);
			}
			lMaps.addAll(resList);
			return lMaps;
		}else {
			return resList;
		}
	
	}
	public static void main(String[] args) {
		List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("32", 123);
		list.add(map);
		Map<String, Integer> map2 = list.get(0);
		System.out.println(map2);
		list.remove(0);
		System.out.println(list);
		System.out.println(map2);
	}
	

}
