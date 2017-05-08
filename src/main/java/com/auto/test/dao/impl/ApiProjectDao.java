package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.AProject;

@Repository("apiProjectDao")
public class ApiProjectDao extends AbstractHibernateDao<AProject> implements IApiProjectDao {

	public ApiProjectDao() {
        super();
        setClazz(AProject.class);
    }
	
}
