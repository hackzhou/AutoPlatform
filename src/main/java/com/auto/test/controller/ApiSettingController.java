package com.auto.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.service.IApiCaseService;

@Controller
@RequestMapping(value = "api/setting")
public class ApiSettingController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ApiSettingController.class);
	
	@Resource
	private IApiCaseService caseService;

	@RequestMapping(value = "/list")
	public ModelAndView getAllSetting(HttpServletRequest request) {
		logger.info("[Setting]==>请求页面[api/setting],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/setting", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request, @RequestParam("api-copy-project") String project, @RequestParam("api-copy-version-a") String versiona, @RequestParam("api-copy-version-b") String versionb) throws Exception {
		if(isNull(project)){
			logger.error("[Setting]==>批量复制案例[项目不能为空！]");
			return failMsg("项目不能为空！", "api/setting");
		}
		if(isNull(versiona)){
			logger.error("[Setting]==>批量复制案例[版本号不能为空！]");
			return failMsg("版本号不能为空！", "api/setting");
		}
		if(versiona.equals(versionb)){
			logger.error("[Setting]==>批量复制案例[案例复制版本号不能相同！]");
			return failMsg("案例复制版本号不能相同！", "api/setting");
		}
		caseService.copyCase(Integer.parseInt(project), Integer.parseInt(versiona), Integer.parseInt(versionb));
		logger.info("[Setting]==>批量复制案例成功[project=" + project + ",versiona=" + versiona + ",versionb=" + versionb + "]");
		return success("success", "redirect:/api/setting/list", getCurrentUserName(request));
	}
	
}
