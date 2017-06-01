package com.auto.test.core.api.execute;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.ApiRunStatus;
import com.auto.test.common.constant.ApiStatus;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
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

	public ApiExecuteRun(ACase aCase, ApiContext apiContext) {
		super();
		this.aCase = aCase;
		this.apiContext = apiContext;
	}

	@Override
	public void run() {
		AResultDetail aResultDetail = new AResultDetail();
		try {
			String authora = apiContext.getAuthora();
			String authorb = apiContext.getAuthorb();
			String channel = apiContext.getaVersion().getChannel();
			String version = apiContext.getaVersion().getVersion();
			aResultDetail.update(aCase);
			aResultDetail.setResulto(apiContext.getResult());
			if(apiContext.getaAccount() != null){
				aResultDetail.setAccount(apiContext.getaAccount().getLoginname() + "/" + apiContext.getaAccount().getPassword());
			}
			aResultDetail.setVersion(version);
			aResultDetail.setChannel(channel);
			IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
			if(HttpType.GET.name().equals(aCase.getInterfaceo().getType())){
				aResultDetail.setResulta(sendMessageGet(apiSendMessage, authora, apiContext.getUrla(), channel, version));
				aResultDetail.setResultb(sendMessageGet(apiSendMessage, authorb, apiContext.getUrlb(), channel, version));
			}else if(HttpType.POST.name().equals(aCase.getInterfaceo().getType())){
				aResultDetail.setResulta(sendMessagePost(apiSendMessage, authora, apiContext.getUrla(), channel, version));
				aResultDetail.setResultb(sendMessagePost(apiSendMessage, authorb, apiContext.getUrlb(), channel, version));
			}
			IApiResultDetailService apiResultDetailService = (IApiResultDetailService) SpringContext.getBean("apiResultDetailService");
			if(aResultDetail.getResulta() != null && aResultDetail.getResulta().equals(aResultDetail.getResultb())){
				aResultDetail.setStatus(ApiStatus.SUCCESS.name());
			}else{
				aResultDetail.setStatus(ApiStatus.FAILURE.name());
			}
			apiResultDetailService.create(aResultDetail);
			logger.info(aResultDetail.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			apiContext.setCount(apiContext.getCount() + 1);
			AResult aResult = apiContext.getResult();
			if(ApiStatus.SUCCESS.name().equals(aResultDetail.getStatus())){
				aResult.setSuccess(aResult.getSuccess() + 1);
			}
			if(apiContext.getCount().equals(apiContext.getTotal())){
				IApiResultService apiResultService = (IApiResultService) SpringContext.getBean("apiResultService");
				aResult.setEndTime(new Date());
				aResult.setStatus(ApiRunStatus.COMPLETE.name());
				aResult.setFail(aResult.getTotal() - aResult.getSuccess());
				apiResultService.update(aResult);
			}
		}
	}
	
	private String sendMessageGet(IApiSendMessage apiSendMessage, String author, String url, String channel, String version){
		logger.info("[Run][GET:" + url + aCase.getInterfaceo().getUrl() + "],[Author:" + author + "],[Channel:" + channel + "],[Version:" + version + "]");
		String result = apiSendMessage.sendGet(url + aCase.getInterfaceo().getUrl(), author, channel, version);
		logger.info(result);
		return result;
	}
	
	private String sendMessagePost(IApiSendMessage apiSendMessage, String author, String url, String channel, String version){
		logger.info("[Run][POST:" + url + aCase.getInterfaceo().getUrl() + "],[Author:" + author + "],[Channel:" + channel + "],[Version:" + version + "],[Data:" + aCase.getBody() + "]");
		String result = apiSendMessage.sendPost(url + aCase.getInterfaceo().getUrl(), aCase.getBody(), author, channel, version);
		logger.info(result);
		return result;
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
