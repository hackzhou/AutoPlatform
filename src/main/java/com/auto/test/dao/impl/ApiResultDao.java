package com.auto.test.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiResultDao;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AVersion;

@Repository("apiResultDao")
public class ApiResultDao extends AbstractHibernateDao<AResult> implements IApiResultDao {

	public ApiResultDao() {
        super();
        setClazz(AResult.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AResult> findByProjectVersionTime(Integer pid, Integer vid, Date startTime, Date endTime) {
		Criteria criteria = null;
		if(pid == null || new Integer(0).equals(pid)){
			criteria = getCurrentSession().createCriteria(AResult.class);
		}else{
			criteria = getCurrentSession().createCriteria(AResult.class).add(Restrictions.eq("projecto", new AProject(pid)));
			if(vid != null && !new Integer(0).equals(vid)){
				criteria = criteria.add(Restrictions.eq("versiono", new AVersion(vid)));
			}
		}
		return criteria.add(Restrictions.ge("startTime", startTime)).add(Restrictions.or(Restrictions.isNull("endTime"), Restrictions.le("endTime", endTime))).addOrder(Order.desc("id")).list();
	}

}
