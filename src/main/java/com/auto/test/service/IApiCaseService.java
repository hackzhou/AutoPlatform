package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.ACase;

@Transactional
public interface IApiCaseService{
	
	List<ACase> findAll();
	
	List<ACase> findByInterfaceId(Integer id);

	List<ACase> findByVersionId(Integer id);
	
	List<ACase> findByProjectVersion(Integer pid, Integer vid);

	ACase findById(Integer id);
	
	List<ACase> findByIds(List<Integer> cids);
	
	Integer create(ACase aCase);
	
	ACase update(ACase aCase);
	
	void delete(ACase aCase);

	void delete(Integer id);
	
	void copyCase(Integer pid, Integer vida, Integer vidb);
	
	void evict(ACase aCase);

}
