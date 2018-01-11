package com.auto.test.core.api.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.utils.ArrayUtil;

public class HttpClientManager {
	private static final Integer MAX_TOTAL = 500;
	private static final Integer MAX_PERROUTE = MAX_TOTAL / 2;
	private CloseableHttpClient httpClient = null;
	private PoolingHttpClientConnectionManager connManager = null;
	
	public HttpClientManager(){
		super();
	}
	
	public HttpClientManager(Integer type) throws Exception{
		this.connManager = disableSslVerification(type);
		if(this.connManager != null){
			this.connManager.setMaxTotal(MAX_TOTAL);
			this.connManager.setDefaultMaxPerRoute(MAX_PERROUTE);
			this.httpClient = HttpClients.custom().setConnectionManager(this.connManager).build();
		}
	}
	
	private PoolingHttpClientConnectionManager disableSslVerification(Integer type) throws Exception{
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
			return new PoolingHttpClientConnectionManager(socketFactoryRegistry, getDnsResolver(type));
		} catch (Exception e) {
			throw e;
		}
	}
	
	private DnsResolver getDnsResolver(Integer type) throws Exception{
		DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
			@Override
			public InetAddress[] resolve(final String host) throws UnknownHostException {
				if(Arrays.asList(ArrayUtil.getServerUrls()).contains(host)){
					if(new Integer(1).equals(type)){
						return new InetAddress[] {InetAddress.getByName(GlobalValueConfig.getConfig("host.test.ip").trim())};
					}else if(new Integer(2).equals(type)){
						return new InetAddress[] {InetAddress.getByName(GlobalValueConfig.getConfig("host.preview.ip").trim())};
					}else if(new Integer(3).equals(type)){
						return super.resolve(host);
					}else{
						throw new BusinessException("运行环境[type=" + type + "]不存在！");
					}
				}else{
					throw new BusinessException("Host[host=" + host + "]不存在！");
				}
			}
		};
		return dnsResolver;
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
