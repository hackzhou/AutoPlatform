package com.auto.test.service.impl;

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
	public Integer create(AUser aUser) {
		dao.create(aUser);
		return aUser.getId();
	}

	@Override
	public AUser update(AUser aUser) {
		return dao.update(aUser);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}


}
