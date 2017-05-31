package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AVersion> findByVersion(String version) {
		return getCurrentSession().createCriteria(AVersion.class).add(Restrictions.eq("version", version)).list();
	}
}
