package com.auto.test.dao;

import java.util.Date;
import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AResult;

public interface IApiResultDao extends IBaseDao<AResult> {
	
	public List<AResult> findByProjectVersionTime(Integer pid, Integer vid, Date startTime, Date endTime);
	
}
