package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {

	private JedisPoolUtils() {
	}

	public static volatile JedisPool jedisPool = null;

	public static JedisPool getJedisPoolInstance() {

		if (null == jedisPool) {
			synchronized (JedisPoolUtils.class) {
				if (null == jedisPool) {
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					jedisPoolConfig.setMaxActive(1000);
					jedisPoolConfig.setMaxIdle(32);
					jedisPoolConfig.setTestOnBorrow(true);
					jedisPoolConfig.setMaxWait(100 * 1000);

					jedisPool = new JedisPool(jedisPoolConfig, "192.168.56.102", 6379);
				}
			}
		}

		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnResourceObject(jedis);
		}
	}
}
