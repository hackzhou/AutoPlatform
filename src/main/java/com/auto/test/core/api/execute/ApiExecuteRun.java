package com.auto.test.core.api.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.context.ApiContext;
import com.auto.test.common.context.SpringContext;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiResultDetailService;

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
		String authora = apiContext.getAuthora();
		String authorb = apiContext.getAuthorb();
		String channel = apiContext.getaVersion().getChannel();
		String version = apiContext.getaVersion().getVersion();
		AResultDetail aResultDetail = new AResultDetail();
		aResultDetail.update(aCase);
		aResultDetail.setResulto(apiContext.getResult());
		if(apiContext.getaAccount() != null){
			aResultDetail.setAccount(apiContext.getaAccount().getLoginname() + "/" + apiContext.getaAccount().getPassword());
		}
		aResultDetail.setVersion(version);
		aResultDetail.setChannel(channel);
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		if(HttpType.GET.equals(aCase.getInterfaceo().getType())){
			logger.info("[GET] URL:" + apiContext.getUrla() + aCase.getInterfaceo().getUrl() + ",Author:" + authora + ",Channel:" + channel + ",Version:" + version);
			String resulta = apiSendMessage.sendGet(apiContext.getUrla() + aCase.getInterfaceo().getUrl(), authora, channel, version);
			logger.info(resulta);
			aResultDetail.setResulta(resulta);
			logger.info("[GET] URL:" + apiContext.getUrlb() + aCase.getInterfaceo().getUrl() + ",Author:" + authorb + ",Channel:" + channel + ",Version:" + version);
			String resultb = apiSendMessage.sendGet(apiContext.getUrlb() + aCase.getInterfaceo().getUrl(), authorb, channel, version);
			logger.info(resultb);
			aResultDetail.setResultb(resultb);
		}else if(HttpType.POST.equals(aCase.getInterfaceo().getType())){
			logger.info("[POST] URL:" + apiContext.getUrla() + aCase.getInterfaceo().getUrl() + ",Author:" + authora + ",Channel:" + channel + ",Version:" + version);
			String resulta = apiSendMessage.sendPost(apiContext.getUrla() + aCase.getInterfaceo().getUrl(), aCase.getBody(), authora, channel, version);
			logger.info(resulta);
			aResultDetail.setResulta(resulta);
			logger.info("[POST] URL:" + apiContext.getUrlb() + aCase.getInterfaceo().getUrl() + ",Author:" + authorb + ",Channel:" + channel + ",Version:" + version);
			String resultb = apiSendMessage.sendPost(apiContext.getUrlb() + aCase.getInterfaceo().getUrl(), aCase.getBody(), authorb, channel, version);
			logger.info(resultb);
			aResultDetail.setResultb(resultb);
		}
		IApiResultDetailService apiResultDetailService = (IApiResultDetailService) SpringContext.getBean("apiResultDetailService");
		apiResultDetailService.create(aResultDetail);
		logger.info(aResultDetail.toString());
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
