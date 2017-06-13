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
import com.auto.test.common.context.ApiApplication;
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
import com.auto.test.entity.AVersion;
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
	public void run(ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby) throws Exception{
		isNotRunAccount(accountId);
		List<ACase> list = getRunCases(type, runId, versionId);
		AVersion aVersion = getApiVersion(list, type, versionId);
		ApiContext apiContext = getApiContext(list, type, runId, accountId, runby, aVersion);
		if(apiContext != null){
			IApiCaseParse caseParse = (IApiCaseParse) SpringContext.getBean("apiCaseParse");
			caseParse.execute(apiContext);
		}
	}
	
	private void isNotRunAccount(Integer accountId){
		ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
		if(accountId != null && apiApplication.contain(accountId)){
			throw new BusinessException("该账号正在使用，请稍后运行！");
		}
	}
	
	private List<ACase> getRunCases(ApiRunType type, Integer runId, Integer vId){
		List<ACase> list = new ArrayList<ACase>();
		if(ApiRunType.PROJECT.equals(type)){
			List<ACase> cList = caseDao.findByProjectVersion(runId, vId);
			if(cList != null && !cList.isEmpty()){
				for (ACase aCase : cList) {
					aCase.setList(caseDao.findByIds(getIdList(aCase.getLink())));
				}
				list.addAll(cList);
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseDao.findById(runId);
			if(aCase != null){
				aCase.setList(caseDao.findByIds(getIdList(aCase.getLink())));
				list.add(aCase);
			}
		}
		if(list.isEmpty()){
			throw new BusinessException("运行[案例]未找到！");
		}
		return list;
	}
	
	private AVersion getApiVersion(List<ACase> list, ApiRunType type, Integer versionId){
		AVersion aVersion = null;
		if(ApiRunType.PROJECT.equals(type)){
			aVersion = versionDao.findById(versionId);
		}else if(ApiRunType.CASE.equals(type)){
			aVersion = list.get(0).getVersiono();
		}
		if(aVersion == null){
			throw new BusinessException("运行[版本/渠道号]未找到！");
		}
		return aVersion;
	}
	
	private ApiContext getApiContext(List<ACase> list, ApiRunType type, Integer runId, Integer accountId, String runby, AVersion aVersion) throws Exception{
		ApiContext apiContext = new ApiContext();
		if(accountId != null){
			apiContext.setAccount(accountDao.findById(accountId));
		}
		apiContext.setList(list);
		apiContext.setVersion(aVersion);
		Integer len = aVersion.getChannel().split(",").length;
		apiContext.setTotal(getCaseTotal(list, len));
		apiContext.setResult(createApiResult(type, runId, runby, apiContext));
		return apiContext;
	}
	
	private AResult createApiResult(ApiRunType type, Integer runId, String runby, ApiContext apiContext) throws Exception{
		AResult aResult = new AResult();
		if(ApiRunType.PROJECT.equals(type)){
			AProject aProject = projectDao.findById(runId);
			if(aProject != null){
				apiContext.setProject(aProject);
				aResult.setProjecto(aProject);
				aResult.setName(String.format(Const.RUN_PROJECT_NAME, aProject.getName()));
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseDao.findById(runId);
			if(aCase != null){
				apiContext.setProject(aCase.getInterfaceo().getProjecto());
				aResult.setProjecto(aCase.getInterfaceo().getProjecto());
				aResult.setName(String.format(Const.RUN_CASE_NAME, aCase.getName()));
			}
		}
		aResult.setVersiono(apiContext.getVersion());
		aResult.setRunby(runby);
		aResult.setStatus(ApiRunStatus.RUNNING.name());
		aResult.setSuccess(0);
		aResult.setFail(0);
		aResult.setTotal(apiContext.getTotal());
		aResult.setCreateTime(new Date());
		aResult.setStartTime(new Date());
		resultDao.create(aResult);
		return aResult;
	}
	
	private List<Integer> getIdList(String ids){
		List<Integer> idList = null;
		if(ids != null && !ids.isEmpty()){
			idList = new ArrayList<Integer>();
			for (String s : ids.split(",")) {
				idList.add(Integer.parseInt(s));
			}
		}
		return idList;
	}
	
	private Integer getCaseTotal(List<ACase> list, Integer len){
		Integer total = 0;
		if(list != null && !list.isEmpty()){
			total = list.size();
			for (ACase aCase : list) {
				if(aCase.getList() != null){
					total += aCase.getList().size();
				}
			}
		}
		return total * len;
	}
	
}
