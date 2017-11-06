package com.auto.test.core.api.execute;

import java.io.File;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.auto.test.core.api.http.HttpClientManager;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.core.api.ready.ReadyData;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiResultDetailService;
import com.auto.test.service.IApiResultService;
import com.auto.test.utils.EmailUtil;

public class ApiExecuteRun implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ApiExecuteRun.class);
	private HttpClientManager httpClientManager = null;
	private ApiContext apiContext = null;
	private ACase aCase = null;
	/*private String urlA = null;	//Online Compare*/	
	private String urlB = null;
	/*private String authorA = null;	//Online Compare*/	
	private String authorB = null;
	private String version = null;
	private String channel = null;
	private String projectRootPath = null;
	private String nologinResult = null;

	public ApiExecuteRun(HttpClientManager httpClientManager, ApiContext apiContext, ACase aCase, 
			String urlB, String authorB, String version, String channel, String projectRootPath, String nologinResult) {
		super();
		this.httpClientManager = httpClientManager;
		this.apiContext = apiContext;
		this.aCase = aCase;
		this.urlB = urlB;
		this.authorB = authorB;
		this.version = version;
		this.channel = channel;
		this.projectRootPath = projectRootPath;
		this.nologinResult = nologinResult;
	}
	
	/*public ApiExecuteRun(HttpClientManager httpClientManager, ApiContext apiContext, ACase aCase, 
			String urlA, String urlB, String authorA, String authorB, String version, String channel, String projectRootPath, String nologinResult) {	//Online Compare
		super();
		this.httpClientManager = httpClientManager;
		this.apiContext = apiContext;
		this.aCase = aCase;
		this.urlA = urlA;
		this.urlB = urlB;
		this.authorA = authorA;
		this.authorB = authorB;
		this.version = version;
		this.channel = channel;
		this.projectRootPath = projectRootPath;
		this.nologinResult = nologinResult;
	}*/

	@Override
	public void run() {
		try {
			oneRunBody(aCase, new AResultDetail());
			List<ACase> list = aCase.getList();
			if(list != null && !list.isEmpty()){
				for (ACase aCase : list) {
					oneRunBody(aCase, new AResultDetail());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}
	
	private void oneRunBody(ACase aCase, AResultDetail aResultDetail) throws Exception{
		try {
			if(aCase.getReady() != null && aCase.getReady() > 0){
				ReadyData.exe(aCase.getReady());
			}
			sendMessage(aCase, aResultDetail);
			saveResultDetailSuccess(aCase, aResultDetail);
		} catch (Exception e) {
			saveResultDetailFail(aCase, aResultDetail, e.getMessage());
		} finally {
			runFinal(aResultDetail);
		}
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
		apiResultDetailService.create(aResultDetail);
		addReportResultFail(aResultDetail, "结果对比失败");
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
		apiResultDetailService.create(aResultDetail);
		addReportResultFail(aResultDetail, message.split("-->")[0]);
	}
	
	private void addReportResultFail(AResultDetail aResultDetail, String message){
		if(ApiStatus.FAILURE.name().equals(aResultDetail.getStatus())){
			AResultFail resultFail = new AResultFail(aResultDetail);
			resultFail.setMessage(message);
			apiContext.getResult().getFails().add(resultFail);
		}
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
				if(httpClientManager != null){
					httpClientManager.close();
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
				if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
					if(apiContext.getAccount() == null && new Integer(1).equals(aCase.getLogin())){
						aResultDetail.setResulta(nologinResult);
					}else{
						aResultDetail.setResulta(aCase.getResult());
					}
				}else{
					/*aResultDetail.setResulta(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlA, null), authorA, version, channel, aCase.getId()));	//Online Compare*/			
				}
				aResultDetail.setResultb(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlB, null), authorB, version, channel, aCase.getId(), time));
			}else if(HttpType.POST.name().equals(aCase.getInterfaceo().getType())){
				if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
					if(apiContext.getAccount() == null && new Integer(1).equals(aCase.getLogin())){
						aResultDetail.setResulta(nologinResult);
					}else{
						aResultDetail.setResulta(aCase.getResult());
					}
				}else{
					/*aResultDetail.setResulta(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlA, aCase.getBody()), aCase.getBody(), authorA, version, channel, aCase.getId(), aCase.getImg()));	//Online Compare*/			
				}
				aResultDetail.setResultb(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlB, aCase.getBody()), aCase.getBody(), authorB, version, channel, aCase.getId(), aCase.getImg(), time));
			}
		} finally {
			if(time.getTime() == null){
				aResultDetail.setTime(-1);
			}else{
				aResultDetail.setTime(time.getTime());
			}
		}
	}
	
	private String sendMessageGet(IApiSendMessage apiSendMessage, String url, String author, String version, String channel, Integer runid, ARunTime time) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[GET:" + url  + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "]");
		String result = apiSendMessage.sendGet(httpClientManager.getHttpClient(), url, author, channel, version, time);
		logger.info("[主体运行][" + runid + "]==>" + result);
		return result;
	}
	
	private String sendMessagePost(IApiSendMessage apiSendMessage, String url, String body, String author, String version, String channel, Integer runid, String img, ARunTime time) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[POST:" + url + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + body + "]");
		String result = "";
		if(img != null && !img.isEmpty()){
			File file = new File(img);
			if(file.exists() && file.isFile()){
				result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url, body, author, channel, version, false, file, time);
			}else{
				logger.error("[案例][" + runid + "]==>文件不存在[" + img + "]");
				throw new BusinessException("[案例][" + runid + "]==>文件不存在[" + img + "]");
			}
		}else{
			result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), url, body, author, channel, version, false, time);
		}
		logger.info("[主体运行][" + runid + "]==>" + result);
		return result;
	}
	
	private String getFullUrl(ACase aCase, String url, String body){
		String iUrl = aCase.getInterfaceo().getUrl();
		String fullUrl = fillVariable(iUrl, body);
		if(fullUrl != null){
			iUrl = fullUrl;
		}
		String desc = aCase.getInterfaceo().getDescription();
		if(projectRootPath != null && !projectRootPath.isEmpty()){
			for (String pPath : projectRootPath.split(",")) {
				if(desc != null && desc.contains(pPath)){
					return url + "/" + pPath + iUrl;
				}
			}
		}
		return url + apiContext.getProject().getPath() + iUrl;
	}
	
	public String fillVariable(String url, String body){
		if(body != null && !body.isEmpty() && url != null && url.contains("{") && url.contains("}") && (url.indexOf("{") < url.indexOf("}"))){
			String tempA = url.substring(url.indexOf("{"), url.indexOf("}") + 1);
			String tempB = url.substring(url.indexOf("{") + 1, url.indexOf("}"));
			JSONObject jsono = JSON.parseObject(body);
			if(jsono != null && jsono.get(tempB) != null){
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
