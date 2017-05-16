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
import com.auto.test.utils.StrUtil;

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rememberme") String rememberme) {
		if(username == null || username.isEmpty()){
			return failMsg("请您输入用户名!", "login");
		}
		if(password == null || password.isEmpty()){
			return failLogin(username, null, "请您输入密码!", "login");
		}
		AUser aUser = userService.isLogin(username, StrUtil.encryptByMD5(password));
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
					cookie = new Cookie("password", StrUtil.encryptByMD5(password));
					cookie.setPath("/");
					cookie.setMaxAge(maxAge);
					response.addCookie(cookie);
				}
				return success("index", getCurrentUserName(request));
			} catch (Exception e) {
				cleanCookie(request, response);
				logger.error(e.getMessage(), e);
				return failMsg(e.getMessage(), "login");
			}
		}else{
			return failMsg("用户名或密码有误，请重新输入!", "login");
		}
	}
	
	@RequestMapping(value = "/login/cookie", method = RequestMethod.GET)
	public ModelAndView loginCookie(HttpServletRequest request, HttpServletResponse response) {
		String username = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("username")){
					username = cookie.getValue();
				}
				if(cookie.getName().equals("password")){
					password = cookie.getValue();
				}
			}
			AUser aUser = userService.isLogin(username, password);
			if(aUser != null){
				request.getSession().setAttribute("user", aUser);
				return success("index", aUser.getUsername());
			}
		}
		return failMsg("请您登录!", "login");
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		cleanCookie(request, response);
		return success("redirect:/", null);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("password2") String password2) {
		if(username == null || username.isEmpty()){
			return failMsg("请您输入用户名!", "register");
		}
		if(email == null || email.isEmpty()){
			return failLogin(username, null, "请您输入邮箱!", "register");
		}
		if(!StrUtil.checkEmail(email)){
			return failLogin(username, null, "邮箱格式不正确!", "register");
		}
		if(password == null || password.isEmpty()){
			return failLogin(username, email, "请您输入密码!", "register");
		}
		if(password2 == null || password2.isEmpty()){
			return failLogin(username, email, "请您再次输入密码!", "register");
		}
		if(!password.equals(password2)){
			return failLogin(username, email, "两次输入的密码不一样!", "register");
		}
		List<AUser> list = userService.findByName(username);
		if(list != null && !list.isEmpty()){
			return failMsg("[" + username + "]已经注册!", "register");
		}
		AUser aUser = userService.create(new AUser(username, StrUtil.encryptByMD5(password), email));
		request.getSession().setAttribute("user", aUser);
		return success("index", getCurrentUserName(request));
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
