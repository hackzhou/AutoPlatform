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
@Table(name="u_code")
public class UCode implements Serializable{
	private static final long serialVersionUID = -7200655685237297961L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="devices")
	private String devices;
	
	@Column(name="path")
	private String path;
	
	@Column(name="cls")
	private String cls;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_by")
	private String createBy;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public UCode() {
		super();
	}
	public UCode(Integer id) {
		super();
		this.id = id;
	}
	public UCode(String cls, String description, String devices) {
		super();
		this.cls = cls;
		this.description = description;
		this.devices = devices;
	}
	public UCode(Integer id, String devices, String path, String cls, String description, String createBy) {
		super();
		this.id = id;
		this.devices = devices;
		this.path = path;
		this.cls = cls;
		this.description = description;
		this.createBy = createBy;
	}
	public UCode(String devices, String path, String cls, String description, String createBy) {
		super();
		this.devices = devices;
		this.path = path;
		this.cls = cls;
		this.description = description;
		this.createBy = createBy;
	}
	
	public void update(UCode uCode) {
		this.path = uCode.getPath();
		this.devices = uCode.getDevices();
		this.cls = uCode.getCls();
		this.description = uCode.getDescription();
		this.createBy = uCode.getCreateBy();
		this.updateTime = new Date();
		this.memo = uCode.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDevices() {
		return devices;
	}
	public void setDevices(String devices) {
		this.devices = devices;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		return "UCode [id=" + id + ", devices=" + devices + ", path=" + path + ", cls=" + cls + ", description="
				+ description + ", createBy=" + createBy + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + "]";
	}
	
}
