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
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AProject;
import com.auto.test.service.IApiProjectService;
import com.auto.test.service.IApiRunService;

@RestController
@RequestMapping(value = "api/project")
public class ApiProjectController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(ApiProjectController.class);
	
	@Resource
	private IApiProjectService projectService;
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runProject(HttpServletRequest request, @RequestParam("api-project-run-id") String runid, @RequestParam("api-project-run-version") String version, @RequestParam("api-project-run-account") String account) {
		try {
			logger.info("[Project]==>运行项目[id=" + runid + ",account=" + account + ",version=" + version + ",user=" + getCurrentUserName(request) + "]");
			IApiRunService apiRunService = (IApiRunService) SpringContext.getBean("apiRunService");
			apiRunService.run(ApiRunType.PROJECT, Integer.parseInt(runid), Integer.parseInt(account), Integer.parseInt(version), getCurrentUserName(request));
			logger.info("[Project]==>运行项目成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Project]==>运行项目失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllProject(HttpServletRequest request) {
		logger.info("[Project]==>请求页面[api/project],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/project", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProjectData() {
		logger.info("[Project]==>获取所有项目数据！");
		List<AProject> projectList = projectService.findAll();
		return successJson(projectList);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProjectById(@PathVariable("id") String id) {
		AProject aProject = projectService.findById(Integer.parseInt(id));
		if(aProject != null){
			logger.info("[Project]==>获取项目[id=" + id + "]数据！");
			return successJson(aProject);
		}
		logger.error("[Project]==>获取项目[id=" + id + "]不存在！");
		return failedJson("获取项目[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getProjectByName(@RequestParam("api-project-id") String id, @RequestParam("api-project-name") String name) {
		if(id != null && !id.isEmpty()){
			AProject aProject = projectService.findById(Integer.parseInt(id));
			if(aProject != null && aProject.getName() != null && aProject.getName().equals(name)){
				logger.info("[Project]==>验证项目[name=" + name + "]是本身！");
				return successJson();
			}
		}
		List<AProject> projectList = projectService.findByName(name);
		if(projectList == null || projectList.isEmpty()){
			logger.info("[Project]==>验证项目[name=" + name + "]不存在！");
			return successJson();
		}
		logger.error("[Project]==>验证项目[name=" + name + "]已存在！");
		return failedJson("验证项目[name=" + name + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-project-id") String id, @RequestParam("api-project-name") String name, @RequestParam("api-project-path") String path) {
		try {
			if(id == null || id.isEmpty()){
				Integer pid = projectService.create(new AProject(name.trim(), path.trim()));
				if(pid != null){
					logger.info("[Project]==>添加项目[id=" + pid + ",name=" + name + "]成功！");
					return successJson();
				}else{
					logger.error("[Project]==>添加项目[name=" + name + "]失败！");
					return failedJson("添加项目[name=" + name + "]失败！");
				}
			}else{
				AProject aProject = projectService.update(new AProject(Integer.parseInt(id), name.trim(), path.trim()));
				if(aProject != null){
					logger.info("[Project]==>更新项目[id=" + id + ",name=" + name + "]成功！");
					return successJson();
				}else{
					logger.error("[Project]==>更新项目[id=" + id + ",name=" + name + "]失败！");
					return failedJson("更新项目[id=" + id + ",name=" + name + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Project]==>添加/更新项目失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteProject(@PathVariable("id") String id) {
		try {
			projectService.deleteCascade(Integer.parseInt(id));
			logger.info("[Project]==>删除项目[id=" + id + "]以及项目下接口/案例成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Project]==>删除项目失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}
