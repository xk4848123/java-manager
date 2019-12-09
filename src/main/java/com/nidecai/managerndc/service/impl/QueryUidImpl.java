package com.nidecai.managerndc.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.common.codeutil.MyStringUtils;
import com.nidecai.managerndc.entity.PayStatistic;
import com.nidecai.managerndc.entity.UserCoupon;
import com.nidecai.managerndc.mapper.PayStatisticMapper;
import com.nidecai.managerndc.mapper.PayidOrderMapper;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.mapper.UserCouponMapper;
import com.nidecai.managerndc.service.QueryUid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author river
 * @title: QueryUidImpl
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:25
 */
@Service
public class QueryUidImpl implements QueryUid {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("other1")
	private MongoTemplate mongoTemplate;

	@Autowired
	private PayStatisticMapper payStatisticMapper;
	@Autowired
	private PayidOrderMapper payidOrderMapper;
	@Autowired
	private RiderOrderMapper riderOrderMapper;
	@Autowired
	private UserCouponMapper userCouponMapper;

	@Override
	public void queryUidById() {
		List<PayStatistic> payStatistics = payStatisticMapper.selectAll();
		if (!CollectionUtils.isEmpty(payStatistics)) {
			for (PayStatistic payStatistic : payStatistics) {
				List<String> stringListPayOrder = payidOrderMapper.selectListPayOrder(payStatistic.getId());
				if (stringListPayOrder == null || stringListPayOrder.size() == 0) {
					continue;
				}
				Map<String, Object> resMap = riderOrderMapper.selectList(stringListPayOrder);
				if (resMap == null) {
					continue;
				}
				Object uidCO = resMap.get("uidC");
				if (uidCO == null) {
					continue;
				}
				String uidC = (String) uidCO;
				if ("".equals(uidC)) {
					continue;
				}
				payStatistic.setUid(uidC);
				payStatistic.setUidCount(uidC.split(",").length);
				payStatistic.setMarketid((String) resMap.get("mC"));
				payStatistic.setRid((String) resMap.get("ridC"));
				payStatistic.setCouponPrice((BigDecimal) resMap.get("cp"));
				payStatistic.setMarketPrice((BigDecimal) resMap.get("map"));
				payStatistic.setIntagral((BigDecimal) resMap.get("integralp"));
				payStatistic.setVipRelief((BigDecimal) resMap.get("vipp"));
				payStatistic.setRiderPay((BigDecimal) resMap.get("rp"));
				payStatisticMapper.updateByPrimaryKey(payStatistic);
			}

		}
	}

	@Override
	public void queryName() {
		List<PayStatistic> payStatistics = payStatisticMapper.selectAll();
		for (PayStatistic payStatistic : payStatistics) {
			String rid = payStatistic.getRid();
			if (rid != null) {
				List<String> riderNames = riderOrderMapper.selectRiderName(rid);
				payStatistic.setRidName(MyStringUtils.StringlistToString(riderNames));
			}
			String mid = payStatistic.getMarketid();
			if (mid != null) {
				List<String> stringListPayOrder = payidOrderMapper.selectListPayOrder(payStatistic.getId());
				if (stringListPayOrder == null || stringListPayOrder.size() == 0) {

				} else {
					List<Map<String, Object>> mNameNums = riderOrderMapper.selectMName(stringListPayOrder);
					if (mNameNums != null && mNameNums.size() != 0) {
						StringBuilder marketStat = new StringBuilder();
						for (Map<String, Object> m : mNameNums) {
							String ename = (String) m.get("ename");
							Long num = (Long) m.get("num");
							marketStat.append(ename).append("(").append(num).append(")");
						}
						payStatistic.setMarketName(marketStat.toString());
					}

				}
			}
			String uid = payStatistic.getUid();
			if (uid != null) {
				List<String> uNames = riderOrderMapper.selectUName(uid);
				payStatistic.setPhone(MyStringUtils.StringlistToString(uNames));
			}
			payStatisticMapper.updateByPrimaryKey(payStatistic);
		}

	}


