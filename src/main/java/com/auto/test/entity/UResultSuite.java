package com.auto.test.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="u_result_suite")
public class UResultSuite implements Serializable{
	private static final long serialVersionUID = 2995474337489164786L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="total_count")
	private Integer totalCount;
	
	@Column(name="success_count")
	private Integer successCount;
	
	@Column(name="fail_count")
	private Integer failCount;
	
	@Column(name="ignore_count")
	private Integer ignoreCount;
	
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
	private List<UResultCase> cases = new LinkedList<UResultCase>();
	
	public UResultSuite() {
		super();
	}
	public UResultSuite(Integer id) {
		super();
		this.id = id;
	}

	public void update(UResultSuite uResultSuite){
		this.name = uResultSuite.getName();
		this.totalCount = uResultSuite.getTotalCount();
		this.successCount = uResultSuite.getSuccessCount();
		this.failCount = uResultSuite.getFailCount();
		this.ignoreCount = uResultSuite.getIgnoreCount();
		this.startTime = uResultSuite.getStartTime();
		this.endTime = uResultSuite.getEndTime();
		this.updateTime = new Date();
		this.memo = uResultSuite.getMemo();
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public Integer getIgnoreCount() {
		return ignoreCount;
	}
	public void setIgnoreCount(Integer ignoreCount) {
		this.ignoreCount = ignoreCount;
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
	public List<UResultCase> getCases() {
		return cases;
	}
	public void setCases(List<UResultCase> cases) {
		this.cases = cases;
	}
	public void addCase(UResultCase uResultCase) {
		this.cases.add(uResultCase);
	}
	
	@Override
	public String toString() {
		return "UResultSuite [id=" + id + ", name=" + name + ", totalCount=" + totalCount + ", successCount="
				+ successCount + ", failCount=" + failCount + ", ignoreCount=" + ignoreCount + ", startTime="
				+ startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + "]";
	}

}
