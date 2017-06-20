package com.auto.test.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "home")
public class HomeController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/login")
	public ModelAndView getLoginPage(HttpServletRequest request) {
		logger.info("[Home]==>请求页面[login],登录用户[" + getCurrentUserName(request) + "]");
		return success("login", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView getRegisterPage() {
		logger.info("[Home]==>请求页面[register]");
		return success("register", null);
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView getIndexPage(HttpServletRequest request) {
		logger.info("[Home]==>请求页面[index],登录用户[" + getCurrentUserName(request) + "]");
		return success("index", getCurrentUserName(request));
	}
	
}
