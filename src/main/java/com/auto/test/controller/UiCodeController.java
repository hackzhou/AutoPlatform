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
@RequestMapping(value = "ui/code")
public class UiCodeController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(UiCodeController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getUiCode(HttpServletRequest request) {
		return success("ui/code", getCurrentUserName(request));
	}
}
