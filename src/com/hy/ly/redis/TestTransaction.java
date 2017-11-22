package com.hy.ly.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	public static void main(String[] args) {
		Jedis jedis=new Jedis("192.168.56.102",6379);
		
		Transaction transaction = jedis.multi();
		
		transaction.set("k1", "100");
		transaction.set("k2", "200");
		transaction.set("k3", "300");
		transaction.set("k4", "400");
		transaction.exec();
		transaction.discard();
	}
}
