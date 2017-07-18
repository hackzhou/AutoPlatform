package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.TUser;

@Transactional
public interface IUserService{
	
	List<TUser> findAll();
	
	List<TUser> findByName(String username);
	
	TUser isLogin(String username, String password);
	
	TUser create(TUser aUser);
	
	TUser update(TUser aUser);
	
	void delete(Integer id);

}
