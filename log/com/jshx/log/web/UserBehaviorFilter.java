package com.jshx.log.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.log.entity.BehaviorLogParam;
import com.jshx.log.entity.UserBehavior;
import com.jshx.log.entity.UserBehaviorLog;
import com.jshx.log.service.BehaviorService;
import com.jshx.log.service.UserBehaviorService;
import com.jshx.module.admin.entity.User;
/**
 * 用户行为过滤器
 */
public class UserBehaviorFilter implements Filter {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private BehaviorService behaviorService;
	
	@SuppressWarnings("unused")
	private FilterConfig config;

	@Override
	public void destroy() {
		this.config = null;
	}
	/**
	 * 保存需要记录的用户行为
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		try{
			HttpServletRequest request = (HttpServletRequest)req;
			HttpServletResponse response = (HttpServletResponse)res;
			User user = (User)request.getSession().getAttribute(Constants.CURR_USER);
			String url = request.getRequestURI().substring(request.getContextPath().length()+1, request.getRequestURI().length());
			UserBehavior behavior = UserBehaviorService.behaviorMap.get(url);
			if(behavior!=null){
				//需要记录的用户行为
				if(behavior.getBehaviorService()!=null && !behavior.getBehaviorService().trim().equals(""))
					behaviorService = (BehaviorService)SpringContextHolder.getBean(behavior.getBehaviorService());
				else
					behaviorService = (BehaviorService)SpringContextHolder.getBean("defaultBehaviorService");
				String logContent = behavior.getDefaultLog();
				
				UserBehaviorLog log = new UserBehaviorLog();
				log.setBehavior(behavior);
				log.setLoggedDate(new Date());
				List<BehaviorLogParam> paramList = new ArrayList<BehaviorLogParam>();
				
				Enumeration<String> paramNames = request.getParameterNames();
				while(paramNames.hasMoreElements()){
					String paramName = paramNames.nextElement();
					String paramValue = request.getParameter(paramName);
					if(logContent!=null)
						logContent = logContent.replaceAll("\\{"+paramName+"\\}", paramValue);
					BehaviorLogParam param = new BehaviorLogParam();
					param.setParamName(paramName);
					param.setParamValue(paramValue);
					param.setLog(log);
					param.setDelFlag(0);
					paramList.add(param);
				}
				log.setParamList(paramList);
				log.setLogContent(logContent);
				
				String userId = null;
				if(user!=null){
					userId = user.getId();
					log.setCreateUserID(userId);
				}
				log.setRemoteIp(request.getRemoteAddr());
				
				log.setDelFlag(0);
				behaviorService.logUserBehavior(log);
				if(behavior.getIsContinue().equals("0")){
					response.getWriter().println("({result:true})");
				}else
					chain.doFilter(req, res);
			}else{
				chain.doFilter(req, res);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
