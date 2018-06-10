/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.OperationLog;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.LogService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:39:37  
 * 类说明  
 */
public class OperationLogFilter implements Filter {
	
	@SuppressWarnings("unused")
	private FilterConfig config;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */

	public void destroy() {
		this.config = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		LogService logService = (LogService)SpringContextHolder.getBean("logService");
		if("true".equals(SysPropertiesUtil.getProperty("isLog"))){
				User user = (User)request.getSession().getAttribute(Constants.CURR_USER);
				if(user==null||user.getId()==null)
					chain.doFilter(req, res);
				else{
					Module module = null;
					//保存访问的URL记录
					OperationLog log = new OperationLog();
					String fromIp = request.getRemoteAddr();
					String url = request.getRequestURI();
					log.setFromIp(fromIp);
					log.setUrl(url);
					log.setVisitor(user);
					
					String moduleCode = request.getParameter("currModuleCode");
					
					if(moduleCode!=null){
						 module = new Module();
						 module.setModuleCode(moduleCode);
						 log.setModule(module);
						 logService.saveLog(log);
					}else{
						if(SysPropertiesUtil.getProperty("isOpLogAll").equals("true"))
							logService.saveLog(log);
					}
					chain.doFilter(req, res);
				}
	    }else
	    	chain.doFilter(req, res);	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
