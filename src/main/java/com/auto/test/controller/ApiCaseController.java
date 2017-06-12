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
import com.alibaba.fastjson.JSON;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiCaseService;
import com.auto.test.service.IApiRunService;

@Controller
@RequestMapping(value = "api/case")
public class ApiCaseController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiCaseController.class);
	
	@Resource
	private IApiCaseService caseService;
	
	@Resource
	private IApiRunService runService;

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runCase(HttpServletRequest request, @RequestParam("api-case-run-id") String runid, @RequestParam("api-case-run-account") String account) {
		try {
			runService.run(ApiRunType.CASE, Integer.parseInt(runid), Integer.parseInt(account), null, getCurrentUserName(request));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllCase(HttpServletRequest request) {
		return success("api/case", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCaseData() {
		List<ACase> list = caseService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCaseById(@PathVariable("id") String id) {
		ACase aCase = caseService.findById(Integer.parseInt(id));
		if(aCase != null){
			aCase.setBody(jsonFormat(aCase.getBody(), true));
			return successJson(aCase);
		}
		return failedJson("该案例不存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-case-id") String id, @RequestParam("api-case-interface") String inter, 
			@RequestParam("api-case-version") String version, @RequestParam("api-case-name") String name, @RequestParam("api-case-strategy") String strategy, 
			@RequestParam("api-case-run") String run, @RequestParam("api-case-body") String body, @RequestParam("api-case-result") String result,
			@RequestParam("api-case-case") String cases, @RequestParam("api-case-is-body") String isBody, @RequestParam("api-case-is-result") String isResult) {
		try {
			if("0".equals(isBody)){
				body = null;
			}
			if("0".equals(isResult)){
				result = null;
			}
			if(id == null || id.isEmpty()){
				Integer pid = caseService.create(new ACase(new AVersion(Integer.parseInt(version)), new AInterface(Integer.parseInt(inter)), name.trim(), jsonFormat(body, false), jsonFormat(result, false), trimArray(strategy), trimArray(cases), Integer.parseInt(run)));
				if(pid != null){
					return successJson();
				}else{
					return failedJson("添加案例失败！");
				}
			}else{
				ACase aCase = caseService.update(new ACase(Integer.parseInt(id), new AVersion(Integer.parseInt(version)), new AInterface(Integer.parseInt(inter)), name.trim(), jsonFormat(body, false), jsonFormat(result, false), trimArray(strategy), trimArray(cases), Integer.parseInt(run)));
				if(aCase != null){
					return successJson();
				}else{
					return failedJson("更新案例失败！");
				}
			}
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteCase(@PathVariable("id") String id) {
		try {
			caseService.delete(Integer.parseInt(id));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/is/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isJson(@RequestParam("api-case-is-body") String isBody, @RequestParam("api-case-is-result") String isResult, @RequestParam("api-case-body") String body, @RequestParam("api-case-result") String result) {
		try {
			if("1".equals(isBody)){
				JSON.parseObject(body);
			}
			if("1".equals(isResult)){
				JSON.parseObject(result);
			}
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
}
