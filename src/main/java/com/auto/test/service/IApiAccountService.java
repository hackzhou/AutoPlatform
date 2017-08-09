package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AAccount;

@Transactional
public interface IApiAccountService{
	
	List<AAccount> findAll();
	
	List<AAccount> findByName(String name);

	List<AAccount> findByTypeOrder(String type);

	AAccount findById(Integer id);
	
	Integer create(AAccount aAccount);
	
	AAccount update(AAccount aAccount);
	
	void delete(Integer id);
	
	void deleteCascade(Integer id) throws Exception;

}
