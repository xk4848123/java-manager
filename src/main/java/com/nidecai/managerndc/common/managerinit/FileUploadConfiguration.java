package com.nidecai.managerndc.common.managerinit;


import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置
 * 
 * @author chen_di
 * @create 2017年12月12日
 */
@Configuration
public class FileUploadConfiguration {
	
	@Value("${upload.maxFileSize}")
	private String maxFileSize;
	
	@Value("${upload.maxRequestSize}")
	private String maxRequestSize;
	
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize(maxFileSize); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        // Sets the directory location where files will be stored.
        return factory.createMultipartConfig();
    }
}