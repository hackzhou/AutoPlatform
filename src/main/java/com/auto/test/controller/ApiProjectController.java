package com.auto.test.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;

@Controller
@RequestMapping(value = "api")
public class ApiProjectController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiProjectController.class);
	
	@Resource
	private IApiProjectService projectService;
	
	@RequestMapping(value = "/list")
	public ModelAndView getAllProject() {
		List<AProject> projectList = projectService.getAllProject();
		for (AProject tProject : projectList) {
			System.out.println(tProject.toString());
		}
		return success(projectList, "api/project");
	}
	
}
