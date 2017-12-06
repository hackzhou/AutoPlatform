package com.auto.test.core.api.service;

import java.util.List;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.entity.AAccount;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AVersion;

public interface IApiRunService{
	
	void rerun(ApiRunType type, Integer runId, List<ACase> list, AAccount account, AVersion version, String runby, Integer compare, Integer platform) throws Exception;
	
	void run(ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby, Integer compare, Integer platform, boolean mail, String emails) throws Exception;

}
