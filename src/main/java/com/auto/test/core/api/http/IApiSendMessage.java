package com.auto.test.core.api.http;

import java.io.File;
import org.apache.http.impl.client.CloseableHttpClient;
import com.auto.test.common.bean.ARunTime;

public interface IApiSendMessage {
	
	<T> T json2JavaBean(Class<T> c, String text) throws Exception;
	
	String sendGet(CloseableHttpClient httpclient, String url, String author, String channel, String version, ARunTime time) throws Exception;
	
	String sendPost(CloseableHttpClient httpclient, String url, String data, String author, String channel, String version, boolean bool, ARunTime time) throws Exception;

	String sendPost(CloseableHttpClient httpclient, String url, String data, String author, String channel, String version, boolean bool, File file, ARunTime time) throws Exception;

}
