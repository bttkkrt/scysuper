package com.jshx.wxhxpzdwxyhx.web;

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
import com.jshx.wxhxpzdwxyhx.entity.MajSouVer;
import com.jshx.wxhxpzdwxyhx.service.MajSouVerService;

public class MajSouVerAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private MajSouVer majSouVer = new MajSouVer();

	/**
	 * 业务类
	 */
	@Autowired
	private MajSouVerService majSouVerService;
	
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
	
	private Date queryUploadTimeStart;

	private Date queryUploadTimeEnd;
	
	private String roleName;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	 private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//核销申请
		
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//核销登记
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	
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
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != majSouVer){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != majSouVer.getAreaId()) && (0 < majSouVer.getAreaId().trim().length())){
				paraMap.put("areaId", majSouVer.getAreaId().trim() );
			}

			if ((null != majSouVer.getAreaName()) && (0 < majSouVer.getAreaName().trim().length())){
				paraMap.put("areaName", majSouVer.getAreaName().trim());
			}

			if ((null != majSouVer.getCompanyName()) && (0 < majSouVer.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + majSouVer.getCompanyName().trim() + "%");
			}
			if (null != queryUploadTimeStart){
				paraMap.put("startUploadTime", queryUploadTimeStart);
			}

			if (null != queryUploadTimeEnd){
				paraMap.put("endUploadTime", queryUploadTimeEnd);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId


		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|uploadTime|";
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
		pagination = majSouVerService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
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
		    
		if(null != majSouVer){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != majSouVer.getAreaId()) && (0 < majSouVer.getAreaId().trim().length())){
				paraMap.put("areaId", majSouVer.getAreaId().trim() );
			}

			if ((null != majSouVer.getAreaName()) && (0 < majSouVer.getAreaName().trim().length())){
				paraMap.put("areaName", majSouVer.getAreaName().trim());
			}

			if ((null != majSouVer.getCompanyName()) && (0 < majSouVer.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + majSouVer.getCompanyName().trim() + "%");
			}
			if (null != queryUploadTimeStart){
				paraMap.put("startUploadTime", queryUploadTimeStart);
			}

			if (null != queryUploadTimeEnd){
				paraMap.put("endUploadTime", queryUploadTimeEnd);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId


		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|uploadTime|";
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
		pagination = majSouVerService.findByPage(pagination, paraMap);
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

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != majSouVer)&&(null != majSouVer.getId()))
		{
			majSouVer = majSouVerService.getById(majSouVer.getId());
				if(majSouVer.getLinkId() == null || "".equals(majSouVer.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						majSouVer.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",majSouVer.getLinkId());
							map.put("mkType", "wxhxpzdwxyhx1");
							map.put("picType","wxhxpzdwxyhxfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "wxhxpzdwxyhx2");
							map.put("picType","wxhxpzdwxyhxfj2");
							picList2 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					majSouVer.setLinkId(linkId);
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
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		majSouVer.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		majSouVer.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		majSouVer.setCompanyId(enBaseInfo.getId());
		majSouVer.setCompanyName(enBaseInfo.getEnterpriseName());
		Date date=new Date();
		majSouVer.setUploadTime(date);
		if ("add".equalsIgnoreCase(this.flag)){
			majSouVer.setDeptId(this.getLoginUserDepartmentId());
			majSouVer.setDelFlag(0);
			
			majSouVerService.save(majSouVer);
		}else{
			majSouVerService.update(majSouVer);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != majSouVer)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到majSouVer中去
				
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
			majSouVerService.deleteWithFlag(ids);
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

	public MajSouVer getMajSouVer(){
		return this.majSouVer;
	}

	public void setMajSouVer(MajSouVer majSouVer){
		this.majSouVer = majSouVer;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getQueryUploadTimeStart() {
		return queryUploadTimeStart;
	}

	public void setQueryUploadTimeStart(Date queryUploadTimeStart) {
		this.queryUploadTimeStart = queryUploadTimeStart;
	}

	public Date getQueryUploadTimeEnd() {
		return queryUploadTimeEnd;
	}

	public void setQueryUploadTimeEnd(Date queryUploadTimeEnd) {
		this.queryUploadTimeEnd = queryUploadTimeEnd;
	}

	
       
    
}
