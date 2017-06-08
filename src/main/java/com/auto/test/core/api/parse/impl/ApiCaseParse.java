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
		cachedThreadPool = ThreadPool.getInstance();
		urlA = GlobalValueConfig.getConfig("uri.production.environment");
		urlB = GlobalValueConfig.getConfig("uri.advance.environment");
		userLogin = GlobalValueConfig.getConfig("uri.user.login");
		usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
	}

	@Override
	public void execute(ApiContext apiContext) {
		try {
			List<ACase> list = apiContext.getList();
			if(list != null && !list.isEmpty()){
				String authorA = "";
				String authorB = "";
				String version = apiContext.getVersion().getVersion();
				String channels = apiContext.getVersion().getChannel();
				for (String channel : channels.split(",")) {
					setAuthor(apiContext.getAccount(), version, channel, authorA, authorB);
					for (ACase aCase : list) {
						ApiExecuteRun apiExecuteRun = new ApiExecuteRun(apiContext, aCase, urlA, urlB, authorA, authorB, version, channel);
						cachedThreadPool.execute(apiExecuteRun);
					}
				}
			}
		} catch (Exception e) {
			ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
			apiApplication.remove(apiContext.getAccount().getId());
			IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
			AResult aResult = apiContext.getResult();
			aResult.setEndTime(new Date());
			aResult.setStatus(ApiRunStatus.COMPLETE.name());
			aResult.setFail(aResult.getTotal() - aResult.getSuccess());
			aResult.setMsg(e.getMessage().length() > 2048 ? e.getMessage().substring(0, 2048) : e.getMessage());
			apiResultService.update(aResult);
			throw e;
		}
	}
	
	private void setAuthor(AAccount aAccount, String version, String channel, String authorA, String authorB){
		if(aAccount != null){
			ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
			apiApplication.add(aAccount.getId());
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			authorA = sendMessage(apiSendMessage, urlA, aAccount, version, channel);
			logger.info("[AuthorA:" + authorA + "]");
			authorB = sendMessage(apiSendMessage, urlB, aAccount, version, channel);
			logger.info("[AuthorB:" + authorB + "]");
		}
	}
	
	private String sendMessage(IApiSendMessage apiSendMessage, String url, AAccount aAccount, String version, String channel){
		String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
		logger.info("[Author][POST:" + url + userLogin + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data + "]");
		String result = apiSendMessage.sendPost(url + userLogin, data, "", channel, version);
		Login login = apiSendMessage.json2JavaBean(Login.class, result);
		if(login != null){
			logger.info("[Author]" + login.toString());
			if("200".equals(login.getCode())){
				String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[Author][POST:" + url + usersAccessToken + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data2 + "]");
				result = apiSendMessage.sendPost(url + usersAccessToken, data2, "", channel, version);
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				if(accessToken != null){
					logger.info("[Author]" + accessToken.toString());
					if("200".equals(accessToken.getCode())){
						return accessToken.getData().getAccessToken();
					}else{
						throw new BusinessException(accessToken.getMessage());
					}
				}else{
					throw new BusinessException("[Author]获取AccessToken失败！[" + url + GlobalValueConfig.getConfig("uri.user.accessToken") + "][" + data2 + "][" + data + "]");
				}
			}else{
				throw new BusinessException(login.getMessage());
			}
		}else{
			throw new BusinessException("[Author]登录失败！[" + url + GlobalValueConfig.getConfig("uri.user.login") + "][" + data + "]");
		}
	}
	
}
