package com.nidecai.managerndc.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.entity.RiderOrder;
import com.nidecai.managerndc.entity.User;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.mapper.UserMapper;
import com.nidecai.managerndc.service.StatService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class StatServiceImpl implements StatService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RiderOrderMapper riderOrderMapper;
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getReport(String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int unixTime = 0;
		if ("week".equals(type)) {
			unixTime = DateUtil.getUnixTime(DateUtil.getLastSaturday());
			//新用户订单量
			//老用户订单量
		}else if ("month".equals(type)) {
			unixTime = DateUtil.getUnixTime(DateUtil.getLastSaturday());
		}else {
			return null;
		}
		int noFavorableOrderNum = 0;
		int FavorableOrderNum = 0;
		int couponNum = 0;
		BigDecimal sumFavorableMoney = BigDecimal.ZERO;
		//注册人数
		Example example = new Example(User.class);
		Criteria condition = example.createCriteria().andCondition("ctime>", unixTime);
		int registerNum = userMapper.selectCountByExample(example);
		condition.andCondition("late_marketid is NOT NULL");
		//激活人数
		int activateNum = userMapper.selectCountByExample(example);
		Example riderOrderExample = new Example(RiderOrder.class);
		riderOrderExample.createCriteria().andCondition("ctime>",unixTime);
		List<RiderOrder> riderOrders = riderOrderMapper.selectByExample(riderOrderExample);
		int allOrderNum = riderOrders.size();
		for (Iterator iterator = riderOrders.iterator(); iterator.hasNext();) {
			RiderOrder riderOrder = (RiderOrder) iterator.next();
			BigDecimal totBigDecimal = new BigDecimal(riderOrder.getTotalprice()).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal orignBigDecimal = riderOrder.getOriginalPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal subtract = totBigDecimal.subtract(orignBigDecimal).setScale(2,  BigDecimal.ROUND_HALF_UP);
			BigDecimal noFavorable = new BigDecimal("6");
			if (subtract.compareTo(noFavorable) == 0) {
				//无优惠
				//无优惠订单量
				noFavorableOrderNum ++;
			}else {
				//优惠订单量
				if (riderOrder.getCouponPrice() != null) {
					couponNum ++;
				}
				FavorableOrderNum ++;
				BigDecimal favorableMoney = noFavorable.subtract(subtract);
				sumFavorableMoney = sumFavorableMoney.add(favorableMoney);
			}
		}
		//周优惠金额
		sumFavorableMoney =sumFavorableMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		Integer newUseRorderNum = riderOrderMapper.getNewUserOrderNum(unixTime);
		resultMap.put("registernum", registerNum);
		resultMap.put("activatenum", activateNum);
		resultMap.put("nofavorableordernum", noFavorableOrderNum);
		resultMap.put("favorableordernum", FavorableOrderNum);
		resultMap.put("sumfavorablemoney", sumFavorableMoney);
		resultMap.put("allordernum", allOrderNum);
		resultMap.put("newuserordernum", newUseRorderNum);
		resultMap.put("olduserordernum",allOrderNum - newUseRorderNum);
		resultMap.put("couponnum",couponNum);
		
		return resultMap;
		
	}

}
