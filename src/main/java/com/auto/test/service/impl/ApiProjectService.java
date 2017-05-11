package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;

@Service("apiProjectService")
public class ApiProjectService implements IApiProjectService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao dao;

	@Override
	public List<AProject> getAllProject() {
		return dao.findAllOrder();
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
				dao.evict(pro);
				aProject.setCreateTime(pro.getCreateTime());
				aProject.setUpdateTime(new Date());
				dao.update(aProject);
				return aProject;
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

}