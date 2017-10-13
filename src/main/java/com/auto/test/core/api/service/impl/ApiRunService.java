package com.auto.test.core.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.ApiApplication;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.parse.IApiCaseParse;
import com.auto.test.core.api.service.IApiRunService;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AProject;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiAccountService;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiProjectService;
import com.auto.test.service.IApiResultService;
import com.auto.test.service.IApiVersionService;

public class ApiRunService implements IApiRunService {
	private static Logger logger = LoggerFactory.getLogger(ApiRunService.class);
	
	@Resource
	private IApiProjectService projectService;
	
	@Resource
	private IApiCaseService caseService;
	
	@Resource
	private IApiVersionService versionService;
	
	@Resource
	private IApiAccountService accountService;

	@Resource
	private IApiResultService resultService;

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
			throw throwException(logger, "该账号[id=" + accountId + "]正在使用，请稍后运行！");
		}
	}
	
	private List<ACase> getRunCases(ApiRunType type, Integer runId, Integer vId){
		List<ACase> list = new ArrayList<ACase>();
		if(ApiRunType.PROJECT.equals(type)){
			List<ACase> cList = caseService.findByProjectVersion(runId, vId);
			if(cList != null && !cList.isEmpty()){
				for (ACase aCase : cList) {
					if(new Integer(1).equals(aCase.getRun())){
						aCase.setList(caseService.findByIds(getIdList(aCase.getLink())));
						list.add(aCase);
					}
				}
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseService.findById(runId);
			if(aCase != null && new Integer(1).equals(aCase.getRun())){
				aCase.setList(caseService.findByIds(getIdList(aCase.getLink())));
				list.add(aCase);
			}
		}
		if(list.isEmpty()){
			throw throwException(logger, "运行[案例]未找到！");
		}
		return list;
	}
	
	private AVersion getApiVersion(List<ACase> list, ApiRunType type, Integer versionId){
		AVersion aVersion = null;
		if(ApiRunType.PROJECT.equals(type)){
			aVersion = versionService.findById(versionId);
		}else if(ApiRunType.CASE.equals(type)){
			aVersion = list.get(0).getVersiono();
		}
		if(aVersion == null){
			throw throwException(logger, "运行[版本/渠道号]未找到！");
		}
		return aVersion;
	}
	
	private ApiContext getApiContext(List<ACase> list, ApiRunType type, Integer runId, Integer accountId, String runby, AVersion aVersion) throws Exception{
		ApiContext apiContext = new ApiContext();
		if(accountId != null){
			apiContext.setAccount(accountService.findById(accountId));
		}
		apiContext.setList(list);
		apiContext.setVersion(aVersion);
		Integer len = aVersion.getChannel().split(",").length;
		apiContext.setTotal(getCaseTotal(list, len));
		/*apiContext.setBool(isRunOnline(list));	//Online Compare
		if(apiContext.getAccount() != null && "1".equals(apiContext.getAccount().getToken())){
			int index = apiContext.getAccount().getPassword().split(",").length;
			if(index > 2){
				throw throwException(logger, "Token[格式错误]-[" + apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword() + "]");
			}
			if(apiContext.isBool() && index != 2){
				throw throwException(logger, "Token[线上未设置]-[" + apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword() + "]");
			}
		}*/
		apiContext.setResult(createApiResult(type, runId, runby, apiContext));
		return apiContext;
	}
	
	/*private boolean isRunOnline(List<ACase> list){	//Online Compare
		for (ACase aCase : list) {
			if(aCase.getResult() == null || aCase.getResult().isEmpty()){
				return true;
			}
		}
		return false;
	}*/
	
	private AResult createApiResult(ApiRunType type, Integer runId, String runby, ApiContext apiContext) throws Exception{
		AResult aResult = new AResult();
		if(ApiRunType.PROJECT.equals(type)){
			AProject aProject = projectService.findById(runId);
			if(aProject != null){
				apiContext.setProject(aProject);
				aResult.setProjecto(aProject);
				aResult.setName(String.format(Const.RUN_PROJECT_NAME, aProject.getName()));
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseService.findById(runId);
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
		aResult.setStartTime(new Date());
		resultService.create(aResult);
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
	
	public BusinessException throwException(Logger logger, String message){
		logger.error(message);
		return new BusinessException(message);
	}
	
}
