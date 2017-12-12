package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IToolStatusDao;
import com.auto.test.entity.TStatus;

@Repository("toolStatusDao")
public class ToolStatusDao extends AbstractHibernateDao<TStatus> implements IToolStatusDao {

	public ToolStatusDao() {
        super();
        setClazz(TStatus.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<TStatus> findByRootName(String root, String name) {
		return getCurrentSession().createCriteria(TStatus.class).add(Restrictions.eq("root", root)).add(Restrictions.eq("name", name)).addOrder(Order.asc("id")).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<TStatus> findByDept(String dept) {
		return getCurrentSession().createCriteria(TStatus.class).add(Restrictions.eq("dept", dept)).addOrder(Order.asc("id")).list();
	}

}
