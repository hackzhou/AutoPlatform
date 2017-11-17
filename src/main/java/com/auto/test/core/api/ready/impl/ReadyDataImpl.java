package com.auto.test.core.api.ready.impl;

import com.auto.test.core.api.compare.JSONVar;
import com.auto.test.utils.DBUtil;

public class ReadyDataImpl {
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
		return new DBUtil().selectUserID(TEST_IP_209, TEST_PORT_8066, TEST_DB_MYCATUIC, TEST_USER, TEST_PWD, sql);
	}
	
	public void A(int uid, String body){
		String sql = "UPDATE trans_account SET use_amount = '100000000.0000',total_amount = '100000000.0000',grand_amount = '100000000.0000' WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_8066, TEST_DB_MYCATTRANS, TEST_USER, TEST_PWD, sql);
	}
	
	public void B(int uid, String body){
		String sql = "DELETE FROM uic_user_weal WHERE user_id = " + uid;
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_UIC, TEST_USER, TEST_PWD, sql);
	}
	
	public void C(int uid, String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("recordId", body);
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
	}
	
	public void D(int uid, String body){
		String sql = "UPDATE inventory_phy_awards_sendlog SET receive_status = 1 WHERE id = " + new JSONVar().getValByResult("id", body);
		new DBUtil().updateSQL(TEST_IP_209, TEST_PORT_3306, TEST_DB_MALL, TEST_USER, TEST_PWD, sql);
	}

}
