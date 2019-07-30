package com.nidecai.managerndc.common.managerinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.nidecai.managerndc.common.config.RedisConfig;


@Configuration
public class RedisConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public RedisConfig redisConfig() {
		if ("dev".equals(env.getActiveProfiles()[0])) {
			return new RedisConfig("192.168.0.252", 6379,"");
		} else if ("pro".equals(env.getActiveProfiles()[0])) {
			return new RedisConfig("47.98.235.104", 6389,"N1deC@2a^@.3dmin");
		} else {
			return new RedisConfig("47.98.195.250", 6389,"");
		}
	}
	
	
}
