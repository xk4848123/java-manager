package com.nidecai.managerndc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

}
