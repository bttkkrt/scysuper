package com.jshx.gawjzzjggl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.gawjzzjggl.entity.PoliceInstitutions;
import com.jshx.gawjzzjggl.service.PoliceInstitutionsService;
import com.jshx.http.util.SaveOrUpdateDxx;

public class PoliceInstitutionsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PoliceInstitutions policeInstitutions = new PoliceInstitutions();

	/**
	 * 业务类
	 */
	@Autowired
	private PoliceInstitutionsService policeInstitutionsService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String roleName;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
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
		    
		if(null != policeInstitutions){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != policeInstitutions.getAgencyNum()) && (0 < policeInstitutions.getAgencyNum().trim().length())){
				paraMap.put("agencyNum", "%" + policeInstitutions.getAgencyNum().trim() + "%");
			}

			if ((null != policeInstitutions.getAgencyName()) && (0 < policeInstitutions.getAgencyName().trim().length())){
				paraMap.put("agencyName", "%" + policeInstitutions.getAgencyName().trim() + "%");
			}

			if ((null != policeInstitutions.getMobile()) && (0 < policeInstitutions.getMobile().trim().length())){
				paraMap.put("mobile", "%" + policeInstitutions.getMobile().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|agencyNum|agencyName|mobile|createUserID|";
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
		pagination = policeInstitutionsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != policeInstitutions)&&(null != policeInstitutions.getId())){
			policeInstitutions = policeInstitutionsService.getById(policeInstitutions.getId());
			if(policeInstitutions.getMapkey() == null || "".equals(policeInstitutions.getMapkey()))
			{
				String mapKey = java.util.UUID.randomUUID().toString();
				policeInstitutions.setMapkey(mapKey);
			}
		}else{
			String mapKey = java.util.UUID.randomUUID().toString();
			policeInstitutions.setMapkey(mapKey);
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
			policeInstitutions.setDeptId(this.getLoginUserDepartmentId());
			policeInstitutions.setDelFlag(0);
			policeInstitutionsService.save(policeInstitutions);
		}else{
			policeInstitutionsService.update(policeInstitutions);
		}
		SaveOrUpdateDxx dot = new SaveOrUpdateDxx();
		//删除点位信息
		dot.saveDot("4", policeInstitutions.getMapkey(),policeInstitutions.getLongitude(),policeInstitutions.getLatitude());
		//保存点位信息
		dot.saveDot("1", policeInstitutions.getMapkey(), policeInstitutions.getLongitude(),policeInstitutions.getLatitude());
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != policeInstitutions)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到policeInstitutions中去
				
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
			policeInstitutionsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != policeInstitutions){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != policeInstitutions.getAgencyNum()) && (0 < policeInstitutions.getAgencyNum().trim().length())){
				paraMap.put("agencyNum", "%" + policeInstitutions.getAgencyNum().trim() + "%");
			}

			if ((null != policeInstitutions.getAgencyName()) && (0 < policeInstitutions.getAgencyName().trim().length())){
				paraMap.put("agencyName", "%" + policeInstitutions.getAgencyName().trim() + "%");
			}

			if ((null != policeInstitutions.getMobile()) && (0 < policeInstitutions.getMobile().trim().length())){
				paraMap.put("mobile", "%" + policeInstitutions.getMobile().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|agencyNum|agencyName|mobile|createUserID|";
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
		
		
		
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = policeInstitutionsService.findByPage(pagination, paraMap);
			
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public PoliceInstitutions getPoliceInstitutions(){
		return this.policeInstitutions;
	}

	public void setPoliceInstitutions(PoliceInstitutions policeInstitutions){
		this.policeInstitutions = policeInstitutions;
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

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchLike() {
		return searchLike;
	}

	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
       
    
}
