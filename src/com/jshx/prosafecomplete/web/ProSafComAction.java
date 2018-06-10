package com.jshx.prosafecomplete.web;

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
import com.jshx.prosafecomplete.entity.ProSafCom;
import com.jshx.prosafecomplete.service.ProSafComService;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class ProSafComAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ProSafCom proSafCom = new ProSafCom();

	/**
	 * 业务类
	 */
	@Autowired
	private ProSafComService proSafComService;

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
	
	
	private Date queryAcceptanceDateStart;

	private Date queryAcceptanceDateEnd;

	private Date queryApprovalDateStart;

	private Date queryApprovalDateEnd;
	
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
		    
		if(null != proSafCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proSafCom.getAreaId()) && (0 < proSafCom.getAreaId().trim().length())){
				paraMap.put("areaId", proSafCom.getAreaId().trim());
			}
			if ((null != proSafCom.getAreaName()) && (0 < proSafCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + proSafCom.getAreaName().trim() + "%");
			}

			if ((null != proSafCom.getCompanyName()) && (0 < proSafCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proSafCom.getCompanyName().trim() + "%");
			}

			if ((null != proSafCom.getProjectNature()) && (0 < proSafCom.getProjectNature().trim().length())){
				paraMap.put("projectNature", proSafCom.getProjectNature().trim());
			}
			if ((null != proSafCom.getProjectType()) && (0 < proSafCom.getProjectType().trim().length())){
				paraMap.put("projectType", proSafCom.getProjectType().trim());
			}
			if ((null != proSafCom.getApprovalNum()) && (0 < proSafCom.getApprovalNum().trim().length())){
				paraMap.put("approvalNum", "%" + proSafCom.getApprovalNum().trim() + "%");
			}
			if ((null != proSafCom.getEvaluationUnit()) && (0 < proSafCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + proSafCom.getEvaluationUnit().trim() + "%");
			}
			if (null != queryApprovalDateStart){
				paraMap.put("startApprovalDate", queryApprovalDateStart);
			}

			if (null != queryApprovalDateEnd){
				paraMap.put("endApprovalDate", queryApprovalDateEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|evaluationUnit|projectNature|projectType|acceptanceExpert|acceptanceDate|approvalNum|approvalDate|areaName|companyName|createUserID|";
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
		pagination = proSafComService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != proSafCom)&&(null != proSafCom.getId()))
		{
			proSafCom = proSafComService.getById(proSafCom.getId());
				if(proSafCom.getLinkId() == null || "".equals(proSafCom.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						proSafCom.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",proSafCom.getLinkId());
							map.put("mkType", "prosafecomplete");
							map.put("picType","prosafecompletefj");
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
					proSafCom.setLinkId(linkId);
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
		m.put("itemValue", proSafCom.getAreaId());
		proSafCom.setAreaName(codeService.findCodeValueByMap(m).getItemText());
	
		if ("add".equalsIgnoreCase(this.flag)){
			proSafCom.setDeptId(this.getLoginUserDepartmentId());
			proSafCom.setDelFlag(0);
			proSafComService.save(proSafCom);
		}else{
			proSafComService.update(proSafCom);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != proSafCom)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到proSafCom中去
				
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
			proSafComService.deleteWithFlag(ids);
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
		    
		if(null != proSafCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proSafCom.getAreaId()) && (0 < proSafCom.getAreaId().trim().length())){
				paraMap.put("areaId", proSafCom.getAreaId().trim());
			}
			if ((null != proSafCom.getAreaName()) && (0 < proSafCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + proSafCom.getAreaName().trim() + "%");
			}

			if ((null != proSafCom.getCompanyName()) && (0 < proSafCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proSafCom.getCompanyName().trim() + "%");
			}

			if ((null != proSafCom.getProjectNature()) && (0 < proSafCom.getProjectNature().trim().length())){
				paraMap.put("projectNature", proSafCom.getProjectNature().trim());
			}
			if ((null != proSafCom.getProjectType()) && (0 < proSafCom.getProjectType().trim().length())){
				paraMap.put("projectType", proSafCom.getProjectType().trim());
			}
			if ((null != proSafCom.getApprovalNum()) && (0 < proSafCom.getApprovalNum().trim().length())){
				paraMap.put("approvalNum", "%" + proSafCom.getApprovalNum().trim() + "%");
			}
			if ((null != proSafCom.getEvaluationUnit()) && (0 < proSafCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + proSafCom.getEvaluationUnit().trim() + "%");
			}
			if (null != queryApprovalDateStart){
				paraMap.put("startApprovalDate", queryApprovalDateStart);
			}

			if (null != queryApprovalDateEnd){
				paraMap.put("endApprovalDate", queryApprovalDateEnd);
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、评价单位或审批编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|evaluationUnit|projectNature|projectType|acceptanceExpert|acceptanceDate|approvalNum|approvalDate|areaName|companyName|createUserID|";
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
		pagination = proSafComService.findByPage(pagination, paraMap);
		
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

	public ProSafCom getProSafCom(){
		return this.proSafCom;
	}

	public void setProSafCom(ProSafCom proSafCom){
		this.proSafCom = proSafCom;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryAcceptanceDateStart(){
		return this.queryAcceptanceDateStart;
	}

	public void setQueryAcceptanceDateStart(Date queryAcceptanceDateStart){
		this.queryAcceptanceDateStart = queryAcceptanceDateStart;
	}

	public Date getQueryAcceptanceDateEnd(){
		return this.queryAcceptanceDateEnd;
	}

	public void setQueryAcceptanceDateEnd(Date queryAcceptanceDateEnd){
		this.queryAcceptanceDateEnd = queryAcceptanceDateEnd;
	}

	public Date getQueryApprovalDateStart(){
		return this.queryApprovalDateStart;
	}

	public void setQueryApprovalDateStart(Date queryApprovalDateStart){
		this.queryApprovalDateStart = queryApprovalDateStart;
	}

	public Date getQueryApprovalDateEnd(){
		return this.queryApprovalDateEnd;
	}

	public void setQueryApprovalDateEnd(Date queryApprovalDateEnd){
		this.queryApprovalDateEnd = queryApprovalDateEnd;
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
