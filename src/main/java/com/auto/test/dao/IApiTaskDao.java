package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.ATask;

public interface IApiTaskDao extends IBaseDao<ATask> {
	
	List<ATask> findByProject(Integer pid);

	List<ATask> findByVersion(Integer vid);
	
	List<ATask> findByAccount(Integer aid);
	
	List<ATask> findByTime(String time);
	
}
