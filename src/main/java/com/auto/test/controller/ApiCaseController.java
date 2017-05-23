package com.auto.test.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.ACase;
import com.auto.test.service.IApiCaseService;

@Controller
@RequestMapping(value = "api/case")
public class ApiCaseController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiCaseController.class);
	
	@Resource
	private IApiCaseService caseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllCase(HttpServletRequest request) {
		List<ACase> list = caseService.getAllCase();
		for (ACase aCase : list) {
			System.out.println(aCase.toString());
		}
		return success(list, "api/case", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCaseData() {
		List<ACase> list = caseService.getAllCase();
		return successJson(list);
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-project-id") String id) {
		if(id == null || id.isEmpty()){
			Integer pid = caseService.create(new ACase());
			if(pid != null){
				return successJson();
			}else{
				return failedJson();
			}
		}else{
			ACase aCase = caseService.update(new ACase());
			if(aCase != null){
				return successJson();
			}else{
				return failedJson();
			}
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteCase(@PathVariable("id") String id) {
		caseService.delete(Integer.parseInt(id));
		return successJson();
	}
	
}
