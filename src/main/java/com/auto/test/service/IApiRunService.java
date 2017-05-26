package com.auto.test.service;

import javax.transaction.Transactional;
import com.auto.test.common.constant.ApiRunType;

@Transactional
public interface IApiRunService{
	
	void run(ApiRunType type, Integer runId, Integer accountId);

}
