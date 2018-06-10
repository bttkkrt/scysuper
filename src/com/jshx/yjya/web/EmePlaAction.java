package com.jshx.yjya.web;

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
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.yjya.entity.EmePla;
import com.jshx.yjya.service.EmePlaService;

public class EmePlaAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private EmePla emePla = new EmePla();

	/**
	 * 业务类
	 */
	@Autowired
	private EmePlaService emePlaService;
	@Autowired
	private PhotoPicService photoPicService;

	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;

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
	
	
	private Date queryPublishDateStart;

	private Date queryPublishDateEnd;

	private Date queryPlanFilingTimeStart;

	private Date queryPlanFilingTimeEnd;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();

	private String type;//type=0：企业登录
	
	private String roleName;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;

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
	public String inits(){
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
		    
		if(null != emePla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emePla.getAreaId()) && (0 < emePla.getAreaId().trim().length())){
				paraMap.put("areaId", emePla.getAreaId().trim());
			}

			if ((null != emePla.getCompanyName()) && (0 < emePla.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emePla.getCompanyName().trim() + "%");
			}

			if ((null != emePla.getPlanName()) && (0 < emePla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + emePla.getPlanName().trim() + "%");
			}

			if ((null != emePla.getPlanType()) && (0 < emePla.getPlanType().trim().length())){
				paraMap.put("planType", emePla.getPlanType().trim());
			}

			if ((null != emePla.getPlanLevel()) && (0 < emePla.getPlanLevel().trim().length())){
				paraMap.put("planLevel", emePla.getPlanLevel().trim());
			}

			if ((null != emePla.getPlanFilingNumber()) && (0 < emePla.getPlanFilingNumber().trim().length())){
				paraMap.put("planFilingNumber", "%" + emePla.getPlanFilingNumber().trim() + "%");
			}
			
			paraMap.put("type", emePla.getType());
			if(emePla.getType() != null && "0".equals(emePla.getType()))
			{
				String userId=this.getLoginUser().getId();
				if(httpService.judgeRoleCode(userId, "A23")){
					paraMap.put("createUserId", userId);
				}
				else
				{
					paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
				}
			}
            
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("planType","402880084196a4a3014196b2d8330034");
codeMap.put("planLevel","402880084196a4a3014196b3f30a0037");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|planType|planLevel|planFilingNumber|areaName|companyName|planName|createUserID|";
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
		pagination = emePlaService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != emePla)&&(null != emePla.getId()))
		{
			emePla = emePlaService.getById(emePla.getId());
				if(emePla.getLinkId() == null || "".equals(emePla.getLinkId()))
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					emePla.setLinkId(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",emePla.getLinkId());
						map.put("mkType", "yjya");
						map.put("picType","yjyafj");
						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				emePla.setLinkId(linkId);
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
		if(emePla.getType().equals("0")){
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		emePla.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		emePla.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		emePla.setCompanyId(enBaseInfo.getId());
		emePla.setCompanyName(enBaseInfo.getEnterpriseName());
		}else{
			emePla.setType("1");
		}
		if ("add".equalsIgnoreCase(this.flag)){
			emePla.setDeptId(this.getLoginUserDepartmentId());
			emePla.setDelFlag(0);
			emePlaService.save(emePla);
		}else{
			emePlaService.update(emePla);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != emePla)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到emePla中去
				
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
			emePlaService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String yjya(){
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
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != emePla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emePla.getAreaId()) && (0 < emePla.getAreaId().trim().length())){
				paraMap.put("areaId", emePla.getAreaId().trim());
			}

			if ((null != emePla.getCompanyName()) && (0 < emePla.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emePla.getCompanyName().trim() + "%");
			}

			if ((null != emePla.getPlanName()) && (0 < emePla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + emePla.getPlanName().trim() + "%");
			}

			if ((null != emePla.getPlanType()) && (0 < emePla.getPlanType().trim().length())){
				paraMap.put("planType", emePla.getPlanType().trim());
			}

			if ((null != emePla.getPlanLevel()) && (0 < emePla.getPlanLevel().trim().length())){
				paraMap.put("planLevel", emePla.getPlanLevel().trim());
			}

			if ((null != emePla.getPlanFilingNumber()) && (0 < emePla.getPlanFilingNumber().trim().length())){
				paraMap.put("planFilingNumber", "%" + emePla.getPlanFilingNumber().trim() + "%");
			}
			
			paraMap.put("type", type);
			if(null != type && "0".equals(type))
			{
				String userId=this.getLoginUser().getId();
				if(httpService.judgeRoleCode(userId, "A23")){
					paraMap.put("createUserId", userId);
				}
				paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
			}
            
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("planType","402880084196a4a3014196b2d8330034");
		codeMap.put("planLevel","402880084196a4a3014196b3f30a0037");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|planType|planLevel|planFilingNumber|areaName|companyName|planName|createUserID|";
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
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = emePlaService.findByPage(pagination, paraMap);
			
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
	
	public void zwtListZfyjjyya() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != emePla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emePla.getAreaId()) && (0 < emePla.getAreaId().trim().length())){
				paraMap.put("areaId", emePla.getAreaId().trim());
			}

			if ((null != emePla.getCompanyName()) && (0 < emePla.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emePla.getCompanyName().trim() + "%");
			}

			if ((null != emePla.getPlanName()) && (0 < emePla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + emePla.getPlanName().trim() + "%");
			}

			if ((null != emePla.getPlanType()) && (0 < emePla.getPlanType().trim().length())){
				paraMap.put("planType", emePla.getPlanType().trim());
			}

			if ((null != emePla.getPlanLevel()) && (0 < emePla.getPlanLevel().trim().length())){
				paraMap.put("planLevel", emePla.getPlanLevel().trim());
			}

			if ((null != emePla.getPlanFilingNumber()) && (0 < emePla.getPlanFilingNumber().trim().length())){
				paraMap.put("planFilingNumber", "%" + emePla.getPlanFilingNumber().trim() + "%");
			}
			
			paraMap.put("type", type);
			if(null != type && "0".equals(type))
			{
				String userId=this.getLoginUser().getId();
				if(httpService.judgeRoleCode(userId, "A23")){
					paraMap.put("createUserId", userId);
				}
				paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
			}
            
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("planType","402880084196a4a3014196b2d8330034");
		codeMap.put("planLevel","402880084196a4a3014196b3f30a0037");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|planType|planLevel|planFilingNumber|areaName|companyName|planName|createUserID|";
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
		pagination = emePlaService.findByPage(pagination, paraMap);
			
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

	public EmePla getEmePla(){
		return this.emePla;
	}

	public void setEmePla(EmePla emePla){
		this.emePla = emePla;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPublishDateStart(){
		return this.queryPublishDateStart;
	}

	public void setQueryPublishDateStart(Date queryPublishDateStart){
		this.queryPublishDateStart = queryPublishDateStart;
	}

	public Date getQueryPublishDateEnd(){
		return this.queryPublishDateEnd;
	}

	public void setQueryPublishDateEnd(Date queryPublishDateEnd){
		this.queryPublishDateEnd = queryPublishDateEnd;
	}

	public Date getQueryPlanFilingTimeStart(){
		return this.queryPlanFilingTimeStart;
	}

	public void setQueryPlanFilingTimeStart(Date queryPlanFilingTimeStart){
		this.queryPlanFilingTimeStart = queryPlanFilingTimeStart;
	}

	public Date getQueryPlanFilingTimeEnd(){
		return this.queryPlanFilingTimeEnd;
	}

	public void setQueryPlanFilingTimeEnd(Date queryPlanFilingTimeEnd){
		this.queryPlanFilingTimeEnd = queryPlanFilingTimeEnd;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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
