package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.constant.ApiStatus;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiResultDetailDao;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AResultDetail;

@Repository("apiResultDetailDao")
public class ApiResultDetailDao extends AbstractHibernateDao<AResultDetail> implements IApiResultDetailDao {

	public ApiResultDetailDao() {
        super();
        setClazz(AResultDetail.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AResultDetail> findByAllErr(Integer rid) {
		return getCurrentSession().createCriteria(AResultDetail.class).add(Restrictions.eq("resulto", new AResult(rid))).add(Restrictions.eq("status", ApiStatus.FAILURE.name())).addOrder(Order.desc("id")).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AResultDetail> findByPingNo(Integer rid) {
		return getCurrentSession().createCriteria(AResultDetail.class).add(Restrictions.eq("resulto", new AResult(rid))).add(Restrictions.eq("status", ApiStatus.FAILURE.name())).add(Restrictions.isNotNull("msg")).addOrder(Order.desc("id")).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AResultDetail> findByErr500(Integer rid) {
		return getCurrentSession().createCriteria(AResultDetail.class).add(Restrictions.eq("resulto", new AResult(rid))).add(Restrictions.eq("status", ApiStatus.FAILURE.name())).add(Restrictions.like("resultb", "%{\"code\":500%")).addOrder(Order.desc("id")).list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AResultDetail> findByResultId(Integer rid) {
		return getCurrentSession().createCriteria(AResultDetail.class).add(Restrictions.eq("resulto", new AResult(rid))).list();
	}

}
