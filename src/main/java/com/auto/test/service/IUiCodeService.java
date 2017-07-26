package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.UCode;
import com.auto.test.entity.UDevice;

@Transactional
public interface IUiCodeService{
	
	List<UCode> findAll();
	
	List<UCode> findByCls(String cls);

	List<UDevice> findByDevices(String devices);

	UCode findById(Integer id);
	
	Integer create(UCode uCode);
	
	UCode update(UCode uCode);
	
	void delete(Integer id);

	void evict(UCode uCode);
	
}
