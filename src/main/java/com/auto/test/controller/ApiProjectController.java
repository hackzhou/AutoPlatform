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

import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiInterfaceService;
import com.auto.test.service.IApiProjectService;
import com.auto.test.service.IApiRunService;

@RestController
@RequestMapping(value = "api/project")
public class ApiProjectController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiProjectController.class);
	
	@Resource
	private IApiProjectService projectService;
	
	@Resource
	private IApiInterfaceService interfaceService;
	
	@Resource
	private IApiCaseService caseService;
	
	@Resource
	private IApiRunService runService;
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runProject(HttpServletRequest request, @RequestParam("api-project-run-id") String runid, @RequestParam("api-case-run-version") String version, @RequestParam("api-project-run-account") String account) {
		try {
			runService.run(ApiRunType.PROJECT, Integer.parseInt(runid), Integer.parseInt(account), Integer.parseInt(version), getCurrentUserName(request));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllProject(HttpServletRequest request) {
		List<AProject> projectList = projectService.findAllProject();
		return success(projectList, "api/project", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProjectData() {
		List<AProject> projectList = projectService.findAllProject();
		return successJson(projectList);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProjectById(@PathVariable("id") String id) {
		AProject aProject = projectService.findById(Integer.parseInt(id));
		if(aProject != null){
			return successJson(aProject);
		}
		return failedJson("项目不存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-project-id") String id, @RequestParam("api-project-name") String name) {
		try {
			if(id == null || id.isEmpty()){
				Integer pid = projectService.create(new AProject(name.trim()));
				if(pid != null){
					return successJson();
				}else{
					return failedJson("添加项目失败！");
				}
			}else{
				AProject aProject = projectService.update(new AProject(Integer.parseInt(id), name.trim()));
				if(aProject != null){
					return successJson();
				}else{
					return failedJson("更新项目失败！");
				}
			}
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteProject(@PathVariable("id") String id) {
		try {
			List<AInterface> interList = interfaceService.findByProjectId(Integer.parseInt(id));
			if(interList != null && !interList.isEmpty()){
				List<ACase> caseList = null;
				for (AInterface aInterface : interList) {
					caseList = caseService.findByInterfaceId(aInterface.getId());
					if(caseList != null && !caseList.isEmpty()){
						for (ACase aCase : caseList) {
							caseService.delete(aCase);
						}
					}
					interfaceService.delete(aInterface);
				}
			}
			projectService.delete(Integer.parseInt(id));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
}
