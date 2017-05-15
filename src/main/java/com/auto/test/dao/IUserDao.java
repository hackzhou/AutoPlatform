package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.AUser;

public interface IUserDao extends IBaseDao<AUser> {
	
	public AUser isLogin(String username, String password);
	
	public List<AUser> findByName(String username);

}
