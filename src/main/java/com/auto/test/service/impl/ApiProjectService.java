package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;

@Service("apiProjectService")
public class ApiProjectService implements IApiProjectService {
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao dao;

	@Resource(name="apiInterfaceDao")
	private IApiInterfaceDao daoInterface;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao daoCase;
	
	@Override
	public List<AProject> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AProject> findByName(String name) {
		return dao.findByName(name);
	}
	
	@Override
	public AProject findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public Integer create(AProject aProject) {
		if(aProject != null){
			aProject.setCreateTime(new Date());
			dao.create(aProject);
			return aProject.getId();
		}
		return null;
	}
	
	@Override
	public AProject update(AProject aProject) {
		if(aProject != null){
			AProject pro = dao.findById(aProject.getId());
			if(pro != null){
				pro.update(aProject);
				dao.update(pro);
				return pro;
			}
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}

	@Override
	public void deleteCascade(Integer id) throws Exception {
		List<AInterface> interList = daoInterface.findByProjectId(id);
		if(interList != null && !interList.isEmpty()){
			List<ACase> caseList = null;
			for (AInterface aInterface : interList) {
				caseList = daoCase.findByInterfaceId(aInterface.getId());
				if(caseList != null && !caseList.isEmpty()){
					for (ACase aCase : caseList) {
						daoCase.delete(aCase);
					}
				}
				daoInterface.delete(aInterface);
			}
		}
		delete(id);
	}

}
