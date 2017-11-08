package com.auto.test.dao;

import java.util.Date;
import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AInterface;

public interface IApiInterfaceDao extends IBaseDao<AInterface> {
	
	List<AInterface> findByProjectId(Integer id);

	List<AInterface> findByUrl(String url);
	
	List<AInterface> findByProjectUrl(Integer id, String url);

	List<AInterface> findByNotBacthTime(Date batchTime);
	
}
