package com.auto.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@Controller
@RequestMapping(value = "api/report")
public class ApiReportController extends BaseController{

	@RequestMapping(value = "/list")
	public ModelAndView getAllReport() {
		return success("api/report");
	}
	
}
