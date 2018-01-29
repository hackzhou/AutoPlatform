package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.entity.AResultDetail;

@Transactional
public interface IApiResultDetailService{
	
	List<AResultDetail> findAll();
	
	List<AResultDetail> findByAllErr(Integer rid);

	List<AResultDetail> findByPingNo(Integer rid);

	List<AResultDetail> findByErr500(Integer rid);

	List<AResultDetail> findByResultId(Integer rid);
	
	List<AResultDetail> findByResultId(Integer rid, Integer filter);
	
	AResultDetail findById(Integer id);
	
	Integer create(AResultDetail aResultDetail);
	
	AResultDetail update(AResultDetail aResultDetail);
	
	void delete(Integer id);

}
