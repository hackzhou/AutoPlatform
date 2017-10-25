package com.auto.test.common.bean;

import com.auto.test.entity.AResultDetail;

public class AResultFail {
	
	private String id;
	private String name;
	private String type;
	private String url;
	private String message;
	
	public AResultFail() {
		super();
	}
	
	public AResultFail(AResultDetail resultDetail) {
		super();
		this.id = String.valueOf(resultDetail.getId());
		this.name = resultDetail.getName();
		this.type = resultDetail.getType();
		this.url = resultDetail.getUrl();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "AResultFail [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", message=" + message
				+ "]";
	}
	
}
