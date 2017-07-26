package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.UResultSuite;

@Transactional
public interface IUiResultSuiteService{
	
	List<UResultSuite> findAll();
	
	UResultSuite findById(Integer id);

	Integer create(UResultSuite uResultSuite);
	
	UResultSuite update(UResultSuite uResultSuite);
	
	void delete(Integer id);
	
}
