package com.nidecai.managerndc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan(basePackages = "com.nidecai.managerndc.mapper")
@EnableTransactionManagement
public class ManagerNdcApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerNdcApplication.class, args);
	}

}
