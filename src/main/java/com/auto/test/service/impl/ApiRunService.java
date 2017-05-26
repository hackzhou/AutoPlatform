package com.auto.test.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.ApiContext;
import com.auto.test.core.api.parse.IApiCaseParse;
import com.auto.test.core.api.parse.impl.ApiCaseParse;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.dao.IApiResultDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AResult;
import com.auto.test.service.IApiRunService;

@Service("apiRunService")
public class ApiRunService implements IApiRunService {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao projectDao;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao caseDao;
	
	@Resource(name="apiResultDao")
	private IApiResultDao resultDao;

	@Override
	public void run(ApiRunType type, Integer runId, Integer accountId, String runby) {
		List<ACase> list = getRunCases(type, runId);
		if(list != null && !list.isEmpty()){
			AResult aResult = createApiResult(type, runId, runby);
			System.out.println(aResult.toString());
			ApiContext apiContext = getApiContext(list, aResult);
			IApiCaseParse caseParse = new ApiCaseParse();
			caseParse.execute(apiContext);
		}
	}
	
	private ApiContext getApiContext(List<ACase> list, AResult aResult){
		ApiContext apiContext = new ApiContext();
		apiContext.setResult(aResult);
		apiContext.setList(list);
		return apiContext;
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
	
	private AResult createApiResult(ApiRunType type, Integer runId, String runby){
		AResult aResult = new AResult();
		if(ApiRunType.PROJECT.equals(type)){
			AProject aProject = projectDao.findById(runId);
			if(aProject != null){
				aResult.setProjecto(aProject);
				aResult.setName(String.format(Const.RUN_PROJECT_NAME, aProject.getName()));
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseDao.findById(runId);
			if(aCase != null){
				aResult.setProjecto(aCase.getInterfaceo().getProjecto());
				aResult.setName(String.format(Const.RUN_CASE_NAME, aCase.getName()));
			}
		}
		aResult.setRunby(runby);
		aResult.setStatus(ApiRunStatus.RUNNING.name());
		aResult.setSuccess(0);
		aResult.setFail(0);
		aResult.setTotal(0);
		aResult.setCreateTime(new Date());
		resultDao.create(aResult);
		return aResult;
	}
	
}
