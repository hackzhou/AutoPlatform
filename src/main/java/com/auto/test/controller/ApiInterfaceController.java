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
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiInterfaceService;

@RestController
@RequestMapping(value = "api/interface")
public class ApiInterfaceController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiInterfaceController.class);
	
	@Resource
	private IApiInterfaceService interfaceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllProject(HttpServletRequest request) {
		List<AInterface> list = interfaceService.getAllInterface();
		for (AInterface aInterface : list) {
			System.out.println(aInterface.toString());
		}
		return success(list, "api/interface", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllProjectData() {
		List<AInterface> list = interfaceService.getAllInterface();
		return successJson(list);
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-interface-id") String id, @RequestParam("api-interface-project") String project, @RequestParam("api-interface-name") String name, @RequestParam("api-interface-type") String type, @RequestParam("api-interface-url") String url, @RequestParam("api-interface-description") String description) {
		if(id == null || id.isEmpty()){
			Integer pid = interfaceService.create(new AInterface(Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
			if(pid != null){
				return successJson();
			}else{
				return failedJson();
			}
		}else{
			AInterface aInterface = interfaceService.update(new AInterface(Integer.parseInt(id), Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
			if(aInterface != null){
				return successJson();
			}else{
				return failedJson();
			}
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteInterface(@PathVariable("id") String id) {
		interfaceService.delete(Integer.parseInt(id));
		return successJson();
	}
	
}
