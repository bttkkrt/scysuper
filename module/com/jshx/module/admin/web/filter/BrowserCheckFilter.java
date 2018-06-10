package com.jshx.module.admin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshx.core.utils.Constants;

public class BrowserCheckFilter implements Filter {
	
	protected Logger logger = LoggerFactory.getLogger(BrowserCheckFilter.class);
	
	private FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		Boolean firstVisited = (Boolean)session.getAttribute(Constants.FIRST_VISITED);
		if(firstVisited==null){
			//第一次访问，判断当前浏览器类型
			String loginPage = config.getInitParameter("loginPage");
			String mobileLoginPage = config.getInitParameter("mobileLoginPage");
			String url = request.getRequestURI();
			url = url.substring(request.getContextPath().length(),url.length());
			String userAgent = request.getHeader("user-agent");
			session.setAttribute(Constants.FIRST_VISITED, false);
			//TODO 根据浏览器和操作系统类型转到相应的登录页面
			/**
            
			if(url.equals(loginPage)||url.equals(mobileLoginPage)){
				chain.doFilter(req, res);
			}else{
								
			}
			**/
		}
		chain.doFilter(req, res);
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
