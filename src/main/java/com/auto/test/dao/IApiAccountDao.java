package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AAccount;

public interface IApiAccountDao extends IBaseDao<AAccount> {

	List<AAccount> findByName(String name);
	
}
