package com.auto.test.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "tool/status")
public class ToolStatusController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolStatusController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/status],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/status", getCurrentUserName(request));
	}
	
}
