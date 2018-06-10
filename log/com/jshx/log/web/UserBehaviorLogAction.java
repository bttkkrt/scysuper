package com.jshx.log.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.entity.UserBehaviorLog;
import com.jshx.log.service.UserBehaviorLogService;

public class UserBehaviorLogAction extends BaseAction
{

	private static final long serialVersionUID = 3456585809753588018L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private UserBehaviorLog userBehaviorLog = new UserBehaviorLog();

	/**
	 * 业务类
	 */
	@Autowired
	private UserBehaviorLogService userBehaviorLogService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryLoggedDateStart;

	private Date queryLoggedDateEnd;

	
	/**
	 * 执行查询的方法，返回json数据
	 * 
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != userBehaviorLog){
		    paraMap.put("behaviorId", userBehaviorLog.getBehavior().getId());
		}
		paraMap.put("queryLoggedDateStart", queryLoggedDateStart);
		paraMap.put("queryLoggedDateEnd", queryLoggedDateEnd);
		
		pagination = userBehaviorLogService.findByPage(pagination, paraMap);
		final String filter = "loggedDate|id|logContent|user|displayName|time|remoteIp|";
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter(){
			public boolean apply(Object source, String name, Object value) { 
				if(filter.indexOf(name+"|")!=-1)
					return false;
				else
					return true;
			}
		});
		try{
			convObjectToJson(pagination,config);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String userBehaviorLogList(){
		return SUCCESS;
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != userBehaviorLog)&&(null != userBehaviorLog.getId()))
			userBehaviorLog = userBehaviorLogService.getById(userBehaviorLog.getId());
		
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
			userBehaviorLog.setDeptId(this.getLoginUserDepartmentId());
			userBehaviorLog.setDelFlag(0);
			userBehaviorLogService.save(userBehaviorLog);
		}else{
			userBehaviorLogService.update(userBehaviorLog);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			userBehaviorLogService.deleteWithFlag(ids);
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

	public UserBehaviorLog getUserBehaviorLog(){
		return this.userBehaviorLog;
	}

	public void setUserBehaviorLog(UserBehaviorLog userBehaviorLog){
		this.userBehaviorLog = userBehaviorLog;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryLoggedDateStart(){
		return this.queryLoggedDateStart;
	}

	public void setQueryLoggedDateStart(Date queryLoggedDateStart){
		this.queryLoggedDateStart = queryLoggedDateStart;
	}

	public Date getQueryLoggedDateEnd(){
		return this.queryLoggedDateEnd;
	}

	public void setQueryLoggedDateEnd(Date queryLoggedDateEnd){
		this.queryLoggedDateEnd = queryLoggedDateEnd;
	}

}
