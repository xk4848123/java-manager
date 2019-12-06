package com.nidecai.managerndc.supportcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;
import com.nidecai.managerndc.service.SupportService;

@RestController
@RequestMapping(value = "/jmanager1/v1/support")
public class SController {

	
	@Autowired
	private SupportService supportService;
	
    //查询全部订单
    @RequestMapping(value = "/getuidbyphone")
    public ResultDTO<Object> listOrder(@RequestParam(name = "phone", required = true) String phone) throws BusinessException {
    	try {
    		return ResultUtil.getSuccess(supportService.getUidByPhone(phone));
		    } catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}

    }
    

}
