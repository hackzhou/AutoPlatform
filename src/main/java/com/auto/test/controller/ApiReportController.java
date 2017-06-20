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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AResult;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiResultDetailService;
import com.auto.test.service.IApiResultService;

@Controller
@RequestMapping(value = "api/report")
public class ApiReportController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(ApiReportController.class);
	
	@Resource
	private IApiResultService resultService;
	
	@Resource
	private IApiResultDetailService resultDetailService;

	@RequestMapping(value = "/list")
	public ModelAndView getAllReport(HttpServletRequest request) {
		logger.info("[Report]==>请求页面[api/report],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/report", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllResultData() {
		logger.info("[Report]==>获取所有报告数据！");
		List<AResult> resultList = resultService.findAll();
		return successJson(resultList);
	}
	
	@RequestMapping(value = "/detail/list/id={id}", method = RequestMethod.GET)
	public ModelAndView getReportDetail(HttpServletRequest request, @PathVariable("id") String id) {
		logger.info("[ReportDetail]==>请求页面[api/detail],登录用户[" + getCurrentUserName(request) + "]");
		return success(id, "api/detail", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/detail/list/data/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReportDetailDataByResultId(@PathVariable("id") String id) {
		logger.info("[ReportDetail]==>获取所有报告[id=" + id + "]详情数据！");
		List<AResultDetail> list = resultDetailService.findByResultId(Integer.parseInt(id));
		return successJson(list);
	}
	
	@RequestMapping(value = "/detail/data/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReportDetailById(@PathVariable("id") String id) {
		logger.info("[ReportDetail]==>获取报告详情[id=" + id + "]数据！");
		AResultDetail resultDetail = resultDetailService.findById(Integer.parseInt(id));
		return successJson(resultDetail);
	}
	
}
