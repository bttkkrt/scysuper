package com.jshx.fczycsgl.web;

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
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.fczycsgl.entity.DusWorMan;
import com.jshx.fczycsgl.service.DusWorManService;
import com.jshx.http.service.HttpService;

public class DusWorManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DusWorMan dusWorMan = new DusWorMan();

	/**
	 * 业务类
	 */
	@Autowired
	private DusWorManService dusWorManService;
	
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
	
	
	private Date queryRecentlyDustDetectTimeStart;

	private Date queryRecentlyDustDetectTimeEnd;

	private Date queryWiperInUseTimeStart;

	private Date queryWiperInUseTimeEnd;

	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//隔爆阀照片
    private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//泄爆口照片
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//自动卸灰锁气装置图片
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//作业场所图片
	
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();//除尘器全景图片
	
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
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != dusWorMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dusWorMan.getAreaId()) && (0 < dusWorMan.getAreaId().trim().length())){
				paraMap.put("areaId",  dusWorMan.getAreaId().trim() );
			}

			if ((null != dusWorMan.getCompanyName()) && (0 < dusWorMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + dusWorMan.getCompanyName().trim() + "%");
			}

			if ((null != dusWorMan.getWorkplaceName()) && (0 < dusWorMan.getWorkplaceName().trim().length())){
				paraMap.put("workplaceName", "%" + dusWorMan.getWorkplaceName().trim() + "%");
			}

			if ((null != dusWorMan.getAgencyResponsible()) && (0 < dusWorMan.getAgencyResponsible().trim().length())){
				paraMap.put("agencyResponsible", dusWorMan.getAgencyResponsible().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeIds
codeMap.put("agencyResponsible","40288008416c6c1a01416e0b21b00352");
codeMap.put("industryType","40288008416c6c1a01416e0c70b60358");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|workplaceName|agencyResponsible|industryType|";
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
		pagination = dusWorManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dusWorMan)&&(null != dusWorMan.getId()))
		{
			dusWorMan = dusWorManService.getById(dusWorMan.getId());
				if(dusWorMan.getLinkId() == null || "".equals(dusWorMan.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						dusWorMan.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",dusWorMan.getLinkId());
							map.put("mkType", "fczycsgl1");
							map.put("picType","fczycsglfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "fczycsgl2");
							map.put("picType","fczycsglfj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "fczycsgl3");
							map.put("picType","fczycsglfj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "fczycsgl4");
							map.put("picType","fczycsglfj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "fczycsgl5");
							map.put("picType","fczycsglfj5");
							picList5 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					dusWorMan.setLinkId(linkId);
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
		dusWorMan.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		dusWorMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		dusWorMan.setCompanyId(enBaseInfo.getId());
		dusWorMan.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			dusWorMan.setDeptId(this.getLoginUserDepartmentId());
			dusWorMan.setDelFlag(0);
			

			dusWorManService.save(dusWorMan);
		}else{
			dusWorManService.update(dusWorMan);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != dusWorMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到dusWorMan中去
				
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
			dusWorManService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String fczycs(){
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
	@SuppressWarnings("unchecked")
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != dusWorMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dusWorMan.getAreaId()) && (0 < dusWorMan.getAreaId().trim().length())){
				paraMap.put("areaId",  dusWorMan.getAreaId().trim() );
			}

			if ((null != dusWorMan.getCompanyName()) && (0 < dusWorMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + dusWorMan.getCompanyName().trim() + "%");
			}

			if ((null != dusWorMan.getWorkplaceName()) && (0 < dusWorMan.getWorkplaceName().trim().length())){
				paraMap.put("workplaceName", "%" + dusWorMan.getWorkplaceName().trim() + "%");
			}

			if ((null != dusWorMan.getAgencyResponsible()) && (0 < dusWorMan.getAgencyResponsible().trim().length())){
				paraMap.put("agencyResponsible", dusWorMan.getAgencyResponsible().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeIds
		codeMap.put("agencyResponsible","40288008416c6c1a01416e0b21b00352");
		codeMap.put("industryType","40288008416c6c1a01416e0c70b60358");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|workplaceName|agencyResponsible|industryType|";
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
		pagination = dusWorManService.findByPage(pagination, paraMap);
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

	public DusWorMan getDusWorMan(){
		return this.dusWorMan;
	}

	public void setDusWorMan(DusWorMan dusWorMan){
		this.dusWorMan = dusWorMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryRecentlyDustDetectTimeStart(){
		return this.queryRecentlyDustDetectTimeStart;
	}

	public void setQueryRecentlyDustDetectTimeStart(Date queryRecentlyDustDetectTimeStart){
		this.queryRecentlyDustDetectTimeStart = queryRecentlyDustDetectTimeStart;
	}

	public Date getQueryRecentlyDustDetectTimeEnd(){
		return this.queryRecentlyDustDetectTimeEnd;
	}

	public void setQueryRecentlyDustDetectTimeEnd(Date queryRecentlyDustDetectTimeEnd){
		this.queryRecentlyDustDetectTimeEnd = queryRecentlyDustDetectTimeEnd;
	}

	public Date getQueryWiperInUseTimeStart(){
		return this.queryWiperInUseTimeStart;
	}

	public void setQueryWiperInUseTimeStart(Date queryWiperInUseTimeStart){
		this.queryWiperInUseTimeStart = queryWiperInUseTimeStart;
	}

	public Date getQueryWiperInUseTimeEnd(){
		return this.queryWiperInUseTimeEnd;
	}

	public void setQueryWiperInUseTimeEnd(Date queryWiperInUseTimeEnd){
		this.queryWiperInUseTimeEnd = queryWiperInUseTimeEnd;
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

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
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
