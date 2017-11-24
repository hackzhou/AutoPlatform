package com.auto.test.core.api.ready.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.core.api.compare.JSONVar;
import com.auto.test.utils.DBUtil;
import com.auto.test.utils.DateUtil;
import com.auto.test.utils.RedisUtil;

public class ReadyDataImpl {
	private static Logger logger = LoggerFactory.getLogger(ReadyDataImpl.class);
	private static final Integer TEST_PORT_3306		= 3306;
	private static final Integer TEST_PORT_8066		= 8066;
	private static final String TEST_IP_219			= "192.168.136.219";
	private static final String TEST_USER			= "dev";
	private static final String TEST_PWD			= "dev@2016";
	
	private static final String TEST_DB_MYCATUIC	= "mycatuic";
	private static final String TEST_DB_MYCATTRANS	= "mycattrans";
	private static final String TEST_DB_UIC			= "uic";
	private static final String TEST_DB_MALL		= "mall";
	private static final String TEST_DB_PLATFORM	= "platform";
	
	public int getUserID(String loginname){
		String sql = "SELECT id FROM uic_user WHERE loginname = '" + loginname + "'";
		int id = new DBUtil().selectUserID(TEST_IP_219, TEST_PORT_8066, TEST_DB_MYCATUIC, TEST_USER, TEST_PWD, sql);
		logger.info("[loginname=" + loginname + "]获取用户ID=" + id);
		return id;
	}
	
	public void AA(int uid){
		String sql = "UPDATE trans_account SET use_amount = '100000000.0000',total_amount = '100000000.0000',grand_amount = '100000000.0000' WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_8066, TEST_DB_MYCATTRANS, TEST_USER, TEST_PWD, sql);
		logger.info("[用户中心-更新我的金叶子为一亿]-->" + sql);
	}
	
	public void AB(String loginname){
		new RedisUtil().DelYanZhengMa(loginname);
		logger.info("[用户中心-清除验证码发送频繁]-->清除Redis缓存[keys *SMS_VERIFY_CODE_LOCK*]");
	}
	
	public void AC(int uid){
		String sql = "DELETE FROM uic_user_weal WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_UIC, TEST_USER, TEST_PWD, sql);
		logger.info("[用户中心-删除个人关联信息(保存本站/修改昵称)]-->" + sql);
	}
	
	public void BA(String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("recordId", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
		logger.info("[商城-更新背包保存中奖地址状态]-->" + sql);
		new RedisUtil().DelShangCheng();
		logger.info("[商城-更新背包保存中奖地址状态]-->清除Redis缓存[keys *AWARDS_SAVE_ADDRESS*]");
	}
	
	public void BB(String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("id", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
		logger.info("[商城-更新背包领取福利状态]-->" + sql);
		new RedisUtil().DelShangCheng();
		logger.info("[商城-更新背包领取福利状态]-->清除Redis缓存[keys *AWARDS_SAVE_ADDRESS*]");
	}
	
	public void CA(int uid){
		String sql = "DELETE FROM plat_coterie_praise WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-删除分享圈点赞]-->" + sql);
		new RedisUtil().DelDianZan();
		logger.info("[App-删除分享圈点赞]-->清除Redis缓存[keys *COTERIE_PRAISE_TIMES*][keys *COTERIE_PRAISE_LOCK*]");
	}
	
	public void CB(int uid){
		String sql = "DELETE FROM plat_coterie_comment_praise WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-删除分享圈评论点赞]-->" + sql);
		new RedisUtil().DelPingLunDianZan();
		logger.info("[App-删除分享圈评论点赞]-->清除Redis缓存[keys *COTERIE_COMMENT_PRAISE_TIMES*][keys *COTERIE_COMMENT_PRAISE_LOCK*]");
	}
	
	public void CC(String body){
		String sql = "UPDATE app_information_user SET is_collect = 0 WHERE id = " + new JSONVar().getValByResult("value", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-更新咨询收藏状态]-->" + sql);
	}
	
	public void CD(String body){
		String sql = "UPDATE app_information_user SET is_praise = 0 WHERE id = " + new JSONVar().getValByResult("value", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-更新咨询点赞状态]-->" + sql);
	}
	
	public void CE(int uid){
		String sql = "DELETE FROM plat_user_check_lottery_log WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-删除每日签到]-->" + sql);
		new RedisUtil().DelMeiRiQianDao();
		logger.info("[App-删除每日签到]-->清除Redis缓存[keys *PLAT_USER*][keys *PLAT_USER_LAST*]");
	}
	
	public void CF(String body){
		String sql = "UPDATE app_user_message SET message_status = 0 WHERE message_id = " + new JSONVar().getValByResult("value", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-消息中心-更新消息状态]-->" + sql);
		new RedisUtil().DelAppXiaoXiZhongXin();
		logger.info("[App-消息中心-更新消息状态]-->清除Redis缓存[keys *APP_MESSAGE*]");
	}
	
	public void CG(String body){
		String sql = "UPDATE app_user_message SET award_status = 1 WHERE message_id = " + new JSONVar().getValByResult("value", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-消息中心-更新领取奖励状态]-->" + sql);
		new RedisUtil().DelAppXiaoXiZhongXin();
		logger.info("[App-消息中心-更新领取奖励状态]-->清除Redis缓存[keys *APP_MESSAGE*]");
	}
	
	public void CH(int uid){
		String d = DateUtil.getFormatDateTime();
		String s = "UPDATE uic_user SET create_time = '" + d + "',update_time = '" + d + "' WHERE id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_8066, TEST_DB_MYCATUIC, TEST_USER, TEST_PWD, s);
		logger.info("[App-更新注册时间]-->" + s);
		String sql = "DELETE FROM plat_new_user_award_log WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[App-删除新人礼包]-->" + sql);
		new RedisUtil().DelXinRenLiBao();
		logger.info("[App-删除新人礼包]-->清除Redis缓存[keys *PLAT_NEW_USER*]");
	}
	
	public void CI(int uid){
		String sql1 = "DELETE FROM plat_club_user_apply WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql1);
		String sql2 = "DELETE FROM plat_club_user WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql2);
		logger.info("[App-删除俱乐部]-->" + sql1 + "," + sql2);
	}
	
	public void CJ(){
		new RedisUtil().DelXiangPiCa();
		logger.info("[App-清除橡皮擦]-->清除Redis缓存[keys *ERASER_RESUME_TIMES*]");
	}
	
	public void DA(String body){
		String sql = "UPDATE wap_user_message SET award_status = 1 WHERE message_id = " + new JSONVar().getValByResult("value", body);
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[Wap-消息中心-更新领取奖励状态]-->" + sql);
		new RedisUtil().DelWapXiaoXiZhongXin();
		logger.info("[Wap-消息中心-更新领取奖励状态]-->清除Redis缓存[keys *WAP_MESSAGE*]");
	}
	
	public void DB(int uid){
		String sql = "DELETE FROM plat_signed_user WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_219, TEST_PORT_3306, TEST_DB_PLATFORM, TEST_USER, TEST_PWD, sql);
		logger.info("[Wap-老签到-删除签到]-->" + sql);
		new RedisUtil().DelWapLaoQianDao();
		logger.info("[Wap-老签到-删除签到]-->清除Redis缓存[keys *SIGNING*]");
	}
	
	public void EA(int uid){
		new RedisUtil().DelFuFeiZhuanPan(uid);
		logger.info("[运营活动-清除付费转盘次数]-->清除Redis缓存[del OPS:WHEEL:WHEEL_FEE_USER_BET_TIME:1:" + uid + "]");
	}

}
