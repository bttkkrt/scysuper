/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 26, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;

/**
 * @author john.zhang
 * @version 创建时间：Jan 26, 2011 10:08:32 AM 类说明 用于过滤请求 判断是否登录，是否具有访问权限
 */
public class LoginFilter implements Filter {

	private FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;

			HttpSession session = req.getSession(true);
			User user = (User) session.getAttribute(Constants.CURR_USER);

			boolean checkFlag = checkLoginInitPara(req);
			if (checkFlag) {
				chain.doFilter(request, response);
			} else if (user == null) {

				// 保存用户请求访问的网址以及参数
				Enumeration<?> paramNames = request.getParameterNames();
				StringBuffer sb = new StringBuffer();
				if (paramNames != null) {
					while (paramNames.hasMoreElements()) {
						String key = (String) paramNames.nextElement();
						sb.append(key).append("=").append(
								request.getParameter(key)).append("&");
					}
				}
				String url = req.getRequestURI();
				url = url
						.substring(req.getContextPath().length(), url.length());
				String loginPage = config.getInitParameter("loginPage");

				if (url.equals("/index.action")) {
					res.sendRedirect(req.getContextPath() + loginPage);
				} else {
					if(((HttpServletRequest)request).getHeader("user-agent")!=null && ((HttpServletRequest)request).getHeader("user-agent").toLowerCase().indexOf("android")>0){
						try{
							String callback = request.getParameter("callback");
							res.getWriter().println(callback+"({\"error\":\"504\"})");
						}catch(Exception e){
						}
					}else{
						StringBuffer js = new StringBuffer();
						js.append("<script>\n");
						js.append("window.top.location='" + req.getContextPath() + loginPage + "';\n");
						js.append("</script>\n");
						try {
							res.getWriter().append(js);
						} catch (Exception e) {
							// e.printStackTrace();
						}
					}
				}

				// req.getSession().setAttribute(Constants.USER_REQUEST_URL,
				// requestUrl);
				// res.sendRedirect(req.getContextPath() + loginPage);
			} else {
				// 验证权限
				if (SysPropertiesUtil.getBoolean("NEED_URL_AUTH", true)) {
					String url = req.getRequestURI();

					Boolean flag = false;

					url = url.substring(req.getContextPath().length() + 1, url
							.length());
					String roles = Constants.RESOURCE_AUTH.get(url);
					if (roles == null) {
						flag = true;
					} else {
						List<UserRight> rightList = (List<UserRight>)user.getUserRoles();
						for (UserRight right : rightList) {
							UserRole role = right.getRole();
							if (roles.indexOf(role.getId()) != -1) {
								flag = true;
								break;
							}
						}
					}

					if (flag) {
						chain.doFilter(request, response);
					} else {
						try {
							res.sendRedirect(req.getContextPath()
									+ "/common/403.jsp");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: checkLoginInitPara
	 * @Description: 不做登录判断
	 * @param request
	 * @return boolean
	 * @throws
	 */
	private boolean checkLoginInitPara(HttpServletRequest request) {
		String loginAction = config.getInitParameter("loginAction");
		String mobileLoginAction = config.getInitParameter("mobileLoginAction");
		String excludeAction = config.getInitParameter("excludeAction");
		String ajaxAction = config.getInitParameter("ajaxAction");
		// add by fc--排除掉某些action
		String url = request.getRequestURI();
		url = url.substring(request.getContextPath().length(), url.length());
		List<String> excludeList = getExcludeList(excludeAction);
		for (String exclude : excludeList) {
			int index = exclude.indexOf("*");
			if (index >= 0) {
				String subExclude = exclude.substring(0, index);
				if(url.indexOf(subExclude)>=0){
					return true;
				}
			} else {
				if (url.equals(exclude)) {
					return true;
				}
			}
		}
		if (url.equals(ajaxAction) || url.equals(loginAction)
				|| url.equals(mobileLoginAction)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Title: checkAccessAuth
	 * @Description:判断是否具有访问权限
	 * @param request
	 * @return boolean
	 * @throws
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private boolean checkAccessAuth(HttpServletRequest request) {
		boolean returnFlag = false;
		String moduleCode = request.getParameter("module.moduleCode");
		List<String> list = (List<String>) request.getSession().getAttribute(
				Constants.CURR_USER_MODULE);
		for (String modCode : list) {
			if (modCode.equals(moduleCode)) {
				returnFlag = true;
				break;
			}
		}
		return returnFlag;
	}

	/**
	 * @Title: getExcludeList
	 * @Description:得到不进行过滤的List
	 * @param excludeString
	 * @return List<String>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private List<String> getExcludeList(String excludeString) {
		return StringUtil.getListByToken(excludeString, ",");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

}
