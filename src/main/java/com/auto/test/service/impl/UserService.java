package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUserDao;
import com.auto.test.entity.TUser;
import com.auto.test.service.IUserService;

@Service("userService")
public class UserService implements IUserService {
	
	@Resource(name="userDao")
	private IUserDao dao;

	@Override
	public List<TUser> findAll() {
		return dao.findAll();
	}

	@Override
	public TUser isLogin(String username, String password) {
		return dao.isLogin(username, password);
	}

	@Override
	public TUser create(TUser aUser) {
		if(aUser != null){
			aUser.setCreateTime(new Date());
			return dao.create(aUser);
		}
		return null;
	}

	@Override
	public TUser update(TUser aUser) {
		if(aUser != null){
			TUser user = dao.findById(aUser.getId());
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
	public List<TUser> findByName(String username) {
		return dao.findByName(username);
	}

}
