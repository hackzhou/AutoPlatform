package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.entity.ACase;

@Repository("apiCaseDao")
public class ApiCaseDao extends AbstractHibernateDao<ACase> implements IApiCaseDao {

	public ApiCaseDao() {
        super();
        setClazz(ACase.class);
    }
	
}
