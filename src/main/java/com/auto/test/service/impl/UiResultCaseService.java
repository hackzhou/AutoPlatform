package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUiResultCaseDao;
import com.auto.test.entity.UResultCase;
import com.auto.test.service.IUiResultCaseService;

@Service("uiResultCaseService")
public class UiResultCaseService implements IUiResultCaseService {
	
	@Resource(name="uiResultCaseDao")
	private IUiResultCaseDao dao;

	@Override
	public List<UResultCase> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public UResultCase findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public Integer create(UResultCase uResultCase) {
		if(uResultCase != null){
			uResultCase.setCreateTime(new Date());
			dao.create(uResultCase);
			return uResultCase.getId();
		}
		return null;
	}

	@Override
	public UResultCase update(UResultCase uResultCase) {
		if(uResultCase != null){
			UResultCase rCase = dao.findById(uResultCase.getId());
			if(rCase != null){
				rCase.update(uResultCase);
				dao.update(rCase);
				return rCase;
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
