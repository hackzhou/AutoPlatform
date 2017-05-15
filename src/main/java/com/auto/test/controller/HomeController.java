package com.auto.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "home")
public class HomeController extends BaseController{
	
	@RequestMapping(value = "/login")
	public ModelAndView getLoginPage() {
		return success("login");
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView getRegisterPage() {
		return success("register");
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView getIndexPage() {
		return success("index");
	}
	
}
