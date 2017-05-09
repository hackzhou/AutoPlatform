package com.auto.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;

@Controller
@RequestMapping(value = "api/project")
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
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		projectService.delete(Integer.parseInt(id));
		map.put("success", true);
		return map;
	}
	
}
