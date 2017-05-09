package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AProject;

@Transactional
public interface IApiProjectService{
	
	List<AProject> getAllProject();
	
	void delete(Integer id);

}
