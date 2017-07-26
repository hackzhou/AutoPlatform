package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUiResultStepDao;
import com.auto.test.entity.UResultStep;

@Repository("uiResultStepDao")
public class UiResultStepDao extends AbstractHibernateDao<UResultStep> implements IUiResultStepDao {

	public UiResultStepDao() {
        super();
        setClazz(UResultStep.class);
    }
	
}
