package com.auto.test.utils;

import java.io.IOException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.auto.test.core.api.http.impl.ApiSendMessage;

public class ReadyUtil {
	
	public static void main(String[] args) {
//		192.168.136.161
//		123qwe!@#
//		cd /wfApp/wf_uic_api/tomcat8/logs/
//		tail -f catalina.out
		String version = "1.0.0";
		String channel = "100006";
		sendMessage("http://uic-api.beeplay123.com/uic/api/user/register/sendCode", "{\"username\":\"13151815253\"}", channel, version);
//		sendMessage("http://uic-api.beeplay123.com/uic/api/user/register/save", "{\"username\":\"13151815253\",\"password\":\"zhouzhou\",\"smsCode\":\"123456\"}", channel, version);
	}
	
	public static void sendMessage(String url, String data, String channel, String version){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = httpClientBuilder.build();
		ApiSendMessage sendMessage = new ApiSendMessage();
		try {
			String result = sendMessage.sendPost(httpClient, url, data, null, channel, version, false, null);
			System.out.println(result);
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
	}

}
