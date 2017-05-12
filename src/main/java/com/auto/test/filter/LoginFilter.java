package com.auto.test.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.entity.AUser;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String[] noLogin = GlobalValueConfig.getConfig("noLoginUrl").split(",");
		boolean isFilterURL= false;
		for (String s : noLogin) {
			if(request.getServletPath().endsWith(s)){
				isFilterURL = true;
			}
		}
		if(isFilterURL){
			arg2.doFilter(arg0, arg1);
		}else{
			AUser user = (AUser) request.getSession().getAttribute("user");
			if(user == null){
				HttpServletResponse response = (HttpServletResponse) arg1;
				response.sendRedirect(request.getContextPath()+"/login/page");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
