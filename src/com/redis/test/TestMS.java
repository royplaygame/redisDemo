package com.redis.test;

import redis.clients.jedis.Jedis;

public class TestMS {

	public static void main(String[] args) {
		Jedis jedisM=new Jedis("192.168.56.102",6379);
		Jedis jedisS=new Jedis("192.168.56.102",6380);
		
		jedisS.slaveof("192.168.56.102",6379);
		
		
		jedisM.set("k1", "v1111");
		jedisM.set("k2", "v2");
		jedisM.set("k3", "v3");
		
		String k1=jedisS.get("k1");
		System.out.println(k1);
	}
}
