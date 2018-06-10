package com.jshx.preprorecord.web;

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
import com.jshx.preprorecord.entity.PreProRec;
import com.jshx.preprorecord.service.PreProRecService;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class PreProRecAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PreProRec preProRec = new PreProRec();

	/**
	 * 业务类
	 */
	@Autowired
	private PreProRecService preProRecService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private PhotoPicService photoPicService;
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
	
	
	private Date queryRecordDateStart;

	private Date queryRecordDateEnd;

	private Date queryValidityStart;

	private Date queryValidityEnd;
	
    private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//非药品类易制毒化学品备案申请书
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//非药品类易制毒化学品备案申请材料
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//集体会审记录
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//非药品类易制毒化学品备案证明副本

	
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
			
			if(ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A06")) 
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
		    
		if(null != preProRec){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preProRec.getAreaName()) && (0 < preProRec.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + preProRec.getAreaName().trim() + "%");
			}
			if ((null != preProRec.getAreaId()) && (0 < preProRec.getAreaId().trim().length())){
				paraMap.put("areaId", preProRec.getAreaId().trim() );
			}
			if ((null != preProRec.getCompanyName()) && (0 < preProRec.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preProRec.getCompanyName().trim() + "%");
			}

			if ((null != preProRec.getAuthority()) && (0 < preProRec.getAuthority().trim().length())){
				paraMap.put("authority", "%" + preProRec.getAuthority().trim() + "%");
			}
			if ((null != preProRec.getFileNo()) && (0 < preProRec.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preProRec.getFileNo().trim() + "%");
			}


		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|companyName|authority|fileNo|areaName|createUserID|";
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
		pagination = preProRecService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != preProRec)&&(null != preProRec.getId()))
		{
			preProRec = preProRecService.getById(preProRec.getId());
				if(preProRec.getLinkId() == null || "".equals(preProRec.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						preProRec.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",preProRec.getLinkId());
							map.put("mkType", "preprorecord1");
							map.put("picType","preprorecordfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "preprorecord2");
							map.put("picType","preprorecordfj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "preprorecord3");
							map.put("picType","preprorecordfj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "preprorecord4");
							map.put("picType","preprorecordfj4");
							picList4 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					preProRec.setLinkId(linkId);
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
		m.put("itemValue", preProRec.getAreaId());
		preProRec.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			preProRec.setDeptId(this.getLoginUserDepartmentId());
			preProRec.setDelFlag(0);
			preProRecService.save(preProRec);
		}else{
			preProRecService.update(preProRec);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != preProRec)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到preProRec中去
				
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
			preProRecService.deleteWithFlag(ids);
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
			
			if(ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A06")) 
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
		    
		if(null != preProRec){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preProRec.getAreaName()) && (0 < preProRec.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + preProRec.getAreaName().trim() + "%");
			}
			if ((null != preProRec.getAreaId()) && (0 < preProRec.getAreaId().trim().length())){
				paraMap.put("areaId", preProRec.getAreaId().trim() );
			}
			if ((null != preProRec.getCompanyName()) && (0 < preProRec.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preProRec.getCompanyName().trim() + "%");
			}

			if ((null != preProRec.getAuthority()) && (0 < preProRec.getAuthority().trim().length())){
				paraMap.put("authority", "%" + preProRec.getAuthority().trim() + "%");
			}
			if ((null != preProRec.getFileNo()) && (0 < preProRec.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preProRec.getFileNo().trim() + "%");
			}


		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或档案编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|companyName|authority|fileNo|areaName|createUserID|";
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
		pagination = preProRecService.findByPage(pagination, paraMap);
		
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

	public PreProRec getPreProRec(){
		return this.preProRec;
	}

	public void setPreProRec(PreProRec preProRec){
		this.preProRec = preProRec;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryRecordDateStart(){
		return this.queryRecordDateStart;
	}

	public void setQueryRecordDateStart(Date queryRecordDateStart){
		this.queryRecordDateStart = queryRecordDateStart;
	}

	public Date getQueryRecordDateEnd(){
		return this.queryRecordDateEnd;
	}

	public void setQueryRecordDateEnd(Date queryRecordDateEnd){
		this.queryRecordDateEnd = queryRecordDateEnd;
	}

	public Date getQueryValidityStart(){
		return this.queryValidityStart;
	}

	public void setQueryValidityStart(Date queryValidityStart){
		this.queryValidityStart = queryValidityStart;
	}

	public Date getQueryValidityEnd(){
		return this.queryValidityEnd;
	}

	public void setQueryValidityEnd(Date queryValidityEnd){
		this.queryValidityEnd = queryValidityEnd;
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

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
	}

	public List<PhotoPic> getPicList4() {
		return picList4;
	}

	public void setPicList4(List<PhotoPic> picList4) {
		this.picList4 = picList4;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
