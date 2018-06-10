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

/**
 * @author john.zhang
 * @version 创建时间：Oct 30, 2011 4:34:47 PM 类说明
 */
public class ExcelExportTag extends SimpleTagSupport {
	private String sqlId;
	private String headers;
	private String title;

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
		String exportTag = "<a href=\""+servletContext.getContextPath()+"/jsp/personal/exportExcel.action";
		if (StringUtil.isNotNullAndNotEmpty(sqlId)
				&& StringUtil.isNotNullAndNotEmpty(headers)) {
			exportTag = exportTag + "?sqlId=" + sqlId + "&headers=" + headers;
		} else if (StringUtil.isNotNullAndNotEmpty(sqlId)
				&& !StringUtil.isNotNullAndNotEmpty(headers)) {
			exportTag = exportTag + "?sqlId=" + sqlId;
		} else if (!StringUtil.isNotNullAndNotEmpty(sqlId)
				&& StringUtil.isNotNullAndNotEmpty(headers)) {
			exportTag = exportTag + "?headers=" + headers;
		}
		if (StringUtil.isNotNullAndNotEmpty(title)) {
			exportTag = exportTag + "&title=" + title;
		}
		exportTag = exportTag
				+ "\" class='easyui-linkbutton'+ iconCls='icon-add'>导出</a>";
		System.out.print(exportTag);
		out.print(exportTag);
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
