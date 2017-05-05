package com.auto.test.service;

import java.util.List;
import com.auto.test.common.service.IBaseService;
import com.auto.test.entity.TProject;

public interface IProjectService extends IBaseService{
	
	List<TProject> getAllProject();

}
