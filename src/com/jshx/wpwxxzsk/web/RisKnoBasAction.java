package com.jshx.wpwxxzsk.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.wpwxxzsk.entity.RisKnoBas;
import com.jshx.wpwxxzsk.service.RisKnoBasService;

public class RisKnoBasAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private RisKnoBas risKnoBas = new RisKnoBas();

	/**
	 * 业务类
	 */
	@Autowired
	private RisKnoBasService risKnoBasService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;


	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
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
	
	@Autowired
	private HttpService httpService;
	
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

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
				setRoleName("0");
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
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != risKnoBas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != risKnoBas.getAreaId()) && (0 < risKnoBas.getAreaId().trim().length())){
				paraMap.put("areaId",  risKnoBas.getAreaId() );
			}

			if ((null != risKnoBas.getCompanyName()) && (0 < risKnoBas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + risKnoBas.getCompanyName().trim() + "%");
			}

			if ((null != risKnoBas.getItemName()) && (0 < risKnoBas.getItemName().trim().length())){
				paraMap.put("itemName", "%" + risKnoBas.getItemName().trim() + "%");
			}

		}
		paraMap.put("deptType", "QY");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|itemName|dangerousContent|responseMeasures|";
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
		pagination = risKnoBasService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != risKnoBas)&&(null != risKnoBas.getId())){
			risKnoBas = risKnoBasService.getById(risKnoBas.getId());
			if(risKnoBas.getLinkId() == null || "".equals(risKnoBas.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				risKnoBas.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",risKnoBas.getLinkId());
				map.put("mkType", "wpwxxzsk");
				map.put("picType","wpwxxzskfj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			risKnoBas.setLinkId(linkId);
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
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		risKnoBas.setAreaId(enBaseInfo.getEnterprisePossession());
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		risKnoBas.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		risKnoBas.setCompanyId(enBaseInfo.getId());
		risKnoBas.setCompanyName(enBaseInfo.getEnterpriseName());
		risKnoBas.setDeptType("QY");
		if ("add".equalsIgnoreCase(this.flag)){
			risKnoBas.setDeptId(this.getLoginUserDepartmentId());
			risKnoBas.setDelFlag(0);
			risKnoBasService.save(risKnoBas);
		}else{
			risKnoBasService.update(risKnoBas);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != risKnoBas)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到risKnoBas中去
				
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
			risKnoBasService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void listAJ() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != risKnoBas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != risKnoBas.getAreaId()) && (0 < risKnoBas.getAreaId().trim().length())){
				paraMap.put("areaId",  risKnoBas.getAreaId() );
			}

			if ((null != risKnoBas.getCompanyName()) && (0 < risKnoBas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + risKnoBas.getCompanyName().trim() + "%");
			}

			if ((null != risKnoBas.getItemName()) && (0 < risKnoBas.getItemName().trim().length())){
				paraMap.put("itemName", "%" + risKnoBas.getItemName().trim() + "%");
			}

		}
		paraMap.put("deptType", "AJ");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|itemName|dangerousContent|responseMeasures|";
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
		pagination = risKnoBasService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String viewAJ() throws Exception{
		
		if((null != risKnoBas)&&(null != risKnoBas.getId())){
			risKnoBas = risKnoBasService.getById(risKnoBas.getId());
			if(risKnoBas.getLinkId() == null || "".equals(risKnoBas.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				risKnoBas.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",risKnoBas.getLinkId());
				map.put("mkType", "wpwxxzsk");
				map.put("picType","wpwxxzskfj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			risKnoBas.setLinkId(linkId);
		}
		
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEditAJ() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String saveAJ() throws Exception{
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("loginId", this.getLoginUser().getLoginId());
//		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
//		risKnoBas.setAreaId(enBaseInfo.getEnterprisePossession());
//		Map<String,Object> m = new HashMap<String,Object>();
//		m.put("codeName", "企业属地");
//		m.put("itemValue", enBaseInfo.getEnterprisePossession());
//		risKnoBas.setAreaName(codeService.findCodeValueByMap(m).getItemText());
//		risKnoBas.setCompanyId(enBaseInfo.getId());
//		risKnoBas.setCompanyName(enBaseInfo.getEnterpriseName());
		risKnoBas.setDeptType("AJ");
		if ("add".equalsIgnoreCase(this.flag)){
			risKnoBas.setDeptId(this.getLoginUserDepartmentId());
			risKnoBas.setDelFlag(0);
			risKnoBasService.save(risKnoBas);
		}else{
			risKnoBasService.update(risKnoBas);
		}
		
		return RELOAD;
	}


	/**
	 * 删除信息
	 */
	public String deleteAJ() throws Exception{
	    try{
			risKnoBasService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String wxxzsk(){
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
				break;
			}
		}
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != risKnoBas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != risKnoBas.getAreaId()) && (0 < risKnoBas.getAreaId().trim().length())){
				paraMap.put("areaId",  risKnoBas.getAreaId() );
			}

			if ((null != risKnoBas.getCompanyName()) && (0 < risKnoBas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + risKnoBas.getCompanyName().trim() + "%");
			}

			if ((null != risKnoBas.getItemName()) && (0 < risKnoBas.getItemName().trim().length())){
				paraMap.put("itemName", "%" + risKnoBas.getItemName().trim() + "%");
			}

		}
		paraMap.put("deptType", "QY");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|itemName|dangerousContent|responseMeasures|";
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
		pagination = risKnoBasService.findByPage(pagination, paraMap);
			
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
		    
		if(null != risKnoBas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != risKnoBas.getAreaId()) && (0 < risKnoBas.getAreaId().trim().length())){
				paraMap.put("areaId",  risKnoBas.getAreaId() );
			}

			if ((null != risKnoBas.getCompanyName()) && (0 < risKnoBas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + risKnoBas.getCompanyName().trim() + "%");
			}

			if ((null != risKnoBas.getItemName()) && (0 < risKnoBas.getItemName().trim().length())){
				paraMap.put("itemName", "%" + risKnoBas.getItemName().trim() + "%");
			}

		}
		paraMap.put("deptType", "AJ");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|itemName|dangerousContent|responseMeasures|";
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
			paraMap.put("itemName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = risKnoBasService.findByPage(pagination, paraMap);
			
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

	public RisKnoBas getRisKnoBas(){
		return this.risKnoBas;
	}

	public void setRisKnoBas(RisKnoBas risKnoBas){
		this.risKnoBas = risKnoBas;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
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
