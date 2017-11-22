package com.hy.ly.redis;

import redis.clients.jedis.Jedis;

public class JRedisPing {

	public static void main(String[] args) {
		Jedis jedis=new Jedis("192.168.56.102",6379);
		System.out.println("连接Redis服务器："+jedis.ping());
	}
}
