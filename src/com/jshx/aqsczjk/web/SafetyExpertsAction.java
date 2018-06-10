package com.jshx.aqsczjk.web;

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
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.aqsczjk.entity.SafetyExperts;
import com.jshx.aqsczjk.service.SafetyExpertsService;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.tzgg.service.TzggService;

public class SafetyExpertsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SafetyExperts safetyExperts = new SafetyExperts();

	/**
	 * 业务类
	 */
	@Autowired
	private SafetyExpertsService safetyExpertsService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryBirthStart;

	private Date queryBirthEnd;

	private Date queryGraduationTimeStart;

	private Date queryGraduationTimeEnd;

	private int pageNum=1;
		
	private int totalCount;
		
	private int totalPage;
		
	private List<SafetyExperts> aqsczjList=new ArrayList<SafetyExperts>();
		
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

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safetyExperts){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safetyExperts.getSafetyName()) && (0 < safetyExperts.getSafetyName().trim().length())){
				paraMap.put("safetyName", "%" + safetyExperts.getSafetyName().trim() + "%");
			}

			if ((null != safetyExperts.getMobile()) && (0 < safetyExperts.getMobile().trim().length())){
				paraMap.put("mobile", "%" + safetyExperts.getMobile().trim() + "%");
			}

			if ((null != safetyExperts.getJobTitle()) && (0 < safetyExperts.getJobTitle().trim().length())){
				paraMap.put("jobTitle", "%" + safetyExperts.getJobTitle().trim() + "%");
			}

			if ((null != safetyExperts.getEducation()) && (0 < safetyExperts.getEducation().trim().length())){
				paraMap.put("education", safetyExperts.getEducation().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("education","402880fc501c2be401501c33a996000a");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|safetyName|education|jobTitle|mobile|createUserID|";
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
		pagination = safetyExpertsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != safetyExperts)&&(null != safetyExperts.getId()))
			safetyExperts = safetyExpertsService.getById(safetyExperts.getId());
		
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
			safetyExperts.setDeptId(this.getLoginUserDepartmentId());
			safetyExperts.setDelFlag(0);
			safetyExpertsService.save(safetyExperts);
		}else{
			safetyExpertsService.update(safetyExperts);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != safetyExperts)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到safetyExperts中去
				
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
			safetyExpertsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String aqsczjList()
	{
		aqsczjList = safetyExpertsService.findAllInfo(null, pageNum, 10);
		totalCount = safetyExpertsService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String aqsczjContent()
	{
		safetyExperts = safetyExpertsService.getById(safetyExperts.getId());
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safetyExperts){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safetyExperts.getSafetyName()) && (0 < safetyExperts.getSafetyName().trim().length())){
				paraMap.put("safetyName", "%" + safetyExperts.getSafetyName().trim() + "%");
			}

			if ((null != safetyExperts.getMobile()) && (0 < safetyExperts.getMobile().trim().length())){
				paraMap.put("mobile", "%" + safetyExperts.getMobile().trim() + "%");
			}

			if ((null != safetyExperts.getJobTitle()) && (0 < safetyExperts.getJobTitle().trim().length())){
				paraMap.put("jobTitle", "%" + safetyExperts.getJobTitle().trim() + "%");
			}

			if ((null != safetyExperts.getEducation()) && (0 < safetyExperts.getEducation().trim().length())){
				paraMap.put("education", safetyExperts.getEducation().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("education","402880fc501c2be401501c33a996000a");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|safetyName|education|jobTitle|mobile|createUserID|";
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
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = safetyExpertsService.findByPage(pagination, paraMap);
			
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

	public SafetyExperts getSafetyExperts(){
		return this.safetyExperts;
	}

	public void setSafetyExperts(SafetyExperts safetyExperts){
		this.safetyExperts = safetyExperts;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryBirthStart(){
		return this.queryBirthStart;
	}

	public void setQueryBirthStart(Date queryBirthStart){
		this.queryBirthStart = queryBirthStart;
	}

	public Date getQueryBirthEnd(){
		return this.queryBirthEnd;
	}

	public void setQueryBirthEnd(Date queryBirthEnd){
		this.queryBirthEnd = queryBirthEnd;
	}

	public Date getQueryGraduationTimeStart(){
		return this.queryGraduationTimeStart;
	}

	public void setQueryGraduationTimeStart(Date queryGraduationTimeStart){
		this.queryGraduationTimeStart = queryGraduationTimeStart;
	}

	public Date getQueryGraduationTimeEnd(){
		return this.queryGraduationTimeEnd;
	}

	public void setQueryGraduationTimeEnd(Date queryGraduationTimeEnd){
		this.queryGraduationTimeEnd = queryGraduationTimeEnd;
	}

	public List<SafetyExperts> getAqsczjList() {
		return aqsczjList;
	}

	public void setAqsczjList(List<SafetyExperts> aqsczjList) {
		this.aqsczjList = aqsczjList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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
