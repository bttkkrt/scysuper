package com.jshx.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.views.jsp.StrutsBodyTagSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.ModuleService;

public class ButtonTag extends StrutsBodyTagSupport {
	private String moduleCode;// 模型Code

	private int num;// 按钮

	private String iconCls;

	private String function;

	private String functionName;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Autowired
	private ModuleService moduleService = (ModuleService) SpringContextHolder
			.getBean("moduleService");

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuilder sb = new StringBuilder();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		User u = (User) request.getSession().getAttribute("curr_user");

		int i = 0;
		i = moduleService.findButton(moduleCode, u.getRoleIds(), num);
		if (i > 0) {
			sb.append("<a href='javaScript:void(0)' class='easyui-linkbutton' onclick=");
			if (null != function) {
				sb.append("'");
				sb.append(function);
				sb.append("' ");
			}
			if (null != iconCls) {
				sb.append("iconCls='");
				sb.append(iconCls);
				sb.append("'>");
			} else {
				sb.append("iconCls='icon-search'>");
			}
			if (null != functionName)
				sb.append(functionName);
			sb.append("</a>&nbsp;");
		}

		try {
			out.print(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
