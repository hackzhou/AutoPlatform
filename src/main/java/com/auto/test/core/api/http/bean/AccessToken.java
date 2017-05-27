package com.auto.test.core.api.http.bean;

public class AccessToken {
	
	private String code;
	private String message;
	private data data;
	
	public AccessToken() {
		super();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public data getData() {
		return data;
	}
	public void setData(data data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "AccessToken [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
