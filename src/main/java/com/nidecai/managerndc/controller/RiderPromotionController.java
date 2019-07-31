package com.nidecai.managerndc.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.JedisClient;
import com.nidecai.managerndc.common.codeutil.JedisUtil;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;
import com.nidecai.managerndc.service.RiderPromotionService;

@RestController
@RequestMapping(value = "/jmanager/v1/riderpromotion")
public class RiderPromotionController {

	
	@Autowired
	private RiderPromotionService riderPromotionService;
	
	  //查询全部订单
    @RequestMapping(value = "/getpromotionnum" ,method = RequestMethod.GET)
    @ConvenientStore(value = "riderpromotionnum")
    public ResultDTO<List<Map<String, Object>>> listOrder(@RequestParam(name = "rid", required = false) Integer rid,
    		@RequestParam(name = "days", required = false) Integer days,@RequestParam(name = "page", required = true) Integer page,
    		@RequestParam(name = "limit", required = true) Integer limit
    		) throws BusinessException {
    	try {
    		if (days != null && (days > 180 || days < 1)) {
    			throw new BusinessException(CommonMessageEnum.FAIL.getCode(), "参数超出范围");
			}
    		Integer redisTag = days;
    		if (redisTag == null) {
    			redisTag = 0;
			}
    		String redisKey = "riderpromotion_" + redisTag;
    		String cache = JedisClient.get(redisKey);
    		List<Map<String, Object>> result = null;
    		if (cache == null) {
    			result = riderPromotionService.getPromotionNum(days, rid);
    			String cacheForStore = JSON.toJSONString(result);
    			JedisClient.set(redisKey, cacheForStore, JedisUtil.EXRP_HOUR);
			}else {
				result = JSON.parseObject(cache, List.class);
			}
    		//数据的长度
    		Integer resultSize = result.size();
    		Integer fromIndex = (page - 1) * limit;
    		if (fromIndex > resultSize) {
    			throw new BusinessException(CommonMessageEnum.FAIL.getCode(), "参数超出范围");
			}
    		Integer toIndex = page * limit;
    		if (toIndex > resultSize) {
				toIndex = resultSize;
			}
    		ResultDTO<List<Map<String, Object>>> finalResult = new ResultDTO<List<Map<String, Object>>>();
    		finalResult.setTotal(resultSize);
    		finalResult.setData(result.subList(fromIndex, toIndex));
    		finalResult.setFlag(true);
    		finalResult.setCode(CommonMessageEnum.SUCCESS.getCode());
    		finalResult.setMsg(CommonMessageEnum.SUCCESS.getMsg());
    		return finalResult;
		    } catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.SERVERERR.getCode(), e.getMessage());
		}

    }
}
