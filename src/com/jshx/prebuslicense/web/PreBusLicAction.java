package com.jshx.prebuslicense.web;

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
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.prebuslicense.entity.PreBusLic;
import com.jshx.prebuslicense.service.PreBusLicService;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.service.CheckRecordService;

public class PreBusLicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PreBusLic preBusLic = new PreBusLic();

	/**
	 * 业务类
	 */
	@Autowired
	private PreBusLicService preBusLicService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryLicenseValidStart;

	private Date queryLicenseValidEnd;

	private Date queryIssuingDateStart;

	private Date queryIssuingDateEnd;

	private Date queryReceptDateStart;

	private Date queryReceptDateEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList10 = new ArrayList<PhotoPic>();
	
	@Autowired
	private HttpService httpService;
	
	private String operateRight="";
	
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
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A05")||httpService.judgeRoleCode(userId, "A06")){
			operateRight="add";
		}else if(httpService.judgeRoleCode(userId, "A23")){
			operateRight="qy";
		}else{
			operateRight="aj";
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
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != preBusLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preBusLic.getAreaId()) && (0 < preBusLic.getAreaId().trim().length())){
				paraMap.put("areaId",  preBusLic.getAreaId()  );
			}

			if ((null != preBusLic.getCompanyName()) && (0 < preBusLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preBusLic.getCompanyName().trim() + "%");
			}
			if ((null != preBusLic.getFileNo()) && (0 < preBusLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preBusLic.getFileNo().trim() + "%");
			}
			
			if ((null != preBusLic.getLicenseNumber()) && (0 < preBusLic.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + preBusLic.getLicenseNumber().trim() + "%");
			}

			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != preBusLic.getReviewName()) && (0 < preBusLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + preBusLic.getReviewName().trim() + "%");
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != preBusLic.getReceptName()) && (0 < preBusLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + preBusLic.getReceptName().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|licenseNumber|busductFlow|licenseValid|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|createUserID|";
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
		try{
			
			pagination = preBusLicService.findByPage(pagination, paraMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != preBusLic)&&(null != preBusLic.getId())){
			preBusLic = preBusLicService.getById(preBusLic.getId());
			if(preBusLic.getLinkId() == null || "".equals(preBusLic.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				preBusLic.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",preBusLic.getLinkId());
				map.put("mkType", "prebuslicense");
				map.put("picType","hsjlfj");
				picList1 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","fyplyzdhxpjyxkzsqsfj");
				picList2 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","gsyyzzfj");
				picList3 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","yzdhxpjyglzdhbkxsjgdlszzyhxsdddhdzdnrdxswlwjfj");
				picList4 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","dwfddbrhzyfzrhxsglryjyyzdhxpzsdzmclyjwdpfzjldzmclfj");
				picList5 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","dwfddbrhzyfzrhaqscglryjaqscjdglbmpxhghbfdaqzgzshzyzgzsfj");
				picList6 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","jycsccssqksmclfj");
				picList7 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","cpbzsmhsysmsfj");
				picList8 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","sywxhxpjydwdhxtjwxhxpjyxkzfj");
				picList9 = photoPicService.findPicPath(map); 
				map.put("mkType", "prebuslicense");
				map.put("picType","flfghgzgddqttjdzmwjzlfj");
				picList10 = photoPicService.findPicPath(map); 
				
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			preBusLic.setLinkId(linkId);
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
			preBusLic.setDeptId(this.getLoginUserDepartmentId());
			preBusLic.setDelFlag(0);
			preBusLicService.save(preBusLic);
		}else{
			preBusLicService.update(preBusLic);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != preBusLic)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到preBusLic中去
				
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
			preBusLicService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String zwtInit(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A05")||httpService.judgeRoleCode(userId, "A06")){
			operateRight="add";
		}else if(httpService.judgeRoleCode(userId, "A23")){
			operateRight="qy";
		}else{
			operateRight="aj";
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());

		String userId=this.getLoginUser().getId();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != preBusLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preBusLic.getAreaId()) && (0 < preBusLic.getAreaId().trim().length())){
				paraMap.put("areaId",  preBusLic.getAreaId()  );
			}

			if ((null != preBusLic.getCompanyName()) && (0 < preBusLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preBusLic.getCompanyName().trim() + "%");
			}
			if ((null != preBusLic.getFileNo()) && (0 < preBusLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preBusLic.getFileNo().trim() + "%");
			}
			
			if ((null != preBusLic.getLicenseNumber()) && (0 < preBusLic.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + preBusLic.getLicenseNumber().trim() + "%");
			}

			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != preBusLic.getReviewName()) && (0 < preBusLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + preBusLic.getReviewName().trim() + "%");
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != preBusLic.getReceptName()) && (0 < preBusLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + preBusLic.getReceptName().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或档案编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|licenseNumber|busductFlow|licenseValid|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|createUserID|";
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
		pagination = preBusLicService.findByPage(pagination, paraMap);
		
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

	public PreBusLic getPreBusLic(){
		return this.preBusLic;
	}

	public void setPreBusLic(PreBusLic preBusLic){
		this.preBusLic = preBusLic;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryLicenseValidStart(){
		return this.queryLicenseValidStart;
	}

	public void setQueryLicenseValidStart(Date queryLicenseValidStart){
		this.queryLicenseValidStart = queryLicenseValidStart;
	}

	public Date getQueryLicenseValidEnd(){
		return this.queryLicenseValidEnd;
	}

	public void setQueryLicenseValidEnd(Date queryLicenseValidEnd){
		this.queryLicenseValidEnd = queryLicenseValidEnd;
	}

	public Date getQueryIssuingDateStart(){
		return this.queryIssuingDateStart;
	}

	public void setQueryIssuingDateStart(Date queryIssuingDateStart){
		this.queryIssuingDateStart = queryIssuingDateStart;
	}

	public Date getQueryIssuingDateEnd(){
		return this.queryIssuingDateEnd;
	}

	public void setQueryIssuingDateEnd(Date queryIssuingDateEnd){
		this.queryIssuingDateEnd = queryIssuingDateEnd;
	}

	public Date getQueryReceptDateStart(){
		return this.queryReceptDateStart;
	}

	public void setQueryReceptDateStart(Date queryReceptDateStart){
		this.queryReceptDateStart = queryReceptDateStart;
	}

	public Date getQueryReceptDateEnd(){
		return this.queryReceptDateEnd;
	}

	public void setQueryReceptDateEnd(Date queryReceptDateEnd){
		this.queryReceptDateEnd = queryReceptDateEnd;
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

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
	}

	public List<PhotoPic> getPicList6() {
		return picList6;
	}

	public void setPicList6(List<PhotoPic> picList6) {
		this.picList6 = picList6;
	}

	public List<PhotoPic> getPicList7() {
		return picList7;
	}

	public void setPicList7(List<PhotoPic> picList7) {
		this.picList7 = picList7;
	}

	public List<PhotoPic> getPicList8() {
		return picList8;
	}

	public void setPicList8(List<PhotoPic> picList8) {
		this.picList8 = picList8;
	}

	public List<PhotoPic> getPicList9() {
		return picList9;
	}

	public void setPicList9(List<PhotoPic> picList9) {
		this.picList9 = picList9;
	}

	public List<PhotoPic> getPicList10() {
		return picList10;
	}

	public void setPicList10(List<PhotoPic> picList10) {
		this.picList10 = picList10;
	}
	public String getOperateRight() {
		return operateRight;
	}
	public void setOperateRight(String operateRight) {
		this.operateRight = operateRight;
	}


}
