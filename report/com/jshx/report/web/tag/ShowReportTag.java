/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-5-4		  	  YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.report.web.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.service.CodeService;

@SuppressWarnings("serial")
public class ShowReportTag extends TagSupport {
	
	private JspWriter out;
	
	private String pattern = "frameset";
	
	private String rptdesignFileName;
	
	private String title;
	
	private String scrolling = "auto";
	
	private String align;
	
	private String height = "100%";
	
	private String width = "100%";

	private String frameborder = "1";
	
	private String style;
	
	private String format = "html";
	


	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		out = this.pageContext.getOut();
		
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException{
		try {
			this.showReportFrame();
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getCause());
		}
		return super.doEndTag();
	}

	private void showReportFrame() throws JspException {
		JspWriter jspOut = pageContext.getOut();
		String baseAppUrl = SysPropertiesUtil.getProperty("birtAppPath");
		String url = baseAppUrl + pattern +"?__report=" + rptdesignFileName;
		if(null != this.format){
			url += "&__format="+this.format;
		}
		try{
			jspOut.println(	"<iframe id=\""+this.id+"\" "
							+"src=\""+url+"\" "
							+"title=\""+this.title+"\" "
							+"align=\""+this.align+"\" "
							+"frameborder=\""+this.frameborder+"\" "
							+"height=\""+this.height+"\" "
							+"width=\""+this.width+"\" "
							+"scrolling=\""+this.scrolling+"\" "							
							+"class=\""+this.style+"\"></iframe>"
			);
		}catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getCause());
		}
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
	    if (!("run".equalsIgnoreCase(pattern))){
	      this.pattern = "frameset";
	    }else
	    	this.pattern = pattern;
	}
	
	public String getRptdesignFileName() {
		return rptdesignFileName;
	}

	public void setRptdesignFileName(String rptdesignFileName) {
		this.rptdesignFileName = rptdesignFileName;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScrolling() {
		return scrolling;
	}

	public void setScrolling(String scrolling) {
    	this.scrolling = scrolling;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getFrameborder() {
		return frameborder;
	}

	public void setFrameborder(String frameborder) {
		this.frameborder = frameborder;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}

