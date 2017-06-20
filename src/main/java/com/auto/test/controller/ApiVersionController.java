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
import com.auto.test.entity.ACase;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiVersionService;

@RestController
@RequestMapping(value = "api/version")
public class ApiVersionController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(ApiVersionController.class);
	
	@Resource
	private IApiVersionService versionService;
	
	@Resource
	private IApiCaseService caseService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllVersion(HttpServletRequest request) {
		return success("api/version", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllVersionData() {
		List<AVersion> list = versionService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getVersionById(@PathVariable("id") String id) {
		AVersion aVersion = versionService.findById(Integer.parseInt(id));
		if(aVersion != null){
			return successJson(aVersion);
		}
		return failedJson(logger, "版本[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getVersionByVersion(@RequestParam("api-version-id") String id, @RequestParam("api-version-version") String version) {
		if(id != null && !id.isEmpty()){
			AVersion aVersion = versionService.findById(Integer.parseInt(id));
			if(aVersion != null && aVersion.getVersion() != null && aVersion.getVersion().equals(version)){
				return successJson();
			}
		}
		List<AVersion> list = versionService.findByVersion(version);
		if(list == null || list.isEmpty()){
			return successJson();
		}
		return failedJson(logger, "版本[version=" + version + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-version-id") String id, @RequestParam("api-version-version") String version, @RequestParam("api-version-channel") String channel) {
		try {
			if(id == null || id.isEmpty()){
				Integer pid = versionService.create(new AVersion(version.trim(), trimArray(channel)));
				if(pid != null){
					return successJson();
				}else{
					return failedJson(logger, "添加版本[version=" + version + "]失败！");
				}
			}else{
				AVersion aVersion = versionService.update(new AVersion(Integer.parseInt(id), version.trim(), trimArray(channel)));
				if(aVersion != null){
					return successJson();
				}else{
					return failedJson(logger, "更新版本[id=" + id + "]失败！");
				}
			}
		} catch (Exception e) {
			return failedJson(logger, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteVersion(@PathVariable("id") String id) {
		try {
			List<ACase> caseList = caseService.findByVersionId(Integer.parseInt(id));
			if(caseList != null && !caseList.isEmpty()){
				for (ACase aCase : caseList) {
					caseService.delete(aCase);
				}
			}
			versionService.delete(Integer.parseInt(id));
			return successJson();
		} catch (Exception e) {
			return failedJson(logger, e.getMessage());
		}
	}
	
}
