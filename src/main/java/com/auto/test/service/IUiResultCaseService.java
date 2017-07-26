package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.UResultCase;

@Transactional
public interface IUiResultCaseService{
	
	List<UResultCase> findAll();
	
	UResultCase findById(Integer id);

	Integer create(UResultCase uResultCase);
	
	UResultCase update(UResultCase uResultCase);
	
	void delete(Integer id);
	
}
