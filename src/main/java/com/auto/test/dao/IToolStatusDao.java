package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.TStatus;

public interface IToolStatusDao extends IBaseDao<TStatus> {

	List<TStatus> findAll();
	
	List<TStatus> findByDept(String dept);
	
	List<TStatus> findByRootName(String root, String name);

	TStatus update(TStatus tStatus);
	
}
