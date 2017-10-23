package com.auto.test.controller;

import java.util.Map;
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
import com.auto.test.common.bean.SimpleJsonResult;
import com.auto.test.common.constant.Const;
import com.auto.test.common.controller.BaseController;
import com.auto.test.utils.HttpUtil;
import com.auto.test.utils.SvnUtil;

@RestController
@RequestMapping(value = "tool/web")
public class ToolWebController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolWebController.class);
	private static String WEB_IP = Const.IP_SERVER + ".181";
	private static Integer WEB_PORT = 8090;
	private static String WEB_PROJECT = "AutoTest";
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWeb(HttpServletRequest request) {
		logger.info("[Web]==>请求页面[tool/web],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/web", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/project={project}/versions", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolWebVersions(@PathVariable("project") String project) {
		logger.info("[War]==>获取所有SVN服务器项目[" + project + "]列表数据！");
		try {
			project = isNull(project) ? "ball" : project.replace("*", "/");
			return successJson(new SvnUtil(Const.SVN_LKCZ_PUBLISH + project + "/").getAllFileName(0));
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/server/projects", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolServerProjects() {
		logger.info("[War]==>获取服务器部署项目列表数据！");
		try {
			String url = "http://" + WEB_IP + ":" + WEB_PORT + "/" + WEB_PROJECT +"/rs/files/file/web/list";
			logger.info("[War]==>获取服务器所有部署服务数据[" + url + "]");
			HttpUtil hu = new HttpUtil();
			if(hu.isAvailablePort(WEB_IP, WEB_PORT)){
				SimpleJsonResult sjr = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(url));
				if(sjr.isSuccess()){
					return successJson(sjr.getData());
				}else{
					return failedJson(sjr.getMsg());
				}
			}else{
				return failedJson("服务未启动[" + WEB_IP +":" + WEB_PORT + "][" + WEB_PROJECT +"]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getToolWebRun(HttpServletRequest request, @RequestParam("tool-web-path") String path, @RequestParam("tool-server-project") String project) {
		System.out.println("path=" + Const.SVN_LKCZ_PUBLISH + path);
		System.out.println("project=" + project);
		return successJson();
	}
	
}
