package com.auto.test.core.api.parse.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.Const;
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
import com.auto.test.utils.CheckUtil;
import com.auto.test.utils.EmailUtil;
import com.auto.test.utils.ReadyUtil;

public class ApiCaseParse implements IApiCaseParse {
	private static final Logger logger = LoggerFactory.getLogger(ApiCaseParse.class);
	private ExecutorService cachedThreadPool = null;
	private HttpClientManager httpClientManagerA = null;
	private HttpClientManager httpClientManagerB = null;
	private String loginUrl = null;
	private String url = null;
	private String userLogin = null;
	private String usersAccessToken = null;
	private String projectRootPath = null;
	private String nologinResult = null;
	private String onceResult = null;
	private String gameStatus = null;
	private String gameBetting = null;
	private String gameResult = null;
	private String gameProject = null;
	private Integer gameTimeout = null;
	
	public ApiCaseParse(){
		super();
		this.cachedThreadPool = ApiThreadPool.getInstance();
		this.userLogin = GlobalValueConfig.getConfig("uri.user.login");
		this.usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
		this.projectRootPath = GlobalValueConfig.getConfig("uri.project.path");
		this.nologinResult = GlobalValueConfig.getConfig("api.nologin.result");
		this.onceResult = GlobalValueConfig.getConfig("api.once.result");
		this.loginUrl = GlobalValueConfig.getConfig("url.login.uic");
		this.gameStatus = GlobalValueConfig.getConfig("game.timeout.status");
		this.gameBetting = GlobalValueConfig.getConfig("game.timeout.betting");
		this.gameResult = GlobalValueConfig.getConfig("game.timeout.result");
		this.gameProject = GlobalValueConfig.getConfig("game.timeout.project");
		String gameTime = GlobalValueConfig.getConfig("game.timeout.time");
		this.gameTimeout = (gameTime == null || gameTime.isEmpty()) ? 0 : Integer.parseInt(gameTime);
	}

	@Override
	public void execute(ApiContext apiContext) throws Exception{
		if(new Integer(1).equals(apiContext.getPlatform())){
			String testIp = GlobalValueConfig.getConfig("host.test.ip");
			new CheckUtil().checkIP(testIp, "测试环境");
			logger.info("[运行环境]==>测试环境");
		}else if(new Integer(2).equals(apiContext.getPlatform())){
			String previewIp = GlobalValueConfig.getConfig("host.preview.ip");
			new CheckUtil().checkIP(previewIp, "预发环境");
			logger.info("[运行环境]==>预发环境");
		}else if(new Integer(3).equals(apiContext.getPlatform())){
			logger.info("[运行环境]==>线上环境");
		}else{
			throw new BusinessException("运行环境[type=" + apiContext.getPlatform() + "]不存在！");
		}
		this.httpClientManagerB = new HttpClientManager(apiContext.getPlatform());
		if(apiContext.isCompare()){
			this.httpClientManagerA = new HttpClientManager(3);
		}
		try {
			this.url = Const.API_HTTPS + apiContext.getProject().getServer();
			executeBody(apiContext);
		} catch (Exception e) {
			String message = e.getMessage() == null ? "Error" : e.getMessage();
			executeFinal(apiContext, message);
			logger.error(message);
			throw e;
		}
	}
	
