package com.auto.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController{
	
	@RequestMapping(value = "/page")
	public ModelAndView getLoginPage() {
		return success("login");
	}
	
}
