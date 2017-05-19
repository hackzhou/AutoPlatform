package com.auto.test.utils;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.exception.BusinessException;

public class HttpUtil {
	
	public static void main(String[] args) {
//		sendGet("https://www.jddfun.com/api_app/api/app/usercenter/getUserPersonalInfo");
//		sendPost("https://www.jddfun.com/api_app/api/app/usercenter/appVersionUpdate", "");
//		sendPost("https://www.jddfun.com/api_app/api/app/coterie/list", "{\"page\":1,\"pageSize\":10}");
	
		parseJSONObject("aaa", false);
	}

	public static void sendGet(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Authorization", "f6318746ed6847d9bb421d4bf886d150");
			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				System.out.println(conResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null){
					response.close();
				}
				if(httpclient != null){
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sendPost(String url, String data){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httpPost.setHeader("Authorization", "f6318746ed6847d9bb421d4bf886d150");
			httpPost.setHeader("App-Channel", "100004");
			httpPost.setHeader("App-Version", "1.0.3");
			httpPost.setEntity(new StringEntity(data));
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				System.out.println(parseJSONObject(conResult, true));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null){
					response.close();
				}
				if(httpclient != null){
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String parseJSONObject(String result, boolean format){
		try {
			JSONObject json = JSON.parseObject(result);
			return JSON.toJSONString(json, format);
		} catch (JSONException e) {
			throw new BusinessException("JSON格式错误[" + result + "]");
		}
	}
	
}
