package com.nidecai.managerndc.ExceptionHandle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalCommonExcptionHandler {
	/**
	 * 系统异常处理，比如：404,500
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("url", req.getRequestURL().toString());
		map.put("msg", e.getMessage());
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			
			map.put("code", 404);
		} else if (e instanceof BusinessException) {
			BusinessException businessException = (BusinessException)e;
			map.put("code", businessException.getErrorCode());
		} else {
			map.put("code", 500);
		}
		return map;
	}
}
