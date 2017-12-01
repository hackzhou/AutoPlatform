package com.auto.test.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.auto.test.entity.AAccount;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AVersion;

public class ApiContext {
	private boolean compare;
	private Integer platform;
	private boolean mail;
	private String emails;
	private Integer count;
	private Integer total;
	
	private AProject project;
	private AVersion version;
	private AAccount account;
	private AResult result;
	
	private List<ACase> list = new ArrayList<ACase>();
	private Map<String, String> runInfo = new HashMap<String, String>();

	private int dbUser;
	public ApiContext() {
		super();
		this.count = 0;
		this.dbUser = 0;
	}

	public synchronized void countPlus(){
		this.count++;
	}
	public synchronized void successPlus(){
		this.result.setSuccess(this.result.getSuccess() + 1);
	}
	public boolean isCompare() {
		return compare;
	}
	public void setCompare(boolean compare) {
		this.compare = compare;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Integer getCount() {
		return count;
	}
	public boolean isMail() {
		return mail;
	}
	public void setMail(boolean mail) {
		this.mail = mail;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public AProject getProject() {
		return project;
	}
	public void setProject(AProject project) {
		this.project = project;
	}
	public AVersion getVersion() {
		return version;
	}
	public void setVersion(AVersion version) {
		this.version = version;
	}
	public AAccount getAccount() {
		return account;
	}
	public void setAccount(AAccount account) {
		this.account = account;
	}
	public AResult getResult() {
		return result;
	}
	public void setResult(AResult result) {
		this.result = result;
	}
	public List<ACase> getList() {
		return list;
	}
	public void setList(List<ACase> list) {
		this.list = list;
	}
	public Map<String, String> getRunInfo() {
		return runInfo;
	}
	public void setRunInfo(Map<String, String> runInfo) {
		this.runInfo = runInfo;
	}
	public int getDbUser() {
		return dbUser;
	}
	public void setDbUser(int dbUser) {
		this.dbUser = dbUser;
	}
}
