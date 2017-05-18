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
@Table(name="a_result_detail")
public class AResultDetail implements Serializable{
	private static final long serialVersionUID = 7596016181206871659L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="result_id")
	private AResult resulto;
	
	@Column(name="case_id")
	private ACase caseo;
	
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

	@Column(name="status")
	private String status;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;

	public AResultDetail() {
		super();
	}

	public void updateAResult(AResultDetail aResultDetail){
		this.resulto = aResultDetail.getResulto();
		this.caseo = aResultDetail.getCaseo();
		this.name = aResultDetail.getName();
		this.method = aResultDetail.getMethod();
		this.url = aResultDetail.getUrl();
		this.header = aResultDetail.getHeader();
		this.body = aResultDetail.getBody();
		this.result = aResultDetail.getResult();
		this.status = aResultDetail.getStatus();
		this.updateTime = new Date();
		this.memo = aResultDetail.getMemo();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AResult getResulto() {
		return resulto;
	}
	public void setResulto(AResult resulto) {
		this.resulto = resulto;
	}
	public ACase getCaseo() {
		return caseo;
	}
	public void setCaseo(ACase caseo) {
		this.caseo = caseo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "AResultDetail [id=" + id + ", resulto=" + resulto + ", caseo=" + caseo + ", name=" + name + ", method="
				+ method + ", url=" + url + ", header=" + header + ", body=" + body + ", result=" + result + ", status="
				+ status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
	
}
