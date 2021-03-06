package com.auto.test.service;

import java.util.List;
import javax.transaction.Transactional;
import com.auto.test.common.bean.AInterfaceCase;
import com.auto.test.entity.AInterface;

@Transactional
public interface IApiInterfaceService{
	
	List<AInterface> findAll();

	List<AInterface> findByProjectId(Integer id);

	List<AInterface> findByMinProjectId();

	List<AInterface> findByUrl(String url);

	List<AInterface> findByProjectUrl(Integer id, String url);

	List<AInterface> findByNotBacth(Integer pid, String batch);
	
	AInterface findById(Integer id);
	
	void exportApiInterface(List<AInterfaceCase> list);
	
	Integer create(AInterface aInterface);
	
	AInterface update(AInterface aInterface);
	
	void delete(AInterface aInterface);

	void delete(Integer id);

	void deleteCascade(Integer pid, String batch);

	void deleteCascade(Integer id);

}
