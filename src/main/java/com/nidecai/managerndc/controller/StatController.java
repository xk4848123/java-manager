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
import com.nidecai.managerndc.service.StatService;

@RestController
@RequestMapping(value = "/jmanager/v1/stat")
public class StatController {

	
	@Autowired
	private StatService statService;
	
    @RequestMapping(value = "/getreprot" ,method = RequestMethod.GET)
    @ConvenientStore(value = "getreprot")
    public ResultDTO<Object> getStatReport(@RequestParam(name = "type", required = false,defaultValue = "week") String type) throws BusinessException{
    	try {
    		return ResultUtil.getSuccess(statService.getReport(type));
		} catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}
    	
    }
	
}
