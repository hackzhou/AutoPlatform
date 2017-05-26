package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AProject;

@Transactional
public interface IApiProjectService{
	
	List<AProject> findAllProject();
	
	AProject findById(Integer id);
	
	Integer create(AProject aProject);
	
	AProject update(AProject aProject);
	
	void delete(Integer id);

}
