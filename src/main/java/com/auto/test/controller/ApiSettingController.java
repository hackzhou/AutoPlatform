package com.auto.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@Controller
@RequestMapping(value = "api/setting")
public class ApiSettingController extends BaseController{

	@RequestMapping(value = "/list")
	public ModelAndView getAllSetting() {
		return success("api/setting");
	}
	
}
