package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiAccountDao;
import com.auto.test.entity.AAccount;
import com.auto.test.service.IApiAccountService;

@Service("apiAccountService")
public class ApiAccountService implements IApiAccountService {
	
	@Resource(name="apiAccountDao")
	private IApiAccountDao dao;

	@Override
	public List<AAccount> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AAccount> findByName(String name) {
		return dao.findByName(name);
	}
	
	@Override
	public AAccount findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AAccount aAccount) {
		if(aAccount != null){
			aAccount.setCreateTime(new Date());
			dao.create(aAccount);
			return aAccount.getId();
		}
		return null;
	}

	@Override
	public AAccount update(AAccount aAccount) {
		if(aAccount != null){
			AAccount acc = dao.findById(aAccount.getId());
			if(acc != null){
				acc.update(aAccount);
				dao.update(acc);
				return acc;
			}
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}

}
