/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-24 上午09:00:39  
 * 将一维代码编码转为值  
 */
public class CodeValueTag extends TagSupport {

	private static final long serialVersionUID = 3289315371503057989L;
	
	private String codeId;
	
	private String itemValue;
	
	private JspWriter out;
	
	private CodeService codeService;
	
	public int doStartTag() throws JspException {
		ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        codeService = (CodeService)wac.getBean("codeService");
		out = this.pageContext.getOut();
		return super.doStartTag();
	}
	
	@Override
	public int doEndTag() throws JspException{
		try{
			CodeValue codeValue = codeService.findValueByCodeAndItem(codeId, itemValue);
			if(codeValue!=null)
				out.println(codeValue.getItemText());
		}catch(IOException e){
			throw new JspException(e.getCause());
		}
		
		return super.doEndTag();
	}

	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}

	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
}
