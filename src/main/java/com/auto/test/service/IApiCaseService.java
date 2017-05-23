package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.ACase;

@Transactional
public interface IApiCaseService{
	
	List<ACase> getAllCase();

	ACase getCaseById(Integer id);
	
	Integer create(ACase aCase);
	
	ACase update(ACase aCase);
	
	void delete(Integer id);

}
