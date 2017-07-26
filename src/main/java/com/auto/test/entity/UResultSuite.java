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
@Table(name="u_result_suite")
public class UResultSuite implements Serializable{
	private static final long serialVersionUID = 4479831202364582207L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="platform_name")
	private String platformName;
	
	@Column(name="platform_version")
	private String platformVersion;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="udid")
	private String udid;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="success")
	private Integer success;
	
	@Column(name="fail")
	private Integer fail;
	
	@Column(name="ignore")
	private Integer ignore;
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public UResultSuite() {
		super();
	}
	public UResultSuite(Integer id) {
		super();
		this.id = id;
	}

	public void update(UResultSuite uResultSuite){
		this.name = uResultSuite.getName();
		this.platformName = uResultSuite.getPlatformName();
		this.platformVersion = uResultSuite.getPlatformVersion();
		this.deviceName = uResultSuite.getDeviceName();
		this.udid = uResultSuite.getUdid();
		this.total = uResultSuite.getTotal();
		this.success = uResultSuite.getSuccess();
		this.fail = uResultSuite.getFail();
		this.ignore = uResultSuite.getIgnore();
		this.startTime = uResultSuite.getStartTime();
		this.endTime = uResultSuite.getEndTime();
		this.updateTime = new Date();
		this.memo = uResultSuite.getMemo();
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
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public Integer getFail() {
		return fail;
	}
	public void setFail(Integer fail) {
		this.fail = fail;
	}
	public Integer getIgnore() {
		return ignore;
	}
	public void setIgnore(Integer ignore) {
		this.ignore = ignore;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
		return "UResultSuite [id=" + id + ", name=" + name + ", platformName=" + platformName + ", platformVersion="
				+ platformVersion + ", deviceName=" + deviceName + ", udid=" + udid + ", total=" + total + ", success="
				+ success + ", fail=" + fail + ", ignore=" + ignore + ", startTime=" + startTime + ", endTime="
				+ endTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
