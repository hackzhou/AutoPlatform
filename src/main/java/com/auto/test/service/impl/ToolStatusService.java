package com.auto.test.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IToolStatusDao;
import com.auto.test.entity.TStatus;
import com.auto.test.service.IToolStatusService;

@Service("toolStatusService")
public class ToolStatusService implements IToolStatusService {
	
	@Resource(name="toolStatusDao")
	private IToolStatusDao dao;
	
	@Override
	public List<TStatus> findAll() {
		return dao.findAllOrderAsc();
	}

	@Override
	public List<TStatus> findByRootName(String root, String name) {
		return dao.findByRootName(root, name);
	}
	
	@Override
	public TStatus findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public TStatus update(TStatus tStatus) {
		if(tStatus != null){
			TStatus ts = dao.findById(tStatus.getId());
			if(ts != null){
				ts.update(tStatus);
				dao.update(ts);
				return ts;
			}
		}
		return null;
	}

}
