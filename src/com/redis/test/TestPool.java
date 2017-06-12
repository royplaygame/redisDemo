package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {

	public static void main(String[] args) {
		JedisPool jedisPool=JedisPoolUtils.getJedisPoolInstance();
		
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			jedis.set("aaa", "bbb");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JedisPoolUtils.release(jedisPool, jedis);
		}
	}
}
