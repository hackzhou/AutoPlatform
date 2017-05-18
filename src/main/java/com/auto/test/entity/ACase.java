package com.auto.test.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="a_case")
public class ACase implements Serializable{
	private static final long serialVersionUID = -372190487810560745L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="project_id")
	private AProject projecto;
	
	@Column(name="name")
	private String name;
	
	@Column(name="method")
	private String method;

	@Column(name="url")
	private String url;
	
	@Column(name="header")
	private String header;

	@Column(name="body")
	private String body;
	
	@Column(name="result")
	private String result;
	
	@Column(name="strategy")
	private String strategy;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;

	public ACase() {
		super();
	}
	
	public void updateACase(ACase aCase) {
		this.projecto = aCase.getProjecto();
		this.name = aCase.getName();
		this.method = aCase.getMethod();
		this.url = aCase.getUrl();
		this.header = aCase.getHeader();
		this.body = aCase.getBody();
		this.result = aCase.getResult();
		this.strategy = aCase.getStrategy();
		this.updateTime = new Date();
		this.memo = aCase.getMemo();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AProject getProjecto() {
		return projecto;
	}
	public void setProjecto(AProject projecto) {
		this.projecto = projecto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "ACase [id=" + id + ", projecto=" + projecto + ", name=" + name + ", method=" + method + ", url=" + url
				+ ", header=" + header + ", body=" + body + ", result=" + result + ", strategy=" + strategy
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
