package com.auto.test.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.entity.AAccount;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AVersion;

public class ApiContext {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiContext.class);

	private Integer count;
	private Integer total;
	private String urla;
	private String urlb;
	private String authora;
	private String authorb;
	private AResult result;
	private AProject aProject;
	private AVersion aVersion;
	private AAccount aAccount;
	private List<ACase> list = new ArrayList<ACase>();
	private Map<String, String> runInfo = new HashMap<String, String>();
	
	public ApiContext() {
		super();
		this.count = 0;
		this.urla = GlobalValueConfig.getConfig("uri.production.environment");
		this.urlb = GlobalValueConfig.getConfig("uri.advance.environment");
	}
	
	public Integer getCount() {
		return count;
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
	public String getUrla() {
		return urla;
	}
	public void setUrla(String urla) {
		this.urla = urla;
	}
	public String getUrlb() {
		return urlb;
	}
	public void setUrlb(String urlb) {
		this.urlb = urlb;
	}
	public AResult getResult() {
		return result;
	}
	public void setResult(AResult result) {
		this.result = result;
	}
	public AProject getaProject() {
		return aProject;
	}
	public void setaProject(AProject aProject) {
		this.aProject = aProject;
	}
	public AVersion getaVersion() {
		return aVersion;
	}
	public void setaVersion(AVersion aVersion) {
		this.aVersion = aVersion;
	}
	public AAccount getaAccount() {
		return aAccount;
	}
	public void setaAccount(AAccount aAccount) {
		this.aAccount = aAccount;
	}
	public String getAuthora() {
		return authora;
	}
	public void setAuthora(String authora) {
		this.authora = authora;
	}
	public String getAuthorb() {
		return authorb;
	}
	public void setAuthorb(String authorb) {
		this.authorb = authorb;
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
}
