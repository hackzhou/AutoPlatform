package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AVersion;

@Transactional
public interface IApiVersionService{
	
	List<AVersion> findAll();

	List<AVersion> findByMinProject();

	List<AVersion> findByProject(Integer pid);

	List<AVersion> findByVersionProject(String version, Integer pid);
	
	AVersion findById(Integer id);
	
	Integer create(AVersion aVersion);
	
	AVersion update(AVersion aVersion);
	
	void delete(Integer id);
	
	void deleteCascade(Integer id) throws Exception;

}
