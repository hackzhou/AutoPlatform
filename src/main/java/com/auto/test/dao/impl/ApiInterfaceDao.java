package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.entity.AInterface;

@Repository("apiInterfaceDao")
public class ApiInterfaceDao extends AbstractHibernateDao<AInterface> implements IApiInterfaceDao {

	public ApiInterfaceDao() {
        super();
        setClazz(AInterface.class);
    }
}
