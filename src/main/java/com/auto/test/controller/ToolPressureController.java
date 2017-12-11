package com.auto.test.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.socket.WebSocketService;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "tool/pressure")
public class ToolPressureController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolPressureController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/pressure],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/pressure", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView runToolPressure(HttpServletRequest request, @RequestParam("tool-pressure-project") String project, @RequestParam("file") CommonsMultipartFile file) {
		try {
			logger.info("[Pressure]==>获取文件所有TOKEN！");
			List<String> tokenList = new FileUtil().readFile(file.getInputStream());
			logger.info("[Pressure]==>Token个数[" + tokenList.size() + "]");
			if(tokenList.isEmpty()){
				return failMsg("Token不能为空！", "tool/pressure");
			}
			WebSocketService wss = new WebSocketService();
			wss.checkProjectUrl(project);
			wss.getInstance(project, tokenList);
			return success("redirect:/tool/pressure/page", getCurrentUserName(request));
		} catch (Exception e) {
			e.printStackTrace();
			return failMsg(e.getMessage(), "tool/pressure");
		}
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> stopToolPressure() {
		logger.info("[Pressure]==>停止全部压测项目！");
		try {
			WebSocketService.disconnectAll();
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolPressureProjects() {
		logger.info("[Pressure]==>获取所有压测项目！");
		try {
			return successJson(WebSocketService.getPressureProjects());
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
}
