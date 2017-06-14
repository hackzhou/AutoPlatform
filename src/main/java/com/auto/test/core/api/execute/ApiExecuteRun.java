package com.auto.test.core.api.execute;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
			throw new BusinessException(e.getMessage());
		}
	}
	
	private void oneRunBody(ACase aCase, AResultDetail aResultDetail) throws Exception{
		try {
			sendMessage(aCase, aResultDetail);
			saveResultDetail(aCase, aResultDetail);
		} finally {
			runFinal(aResultDetail);
		}
	}
	
	private void saveResultDetail(ACase aCase, AResultDetail aResultDetail){
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
		if(new JSONCompare().compareJson(aResultDetail.getResulta(), aResultDetail.getResultb(), ignore)){
			aResultDetail.setStatus(ApiStatus.SUCCESS.name());
		}else{
			aResultDetail.setStatus(ApiStatus.FAILURE.name());
		}
		apiResultDetailService.create(aResultDetail);
		logger.info(aResultDetail.toString());
	}
	
	private void runFinal(AResultDetail aResultDetail) throws Exception{
		apiContext.setCount(apiContext.getCount() + 1);
		AResult aResult = apiContext.getResult();
		if(ApiStatus.SUCCESS.name().equals(aResultDetail.getStatus())){
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
	}
	
	private void sendMessage(ACase aCase, AResultDetail aResultDetail) throws Exception{
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		if(HttpType.GET.name().equals(aCase.getInterfaceo().getType())){
			if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
				aResultDetail.setResulta(aCase.getResult());
			}else{
				aResultDetail.setResulta(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlA), authorA, version, channel));
			}
			aResultDetail.setResultb(sendMessageGet(apiSendMessage, getFullUrl(aCase, urlB), authorB, version, channel));
		}else if(HttpType.POST.name().equals(aCase.getInterfaceo().getType())){
			if(aCase.getResult() != null && !aCase.getResult().isEmpty()){
				aResultDetail.setResulta(aCase.getResult());
			}else{
				aResultDetail.setResulta(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlA), aCase.getBody(), authorA, version, channel));
			}
			aResultDetail.setResultb(sendMessagePost(apiSendMessage, getFullUrl(aCase, urlB), aCase.getBody(), authorB, version, channel));
		}
	}
	
	private String sendMessageGet(IApiSendMessage apiSendMessage, String url, String author, String version, String channel) throws Exception{
		logger.info("[Run][GET:" + url  + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "]");
		String result = apiSendMessage.sendGet(url, author, channel, version);
		logger.info(result);
		return result;
	}
	
	private String sendMessagePost(IApiSendMessage apiSendMessage, String url, String body, String author, String version, String channel) throws Exception{
		logger.info("[Run][POST:" + url + "],[Author:" + author + "],[Version:" + version + "],[Channel:" + channel + "],[Data:" + body + "]");
		String result = apiSendMessage.sendPost(url, body, author, channel, version);
		logger.info(result);
		return result;
	}
	
	private String getFullUrl(ACase aCase, String url){
		String desc = aCase.getInterfaceo().getDescription();
		if(desc != null && desc.contains(Const.API_PLATFORM)){
			return url + "/" + Const.API_PLATFORM + aCase.getInterfaceo().getUrl();
		}
		return url + apiContext.getProject().getPath() + aCase.getInterfaceo().getUrl();
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
