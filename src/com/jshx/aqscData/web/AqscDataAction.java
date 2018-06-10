/**
 * Class Name: AqscDataAction
 * Class Description：安全生产经费关联表
 */
package com.jshx.aqscData.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.aqscData.entity.AqscData;
import com.jshx.aqscData.service.AqscDataService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;

public class AqscDataAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private AqscData aqscData = new AqscData();

	/**
	 * 业务类
	 */
	@Autowired
	private AqscDataService aqscDataService;

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
		    
		if(null != aqscData){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		
		pagination = aqscDataService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != aqscData)&&(null != aqscData.getId()))
			aqscData = aqscDataService.getById(aqscData.getId());
		
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
			aqscData.setDeptId(this.getLoginUserDepartmentId());
			aqscData.setDelFlag(0);
			aqscData.setCreateTime(new Date());
			aqscData.setCreateUserID(this.getLoginUserId());
			aqscDataService.save(aqscData);
		}else{
			aqscDataService.update(aqscData);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			aqscDataService.deleteWithFlag(ids);
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

	public AqscData getAqscData(){
		return this.aqscData;
	}

	public void setAqscData(AqscData aqscData){
		this.aqscData = aqscData;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
