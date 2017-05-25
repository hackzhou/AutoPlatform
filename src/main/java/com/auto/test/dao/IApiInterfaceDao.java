package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AInterface;

public interface IApiInterfaceDao extends IBaseDao<AInterface> {
	
	List<AInterface> findByProjectId(Integer id);

}
