package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		Transaction transaction=jedis.multi();
		transaction.set("k5", "v5");
		transaction.set("k6", "v6");
		transaction.exec();
		transaction.discard();
	}
}
