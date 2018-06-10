package com.jshx.ybqyapbgba.web;

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
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.ybqyapbgba.entity.AssRepFil;
import com.jshx.ybqyapbgba.service.AssRepFilService;

public class AssRepFilAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private AssRepFil assRepFil = new AssRepFil();

	/**
	 * 业务类
	 */
	@Autowired
	private AssRepFilService assRepFilService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date querySubmitDateStart;

	private Date querySubmitDateEnd;

	private Date queryCompleteDateStart;

	private Date queryCompleteDateEnd;

	private Date queryNextRecordDateStart;

	private Date queryNextRecordDateEnd;

	private Date queryRecordDateStart;

	private Date queryRecordDateEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
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
		if(httpService.judgeRoleCode(userId, "A04")||httpService.judgeRoleCode(userId, "A03")){
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
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != assRepFil){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != assRepFil.getAreaId()) && (0 < assRepFil.getAreaId().trim().length())){
				paraMap.put("areaId", assRepFil.getAreaId() );
			}

			if ((null != assRepFil.getCompanyName()) && (0 < assRepFil.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + assRepFil.getCompanyName().trim() + "%");
			}

			if ((null != assRepFil.getRecordNum()) && (0 < assRepFil.getRecordNum().trim().length())){
				paraMap.put("recordNum", "%" + assRepFil.getRecordNum().trim() + "%");
			}

			if (null != queryRecordDateStart){
				paraMap.put("startRecordDate", queryRecordDateStart);
			}

			if (null != queryRecordDateEnd){
				paraMap.put("endRecordDate", queryRecordDateEnd);
			}
			if ((null != assRepFil.getRatingAgenciesName()) && (0 < assRepFil.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + assRepFil.getRatingAgenciesName().trim() + "%");
			}

			if (null != querySubmitDateStart){
				paraMap.put("startSubmitDate", querySubmitDateStart);
			}

			if (null != querySubmitDateEnd){
				paraMap.put("endSubmitDate", querySubmitDateEnd);
			}
			if (null != queryCompleteDateStart){
				paraMap.put("startCompleteDate", queryCompleteDateStart);
			}

			if (null != queryCompleteDateEnd){
				paraMap.put("endCompleteDate", queryCompleteDateEnd);
			}
			if (null != queryNextRecordDateStart){
				paraMap.put("startNextRecordDate", queryNextRecordDateStart);
			}

			if (null != queryNextRecordDateEnd){
				paraMap.put("endNextRecordDate", queryNextRecordDateEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|ratingAgenciesName|submitDate|completeDate|nextRecordDate|areaId|companyName|recordNum|recordDate|createUserID|";
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
		pagination = assRepFilService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != assRepFil)&&(null != assRepFil.getId())){
			assRepFil = assRepFilService.getById(assRepFil.getId());
			if(assRepFil.getLinkId() == null || "".equals(assRepFil.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				assRepFil.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",assRepFil.getLinkId());
				map.put("mkType", "ybqyapbgba");
				map.put("picType","ybqyapbgbafj");
				picList = photoPicService.findPicPath(map); 
				
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			assRepFil.setLinkId(linkId);
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
			assRepFil.setDeptId(this.getLoginUserDepartmentId());
			assRepFil.setDelFlag(0);
			assRepFilService.save(assRepFil);
		}else{
			assRepFilService.update(assRepFil);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != assRepFil)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到assRepFil中去
				
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
			assRepFilService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String zwtInit(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A04")||httpService.judgeRoleCode(userId, "A03")){
			operateRight="add";
		}else if(httpService.judgeRoleCode(userId, "A23")){
			operateRight="qy";
		}else{
			operateRight="aj";
		}
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != assRepFil){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != assRepFil.getAreaId()) && (0 < assRepFil.getAreaId().trim().length())){
				paraMap.put("areaId", assRepFil.getAreaId() );
			}

			if ((null != assRepFil.getCompanyName()) && (0 < assRepFil.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + assRepFil.getCompanyName().trim() + "%");
			}

			if ((null != assRepFil.getRecordNum()) && (0 < assRepFil.getRecordNum().trim().length())){
				paraMap.put("recordNum", "%" + assRepFil.getRecordNum().trim() + "%");
			}

			if (null != queryRecordDateStart){
				paraMap.put("startRecordDate", queryRecordDateStart);
			}

			if (null != queryRecordDateEnd){
				paraMap.put("endRecordDate", queryRecordDateEnd);
			}
			if ((null != assRepFil.getRatingAgenciesName()) && (0 < assRepFil.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + assRepFil.getRatingAgenciesName().trim() + "%");
			}

			if (null != querySubmitDateStart){
				paraMap.put("startSubmitDate", querySubmitDateStart);
			}

			if (null != querySubmitDateEnd){
				paraMap.put("endSubmitDate", querySubmitDateEnd);
			}
			if (null != queryCompleteDateStart){
				paraMap.put("startCompleteDate", queryCompleteDateStart);
			}

			if (null != queryCompleteDateEnd){
				paraMap.put("endCompleteDate", queryCompleteDateEnd);
			}
			if (null != queryNextRecordDateStart){
				paraMap.put("startNextRecordDate", queryNextRecordDateStart);
			}

			if (null != queryNextRecordDateEnd){
				paraMap.put("endNextRecordDate", queryNextRecordDateEnd);
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、备案编号或评价机构名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|ratingAgenciesName|submitDate|completeDate|nextRecordDate|areaId|companyName|recordNum|recordDate|createUserID|";
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
		pagination = assRepFilService.findByPage(pagination, paraMap);
		
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

	public AssRepFil getAssRepFil(){
		return this.assRepFil;
	}

	public void setAssRepFil(AssRepFil assRepFil){
		this.assRepFil = assRepFil;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQuerySubmitDateStart(){
		return this.querySubmitDateStart;
	}

	public void setQuerySubmitDateStart(Date querySubmitDateStart){
		this.querySubmitDateStart = querySubmitDateStart;
	}

	public Date getQuerySubmitDateEnd(){
		return this.querySubmitDateEnd;
	}

	public void setQuerySubmitDateEnd(Date querySubmitDateEnd){
		this.querySubmitDateEnd = querySubmitDateEnd;
	}

	public Date getQueryCompleteDateStart(){
		return this.queryCompleteDateStart;
	}

	public void setQueryCompleteDateStart(Date queryCompleteDateStart){
		this.queryCompleteDateStart = queryCompleteDateStart;
	}

	public Date getQueryCompleteDateEnd(){
		return this.queryCompleteDateEnd;
	}

	public void setQueryCompleteDateEnd(Date queryCompleteDateEnd){
		this.queryCompleteDateEnd = queryCompleteDateEnd;
	}

	public Date getQueryNextRecordDateStart(){
		return this.queryNextRecordDateStart;
	}

	public void setQueryNextRecordDateStart(Date queryNextRecordDateStart){
		this.queryNextRecordDateStart = queryNextRecordDateStart;
	}

	public Date getQueryNextRecordDateEnd(){
		return this.queryNextRecordDateEnd;
	}

	public void setQueryNextRecordDateEnd(Date queryNextRecordDateEnd){
		this.queryNextRecordDateEnd = queryNextRecordDateEnd;
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

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	public String getOperateRight() {
		return operateRight;
	}
	public void setOperateRight(String operateRight) {
		this.operateRight = operateRight;
	}

}
