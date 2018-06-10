/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-26        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.web.tag;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.service.FormFieldService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-26 下午03:30:04  
 * 表单的表头
 */
public class RecordHeaderTag extends TagSupport {

	private static final long serialVersionUID = 4831976731046685358L;
	
	private String tableId;
	
	private String columnName;
	
	@Autowired() 
	@Qualifier("formFieldService")
	private FormFieldService formFieldService;
	
	private JspWriter out;
	
	public int doStartTag() throws JspException {
		out = pageContext.getOut();
		ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        formFieldService = (FormFieldService)wac.getBean("formFieldService");
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException{
		try{
			System.out.println(tableId+"   "+columnName);
			FormField field = formFieldService.getDisplayFieldByTableAndColumn(tableId, columnName);
			if(field!=null){
				out.println(field.getFieldDisplayName());
			}
		}catch(Exception e){
			throw new JspException(e.getCause());
		}
		return super.doEndTag();
	}

	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the formFieldService
	 */
	public FormFieldService getFormFieldService() {
		return formFieldService;
	}

	/**
	 * @param formFieldService the formFieldService to set
	 */
	public void setFormFieldService(FormFieldService formFieldService) {
		this.formFieldService = formFieldService;
	}

}
