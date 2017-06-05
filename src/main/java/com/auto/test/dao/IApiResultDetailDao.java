package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AResultDetail;

public interface IApiResultDetailDao extends IBaseDao<AResultDetail> {
	
	List<AResultDetail> findByResultId(Integer rid);
	
}
