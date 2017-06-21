package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.ATask;

@Transactional
public interface IApiTaskService{
	
	List<ATask> findAll();
	
	ATask findById(Integer id);
	
	Integer create(ATask aTask);
	
	ATask update(ATask aTask);
	
	void delete(Integer id);

}
