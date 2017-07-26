package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.UResultStep;

@Transactional
public interface IUiResultStepService{
	
	List<UResultStep> findAll();
	
	UResultStep findById(Integer id);

	Integer create(UResultStep uResultStep);
	
	UResultStep update(UResultStep uResultStep);
	
	void delete(Integer id);
	
}
