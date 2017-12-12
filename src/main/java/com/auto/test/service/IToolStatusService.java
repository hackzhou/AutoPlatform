package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.TStatus;

@Transactional
public interface IToolStatusService{
	
	List<TStatus> findAll();

	List<TStatus> findByDept(String dept);

	List<TStatus> findByRootName(String root, String name);
	
	TStatus findById(Integer id);

	TStatus update(TStatus tStatus);
	
}