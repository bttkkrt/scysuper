package com.jshx.log.web;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.log.entity.ExceptionLog;
import com.jshx.log.service.ExceptionLogService;

public class ExceptionLogAction extends BaseAction
{

	private static final long serialVersionUID = 6523717953657047375L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ExceptionLog exceptionLog = new ExceptionLog();

	/**
	 * 业务类
	 */
	@Autowired
	private ExceptionLogService exceptionLogService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	/**
	 * 异常日志列表查询，返回json数据<br>
	 * {"total":20886,"rows":[{"className":"","createTime":"","id":"","logLevel":"","mothodName":"","oprator":{"createTime":"","displayName":"","id":""}]}
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != exceptionLog){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != exceptionLog.getClassName()) && (0 < exceptionLog.getClassName().trim().length())){
				paraMap.put("className", "%" + exceptionLog.getClassName().trim() + "%");
			}

			if ((null != exceptionLog.getMothodName()) && (0 < exceptionLog.getMothodName().trim().length())){
				paraMap.put("mothodName", "%" + exceptionLog.getMothodName().trim() + "%");
			}

		}
		
		pagination = exceptionLogService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		 
		final String filter = "oprator|displayName|id|logLevel|createTime|className|mothodName|";
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
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != exceptionLog)&&(null != exceptionLog.getId())){
			exceptionLog = exceptionLogService.getById(exceptionLog.getId());
			if(exceptionLog!=null && exceptionLog.getMsg()!=null){
				String msg = exceptionLog.getMsg();
				msg = msg.replaceAll("\\r", "<br>");
				msg = msg.replaceAll("\\n", "<br>");
				exceptionLog.setMsg(msg);
			}
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			exceptionLog.setDeptId(this.getLoginUserDepartmentId());
			exceptionLog.setDelFlag(0);
			exceptionLogService.save(exceptionLog);
		}else{
			exceptionLogService.update(exceptionLog);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			exceptionLogService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public ExceptionLog getExceptionLog(){
		return this.exceptionLog;
	}

	public void setExceptionLog(ExceptionLog exceptionLog){
		this.exceptionLog = exceptionLog;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
