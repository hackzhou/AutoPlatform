package com.auto.test.core.api.parse.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.context.ThreadPool;
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
		List<ACase> list = apiContext.getList();
		if(list != null && !list.isEmpty()){
			setAuthor(apiContext);
			for (ACase aCase : list) {
				ApiExecuteRun apiExecuteRun = new ApiExecuteRun(aCase, apiContext);
				cachedThreadPool.execute(apiExecuteRun);
			}
		}
	}
	
	private void setAuthor(ApiContext apiContext){
		AVersion aVersion = apiContext.getaVersion();
		AAccount aAccount = apiContext.getaAccount();
		if(aAccount != null){
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
			String result = apiSendMessage.sendPost(apiContext.getUrla() + "/api/user/login", data, "", aVersion.getChannel(), aVersion.getVersion());
			logger.info("[POST] " + apiContext.getUrla() + "/api/user/login,Channel:" + aVersion.getChannel() + ",Version:" + aVersion.getVersion());
			Login login = apiSendMessage.json2JavaBean(Login.class, result);
			logger.info(login.toString());
			if(login != null && "200".equals(login.getCode())){
				data = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[POST] " + apiContext.getUrla() + "/api/user/accessToken,Channel:" + aVersion.getChannel() + ",Version:" + aVersion.getVersion() + ",Data:" + data);
				result = apiSendMessage.sendPost(apiContext.getUrla() + "/api/user/accessToken", data, "", aVersion.getChannel(), aVersion.getVersion());
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				logger.info(accessToken.toString());
				if(accessToken != null && "200".equals(accessToken.getCode())){
					apiContext.setAuthora(accessToken.getData().getAccessToken());
				}
			}
			result = apiSendMessage.sendPost(apiContext.getUrlb() + "/api/user/login", data, "", aVersion.getChannel(), aVersion.getVersion());
			logger.info("[POST] " + apiContext.getUrlb() + "/api/user/login,Channel:" + aVersion.getChannel() + ",Version:" + aVersion.getVersion());
			login = apiSendMessage.json2JavaBean(Login.class, result);
			logger.info(login.toString());
			if(login != null && "200".equals(login.getCode())){
				data = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[POST] " + apiContext.getUrlb() + "/api/user/accessToken,Channel:" + aVersion.getChannel() + ",Version:" + aVersion.getVersion() + ",Data:" + data);
				result = apiSendMessage.sendPost(apiContext.getUrlb() + "/api/user/accessToken", data, "", aVersion.getChannel(), aVersion.getVersion());
				AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
				logger.info(accessToken.toString());
				if(accessToken != null && "200".equals(accessToken.getCode())){
					apiContext.setAuthorb(accessToken.getData().getAccessToken());
				}
			}
		}
	}
	
}
