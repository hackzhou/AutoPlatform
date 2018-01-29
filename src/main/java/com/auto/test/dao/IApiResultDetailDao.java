package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AResultDetail;

public interface IApiResultDetailDao extends IBaseDao<AResultDetail> {
	
	List<AResultDetail> findByAllErr(Integer rid);

	List<AResultDetail> findByPingNo(Integer rid);

	List<AResultDetail> findByErr500(Integer rid);
	
	List<AResultDetail> findByResultId(Integer rid);

	List<AResultDetail> findByResultId(Integer rid, Integer filter);
	
}
