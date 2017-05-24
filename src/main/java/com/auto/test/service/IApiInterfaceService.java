package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AInterface;

@Transactional
public interface IApiInterfaceService{
	
	List<AInterface> getAllInterface();
	
	AInterface getInterfaceById(Integer id);
	
	Integer create(AInterface aInterface);
	
	AInterface update(AInterface aInterface);
	
	void delete(Integer id);

}
