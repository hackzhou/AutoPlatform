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
@Table(name="a_version")
public class AVersion implements Serializable{
	private static final long serialVersionUID = -3957590496068903091L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AProject projecto;
	
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
	public AVersion(Integer projectId, String version, String channel) {
		super();
		this.projecto = new AProject(projectId);
		this.version = version;
		this.channel = channel;
	}
	public AVersion(Integer id, Integer projectId, String version, String channel) {
		super();
		this.id = id;
		this.projecto = new AProject(projectId);
		this.version = version;
		this.channel = channel;
	}

	public void update(AVersion aVersion){
		this.projecto = aVersion.getProjecto();
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
	public AProject getProjecto() {
		return projecto;
	}
	public void setProjecto(AProject projecto) {
		this.projecto = projecto;
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
		return "AVersion [id=" + id + ", projecto=" + projecto + ", version=" + version + ", channel=" + channel
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
}
