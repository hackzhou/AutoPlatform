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
@Table(name="a_version")
public class AVersion implements Serializable{
	private static final long serialVersionUID = -3957590496068903091L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="version")
	private String version;
	
	@Column(name="channel")
	private String channel;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AVersion() {
		super();
	}
	public AVersion(Integer id) {
		super();
		this.id = id;
	}
	public AVersion(String version, String channel) {
		super();
		this.version = version;
		this.channel = channel;
	}
	public AVersion(Integer id, String version, String channel) {
		super();
		this.id = id;
		this.version = version;
		this.channel = channel;
	}

	public void update(AVersion aVersion){
		this.version = aVersion.getVersion();
		this.channel = aVersion.getChannel();
		this.updateTime = new Date();
		this.memo = aVersion.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
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
		return "AVersion [id=" + id + ", version=" + version + ", channel=" + channel + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
