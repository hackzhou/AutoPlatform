package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiCaseService;

@Service("apiCaseService")
public class ApiCaseService implements IApiCaseService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao dao;

	@Override
	public List<ACase> findAllCase() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<ACase> findByInterfaceId(Integer id) {
		return dao.findByInterfaceId(id);
	}
	
	@Override
	public List<ACase> findByVersionId(Integer id) {
		return dao.findByVersionId(id);
	}
	
	@Override
	public List<ACase> findByProjectVersion(Integer pid, Integer vid) {
		return dao.findByProjectVersion(pid, vid);
	}
	
	@Override
	public ACase findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(ACase aCase) {
		if(aCase != null){
			aCase.setCreateTime(new Date());
			dao.create(aCase);
			return aCase.getId();
		}
		return null;
	}

	@Override
	public ACase update(ACase aCase) {
		if(aCase != null){
			ACase case0 = dao.findById(aCase.getId());
			if(case0 != null){
				case0.update(aCase);
				dao.update(case0);
				return case0;
			}
		}
		return null;
	}

	@Override
	public void delete(ACase aCase) {
		if(aCase != null){
			dao.delete(aCase);
		}
	}
	
	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}

	@Override
	public void copyCase(Integer pid, Integer vida, Integer vidb) {
		List<ACase> list = this.findByProjectVersion(pid, vida);
		if(list != null && !list.isEmpty()){
			ACase newCase = null;
			for (ACase aCase : list) {
				newCase = new ACase();
				newCase.update(aCase);
				newCase.setVersiono(new AVersion(vidb));
				this.create(newCase);
			}
		}
	}

}
