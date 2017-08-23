package com.auto.test.common.constant;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Const {
	
	public static final String IP_ONLINE				= "192.168.101.245";
	public static final String IP_TEST					= "192.168.101.59";
	public static final String IP_CURRENT				= getCurrentIP();
	
	public static final String RUN_PROJECT_NAME			= "项目[%s]";
	public static final String RUN_CASE_NAME			= "案例[%s]";

	public static final String API_PLATFORM				= "api_platform";
	public static final String AUTO_PLATFORM			= "AutoPlatform";
	public static final String PATH_AUTO_PLATFORM		= System.getProperty("user.home") + File.separator + AUTO_PLATFORM;
	public static final String PATH_UI_CODE				= PATH_AUTO_PLATFORM + File.separator + "code";
	
	public static final String PATH_FILE				= PATH_AUTO_PLATFORM + File.separator + "file";
	public static final String PATH_FILE_WAR			= PATH_FILE + File.separator + "war";
	public static final String PATH_FILE_TEMP			= PATH_FILE + File.separator + "temp";
	public static final String PATH_FILE_TEMP_CLASSES	= PATH_FILE_TEMP + File.separator + "WEB-INF" + File.separator + "classes";
	public static final String PATH_FILE_PROPERTIES		= PATH_FILE_TEMP_CLASSES + File.separator + "application.properties";
	
	public static final String SVN_USERNAME 			= "zhouzhou";
	public static final String SVN_PASSWORD 			= "Jih3wroK1d19yerM";
	public static final String SVN_LKCZ_QA_TEST			= "https://61.155.136.217:8443/svn/LKCZ/QA/application_test/";
	public static final String SVN_LKCZ_PUBLISH			= "https://61.155.136.217:8443/svn/LKCZ/publish/";
	
	public static final String DATE_FORAMT_1			= "yyyy-MM-dd HH:mm:ss";

	public static final String PLATFORM_ANDROID			= "Android";
	public static final String PLATFORM_IOS				= "iOS";
	
	public static final String KEYBOARD_RETURN			= "返回";
	public static final String KEYBOARD_ENTER			= "回车";
	public static final String KEYBOARD_SEARCH			= "搜索";
	
	public static String getCurrentIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress().trim();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
