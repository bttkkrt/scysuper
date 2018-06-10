package com.jshx.spgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.spgl.entity.Spgl;
import com.jshx.spgl.service.SpglService;

public class SpglAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Spgl spgl = new Spgl();

	/**
	 * 业务类
	 */
	@Autowired
	private SpglService spglService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryLoginTimeStart;

	private Date queryLoginTimeEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init()
	{
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != spgl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != spgl.getLoginName()) && (0 < spgl.getLoginName().trim().length())){
				paraMap.put("loginName", "%" + spgl.getLoginName().trim() + "%");
			}
			
			if ((null != spgl.getGuid()) && (0 < spgl.getGuid().trim().length())){
				String[] ss = spgl.getGuid().split(",");
				String guid = ss[0];
				String name = ss[1];
				if(guid != null && !"".equals(guid))
				{
					paraMap.put("guid", guid );
				}
				else if(name != null && !"".equals(name) && !"粉尘视频".equals(name))
				{
					paraMap.put("name", name );
				}
			}
			if ((null != spgl.getVideoName()) && (0 < spgl.getVideoName().trim().length())){
				paraMap.put("videoName", "%" + spgl.getVideoName().trim() + "%");
			}

			if (null != queryLoginTimeStart){
				paraMap.put("startLoginTime", queryLoginTimeStart);
			}

			if (null != queryLoginTimeEnd){
				paraMap.put("endLoginTime", queryLoginTimeEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|loginName|loginTime|videoName|";
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
		pagination = spglService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != spgl)&&(null != spgl.getId()))
			spgl = spglService.getById(spgl.getId());
		
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
		try{
		 	spgl.setDeptId(this.getLoginUserDepartmentId());
			spgl.setDelFlag(0);
			spgl.setLoginName(this.getLoginUser().getDisplayName());
			spgl.setLoginTime(new Date());
			spglService.save(spgl);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;	
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != spgl)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到spgl中去
				
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
			spglService.deleteWithFlag(ids);
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

	public Spgl getSpgl(){
		return this.spgl;
	}

	public void setSpgl(Spgl spgl){
		this.spgl = spgl;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryLoginTimeStart(){
		return this.queryLoginTimeStart;
	}

	public void setQueryLoginTimeStart(Date queryLoginTimeStart){
		this.queryLoginTimeStart = queryLoginTimeStart;
	}

	public Date getQueryLoginTimeEnd(){
		return this.queryLoginTimeEnd;
	}

	public void setQueryLoginTimeEnd(Date queryLoginTimeEnd){
		this.queryLoginTimeEnd = queryLoginTimeEnd;
	}

}
