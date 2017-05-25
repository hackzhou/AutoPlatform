package com.auto.test.dao.impl;

import java.util.List;
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
		List<AInterface> list = getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(id))).list();
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
}
