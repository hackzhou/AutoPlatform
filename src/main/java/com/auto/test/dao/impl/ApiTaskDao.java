package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ATask> findByTime(String time) {
		return getCurrentSession().createCriteria(ATask.class).add(Restrictions.eq("runTime", time)).list();
	}

}
