package com.auto.test.core.api.http;

import org.apache.http.impl.client.CloseableHttpClient;

public interface IApiSendMessage {
	
	<T> T json2JavaBean(Class<T> c, String text) throws Exception;
	
	String sendGet(CloseableHttpClient httpclient, String url, String author, String channel, String version) throws Exception;
	
	String sendPost(CloseableHttpClient httpclient, String url, String data, String author, String channel, String version, boolean bool) throws Exception;

}
