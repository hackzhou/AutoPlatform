package com.auto.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;

@RestController
@RequestMapping(value = "api/project")
public class ApiProjectController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiProjectController.class);
	
	@Resource
	private IApiProjectService projectService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllProject() {
		List<AProject> projectList = projectService.getAllProject();
		for (AProject tProject : projectList) {
			System.out.println(tProject.toString());
		}
		return success(projectList, "api/project");
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProjectData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AProject> projectList = projectService.getAllProject();
		map.put("data", projectList);
		map.put("success", true);
		return map;
	}
	
	@RequestMapping(value = "/create/name={name}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createProject(@PathVariable("name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pid = projectService.create(new AProject(name));
		if(pid != null){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping(value = "/update/id={id}/name={name}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProject(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		AProject aProject = projectService.update(new AProject(id, name));
		if(aProject != null){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		map.put("success", true);
		return map;
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteProject(@PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		projectService.delete(Integer.parseInt(id));
		map.put("success", true);
		return map;
	}
	
}