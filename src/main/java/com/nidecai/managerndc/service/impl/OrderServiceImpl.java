package com.nidecai.managerndc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.common.compoent.AliPayCompoent;
import com.nidecai.managerndc.common.mongoentity.StoreTurnover;
import com.nidecai.managerndc.entity.Order;
import com.nidecai.managerndc.entity.RiderOrder;
import com.nidecai.managerndc.entity.UserCoupon;
import com.nidecai.managerndc.mapper.OrderMapper;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.mapper.ShopownMapper;
import com.nidecai.managerndc.mapper.UserCouponMapper;
import com.nidecai.managerndc.mapper.UserMapper;
import com.nidecai.managerndc.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	@Qualifier("other1")
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private RiderOrderMapper riderOrderMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AliPayCompoent aliPayCompoent;
	
	@Autowired
	private UserCouponMapper userCouponMapper;
	
	@Autowired
	private ShopownMapper shopownMapper;
	
	@Override
	@Transactional
	public boolean orderReturn(Integer id) {
		Order order = orderMapper.selectByPrimaryKey(id);
		Byte oReturnPayStatus = 2;
		Byte oAlreadyPayStatus = 1;
		Byte opayStatus = order.getPayStatus();
		if (oReturnPayStatus.equals(opayStatus)) {
			//已经退款
			return false;
		}
		//部分退款还是全部退款判断
		Order orderQuery = new Order();
		orderQuery.setRoid(order.getRoid());
		orderQuery.setPayStatus(oReturnPayStatus);
		int count = orderMapper.selectCount(orderQuery);
		if (count > 0) {
			//有退款的商户订单不能再退款
			return false;
		}
		orderQuery.setPayStatus(oAlreadyPayStatus);
		count = orderMapper.selectCount(orderQuery);
		boolean isRorderReturn = false;
		if (count == 1) {
			isRorderReturn = true;
		}
		RiderOrder riderOrder = riderOrderMapper.selectByPrimaryKey(order.getRoid());
		//如果订单没有完成 先完成再退 后期将自动完成再退
		Byte riderStatus = riderOrder.getRiderStatus();
		//订单完成
		Byte riderCompleteStatus = 2;
		//订单评价
		Byte riderEvaluteStatus = 4;
		if (riderStatus.equals(riderCompleteStatus) || riderStatus.equals(riderEvaluteStatus)) {
			String returnNo = riderOrder.getOutTradeNo();
			String returnAmount = "";
			String requestNo = "";
			int integral = 0;
			if (isRorderReturn) {
				return this.rorderReturn(order.getRoid());
			}else {
				BigDecimal payment = order.getPayment();
				Double totalprice = riderOrder.getTotalprice();
				BigDecimal totalPriceB = BigDecimal.valueOf(totalprice);
				if (totalPriceB.compareTo(payment) == 1) {
					returnAmount = payment.toPlainString();
				}else {
					returnAmount = totalPriceB.toPlainString();
					integral = payment.subtract(totalPriceB).multiply(new BigDecimal("100")).intValue();
				}
				requestNo = order.getOrderSn();
			}
			//付款类型
			String typePay = riderOrder.getTypePay();
			if (typePay.equals("aliwap")) {
				//支付宝退款
				AlipayClient aliPayClient = aliPayCompoent.getAliPayClient();
				AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
				request.setBizContent("{" +
						"\"out_trade_no\":\"" + returnNo + "\"," +
						"\"refund_amount\":" + returnAmount + "," +
						"\"refund_currency\":\"USD\"," +
						"\"refund_reason\":\"正常退款\"," +
						"\"out_request_no\":\"" + requestNo + "\"" +
						"  }");
				AlipayTradeRefundResponse response = null;
				try {
					 response = aliPayClient.execute(request);
				} catch (AlipayApiException e) {
					//退款异常
					return false;
				}
				if (response.isSuccess()) {
					Order orderUpdate = new Order();
					orderUpdate.setId(id);
					orderUpdate.setPayStatus(oReturnPayStatus);
					if (orderMapper.updateByPrimaryKeySelective(orderUpdate) == 1) {
						if (integral > 0) {
							userMapper.updateIntegral(integral, riderOrder.getUid());
						}
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}else if (typePay.equals("wechat") || typePay.equals("applets")) {
				//微信退款
				return false;
			}else if(typePay.equals("integral")) {
				Order orderUpdate = new Order();
				orderUpdate.setId(id);
				orderUpdate.setPayStatus(oReturnPayStatus);
				if (orderMapper.updateByPrimaryKeySelective(orderUpdate) == 1) {
					userMapper.updateIntegral(order.getPayment().multiply(new BigDecimal("100")).intValue(), riderOrder.getUid());
					return true;
				}else {
					return false;
				}
				//积分退款
			}else {
				//退款异常
				return false;
			}
		}else {
			//退款异常
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean orderJudge(Integer id) {
		//两天之内的单子能退
		Order order = orderMapper.selectByPrimaryKey(id);
		RiderOrder riderOrder = riderOrderMapper.selectByPrimaryKey(order.getRoid());
		int currentTime = DateUtil.getCurrentTime();
		if (currentTime - riderOrder.getPayTime() > 86400) {
			return false;
		}
		//如果订单没有完成 先完成再退 后期将自动完成再退
		Byte riderStatus = riderOrder.getRiderStatus();
		//订单完成
		Byte riderCompleteStatus = 2;
		//订单评价
		Byte riderEvaluteStatus = 4;
		if (riderStatus.equals(riderCompleteStatus) || riderStatus.equals(riderEvaluteStatus)) {
		  return true;
		}else {
			List<Integer> orderIds = orderMapper.getOrderId(riderOrder.getId());
			if (riderStatus == 0) {
				if (riderOrderMapper.updateRiderStatusZoreToTwo(currentTime, currentTime, riderOrder.getId()) == 1) {
					orderMapper.updateRiderStatus(2, riderOrder.getId());
				}else {
					return false;
				}
			}else if(riderStatus == 1 || riderStatus == 3) {
                if (riderOrderMapper.updateRiderStatusOneToTwo(currentTime, riderOrder.getId()) == 1) {
                	orderMapper.updateRiderStatus(2, riderOrder.getId());
				}else {
					return false;
				}
			}else {
				return false;
			}
			//订单收入
			for (Integer oid : orderIds) {
				 Order currentOrder = orderMapper.selectByPrimaryKey(oid);
				 Integer pid = currentOrder.getPid();
				 BigDecimal actualAchieve = currentOrder.getActualAchieve();
				 String order_sn = currentOrder.getOrderSn();
				 Map<String, Object> pidInfos = shopownMapper.getPidInfos(pid);
				 BigDecimal balance = (BigDecimal) pidInfos.get("balance");
				 BigDecimal newBalance = balance.add(actualAchieve).setScale(2, BigDecimal.ROUND_HALF_UP);
				 shopownMapper.updateShopBalance(pid, newBalance);
				 Integer type = (Integer) pidInfos.get("type");
				 Integer tag = 0;
				 if (type == 1) {
					tag = 1;
				 }
				 StoreTurnover record = new StoreTurnover();
				 record.setCreateTime(DateUtil.getCurrentTime(new Date()));
		    	 record.setBalance(newBalance.doubleValue());
		    	 record.setMoney(actualAchieve.toPlainString());
		    	 record.setOid(oid);
		    	 record.setSid(pid);
		    	 record.setSn(currentOrder.getOrderSn());
		    	 record.setTag(tag);
		    	 record.setType(40300);
				 mongoTemplate.insert(record);
			}
			return true;
		}
	}

	@Override
	@Transactional
	public boolean rorderJudge(Integer id) {
		RiderOrder riderOrder = riderOrderMapper.selectByPrimaryKey(id);
		int currentTime = DateUtil.getCurrentTime();
		if (currentTime - riderOrder.getPayTime() > 86400) {
			return false;
		}
		//如果订单没有完成 先完成再退 后期将自动完成再退
		Byte riderStatus = riderOrder.getRiderStatus();
		//订单完成
		Byte riderCompleteStatus = 2;
		//订单评价
		Byte riderEvaluteStatus = 4;
		if (riderStatus.equals(riderCompleteStatus) || riderStatus.equals(riderEvaluteStatus)) {
		  return true;
		}else {
			List<Integer> orderIds = orderMapper.getOrderId(riderOrder.getId());
			if (riderStatus == 0) {
				if (riderOrderMapper.updateRiderStatusZoreToTwo(currentTime, currentTime, riderOrder.getId()) == 1) {
					orderMapper.updateRiderStatus(2, riderOrder.getId());
				}else {
					return false;
				}
			}else if(riderStatus == 1 || riderStatus == 3) {
                if (riderOrderMapper.updateRiderStatusOneToTwo(currentTime, riderOrder.getId()) == 1) {
                	orderMapper.updateRiderStatus(2, riderOrder.getId());
				}else {
					return false;
				}
			}else {
				return false;
			}
			//订单收入
			for (Integer oid : orderIds) {
				 Order currentOrder = orderMapper.selectByPrimaryKey(oid);
				 Integer pid = currentOrder.getPid();
				 BigDecimal actualAchieve = currentOrder.getActualAchieve();
				 String order_sn = currentOrder.getOrderSn();
				 Map<String, Object> pidInfos = shopownMapper.getPidInfos(pid);
				 BigDecimal balance = (BigDecimal) pidInfos.get("balance");
				 BigDecimal newBalance = balance.add(actualAchieve).setScale(2, BigDecimal.ROUND_HALF_UP);
				 shopownMapper.updateShopBalance(pid, newBalance);
				 Integer type = (Integer) pidInfos.get("type");
				 Integer tag = 0;
				 if (type == 1) {
					tag = 1;
				 }
				 StoreTurnover record = new StoreTurnover();
				 record.setCreateTime(DateUtil.getCurrentTime(new Date()));
		    	 record.setBalance(newBalance.doubleValue());
		    	 record.setMoney(actualAchieve.toPlainString());
		    	 record.setOid(oid);
		    	 record.setSid(pid);
		    	 record.setSn(currentOrder.getOrderSn());
		    	 record.setTag(tag);
		    	 record.setType(40300);
				 mongoTemplate.insert(record);
			}
			return true;
		}
	}

	@Override
	@Transactional
	public boolean rorderReturn(Integer id) {
		Byte oReturnPayStatus = 2;
		Order orderQuery = new Order();
		orderQuery.setRoid(id);
		orderQuery.setPayStatus(oReturnPayStatus);
		int count = orderMapper.selectCount(orderQuery);
		if (count > 0) {
			return false;
		}
		RiderOrder riderOrder = riderOrderMapper.selectByPrimaryKey(id);
		//如果订单没有完成 先完成再退 后期将自动完成再退
		Byte riderStatus = riderOrder.getRiderStatus();
		//订单完成
		Byte riderCompleteStatus = 2;
		//订单评价
		Byte riderEvaluteStatus = 4;
		if (!riderStatus.equals(riderCompleteStatus) && !riderStatus.equals(riderEvaluteStatus)) {
			return false;
		}
		String returnNo = riderOrder.getOutTradeNo();
		String returnAmount = riderOrder.getTotalprice().toString();
		String typePay = riderOrder.getTypePay();
		if(typePay.equals("aliwap")) {
			//支付宝退款
			AlipayClient aliPayClient = aliPayCompoent.getAliPayClient();
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent("{" +
					"\"out_trade_no\":\"" + returnNo + "\"," +
					"\"refund_amount\":" + returnAmount + "," +
					"\"refund_currency\":\"USD\"," +
					"\"refund_reason\":\"正常退款\"" +
					"  }");
			AlipayTradeRefundResponse response = null;
			try {
				 response = aliPayClient.execute(request);
			} catch (AlipayApiException e) {
				//退款异常
				return false;
			}
			if (response.isSuccess()) {
				RiderOrder riderOrderUpdate = new RiderOrder();
				riderOrderUpdate.setId(id);
				riderOrderUpdate.setPayStatus(oReturnPayStatus);
				if (riderOrderMapper.updateByPrimaryKeySelective(riderOrderUpdate) == 1) {
					orderMapper.updatePayStatus(2, id);
					int integral = riderOrder.getIntegral();
					if (integral > 0) {
						userMapper.updateIntegral(integral, riderOrder.getUid());
					}
					if (riderOrder.getCouponid() != null) {
						UserCoupon userCoupon = new UserCoupon();
						userCoupon.setId(riderOrder.getCouponid());
						Byte noUserState = 0;
						userCoupon.setState(noUserState);
						userCouponMapper.updateByPrimaryKeySelective(userCoupon);
					}
				 return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else if (typePay.equals("wechat") || typePay.equals("applets")) {
			//微信退款
			return false;
		}else if(typePay.equals("integral")) {
			RiderOrder riderOrderUpdate = new RiderOrder();
			riderOrderUpdate.setId(id);
			riderOrderUpdate.setPayStatus(oReturnPayStatus);
			if (riderOrderMapper.updateByPrimaryKeySelective(riderOrderUpdate) == 1) {
				orderMapper.updatePayStatus(2, id);
				int integral = riderOrder.getIntegral();
				if (integral > 0) {
					userMapper.updateIntegral(integral, riderOrder.getUid());
				}
				if (riderOrder.getCouponid() != null) {
					UserCoupon userCoupon = new UserCoupon();
					userCoupon.setId(riderOrder.getCouponid());
					Byte noUserState = 0;
					userCoupon.setState(noUserState);
					userCouponMapper.updateByPrimaryKeySelective(userCoupon);
				}
			 return true;
			}else {
				return false;
			}
		}else {
			//退款异常
			return false;
		}
	}
	

}
