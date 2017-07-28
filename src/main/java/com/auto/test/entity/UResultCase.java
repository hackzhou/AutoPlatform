package com.auto.test.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
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
	
	@Column(name="platform_name")
	private String platformName;
	
	@Column(name="platform_version")
	private String platformVersion;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="udid")
	private String udid;
	
	@Column(name="total_count")
	private Integer totalCount;
	
	@Column(name="success_count")
	private Integer successCount;
	
	@Column(name="fail_count")
	private Integer failCount;
	
	@Column(name="ignore_count")
	private Integer ignoreCount;
	
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
	
	@Transient
	private List<UResultStep> steps = new LinkedList<UResultStep>();
	
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
		this.platformName = uResultCase.getPlatformName();
		this.platformVersion = uResultCase.getPlatformVersion();
		this.deviceName = uResultCase.getDeviceName();
		this.udid = uResultCase.getUdid();
		this.totalCount = uResultCase.getTotalCount();
		this.successCount = uResultCase.getSuccessCount();
		this.failCount = uResultCase.getFailCount();
		this.ignoreCount = uResultCase.getIgnoreCount();
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
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
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
	public List<UResultStep> getSteps() {
		return steps;
	}
	public void setSteps(List<UResultStep> steps) {
		this.steps = steps;
	}
	public void addStep(UResultStep uResultStep) {
		this.steps.add(uResultStep);
	}
	
	@Override
	public String toString() {
		return "UResultCase [id=" + id + ", suiteo=" + suiteo + ", codeo=" + codeo + ", name=" + name
				+ ", platformName=" + platformName + ", platformVersion=" + platformVersion + ", deviceName="
				+ deviceName + ", udid=" + udid + ", totalCount=" + totalCount + ", successCount=" + successCount
				+ ", failCount=" + failCount + ", ignoreCount=" + ignoreCount + ", status=" + status + ", startTime="
				+ startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + ", steps=" + steps + "]";
	}
	
}
