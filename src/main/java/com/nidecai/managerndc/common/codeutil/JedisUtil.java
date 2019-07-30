package com.nidecai.managerndc.common.codeutil;

import java.util.concurrent.locks.ReentrantLock;

import com.nidecai.managerndc.common.compoent.SpringContextHolder;
import com.nidecai.managerndc.common.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 工具类
 */
public class JedisUtil {

	protected static ReentrantLock lockPool = new ReentrantLock();
	protected static ReentrantLock lockJedis = new ReentrantLock();

	// protected static Logger logger = Logger.getLogger(JedisUtil.class);
	private static RedisConfig redisConfig = null;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 8;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 8;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	// 超时时间
	private static int TIMEOUT = 50000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	static {
		redisConfig = SpringContextHolder.getBean(RedisConfig.class);
	}

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, redisConfig.getRedisIp(), redisConfig.getRedisPort(), TIMEOUT);
		} catch (Exception e) {
			// logger.error("First create JedisPool error : " + e);
			try {
				// 如果第一个IP异常，则访问第二个IP
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(MAX_ACTIVE);
				config.setMaxIdle(MAX_IDLE);
				config.setMaxWaitMillis(MAX_WAIT);
				config.setTestOnBorrow(TEST_ON_BORROW);
				jedisPool = new JedisPool(config, redisConfig.getRedisIp(), redisConfig.getRedisPort(), TIMEOUT);
			} catch (Exception e2) {
				// logger.error("Second create JedisPool error : " + e2);
			}
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static void poolInit() {
		lockPool.lock();
		try {
			if (jedisPool == null) {
				initialPool();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPool.unlock();
		}
	}

	public static Jedis getJedis() {
		lockJedis.lock();
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Get jedis error : " + e);
		} finally {
			lockJedis.unlock();
		}
		return jedis;
	}


}
