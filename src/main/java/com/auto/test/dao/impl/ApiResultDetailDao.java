package com.auto.test.dao.impl;

import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IApiResultDetailDao;
import com.auto.test.entity.AResultDetail;

@Repository("apiResultDetailDao")
public class ApiResultDetailDao extends AbstractHibernateDao<AResultDetail> implements IApiResultDetailDao {

	public ApiResultDetailDao() {
        super();
        setClazz(AResultDetail.class);
    }

}
