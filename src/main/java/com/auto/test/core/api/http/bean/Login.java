package com.auto.test.core.api.http.bean;

public class Login {
	
	private String code;
	private String data;
	private String message;
	
	public Login() {
		super();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Login [code=" + code + ", data=" + data + ", message=" + message + "]";
	}

}
