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
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AProject() {
		super();
	}
	
	public AProject(String name) {
		super();
		this.name = name;
	}
	public AProject(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public void updateAProject(AProject aProject) {
		this.name = aProject.getName();
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
		return "AProject [id=" + id + ", name=" + name + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + "]";
	}
	
}
