package com.auto.test.core.api.parse.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.config.GlobalValueConfig;
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
import com.auto.test.entity.AVersion;

public class ApiCaseParse implements IApiCaseParse {
	private static final Logger logger = LoggerFactory.getLogger(ApiCaseParse.class);
	private ExecutorService cachedThreadPool = null;
	
	public ApiCaseParse(){
		cachedThreadPool = ThreadPool.getInstance();
	}

	@Override
	public void execute(ApiContext apiContext) {
		try {
			List<ACase> list = apiContext.getList();
			if(list != null && !list.isEmpty()){
				setAuthor(apiContext);
				for (ACase aCase : list) {
					ApiExecuteRun apiExecuteRun = new ApiExecuteRun(aCase, apiContext);
					cachedThreadPool.execute(apiExecuteRun);
				}
			}
		} catch (Exception e) {
			ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
			apiApplication.remove(apiContext.getaAccount().getId());
			throw e;
		}
	}
	
	private void setAuthor(ApiContext apiContext){
		AVersion aVersion = apiContext.getaVersion();
		AAccount aAccount = apiContext.getaAccount();
		if(aAccount != null){
			ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
			apiApplication.add(aAccount.getId());
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			String accessTokena = sendMessage(apiSendMessage, apiContext.getUrla(), aAccount, aVersion);
			if(accessTokena != null && !accessTokena.isEmpty()){
				logger.info("[Authora:" + accessTokena + "]");
				apiContext.setAuthora(accessTokena);
			}
			String accessTokenb = sendMessage(apiSendMessage, apiContext.getUrlb(), aAccount, aVersion);
			if(accessTokenb != null && !accessTokenb.isEmpty()){
				logger.info("[Authorb:" + accessTokenb + "]");
				apiContext.setAuthorb(accessTokenb);
			}
		}
	}
	
	private String sendMessage(IApiSendMessage apiSendMessage, String url, AAccount aAccount, AVersion aVersion){
		String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
		logger.info("[Author][POST:" + url + GlobalValueConfig.getConfig("uri.user.login") + "],[Channel:" + aVersion.getChannel() + "],[Version:" + aVersion.getVersion() + "],[Data:" + data + "]");
		String result = apiSendMessage.sendPost(url + GlobalValueConfig.getConfig("uri.user.login"), data, "", aVersion.getChannel(), aVersion.getVersion());
		Login login = apiSendMessage.json2JavaBean(Login.class, result);
		if(login != null){
			logger.info("[Author]" + login.toString());
			if("200".equals(login.getCode())){
				String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[Author][POST:" + url + GlobalValueConfig.getConfig("uri.user.accessToken") + "],[Channel:" + aVersion.getChannel() + "],[Version:" + aVersion.getVersion() + "],[Data:" + data2 + "]");
				result = apiSendMessage.sendPost(url + GlobalValueConfig.getConfig("uri.user.accessToken"), data2, "", aVersion.getChannel(), aVersion.getVersion());
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				if(accessToken != null){
					logger.info("[Author]" + accessToken.toString());
					if("200".equals(accessToken.getCode())){
						return accessToken.getData().getAccessToken();
					}else{
						throw new BusinessException(accessToken.getMessage());
					}
				}else{
					throw new BusinessException("[Author]获取AccessToken失败！[" + data + "][" + data2 + "]");
				}
			}else{
				throw new BusinessException(login.getMessage());
			}
		}else{
			throw new BusinessException("[Author]登录失败！[" + data + "]");
		}
	}
	
}
