/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-26        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.tag;

import java.util.List;

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
 * @version 创建时间：2011-1-26 上午08:56:14  
 * 显示一维代码的下拉框
 */
public class SelectCodeValueTag extends TagSupport {

	private static final long serialVersionUID = -601300245773446701L;
	
	/** 一维代码编号 */
	private String codeId;
	
	/** 默认值 */
	private String defaultValue;
	
	/** html中select的名称 */
	private String domName;
	
	/** html中select的ID */
	private String domId;
	
	/** 其他属性，可设置样式，事件等 */
	private String otherProperty;
	
	private JspWriter out;
	
	private CodeService codeService;
	
	public int doStartTag() throws JspException {
		ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        codeService = (CodeService)wac.getBean("codeService");
		out = this.pageContext.getOut();
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException{
		try{
			List<CodeValue> valueList = codeService.findCodeValueByCode(codeId);
			StringBuffer sb = new StringBuffer();
			sb.append("<select id='").append(domId).append("' name='").append(domName).append("' ");
			if(otherProperty!=null)
				sb.append(otherProperty);
			sb.append(">");
			sb.append("<option value='0'>--请选择--</option>\n");
			for(CodeValue codeValue : valueList){
				sb.append("<option value='").append(codeValue.getItemValue()).append("'");
				if(defaultValue.equals(codeValue.getItemValue()))
					sb.append(" selected ");
				sb.append(">").append(codeValue.getItemText()).append("</option>\n");
			}
			sb.append("</select>\n");
			out.println(sb);
		}catch(Exception e){
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
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the domName
	 */
	public String getDomName() {
		return domName;
	}

	/**
	 * @param domName the domName to set
	 */
	public void setDomName(String domName) {
		this.domName = domName;
	}

	/**
	 * @return the domId
	 */
	public String getDomId() {
		return domId;
	}

	/**
	 * @param domId the domId to set
	 */
	public void setDomId(String domId) {
		this.domId = domId;
	}

	/**
	 * @return the otherProperty
	 */
	public String getOtherProperty() {
		return otherProperty;
	}

	/**
	 * @param otherProperty the otherProperty to set
	 */
	public void setOtherProperty(String otherProperty) {
		this.otherProperty = otherProperty;
	}
}
