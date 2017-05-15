package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AUser;

@Transactional
public interface IUserService{
	
	List<AUser> getAllUser();
	
	List<AUser> findByName(String username);
	
	AUser isLogin(String username, String password);
	
	AUser create(AUser aUser);
	
	AUser update(AUser aUser);
	
	void delete(Integer id);

}
