package com.auto.test.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
	
	@Resource
	private IApiResultService resultService;
	
	@Resource
	private IApiResultDetailService resultDetailService;

	@RequestMapping(value = "/list")
	public ModelAndView getAllReport(HttpServletRequest request) {
		return success("api/report", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllResultData() {
		List<AResult> resultList = resultService.findAll();
		return successJson(resultList);
	}
	
	@RequestMapping(value = "/detail/id={id}", method = RequestMethod.GET)
	public ModelAndView getAllReportDetail(HttpServletRequest request, @PathVariable("id") String id) {
		List<AResultDetail> list = resultDetailService.findByResultId(Integer.parseInt(id));
		for (AResultDetail aResultDetail : list) {
			System.out.println(aResultDetail.toString());
		}
		return success("api/report", getCurrentUserName(request));
	}
	
}
