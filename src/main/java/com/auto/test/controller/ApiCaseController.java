package com.auto.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@Controller
@RequestMapping(value = "api/case")
public class ApiCaseController extends BaseController{

	@RequestMapping(value = "/list")
	public ModelAndView getAllCase() {
		return success("api/case");
	}
	
}
