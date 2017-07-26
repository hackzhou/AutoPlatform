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
@Table(name="u_result_case")
public class UResultCase implements Serializable{
	private static final long serialVersionUID = 3871351944205123579L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="suite_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private UResultSuite suiteo;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="code_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private UCode codeo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="success")
	private Integer success;
	
	@Column(name="fail")
	private Integer fail;
	
	@Column(name="ignore")
	private Integer ignore;
	
	@Column(name="status")
	private String status;
	
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
	
	public UResultCase() {
		super();
	}
	public UResultCase(Integer id) {
		super();
		this.id = id;
	}

	public void update(UResultCase uResultCase){
		this.suiteo = uResultCase.getSuiteo();
		this.codeo = uResultCase.getCodeo();
		this.name = uResultCase.getName();
		this.total = uResultCase.getTotal();
		this.success = uResultCase.getSuccess();
		this.fail = uResultCase.getFail();
		this.ignore = uResultCase.getIgnore();
		this.status = uResultCase.getStatus();
		this.startTime = uResultCase.getStartTime();
		this.endTime = uResultCase.getEndTime();
		this.updateTime = new Date();
		this.memo = uResultCase.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UResultSuite getSuiteo() {
		return suiteo;
	}
	public void setSuiteo(UResultSuite suiteo) {
		this.suiteo = suiteo;
	}
	public UCode getCodeo() {
		return codeo;
	}
	public void setCodeo(UCode codeo) {
		this.codeo = codeo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "UResultCase [id=" + id + ", suiteo=" + suiteo + ", codeo=" + codeo + ", name=" + name + ", total="
				+ total + ", success=" + success + ", fail=" + fail + ", ignore=" + ignore + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", memo=" + memo + "]";
	}

}
