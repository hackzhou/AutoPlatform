package com.auto.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@Controller
@RequestMapping(value = "home")
public class HomeController extends BaseController{
	
	@RequestMapping(value = "/page")
	public ModelAndView getHomePage() {
		return success("index");
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ModelAndView getHomePage(@RequestParam("username") String username, @RequestParam("password") String password) {
		if("admin".equals(username) && "admin".equals(password)){
			return success("index");
		}else{
			return success("login");
		}
	}
	
}
