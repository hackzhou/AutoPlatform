package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiResultDao;
import com.auto.test.entity.AResult;
import com.auto.test.service.IApiResultService;

@Service("apiResultService")
public class ApiResultService implements IApiResultService {
	
	@Resource(name="apiResultDao")
	private IApiResultDao dao;

	@Override
	public List<AResult> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public AResult findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AResult aResult) {
		if(aResult != null){
			aResult.setCreateTime(new Date());
			dao.create(aResult);
			return aResult.getId();
		}
		return null;
	}

	@Override
	public AResult update(AResult aResult) {
		if(aResult != null){
			AResult result = dao.findById(aResult.getId());
			if(result != null){
				result.update(aResult);
				dao.update(result);
				return result;
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
