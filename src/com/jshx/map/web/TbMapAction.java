package com.jshx.map.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.map.entity.TbMap;
import com.jshx.map.service.TbMapService;
import com.jshx.module.admin.entity.UserRight;

public class TbMapAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private TbMap tbMap = new TbMap();

	/**
	 * 业务类
	 */
	@Autowired
	private TbMapService tbMapService;
	@Autowired
	private HttpService httpService;

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
	private String loginUserId;
	private String roleName;
	private String  roleCode;
	
	private String companyName;
	/**
	 * 初始化 用于判断审核角色
	 */
	public String init(){
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "1";
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
		    
		if(null != tbMap){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|";
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
		pagination = tbMapService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != tbMap)&&(null != tbMap.getId()))
			tbMap = tbMapService.getById(tbMap.getId());
		
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
			tbMap.setDeptId(this.getLoginUserDepartmentId());
			tbMap.setDelFlag(0);
			tbMapService.save(tbMap);
		}else{
			tbMapService.update(tbMap);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != tbMap)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到tbMap中去
				
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
			tbMapService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 获取企业列表信息 GIS JSON
	 * lj 2015-10-31 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String companyJSON() throws Exception{
	    try{
			JSONObject json = new JSONObject();
			JSONObject j = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONArray ja1 = new JSONArray();
			Map map = new HashMap();
			String name = java.net.URLDecoder.decode(new String(flag.getBytes(),"utf-8"),"utf-8");
			map.put("name", "%"+name+"%");
			System.out.println("得到的检索企业名称："+name);
			String companyIds ="";
			List<Map> companys = tbMapService.queryMapListByMap("query_company_list_map", map);
			for(Map m:companys){
				JSONObject jn = new JSONObject();
				jn.put("ID", m.get("id"));
				ja.add(jn);
				companyIds  += m.get("id")+",";
			}
			json.put("ID", ja);
			
			ja1.add(json);
			j.put("result", true);
			j.put("id",ja1);
			j.put("ids", companyIds);
			this.getResponse().getWriter().println(j.toString());
			System.out.println(ja1.toString());
		}catch(Exception e){
			e.printStackTrace();
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 获取企业详情信息 GIS JSON
	 * lj 2015-10-31 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String companyDetailJSON() throws Exception{
	    try{
	    	JSONObject retuJson = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONObject json = new JSONObject();
			Map map = new HashMap();
			map.put("id", flag);
			Map company = tbMapService.getMapDetailByMap("get_company_detail_map", map);
			if(company!=null){
				json.put("id", company.get("id"));
				json.put("name", company.get("name"));
				JSONObject j = new JSONObject();
				j.put("NAMETIP", company.get("NAMETIP"));
				j.put("moreInfo", company.get("moreInfo"));
				j.put("企业名称", company.get("name"));
				j.put("法人代表", company.get("legalName"));
				j.put("联系电话", company.get("legalPhone"));
				j.put("成立日期", company.get("foundDate"));
				j.put("地址", company.get("address"));
				j.put("网格中队", company.get("managerTeam"));
				j.put("网格中队管理人员", company.get("manager"));
				json.put("attribute", j);
				ja.add(json);
			}
			
			retuJson.put("result", true);
			retuJson.put("detail", ja.toString());
			this.getResponse().getWriter().println(retuJson.toString());
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 获取网格列表信息 GIS JSON
	 * lj 2015-10-31 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String gridJSON() throws Exception{
	    try{
			JSONObject json = new JSONObject();
			JSONObject j = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONArray ja1 = new JSONArray();
			Map map = new HashMap();
			map.put("name", "%"+flag+"%");
			List<Map> companys = tbMapService.queryMapListByMap("query_grid_list_map", map);
			for(Map m:companys){
				JSONObject jn = new JSONObject();
				jn.put("id", m.get("id"));
				jn.put("name", m.get("name"));
				ja.add(jn);
			}
			json.put("ID", ja);
			
			ja1.add(json);
			j.put("result", true);
			j.put("id",ja1);
			this.getResponse().getWriter().println(j.toString());
			System.out.println(ja1.toString());
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public void judgeRoleCode(){
		try {
			if(httpService.judgeRoleCode(loginUserId, roleCode)){
				this.getResponse().getWriter().println("{\"result\":true}");
			}else{
				this.getResponse().getWriter().println("{\"result\":false}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String searchCom() throws IOException
	{
		 try{
			 setSessionAttribute("companyName", companyName);
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

	public TbMap getTbMap(){
		return this.tbMap;
	}

	public void setTbMap(TbMap tbMap){
		this.tbMap = tbMap;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
       
    
}
