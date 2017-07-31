package com.auto.test.common.bean;

public class AInterfaceCase {
	private Integer project;
	private String name;
	private String type;
	private String url;
	private String description;
	private Integer version;
	private String body;
	private Integer rowNum;
	
	public AInterfaceCase() {
		super();
	}
	
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
				+ ", description=" + description + ", version=" + version + ", body=" + body + ", rowNum=" + rowNum
				+ "]";
	}

}
