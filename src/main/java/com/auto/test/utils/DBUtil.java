package com.auto.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static final String DB_MYSQL_DRIVER	= "com.mysql.jdbc.Driver";
	public static final String DB_URL			= "jdbc:mysql://%s:%s/%s";
	public static final String DB_URL_PARAM		= "?useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2b8&useSSL=false";
	
	public static void main(String[] args) {
		String sql = "UPDATE u_code SET devices = '3' WHERE id = 1;";
		new DBUtil().updateSQL("localhost", 3306, "autotest0", "root", "zhouzhou", sql);
	}
	
	public Connection getConn(String ip, Integer port, String db, String user, String pwd) {
	    Connection conn = null;
	    try {
	    	Class.forName(DB_MYSQL_DRIVER);
	    	conn = (Connection) DriverManager.getConnection(String.format(DB_URL, ip, port, db) + DB_URL_PARAM, user, pwd);
	    	conn.setAutoCommit(true);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public int updateSQL(String ip, Integer port, String db, String user, String pwd, String sql){
		Connection con = getConn(ip, port, db, user, pwd);
		Statement stm = null;
		try {
			stm = con.createStatement();
			return stm.executeUpdate(sql);
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
		return 0;
	}
	
	public int selectUserID(String ip, Integer port, String db, String user, String pwd, String sql){
		Connection con = getConn(ip, port, db, user, pwd);
		Statement stm = null;
		ResultSet rs = null;
		int id = 0;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
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
		return id;
	}
	
}
