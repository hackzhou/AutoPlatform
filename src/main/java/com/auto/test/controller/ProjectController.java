package com.auto.test.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.TProject;
import com.auto.test.service.IProjectService;

@Controller
@RequestMapping(value = "project")
public class ProjectController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	private IProjectService projectService;
	
	@RequestMapping(value = "/list")
	public ModelAndView getAllProject() {
		List<TProject> projectList = projectService.getAllProject();
		return success(projectList, "project/list");
	}
	
}
