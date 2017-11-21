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
		RedisUtil ru = new RedisUtil();
		Set<String> set = ru.getJedis().keys("*SMS_DATE_MAX*");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public void DeXinRenLiBao(){//新人礼包
		batchDel("*PLAT_NEW_USER*");
		close();
	}
	
	public void DeXiaoXiZhongXin(){//消息中心
		batchDel("*APP_MESSAGE*");
		close();
	}
	
	public void DelMeiRiQianDao(){//每日签到
		batchDel("*PLAT_USER*");
		batchDel("*PLAT_USER_LAST*");
		close();
	}
	
	public void DelYanZhengMa(String loginname){//验证码
		batchDel("*SMS_VERIFY_CODE_LOCK*");
		batchDel("*SMS_DATE_MAX:" + loginname + "*");
		close();
	}
	
	public void DelShangCheng(){//商城
		batchDel("*AWARDS_SAVE_ADDRESS*");
		close();
	}
	
	public void batchDel(String text){
		Set<String> set = jedis.keys(text);
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

	public Jedis getJedis() {
		return jedis;
	}
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
}
