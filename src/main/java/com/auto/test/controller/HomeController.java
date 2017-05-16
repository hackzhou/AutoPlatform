package com.auto.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "home")
public class HomeController extends BaseController{
	
	@RequestMapping(value = "/login")
	public ModelAndView getLoginPage(HttpServletRequest request) {
		return success("login", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView getRegisterPage() {
		return success("register", null);
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView getIndexPage(HttpServletRequest request) {
		return success("index", getCurrentUserName(request));
	}
	
}
