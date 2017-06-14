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
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiInterfaceService;

@RestController
@RequestMapping(value = "api/interface")
public class ApiInterfaceController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiInterfaceController.class);
	
	@Resource
	private IApiInterfaceService interfaceService;
	
	@Resource
	private IApiCaseService caseService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllInterface(HttpServletRequest request) {
		return success("api/interface", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllInterfaceData() {
		List<AInterface> list = interfaceService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/list/data/projectid={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataByProject(@PathVariable("id") String id) {
		if(id != null && !id.isEmpty()){
			List<AInterface> list = interfaceService.findByProjectId(Integer.parseInt(id));
			return successJson(list);
		}else{
			return successJson();
		}
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getInterfaceById(@PathVariable("id") String id) {
		AInterface aInterface = interfaceService.findById(Integer.parseInt(id));
		if(aInterface != null){
			return successJson(aInterface);
		}
		return failedJson("接口不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInterfaceByUrl(@RequestParam("api-interface-id") String id, @RequestParam("api-interface-url") String url, @RequestParam("api-interface-project") String project) {
		if(id != null && !id.isEmpty()){
			AInterface aInterface = interfaceService.findById(Integer.parseInt(id));
			if(aInterface != null && aInterface.getUrl() != null && aInterface.getUrl().equals(url)){
				return successJson();
			}
		}
		List<AInterface> list = interfaceService.findByProjectUrl(Integer.parseInt(project), url);
		if(list == null || list.isEmpty()){
			return successJson();
		}
		return failedJson("该项目下接口[" + url + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-interface-id") String id, @RequestParam("api-interface-project") String project, @RequestParam("api-interface-name") String name, @RequestParam("api-interface-type") String type, @RequestParam("api-interface-url") String url, @RequestParam("api-interface-description") String description) {
		try {
			if(id == null || id.isEmpty()){
				Integer pid = interfaceService.create(new AInterface(Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
				if(pid != null){
					return successJson();
				}else{
					return failedJson("添加接口失败！");
				}
			}else{
				AInterface aInterface = interfaceService.update(new AInterface(Integer.parseInt(id), Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
				if(aInterface != null){
					return successJson();
				}else{
					return failedJson("更新接口失败！");
				}
			}
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteInterface(@PathVariable("id") String id) {
		try {
			List<ACase> caseList = caseService.findByInterfaceId(Integer.parseInt(id));
			if(caseList != null && !caseList.isEmpty()){
				for (ACase aCase : caseList) {
					caseService.delete(aCase);
				}
			}
			interfaceService.delete(Integer.parseInt(id));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
}
