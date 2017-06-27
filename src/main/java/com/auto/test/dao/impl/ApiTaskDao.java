package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiTaskDao;
import com.auto.test.entity.AAccount;
import com.auto.test.entity.AProject;
import com.auto.test.entity.ATask;
import com.auto.test.entity.AVersion;

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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ATask> findByProject(Integer pid) {
		return getCurrentSession().createCriteria(ATask.class).add(Restrictions.eq("projecto", new AProject(pid))).list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ATask> findByVersion(Integer vid) {
		return getCurrentSession().createCriteria(ATask.class).add(Restrictions.eq("projecto", new AVersion(vid))).list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ATask> findByAccount(Integer aid) {
		return getCurrentSession().createCriteria(ATask.class).add(Restrictions.eq("projecto", new AAccount(aid))).list();
	}

}
