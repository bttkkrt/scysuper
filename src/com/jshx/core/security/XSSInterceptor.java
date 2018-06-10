package com.jshx.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;

public class XSSInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 2985226456573509583L;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public String intercept(ActionInvocation invocation) throws Exception {		
	    String result=null;
		try
        {
            if (ServletActionContext.getRequest() != null && ServletActionContext.getResponse() != null) {
                HttpServletRequest httpRequest = ServletActionContext.getRequest();
                //System.out.println(httpRequest.getRequestURI());
                HttpServletResponse httpResponse = ServletActionContext.getResponse();
                /*
                Enumeration params = httpRequest.getParameterNames();
                while(params.hasMoreElements()){
                    System.out.println(params.nextElement());
                }*/
                // http信息封装类
                XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);
                
                // 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
                if(xssRequest.validateParameter(httpResponse)){
                    if(XSSSecurityConfig.IS_LOG){
                        // 记录攻击访问日志
                        // 可使用数据库、日志、文件等方式                 
                        logger.error("请求中包含XSS攻击信息1");
                    }
                    if(XSSSecurityConfig.IS_CHAIN){
                        result="xsserror";
                    }
                    httpRequest.setAttribute("message", "请求中包含XSS攻击信息2！");
                }else{
                    result = invocation.invoke();
                }
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 		
		return result;
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
