package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUiResultStepDao;
import com.auto.test.entity.UResultStep;
import com.auto.test.service.IUiResultStepService;

@Service("uiResultStepService")
public class UiResultStepService implements IUiResultStepService {
	
	@Resource(name="uiResultStepDao")
	private IUiResultStepDao dao;

	@Override
	public List<UResultStep> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public UResultStep findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public Integer create(UResultStep uResultStep) {
		if(uResultStep != null){
			uResultStep.setCreateTime(new Date());
			dao.create(uResultStep);
			return uResultStep.getId();
		}
		return null;
	}

	@Override
	public UResultStep update(UResultStep uResultStep) {
		if(uResultStep != null){
			UResultStep step = dao.findById(uResultStep.getId());
			if(step != null){
				step.update(uResultStep);
				dao.update(step);
				return step;
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
