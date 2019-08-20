package com.nidecai.managerndc.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nidecai.managerndc.common.codeutil.JedisClient;
import com.nidecai.managerndc.common.codeutil.LoggingUtil;
import com.nidecai.managerndc.common.codeutil.RandomUtil;
import com.nidecai.managerndc.entity.Order;
import com.nidecai.managerndc.entity.RiderOrder;
import com.nidecai.managerndc.mapper.OrderMapper;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.service.SupportService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SupportServiceImpl implements SupportService{

	@Autowired
	private RiderOrderMapper riderOrderMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public String getVerifyCodeByPhone(String phone) {
		String code = JedisClient.get("verify_" + phone);
		if (code == null) {
			code = RandomUtil.generateCode();
			JedisClient.set("verify_" + phone, code,60 * 5);
		}
		return code;
	}

	@Override
	public String getTodayUserRegisterByAreaName(String areaName) {
		return null;
	}

	@Override
	@Transactional
	public void copyOrder(Integer sCtime, Integer eCtime, Integer days, Integer orderNum) {
		Integer queryBeforeDaysTime = days * 24 * 60 * 60;
		sCtime = sCtime - queryBeforeDaysTime;
		eCtime = eCtime - queryBeforeDaysTime;
		Example example = new Example(RiderOrder.class);
		example.createCriteria().andCondition("ctime<", eCtime).andCondition("ctime>", sCtime).andCondition("pay_status=", 1);
		Integer waitCopyOrderNum = riderOrderMapper.selectCountByExample(example);
		LoggingUtil.i("待拷贝单量为：" + waitCopyOrderNum);
		if (waitCopyOrderNum.compareTo(orderNum) < 0) {
			LoggingUtil.i("单量不够");
			return;
		}
		float ad = (float)orderNum / (float)waitCopyOrderNum;
		Random r=new Random();
		//查询范围单子
		Example example1 = new Example(RiderOrder.class);
		example1.createCriteria().andCondition("ctime<", eCtime).andCondition("ctime>", sCtime).andCondition("pay_status=", 1);
		List<RiderOrder> rorders = riderOrderMapper.selectByExample(example1);
		Integer a = 0;
		for (RiderOrder crorder : rorders) {
			if (r.nextFloat() > ad) {
				continue;
			}
			a ++;
			crorder.setCtime( crorder.getCtime() == null?null:crorder.getCtime()+ queryBeforeDaysTime);
			crorder.setOrderTime( crorder.getOrderTime() == null?null:crorder.getOrderTime()+ queryBeforeDaysTime);
			crorder.setFinishTime( crorder.getFinishTime() == null?null:crorder.getFinishTime()+ queryBeforeDaysTime);
			crorder.setPayTime( crorder.getPayTime() == null?null:crorder.getPayTime()+ queryBeforeDaysTime);
			crorder.setServiceTime( crorder.getServiceTime() == null?null:crorder.getServiceTime()+ queryBeforeDaysTime);
			BigInteger roid = crorder.getId();
			LoggingUtil.i(roid + "---");
			crorder.setId(null);
			riderOrderMapper.insertSelective(crorder);
			LoggingUtil.i("newroid" + crorder.getId());
			Example example2 = new Example(Order.class);
			example2.createCriteria().andCondition("roid=", roid);
			List<Order> orders = orderMapper.selectByExample(example2);
			for (Order order : orders) {
				order.setPayTime(order.getPayTime() == null?null:order.getPayTime() + queryBeforeDaysTime);
				order.setCtime(order.getCtime() == null?null:order.getCtime() + queryBeforeDaysTime);
				order.setId(null);
				order.setRoid(crorder.getId());
				orderMapper.insertSelective(order);
			}
			
		}
		
	}

}
