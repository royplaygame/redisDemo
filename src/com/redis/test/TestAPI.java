package com.redis.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.set("k1", "v1");
		jedis.set("k2", "v2");
		jedis.set("k3", "v3");
		jedis.set("k4", "v4");

		System.out.println(jedis.get("k3"));

		Set<String> sets = jedis.keys("*");
		System.out.println(sets.size());
		for (Iterator<String> iterator = sets.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}

		System.out.println("jedis exist=" + jedis.exists("k3"));

		/**
		 * Redis TTL 命令以秒为单位返回 key 的剩余过期时间。 当 key 不存在时，返回 -2 。 当 key
		 * 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
		 */
		System.out.println(jedis.ttl("k1"));

		// 设置k2的过期间
		jedis.expire("k2", 60);
		// Thread.sleep(5000);
		System.out.println(jedis.ttl("k2"));

		// string
		jedis.append("k1", "appendnewstring");
		System.out.println(jedis.get("k1"));

		jedis.set("k4", "k4_redis");
		System.out.println("----------------------------------------");
		jedis.mset("flag1", "优秀", "flag2", "良好", "flag3", "及格", "flag4", "不及格");
		System.out.println(jedis.mget("flag1", "flag2", "flag3", "flag4"));

		// list
		System.out.println("----------------------------------------");
		jedis.lpush("mylist", "1", "2", "3", "4", "5", "6");
		List<String> list = jedis.lrange("mylist", 0, -1);
		for (String str : list) {
			System.out.println(str);
		}
		// set
		System.out.println("----------------------------------------");
		jedis.sadd("orders", "od0001");
		jedis.sadd("orders", "od0002");
		jedis.sadd("orders", "od0003");
		jedis.sadd("orders", "od0004");
		Set<String> set1 =jedis.smembers("orders");
		for(Iterator<String> iterator=set1.iterator();iterator.hasNext();){
			System.out.println(iterator.next());
		}
		System.out.println(jedis.smembers("orders").size());
		jedis.srem("orders", "od0002");
		System.out.println(jedis.smembers("orders").size());
		
		
	}
}
