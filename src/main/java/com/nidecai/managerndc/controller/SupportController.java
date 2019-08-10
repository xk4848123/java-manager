package com.nidecai.managerndc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;
import com.nidecai.managerndc.service.SupportService;

@RestController
@RequestMapping(value = "/jmanager/v1/support")
public class SupportController {

	@Autowired
	private SupportService supportService;
	
    //查询全部订单
    @RequestMapping(value = "/getphoneverifycode")
    @ConvenientStore(value = "getphoneverifycode")
    public ResultDTO<Object> listOrder(@RequestParam(name = "phone", required = false) String phone) throws BusinessException {
    	try {
    		return ResultUtil.getSuccess(supportService.getVerifyCodeByPhone(phone));
		    } catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}

    }

}
