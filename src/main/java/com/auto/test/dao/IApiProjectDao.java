package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AProject;

public interface IApiProjectDao extends IBaseDao<AProject> {
	
	List<AProject> findByName(String name);

}
