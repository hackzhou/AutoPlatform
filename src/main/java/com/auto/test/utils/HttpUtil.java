package com.auto.test.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.constant.Const;
import com.auto.test.common.exception.BusinessException;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static final Integer CONNECT_TIMEOUT = 1000 * 30;
	private static final Integer REQUEST_TIMEOUT = 1000 * 30;
	private static final Integer SOCKET_TIMEOUT = 1000 * 300;
	
	public static void main(String[] args) {
		System.out.println(new HttpUtil().isAvailablePort(Const.IP_SERVER_1 + ".182", 8090));
	}
	
	private RequestConfig getTimeOutConfig(){
		return RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
	}
	
	public String sendGet(String url){
		return sendGet(url, true);
	}

	public String sendGet(String url, boolean bool){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(getTimeOutConfig());
			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				if(bool){
					logger.info("[Http]==>[" + url +"][" + result + "]");
				}
				return result;
			}else{
				logger.info("[Http]==>[" + url +"][" + response.getStatusLine().getStatusCode() + "]");
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
	
	public String sendPost(String url, String data){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(data));
			httpPost.setConfig(getTimeOutConfig());
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				logger.info("[Http]==>[" + url +"][" + result + "]");
				return result;
			}else{
				logger.info("[Http]==>[" + url +"][" + response.getStatusLine().getStatusCode() + "]");
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
	
	public String sendPost(String url, File file){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("file", new FileBody(file));
			HttpEntity reqEntity = builder.build();
			httpPost.setEntity(reqEntity);
			httpPost.setHeader("Content-Type", "multipart/form-data;");
			httpPost.setConfig(getTimeOutConfig());
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				logger.info("[Http]==>[" + url +"][" + result + "]");
				return result;
			}else{
				logger.info("[Http]==>[" + url +"][" + response.getStatusLine().getStatusCode() + "]");
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
	
	public String parseJSONObject(String result, boolean format){
		try {
			JSONObject json = JSON.parseObject(result);
			return JSON.toJSONString(json, format);
		} catch (JSONException e) {
			throw new BusinessException("JSON格式错误[" + result + "]");
		}
	}

	public <T> T json2JavaBean(Class<T> c, String text){
		return JSON.parseObject(text, c);
	}
	
	public boolean isAvailablePort(String host, int port){
		Socket socket = new Socket();
        try {
        	socket.connect(new InetSocketAddress(host, port));
        	return true;
        } catch (UnknownHostException e) {
        	throw new BusinessException("服务器访问地址不通[" + host + "]");
		} catch (IOException e) {
			return false;
        } finally {
        	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
}
