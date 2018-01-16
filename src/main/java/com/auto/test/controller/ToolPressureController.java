package com.auto.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.controller.BaseController;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.core.api.http.HttpClientManager;
import com.auto.test.core.api.http.IApiSendMessage;
import com.auto.test.core.api.http.bean.AccessToken;
import com.auto.test.core.api.http.bean.Login;
import com.auto.test.socket.WebSocketService;
import com.auto.test.socket.session.SessionRegistry;
import com.auto.test.utils.FileUtil;
import com.neovisionaries.ws.client.WebSocket;

@RestController
@RequestMapping(value = "tool/pressure")
public class ToolPressureController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolPressureController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/pressure],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/pressure", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView runToolPressure(HttpServletRequest request, @RequestParam("tool-pressure-project") String project, @RequestParam("file") CommonsMultipartFile file) {
		try {
			logger.info("[Pressure]==>获取文件所有TOKEN！");
			List<String> reqList = new FileUtil().readFile(file.getInputStream());
			if(reqList.isEmpty()){
				return failMsg("文本内容不能为空！", "tool/pressure");
			}
			String version = reqList.get(0).split(",")[0];
			String channel = reqList.get(0).split(",")[1];
			reqList.remove(0);
			List<String> tokenList = getTokens(version, channel, reqList);
			logger.info("[Pressure]==>版本[" + version + "],渠道号[" + channel + "],Token个数[" + tokenList.size() + "]");
			WebSocketService wss = new WebSocketService();
			wss.checkProjectUrl(project);
			wss.getInstance(project, channel, tokenList);
			return success("success", "redirect:/tool/pressure/page", getCurrentUserName(request));
		} catch (Exception e) {
			e.printStackTrace();
			return failMsg(e.getMessage(), "tool/pressure");
		}
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkToolPressure() {
		logger.info("[Pressure]==>检查压测项目状态！");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<WebSocket> list = WebSocketService.getWsList();
			if(list == null || list.isEmpty()){
				map.put("check", -1);
			}else{
				map.put("check", SessionRegistry.getOnlineCount());
			}
			return successJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> stopToolPressure() {
		logger.info("[Pressure]==>停止全部压测项目！");
		try {
			WebSocketService.disconnectAll();
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolPressureProjects() {
		logger.info("[Pressure]==>获取所有压测项目！");
		try {
			return successJson(WebSocketService.getPressureProjects());
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	private List<String> getTokens(String version, String channel, List<String> accountList) throws Exception{
		if(accountList == null || accountList.isEmpty()){
			throw new BusinessException("账号/密码不可以为空！");
		}
		HttpClientManager httpClientManager = new HttpClientManager(3);
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		String loginUrl = GlobalValueConfig.getConfig("url.login.uic");
		String userLogin = GlobalValueConfig.getConfig("uri.user.login");
		String usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
		List<String> tokenList = new ArrayList<String>();
		for (String account : accountList) {
			if(!account.contains(",")){
				throw new BusinessException("[格式错误]==>" + account);
			}
			String[] acc = account.split(",");
			String data = "{\"username\":\"" + acc[0].trim() + "\",\"password\":\"" + acc[1].trim() + "\"}";
			String result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), loginUrl + userLogin, data, "", channel, version, true, null);
			Login login = apiSendMessage.json2JavaBean(Login.class, result);
			if(login != null){
				if("200".equals(login.getCode())){
					String data2 = "{\"token\":\"" + login.getData() + "\",\"type\":1}";
					result = apiSendMessage.sendPost(httpClientManager.getHttpClient(), loginUrl + usersAccessToken, data2, "", channel, version, true, null);
					AccessToken accessToken = apiSendMessage.json2JavaBean(AccessToken.class, result);
					if(accessToken != null){
						if("200".equals(accessToken.getCode())){
							tokenList.add(accessToken.getData().getAccessToken());
						}else{
							throw new BusinessException("[登录权限]==>AccessToken失败！[" + accessToken.getMessage() + "][" + data2 + "][" + data + "]");
						}
					}else{
						throw new BusinessException("[登录权限]==>请求AccessToken失败！[" + loginUrl + usersAccessToken + "][" + data2 + "][" + data + "]");
					}
				}else{
					throw new BusinessException("[登录权限]==>登录失败！[" + login.getMessage() + "][" + data + "]");
				}
			}else{
				throw new BusinessException("[登录权限]==>请求登录失败！[" + loginUrl + userLogin + "][" + data + "]");
			}
			Thread.sleep(100);
		}
		return tokenList;
	}
}
