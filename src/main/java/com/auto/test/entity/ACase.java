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
import com.auto.test.utils.DateUtil;

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
	
	@Column(name="validate")
	private String validate;

	@Column(name="ready")
	private Integer ready;
	
	@Column(name="once")
	private String once;
	
	@Column(name="img")
	private String img;
	
	@Column(name="flag")
	private Integer flag;

	@Column(name="run")
	private Integer run;
	
	@Column(name="login")
	private Integer login;
	
	@Column(name="batch")
	private String batch;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	@Transient
	List<ACase> list = new LinkedList<ACase>();

	public ACase() {
		super();
	}

	public ACase(Integer id) {
		super();
		this.id = id;
	}
	
	public ACase(AVersion versiono, AInterface interfaceo, String name, String body, String result, String strategy, String validate, Integer ready, String link, String img, String once, Integer flag, Integer run, Integer login) {
		super();
		this.versiono = versiono;
		this.interfaceo = interfaceo;
		this.name = name;
		this.body = body;
		this.result = result;
		this.strategy = strategy;
		this.validate = validate;
		this.ready = ready;
		this.img = img;
		this.once = once;
		this.flag = flag;
		this.run = run;
		this.login = login;
		this.link = link;
	}
	public ACase(Integer id, AVersion versiono, AInterface interfaceo, String name, String body, String result, String strategy, String validate, Integer ready, String link, String img, String once, Integer flag, Integer run, Integer login) {
		super();
		this.id = id;
		this.versiono = versiono;
		this.interfaceo = interfaceo;
		this.name = name;
		this.body = body;
		this.result = result;
		this.strategy = strategy;
		this.validate = validate;
		this.ready = ready;
		this.img = img;
		this.once = once;
		this.flag = flag;
		this.run = run;
		this.login = login;
		this.link = link;
	}
	
	public void update(ACase aCase) {
		update(aCase, true, true);
	}
	
	public void update(ACase aCase, boolean boolImg, boolean boolLink) {
		this.interfaceo = aCase.getInterfaceo();
		this.versiono = aCase.getVersiono();
		this.name = aCase.getName();
		this.body = aCase.getBody();
		this.result = aCase.getResult();
		this.strategy = aCase.getStrategy();
		this.validate = aCase.getValidate();
		this.ready = aCase.getReady();
		if(boolImg){
			this.img = aCase.getImg();
		}
		this.flag = aCase.getFlag();
		this.login = aCase.getLogin();
		if(boolLink){
			this.run = aCase.getRun();
			this.link = aCase.getLink();
		}
		if("0".equals(aCase.getOnce())){
			if(this.once == null || !this.once.startsWith(DateUtil.getFormatDate())){
				this.once = aCase.getOnce();
			}
		}else{
			this.once = aCase.getOnce();
		}
		this.batch = aCase.getBatch();
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
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public Integer getReady() {
		return ready;
	}
	public void setReady(Integer ready) {
		this.ready = ready;
	}
	public String getOnce() {
		return once;
	}
	public void setOnce(String once) {
		this.once = once;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getRun() {
		return run;
	}
	public void setRun(Integer run) {
		this.run = run;
	}
	public Integer getLogin() {
		return login;
	}
	public void setLogin(Integer login) {
		this.login = login;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
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
	public List<ACase> getList() {
		return list;
	}
	public void setList(List<ACase> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ACase [id=" + id + ", interfaceo=" + interfaceo + ", versiono=" + versiono + ", name=" + name
				+ ", link=" + link + ", body=" + body + ", result=" + result + ", strategy=" + strategy + ", validate="
				+ validate + ", ready=" + ready + ", once=" + once + ", img=" + img + ", flag=" + flag + ", run=" + run
				+ ", login=" + login + ", batch=" + batch + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + ", list=" + list + "]";
	}
}
