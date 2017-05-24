package com.auto.test.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runProject(@RequestParam("api-project-run-id") String runid, @RequestParam("api-project-run-account") String account) {
		System.out.println("runid:" + runid);
		System.out.println("account:" + account);
		return successJson();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllProject(HttpServletRequest request) {
		List<AProject> projectList = projectService.getAllProject();
		return success(projectList, "api/project", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProjectData() {
		List<AProject> projectList = projectService.getAllProject();
		return successJson(projectList);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProjectById(@PathVariable("id") String id) {
		AProject aProject = projectService.getProjectById(Integer.parseInt(id));
		return successJson(aProject);
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-project-id") String id, @RequestParam("api-project-name") String name) {
		if(id == null || id.isEmpty()){
			Integer pid = projectService.create(new AProject(name.trim()));
			if(pid != null){
				return successJson();
			}else{
				return failedJson();
			}
		}else{
			AProject aProject = projectService.update(new AProject(Integer.parseInt(id), name.trim()));
			if(aProject != null){
				return successJson();
			}else{
				return failedJson();
			}
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteProject(@PathVariable("id") String id) {
		projectService.delete(Integer.parseInt(id));
		return successJson();
	}
	
}
