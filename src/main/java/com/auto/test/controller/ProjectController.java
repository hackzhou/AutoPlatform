package com.auto.test.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import com.auto.test.common.controller.BaseController;
import com.auto.test.service.IProjectService;

@Controller
public class ProjectController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	private IProjectService ProjectService;
	
}
