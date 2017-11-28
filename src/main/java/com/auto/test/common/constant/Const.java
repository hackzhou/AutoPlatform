package com.auto.test.common.constant;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.auto.test.common.config.GlobalValueConfig;

public class Const {
	
	public static final String IP_SERVER_1				= "10.33.85";
	public static final String IP_SERVER_2				= "192.168.136";
	public static final String IP_SERVER_TEST			= IP_SERVER_2 + ".208";
	public static final String IP_ONLINE				= IP_SERVER_1 + ".242";
	public static final String IP_TEST					= "10.33.80.18";
	public static final String IP_CURRENT				= getCurrentIP();
	
	public static final String RUN_PROJECT_NAME_Y		= "项目[%s]-已登录";
	public static final String RUN_CASE_NAME_Y			= "案例[%s]-已登录";
	public static final String RUN_PROJECT_NAME_N		= "项目[%s]-未登录";
	public static final String RUN_CASE_NAME_N			= "案例[%s]-未登录";

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
	
	public static final String IMG_PARAME_KEY			= GlobalValueConfig.getConfig("img.parame.key");
	
	public static String getCurrentIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress().trim();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
