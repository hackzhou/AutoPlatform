package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUiDeviceDao;
import com.auto.test.entity.UDevice;

@Repository("uiDeviceDao")
public class UiDeviceDao extends AbstractHibernateDao<UDevice> implements IUiDeviceDao {

	public UiDeviceDao() {
        super();
        setClazz(UDevice.class);
    }

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<UDevice> findByUdid(String udid) {
		return getCurrentSession().createCriteria(UDevice.class).add(Restrictions.eq("udid", udid)).list();
	}

}
