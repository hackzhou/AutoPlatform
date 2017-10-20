package com.auto.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.TUser;
import com.auto.test.service.IUserService;
import com.auto.test.utils.StrUtil;

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rememberme") String rememberme, @RequestParam("hiddenjump") String jump) {
		if(isNull(username)){
			logger.error("[UserLogin]==>登录用户[请您输入用户名！]");
			return failMsg("请您输入用户名！", "login");
		}
		if(isNull(password)){
			logger.error("[UserLogin]==>登录用户[请您输入密码！]");
			return failLogin(username, null, "请您输入密码！", "login");
		}
		TUser aUser = userService.isLogin(username, StrUtil.encryptByMD5(password));
		if(aUser != null){
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
				logger.info("[UserLogin]==>登录用户[" + aUser.getUsername() + "]");
				return success("redirect:" + jump, getCurrentUserName(request));
			} catch (Exception e) {
				cleanCookie(request, response);
				logger.error("[UserLogin]==>登录失败[" + e.getMessage() + "]", e);
				return failMsg(e.getMessage(), "login");
			}
		}else{
			logger.error("[UserLogin]==>用户名或密码有误，请重新输入！");
			return failMsg("用户名或密码有误，请重新输入！", "login");
		}
	}
	
	@RequestMapping(value = "/login/cookie", method = RequestMethod.GET)
	public ModelAndView loginCookie(HttpServletRequest request, HttpServletResponse response, @RequestParam("jump") String jump) {
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
			TUser aUser = userService.isLogin(username, password);
			if(aUser != null){
				request.getSession().setAttribute("user", aUser);
				logger.info("[UserLoginCookie]==>自动登录用户[" + aUser.getUsername() + "]");
				return success("redirect:" + jump, getCurrentUserName(request));
			}
		}
		return failMsg("请您登录!", "login");
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
		logger.info("[UserLogout]==>登出用户[" + getCurrentUserName(request) + "]");
		request.getSession().invalidate();
		cleanCookie(request, response);
		return success("redirect:/", null);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("password2") String password2) {
		if(isNull(username)){
			logger.error("[UserRegister]==>用户注册[请您输入用户名！]");
			return failMsg("请您输入用户名！", "register");
		}
		if(isNull(email)){
			logger.error("[UserRegister]==>用户注册[请您输入邮箱！]");
			return failLogin(username, null, "请您输入邮箱！", "register");
		}
		if(!StrUtil.checkEmail(email)){
			logger.error("[UserRegister]==>用户注册[邮箱格式不正确！]");
			return failLogin(username, null, "邮箱格式不正确！", "register");
		}
		if(isNull(password)){
			logger.error("[UserRegister]==>用户注册[请您输入密码！]");
			return failLogin(username, email, "请您输入密码！", "register");
		}
		if(isNull(password2)){
			logger.error("[UserRegister]==>用户注册[请您再次输入密码！]");
			return failLogin(username, email, "请您再次输入密码！", "register");
		}
		if(!password.equals(password2)){
			logger.error("[UserRegister]==>用户注册[两次输入的密码不一样！]");
			return failLogin(username, email, "两次输入的密码不一样！", "register");
		}
		List<TUser> list = userService.findByName(username);
		if(list != null && !list.isEmpty()){
			logger.error("[UserRegister]==>用户已经注册[" + username + "]");
			return failMsg("[" + username + "]已经注册!", "register");
		}
		TUser aUser = userService.create(new TUser(username, StrUtil.encryptByMD5(password), email));
		request.getSession().setAttribute("user", aUser);
		logger.info("[UserRegister]==>用户注册[" + getCurrentUserName(request) + "]");
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
	
	@RequestMapping(value = "/list/eamil", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllTaskData() {
		logger.info("[TUser]==>获取所有用户邮箱！");
		List<String> mailList = new ArrayList<String>();
		List<TUser> list = userService.findAll();
		if(list != null && !list.isEmpty()){
			for (TUser tUser : list) {
				if(!mailList.contains(tUser.getEmail())){
					mailList.add(tUser.getEmail());
				}
			}
		}
		return successJson(mailList);
	}
	
}
