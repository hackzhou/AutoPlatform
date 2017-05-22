package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiAccountDao;
import com.auto.test.entity.AAccount;

@Repository("apiAccountDao")
public class ApiAccountDao extends AbstractHibernateDao<AAccount> implements IApiAccountDao {

	public ApiAccountDao() {
        super();
        setClazz(AAccount.class);
    }
}
