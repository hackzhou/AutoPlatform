package com.auto.test.utils;

import java.io.IOException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.core.api.http.impl.ApiSendMessage;

public class ReadyUtil {
	private static final String URL_LOGIN_VISITOR		= "http://uic-api.beeplay123.com/uic/api/user/login/visitor";
	private static final String URL_LOGIN_ACCESSTOKEN	= "http://uic-api.beeplay123.com/uic/api/user/login/accessToken";
	private static final String BODY_VISITOR			= "{\"source\":1,\"visitorToken\":\"08ef7567-cfa1-3e46-9060-f62f846cb0fa\"}";
	private static final String BODY_ACCESSTOKEN		= "{\"type\":1,\"token\":\"%s\"}";
	private static final String APP_VERSION				= "1.0.0";
	private static final String APP_CHANNEL				= "200001";
	private static final String VAR_DATA				= "data";
	private static final String VAR_ACCESSTOKEN			= "accessToken";
	
	public static void main(String[] args) {
		System.out.println(new ReadyUtil().getVisitorToken());
	}
	
	public String getVisitorToken(){
		String result = sendMessage(URL_LOGIN_VISITOR, BODY_VISITOR, APP_VERSION, APP_CHANNEL);
		String data = getTokenData(result);
		result = sendMessage(URL_LOGIN_ACCESSTOKEN, String.format(BODY_ACCESSTOKEN, data), APP_VERSION, APP_CHANNEL);
		return getAccessToken(result);
	}
	
	public String sendMessage(String url, String data, String version, String channel){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = httpClientBuilder.build();
		ApiSendMessage sendMessage = new ApiSendMessage();
		try {
			return sendMessage.sendPost(httpClient, url, data, null, channel, version, false, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null){
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
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
