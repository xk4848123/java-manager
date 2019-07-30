package com.nidecai.managerndc.common.config;

public class RedisConfig {

	private String redisIp;

	private Integer redisPort;
	
	private String password;

	public String getRedisIp() {
		return redisIp;
	}

	public void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}

	public Integer getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(Integer redisPort) {
		this.redisPort = redisPort;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RedisConfig(String redisIp, Integer redisPort,String password) {
		super();
		this.redisIp = redisIp;
		this.redisPort = redisPort;
		this.password = password;
	}

}