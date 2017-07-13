package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiInterfaceService;

@Service("apiInterfaceService")
public class ApiInterfaceService implements IApiInterfaceService {
	
	@Resource(name="apiInterfaceDao")
	private IApiInterfaceDao dao;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao daoCase;

	@Override
	public List<AInterface> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AInterface> findByProjectId(Integer id) {
		return dao.findByProjectId(id);
	}
	
	@Override
	public List<AInterface> findByUrl(String url) {
		return dao.findByUrl(url);
	}
	
	@Override
	public List<AInterface> findByProjectUrl(Integer id, String url) {
		return dao.findByProjectUrl(id, url);
	}
	
	@Override
	public AInterface findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AInterface aInterface) {
		if(aInterface != null){
			aInterface.setCreateTime(new Date());
			dao.create(aInterface);
			return aInterface.getId();
		}
		return null;
	}

	@Override
	public AInterface update(AInterface aInterface) {
		if(aInterface != null){
			AInterface inter = dao.findById(aInterface.getId());
			if(inter != null){
				inter.update(aInterface);
				dao.update(inter);
				return inter;
			}
		}
		return null;
	}

	@Override
	public void delete(AInterface aInterface) {
		if(aInterface != null){
			dao.delete(aInterface);
		}
	}
	
	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}
	
	@Override
	public void deleteCascade(Integer id) throws Exception{
		List<ACase> caseList = daoCase.findByInterfaceId(id);
		if(caseList != null && !caseList.isEmpty()){
			for (ACase aCase : caseList) {
				daoCase.delete(aCase);
			}
		}
		delete(id);
	}

	@Override
	public void exportApiInterface(List<AInterface> list) {
		if(list != null && !list.isEmpty()){
			for (AInterface aInterface : list) {
				if(aInterface.getProjecto() != null && aInterface.getType() != null && aInterface.getName() != null && aInterface.getUrl() != null){
					List<AInterface> interList = findByProjectUrl(aInterface.getProjecto().getId(), aInterface.getUrl());
					if(interList != null && !interList.isEmpty()){
						AInterface aInterfaceDB = interList.get(0);
						aInterfaceDB.update(aInterface);
						update(aInterfaceDB);
					}else{
						create(aInterface);
					}
				}
			}
		}
	}

}
