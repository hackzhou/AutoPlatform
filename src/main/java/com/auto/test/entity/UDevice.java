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
@Table(name="u_device")
public class UDevice implements Serializable{
	private static final long serialVersionUID = -6263899059721726402L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="platform_name")
	private String platformName;
	
	@Column(name="platform_version")
	private String platformVersion;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="udid")
	private String udid;
	
	@Column(name="server_url")
	private String serverUrl;
	
	@Column(name="app")
	private String app;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public UDevice() {
		super();
	}
	public UDevice(Integer id) {
		super();
		this.id = id;
	}
	public UDevice(Integer id, String platformName, String platformVersion, String nickname, String deviceName, String udid,
			String serverUrl, String app) {
		super();
		this.id = id;
		this.platformName = platformName;
		this.platformVersion = platformVersion;
		this.nickname = nickname;
		this.deviceName = deviceName;
		this.udid = udid;
		this.serverUrl = serverUrl;
		this.app = app;
	}
	public UDevice(String platformName, String platformVersion, String nickname, String deviceName, String udid,
			String serverUrl, String app) {
		super();
		this.platformName = platformName;
		this.platformVersion = platformVersion;
		this.nickname = nickname;
		this.deviceName = deviceName;
		this.udid = udid;
		this.serverUrl = serverUrl;
		this.app = app;
	}
	
	public void update(UDevice uDevice) {
		this.platformName = uDevice.getPlatformName();
		this.platformVersion = uDevice.getPlatformVersion();
		this.nickname = uDevice.getNickname();
		this.deviceName = uDevice.getDeviceName();
		this.udid = uDevice.getUdid();
		this.serverUrl = uDevice.getServerUrl();
		this.app = uDevice.getApp();
		this.updateTime = new Date();
		this.memo = uDevice.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
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
		return "UDevice [id=" + id + ", platformName=" + platformName + ", platformVersion=" + platformVersion
				+ ", nickname=" + nickname + ", deviceName=" + deviceName + ", udid=" + udid + ", serverUrl="
				+ serverUrl + ", app=" + app + ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo="
				+ memo + "]";
	}
	
}
