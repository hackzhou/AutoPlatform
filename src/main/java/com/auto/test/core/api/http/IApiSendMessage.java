package com.auto.test.core.api.http;

public interface IApiSendMessage {
	
	<T> T json2JavaBean(Class<T> c, String text);
	
	String sendGet(String url, String author, String channel, String version);
	
	String sendPost(String url, String data, String author, String channel, String version);

}
