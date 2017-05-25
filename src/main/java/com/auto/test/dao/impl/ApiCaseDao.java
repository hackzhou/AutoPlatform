package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;

@Repository("apiCaseDao")
public class ApiCaseDao extends AbstractHibernateDao<ACase> implements IApiCaseDao {

	public ApiCaseDao() {
        super();
        setClazz(ACase.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ACase> getCaseByInterfaceId(Integer id) {
		List<ACase> list = getCurrentSession().createCriteria(ACase.class).add(Restrictions.eq("interfaceo", new AInterface(id))).list();
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
