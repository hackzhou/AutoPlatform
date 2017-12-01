package com.auto.test.core.api.execute;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.bean.AData;
import com.auto.test.common.bean.AResultFail;
import com.auto.test.common.bean.ARunTime;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.ApiStatus;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.context.ApiApplication;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.compare.JSONCompare;
import com.auto.test.core.api.compare.JSONVar;
import com.auto.test.core.api.http.HttpClientManager;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.core.api.ready.ReadyData;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiResultDetailService;
import com.auto.test.service.IApiResultService;
import com.auto.test.utils.DateUtil;
import com.auto.test.utils.EmailUtil;

public class ApiExecuteRun implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ApiExecuteRun.class);
	private HttpClientManager httpClientManagerA = null;
	private HttpClientManager httpClientManagerB = null;
	private ApiContext apiContext = null;
	private ACase aCase = null;
	private String url = null;
	private String authorA = null;
	private String authorB = null;
	private String version = null;
	private String channel = null;
	private String projectRootPath = null;
	private String nologinResult = null;
	private String onceResult = null;
	private String gameStatus = null;
	private String gameBetting = null;
	private String gameResult = null;
	private String gameProject = null;
	private Integer gameTimeout = null;

	public ApiExecuteRun(HttpClientManager httpClientManagerA, HttpClientManager httpClientManagerB, ApiContext apiContext, ACase aCase, String url, 
			String authorA, String authorB, String version, String channel, String projectRootPath, String nologinResult, String onceResult, 
			String gameStatus, String gameBetting, String gameResult, String gameProject, Integer gameTimeout) {
		super();
		this.httpClientManagerA = httpClientManagerA;
		this.httpClientManagerB = httpClientManagerB;
		this.apiContext = apiContext;
		this.aCase = aCase;
		this.url = url;
		this.authorA = authorA;
		this.authorB = authorB;
		this.version = version;
		this.channel = channel;
		this.projectRootPath = projectRootPath;
		this.nologinResult = nologinResult;
		this.onceResult = onceResult;
		this.gameStatus = gameStatus;
		this.gameBetting = gameBetting;
		this.gameResult = gameResult;
		this.gameProject = gameProject;
		this.gameTimeout = gameTimeout;
	}
	
	@Override
	public void run() {
		try {
			AResultDetail resultDetail = new AResultDetail();
			oneRunBody(aCase, resultDetail);
			List<ACase> list = aCase.getList();
			if(list != null && !list.isEmpty()){
				if(Arrays.asList(gameProject.split(",")).contains(apiContext.getProject().getPath())){
					ACase statusCase = null;
					if(Arrays.asList(gameStatus.split(",")).contains(aCase.getInterfaceo().getUrl())){
						statusCase = aCase;
					}else{
						throw new BusinessException("[案例][关联案例的起始案例必须为游戏状态！]");
					}
					AData aData = new AData(resultDetail.getResultb());
					AResultDetail ard = new AResultDetail();
					for (ACase aCase : list) {
						String varBody = aCase.getBody();
						aCase.setBody(new JSONVar().replaceBody(aCase.getBody(), aData.getResult()));
						if(Arrays.asList(gameBetting.split(",")).contains(aCase.getInterfaceo().getUrl()) || 
								Arrays.asList(gameResult.split(",")).contains(aCase.getInterfaceo().getUrl())){
							testPass(aCase, statusCase, varBody, aData);
						}
						oneRunBody(aCase, ard);
						if(Arrays.asList(gameStatus.split(",")).contains(ard.getUrl())){
							statusCase = aCase;
							aData.setResult(ard.getResultb());
						}
					}
				}else{
					for (ACase aCase : list) {
						aCase.setBody(new JSONVar().replaceBody(aCase.getBody(), resultDetail.getResultb()));
						oneRunBody(aCase, new AResultDetail());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}
	
	private void testPass(ACase aCase, ACase statusCase, String varBody, AData aData) throws Exception{
		AResultDetail rd = null;
		for (int j = 0; j < gameTimeout; j++) {
			rd = new AResultDetail();
			oneRunBodyTimeout(aCase, rd);
			if(rd.getResultb() != null){
				JSONObject obj = JSON.parseObject(rd.getResultb());
				if(new Integer(200).equals(obj.get("code")) || "200".equals(obj.get("code")) || "游戏已关服".equals(obj.get("message"))){
					break;
				}else if("期数错误".equals(obj.get("message")) || "期次号错误".equals(obj.get("message"))){
					AResultDetail srd = new AResultDetail();
					oneRunBodyTimeout(statusCase, srd);
					aCase.setBody(new JSONVar().replaceBody(varBody, srd.getResultb()));
					aData.setResult(srd.getResultb());
				}
				Thread.sleep(1000);
			}
		}
	}
	
	private void oneRunBody(ACase aCase, AResultDetail aResultDetail) throws Exception{
		try {
			oneRunBodyTimeout(aCase, aResultDetail);
			saveResultDetailSuccess(aCase, aResultDetail);
		} catch (Exception e) {
			saveResultDetailFail(aCase, aResultDetail, e.getMessage());
		} finally {
			runFinal(aResultDetail);
		}
	}
	
	private void oneRunBodyTimeout(ACase aCase, AResultDetail aResultDetail) throws Exception{
		aCase.setBody(new JSONVar().replaceBodyVar(aCase.getBody(), authorB));
		if(aCase.getReady() != null && aCase.getReady() > 0 && apiContext.getAccount() != null){
			if(apiContext.getDbUser() == 0){
				apiContext.setDbUser(ReadyData.getUserID(apiContext.getAccount().getLoginname()));
			}
			ReadyData.exe(aCase.getReady(), apiContext.getDbUser(), apiContext.getAccount().getLoginname(), aCase.getBody());
		}
		sendMessage(aCase, aResultDetail);
	}
	
	private void saveResultDetailSuccess(ACase aCase, AResultDetail aResultDetail){
		aResultDetail.update(aCase);
		aResultDetail.setVersion(version);
		aResultDetail.setChannel(channel);
		aResultDetail.setResulto(apiContext.getResult());
		if(apiContext.getAccount() != null){
			aResultDetail.setAccount(apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword());
		}
		IApiResultDetailService apiResultDetailService = (IApiResultDetailService) SpringContext.getBean("apiResultDetailService");
		String[] ignore = null;
		if(aCase.getStrategy() != null && !aCase.getStrategy().isEmpty()){
			ignore = aCase.getStrategy().split(",");
		}
		JSONCompare jsonCompare = (JSONCompare) SpringContext.getBean("jsonCompare");
		aResultDetail.setResulta(jsonCompare.sortJson(aResultDetail.getResulta()));
		aResultDetail.setResultb(jsonCompare.sortJson(aResultDetail.getResultb()));
		if(jsonCompare.compareJson(aResultDetail.getResulta(), aResultDetail.getResultb(), ignore)){
			aResultDetail.setStatus(ApiStatus.SUCCESS.name());
		}else{
			aResultDetail.setStatus(ApiStatus.FAILURE.name());
		}
		setRequest(aResultDetail);
		apiResultDetailService.create(aResultDetail);
		addReportResultFail(aResultDetail, "结果对比失败");
		addReportResultTimeout(aResultDetail);
	}
	
	private void saveResultDetailFail(ACase aCase, AResultDetail aResultDetail, String message){
		aResultDetail.update(aCase);
		aResultDetail.setVersion(version);
		aResultDetail.setChannel(channel);
		aResultDetail.setResulto(apiContext.getResult());
		if(apiContext.getAccount() != null){
			aResultDetail.setAccount(apiContext.getAccount().getLoginname() + "/" + apiContext.getAccount().getPassword());
		}
		aResultDetail.setStatus(ApiStatus.FAILURE.name());
		aResultDetail.setMsg(message.length() > 2048 ? message.substring(0, 2048) : message);
		IApiResultDetailService apiResultDetailService = (IApiResultDetailService) SpringContext.getBean("apiResultDetailService");
		setRequest(aResultDetail);
		apiResultDetailService.create(aResultDetail);
		addReportResultFail(aResultDetail, message.split("-->")[0]);
		addReportResultTimeout(aResultDetail);
	}
	
	private void addReportResultFail(AResultDetail aResultDetail, String message){
		if(ApiStatus.FAILURE.name().equals(aResultDetail.getStatus())){
			AResultFail resultFail = new AResultFail(aResultDetail);
			resultFail.setMessage(message);
			apiContext.getResult().getFails().add(resultFail);
		}
	}
	
	private void addReportResultTimeout(AResultDetail aResultDetail){
		if(aResultDetail.getTime() > 300){
			AResultFail resultTimeout = new AResultFail(aResultDetail);
			if(ApiStatus.FAILURE.name().equals(aResultDetail.getStatus())){
				resultTimeout.setMessage(aResultDetail.getTime() + " 毫秒(响应超时)[失败]");
			}else{
				resultTimeout.setMessage(aResultDetail.getTime() + " 毫秒(响应超时)");
			}
			apiContext.getResult().getTimeouts().add(resultTimeout);
		}
	}
	
	private void setRequest(AResultDetail aResultDetail){
		String url = "http://" + apiContext.getProject().getServer() + apiContext.getProject().getPath() + aResultDetail.getUrl();
		String memo = url + ";Authorization:" + authorB + ";App-Version:" + aResultDetail.getVersion() + ";App-Channel:" + aResultDetail.getChannel()+ ";Body:" + aResultDetail.getBody();
		aResultDetail.setMemo(memo);
	}
	
	private void runFinal(AResultDetail aResultDetail) throws Exception{
		apiContext.countPlus();
		AResult aResult = apiContext.getResult();
		if(aResultDetail != null && ApiStatus.SUCCESS.name().equals(aResultDetail.getStatus())){
			apiContext.successPlus();
		}
		if(apiContext.getCount().equals(apiContext.getTotal())){
			try {
				if(apiContext.getAccount() != null){
					ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
					apiApplication.remove(apiContext.getAccount().getId());
				}
				IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
				aResult.setEndTime(new Date());
				aResult.setStatus(ApiRunStatus.COMPLETE.name());
				aResult.setFail(aResult.getTotal() - aResult.getSuccess());
				apiResultService.update(aResult);
			} finally {
				if(httpClientManagerB != null){
					httpClientManagerB.close();
				}
				if(apiContext.isMail() && aResult.getFail() > 0){
					new EmailUtil().sendEmail(aResult, apiContext.getEmails());
				}
			}
		}
		if(aResultDetail != null && aResultDetail.getCaseo() != null){
			logger.info("[主体运行][" + aResultDetail.getCaseo().getId() + "]==>" + aResultDetail.toString());
		}
	}
	
	private void sendMessage(ACase aCase, AResultDetail aResultDetail) throws Exception{
		ARunTime time = new ARunTime();
		try {
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			if(HttpType.GET.name().equals(aCase.getInterfaceo().getType())){
				if(apiContext.isCompare()){
					aResultDetail.setResulta(sendMessageGet(httpClientManagerA, apiSendMessage, getFullUrl(aCase, url, null), authorA, version, channel, aCase.getId(), null));
				}else{
					addResulta(aCase, aResultDetail);
				}
				aResultDetail.setResultb(sendMessageGet(httpClientManagerB, apiSendMessage, getFullUrl(aCase, url, null), authorB, version, channel, aCase.getId(), time));
			}else if(HttpType.POST.name().equals(aCase.getInterfaceo().getType())){
				if(apiContext.isCompare()){
					aResultDetail.setResulta(sendMessagePost(httpClientManagerA, apiSendMessage, getFullUrl(aCase, url, aCase.getBody()), aCase.getBody(), 
							authorA, version, channel, aCase.getId(), aCase.getImg(), null));
				}else{
					addResulta(aCase, aResultDetail);
				}
				aResultDetail.setResultb(sendMessagePost(httpClientManagerB, apiSendMessage, getFullUrl(aCase, url, aCase.getBody()), aCase.getBody(), 
						authorB, version, channel, aCase.getId(), aCase.getImg(), time));
			}
		} finally {
			if(time.getTime() == null){
				aResultDetail.setTime(-1);
			}else{
				aResultDetail.setTime(time.getTime());
			}
		}
	}
	
	private void addResulta(ACase aCase, AResultDetail aResultDetail) throws Exception{
		if(apiContext.getAccount() == null && new Integer(1).equals(aCase.getLogin())){
			aResultDetail.setResulta(nologinResult);
		}else{
			if(aCase.getOnce() != null && !aCase.getOnce().isEmpty()){
				IApiCaseService apiCaseService = (IApiCaseService) SpringContext.getBean("apiCaseService");
				String now = DateUtil.getFormatDate();
				if(now.equals(aCase.getOnce().split("_")[0])){
					int once = 1;
					if(aCase.getOnce().contains("_")){
						once = Integer.parseInt(aCase.getOnce().split("_")[1]) + 1;
					}
					aCase.setOnce(now + "_" + once);
					apiCaseService.update(aCase);
					apiCaseService.evict(aCase);
					if("/api/app/ranking/clear".equals(aCase.getInterfaceo().getUrl())){
						if(once < 4){
							aResultDetail.setResulta(onceResult);
							aCase.setStrategy("data");
						}else{
							aResultDetail.setResulta(aCase.getResult());
						}
					}else{
						aResultDetail.setResulta(aCase.getResult());
					}
				}else{
					aResultDetail.setResulta(onceResult);
					aCase.setOnce(now + "_1");
					apiCaseService.update(aCase);
					apiCaseService.evict(aCase);
					aCase.setStrategy("data");
				}
			}else{
				aResultDetail.setResulta(aCase.getResult());
			}
		}
	}
	
	private String sendMessageGet(HttpClientManager httpClientManager, IApiSendMessage apiSendMessage, String url, String author, 
			String version, String channel, Integer runid, ARunTime time) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[GET:" + url  + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "]");
		String result = apiSendMessage.sendGet(httpClientManagerB.getHttpClient(), url, author, channel, version, time);
		logger.info("[主体运行][" + runid + "]==>" + result);
		return result;
	}
	
	private String sendMessagePost(HttpClientManager httpClientManager, IApiSendMessage apiSendMessage, String url, String body, String author, 
			String version, String channel, Integer runid, String img, ARunTime time) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[POST:" + url + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + body + "]");
		String result = "";
		if(img != null && !img.isEmpty()){
			File file = new File(img);
			if(file.exists() && file.isFile()){
				result = apiSendMessage.sendPost(httpClientManagerB.getHttpClient(), url, body, author, channel, version, false, file, time);
			}else{
				logger.error("[案例][" + runid + "]==>文件不存在[" + img + "]");
				throw new BusinessException("[案例][" + runid + "]==>文件不存在[" + img + "]");
			}
		}else{
			result = apiSendMessage.sendPost(httpClientManagerB.getHttpClient(), url, body, author, channel, version, false, time);
		}
		logger.info("[主体运行][" + runid + "]==>" + result);
		return result;
	}
	
	private String getFullUrl(ACase aCase, String url, String body) throws Exception{
		String iUrl = aCase.getInterfaceo().getUrl();
		String fullUrl = fillVariable(aCase, iUrl, body);
		if(fullUrl != null){
			iUrl = fullUrl;
		}
		String desc = aCase.getInterfaceo().getDescription();
		if(projectRootPath != null && !projectRootPath.isEmpty()){
			for (String pPath : projectRootPath.split(",")) {
				if(desc != null && desc.trim().equalsIgnoreCase(pPath)){
					return url + pPath + iUrl;
				}
			}
		}
		return url + apiContext.getProject().getPath() + iUrl;
	}
	
	public String fillVariable(ACase aCase, String url, String body) throws Exception{
		if(body != null && !body.isEmpty() && url != null && url.contains("{") && url.contains("}") && (url.indexOf("{") < url.indexOf("}"))){
			String tempA = url.substring(url.indexOf("{"), url.indexOf("}") + 1);
			String tempB = url.substring(url.indexOf("{") + 1, url.indexOf("}"));
			JSONObject jsono = JSON.parseObject(body);
			if(jsono != null && jsono.get(tempB) != null){
//				//jsono.remove(tempB);
//				//aCase.setBody(JSON.toJSONString(jsono));
				return url.replace(tempA, String.valueOf(jsono.get(tempB)));
			}
		}
		return null;
	}

	public ACase getaCase() {
		return aCase;
	}
	public void setaCase(ACase aCase) {
		this.aCase = aCase;
	}
	public ApiContext getApiContext() {
		return apiContext;
	}
	public void setApiContext(ApiContext apiContext) {
		this.apiContext = apiContext;
	}
}
