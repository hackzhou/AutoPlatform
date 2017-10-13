package com.auto.test.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Order;
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
	public List<ACase> findByInterfaceIdFlag(Integer id, Integer flag) {
		return getCurrentSession().createCriteria(ACase.class).add(Restrictions.eq("interfaceo", new AInterface(id))).add(Restrictions.eq("flag", flag)).list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ACase> findByVersionId(Integer id) {
		return getCurrentSession().createCriteria(ACase.class).add(Restrictions.eq("versiono", new AVersion(id))).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ACase> findByProjectVersion(Integer pid, Integer vid) {
		List<AInterface> interList = getCurrentSession().createCriteria(AInterface.class).add(Restrictions.eq("projecto", new AProject(pid))).list();
		if(interList != null && !interList.isEmpty()){
			return getCurrentSession().createCriteria(ACase.class).add(Restrictions.eq("versiono", new AVersion(vid))).add(Restrictions.in("interfaceo", interList)).addOrder(Order.desc("id")).list();
		}
		return null;
	}

	@Override
	public List<ACase> findByIds(List<Integer> cids) {
		List<ACase> list = new ArrayList<ACase>();
		if(cids != null && cids.size() > 0){
			for (Integer cid : cids) {
				ACase aCase = this.findById(cid);
				if(aCase != null){
					list.add(aCase);
				}
			}
			return list;
		}
		return null;
	}

}
