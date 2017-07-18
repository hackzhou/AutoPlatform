package com.auto.test.core.api.execute;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.ApiStatus;
import com.auto.test.common.constant.Const;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.context.ApiApplication;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.compare.JSONCompare;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiResultDetailService;
import com.auto.test.service.IApiResultService;

public class ApiExecuteRun implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ApiExecuteRun.class);
	private ApiContext apiContext = null;
	private ACase aCase = null;
	private String urlA = null;
	private String urlB = null;
	private String authorA = null;
	private String authorB = null;
	private String version = null;
	private String channel = null;

	public ApiExecuteRun(ApiContext apiContext, ACase aCase, String urlA, String urlB, 
			String authorA, String authorB, String version, String channel) {
		super();
		this.apiContext = apiContext;
		this.aCase = aCase;
		this.urlA = urlA;
		this.urlB = urlB;
		this.authorA = authorA;
		this.authorB = authorB;
		this.version = version;
		this.channel = channel;
	}

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
	}
	
	private void runFinal(AResultDetail aResultDetail) throws Exception{
		apiContext.setCount(apiContext.getCount() + 1);
		AResult aResult = apiContext.getResult();
		if(aResultDetail != null && ApiStatus.SUCCESS.name().equals(aResultDetail.getStatus())){
			aResult.setSuccess(aResult.getSuccess() + 1);
		}
		if(apiContext.getCount().equals(apiContext.getTotal())){
			if(apiContext.getAccount() != null){
				ApiApplication apiApplication = (ApiApplication) SpringContext.getBean("apiApplication");
				apiApplication.remove(apiContext.getAccount().getId());
			}
			IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
			aResult.setEndTime(new Date());
			aResult.setStatus(ApiRunStatus.COMPLETE.name());
			aResult.setFail(aResult.getTotal() - aResult.getSuccess());
			apiResultService.update(aResult);
		}
		if(aResultDetail != null && aResultDetail.getCaseo() != null){
			logger.info("[主体运行][" + aResultDetail.getCaseo().getId() + "]==>" + aResultDetail.toString());
		}
	}
	
	private void sendMessage(ACase aCase, AResultDetail aResultDetail) throws Exception{
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		if(HttpType.GET.name().equals(aCase.getInterfaceo().getType())){
			if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
				aResultDetail.setResulta(aCase.getResult());
			}else{
				aResultDetail.setResulta(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlA, null), authorA, version, channel, aCase.getId()));
			}
			aResultDetail.setResultb(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlB, null), authorB, version, channel, aCase.getId()));
		}else if(HttpType.POST.name().equals(aCase.getInterfaceo().getType())){
			if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
				aResultDetail.setResulta(aCase.getResult());
			}else{
				aResultDetail.setResulta(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlA, aCase.getBody()), aCase.getBody(), authorA, version, channel, aCase.getId()));
			}
			aResultDetail.setResultb(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlB, aCase.getBody()), aCase.getBody(), authorB, version, channel, aCase.getId()));
		}
	}
	
	private String sendMessageGet(IApiSendMessage apiSendMessage, String url, String author, String version, String channel, Integer runid) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[GET:" + url  + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "]");
		String result = apiSendMessage.sendGet(url, author, channel, version);
		logger.info("[主体运行][" + runid + "]==>" + result);
		return result;
	}
	
	private String sendMessagePost(IApiSendMessage apiSendMessage, String url, String body, String author, String version, String channel, Integer runid) throws Exception{
		logger.info("[主体运行][" + runid + "]==>[POST:" + url + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + body + "]");
		String result = apiSendMessage.sendPost(url, body, author, channel, version, false);
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
		if(desc != null && desc.contains(Const.API_PLATFORM)){
			return url + "/" + Const.API_PLATFORM + iUrl;
		}
		return url + apiContext.getProject().getPath() + iUrl;
	}
	
	public String fillVariable(String url, String body){
		if(body != null && !body.isEmpty() && url != null && url.contains("{") && url.contains("}") && (url.indexOf("{") < url.indexOf("}"))){
			String tempA = url.substring(url.indexOf("{"), url.indexOf("}") + 1);
			String tempB = url.substring(url.indexOf("{") + 1, url.indexOf("}"));
			JSONObject jsono = JSON.parseObject(body);
			if(jsono != null && jsono.get(tempB) != null){
				return url.replace(tempA, (String) jsono.get(tempB));
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
