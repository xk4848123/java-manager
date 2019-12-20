package com.nidecai.managerndc.common.codeutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class JedisClient {
	/**
	 * 通过key删除（字节）
	 * 
	 * @param key
	 */
	public void del(byte[] key) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.del(key);
		jedis.close();
	}

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.del(key);
		} catch (Exception e) {
			throw new RuntimeException("redis异常: " + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 添加key value 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(byte[] key, byte[] value, int liveTime) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.set(key, value);
		jedis.expire(key, liveTime);
		jedis.close();
	}

	/**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public static void set(String key, String value, int liveTime) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.set(key, value);
		jedis.expire(key, liveTime);
		jedis.close();
	}

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			LoggingUtil.e("redis异常:" + e.getMessage());
			throw new RuntimeException("redis异常:" + e.getMessage());
		}finally {
			jedis.close();
		}
		
	}

	/**
	 * 添加key value (字节)(序列化)
	 * 
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.set(key, value);
		jedis.close();
	}

	/**
	 * 获取redis value (String)
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("redis异常:" + e.getMessage());
			throw new RuntimeException("redis异常:" + e.getMessage());
		} finally {
			jedis.close();
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Long incrKey(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			Long value = jedis.incr(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("redis异常:" + e);
			throw new RuntimeException("redis异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 
	 * @param key
	 * @param n
	 * @return
	 */
	public static Long incrKey(String key, int n) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			Long value = jedis.incrBy(key, n);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("redis异常:" + e);
			throw new RuntimeException("redis异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 获取redis value (byte [] )(反序列化)
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		Jedis jedis = JedisUtil.getJedis();
		byte[] value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return
	 */
	public static Set<String> keys(String pattern) {
		Jedis jedis = JedisUtil.getJedis();
		Set<String> value = jedis.keys(pattern);
		jedis.close();
		return value;
	}

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			boolean value = jedis.exists(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/******************* redis list操作 ************************/
	/**
	 * 往list中添加数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static void lpushAll(String key, List<String> values) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.lpush(key, (String[]) values.toArray(new String[values.size()]));
		} catch (Exception e) {
			LoggingUtil.e("往list中添加数组异常:" + e);
			throw new RuntimeException("往list中添加数组异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 往list中添加数组
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static void lpushAll(String key, List<String> values, int seconds) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.lpush(key, (String[]) values.toArray(new String[values.size()]));
			jedis.expire(key, seconds);
		} catch (Exception e) {
			LoggingUtil.e("往list中添加数组异常:" + e);
			throw new RuntimeException("往list中添加数组异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 往list中添加元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static void lpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.lpush(key, value);
		} catch (Exception e) {
			LoggingUtil.e("往list中添加元素异常:" + e);
			throw new RuntimeException("往list中添加元素异常:" + e);
		} finally {
			jedis.close();
		}
	}

	public void rpush(String key, String value) {
		Jedis jedis = JedisUtil.getJedis();
		jedis.rpush(key, value);
		jedis.close();
	}

	public static String rpop(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = JedisUtil.getJedis();
			value = jedis.rpop(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("往list中添加元素异常:" + e);
			throw new RuntimeException("往list中添加元素异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 数组长度
	 * 
	 * @param key
	 * @return
	 */
	public Long llen(String key) {
		Jedis jedis = JedisUtil.getJedis();
		Long len = jedis.llen(key);
		jedis.close();
		return len;
	}

	/**
	 * 获取下标为index的value
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, Long index) {
		Jedis jedis = JedisUtil.getJedis();
		String str = jedis.lindex(key, index);
		jedis.close();
		return str;
	}

	public String lpop(String key) {
		Jedis jedis = JedisUtil.getJedis();
		String str = jedis.lpop(key);
		jedis.close();
		return str;
	}

	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = JedisUtil.getJedis();
		List<String> str = jedis.lrange(key, start, end);
		jedis.close();
		return str;
	}

	/********************* redis list操作结束 **************************/

	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public String flushDB() {
		Jedis jedis = JedisUtil.getJedis();
		String str = jedis.flushDB();
		jedis.close();
		return str;
	}

	/**
	 * 查看redis里有多少数据
	 */
	public long dbSize() {
		Jedis jedis = JedisUtil.getJedis();
		long len = jedis.dbSize();
		jedis.close();
		return len;
	}

	public static long sadd(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			return jedis.sadd(key, values);
		} catch (Exception e) {
			LoggingUtil.e("redis异常：" + e);
			throw new RuntimeException("redis异常：" + e);
		} finally {
			jedis.close();
		}
	}

	public static long sadd(String key, int seconds, String... values) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			long num = jedis.sadd(key, values);
			jedis.expire(key, seconds);
			return num;
		} catch (Exception e) {
			LoggingUtil.e("redis异常：" + e);
			throw new RuntimeException("redis异常：" + e);
		} finally {
			jedis.close();
		}
	}

	public static Boolean sismember(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			return jedis.sismember(key, value);
		} catch (Exception e) {
			LoggingUtil.e("redis异常：" + e);
			throw new RuntimeException("redis异常：" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * hash中覆盖key的value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			Long count = jedis.hset(key, field, value);
			return count;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * hash删除key
	 * 
	 * @param key
	 * @return
	 */
	public static void hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			jedis.hdel(key, field);
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 查看hash中的values
	 * 
	 * @param key
	 * @return
	 */
	public static List<String> hvals(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			List<String> values = jedis.hvals(key);
			return values;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 查看hash对应key的value
	 * 
	 * @param key
	 * @return
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			String value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 查看hash的长度
	 * 
	 * @param key
	 * @return
	 */
	public static Long hlen(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			Long value = jedis.hlen(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	/**
	 * key递减
	 * 
	 * @param key
	 * @return
	 */
	public static Long decr(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getJedis();
			Long value = jedis.decr(key);
			return value;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}

	public static List<String> scan(Integer count,String pattern) {
		Jedis jedis = null;
		ScanParams scanParams = new ScanParams();
		scanParams.count(count);
		String scanRet = ScanParams.SCAN_POINTER_START;
		try {
			jedis = JedisUtil.getJedis();
			List<String> retList = new ArrayList<>();
			do {
				 ScanResult<String> ret = jedis.scan(scanRet, scanParams.match(pattern + "*"));
				 scanRet = ret.getCursor();
				 retList.addAll(ret.getResult());
			} while (!scanRet.equals("0"));
			return retList;
		} catch (Exception e) {
			LoggingUtil.e("检查key是否存在异常:" + e);
			throw new RuntimeException("检查key是否存在异常:" + e);
		} finally {
			jedis.close();
		}
	}
//	public static String setIfNxByEx(String key, String value, int seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = JedisUtil.getJedis();
//			String result = jedis.set(key, value, "NX", "EX", seconds);
//			return result;
//		} catch (Exception e) {
//			LoggingUtil.e("检查key是否存在异常:" + e);
//			throw new RuntimeException("检查key是否存在异常:" + e);
//		} finally {
//			jedis.close();
//		}
//	}

	/**
	 * 检查是否连接成功
	 * 
	 * @return
	 */
	public String ping() {
		Jedis jedis = JedisUtil.getJedis();
		String str = jedis.ping();
		jedis.close();
		return str;
	}
}