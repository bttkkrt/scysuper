package com.jshx.jdhxp.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdhxp.entity.Jdhxp;
import com.jshx.jdhxp.service.JdhxpService;

public class JdhxpAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Jdhxp jdhxp = new Jdhxp();

	/**
	 * 业务类
	 */
	@Autowired
	private JdhxpService jdhxpService;

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
		    
		if(null != jdhxp){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jdhxp.getHxname()) && (0 < jdhxp.getHxname().trim().length())){
				paraMap.put("hxname", "%" + jdhxp.getHxname().trim() + "%");
			}

			if ((null != jdhxp.getBname()) && (0 < jdhxp.getBname().trim().length())){
				paraMap.put("bname", "%" + jdhxp.getBname().trim() + "%");
			}

			if ((null != jdhxp.getCasname()) && (0 < jdhxp.getCasname().trim().length())){
				paraMap.put("casname", "%" + jdhxp.getCasname().trim() + "%");
			}

			if ((null != jdhxp.getUnname()) && (0 < jdhxp.getUnname().trim().length())){
				paraMap.put("unname", "%" + jdhxp.getUnname().trim() + "%");
			}

		}
		
		pagination = jdhxpService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != jdhxp)&&(null != jdhxp.getId()))
			jdhxp = jdhxpService.getById(jdhxp.getId());
		
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
			jdhxp.setDeptId(this.getLoginUserDepartmentId());
			jdhxp.setDelFlag(0);
			jdhxpService.save(jdhxp);
		}else{
			jdhxpService.update(jdhxp);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jdhxpService.deleteWithFlag(ids);
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

	public Jdhxp getJdhxp(){
		return this.jdhxp;
	}

	public void setJdhxp(Jdhxp jdhxp){
		this.jdhxp = jdhxp;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
