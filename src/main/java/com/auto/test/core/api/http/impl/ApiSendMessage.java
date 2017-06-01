package com.auto.test.core.api.http.impl;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.http.IApiSendMessage;

public class ApiSendMessage implements IApiSendMessage {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiSendMessage.class);
	
	@Override
	public <T> T json2JavaBean(Class<T> c, String text){
		return JSON.parseObject(text, c);
	}

	@Override
	public String sendGet(String url, String author, String channel, String version) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Authorization", author);
			httpGet.setHeader("App-Channel", channel);
			httpGet.setHeader("App-Version", version);
			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}else{
				throw new BusinessException(String.valueOf(response.getStatusLine().getStatusCode()));
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
		return null;
	}

	@Override
	public String sendPost(String url, String data, String author, String channel, String version) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httpPost.setHeader("Authorization", author);
			httpPost.setHeader("App-Channel", channel);
			httpPost.setHeader("App-Version", version);
			if(data != null && !data.isEmpty()){
				httpPost.setEntity(new StringEntity(data));
			}
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}else{
				throw new BusinessException(String.valueOf(response.getStatusLine().getStatusCode()));
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
		return null;
	}
	
}
