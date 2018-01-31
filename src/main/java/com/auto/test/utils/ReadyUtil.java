package com.auto.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.constant.Const;
import com.auto.test.core.api.http.HttpClientManager;
import com.auto.test.core.api.http.impl.ApiSendMessage;

public class ReadyUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReadyUtil.class);
	//private static final String URL_LOGIN_VISITOR		= "https://game-api.yingdd888.com/" + "uic/api/user/login/visitor";
	//private static final String URL_LOGIN_ACCESSTOKEN	= "https://game-api.yingdd888.com/" + "uic/api/user/login/accessToken";
	private static final String URL_LOGIN_VISITOR		= Const.API_HTTPS + "uic-api.beeplay123.com/uic/api/user/login/visitor";
	private static final String URL_LOGIN_ACCESSTOKEN	= Const.API_HTTPS + "uic-api.beeplay123.com/uic/api/user/login/accessToken";
	//private static final String BODY_VISITOR			= "{\"source\":1,\"visitorToken\":\"08ef7567-cfa1-3e46-9060-f62f846cb0fa\"}";
	private static final String BODY_VISITOR			= "{\"source\":1,\"visitorToken\":\"\"}";
	private static final String BODY_ACCESSTOKEN		= "{\"type\":1,\"token\":\"%s\"}";
	private static final String APP_VERSION				= "1.0.0";
	private static final String APP_CHANNEL				= "200001";
	private static final String VAR_DATA				= "data";
	private static final String VAR_ACCESSTOKEN			= "accessToken";
	
	public static void main(String[] args) {
		try {
			ReadyUtil readyUtil = new ReadyUtil();
			HttpClientManager httpClientManager = new HttpClientManager(3);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 3000; i++) {
				String token = readyUtil.getVisitorToken(httpClientManager);
				sb.append(token + "\r\n");
				System.out.println(token);
				Thread.sleep(1000);
			}
			httpClientManager.close();
			new FileUtil().writeJavaFile("D:\\", "TOKEN.txt", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getVisitorToken(HttpClientManager httpClientManager) throws Exception{
		String result = sendMessage(httpClientManager, URL_LOGIN_VISITOR, BODY_VISITOR, APP_VERSION, APP_CHANNEL);
		String data = getTokenData(result);
		result = sendMessage(httpClientManager, URL_LOGIN_ACCESSTOKEN, String.format(BODY_ACCESSTOKEN, data), APP_VERSION, APP_CHANNEL);
		return getAccessToken(result);
	}
	
	public String sendMessage(HttpClientManager httpClientManager, String url, String data, String version, String channel) throws Exception{
		ApiSendMessage sendMessage = new ApiSendMessage();
		try {
			logger.info("[游客登录]==>[POST:" + url + "][Version:" + version + "],[Channel:" + channel + "],[Data:" + data + "]");
			String result = sendMessage.sendPost(httpClientManager.getHttpClient(), url, data, null, channel, version, false, null);
			logger.info("[游客登录]==>[" + result + "]");
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String getTokenData(String data){
		JSONObject obj = JSON.parseObject(data);
		Object o = obj.get(VAR_DATA);
		return o == null ? "" : (String) o;
	}
	
	public String getAccessToken(String data){
		JSONObject obj = JSON.parseObject(data);
		Object o = obj.get(VAR_DATA);
		if(o != null){
			String s = JSON.toJSONString(o);
			if(s != null && s.startsWith("{") && s.endsWith("}")){
				obj = JSON.parseObject(s);
				o = obj.get(VAR_ACCESSTOKEN);
				return o == null ? "" : (String) o;
			}
		}
		return "";
	}
}
