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
@Table(name="a_account")
public class AAccount implements Serializable{
	private static final long serialVersionUID = -5311622782461537226L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="loginname")
	private String loginname;
	
	@Column(name="password")
	private String password;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AAccount() {
		super();
	}
	
	public AAccount(String loginname, String password) {
		super();
		this.loginname = loginname;
		this.password = password;
	}
	public AAccount(Integer id, String loginname, String password) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
	}

	public void updateAAccount(AAccount aAccount) {
		this.loginname = aAccount.getLoginname();
		this.password = aAccount.getPassword();
		this.updateTime = new Date();
		this.memo = aAccount.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return "AAccount [id=" + id + ", loginname=" + loginname + ", password=" + password + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
