package com.auto.test.common.constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Const {
	
	public static final String IP_ONLINE			= "192.168.101.245";
	public static final String IP_TEST				= "192.168.101.59";
	public static final String IP_CURRENT			= getCurrentIP();
	
	public static final String RUN_PROJECT_NAME		= "项目[%s]";
	public static final String RUN_CASE_NAME		= "案例[%s]";

	public static final String API_PLATFORM			= "api_platform";
	
	public static final String DATE_FORAMT_1		= "yyyy-MM-dd HH:mm:ss";
	
	public static String getCurrentIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress().trim();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
}
