package com.auto.test.core.api.ready.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.core.api.compare.JSONVar;
import com.auto.test.utils.DBUtil;
import com.auto.test.utils.RedisUtil;

public class ReadyDataImpl {
	private static Logger logger = LoggerFactory.getLogger(ReadyDataImpl.class);
	private static final Integer TEST_PORT_3306		= 3306;
	private static final Integer TEST_PORT_8066		= 8066;
	private static final String TEST_IP_209			= "192.168.136.219";
	private static final String TEST_USER			= "dev";
	private static final String TEST_PWD			= "dev@2016";
	
	private static final String TEST_DB_MYCATUIC	= "mycatuic";
	private static final String TEST_DB_MYCATTRANS	= "mycattrans";
	private static final String TEST_DB_UIC			= "uic";
	private static final String TEST_DB_MALL		= "mall";
	
	public int getUserID(String loginname){
		String sql = "SELECT id FROM uic_user WHERE loginname = '" + loginname + "'";
		int id = new DBUtil().selectUserID(TEST_IP_209, TEST_PORT_8066, TEST_DB_MYCATUIC, TEST_USER, TEST_PWD, sql);
		logger.info("[loginname=" + loginname + "]获取用户ID=" + id);
		return id;
	}
	
	public void AA(int uid){
		String sql = "UPDATE trans_account SET use_amount = '100000000.0000',total_amount = '100000000.0000',grand_amount = '100000000.0000' WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_8066, TEST_DB_MYCATTRANS, TEST_USER, TEST_PWD, sql);
		logger.info("[用户中心-更新我的金叶子为一亿]-->" + sql);
	}
	
	public void AB(String loginname){
		new RedisUtil().DelYanZhengMa(loginname);
		logger.info("[用户中心-清除验证码发送频繁]-->清除Redis缓存[keys *SMS_VERIFY_CODE_LOCK*]");
	}
	
	public void AC(int uid){
		String sql = "DELETE FROM uic_user_weal WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_UIC, TEST_USER, TEST_PWD, sql);
		logger.info("[用户中心-删除个人关联信息(保存本站/修改昵称)]-->" + sql);
	}
	
	public void BA(String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("recordId", body);
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
		logger.info("[商城-更新背包保存中奖地址状态]-->" + sql);
		new RedisUtil().DelShangCheng();
		logger.info("[商城-更新背包保存中奖地址状态]-->清除Redis缓存[keys *AWARDS_SAVE_ADDRESS*]");
	}
	
	public void BB(String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("id", body);
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
		logger.info("[商城-更新背包领取福利状态]-->" + sql);
		new RedisUtil().DelShangCheng();
		logger.info("[商城-更新背包领取福利状态]-->清除Redis缓存[keys *AWARDS_SAVE_ADDRESS*]");
	}

}
