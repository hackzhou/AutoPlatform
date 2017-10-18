package com.auto.test.core.api.service;

import com.auto.test.common.constant.ApiRunType;

public interface IApiRunService{
	
	void run(ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby, boolean mail) throws Exception;

}
