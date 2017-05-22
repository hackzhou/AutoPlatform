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
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiVersionService;

@RestController
@RequestMapping(value = "api/version")
public class ApiVersionController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiVersionController.class);
	
	@Resource
	private IApiVersionService versionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllVersion(HttpServletRequest request) {
		List<AVersion> list = versionService.getAllVersion();
		return success(list, "api/version", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllVersionData() {
		List<AVersion> list = versionService.getAllVersion();
		return successJson(list);
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-version-id") String id, @RequestParam("api-version-version") String version, @RequestParam("api-version-channel") String channel) {
		if(id == null || id.isEmpty()){
			Integer pid = versionService.create(new AVersion(version, channel));
			if(pid != null){
				return successJson();
			}else{
				return failedJson();
			}
		}else{
			AVersion aVersion = versionService.update(new AVersion(Integer.parseInt(id), version, channel));
			if(aVersion != null){
				return successJson();
			}else{
				return failedJson();
			}
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteVersion(@PathVariable("id") String id) {
		versionService.delete(Integer.parseInt(id));
		return successJson();
	}
	
}
