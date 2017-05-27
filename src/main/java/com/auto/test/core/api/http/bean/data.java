package com.auto.test.core.api.http.bean;

public class data {
	
	private Integer expireIn;
	private String accessToken;
	private String refreshToken;
	
	public data() {
		super();
	}
	
	public Integer getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(Integer expireIn) {
		this.expireIn = expireIn;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "data [expireIn=" + expireIn + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}

}
