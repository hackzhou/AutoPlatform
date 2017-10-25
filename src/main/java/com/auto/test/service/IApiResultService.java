package com.auto.test.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AResult;

@Transactional
public interface IApiResultService{
	
	List<AResult> findAll();

	List<AResult> findByProjectVersionTime(Integer pid, Integer vid, Date startTime, Date endTime);
	
	AResult findById(Integer id);
	
	Integer create(AResult aResult);
	
	AResult update(AResult aResult);
	
	void delete(Integer id);

}
