package com.auto.test.common.bean;

public class AInterfaceCase {
	private String project;
	private String name;
	private String type;
	private String url;
	private String description;
	
	private String version;
	private String body;
	private String result;
	private String strategy;
	private String login;
	
	private Integer rowNum;
	
	public AInterfaceCase() {
		super();
	}
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "AInterfaceCase [project=" + project + ", name=" + name + ", type=" + type + ", url=" + url
				+ ", description=" + description + ", version=" + version + ", body=" + body + ", result=" + result
				+ ", strategy=" + strategy + ", login=" + login + ", rowNum=" + rowNum + "]";
	}

}
