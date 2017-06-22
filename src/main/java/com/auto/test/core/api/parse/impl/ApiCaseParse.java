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
import com.auto.test.common.context.ThreadPool;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.execute.ApiExecuteRun;
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
	private String urlA = null;
	private String urlB = null;
	private String userLogin = null;
	private String usersAccessToken = null;
	
	public ApiCaseParse(){
		super();
		cachedThreadPool = ThreadPool.getInstance();
		urlA = GlobalValueConfig.getConfig("uri.production.environment");
		urlB = GlobalValueConfig.getConfig("uri.advance.environment");
		userLogin = GlobalValueConfig.getConfig("uri.user.login");
		usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
	}

	@Override
	public void execute(ApiContext apiContext) throws Exception{
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
				String authorA = null;
				if(apiContext.isBool()){
					authorA = setAuthor(apiContext.getAccount(), urlA, version, channel, "A");
					logger.info("[Author][A]==>[" + authorA + "]");
				}
				String authorB = setAuthor(apiContext.getAccount(), urlB, version, channel, "B");
				logger.info("[Author][B]==>[" + authorB + "]");
				for (ACase aCase : list) {
					if(new Integer(1).equals(aCase.getRun())){
						ApiExecuteRun apiExecuteRun = new ApiExecuteRun(apiContext, aCase, urlA, urlB, authorA, authorB, version, channel);
						cachedThreadPool.execute(apiExecuteRun);
					}
				}
			}
		}
	}
	
	private void executeFinal(ApiContext apiContext, String message) throws Exception{
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
	}
	
	private String setAuthor(AAccount aAccount, String url, String version, String channel, String type) throws Exception{
		if(aAccount != null){
			ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
			apiApplication.add(aAccount.getId());
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			return sendMessage(apiSendMessage, url, aAccount, version, channel, type);
		}
		return "";
	}
	
	private String sendMessage(IApiSendMessage apiSendMessage, String url, AAccount aAccount, String version, String channel, String type) throws Exception{
		String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
		logger.info("[Author][" + type + "]==>[POST:" + url + userLogin + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data + "]");
		String result = apiSendMessage.sendPost(url + userLogin, data, "", channel, version);
		Login login = apiSendMessage.json2JavaBean(Login.class, result);
		if(login != null){
			logger.info("[Author][" + type + "]==>" + login.toString());
			if("200".equals(login.getCode())){
				String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[Author][" + type + "]==>[POST:" + url + usersAccessToken + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data2 + "]");
				result = apiSendMessage.sendPost(url + usersAccessToken, data2, "", channel, version);
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				if(accessToken != null){
					logger.info("[Author][" + type + "]==>" + accessToken.toString());
					if("200".equals(accessToken.getCode())){
						return accessToken.getData().getAccessToken();
					}else{
						throw new BusinessException("[Author][" + type + "]==>" + accessToken.getMessage());
					}
				}else{
					throw new BusinessException("[Author][" + type + "]==>获取AccessToken失败！[" + url + GlobalValueConfig.getConfig("uri.user.accessToken") + "][" + data2 + "][" + data + "]");
				}
			}else{
				throw new BusinessException("[Author][" + type + "]==>" + login.getMessage());
			}
		}else{
			throw new BusinessException("[Author][" + type + "]==>登录失败！[" + url + GlobalValueConfig.getConfig("uri.user.login") + "][" + data + "]");
		}
	}
	
}