	@Override
	public void promotionStat() {
		  List<PayStatistic> payStatistics = payStatisticMapper.selectAll();
		  String sqlString = "update hm_pay_statistic set rid_pro = ? where id = ?";
          for (PayStatistic payStatistic : payStatistics) {
              String uid = payStatistic.getUid();
              if (uid != null) {
            	  List<Integer> collection = new ArrayList<>(); 
            	  String[] split = uid.split(",");
            	  for (int i = 0; i < split.length; i++) {
            		  collection.add(Integer.valueOf(split[i]));
				 }
            	  Aggregation aggregation1 = Aggregation.newAggregation(Aggregation.match(Criteria.where("newUid").in(collection)),Aggregation.group("name").count().as("num"));
            	  AggregationResults<BasicDBObject> outputTypeCount1 =
            		        mongoTemplate.aggregate(aggregation1, "promotionRider", BasicDBObject.class);
            	  StringBuilder ridPro = new StringBuilder();
            	  for (Iterator<BasicDBObject> iterator = outputTypeCount1.iterator(); iterator.hasNext(); ) {
            		  ridPro.append(JSON.toJSONString(iterator.next()));
            	  }
            	  jdbcTemplate.update(sqlString, ridPro.toString(),payStatistic.getId());
          		}
            	  
			}
	}
	@Override
	public void storeStat() {
		  List<PayStatistic> payStatistics = payStatisticMapper.selectAll();
		  String sqlString = "update hm_pay_statistic set store_info = ? where id = ?";
          for (PayStatistic payStatistic : payStatistics) {
        	  List<String> stringListPayOrder = payidOrderMapper.selectListPayOrder(payStatistic.getId());
        	  if (stringListPayOrder == null || stringListPayOrder.size() == 0) {
					continue;
			  }
        	  List<Map<String, Object>> sNames = riderOrderMapper.selectSName(stringListPayOrder);
        		if (sNames != null && sNames.size() != 0) {
					StringBuilder storeStat = new StringBuilder();
					for (Map<String, Object> m : sNames) {
						String ename = (String) m.get("sinfo");
						Long num = (Long) m.get("num");
						storeStat.append(ename).append("|").append(num).append("  ");
					}
					jdbcTemplate.update(sqlString, storeStat.toString(),payStatistic.getId());
				}
        	  
			}
	}

	@Override
	public void queryStore() {
		List<String> stringListPayOrder = payidOrderMapper.queryStore();
	  	  List<Map<String, Object>> sNames = riderOrderMapper.selectSMName(stringListPayOrder);
  		if (sNames != null && sNames.size() != 0) {
				StringBuilder storeStat = new StringBuilder();
				StringBuilder pidC = new StringBuilder();
				Integer howmany = 0;
				for (Map<String, Object> m : sNames) {
					String mname = (String) m.get("ename");
					String sname = (String) m.get("sinfo");
					Long num = (Long) m.get("num");
					if (num.compareTo(5L) == 1) {
						
						howmany ++;
						storeStat.append(sname).append("|").append(mname).append(System.getProperty("line.separator"));
					}
					
				}
				System.out.println(howmany);
				System.out.println(storeStat.toString());
			}
	}


	@Override
	public void startGiveCoupon(String mongoId,Integer couponId) {
		String chooseUid = "SELECT id FROM hm_user WHERE phone in (SELECT phone FROM hm_phone)";
		List<Integer> uids = jdbcTemplate.queryForList(chooseUid,Integer.class);
		int currentTime = DateUtil.getCurrentTime();
		BigDecimal price = new BigDecimal("8");
		for (Integer uid : uids) {
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCoupon(mongoId);
			userCoupon.setUid(uid);
			userCoupon.setCouponid(couponId);
			userCoupon.setCtime(currentTime);
			userCoupon.setUpdateTime(currentTime);
			userCoupon.setStartTime(currentTime);
			userCoupon.setEndTime(currentTime + 86400);
			userCoupon.setType(1);
			userCoupon.setPrice(price);
			userCouponMapper.insertSelective(userCoupon);
		}
		
	}

	@Override
	public void test() {
		String sqlString = "SELECT uid FROM hm_test";
		String sql = "SELECT tid,count(tid) num,GROUP_CONCAT(uid) uids FROM hm_pay_order WHERE  tid in( SELECT tid FROM hm_pay_order WHERE uid = ? ) GROUP BY tid";
		String getMarketNames = "SELECT GROUP_CONCAT(ename) as enames FROM hm_market WHERE id in(" + 
				"SELECT marketid from hm_rider_order WHERE uid = ? AND is_delete = 0 AND pay_status = 1)"; 
		List<Integer> uids = jdbcTemplate.queryForList(sqlString, Integer.class);
		for (Integer uid : uids) {
			List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, uid);
			String enames = jdbcTemplate.queryForObject(getMarketNames,new Object[] { uid }, String.class);
			String resultString = "";
			Integer flag = 0;
			for (Map<String, Object> r : res) {
				String tid = (String) r.get("tid");
				Long num = (Long) r.get("num");
				String uidStr = (String) r.get("uids");
				Set<String> set = new HashSet<>();
				String[] split = uidStr.split(",");
				for (int i = 0; i < split.length; i++) {
					set.add(split[i]);
				}
				int size = set.size();
				if (size > 5) {
					flag = 1;
				}
				resultString = resultString + tid + "("  +  num + "," + stringCount(uidStr, String.valueOf(uid)) + "," + size + ") ";
			}
			String updateSql = "update hm_test set tids = ? ,flag = ?,market = ? where uid = ?";
			jdbcTemplate.update(updateSql, resultString,flag,enames,uid);
		}
	}

   public static int stringCount(String str,String key){
     int index=0;
     int count=0;
     while((index=str.indexOf(key))!=-1){
     str=str.substring(index+key.length());
     count++;
     }
     return count;
  }
}
