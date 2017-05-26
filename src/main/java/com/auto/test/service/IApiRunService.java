package com.auto.test.service;

import com.auto.test.common.constant.ApiRunType;

public interface IApiRunService{
	
	void run(ApiRunType type, Integer runId, Integer accountId, String runby);

}
