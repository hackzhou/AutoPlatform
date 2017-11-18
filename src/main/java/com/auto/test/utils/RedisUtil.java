package com.auto.test.utils;

import java.util.Iterator;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class RedisUtil {
	private static final String TEST_IP		= "192.168.136.209";
	private Jedis jedis = null;
	
	public RedisUtil(){
		super();
		jedis = new Jedis(TEST_IP);
	}

	public static void main(String[] args) {
		new RedisUtil().close();
	}
	
	public void DelShangCheng(){
		batchDel("AWARDS_SAVE_ADDRESS");
		close();
	}
	
	public void batchDel(String text){
		Set<String> set = jedis.keys("*" + text + "*");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			jedis.del(it.next());
		}
	}
	
	public void close(){
		if(jedis != null){
			jedis.close();
			jedis.quit();
		}
	}
	
	public void ping(){
		System.out.println(jedis.ping());
	}
}
