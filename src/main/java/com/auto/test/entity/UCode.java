package com.auto.test.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="u_code")
public class UCode implements Serializable{
	private static final long serialVersionUID = -7200655685237297961L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="device_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private UDevice deviceo;
	
	@Column(name="path")
	private String path;
	
	@Column(name="cls")
	private String cls;
	
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
	public UCode(Integer id, UDevice deviceo, String path, String cls, String createBy) {
		super();
		this.id = id;
		this.deviceo = deviceo;
		this.path = path;
		this.cls = cls;
		this.createBy = createBy;
	}
	public UCode(UDevice deviceo, String path, String cls, String createBy) {
		super();
		this.deviceo = deviceo;
		this.path = path;
		this.cls = cls;
		this.createBy = createBy;
	}
	
	public void update(UCode uCode) {
		this.path = uCode.getPath();
		this.deviceo = uCode.getDeviceo();
		this.cls = uCode.getCls();
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
	public UDevice getDeviceo() {
		return deviceo;
	}
	public void setDeviceo(UDevice deviceo) {
		this.deviceo = deviceo;
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
		return "UCode [id=" + id + ", deviceo=" + deviceo + ", path=" + path + ", cls=" + cls + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
	
}
