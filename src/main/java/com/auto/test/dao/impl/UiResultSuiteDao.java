package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUiResultSuiteDao;
import com.auto.test.entity.UResultSuite;

@Repository("uiResultSuiteDao")
public class UiResultSuiteDao extends AbstractHibernateDao<UResultSuite> implements IUiResultSuiteDao {

	public UiResultSuiteDao() {
        super();
        setClazz(UResultSuite.class);
    }
	
}
