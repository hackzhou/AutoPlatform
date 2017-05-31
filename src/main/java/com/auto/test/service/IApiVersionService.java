package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AVersion;

@Transactional
public interface IApiVersionService{
	
	List<AVersion> findAllVersion();

	List<AVersion> findByVersion(String version);
	
	AVersion findById(Integer id);
	
	Integer create(AVersion aVersion);
	
	AVersion update(AVersion aVersion);
	
	void delete(Integer id);

}
