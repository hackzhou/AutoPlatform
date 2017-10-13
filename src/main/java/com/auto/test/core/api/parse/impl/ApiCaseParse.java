package com.auto.test.core.api.parse.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.context.ApiApplication;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.context.ApiThreadPool;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.execute.ApiExecuteRun;
import com.auto.test.core.api.http.HttpClientManager;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.core.api.http.bean.AccessToken;
import com.auto.test.core.api.http.bean.Login;
import com.auto.test.core.api.parse.IApiCaseParse;
import com.auto.test.entity.AAccount;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResult;
import com.auto.test.service.IApiResultService;

public class ApiCaseParse implements IApiCaseParse {
	private static final Logger logger = LoggerFactory.getLogger(ApiCaseParse.class);
	private ExecutorService cachedThreadPool = null;
	private HttpClientManager httpClientManager = null;
	/*private String urlA = null;	//Online Compare*/	
	private String urlB = null;
	private String userLogin = null;
	private String usersAccessToken = null;
	private String projectRootPath = null;
	
	public ApiCaseParse(){
		super();
		this.cachedThreadPool = ApiThreadPool.getInstance();
		/*this.urlA = GlobalValueConfig.getConfig("uri.production.environment");	//Online Compare*/		
		this.urlB = GlobalValueConfig.getConfig("uri.advance.environment");
		this.userLogin = GlobalValueConfig.getConfig("uri.user.login");
		this.usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
		this.httpClientManager = (HttpClientManager) SpringContext.getBean("httpClientManager");
		this.projectRootPath = GlobalValueConfig.getConfig("uri.project.path");
	}

	@Override
	public void execute(ApiContext apiContext) throws Exception{
		if(this.httpClientManager == null){
			throw new BusinessException("[HTTP/HTTPS]请求管理初始化失败！");
		}
		try {
			executeBody(apiContext);
		} catch (Exception e) {
			executeFinal(apiContext, e.getMessage());
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	private void executeBody(ApiContext apiContext) throws Exception{
		List<ACase> list = apiContext.getList();
		if(list != null && !list.isEmpty()){
			String version = apiContext.getVersion().getVersion();
			String channels = apiContext.getVersion().getChannel();
			for (String channel : channels.split(",")) {
				/*String authorA = null;	//Online Compare*/				
				String authorB = null;
				if(apiContext.getAccount() != null){
					if("1".equals(apiContext.getAccount().getToken())){
						String token = apiContext.getAccount().getPassword();
						/*if(token.contains(",")){	//Online Compare
							authorA = token.split(",")[0];
							authorB = token.split(",")[1];
						}else{
							authorB = token;
						}*/
						authorB = token;
					}else{
						/*if(apiContext.isBool()){	//Online Compare
							authorA = setAuthor(apiContext.getAccount(), urlA, version, channel, "线上");
							logger.info("[登录权限][线上]==>[" + authorA + "]");
						}*/
						authorB = setAuthor(apiContext.getAccount(), urlB, version, channel, "线下");
						logger.info("[登录权限][线下]==>[" + authorB + "]");
					}
				}
				for (ACase aCase : list) {
					if(new Integer(1).equals(aCase.getRun())){
						/*ApiExecuteRun apiExecuteRun = new ApiExecuteRun(httpClientManager, apiContext,
								aCase, urlA, urlB, authorA, authorB, version, channel, projectRootPath);	//Online Compare*/						
						ApiExecuteRun apiExecuteRun = new ApiExecuteRun(httpClientManager, apiContext,
								aCase, urlB, authorB, version, channel, projectRootPath);
						cachedThreadPool.execute(apiExecuteRun);
					}
				}
			}
		}
	}
	
	private void executeFinal(ApiContext apiContext, String message) throws Exception{
		try {
			if(apiContext.getAccount() != null){
				ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
				apiApplication.remove(apiContext.getAccount().getId());
			}
			IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
			AResult aResult = apiContext.getResult();
			aResult.setEndTime(new Date());
			aResult.setStatus(ApiRunStatus.COMPLETE.name());
			aResult.setFail(aResult.getTotal() - aResult.getSuccess());
			aResult.setMsg(message.length() > 2048 ? message.substring(0, 2048) : message);
			apiResultService.update(aResult);
		} finally {
			if(httpClientManager != null){
				httpClientManager.close();
			}
		}
	}
	
	private String setAuthor(AAccount aAccount, String url, String version, String channel, String type) throws Exception{
		ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
		apiApplication.add(aAccount.getId());
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		return sendMessage(apiSendMessage, url, aAccount, version, channel, type);
	}
	
	private String sendMessage(IApiSendMessage apiSendMessage, String url, AAccount aAccount, String version, String channel, String type) throws Exception{
		String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
		logger.info("[登录权限][" + type + "]==>[POST:" + url + userLogin + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data + "]");
		String result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url + userLogin, data, "", channel, version, true);
		Login login = apiSendMessage.json2JavaBean(Login.class, result);
		if(login != null){
			logger.info("[登录权限][" + type + "]==>" + login.toString());
			if("200".equals(login.getCode())){
				String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[登录权限][" + type + "]==>[POST:" + url + usersAccessToken + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data2 + "]");
				result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url + usersAccessToken, data2, "", channel, version, true);
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				if(accessToken != null){
					logger.info("[登录权限][" + type + "]==>" + accessToken.toString());
					if("200".equals(accessToken.getCode())){
						return accessToken.getData().getAccessToken();
					}else{
						throw new BusinessException("[登录权限][" + type + "]==>AccessToken失败！[" + accessToken.getMessage() + "][" + data2 + "][" + data + "]");
					}
				}else{
					throw new BusinessException("[登录权限][" + type + "]==>请求AccessToken失败！[" + url + GlobalValueConfig.getConfig("uri.user.accessToken") + "][" + data2 + "][" + data + "]");
				}
			}else{
				throw new BusinessException("[登录权限][" + type + "]==>登录失败！[" + login.getMessage() + "][" + data + "]");
			}
		}else{
			throw new BusinessException("[登录权限][" + type + "]==>请求登录失败！[" + url + GlobalValueConfig.getConfig("uri.user.login") + "][" + data + "]");
		}
	}
	
}
