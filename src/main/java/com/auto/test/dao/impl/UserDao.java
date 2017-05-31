package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUserDao;
import com.auto.test.entity.AUser;

@Repository("userDao")
public class UserDao extends AbstractHibernateDao<AUser> implements IUserDao {

	public UserDao() {
        super();
        setClazz(AUser.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public AUser isLogin(String username, String password) {
		List<AUser> list = getCurrentSession().createCriteria(AUser.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).list();
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AUser> findByName(String username) {
		return getCurrentSession().createCriteria(AUser.class).add(Restrictions.eq("username", username)).list();
	}
	
}
