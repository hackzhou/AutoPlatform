package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AVersion;

public interface IApiVersionDao extends IBaseDao<AVersion> {

	List<AVersion> findByProject(Integer pid);

	public List<AVersion> findByVersionProject(String version, Integer pid);
	
}
