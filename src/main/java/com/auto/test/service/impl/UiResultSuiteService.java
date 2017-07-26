package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUiResultSuiteDao;
import com.auto.test.entity.UResultSuite;
import com.auto.test.service.IUiResultSuiteService;

@Service("uiResultSuiteService")
public class UiResultSuiteService implements IUiResultSuiteService {
	
	@Resource(name="uiResultSuiteDao")
	private IUiResultSuiteDao dao;

	@Override
	public List<UResultSuite> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public UResultSuite findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public Integer create(UResultSuite uResultSuite) {
		if(uResultSuite != null){
			uResultSuite.setCreateTime(new Date());
			dao.create(uResultSuite);
			return uResultSuite.getId();
		}
		return null;
	}

	@Override
	public UResultSuite update(UResultSuite uResultSuite) {
		if(uResultSuite != null){
			UResultSuite suite = dao.findById(uResultSuite.getId());
			if(suite != null){
				suite.update(uResultSuite);
				dao.update(suite);
				return suite;
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
