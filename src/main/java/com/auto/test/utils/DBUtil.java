package com.auto.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static final String DB_MYSQL_DRIVER	= "com.mysql.jdbc.Driver";
	public static final String DB_URL			= "jdbc:mysql://%s:%s/%s";
	public static final String DB_URL_PARAM		= "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2b8&useSSL=false";
	
	public static void main(String[] args) {
		String sql = "UPDATE u_code SET devices = '3' WHERE id = 1;";
		updateSQL("localhost", 3306, "autotest0", "root", "zhouzhou", sql);
	}
	
	public static void updateSQL(String ip, Integer port, String db, String user, String pwd, String sql){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		Statement stm = null;
		try {
			con = DriverManager.getConnection(String.format(DB_URL, ip, port, db) + DB_URL_PARAM, user, pwd);
			stm = con.createStatement();
			con.setAutoCommit(true);
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stm != null){
					stm.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
