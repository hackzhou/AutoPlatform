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
	private AProject project;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;

	@Column(name="url")
	private String url;
	
	@Column(name="param")
	private String param;

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
		this.project = aCase.getProject();
		this.name = aCase.getName();
		this.type = aCase.getType();
		this.url = aCase.getUrl();
		this.param = aCase.getParam();
		this.updateTime = new Date();
		this.memo = aCase.getMemo();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AProject getProject() {
		return project;
	}
	public void setProject(AProject project) {
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
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
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
		return "ACase [id=" + id + ", project=" + project + ", name=" + name + ", type=" + type + ", url=" + url
				+ ", param=" + param + ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo
				+ "]";
	}
	
}
