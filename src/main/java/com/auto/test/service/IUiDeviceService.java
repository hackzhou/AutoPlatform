package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.UDevice;

@Transactional
public interface IUiDeviceService{
	
	List<UDevice> findAll();
	
	UDevice findById(Integer id);

	List<UDevice> findByUdid(String udid);
	
	Integer create(UDevice uDevice);
	
	UDevice update(UDevice uDevice);
	
	void delete(Integer id);
	
}
