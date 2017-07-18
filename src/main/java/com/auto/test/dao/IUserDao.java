package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.TUser;

public interface IUserDao extends IBaseDao<TUser> {
	
	public TUser isLogin(String username, String password);
	
	public List<TUser> findByName(String username);

}
