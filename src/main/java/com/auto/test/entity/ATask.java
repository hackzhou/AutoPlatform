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
@Table(name="a_task")
public class ATask implements Serializable{
	private static final long serialVersionUID = 6157173418418202402L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AProject projecto;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="version_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AVersion versiono;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="account_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AAccount accounto;
	
	@Column(name="run_flag")
	private Integer runFlag;

	@Column(name="monitor")
	private Integer monitor;
	
	@Column(name="mail")
	private Integer mail;
	
	@Column(name="email")
	private String email;
	
	@Column(name="run_time")
	private String runTime;
	
	@Column(name="createby")
	private String createby;

	@Column(name="runby")
	private String runby;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public ATask() {
		super();
	}
	
	public ATask(String pid, String vid, String aid, Integer runFlag, Integer monitor, String runTime, String createby, Integer mail, String email) {
		super();
		if(pid != null && !pid.isEmpty()){
			this.projecto = new AProject(Integer.parseInt(pid));
		}
		if(vid != null && !vid.isEmpty()){
			this.versiono = new AVersion(Integer.parseInt(vid));
		}
		if(aid != null && !aid.isEmpty() && !"0".equals(aid)){
			this.accounto = new AAccount(Integer.parseInt(aid));
		}
		this.runFlag = runFlag;
		this.monitor = monitor;
		this.runTime = runTime;
		this.createby = createby;
		this.runby = createby;
		this.mail = mail;
		this.email = email;
	}
	public ATask(Integer id, String pid, String vid, String aid, Integer runFlag, Integer monitor, String runTime, String runby, Integer mail, String email) {
		super();
		this.id = id;
		if(pid != null && !pid.isEmpty()){
			this.projecto = new AProject(Integer.parseInt(pid));
		}
		if(vid != null && !vid.isEmpty()){
			this.versiono = new AVersion(Integer.parseInt(vid));
		}
		if(aid != null && !aid.isEmpty()){
			this.accounto = new AAccount(Integer.parseInt(aid));
		}
		this.runFlag = runFlag;
		this.monitor = monitor;
		this.runTime = runTime;
		this.runby = runby;
		this.mail = mail;
		this.email = email;
	}

	public void update(ATask aTask) {
		this.projecto = aTask.getProjecto();
		this.versiono = aTask.getVersiono();
		this.accounto = aTask.getAccounto();
		this.runFlag = aTask.getRunFlag();
		this.monitor = aTask.getMonitor();
		this.mail = aTask.getMail();
		this.email = aTask.getEmail();
		this.runTime = aTask.getRunTime();
		this.runby = aTask.getRunby();
		this.updateTime = new Date();
		this.memo = aTask.getMemo();
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
	public AVersion getVersiono() {
		return versiono;
	}
	public void setVersiono(AVersion versiono) {
		this.versiono = versiono;
	}
	public AAccount getAccounto() {
		return accounto;
	}
	public void setAccounto(AAccount accounto) {
		this.accounto = accounto;
	}
	public Integer getRunFlag() {
		return runFlag;
	}
	public void setRunFlag(Integer runFlag) {
		this.runFlag = runFlag;
	}
	public Integer getMonitor() {
		return monitor;
	}
	public void setMonitor(Integer monitor) {
		this.monitor = monitor;
	}
	public Integer getMail() {
		return mail;
	}
	public void setMail(Integer mail) {
		this.mail = mail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
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
		return "ATask [id=" + id + ", projecto=" + projecto + ", versiono=" + versiono + ", accounto=" + accounto
				+ ", runFlag=" + runFlag + ", monitor=" + monitor + ", mail=" + mail + ", email=" + email + ", runTime="
				+ runTime + ", createby=" + createby + ", runby=" + runby + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
}
