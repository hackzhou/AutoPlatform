package com.auto.test.dao;

import java.util.List;

import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.ACase;

public interface IApiCaseDao extends IBaseDao<ACase> {
	
	List<ACase> findByInterfaceId(Integer id);
	
	List<ACase> findByProjectVersion(Integer pid, Integer vid);

}
