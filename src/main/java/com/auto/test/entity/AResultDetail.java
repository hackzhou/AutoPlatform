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
@Table(name="a_result_detail")
public class AResultDetail implements Serializable{
	private static final long serialVersionUID = 7596016181206871659L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="result_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AResult resulto;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="case_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private ACase caseo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;

	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;
	
	@Column(name="version")
	private String version;

	@Column(name="channel")
	private String channel;

	@Column(name="account")
	private String account;
	
	@Column(name="strategy")
	private String strategy;

	@Column(name="body")
	private String body;
	
	@Column(name="resulta")
	private String resulta;

	@Column(name="resultb")
	private String resultb;
	
	@Column(name="status")
	private String status;
	
	@Column(name="time")
	private Integer time;
	
	@Column(name="msg")
	private String msg;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;

	public AResultDetail() {
		super();
	}

	public void update(AResultDetail aResultDetail){
		this.resulto = aResultDetail.getResulto();
		this.caseo = aResultDetail.getCaseo();
		this.name = aResultDetail.getName();
		this.type = aResultDetail.getType();
		this.url = aResultDetail.getUrl();
		this.description = aResultDetail.getDescription();
		this.version = aResultDetail.getVersion();
		this.channel = aResultDetail.getChannel();
		this.account = aResultDetail.getAccount();
		this.strategy = aResultDetail.getStrategy();
		this.body = aResultDetail.getBody();
		this.resulta = aResultDetail.getResulta();
		this.resultb = aResultDetail.getResultb();
		this.status = aResultDetail.getStatus();
		this.time = aResultDetail.getTime();
		this.msg = aResultDetail.getMsg();
		this.updateTime = new Date();
		this.memo = aResultDetail.getMemo();
	}
	
	public void update(ACase aCase){
		this.caseo = aCase;
		this.type = aCase.getInterfaceo().getType();
		this.url = aCase.getInterfaceo().getUrl();
		this.description = aCase.getInterfaceo().getDescription();
		this.name = aCase.getName();
		this.body = aCase.getBody();
		this.strategy = aCase.getStrategy();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AResult getResulto() {
		return resulto;
	}
	public void setResulto(AResult resulto) {
		this.resulto = resulto;
	}
	public ACase getCaseo() {
		return caseo;
	}
	public void setCaseo(ACase caseo) {
		this.caseo = caseo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getResulta() {
		return resulta;
	}
	public void setResulta(String resulta) {
		this.resulta = resulta;
	}
	public String getResultb() {
		return resultb;
	}
	public void setResultb(String resultb) {
		this.resultb = resultb;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
		return "AResultDetail [id=" + id + ", resulto=" + resulto + ", caseo=" + caseo + ", name=" + name + ", type="
				+ type + ", url=" + url + ", description=" + description + ", version=" + version + ", channel="
				+ channel + ", account=" + account + ", strategy=" + strategy + ", body=" + body + ", resulta="
				+ resulta + ", resultb=" + resultb + ", status=" + status + ", time=" + time + ", msg=" + msg
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}
	
	public String toLogString() {
		return "AResultDetail [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", description="
				+ description + ", version=" + version + ", channel=" + channel + ", account=" + account + ", strategy="
				+ strategy + ", body=" + body + ", resulta=" + resulta + ", resultb=" + resultb + ", status=" + status
				+ ", time=" + time + ", msg=" + msg + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + "]";
	}

}
