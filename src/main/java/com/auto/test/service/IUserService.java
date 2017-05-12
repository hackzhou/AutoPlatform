package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AUser;

@Transactional
public interface IUserService{
	
	List<AUser> getAllUser();
	
	AUser isLogin(String username, String password);
	
	Integer create(AUser aUser);
	
	AUser update(AUser aUser);
	
	void delete(Integer id);

}
