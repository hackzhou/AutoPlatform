package com.auto.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AUser;
import com.auto.test.service.IUserService;

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rememberme") String rememberme) {
		AUser aUser = userService.isLogin(username, password);
		if(aUser != null){
			logger.info(aUser.toString());
			try {
				request.getSession().setAttribute("user", aUser);
				if("1".equals(rememberme)){
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
				return failMsg(e.getMessage(), "login");
			}
		}else{
			logger.error("Username or Password error!");
			return failMsg("Username or Password error!", "login");
		}
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		cleanCookie(request, response);
		return success("redirect:/");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("password2") String password2) {
		if(username == null || username.isEmpty()){
			return failMsg("Username cannot be empty!", "register");
		}
		if(email == null || email.isEmpty()){
			return failMsg("Email cannot be empty!", "register");
		}
		if(password == null || password.isEmpty()){
			return failMsg("Password cannot be empty!", "register");
		}
		if(password2 == null || password2.isEmpty()){
			return failMsg("Confirm Password cannot be empty!", "register");
		}
		if(!password.equals(password2)){
			return failMsg("The two password is different!", "register");
		}
		List<AUser> list = userService.findByName(username);
		if(list != null && !list.isEmpty()){
			return failMsg("Username has been registered!", "register");
		}
		AUser aUser = userService.create(new AUser(username, password, email));
		request.getSession().setAttribute("user", aUser);
		return success("index");
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
