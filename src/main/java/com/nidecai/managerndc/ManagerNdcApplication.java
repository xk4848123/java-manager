package com.nidecai.managerndc;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;
//去掉mongodb自动配置
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class} ) 
@MapperScan(basePackages = "com.nidecai.managerndc.mapper")
@EnableTransactionManagement
@EnableScheduling
public class ManagerNdcApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerNdcApplication.class, args);
	}

}
