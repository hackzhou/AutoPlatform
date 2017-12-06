package com.auto.test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.auto.test.common.bean.AResultFail;

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
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="version_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AVersion versiono;
	
	@Column(name="name")
	private String name;
	
	@Column(name="runby")
	private String runby;
	
	@Column(name="success")
	private Integer success;
	
	@Column(name="fail")
	private Integer fail;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="status")
	private String status;
	
	@Column(name="msg")
	private String msg;
	
	@Column(name="compare")
	private Integer compare;
	
	@Column(name="platform")
	private Integer platform;
	
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
	
	@Transient
	private String failMsg;
	@Transient
	private List<AResultFail> fails = new ArrayList<AResultFail>();
	@Transient
	private List<AResultFail> timeouts = new ArrayList<AResultFail>();
	
	public AResult() {
		super();
	}
	public AResult(Integer id) {
		super();
		this.id = id;
	}

	public void update(AResult aResult){
		this.projecto = aResult.getProjecto();
		this.versiono = aResult.getVersiono();
		this.name = aResult.getName();
		this.runby = aResult.getRunby();
		this.success = aResult.getSuccess();
		this.fail = aResult.getFail();
		this.total = aResult.getTotal();
		this.status = aResult.getStatus();
		this.msg = aResult.getMsg();
		this.compare = aResult.getCompare();
		this.platform = aResult.getPlatform();
		this.startTime = aResult.getStartTime();
		this.endTime = aResult.getEndTime();
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
	public AVersion getVersiono() {
		return versiono;
	}
	public void setVersiono(AVersion versiono) {
		this.versiono = versiono;
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCompare() {
		return compare;
	}
	public void setCompare(Integer compare) {
		this.compare = compare;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
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
	public String getFailMsg() {
		return failMsg;
	}
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	public List<AResultFail> getFails() {
		return fails;
	}
	public void setFails(List<AResultFail> fails) {
		this.fails = fails;
	}
	public List<AResultFail> getTimeouts() {
		return timeouts;
	}
	public void setTimeouts(List<AResultFail> timeouts) {
		this.timeouts = timeouts;
	}
	
	@Override
	public String toString() {
		return "AResult [id=" + id + ", projecto=" + projecto + ", versiono=" + versiono + ", name=" + name + ", runby="
				+ runby + ", success=" + success + ", fail=" + fail + ", total=" + total + ", status=" + status
				+ ", msg=" + msg + ", compare=" + compare + ", platform=" + platform + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo="
				+ memo + ", failMsg=" + failMsg + ", fails=" + fails + ", timeouts=" + timeouts + "]";
	}
	
}
