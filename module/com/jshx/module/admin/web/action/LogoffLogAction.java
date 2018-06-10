package com.jshx.module.admin.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.StringUtil;
import com.jshx.module.admin.entity.LogoffLog;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.LogoffLogService;

public class LogoffLogAction extends BaseAction {

	private static final long serialVersionUID = 5329065944974899219L;

	private String ids;

	private LogoffLog logoffLog = new LogoffLog();

	@Autowired
	private LogoffLogService logoffLogService;

	private String flag;
	
	private Pagination pagination;
	
	private Date queryLogoffDateStart;

	private Date queryLogoffDateEnd;

	
	/**
	 * 分页查询登出日志，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"browser":"","fromIp":"","logoffDate":{"time":1399538102000},"logoffType":"","operationsystem":"","user":{"dept":{"deptName":""},"displayName":"","loginId":""}}]}
	 * @return
	 */
	public void list() throws Exception{
		pagination = new Pagination(this.getRequest());
		if (StringUtil.isNullOrEmpty(logoffLog.getUser().getDeptCode())) {
			User user = logoffLog.getUser();
			if (user == null)
				user = new User();
			user.setDeptCode(this.getLoginUser().getDept().getDeptCode());
			logoffLog.setUser(user);
		}

		pagination = logoffLogService.findLogByPage(pagination, logoffLog, queryLogoffDateStart, queryLogoffDateEnd);
		outputJsonList(pagination.getTotalCount(), "user|loginId|displayName|dept|deptName|logoffType|logoffDate|time|fromIp|browser|operationsystem|", pagination.getListOfObject());
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != logoffLog)&&(null != logoffLog.getId()))
			logoffLog = logoffLogService.getById(logoffLog.getId());
		
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
			logoffLog.setDelFlag(0);
			logoffLogService.save(logoffLog);
		}else{
			logoffLogService.update(logoffLog);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			logoffLogService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
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

	public LogoffLog getLogoffLog(){
		return this.logoffLog;
	}

	public void setLogoffLog(LogoffLog logoffLog){
		this.logoffLog = logoffLog;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryLogoffDateStart(){
		return this.queryLogoffDateStart;
	}

	public void setQueryLogoffDateStart(Date queryLogoffDateStart){
		this.queryLogoffDateStart = queryLogoffDateStart;
	}

	public Date getQueryLogoffDateEnd(){
		return this.queryLogoffDateEnd;
	}

	public void setQueryLogoffDateEnd(Date queryLogoffDateEnd){
		this.queryLogoffDateEnd = queryLogoffDateEnd;
	}

}
