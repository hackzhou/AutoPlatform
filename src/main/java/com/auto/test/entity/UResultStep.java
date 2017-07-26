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
@Table(name="u_result_step")
public class UResultStep implements Serializable{
	private static final long serialVersionUID = 7262498706279160416L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="case_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private UResultCase caseo;
	
	@Column(name="sort")
	private Integer sort;

	@Column(name="step")
	private String step;
	
	@Column(name="status")
	private String status;

	@Column(name="failed_msg")
	private String failedMsg;
	
	@Column(name="failed_img")
	private String failedImg;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public UResultStep() {
		super();
	}
	public UResultStep(Integer id) {
		super();
		this.id = id;
	}

	public void update(UResultStep uResultStep){
		this.caseo = uResultStep.getCaseo();
		this.sort = uResultStep.getSort();
		this.step = uResultStep.getStep();
		this.status = uResultStep.getStatus();
		this.failedMsg = uResultStep.getFailedMsg();
		this.failedImg = uResultStep.getFailedImg();
		this.updateTime = new Date();
		this.memo = uResultStep.getMemo();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UResultCase getCaseo() {
		return caseo;
	}
	public void setCaseo(UResultCase caseo) {
		this.caseo = caseo;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFailedMsg() {
		return failedMsg;
	}
	public void setFailedMsg(String failedMsg) {
		this.failedMsg = failedMsg;
	}
	public String getFailedImg() {
		return failedImg;
	}
	public void setFailedImg(String failedImg) {
		this.failedImg = failedImg;
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
		return "UResultStep [id=" + id + ", caseo=" + caseo + ", sort=" + sort + ", step=" + step + ", status=" + status
				+ ", failedMsg=" + failedMsg + ", failedImg=" + failedImg + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
