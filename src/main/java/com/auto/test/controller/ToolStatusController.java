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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.TStatus;
import com.auto.test.service.IToolStatusService;

@RestController
@RequestMapping(value = "tool/status")
public class ToolStatusController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolStatusController.class);
	
	@Resource
	private IToolStatusService statusService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/status],登录用户[" + getCurrentUserName(request) + "]");
		List<TStatus> list = statusService.findByDept("后端");
		List<TStatus> list2 = statusService.findByDept("前端");
		return success(list, list2, "tool/status", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/update/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateStatus(HttpServletRequest request, @PathVariable("id") String id) {
		TStatus tStatus = statusService.findById(Integer.parseInt(id));
		if(tStatus != null){
			logger.info("[Status]==>更新测试状态[id=" + id + "]数据！");
			if("测试中".equals(tStatus.getStatus())){
				tStatus.setStatus("测试完成");
			}else if("测试完成".equals(tStatus.getStatus())){
				tStatus.setStatus("测试中");
			}else{
				tStatus.setStatus("测试完成");
			}
			tStatus.setOperator(getCurrentUserName(request));
			statusService.update(tStatus);
			return successJson(tStatus);
		}
		logger.error("[Status]==>获取测试状态[id=" + id + "]不存在！");
		return failedJson("获取测试状态[id=" + id + "]不存在！");
	}
	
}
