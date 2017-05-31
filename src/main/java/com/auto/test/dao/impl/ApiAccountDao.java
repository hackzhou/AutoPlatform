package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AAccount> findByName(String name) {
		return getCurrentSession().createCriteria(AAccount.class).add(Restrictions.eq("loginname", name)).list();
	}
}
