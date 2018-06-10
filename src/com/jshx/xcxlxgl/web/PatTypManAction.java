package com.jshx.xcxlxgl.web;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.xcxlxgl.entity.PatTypMan;
import com.jshx.xcxlxgl.service.PatTypManService;

public class PatTypManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PatTypMan patTypMan = new PatTypMan();

	/**
	 * 业务类
	 */
	@Autowired
	private PatTypManService patTypManService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private String roleName;
	
	public String init(){
		
		//判断登录人的角色  
		this.getLoginUser().getId();
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		
		for(UserRight ur:list)
		{
			
			//中队长角色和大队长角色
			if(ur.getRole().getRoleCode().equals("A11")||ur.getRole().getRoleCode().equals("A10")){
				roleName = "11";
				break;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != patTypMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != patTypMan.getPatrolTypeName()) && (0 < patTypMan.getPatrolTypeName().trim().length())){
				paraMap.put("patrolTypeName", "%" + patTypMan.getPatrolTypeName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|patrolTypeName|";
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
		pagination = patTypManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != patTypMan)&&(null != patTypMan.getId()))
			patTypMan = patTypManService.getById(patTypMan.getId());
		
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
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		if ("add".equalsIgnoreCase(this.flag)){
			patTypMan.setDeptId(this.getLoginUserDepartmentId());
			patTypMan.setDelFlag(0);
			patTypManService.save(patTypMan);
		}else{
			patTypManService.update(patTypMan);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != patTypMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到patTypMan中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			patTypManService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 获取检查项类型名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void getName() throws Exception{
		patTypMan = patTypManService.getById(patTypMan.getId());
		JSONObject json = new JSONObject();
		if(patTypMan!=null){
			json.put("itemText", patTypMan.getPatrolTypeName());
		}else{
			json.put("itemText", "未知");
		}
		this.getResponse().getWriter().println(json.toString());
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

	public PatTypMan getPatTypMan(){
		return this.patTypMan;
	}

	public void setPatTypMan(PatTypMan patTypMan){
		this.patTypMan = patTypMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
       
    
}
