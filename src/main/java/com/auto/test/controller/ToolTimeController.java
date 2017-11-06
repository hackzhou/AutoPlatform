package com.auto.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.bean.SimpleJsonResult;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.context.ToolWarApplication;
import com.auto.test.common.controller.BaseController;
import com.auto.test.utils.DateUtil;
import com.auto.test.utils.HttpUtil;

@RestController
@RequestMapping(value = "tool/time")
public class ToolTimeController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolTimeController.class);
	private static Integer WAR_PORT = 8090;
	private static String WAR_PROJECT = "AutoTest";
	private static String[] IP_ARR = {Const.IP_SERVER_1 + ".181", Const.IP_SERVER_1 + ".182", Const.IP_SERVER_1 + ".184", Const.IP_SERVER_1 + ".192", Const.IP_SERVER_1 + ".194"};
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/time],登录用户[" + getCurrentUserName(request) + "]");
		((ToolWarApplication) SpringContext.getBean("toolWarApplication")).setIps(getAvailableServer(IP_ARR));
		return success("tool/time", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolTimes() {
		try {
			String result = "";
			List<String> list = ((ToolWarApplication) SpringContext.getBean("toolWarApplication")).getIps();
			if(list != null && !list.isEmpty()){
				for (String ip : list) {
					String serverTime = getServerTimes(ip);
					result += ip + "," + serverTime + ";";
				}
			}
			return successJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	private String getServerTimes(String ip){
		String url = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/times/time/get";
		HttpUtil hu = new HttpUtil();
		SimpleJsonResult result = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(url));
		if(result.isSuccess()){
			return DateUtil.getFormatTime(Long.parseLong(String.valueOf(result.getData())));
		}
		return "";
	}
	
	@RequestMapping(value = "/set/ip={ip}/time={time}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> setToolTime(@PathVariable("ip") String ip, @PathVariable("time") String time) {
		try {
			if(time == null || time.isEmpty()){
				time = "0";
			}
			if(setServerTime(ip, time)){
				return successJson();
			}else{
				return failedJson("设置失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return failedJson(e.getMessage());
		}
	}
	
	private boolean setServerTime(String ip, String time){
		time = "0".equals(time) ? "0" : String.valueOf(DateUtil.date2Long(time));
		String url = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/times/time/set/time=" + time;
		HttpUtil hu = new HttpUtil();
		SimpleJsonResult result = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(url));
		if(result.isSuccess()){
			return true;
		}
		return false;
	}
	
	private List<String> getAvailableServer(String[] arr){
		if(arr != null && arr.length > 0){
			List<String> list = new ArrayList<String>();
			HttpUtil hu = new HttpUtil();
			for (String ip : arr) {
				try {
					if(hu.isAvailablePort(ip, WAR_PORT)){
						list.add(ip);
					}
				} catch (Exception e) {
				}
			}
			return list;
		}
		return null;
	}
}
