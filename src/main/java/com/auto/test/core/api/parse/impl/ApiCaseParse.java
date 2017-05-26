package com.auto.test.core.api.parse.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.ThreadPool;
import com.auto.test.core.api.execute.ApiExecuteRun;
import com.auto.test.core.api.parse.IApiCaseParse;
import com.auto.test.entity.ACase;

public class ApiCaseParse implements IApiCaseParse {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ApiCaseParse.class);
	private ExecutorService cachedThreadPool = null;
	
	public ApiCaseParse(){
		cachedThreadPool = ThreadPool.getInstance();
	}

	@Override
	public void execute(ApiContext apiContext) {
		List<ACase> list = apiContext.getList();
		if(list != null && !list.isEmpty()){
			for (ACase aCase : list) {
				ApiExecuteRun apiExecuteRun = new ApiExecuteRun(aCase, apiContext);
				cachedThreadPool.execute(apiExecuteRun);
			}
		}
	}

}
