package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AResult;

@Transactional
public interface IApiResultService{
	
	List<AResult> findAll();
	
	AResult findById(Integer id);
	
	Integer create(AResult aResult);
	
	AResult update(AResult aResult);
	
	void delete(Integer id);

}
