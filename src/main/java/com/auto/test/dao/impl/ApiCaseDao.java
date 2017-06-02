package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AVersion;

@Repository("apiCaseDao")
public class ApiCaseDao extends AbstractHibernateDao<ACase> implements IApiCaseDao {

	public ApiCaseDao() {
        super();
        setClazz(ACase.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ACase> findByInterfaceId(Integer id) {
		return getCurrentSession().createCriteria(ACase.class).add(Restrictions.eq("interfaceo", new AInterface(id))).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ACase> findByProjectVersion(Integer pid, Integer vid) {
		List<AInterface> interList = getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(pid))).list();
		if(interList != null && !interList.isEmpty()){
			return getCurrentSession().createCriteria(ACase.class).add(Restrictions.in("versiono", new AVersion(vid))).add(Restrictions.in("interfaceo", interList)).list();
		}
		return null;
	}
	
}
