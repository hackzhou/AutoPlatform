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
import com.auto.test.entity.AAccount;
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
	private static final String LOG_RERUN	= "[失败重跑]==>";
	private static final String LOG_RUN		= "[运行]==>";
	
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
	public void rerun(ApiRunType type, Integer runId, List<ACase> list, AAccount account, AVersion version, String runby, Integer compare, Integer platform) throws Exception{
		checkComparePlatform(compare, platform, LOG_RERUN);
		isNotRunAccount(account.getId(), LOG_RERUN);
		ApiContext apiContext = getApiContext(type, runId, list, account, version, runby, compare, platform, false, null, LOG_RERUN);
		apiContext.getResult().setName("<失败重跑>-" + apiContext.getResult().getName());
		if(apiContext != null){
			IApiCaseParse caseParse = (IApiCaseParse) SpringContext.getBean("apiCaseParse");
			caseParse.execute(apiContext);
		}
	}

	@Override
	public void run(ApiRunType type, Integer runId, Integer accountId, Integer versionId, String runby, Integer compare, Integer platform, boolean mail, String emails) throws Exception{
		checkComparePlatform(compare, platform, LOG_RUN);
		isNotRunAccount(accountId, LOG_RUN);
		List<ACase> list = getRunCases(type, runId, versionId);
		AVersion aVersion = getApiVersion(list, type, versionId);
		ApiContext apiContext = getApiContext(type, runId, list, getAAccount(accountId), aVersion, runby, compare, platform, mail, emails, LOG_RUN);
		if(apiContext != null){
			IApiCaseParse caseParse = (IApiCaseParse) SpringContext.getBean("apiCaseParse");
			caseParse.execute(apiContext);
		}
	}
	
	private void checkComparePlatform(Integer compare, Integer platform, String text){
		if(new Integer(0).equals(compare)){
			if(!(new Integer(1).equals(platform) || new Integer(2).equals(platform) || new Integer(3).equals(platform))){
				throw throwException(logger, text + "测试环境[platform=" + platform + "]获取异常！");
			}
		}else if(new Integer(1).equals(compare)){
			if(!new Integer(2).equals(platform)){
				throw throwException(logger, text + "测试环境[platform=" + platform + "]线上对比必须为预发环境！");
			}
		}else{
			throw throwException(logger, text + "对比方式[compare=" + compare + "]获取异常！");
		}
	}
	
	private void isNotRunAccount(Integer accountId, String text){
		ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
		if(accountId != null && apiApplication.contain(accountId)){
			throw throwException(logger, text + "该账号[id=" + accountId + "]正在使用，请稍后运行！");
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
			throw throwException(logger, LOG_RUN + "案例未找到！");
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
			throw throwException(logger, LOG_RUN + "版本/渠道号未找到！");
		}
		return aVersion;
	}
	
	private AAccount getAAccount(Integer accountId){
		if(accountId != null){
			return accountService.findById(accountId);
		}
		return null;
	}
	
	private ApiContext getApiContext(ApiRunType type, Integer runId, List<ACase> list, AAccount account, AVersion aVersion, String runby, Integer compare, Integer platform, boolean mail, String emails, String text) throws Exception{
		ApiContext apiContext = new ApiContext();
		apiContext.setAccount(account);
		if(mail && (emails == null || emails.isEmpty())){
			throw throwException(logger, text + "发送邮件[收件人]不能为空！");
		}
		apiContext.setMail(mail);
		apiContext.setEmails(emails);
		apiContext.setList(list);
		apiContext.setVersion(aVersion);
		Integer len = aVersion.getChannel().split(",").length;
		apiContext.setTotal(getCaseTotal(list, len));
		apiContext.setCompare(new Integer(1).equals(compare));
		apiContext.setPlatform(platform);
		if(apiContext.getAccount() != null && "1".equals(apiContext.getAccount().getToken()) && !"游客登录".equals(apiContext.getAccount().getLoginname())){
			int index = apiContext.getAccount().getPassword().split(",").length;
			if(index > 2){
				throw throwException(logger, text + "Token[格式错误]-[" + apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword() + "]");
			}
			if(apiContext.isCompare() && index != 2){
				throw throwException(logger, text + "Token[线上未设置]-[" + apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword() + "]");
			}
		}
		apiContext.setResult(createApiResult(type, runId, runby, apiContext, compare, platform));
		return apiContext;
	}
	
	private AResult createApiResult(ApiRunType type, Integer runId, String runby, ApiContext apiContext, Integer compare, Integer platform) throws Exception{
		AResult aResult = new AResult();
		if(ApiRunType.PROJECT.equals(type)){
			AProject aProject = projectService.findById(runId);
			if(aProject != null){
				apiContext.setProject(aProject);
				aResult.setProjecto(aProject);
				if(apiContext.getAccount() == null){
					aResult.setName(String.format(Const.RUN_PROJECT_NAME_N, aProject.getName()));
				}else{
					aResult.setName(String.format(Const.RUN_PROJECT_NAME_Y, aProject.getName()));
				}
			}
		}else if(ApiRunType.CASE.equals(type)){
			ACase aCase = caseService.findById(runId);
			if(aCase != null){
				apiContext.setProject(aCase.getInterfaceo().getProjecto());
				aResult.setProjecto(aCase.getInterfaceo().getProjecto());
				if(apiContext.getAccount() == null){
					aResult.setName(String.format(Const.RUN_CASE_NAME_N, aCase.getName()));
				}else{
					aResult.setName(String.format(Const.RUN_CASE_NAME_Y, aCase.getName()));
				}
			}
		}
		aResult.setCompare(compare);
		aResult.setPlatform(platform);
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
