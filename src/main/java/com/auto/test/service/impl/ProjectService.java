package com.auto.test.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.service.impl.BaseService;
import com.auto.test.dao.IProjectDao;
import com.auto.test.service.IProjectService;

public class ProjectService extends BaseService implements IProjectService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IProjectDao ProjectDao;
}
