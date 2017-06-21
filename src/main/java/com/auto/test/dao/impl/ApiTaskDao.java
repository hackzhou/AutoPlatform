package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiTaskDao;
import com.auto.test.entity.ATask;

@Repository("apiTaskDao")
public class ApiTaskDao extends AbstractHibernateDao<ATask> implements IApiTaskDao {

	public ApiTaskDao() {
        super();
        setClazz(ATask.class);
    }

}
