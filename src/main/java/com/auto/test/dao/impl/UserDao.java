package com.auto.test.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.auto.test.common.dao.AbstractHibernateDao;
import com.auto.test.dao.IUserDao;
import com.auto.test.entity.TUser;

@Repository("userDao")
public class UserDao extends AbstractHibernateDao<TUser> implements IUserDao {

	public UserDao() {
        super();
        setClazz(TUser.class);
    }

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public TUser isLogin(String username, String password) {
		List<TUser> list = getCurrentSession().createCriteria(TUser.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).list();
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<TUser> findByName(String username) {
		return getCurrentSession().createCriteria(TUser.class).add(Restrictions.eq("username", username)).list();
	}
	
}
