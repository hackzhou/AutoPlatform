package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiResultDao;
import com.auto.test.entity.AResult;

@Repository("apiResultDao")
public class ApiResultDao extends AbstractHibernateDao<AResult> implements IApiResultDao {

	public ApiResultDao() {
        super();
        setClazz(AResult.class);
    }

}
