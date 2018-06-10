/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 12, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;

/**
 * 异常拦截器，在Action层拦截所有的异常，并加以封装，<br>
 * 根据system.properties中的配置在前台页面、控制台或日志文件中显示
 * 
 */
public class ExceptionInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 2985226456573509583L;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;

		try {
			ActionContext ac = invocation.getInvocationContext();
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			MultiPartRequestWrapper multiWrapper = null;
			if(request instanceof MultiPartRequestWrapper)
				multiWrapper = (MultiPartRequestWrapper) request;
	        if (multiWrapper!=null && multiWrapper.hasErrors()) {
	        	StringBuffer errors = new StringBuffer();
	            for (String error : multiWrapper.getErrors()) {
	            	errors.append(error).append("<br>");
	            }
	            throw new Exception(errors.toString());
	        }
			result = invocation.invoke();
		} catch (Exception e) {
			boolean isToConsole = new Boolean(SysPropertiesUtil.getProperty("isExceptionLogToConsole"));
			boolean isToLog4j = new Boolean(SysPropertiesUtil.getProperty("isExceptionLogToLog4j"));
			boolean isToJsp = new Boolean(SysPropertiesUtil.getProperty("isExceptionLogToJsp"));
			
			BaseAction ba = (BaseAction) invocation.getAction();
			List<?> exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
			String mappedResult = this.findResultFromExceptions(exceptionMappings, e);
			Map<String, ResultConfig> map = invocation.getProxy().getConfig().getResults();
			
			if(isToConsole)
			    e.printStackTrace();
			
			publishException(invocation, new ExceptionHolder(e));
			BasalException basalException = null;
			if(e instanceof UndeclaredThrowableException){
				basalException = new BasalException(BasalException.ERROR, e.getCause().getCause().getMessage(),e.getCause().getCause());
			}else if(e instanceof BasalException){
				basalException = (BasalException)e;
			}else{
				basalException = new BasalException(BasalException.ERROR, e.getMessage(),e);
			}
			
			if(isToLog4j){
				if(basalException.getExceptionType()!=null){
					CodeService codeService = (CodeService)SpringContextHolder.getBean("codeService");
					CodeValue codeValue = codeService.getCodeValueByCodeNameAndItemValue("异常类型", String.valueOf(basalException.getExceptionType()));
	                String message = "";
					if (null != codeValue){
						message = codeValue.getItemText();
	                }
	                else{
	                    message = " ";
	                }
					BasalException basalException1 = new BasalException(basalException.getLogLevel(), message, basalException.getCause());
					basalException1.setExceptionType(basalException.getExceptionType());
					basalException = basalException1;
				}
				
				if(BasalException.ERROR==basalException.getLogLevel())
					logger.error(basalException.getMessage(), basalException);
				else if(BasalException.INFO==basalException.getLogLevel())
					logger.info(basalException.getMessage(), basalException);
				else if(BasalException.DEBUG==basalException.getLogLevel())
					logger.debug(basalException.getMessage(), basalException);
				else if(BasalException.WARN==basalException.getLogLevel())
					logger.warn(basalException.getMessage(), basalException);
				else if(BasalException.FATAL==basalException.getLogLevel())
					logger.fatal(basalException.getMessage(), basalException);
				else if(BasalException.TRACE==basalException.getLogLevel())
					logger.trace(basalException.getMessage(), basalException);
			}
			
			
			if(1 == map.size() && map.containsKey("error")){
				//struts设有全局result=error
				//对应ajax调用无返回result跳转页面的情况
				ba.getResponse().getWriter().println("{\"result\":false,\"msg\":\"ExceptionCatched\",\"cause\":\""+basalException.getCause()+"\"}");
				result = null;
			}else {
				ba.getSession().setAttribute("exceptionObject", basalException);
				if(isToJsp){
					Writer w = new StringWriter();
					e.printStackTrace(new PrintWriter(w));
					String eStackTrace = w.toString();
					ba.getSession().setAttribute("exceptionStackTrace", eStackTrace);
				}
				result = mappedResult;
				
			}
		}

		return result;
	}
	

	private String findResultFromExceptions(List<?> exceptionMappings, Throwable t) {
		String result = null;

		// Check for specific exception mappings.
		if (exceptionMappings != null) {
			int deepest = Integer.MAX_VALUE;
			for (Iterator<?> iter = exceptionMappings.iterator(); iter.hasNext();) {
				ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) iter
						.next();
				int depth = getDepth(exceptionMappingConfig
						.getExceptionClassName(), t);
				if (depth >= 0 && depth < deepest) {
					deepest = depth;
					result = exceptionMappingConfig.getResult();
				}
			}
		}

		return result;
	}

	/**
	 * Return the depth to the superclass matching. 0 means ex matches exactly.
	 * Returns -1 if there's no match. Otherwise, returns depth. Lowest depth
	 * wins.
	 * 
	 * @param exceptionMapping
	 *            the mapping classname
	 * @param t
	 *            the cause
	 * @return the depth, if not found -1 is returned.
	 */
	public int getDepth(String exceptionMapping, Throwable t) {
		return getDepth(exceptionMapping, t.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class<?> exceptionClass,
			int depth) {
		if (exceptionClass.getName().indexOf(exceptionMapping) != -1) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(),
				depth + 1);
	}

	/**
	 * Default implementation to handle ExceptionHolder publishing. Pushes given
	 * ExceptionHolder on the stack. Subclasses may override this to customize
	 * publishing.
	 * 
	 * @param invocation
	 *            The invocation to publish Exception for.
	 * @param exceptionHolder
	 *            The exceptionHolder wrapping the Exception to publish.
	 */
	protected void publishException(ActionInvocation invocation,
			ExceptionHolder exceptionHolder) {
		invocation.getStack().push(exceptionHolder);
	}
}
