package com.auto.test.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.entity.AUser;

public class BaseController implements MessageSourceAware {

	protected MessageSource messageSource;
	public static final String RESPONSE_CODE	= "responseCode";
	public static final String RESPONSE_MSG		= "responseMsg";
	public static final String DATA_LIST		= "dataList";
	public static final String DATA				= "data";
	public static final String DATA_COUNT		= "dataCount";
	public static final String USER_NAME		= "username";
	public static final String SUCCESS_CODE		= "0000";
	public static final String FAILED_CODE		= "1111";

	public Map<String, Object> successJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		return map;
	}
	
	public Map<String, Object> failedJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, FAILED_CODE);
		return map;
	}

	public Map<String, Object> successJson(Object result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA, result);
		return map;
	}
	
	public Map<String, Object> successJson(Object result, Object result2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA, result);
		map.put(DATA + "2", result2);
		return map;
	}

	public Map<String, Object> failedJson(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, FAILED_CODE);
		map.put(RESPONSE_MSG, message);
		return map;
	}
	
	public ModelAndView success(List<?> results, int count, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA_LIST, results);
		map.put(DATA_COUNT, count);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView fail(String responseCode, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, responseCode);
		map.put(RESPONSE_MSG, GlobalValueConfig.getMsgByCode(responseCode, ""));
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView fail(String responseCode, String responseMsg, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, responseCode);
		map.put(RESPONSE_MSG, GlobalValueConfig.getMsgByCode(responseCode, responseMsg));
		mv.addAllObjects(map);
		return mv;
	}
	public ModelAndView success(String viewName, String username) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(USER_NAME, username);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView success(Object data, String viewName, String username) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA, data);
		map.put(USER_NAME, username);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView data(String name, Object data, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(name, data);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView fail(String code, Object[] args, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("failure", true);
		String message = messageSource.getMessage(code, args, "Fail!", null);
		map.put("msg", message);
		mv.addAllObjects(map);
		return mv;
	}
	
	public ModelAndView failMsg(String msg, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, FAILED_CODE);
		map.put("msg", msg);
		mv.addAllObjects(map);
		return mv;
	}
	
	public ModelAndView failLogin(String name, String email, String msg, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, FAILED_CODE);
		map.put("msg", msg);
		map.put("name", name);
		map.put("email", email);
		mv.addAllObjects(map);
		return mv;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public int getStart(int page, int size) {
		int start = (page - 1) * size;
		return start < 0 ? 0 : start;
	}

	protected AUser getCurrentUser(HttpServletRequest request) {
		AUser user = (AUser) request.getSession().getAttribute("user");
		return user;
	}
	
	protected String getCurrentUserName(HttpServletRequest request) {
		AUser user = (AUser) request.getSession().getAttribute("user");
		if(user != null){
			return user.getUsername();
		}
		return "";
	}
	
	protected String jsonFormat(String result, boolean format){
		if(result != null && !result.isEmpty()){
			JSONObject json = JSON.parseObject(result.trim());
			return JSON.toJSONString(json, format);
		}
		return null;
	}
	
	protected String trimArray(String text){
		if(text != null && !text.isEmpty()){
			String result = "";
			for (String s : text.trim().split(",")) {
				if(!s.trim().isEmpty()){
					result += "," + s.trim();
				}
			}
			return result.startsWith(",") ? result.substring(1) : "";
		}
		return "";
	}

}
