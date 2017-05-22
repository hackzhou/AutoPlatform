package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiVersionDao;
import com.auto.test.entity.AVersion;

@Repository("apiVersionDao")
public class ApiVersionDao extends AbstractHibernateDao<AVersion> implements IApiVersionDao {

	public ApiVersionDao() {
        super();
        setClazz(AVersion.class);
    }
}
