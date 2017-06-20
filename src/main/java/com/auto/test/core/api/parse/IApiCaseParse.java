package com.auto.test.core.api.parse;

import org.slf4j.Logger;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.exception.BusinessException;

public interface IApiCaseParse {
	
	void execute(ApiContext apiContext);
	
	BusinessException throwException(Logger logger, String message);

}
