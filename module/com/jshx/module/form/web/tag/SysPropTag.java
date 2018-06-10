/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Oct 30, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.form.web.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;

/**
 * @author john.zhang
 * @version 创建时间：Oct 30, 2011 4:34:47 PM 类说明
 */
public class SysPropTag extends SimpleTagSupport {
	private String propName;

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		String exportTag = SysPropertiesUtil.getProperty(propName);
		out.print(exportTag);
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	
}
