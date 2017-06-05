package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiVersionDao;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiVersionService;

@Service("apiVersionService")
public class ApiVersionService implements IApiVersionService {
	
	@Resource(name="apiVersionDao")
	private IApiVersionDao dao;

	@Override
	public List<AVersion> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AVersion> findByVersion(String version) {
		return dao.findByVersion(version);
	}
	
	@Override
	public AVersion findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AVersion aVersion) {
		if(aVersion != null){
			aVersion.setCreateTime(new Date());
			dao.create(aVersion);
			return aVersion.getId();
		}
		return null;
	}

	@Override
	public AVersion update(AVersion aVersion) {
		if(aVersion != null){
			AVersion ver = dao.findById(aVersion.getId());
			if(ver != null){
				ver.update(aVersion);
				dao.update(ver);
				return ver;
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
