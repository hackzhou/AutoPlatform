package com.auto.test.core.ui.code.result;

import java.util.Date;
import com.auto.test.common.context.SpringContext;
import com.auto.test.entity.UResultSuite;
import com.auto.test.service.IUiResultSuiteService;

public class SuiteResult {
	
	public UResultSuite initResultSuite(String cls){
		IUiResultSuiteService suiteService = (IUiResultSuiteService) SpringContext.getBean("uiResultSuiteService");
		UResultSuite suite = new UResultSuite();
		suite.setName(cls);
		suite.setTotalCount(0);
		suite.setSuccessCount(0);
		suite.setFailCount(0);
		suite.setIgnoreCount(0);
		suite.setStartTime(new Date());
		suiteService.create(suite);
		return suite;
	}

}
