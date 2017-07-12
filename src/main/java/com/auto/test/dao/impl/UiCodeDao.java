package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUiCodeDao;
import com.auto.test.entity.UCode;

@Repository("uiCodeDao")
public class UiCodeDao extends AbstractHibernateDao<UCode> implements IUiCodeDao {

	public UiCodeDao() {
        super();
        setClazz(UCode.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<UCode> findByCls(String cls) {
		return getCurrentSession().createCriteria(UCode.class).add(Restrictions.eq("cls", cls)).list();
	}

}
