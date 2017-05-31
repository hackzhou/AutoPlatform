package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.AProject;

@Repository("apiProjectDao")
public class ApiProjectDao extends AbstractHibernateDao<AProject> implements IApiProjectDao {

	public ApiProjectDao() {
        super();
        setClazz(AProject.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AProject> findByName(String name) {
		return getCurrentSession().createCriteria(AProject.class).add(Restrictions.eq("name", name)).list();
	}
	
}
