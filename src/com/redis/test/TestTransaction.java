package com.redis.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.watch("k5");
		Transaction transaction = jedis.multi();
		transaction.set("k5", "v5");
		transaction.set("k6", "v6");
		transaction.exec();
		transaction.discard();
	}

	@Test
	public void testTransaction() {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		// 监控key，如果该动了事务就被放弃
		/*
		 * 3 jedis.watch("serialNum");
		 * jedis.set("serialNum","s#####################"); jedis.unwatch();
		 */

		Transaction transaction = jedis.multi();// 被当作一个命令进行执行
		Response<String> response = transaction.get("serialNum");
		transaction.set("serialNum", "s002");
		response = transaction.get("serialNum");
		transaction.lpush("list3", "a");
		transaction.lpush("list3", "b");
		transaction.lpush("list3", "c");

		transaction.exec();
		// 2 transaction.discard();
		System.out.println("serialNum***********" + response.get());
		System.out.println("lpush====="+jedis.lrange("list3", 0, -1));
	}
	
	
	@Test
	public void tranferMoney() throws InterruptedException{
		Jedis jedis=new Jedis("192.168.56.102",6379);
		int balance;
		int debt;
		int takeMoney=20;
		//设置初始值
		jedis.set("balance", "100");
		jedis.set("debt", "0");
		jedis.watch("balance");
		//在这个地方加一下延时操作
		Thread.sleep(15000);
		balance=Integer.valueOf(jedis.get("balance"));
		System.out.println("=========================================");
		if(balance<takeMoney){
			jedis.unwatch();
			System.out.println("余额不够，不能操作");
		}else{
			
			System.out.println("***************begin transaction");
			Thread.sleep(15000);
			Transaction transaction=jedis.multi();
			transaction.decrBy("balance",takeMoney);
			transaction.incrBy("debt", takeMoney);
			transaction.exec();
			balance=Integer.valueOf(jedis.get("balance"));
			debt=Integer.valueOf(jedis.get("debt"));
			System.out.println("余额是："+balance);
			System.out.println("debt是："+debt);
		}
		
	}
}
