package com.jshx.aqssdjtz.web;

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
import com.jshx.aqssdjtz.entity.SafLed;
import com.jshx.aqssdjtz.service.SafLedService;

public class SafLedAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SafLed safLed = new SafLed();

	/**
	 * 业务类
	 */
	@Autowired
	private SafLedService safLedService;
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
	
	private String roleName;
	
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
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
		    
		if(null != safLed){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safLed.getAreaId()) && (0 < safLed.getAreaId().trim().length())){
				paraMap.put("areaId", safLed.getAreaId().trim());
			}

			if ((null != safLed.getAreaName()) && (0 < safLed.getAreaName().trim().length())){
				paraMap.put("areaName", safLed.getAreaName().trim());
			}

			if ((null != safLed.getCompanyName()) && (0 < safLed.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + safLed.getCompanyName().trim() + "%");
			}

			if ((null != safLed.getAccountType()) && (0 < safLed.getAccountType().trim().length())){
				paraMap.put("accountType", safLed.getAccountType().trim());
			}

			if ((null != safLed.getCategory1()) && (0 < safLed.getCategory1().trim().length())){
				paraMap.put("category", safLed.getCategory1().trim());
			}
			if ((null != safLed.getCategory2()) && (0 < safLed.getCategory2().trim().length())){
				paraMap.put("category", safLed.getCategory2().trim());
			}
			if ((null != safLed.getCategory3()) && (0 < safLed.getCategory3().trim().length())){
				paraMap.put("category", safLed.getCategory3().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		//codeMap.put("accountType","40288008416c6c1a01416dd6af5c02a5");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|accountType|category|";
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
		pagination = safLedService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != safLed)&&(null != safLed.getId()))
		{
			safLed = safLedService.getById(safLed.getId());
				if(safLed.getLinkId() == null || "".equals(safLed.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						safLed.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",safLed.getLinkId());
							map.put("mkType", "aqssdjtz");
							map.put("picType","aqssdjtzfj");
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
					safLed.setLinkId(linkId);
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
		safLed.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		safLed.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		safLed.setCompanyId(enBaseInfo.getId());
		safLed.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			safLed.setDeptId(this.getLoginUserDepartmentId());
			safLed.setDelFlag(0);
			

			 /**
			    * 判断类型并把其值设置到台账类别中
			    */
			   if(safLed.getCategory1()!=null&&safLed.getCategory1().length()>0)
				   safLed.setCategory(safLed.getCategory1());
			   else if(safLed.getCategory2()!=null&&safLed.getCategory2().length()>0)
				   safLed.setCategory(safLed.getCategory2());
			   else
				   safLed.setCategory(safLed.getCategory3());
			safLedService.save(safLed);
		}else{
			 /**
			    * 判断类型并把其值设置到台账类别中
			    */
			   if(safLed.getCategory1()!=null&&safLed.getCategory1().length()>0)
				   safLed.setCategory(safLed.getCategory1());
			   else if(safLed.getCategory2()!=null&&safLed.getCategory2().length()>0)
				   safLed.setCategory(safLed.getCategory2());
			   else
				   safLed.setCategory(safLed.getCategory3());
			safLedService.update(safLed);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != safLed)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到safLed中去
				
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
			safLedService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	

	
	public String  aqssdjtz(){
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
		    
		if(null != safLed){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safLed.getAreaId()) && (0 < safLed.getAreaId().trim().length())){
				paraMap.put("areaId", safLed.getAreaId().trim());
			}

			if ((null != safLed.getAreaName()) && (0 < safLed.getAreaName().trim().length())){
				paraMap.put("areaName", safLed.getAreaName().trim());
			}

			if ((null != safLed.getCompanyName()) && (0 < safLed.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + safLed.getCompanyName().trim() + "%");
			}

			if ((null != safLed.getAccountType()) && (0 < safLed.getAccountType().trim().length())){
				paraMap.put("accountType", safLed.getAccountType().trim());
			}

			if ((null != safLed.getCategory1()) && (0 < safLed.getCategory1().trim().length())){
				paraMap.put("category", safLed.getCategory1().trim());
			}
			if ((null != safLed.getCategory2()) && (0 < safLed.getCategory2().trim().length())){
				paraMap.put("category", safLed.getCategory2().trim());
			}
			if ((null != safLed.getCategory3()) && (0 < safLed.getCategory3().trim().length())){
				paraMap.put("category", safLed.getCategory3().trim());
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		//codeMap.put("accountType","40288008416c6c1a01416dd6af5c02a5");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|accountType|category|";
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
		pagination = safLedService.findByPage(pagination, paraMap);
		
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

	public SafLed getSafLed(){
		return this.safLed;
	}

	public void setSafLed(SafLed safLed){
		this.safLed = safLed;
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
