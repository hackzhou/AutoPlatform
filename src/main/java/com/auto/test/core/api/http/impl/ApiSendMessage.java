package com.auto.test.core.api.http.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.bean.ARunTime;
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
	public String sendGet(CloseableHttpClient httpclient, String url, String author, String channel, String version, ARunTime time) throws Exception{
		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(url);
			httpGet.setHeader(APP_AUTHOR, author);
			httpGet.setHeader(APP_VERSION, version);
			httpGet.setHeader(APP_CHANNEL, channel);
			if(time == null){
				response = httpclient.execute(httpGet);
			}else{
				Long start = System.currentTimeMillis();
				response = httpclient.execute(httpGet);
				time.setTime(System.currentTimeMillis() - start);
			}
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}else{
				throw new BusinessException(response.getStatusLine().getStatusCode() + String.format(PATH, "GET", url, author, version, channel));
			}
		} finally {
			if(httpGet != null){
				httpGet.releaseConnection();
			}
			if(response != null){
				response.close();
			}
		}
	}
	
	@Override
	public String sendPost(CloseableHttpClient httpclient, String url, String data, String author, String channel, String version, boolean bool, ARunTime time) throws Exception{
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader(CONTENT_TYPE, CONTENT_JSON);
			httpPost.setHeader(APP_AUTHOR, author);
			httpPost.setHeader(APP_VERSION, version);
			httpPost.setHeader(APP_CHANNEL, channel);
			if(data != null && !data.isEmpty()){
				httpPost.setEntity(new StringEntity(data));
			}
			if(time == null){
				response = httpclient.execute(httpPost);
			}else{
				Long start = System.currentTimeMillis();
				response = httpclient.execute(httpPost);
				time.setTime(System.currentTimeMillis() - start);
			}
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
			if(httpPost != null){
				httpPost.releaseConnection();
			}
			if(response != null){
				response.close();
			}
		}
	}
	
	@Override
	public String sendPost(CloseableHttpClient httpclient, String url, String data, String author, String channel, String version, boolean bool, File file, ARunTime time) throws Exception{
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			if(data != null && !data.isEmpty()){
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				JSONObject jsonObj = JSON.parseObject(data);
				for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
					if("file".equalsIgnoreCase(entry.getKey())){
						builder.addPart(entry.getKey(), new FileBody(file));
					}else{
						builder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8)));
					}
		        }
				HttpEntity reqEntity = builder.build();
				httpPost.setEntity(reqEntity);
			}else{
				InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1);
				reqEntity.setContentType("binary/octet-stream");
				reqEntity.setContentEncoding("utf-8");
				reqEntity.setChunked(true);
				httpPost.setEntity(reqEntity);
			}
			httpPost.setHeader(APP_AUTHOR, author);
			httpPost.setHeader(APP_VERSION, version);
			httpPost.setHeader(APP_CHANNEL, channel);
			if(time == null){
				response = httpclient.execute(httpPost);
			}else{
				Long start = System.currentTimeMillis();
				response = httpclient.execute(httpPost);
				time.setTime(System.currentTimeMillis() - start);
			}
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
			if(httpPost != null){
				httpPost.releaseConnection();
			}
			if(response != null){
				response.close();
			}
		}
	}
	
	@Override
	public <T> T json2JavaBean(Class<T> c, String text) throws Exception{
		return JSON.parseObject(text, c);
	}
	
}
