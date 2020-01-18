package com.nidecai.managerndc.common.compoent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;


@Component
public class AliPayCompoent {
	
	@Value("${ali.url}")
	private String url;
	
	@Value("${ali.appid}")
	private String appId;
	
	@Value("${ali.privatekey}")
	private String privateKey;
	
	@Value("${ali.format}")
	private String format;
	
	@Value("${ali.charset}")
	private String charset;
	
	@Value("${ali.publickey}")
	private String publickey;
	
	@Value("${ali.signtype}")
	private String signtype;
	
	public AlipayClient getAliPayClient() {
		return new DefaultAlipayClient(url,appId,privateKey,format,charset,publickey,signtype);
	}
	
}
