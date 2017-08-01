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
import com.auto.test.common.bean.AInterfaceCase;

@Entity
@Table(name="a_interface")
public class AInterface implements Serializable{
	private static final long serialVersionUID = 6112628873494753194L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AProject projecto;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;

	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="memo")
	private String memo;
	
	public AInterface() {
		super();
	}
	public AInterface(Integer id) {
		super();
		this.id = id;
	}
	public AInterface(Integer projectId, String name, String type, String url, String description) {
		super();
		this.projecto = new AProject(projectId);
		this.name = name;
		this.type = type;
		this.url = url;
		this.description = description;
	}
	public AInterface(Integer id, Integer projectId, String name, String type, String url, String description) {
		super();
		this.id = id;
		this.projecto = new AProject(projectId);
		this.name = name;
		this.type = type;
		this.url = url;
		this.description = description;
	}
	public AInterface(AInterfaceCase aInterfaceCase, Integer pid) {
		super();
		this.projecto = new AProject(pid);
		this.name = aInterfaceCase.getName();
		this.type = aInterfaceCase.getType();
		this.url = aInterfaceCase.getUrl();
		this.description = aInterfaceCase.getDescription();
	}

	public void update(AInterface aInterface) {
		this.projecto = aInterface.getProjecto();
		this.name = aInterface.getName();
		this.type = aInterface.getType();
		this.url = aInterface.getUrl();
		this.description = aInterface.getDescription();
		this.updateTime = new Date();
		this.memo = aInterface.getMemo();
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
		return "AInterface [id=" + id + ", projecto=" + projecto + ", name=" + name + ", type=" + type + ", url=" + url
				+ ", description=" + description + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", memo=" + memo + "]";
	}

}
