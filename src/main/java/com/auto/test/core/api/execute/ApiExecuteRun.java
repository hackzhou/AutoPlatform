package com.auto.test.core.api.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auto.test.common.context.ApiContext;
import com.auto.test.entity.ACase;

public class ApiExecuteRun implements Runnable {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiExecuteRun.class);
	private ApiContext apiContext = null;
	private ACase aCase = null;

	public ApiExecuteRun(ACase aCase, ApiContext apiContext) {
		super();
		this.aCase = aCase;
		this.apiContext = apiContext;
	}

	@Override
	public void run() {
		System.out.println("zz:" + aCase.toString());
	}

	public ACase getaCase() {
		return aCase;
	}
	public void setaCase(ACase aCase) {
		this.aCase = aCase;
	}
	public ApiContext getApiContext() {
		return apiContext;
	}
	public void setApiContext(ApiContext apiContext) {
		this.apiContext = apiContext;
	}
}
