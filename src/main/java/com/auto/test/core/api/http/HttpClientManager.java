package com.auto.test.core.api.http;

import java.io.IOException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientManager {
	private CloseableHttpClient httpClient = null;
	private PoolingHttpClientConnectionManager connManager = null;
	
	public HttpClientManager(){
		this.connManager = disableSslVerification();
		if(this.connManager != null){
			this.httpClient = HttpClients.custom().setConnectionManager(this.connManager).build();
		}
	}
	
	private PoolingHttpClientConnectionManager disableSslVerification() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					}
					
					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					}
					
					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				}
			};
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sc)).build();
			return new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		try {
			if(httpClient != null){
				httpClient.close();
			}
			if(connManager != null){
				connManager.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

}
