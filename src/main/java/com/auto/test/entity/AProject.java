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
@Table(name="a_project")
public class AProject implements Serializable{
	private static final long serialVersionUID = -6328575654769126042L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="server")
	private String server;
	
	@Column(name="path")
	private String path;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AProject() {
		super();
	}
	
	public AProject(Integer id) {
		super();
		this.id = id;
	}
	public AProject(String name, String server, String path) {
		super();
		this.name = name;
		this.server = server;
		this.path = path;
	}
	public AProject(Integer id, String name, String server, String path) {
		super();
		this.id = id;
		this.name = name;
		this.server = server;
		this.path = path;
	}
	
	public void update(AProject aProject) {
		this.server = aProject.getServer();
		this.name = aProject.getName();
		this.path = aProject.getPath();
		this.updateTime = new Date();
		this.memo = aProject.getMemo();
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
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
		return "AProject [id=" + id + ", name=" + name + ", server=" + server + ", path=" + path + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
}
