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
@Table(name="a_case")
public class ACase implements Serializable{
	private static final long serialVersionUID = -372190487810560745L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="interface_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AInterface interfaceo;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="version_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AVersion versiono;
	
	@Column(name="name")
	private String name;
	
	@Column(name="link")
	private String link;

	@Column(name="body")
	private String body;

	@Column(name="result")
	private String result;
	
	@Column(name="strategy")
	private String strategy;
	
	@Column(name="run")
	private Integer run;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;

	public ACase() {
		super();
	}
	
	public ACase(AVersion versiono, AInterface interfaceo, String name, String body, String result, String strategy, String link, Integer run) {
		super();
		this.versiono = versiono;
		this.interfaceo = interfaceo;
		this.name = name;
		this.body = body;
		this.result = result;
		this.strategy = strategy;
		this.run = run;
		this.link = link;
	}
	public ACase(Integer id, AVersion versiono, AInterface interfaceo, String name, String body, String result, String strategy, String link, Integer run) {
		super();
		this.id = id;
		this.versiono = versiono;
		this.interfaceo = interfaceo;
		this.name = name;
		this.body = body;
		this.result = result;
		this.strategy = strategy;
		this.run = run;
		this.link = link;
	}
	
	public void update(ACase aCase) {
		this.interfaceo = aCase.getInterfaceo();
		this.versiono = aCase.getVersiono();
		this.name = aCase.getName();
		this.body = aCase.getBody();
		this.result = aCase.getResult();
		this.strategy = aCase.getStrategy();
		this.run = aCase.getRun();
		this.link = aCase.getLink();
		this.updateTime = new Date();
		this.memo = aCase.getMemo();
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
	public AInterface getInterfaceo() {
		return interfaceo;
	}
	public void setInterfaceo(AInterface interfaceo) {
		this.interfaceo = interfaceo;
	}
	public AVersion getVersiono() {
		return versiono;
	}
	public void setVersiono(AVersion versiono) {
		this.versiono = versiono;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public Integer getRun() {
		return run;
	}
	public void setRun(Integer run) {
		this.run = run;
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
		return "ACase [id=" + id + ", interfaceo=" + interfaceo + ", versiono=" + versiono + ", name=" + name
				+ ", link=" + link + ", body=" + body + ", result=" + result + ", strategy=" + strategy + ", run=" + run
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", memo=" + memo + "]";
	}

}
