package com.jshx.jshxJf.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.Company;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jshxJf.entity.Jshxjf;
import com.jshx.jshxJf.service.JshxjfService;

public class JshxjfAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Jshxjf jshxjf = new Jshxjf();
	
	private Company company = new Company();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxjfService jshxjfService;

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
		    
		if(null != jshxjf){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		
		pagination = jshxjfService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != company){
		    //设置查询条件，开发人员可以在此增加过滤条件
			
		}
		
		pagination = jshxjfService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}


	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != jshxjf)&&(null != jshxjf.getId()))
			jshxjf = jshxjfService.getById(jshxjf.getId());
		
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
			jshxjf.setDeptId(this.getLoginUserDepartmentId());
			jshxjf.setDelFlag(0);
			jshxjfService.save(jshxjf);
		}else{
			jshxjfService.update(jshxjf);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxjfService.deleteWithFlag(ids);
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

	public Jshxjf getJshxjf(){
		return this.jshxjf;
	}

	public void setJshxjf(Jshxjf jshxjf){
		this.jshxjf = jshxjf;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
