package com.jshx.prosafedesign.web;

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
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.prosafedesign.entity.ProSafDes;
import com.jshx.prosafedesign.service.ProSafDesService;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class ProSafDesAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ProSafDes proSafDes = new ProSafDes();

	/**
	 * 业务类
	 */
	@Autowired
	private ProSafDesService proSafDesService;

	
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
	
	
	private Date queryRecordNumStart;

	private Date queryRecordNumEnd;

	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
    private String roleName;
    
    private int pageNo;
	
	private int pageSize;
	
	private String searchLike;
	
	
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
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A04")) 
			{
				roleName = "1";
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
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != proSafDes){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proSafDes.getAreaId()) && (0 < proSafDes.getAreaId().trim().length())){
				paraMap.put("areaId",proSafDes.getAreaId().trim() );
			}
			if ((null != proSafDes.getAreaName()) && (0 < proSafDes.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + proSafDes.getAreaName().trim() + "%");
			}

			if (null != queryRecordNumStart){
				paraMap.put("startRecordNum", queryRecordNumStart);
			}

			if (null != queryRecordNumEnd){
				paraMap.put("endRecordNum", queryRecordNumEnd);
			}
			if ((null != proSafDes.getProjectType()) && (0 < proSafDes.getProjectType().trim().length())){
				paraMap.put("projectType", proSafDes.getProjectType().trim());
			}

			if ((null != proSafDes.getCompanyName()) && (0 < proSafDes.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proSafDes.getCompanyName().trim() + "%");
			}

			if ((null != proSafDes.getProjectNature()) && (0 < proSafDes.getProjectNature().trim().length())){
				paraMap.put("projectNature", proSafDes.getProjectNature().trim());
			}
			if ((null != proSafDes.getRecordDate()) && (0 < proSafDes.getRecordDate().trim().length())){
				paraMap.put("recordDate",  "%" + proSafDes.getRecordDate().trim()+ "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|projectNature|projectType|recordNum|recordDate|createUserID|";
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
		pagination = proSafDesService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != proSafDes)&&(null != proSafDes.getId()))
		{
			proSafDes = proSafDesService.getById(proSafDes.getId());
				if(proSafDes.getLinkId() == null || "".equals(proSafDes.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						proSafDes.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",proSafDes.getLinkId());
							map.put("mkType", "prosafedesign");
							map.put("picType","prosafedesignfj");
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
					proSafDes.setLinkId(linkId);
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
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", proSafDes.getAreaId());
		proSafDes.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			proSafDes.setDeptId(this.getLoginUserDepartmentId());
			proSafDes.setDelFlag(0);
			proSafDesService.save(proSafDes);
		}else{
			proSafDesService.update(proSafDes);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != proSafDes)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到proSafDes中去
				
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
			proSafDesService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String zwtInit(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A04")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != proSafDes){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proSafDes.getAreaId()) && (0 < proSafDes.getAreaId().trim().length())){
				paraMap.put("areaId",proSafDes.getAreaId().trim() );
			}
			if ((null != proSafDes.getAreaName()) && (0 < proSafDes.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + proSafDes.getAreaName().trim() + "%");
			}

			if (null != queryRecordNumStart){
				paraMap.put("startRecordNum", queryRecordNumStart);
			}

			if (null != queryRecordNumEnd){
				paraMap.put("endRecordNum", queryRecordNumEnd);
			}
			if ((null != proSafDes.getProjectType()) && (0 < proSafDes.getProjectType().trim().length())){
				paraMap.put("projectType", proSafDes.getProjectType().trim());
			}

			if ((null != proSafDes.getCompanyName()) && (0 < proSafDes.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proSafDes.getCompanyName().trim() + "%");
			}

			if ((null != proSafDes.getProjectNature()) && (0 < proSafDes.getProjectNature().trim().length())){
				paraMap.put("projectNature", proSafDes.getProjectNature().trim());
			}
			if ((null != proSafDes.getRecordDate()) && (0 < proSafDes.getRecordDate().trim().length())){
				paraMap.put("recordDate",  "%" + proSafDes.getRecordDate().trim()+ "%");
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或备案编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|projectNature|projectType|recordNum|recordDate|createUserID|";
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
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = proSafDesService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
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

	public ProSafDes getProSafDes(){
		return this.proSafDes;
	}

	public void setProSafDes(ProSafDes proSafDes){
		this.proSafDes = proSafDes;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryRecordNumStart(){
		return this.queryRecordNumStart;
	}

	public void setQueryRecordNumStart(Date queryRecordNumStart){
		this.queryRecordNumStart = queryRecordNumStart;
	}

	public Date getQueryRecordNumEnd(){
		return this.queryRecordNumEnd;
	}

	public void setQueryRecordNumEnd(Date queryRecordNumEnd){
		this.queryRecordNumEnd = queryRecordNumEnd;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
