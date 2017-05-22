package com.auto.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "api/account")
public class ApiAccountController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiAccountController.class);
}
