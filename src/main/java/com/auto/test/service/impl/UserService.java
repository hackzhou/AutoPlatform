package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUserDao;
import com.auto.test.entity.AUser;
import com.auto.test.service.IUserService;

@Service("userService")
public class UserService implements IUserService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="userDao")
	private IUserDao dao;

	@Override
	public List<AUser> getAllUser() {
		return dao.findAll();
	}

	@Override
	public AUser isLogin(String username, String password) {
		return dao.isLogin(username, password);
	}

	@Override
	public AUser create(AUser aUser) {
		if(aUser != null){
			aUser.setCreateTime(new Date());
			return dao.create(aUser);
		}
		return null;
	}

	@Override
	public AUser update(AUser aUser) {
		if(aUser != null){
			AUser user = dao.findById(aUser.getId());
			if(user != null){
				user.update(aUser);
				dao.update(user);
				return user;
			}
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<AUser> findByName(String username) {
		return dao.findByName(username);
	}

}
