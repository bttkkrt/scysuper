/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 11, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.core.base.action;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.extend.IExtendInfo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action的基础类，主要处理获取HttpServletRequest、HttpSession、HttpServletResponse、请求参数等
 * 
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -8278362090314253360L;
	
	protected static final String RELOAD = "reload";

	protected static final String VIEW = "view";

	protected static final String EDIT = "edit";

	protected static final String LIST = "list";
	
	protected static final String JSON_ERROR = "Json Converted Error";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected String currModuleCode;
	
	/** 扩展类名 */
	protected String extendClassName;
	
	/** 扩展类的前缀 */
	protected String extendPrefix;

	public BaseAction() {
	}

	/**
	 * 获取HTTP请求
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取Session对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获取HTTP应答
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 根据key获取在Session中保存的对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getSessionAttribute(String key) {
		return this.getSession().getAttribute(key);

	}

	/**
	 * 根据key获取HTTP请求中保存的对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getRequestAttribute(String key) {
		return this.getRequest().getAttribute(key);
	}

	/**
	 * 将对象保存在HTTP请求中
	 * 
	 * @param key
	 * @param object
	 */
	public void setRequestAttribute(String key, Object object) {
		this.getRequest().setAttribute(key, object);
	}

	/**
	 * 将对象保存在Session中
	 * 
	 * @param key
	 * @param object
	 */
	public void setSessionAttribute(String key, Object object) {
		this.getSession().setAttribute(key, object);
	}

	/**
	 * 根据key获取请求的参数值
	 * 
	 * @param key
	 * @return
	 */
	public String getRequestParameter(String key) {
		return this.getRequest().getParameter(key);
	}

	/**
	 * 获取当前用户的ID
	 * 
	 * @return
	 */
	public String getLoginUserId() {
		return (String) this.getSession().getAttribute(Constants.LOGIN_USER_ID);
	}

	/**
	 * 获取当前用户对象
	 * 
	 * @return
	 */
	public User getLoginUser() {
		return (User) this.getSession().getAttribute(Constants.CURR_USER);
	}

	/**
	 * 获取当前用户所在部门对象
	 * 
	 * @return
	 */
	public Department getLoginUserDepartment() {
		User user = (User) this.getSession().getAttribute(Constants.CURR_USER);
		return (Department) user.getDept();
	}

	/**
	 * 获取当前用户所在部门的ID
	 * 
	 * @return
	 */
	public String getLoginUserDepartmentId() {
		User user = (User) this.getSession().getAttribute(Constants.CURR_USER);
		return ((Department) user.getDept()).getId();
	}

	public String getCurrModuleCode() {
		return currModuleCode;
	}

	public void setCurrModuleCode(String currModuleCode) {
		this.currModuleCode = currModuleCode;
		this.setSessionAttribute("currModuleCode", currModuleCode);
	}

	/**
	 * 将对象列做为json的分页数据输出
	 * 
	 * @param totalCnt  总数据记录数
	 * @param filter    需要输出的对象属性，多个以“,”隔开
	 * @param results   需要转成json的对象列
	 */
	protected void outputJsonList(Long totalCnt, final String filter,
			List<?> results) {
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(totalCnt).append(",\n");
		data.append("  \"rows\":\n");

		JsonConfig config = new JsonConfig();
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		JSONArray json = JSONArray.fromObject(results, config);
		data.append(json.toString());
		data.append("  \n").append("}");

		try {
			this.getResponse().getWriter().println(data);
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
	}

	/**
	 * 将对象列做为json的分页数据输出
	 * 
	 * @param totalCnt  总数据记录数
	 * @param filter    需要输出的对象属性，多个以“,”隔开
	 * @param results   需要转成json的对象列
	 */
	protected void outputJsonList(Integer totalCnt, final String filter,
			List<?> results) {
		outputJsonList((long) totalCnt.intValue(), filter, results);
	}

	/**
	 * 将对象列做为json的分页数据输出(手机端)
	 * 
	 * @param callback  手机端页面回调
	 * @param totalCnt  总数据记录数
	 * @param filter    需要输出的对象属性，多个以“,”隔开
	 * @param results   需要转成json的对象列
	 */
	protected void outputJsonListForMobile(String callback, Long totalCnt,
			final String filter, List<?> results) {
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(totalCnt).append(",\n");
		data.append("  \"rows\":\n");

		JsonConfig config = new JsonConfig();
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		JSONArray json = JSONArray.fromObject(results, config);
		data.append(json.toString());
		data.append("  \n").append("}");

		try {
			this.getResponse().getWriter().println(callback + "(" + data + ")");
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
	}

	/**
	 * 将对象列做为json的分页数据输出(手机端)
	 * 
	 * @param callback  手机端页面回调
	 * @param totalCnt  总数据记录数
	 * @param filter    需要输出的对象属性，多个以“,”隔开
	 * @param results   需要转成json的对象列
	 */
	protected void outputJsonListForMobile(String callback, Integer totalCnt,
			final String filter, List<?> results) {
		outputJsonListForMobile(callback, (long) totalCnt.intValue(), filter,
				results);
	}

	/**
	 * 将对象做为json的分页数据输出
	 * 
	 * @param filter 需要输出的对象属性，多个以“,”隔开
	 * @param results 需要转成json的对象
	 */
	protected void outputJson(final String filter, Object result) {
		JsonConfig config = new JsonConfig();
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		JSONObject json = JSONObject.fromObject(result, config);

		try {
			this.getResponse().setContentType("text/html");
			this.getResponse().getWriter().println(json.toString());
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
	}

	/**
	 * 将Map列做为json数据输出
	 * 
	 * @param items
	 */
	protected void writerJSONArray(List<Map<String, Object>> items) {
		try {
			JSONArray json = JSONArray.fromObject(items);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Charset", "utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().println(json.toString());
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
	}

	/**
	 * 将分页对象列做为json数据输出
	 * 
	 * @param pagination 分页对象
	 * @param config 输出json的配置对象
	 * @return
	 */
	public String convObjectToJson(Pagination pagination, JsonConfig config) {
		StringBuffer data = new StringBuffer("{\n");
		try {
			
			data.append("  \"total\":").append(pagination.getTotalCount())
					.append(",\n");
			data.append("  \"rows\":\n");
			JSONArray json = new JSONArray();
			if (null != config) {
				json = JSONArray.fromObject(pagination.getListOfObject(),
						config);
			} else {
				json = JSONArray.fromObject(pagination.getListOfObject());
			}
			data.append(json.toString());
			data.append("  \n").append("}");
			getResponse().setContentType("application/json;charset=UTF-8");
			getResponse().setCharacterEncoding("utf-8");
			getResponse().setHeader("Charset", "utf-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().getWriter().println(data);
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
		return data.toString();
	}
	
	/**
	 * 根据request实例化扩展信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected IExtendInfo createExtendInfo(){
		if(extendClassName!=null && !extendClassName.trim().equals("")){
			try{
				Class<?> clazz = Class.forName(extendClassName);
				Object obj = clazz.newInstance();
				Enumeration<String> parameters = getRequest().getParameterNames();
				while(parameters.hasMoreElements()){
					String param = parameters.nextElement();
					if(param.startsWith(extendPrefix)){
						String property = param.substring(param.indexOf(extendPrefix)+extendPrefix.length(), param.length());
						String value = getRequestParameter(param);
						BeanUtils.setProperty(obj, property, value);
						//ReflectionUtil.setFieldValue(obj, property, value);
					}
				}
				return (IExtendInfo)obj;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}else
			return null;
	}

	public String getExtendClassName() {
		return extendClassName;
	}

	public void setExtendClassName(String extendClassName) {
		this.extendClassName = extendClassName;
	}

	public String getExtendPrefix() {
		return extendPrefix;
	}

	public void setExtendPrefix(String extendPrefix) {
		this.extendPrefix = extendPrefix;
	}
}
