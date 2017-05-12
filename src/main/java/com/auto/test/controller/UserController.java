package com.auto.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AUser;
import com.auto.test.service.IUserService;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
		AUser aUser = userService.isLogin(username, password);
		if(aUser != null){
			request.getSession().setAttribute("user", aUser);
			try {
				if(true){
					Cookie cookie = null;
					int maxAge = 10 * 24 * 60 * 60;
					cookie = new Cookie("username", username);
					cookie.setMaxAge(maxAge);
					cookie.setPath("/");
					response.addCookie(cookie);
					cookie = new Cookie("password", password);
					cookie.setPath("/");
					cookie.setMaxAge(maxAge);
					response.addCookie(cookie);
				}
				return success("index");
			} catch (Exception e) {
				cleanCookie(request, response);
				logger.error(e.getMessage(), e);
				return success("login");
			}
		}else{
			return success("login");
		}
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		cleanCookie(request, response);
		return success("redirect:/");
	}
	
	private void cleanCookie(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++){
			if("username".equals(cookies[i].getName())){
				Cookie cookie = new Cookie("username", null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			if("password".equals(cookies[i].getName())){
				Cookie cookie = new Cookie("password", null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
	
}
