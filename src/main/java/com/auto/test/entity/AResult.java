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
@Table(name="a_result")
public class AResult implements Serializable{
	private static final long serialVersionUID = 3374046104695184313L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AProject projecto;
	
	@Column(name="name")
	private String name;
	
	@Column(name="runby")
	private String runby;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AResult() {
		super();
	}
	
	public void update(AResult aResult){
		this.projecto = aResult.getProjecto();
		this.name = aResult.getName();
		this.runby = aResult.getRunby();
		this.updateTime = new Date();
		this.memo = aResult.getMemo();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRunby() {
		return runby;
	}
	public void setRunby(String runby) {
		this.runby = runby;
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
		return "AResult [id=" + id + ", projecto=" + projecto + ", name=" + name + ", runby=" + runby + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
