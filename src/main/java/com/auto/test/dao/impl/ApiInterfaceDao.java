package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AProject;

@Repository("apiInterfaceDao")
public class ApiInterfaceDao extends AbstractHibernateDao<AInterface> implements IApiInterfaceDao {

	public ApiInterfaceDao() {
        super();
        setClazz(AInterface.class);
    }
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AInterface> findByProjectId(Integer id) {
		return getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(id))).addOrder(Order.desc("id")).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AInterface> findByUrl(String url) {
		return getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("url", url)).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AInterface> findByProjectUrl(Integer id, String url) {
		return getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(id))).add(Restrictions.eq("url", url)).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AInterface> findByNotBacth(Integer pid, String batch) {
		return getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(pid))).add(Restrictions.ne("batch", batch)).list();
	}
}
