package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.UCode;

public interface IUiCodeDao extends IBaseDao<UCode> {

	public List<UCode> findByCls(String cls);
	
}
