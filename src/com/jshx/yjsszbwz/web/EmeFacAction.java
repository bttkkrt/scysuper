package com.jshx.yjsszbwz.web;

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
import com.jshx.http.util.SaveOrUpdateDxx;
import com.jshx.yjsszbwz.entity.EmeFac;
import com.jshx.yjsszbwz.service.EmeFacService;

public class EmeFacAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private EmeFac emeFac = new EmeFac();

	/**
	 * 业务类
	 */
	@Autowired
	private EmeFacService emeFacService;
	
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
	
	
	private Date queryPurchaseDateStart;

	private Date queryPurchaseDateEnd;

	private Date queryProduceTimeStart;

	private Date queryProduceTimeEnd;

	private Date queryExpiryDateStart;

	private Date queryExpiryDateEnd;
	
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
		    
		if(null != emeFac){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emeFac.getAreaId()) && (0 < emeFac.getAreaId().trim().length())){
				paraMap.put("areaId", emeFac.getAreaId().trim());
			}

			if ((null != emeFac.getCompanyName()) && (0 < emeFac.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emeFac.getCompanyName().trim() + "%");
			}
			if ((null != emeFac.getFacilityName()) && (0 < emeFac.getFacilityName().trim().length())){
				paraMap.put("facilityName", "%" + emeFac.getFacilityName().trim() + "%");
			}

			if ((null != emeFac.getFacilityLevel()) && (0 < emeFac.getFacilityLevel().trim().length())){
				paraMap.put("facilityLevel", emeFac.getFacilityLevel().trim());
			}
			paraMap.put("type", emeFac.getType());
			if(emeFac.getType() != null && "0".equals(emeFac.getType()))
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
		codeMap.put("facilityLevel","402880084196a4a3014196c0b6cd006e");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|facilityLevel|areaName|companyName|facilityName|createUserID|";
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
		pagination = emeFacService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != emeFac)&&(null != emeFac.getId()))
		{
			emeFac = emeFacService.getById(emeFac.getId());
				if(emeFac.getLinkId() == null || "".equals(emeFac.getLinkId()))
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					emeFac.setLinkId(linkId);
					emeFac.setMapkey(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",emeFac.getLinkId());
						map.put("mkType", "yjsszbwz");
						map.put("picType","yjsszbwzfj");
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
				emeFac.setLinkId(linkId);
				emeFac.setMapkey(linkId);
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
		if(emeFac.getType().equals("0")){
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		emeFac.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		emeFac.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		emeFac.setCompanyId(enBaseInfo.getId());
		emeFac.setCompanyName(enBaseInfo.getEnterpriseName());
		}else{
			emeFac.setType("1");
		}
		if ("add".equalsIgnoreCase(this.flag)){
			emeFac.setDeptId(this.getLoginUserDepartmentId());
			emeFac.setDelFlag(0);
			emeFacService.save(emeFac);
		}else{
			emeFacService.update(emeFac);
		}
		SaveOrUpdateDxx dot = new SaveOrUpdateDxx();
		//删除点位信息
		dot.saveDot("4", emeFac.getMapkey(),emeFac.getLongitude(),emeFac.getLatitude());
		//保存点位信息
		dot.saveDot("1", emeFac.getMapkey(), emeFac.getLongitude(),emeFac.getLatitude());
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != emeFac)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到emeFac中去
				
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
			emeFacService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String yjsszbwz(){
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
		    
		if(null != emeFac){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emeFac.getAreaId()) && (0 < emeFac.getAreaId().trim().length())){
				paraMap.put("areaId", emeFac.getAreaId().trim());
			}

			if ((null != emeFac.getCompanyName()) && (0 < emeFac.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emeFac.getCompanyName().trim() + "%");
			}
			if ((null != emeFac.getFacilityName()) && (0 < emeFac.getFacilityName().trim().length())){
				paraMap.put("facilityName", "%" + emeFac.getFacilityName().trim() + "%");
			}

			if ((null != emeFac.getFacilityLevel()) && (0 < emeFac.getFacilityLevel().trim().length())){
				paraMap.put("facilityLevel", emeFac.getFacilityLevel().trim());
			}
			paraMap.put("type", type);
			if(null != type && "0".equals(type))
			{
				String userId=this.getLoginUser().getId();
				if(httpService.judgeRoleCode(userId, "A23")){
					paraMap.put("createUserId", userId);
				}
			}
			paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("facilityLevel","402880084196a4a3014196c0b6cd006e");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|facilityLevel|areaName|companyName|facilityName|createUserID|";
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
		pagination = emeFacService.findByPage(pagination, paraMap);
			
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
	
	
	public void zwtListAJ() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != emeFac){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emeFac.getAreaId()) && (0 < emeFac.getAreaId().trim().length())){
				paraMap.put("areaId", emeFac.getAreaId().trim());
			}

			if ((null != emeFac.getCompanyName()) && (0 < emeFac.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emeFac.getCompanyName().trim() + "%");
			}
			if ((null != emeFac.getFacilityName()) && (0 < emeFac.getFacilityName().trim().length())){
				paraMap.put("facilityName", "%" + emeFac.getFacilityName().trim() + "%");
			}

			if ((null != emeFac.getFacilityLevel()) && (0 < emeFac.getFacilityLevel().trim().length())){
				paraMap.put("facilityLevel", emeFac.getFacilityLevel().trim());
			}
			paraMap.put("type", type);
			if(null != type && "0".equals(type))
			{
				String userId=this.getLoginUser().getId();
				if(httpService.judgeRoleCode(userId, "A23")){
					paraMap.put("createUserId", userId);
				}
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("facilityLevel","402880084196a4a3014196c0b6cd006e");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|facilityLevel|areaName|companyName|facilityName|createUserID|";
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
			paraMap.put("facilityName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = emeFacService.findByPage(pagination, paraMap);
			
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

	public EmeFac getEmeFac(){
		return this.emeFac;
	}

	public void setEmeFac(EmeFac emeFac){
		this.emeFac = emeFac;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPurchaseDateStart(){
		return this.queryPurchaseDateStart;
	}

	public void setQueryPurchaseDateStart(Date queryPurchaseDateStart){
		this.queryPurchaseDateStart = queryPurchaseDateStart;
	}

	public Date getQueryPurchaseDateEnd(){
		return this.queryPurchaseDateEnd;
	}

	public void setQueryPurchaseDateEnd(Date queryPurchaseDateEnd){
		this.queryPurchaseDateEnd = queryPurchaseDateEnd;
	}

	public Date getQueryProduceTimeStart(){
		return this.queryProduceTimeStart;
	}

	public void setQueryProduceTimeStart(Date queryProduceTimeStart){
		this.queryProduceTimeStart = queryProduceTimeStart;
	}

	public Date getQueryProduceTimeEnd(){
		return this.queryProduceTimeEnd;
	}

	public void setQueryProduceTimeEnd(Date queryProduceTimeEnd){
		this.queryProduceTimeEnd = queryProduceTimeEnd;
	}

	public Date getQueryExpiryDateStart(){
		return this.queryExpiryDateStart;
	}

	public void setQueryExpiryDateStart(Date queryExpiryDateStart){
		this.queryExpiryDateStart = queryExpiryDateStart;
	}

	public Date getQueryExpiryDateEnd(){
		return this.queryExpiryDateEnd;
	}

	public void setQueryExpiryDateEnd(Date queryExpiryDateEnd){
		this.queryExpiryDateEnd = queryExpiryDateEnd;
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
