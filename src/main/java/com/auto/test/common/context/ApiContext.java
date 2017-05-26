package com.auto.test.common.context;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiContext {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiContext.class);

	private int index = 1;
	private Object obj = new Object();
	
	private boolean concurrent = true;
	private Map<String, String> runInfo = new HashMap<String, String>();
	
	public ApiContext() {
		super();
	}
	
	public void waitForExecute(int exeIndex) {
		synchronized (obj) {
			while (index != exeIndex) {
				try {
					obj.wait(60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void notifyExecute(int exeIndex) {
		synchronized (obj) {
			this.index = exeIndex;
			obj.notifyAll();
		}
	}

	public boolean isConcurrent() {
		return concurrent;
	}
	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
	}
	public Map<String, String> getRunInfo() {
		return runInfo;
	}
	public void setRunInfo(Map<String, String> runInfo) {
		this.runInfo = runInfo;
	}
	
}
