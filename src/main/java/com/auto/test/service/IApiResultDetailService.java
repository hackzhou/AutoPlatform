package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AResultDetail;

@Transactional
public interface IApiResultDetailService{
	
	List<AResultDetail> findAllResultDetail();
	
	AResultDetail findById(Integer id);
	
	Integer create(AResultDetail aResultDetail);
	
	AResultDetail update(AResultDetail aResultDetail);
	
	void delete(Integer id);

}
