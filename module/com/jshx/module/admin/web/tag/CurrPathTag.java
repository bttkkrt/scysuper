/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-7        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.service.ModuleService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-3-7 下午02:19:14  
 * 类说明  
 */
public class CurrPathTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private String moduleCode;
	
	private String customPath;

	private ModuleService moduleService;
	
	private JspWriter out;
	
	/**
	 * @return the moduleCode
	 */
	public String getModuleCode() {
		return moduleCode;
	}

	/**
	 * @param moduleCode the moduleCode to set
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	public int doStartTag() throws JspException {
		ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        moduleService = (ModuleService)wac.getBean("moduleService");
        out = this.pageContext.getOut();
		return super.doStartTag();
	}

	public int doEndTag() throws JspException{
		StringBuffer sb = new StringBuffer("当前位置：");
		if(customPath!=null && !customPath.trim().equals("")){
			sb.append(this.customPath);
		}else{
			List<Module> moduleList = moduleService.findModuleTrace(moduleCode);
			if(moduleList!=null && moduleList.size()>0){
				int i = 0;
				for(Module module : moduleList){
					if(i>0)
						sb.append("&gt;");
					sb.append(module.getModuleName());
					i++;
				}
			}
		}
		try{
			out.println(sb);
		}catch(Exception e){
			throw new JspException(e.fillInStackTrace());
		}
		return super.doEndTag();
	}

	/**
	 * @return the customPath
	 */
	public String getCustomPath() {
		return customPath;
	}

	/**
	 * @param customPath the customPath to set
	 */
	public void setCustomPath(String customPath) {
		this.customPath = customPath;
	}
}
