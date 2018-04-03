package com.auto.test.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "tool/token")
public class ToolTokenController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolTokenController.class);
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/token],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/token", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
		logger.info("[Download]==>批量生成Token！");
		File file = ResourceUtils.getFile("C:\\Tokens.txt");
		String fileName = new String("Tokens.txt".getBytes("UTF-8"),"iso-8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws Exception {
		if(file == null || file.isEmpty()){
			logger.error("[Upload]==>批量生成Token[文件是空或者不存在！]");
			return failMsg("文件是空或者不存在！", "tool/token");
		}
		if(!file.getOriginalFilename().endsWith("txt")){
			logger.error("[Upload]==>批量生成Token[文件不是TXT！]");
            return failMsg("文件不是TXT！", "tool/token");
        }
		try {
			FileUtil fu = new FileUtil();
			List<String> reqList = fu.readFile(file.getInputStream());
			String version = reqList.get(0).split(",")[0];
			String channel = reqList.get(0).split(",")[1];
			reqList.remove(0);
			String tokens = getTokens(version, channel, reqList);
			fu.writeJavaFile("C:\\", "Tokens.txt", tokens);
			logger.info("[Upload]==>批量生成Token文件上传成功！");
			return success("success", "redirect:/tool/token/page", getCurrentUserName(request));
		} catch (Exception e) {
			logger.error("[Upload]==>批量生成Token文件上传失败！[" + e.getMessage() + "]");
			return success(URLDecoder.decode("Error" + e.getMessage(), "UTF-8"), "redirect:/tool/token/page", getCurrentUserName(request));
		}
	}
	
	private String getTokens(String version, String channel, List<String> accountList) throws Exception{
		if(accountList == null || accountList.isEmpty()){
			throw new BusinessException("账号/密码不可以为空！");
		}
		HttpClientManager httpClientManager = new HttpClientManager(3);
		IApiSendMessage apiSendMessage = (IApiSendMessage) SpringContext.getBean("apiSendMessage");
		String loginUrl = GlobalValueConfig.getConfig("url.login.uic");
		String userLogin = GlobalValueConfig.getConfig("uri.user.login");
		String usersAccessToken = GlobalValueConfig.getConfig("uri.user.accessToken");
		StringBuffer sb = new StringBuffer();
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
							sb.append(accessToken.getData().getAccessToken()).append("\r\n");
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
		return sb.toString();
	}
	
}
