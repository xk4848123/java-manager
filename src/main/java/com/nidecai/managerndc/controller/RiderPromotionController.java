package com.nidecai.managerndc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.service.RiderPromotionService;

@RestController
@RequestMapping(value = "/jmanager/v1/riderpromotion")
public class RiderPromotionController {

	
	@Autowired
	private RiderPromotionService riderPromotionService;
	
	  //查询全部订单
    @RequestMapping(value = "/getpromotionnum" ,method = RequestMethod.GET)
    @ConvenientStore(value = "riderpromotionnum")
    public String listOrder(@RequestParam(name = "rid", required = false) Integer rid,
    		@RequestParam(name = "startTime", required = false) String startTime,
    		@RequestParam(name = "endTime", required = false) String endTime) throws BusinessException {
    	try {
    		if ((startTime == null && endTime != null) || (startTime != null && endTime == null)){
				ResultUtil.getFail(CommonMessageEnum.PARAM_LOST);
			}
    		riderPromotionService.getPromotionNum(startTime, endTime, rid);
    		return null;
		    } catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}

    }

	
}
