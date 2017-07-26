package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUiResultCaseDao;
import com.auto.test.entity.UResultCase;

@Repository("uiResultCaseDao")
public class UiResultCaseDao extends AbstractHibernateDao<UResultCase> implements IUiResultCaseDao {

	public UiResultCaseDao() {
        super();
        setClazz(UResultCase.class);
    }
	
}
