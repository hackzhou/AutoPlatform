package com.auto.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.ACase;
import com.auto.test.service.IApiRunService;

@Service("apiRunService")
public class ApiRunService implements IApiRunService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao projectDao;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao caseDao;

	@Override
	public void run(ApiRunType type, Integer runId, Integer accountId) {
		List<ACase> list = getRunCases(type, runId);
		if(list != null && !list.isEmpty()){
			
		}
	}
	
	private List<ACase> getRunCases(ApiRunType type, Integer runId){
		List<ACase> list = new ArrayList<ACase>();
		if(ApiRunType.PROJECT.equals(type)){
			List<ACase> cList = caseDao.findByProjectId(runId);
			if(cList != null && !cList.isEmpty()){
				list.addAll(cList);
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseDao.findById(runId);
			if(aCase != null){
				list.add(aCase);
			}
		}
		return list;
	}
	
}
