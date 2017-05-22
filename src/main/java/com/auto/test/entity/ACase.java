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
	
	@Column(name="version_id")
	private AVersion versiono;
	
	@Column(name="project_id")
	private AInterface interfaceo;
	
	@Column(name="name")
	private String name;

	@Column(name="body")
	private String body;
	
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
	
	public void update(ACase aCase) {
		this.interfaceo = aCase.getInterfaceo();
		this.name = aCase.getName();
		this.versiono = aCase.getVersiono();
		this.body = aCase.getBody();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AVersion getVersiono() {
		return versiono;
	}
	public void setVersiono(AVersion versiono) {
		this.versiono = versiono;
	}
	public AInterface getInterfaceo() {
		return interfaceo;
	}
	public void setInterfaceo(AInterface interfaceo) {
		this.interfaceo = interfaceo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
		return "ACase [id=" + id + ", versiono=" + versiono + ", interfaceo=" + interfaceo + ", name=" + name
				+ ", body=" + body + ", strategy=" + strategy + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", memo=" + memo + "]";
	}

}
