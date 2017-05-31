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
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.parse.IApiCaseParse;
import com.auto.test.dao.IApiAccountDao;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.dao.IApiResultDao;
import com.auto.test.dao.IApiVersionDao;
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
	
	@Resource(name="apiVersionDao")
	private IApiVersionDao versionDao;
	
	@Resource(name="apiAccountDao")
	private IApiAccountDao accountDao;

	@Resource(name="apiResultDao")
	private IApiResultDao resultDao;

	@Override
	public void run(ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby) {
		try {
			List<ACase> list = getRunCases(type, runId);
			if(list != null && !list.isEmpty()){
				ApiContext apiContext = getApiContext(list, type, runId, accountId, versionId, runby);
				if(apiContext != null){
					IApiCaseParse caseParse = (IApiCaseParse) SpringContext.getBean("apiCaseParse");
					caseParse.execute(apiContext);
				}
			}else{
				throw new BusinessException("运行[案例]未找到！");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private ApiContext getApiContext(List<ACase> list, ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby){
		try {
			ApiContext apiContext = new ApiContext();
			if(accountId != null){
				apiContext.setaAccount(accountDao.findById(accountId));
			}
			if(versionId != null){
				apiContext.setaVersion(versionDao.findById(versionId));
			}else{
				throw new BusinessException("运行[版本/渠道号]未找到！");
			}
			apiContext.setResult(createApiResult(type, runId, runby, list.size()));
			apiContext.setList(list);
			apiContext.setTotal(list.size());
			return apiContext;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	private AResult createApiResult(ApiRunType type, Integer runId, String runby, Integer total){
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
		aResult.setTotal(total);
		aResult.setCreateTime(new Date());
		aResult.setStartTime(new Date());
		resultDao.create(aResult);
		return aResult;
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
