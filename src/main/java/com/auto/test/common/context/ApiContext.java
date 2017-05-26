package com.auto.test.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResult;

public class ApiContext {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiContext.class);

	private AResult result;
	private List<ACase> list = new ArrayList<ACase>();
	private Map<String, String> runInfo = new HashMap<String, String>();
	
	public ApiContext() {
		super();
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
}
