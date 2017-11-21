package com.auto.test.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private static Logger logger = LoggerFactory.getLogger(ApiReportController.class);
	
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
	
	@RequestMapping(value = "/list/data/pid={pid}/vid={vid}/time={time}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getResultDataBy(@PathVariable("pid") String pid, @PathVariable("vid") String vid, @PathVariable("time") String time) {
		logger.info("[Report]==>获取报告[project=" + pid + ",version=" + vid + ",time=" + time + "]数据！");
		List<AResult> resultList = null;
		Date startTime = null;
		Date endTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			String today = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
			if(time == null || "0".equals(time) || time.isEmpty()){
				startTime = sdf.parse(today + " 00:00:00");
				endTime = sdf.parse(today + " 23:59:59");
			}else{
				startTime = sdf.parse(time.split(",")[0] + " 00:00:00");
				endTime = sdf.parse(time.split(",")[1] + " 23:59:59");
			}
			pid = (pid == null || pid.isEmpty()) ? "0" : pid;
			vid = (vid == null || vid.isEmpty()) ? "0" : vid;
			resultList = resultService.findByProjectVersionTime(Integer.parseInt(pid), Integer.parseInt(vid), startTime, endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return successJson(resultList == null ? new ArrayList<AResult>() : resultList);
	}
	
	@RequestMapping(value = "/detail/list/id={id}", method = RequestMethod.GET)
	public ModelAndView getReportDetail(HttpServletRequest request, @PathVariable("id") String id) {
		logger.info("[ReportDetail]==>请求页面[api/detail],登录用户[" + getCurrentUserName(request) + "]");
		return success(id, "api/detail", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/detail/list/data/i={i}/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReportDetailDataByResultId(@PathVariable("i") String i, @PathVariable("id") String id) {
		logger.info("[ReportDetail]==>获取所有报告[id=" + id + "]详情数据！");
		List<AResultDetail> list = null;
		if("0".equals(i)){
			list = resultDetailService.findByResultId(Integer.parseInt(id));
		}else if("1".equals(i)){
			list = resultDetailService.findByAllErr(Integer.parseInt(id));
		}else if("2".equals(i)){
			list = resultDetailService.findByPingNo(Integer.parseInt(id));
		}else if("3".equals(i)){
			list = resultDetailService.findByErr500(Integer.parseInt(id));
		}
		return successJson(list == null ? new ArrayList<AResultDetail>() : list);
	}
	
	@RequestMapping(value = "/detail/data/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReportDetailById(@PathVariable("id") String id) {
		logger.info("[ReportDetail]==>获取报告详情[id=" + id + "]数据！");
		AResultDetail resultDetail = resultDetailService.findById(Integer.parseInt(id));
		return successJson(resultDetail);
	}
	
}
