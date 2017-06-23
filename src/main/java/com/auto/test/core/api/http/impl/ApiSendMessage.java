package com.auto.test.core.api.http.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.http.IApiSendMessage;

public class ApiSendMessage implements IApiSendMessage {
	private static final String APP_AUTHOR	= "Authorization";
	private static final String APP_VERSION	= "App-Version";
	private static final String APP_CHANNEL	= "App-Channel";
	private static final String CONTENT_TYPE= "Content-Type";
	private static final String CONTENT_JSON= "application/json; charset=UTF-8";
	private static final String PATH		= "-->[%s:%s],[Authorization:%s],[Version:%s],[Channel:%s]";
	private static final String DATA		= "-->[Data:%s]";
	
	@Override
	public <T> T json2JavaBean(Class<T> c, String text) throws Exception{
		return JSON.parseObject(text, c);
	}

	@Override
	public String sendGet(String url, String author, String channel, String version) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader(APP_AUTHOR, author);
			httpGet.setHeader(APP_VERSION, version);
			httpGet.setHeader(APP_CHANNEL, channel);
			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}else{
				throw new BusinessException(response.getStatusLine().getStatusCode() + String.format(PATH, "GET", url, author, version, channel));
			}
		} finally {
			if(response != null){
				response.close();
			}
			if(httpclient != null){
				httpclient.close();
			}
		}
	}

	@Override
	public String sendPost(String url, String data, String author, String channel, String version, boolean bool) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader(CONTENT_TYPE, CONTENT_JSON);
			httpPost.setHeader(APP_AUTHOR, author);
			httpPost.setHeader(APP_VERSION, version);
			httpPost.setHeader(APP_CHANNEL, channel);
			if(data != null && !data.isEmpty()){
				httpPost.setEntity(new StringEntity(data));
			}
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}else{
				if(bool){
					throw new BusinessException(response.getStatusLine().getStatusCode() + String.format(PATH, "POST", url, author, version, channel) + String.format(DATA, data));
				}else{
					throw new BusinessException(response.getStatusLine().getStatusCode() + String.format(PATH, "POST", url, author, version, channel));
				}
			}
		} finally {
			if(response != null){
				response.close();
			}
			if(httpclient != null){
				httpclient.close();
			}
		}
	}
	
}
