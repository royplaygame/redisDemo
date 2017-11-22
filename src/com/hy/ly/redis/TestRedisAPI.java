package com.hy.ly.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisAPI {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.set("k1", "v1");
		jedis.set("k2", "v2");
		jedis.set("k3", "v3");
		jedis.set("k4", "v4");
		System.out.println(jedis.get("k1"));
		Set<String> set = jedis.keys("*");
		System.out.println(set);
		System.out.println(jedis.exists("k3"));
		System.out.println(jedis.ttl("k1"));
	}

	@Test
	public void test1() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.expire("k2", 60);
		System.out.println(jedis.ttl("k2"));

		jedis.append("k1", " this is good for");
		System.out.println(jedis.get("k1"));
	}

	@Test
	public void test2() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.mset("l1", "优秀", "l2", "良好", "l3", "及格", "l4", "不及格");
		System.out.println(jedis.mget("l1", "l2", "l3"));
	}

	// list
	@Test
	public void test3() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.lpush("list", "1", "2", "3", "4", "5", "6", "7");
		System.out.println(jedis.lpop("list"));
		List<String> list = jedis.lrange("list", 0, -1);
		System.out.println(list);
	}

	// set
	@Test
	public void test4() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.sadd("set", "11", "22", "33", "44", "55", "66");
		Set<String> set = jedis.smembers("set");
		System.out.println(set);
		System.out.println(set.size());
	}

	// hash
	@Test
	public void test5() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.hset("map", "username", "zhangsan");
		jedis.hset("map", "password", "123456");
		jedis.hset("map", "mobile", "18366668888");
		jedis.hset("map", "address", "河南洛阳");
		jedis.hset("map", "type", "管理员");
		System.out.println(jedis.hget("map", "mobile"));

		List<String> list = jedis.hmget("map", "username", "password", "mobile", "address", "type");
		System.out.println(list);
	}

	// zset
	@Test
	public void test6() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.zadd("zset01", 60d, "v1");
		jedis.zadd("zset01", 70d, "v2");
		jedis.zadd("zset01", 80d, "v3");
		jedis.zadd("zset01", 90d, "v4");

		Set<String> s1 = jedis.zrange("zset01", 0, -1);
		for (Iterator<String> iterator = s1.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
		Set<String> str1=jedis.zrangeByScore("zset01", 0, -1);
		for (Iterator<String> iterator = str1.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}
}
