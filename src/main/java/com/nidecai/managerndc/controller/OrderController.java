package com.nidecai.managerndc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;
import com.nidecai.managerndc.service.OrderService;

@RestController
@RequestMapping(value = "/jmanager/v1/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	//两个退款接口(一个针对商户订单，一个针对配送订 单)
	
	@RequestMapping(value = "/orderretrun" ,method = RequestMethod.POST)
	@ConvenientStore(value = "orderShop")
	public synchronized ResultDTO<Object> orderReturn(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		if(orderService.orderJudge(id)) {
			return ResultUtil.getSuccess(orderService.orderReturn(id));
		}else {
			return ResultUtil.getSuccess(false);
		}
		
		
	} 
	
	@RequestMapping(value = "/rorderretrun" ,method = RequestMethod.POST)
	@ConvenientStore(value = "orderRider")
	public synchronized ResultDTO<Object> riderOrderReturn(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		if(orderService.rorderJudge(id)) {
			return ResultUtil.getSuccess(orderService.rorderReturn(id));
		}else {
			return ResultUtil.getSuccess(false);
		}
	}
	
}