	private void executeBody(ApiContext apiContext) throws Exception{
		List<ACase> list = apiContext.getList();
		if(list != null && !list.isEmpty()){
			String version = apiContext.getVersion().getVersion();
			String channels = apiContext.getVersion().getChannel();
			if(apiContext.isCompare()){
				String authorA = null;//线上
				String authorB = null;//预发
				if(apiContext.getAccount() != null){
					if("1".equals(apiContext.getAccount().getToken())){
						String loginname = apiContext.getAccount().getLoginname();
						if("游客登录".equals(loginname)){
							//authorA = visitorLogin(httpClientManagerA);
							authorB = visitorLogin(httpClientManagerB);
							authorA = authorB;
						}else{
							String token = apiContext.getAccount().getPassword();
							/*if(token.contains(",")){
								authorA = token.split(",")[0];
								authorB = token.split(",")[1];
							}else{
								throw new BusinessException("TOKEN[线上,预发]格式错误！");
							}*/
							if(!token.contains(",")){
								authorA = token;
								authorB = token;
							}else{
								throw new BusinessException("TOKEN[线上/预发]格式错误！");
							}
						}
					}else{
						//authorA = setAuthor(httpClientManagerA, apiContext.getAccount(), loginUrl, version, channels.split(",")[0], "线上");
						authorB = setAuthor(httpClientManagerB, apiContext.getAccount(), loginUrl, version, channels.split(",")[0], "预发");
						authorA = authorB;
					}
				}
				for (String channel : channels.split(",")) {
					for (ACase aCase : list) {
						if(new Integer(1).equals(aCase.getRun())){
							ApiExecuteRun apiExecuteRun = new ApiExecuteRun(httpClientManagerA, httpClientManagerB, apiContext, aCase, url, authorA, authorB, version, channel,
									projectRootPath, nologinResult, onceResult, gameStatus, gameBetting, gameResult, gameProject, gameTimeout);
							cachedThreadPool.execute(apiExecuteRun);
						}
					}
				}
			}else{
				String memo = "";
				if(new Integer(1).equals(apiContext.getPlatform())){
					memo = "测试";
				}else if(new Integer(2).equals(apiContext.getPlatform())){
					memo = "预发";
				}else if(new Integer(3).equals(apiContext.getPlatform())){
					memo = "线上";
				}else{
					throw new BusinessException("测试环境未知！");
				}
				String author = null;
				if(apiContext.getAccount() != null){
					if("1".equals(apiContext.getAccount().getToken())){
						String loginname = apiContext.getAccount().getLoginname();
						if("游客登录".equals(loginname)){
							author = visitorLogin(httpClientManagerB);
						}else{
							String token = apiContext.getAccount().getPassword();
							if(token.contains(",")){
								throw new BusinessException("TOKEN[" + memo + "]格式错误！");
							}
							author = token;
						}
					}else{
						author = setAuthor(httpClientManagerB, apiContext.getAccount(), loginUrl, version, channels.split(",")[0], memo);
					}
				}
				for (String channel : channels.split(",")) {
					for (ACase aCase : list) {
						if(new Integer(1).equals(aCase.getRun())){
							ApiExecuteRun apiExecuteRun = new ApiExecuteRun(null, httpClientManagerB, apiContext, aCase, url, null, author, version, channel,
									projectRootPath, nologinResult, onceResult, gameStatus, gameBetting, gameResult, gameProject, gameTimeout);
							cachedThreadPool.execute(apiExecuteRun);
						}
					}
				}
			}
		}
	}
	
	private void executeFinal(ApiContext apiContext, String message) throws Exception{
		AResult aResult = apiContext.getResult();
		try {
			if(apiContext.getAccount() != null){
				ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
				apiApplication.remove(apiContext.getAccount().getId());
			}
			IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
			aResult.setEndTime(new Date());
			aResult.setStatus(ApiRunStatus.COMPLETE.name());
			aResult.setFail(aResult.getTotal() - aResult.getSuccess());
			aResult.setMsg(message.length() > 2048 ? message.substring(0, 2048) : message);
			apiResultService.update(aResult);
		} finally {
			if(httpClientManagerA != null){
				httpClientManagerA.close();
			}
			if(httpClientManagerB != null){
				httpClientManagerB.close();
			}
			if(apiContext.isMail() && aResult.getFail() > 0){
				apiContext.getResult().setFailMsg(message);
				new EmailUtil().sendEmail(aResult, apiContext.getEmails());
			}
		}
	}
	
	private String setAuthor(HttpClientManager httpClientManager, AAccount aAccount, String url, String version, String channel, String type) throws Exception{
		ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
		apiApplication.add(aAccount.getId());
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		return sendMessage(httpClientManager, apiSendMessage, url, aAccount, version, channel, type);
	}
	
	private String sendMessage(HttpClientManager httpClientManager, IApiSendMessage apiSendMessage, String url, AAccount aAccount, String version, String channel, String type) throws Exception{
		String data = "{\"username\":\"" + aAccount.getLoginname() + "\",\"password\":\"" + aAccount.getPassword() + "\"}";
		logger.info("[登录权限][" + type + "]==>[POST:" + url + userLogin + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data + "]");
		String result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url + userLogin, data, "", channel, version, true, null);
		Login login = apiSendMessage.json2JavaBean(Login.class, result);
		if(login != null){
			logger.info("[登录权限][" + type + "]==>" + login.toString());
			if("200".equals(login.getCode())){
				String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
				logger.info("[登录权限][" + type + "]==>[POST:" + url + usersAccessToken + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + data2 + "]");
				result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url + usersAccessToken, data2, "", channel, version, true, null);
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
	
	private String visitorLogin(HttpClientManager httpClientManager) throws Exception{
		String t = new ReadyUtil().getVisitorToken(httpClientManager);
		if(t == null || t.isEmpty()){
			throw new BusinessException("[游客登录]登录失败！");
		}
		return t;
	}
	
}
