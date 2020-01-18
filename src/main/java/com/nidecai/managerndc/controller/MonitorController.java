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
import com.nidecai.managerndc.common.entitycommon.ResultDTO;
import com.nidecai.managerndc.service.MonitorService;

@RestController
@RequestMapping(value = "/jmanager/v1/monitor")
public class MonitorController {

	
	@Autowired
	private MonitorService monitorService;
	
	  @RequestMapping(value = "/getor" ,method = RequestMethod.GET)
	  @ConvenientStore(value = "getOnlineRider")
	  public ResultDTO<Object> getStatReport() throws BusinessException{
	    	try {
	    		return ResultUtil.getSuccess(monitorService.getOnlineRider());
			} catch (Exception e) {
				throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
			}
	    }
	  
	  @RequestMapping(value = "/getclickfarming" ,method = RequestMethod.GET)
	  @ConvenientStore(value = "getClickFarming")
	  public ResultDTO<Object> getClickFarming(@RequestParam(name = "marketid", required = true) Integer marketId) throws BusinessException{
	    	try {
	    		return ResultUtil.getSuccess(monitorService.getClickFarming(marketId));
			} catch (Exception e) {
				throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
			}
	    }
}
