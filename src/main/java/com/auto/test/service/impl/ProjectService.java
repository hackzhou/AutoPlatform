package com.auto.test.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.service.impl.BaseService;
import com.auto.test.dao.IProjectDao;
import com.auto.test.entity.TProject;
import com.auto.test.service.IProjectService;

public class ProjectService extends BaseService implements IProjectService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IProjectDao projectDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TProject> getAllProject() {
		return (List<TProject>) projectDao.find("from TProject order by createTime desc");
	}
}
