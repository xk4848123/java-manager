package com.nidecai.managerndc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.common.codeutil.JedisClient;
import com.nidecai.managerndc.entity.Market;
import com.nidecai.managerndc.entity.RiderUser;
import com.nidecai.managerndc.mapper.MarketMapper;
import com.nidecai.managerndc.mapper.RiderUserMapper;
import com.nidecai.managerndc.service.MonitorService;

@Service
public class MonitorServiceImpl implements MonitorService {

	@Autowired
	private RiderUserMapper riderUserMapper;
	
	@Autowired
	private MarketMapper marketMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getOnlineRider() {
		List<String> res = JedisClient.scan(5, "rider_");
		List<Map<String, Object>> onLineRiders = new ArrayList<Map<String,Object>>();
		for (String riderExt : res) {
			Integer rid = Integer.valueOf(riderExt.split("_")[1]);
			RiderUser rider = riderUserMapper.selectByPrimaryKey(rid);
			HashMap<String, Object> onLineRider = new HashMap<>();
			
			onLineRider.put("rid", rider.getRid());
			onLineRider.put("name", rider.getName());
			Integer marketId = rider.getMarketid();
			if (marketId != null) {
				Market market = marketMapper.selectByPrimaryKey(marketId);
				onLineRider.put("market", market.getEname());
			}else {
				onLineRider.put("market", "");
			}
			Integer backUpMarketId = rider.getMarketBackup();
			if (backUpMarketId != null) {
				Market backUpMarket = marketMapper.selectByPrimaryKey(backUpMarketId);
				onLineRider.put("market",backUpMarket.getEname());
			}else {
				onLineRider.put("marketBackUp", "");
			}
			onLineRider.put("last_time", DateUtil.timeStamp2Date(rider.getLastTime()+"", null));
			onLineRider.put("is_order", rider.getIsOrder() == 1 ? true : false);
			onLineRider.put("address", rider.getLongitude() + "," + rider.getLatitude());
			onLineRiders.add(onLineRider);
		}
		return onLineRiders;
	}

	@Override
	public List<Map<String, Object>> getClickFarming(Integer marketid) {
		String sql = "SELECT tid FROM hm_pay_order WHERE tid in " + 
				"(SELECT tid FROM hm_pay_order WHERE roid in (SELECT id FROM hm_rider_order WHERE marketid = ? AND pay_status = 1 AND is_delete = 0) GROUP BY tid HAVING COUNT(1) > 3)";
		String queryUidSql = "SELECT uid FROM hm_pay_order WHERE tid = ?";
		StringBuilder finalSql = new StringBuilder();
		finalSql.append("select * from hm_pay_order where tid in (");
		List<String> tidList = jdbcTemplate.queryForList(sql, String.class, marketid);
		for (String tid : tidList) {
			Set<Integer> uniqueUidList = new HashSet<>();
			List<Integer> uidList = jdbcTemplate.queryForList(queryUidSql,Integer.class,tid);
			for (Integer uid : uidList) {
				uniqueUidList.add(uid);
			}
			if (uniqueUidList.size() > 3) {
				finalSql.append("'").append(tid).append("'").append(",");
			}
		}
		String finalSqlString = finalSql.deleteCharAt(finalSql.length() - 1).append(")").toString();
		System.out.println(finalSqlString);
		return jdbcTemplate.queryForList(finalSqlString);
		
	}

}
