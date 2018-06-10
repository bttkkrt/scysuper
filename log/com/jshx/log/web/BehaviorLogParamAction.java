package com.jshx.log.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.entity.BehaviorLogParam;
import com.jshx.log.service.BehaviorLogParamService;

public class BehaviorLogParamAction extends BaseAction
{
	private static final long serialVersionUID = 5563932159398852129L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private BehaviorLogParam behaviorLogParam = new BehaviorLogParam();

	/**
	 * 业务类
	 */
	@Autowired
	private BehaviorLogParamService behaviorLogParamService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != behaviorLogParam){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != behaviorLogParam.getParamValue()) && (0 < behaviorLogParam.getParamValue().trim().length())){
				paraMap.put("paramValue", "%" + behaviorLogParam.getParamValue().trim() + "%");
			}

		}
		
		pagination = behaviorLogParamService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != behaviorLogParam)&&(null != behaviorLogParam.getId()))
			behaviorLogParam = behaviorLogParamService.getById(behaviorLogParam.getId());
		
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
			behaviorLogParam.setDeptId(this.getLoginUserDepartmentId());
			behaviorLogParam.setDelFlag(0);
			behaviorLogParamService.save(behaviorLogParam);
		}else{
			behaviorLogParamService.update(behaviorLogParam);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			behaviorLogParamService.deleteWithFlag(ids);
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

	public BehaviorLogParam getBehaviorLogParam(){
		return this.behaviorLogParam;
	}

	public void setBehaviorLogParam(BehaviorLogParam behaviorLogParam){
		this.behaviorLogParam = behaviorLogParam;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
