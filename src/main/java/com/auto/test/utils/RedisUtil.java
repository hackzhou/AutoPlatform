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
	
	public void DelWapLaoQianDao(){//老签到
		batchDel("*SIGNING_LIST*");
		batchDel("*SIGNING*");
		close();
	}
	
	public void DelFuFeiZhuanPan(int uid){//付费转盘
		batchDel("*OPS:WHEEL:WHEEL_FEE_USER_BET_TIME:1:" + uid + "*");
		close();
	}

	public void DelXiangPiCa(){//橡皮擦
		batchDel("*ERASER_RESUME_TIMES*");
		close();
	}
	
	public void DelXinRenLiBao(){//新人礼包
		batchDel("*PLAT_NEW_USER*");
		batchDel("*UIC_USER_BY_ID*");
		batchDel("*IS_NEW_USER_FIRST_IN*");
		close();
	}
	
	public void DelWapXiaoXiZhongXin(){//消息中心
		batchDel("*WAP_MESSAGE*");
		close();
	}
	
	public void DelAppXiaoXiZhongXin(){//消息中心
		batchDel("*APP_MESSAGE*");
		close();
	}
	
	public void DelDianZan(){//点赞
		batchDel("*COTERIE_PRAISE_TIMES*");
		batchDel("*COTERIE_PRAISE_LOCK*");
		close();
	}
	
	public void DelPingLunDianZan(){//评论点赞
		batchDel("*COTERIE_COMMENT_PRAISE_TIMES*");
		batchDel("*COTERIE_COMMENT_PRAISE_LOCK*");
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
	
	public void del(String text){
		jedis.del(text);
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
