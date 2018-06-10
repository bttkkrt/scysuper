package com.jshx.occconcomplete.web;

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
import com.jshx.occconcomplete.entity.OccConCom;
import com.jshx.occconcomplete.service.OccConComService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class OccConComAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private OccConCom occConCom = new OccConCom();

	/**
	 * 业务类
	 */
	@Autowired
	private OccConComService occConComService;
	
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

	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//职业病防护设施竣工验收报告
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//职业病防护设施竣工验收报告专家审查意见及审查会签到表
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//建设项目职业病防护设施竣工验收报告备案通知书或审批文件
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
    private String roleName;
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A07")||ur.getRole().getRoleCode().equals("A08")) 
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
		    
		if(null != occConCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occConCom.getAreaId()) && (0 < occConCom.getAreaId().trim().length())){
				paraMap.put("areaId",  occConCom.getAreaId().trim() );
			}
			if ((null != occConCom.getAreaName()) && (0 < occConCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + occConCom.getAreaName().trim() + "%");
			}

			if ((null != occConCom.getCompanyName()) && (0 < occConCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occConCom.getCompanyName().trim() + "%");
			}

			if ((null != occConCom.getOccupationalClassification()) && (0 < occConCom.getOccupationalClassification().trim().length())){
				paraMap.put("occupationalClassification", occConCom.getOccupationalClassification().trim());
			}

			if ((null != occConCom.getAcceptanceExpert()) && (0 < occConCom.getAcceptanceExpert().trim().length())){
				paraMap.put("acceptanceExpert", "%" + occConCom.getAcceptanceExpert().trim() + "%");
			}

			if ((null != occConCom.getEvaluationUnit()) && (0 < occConCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + occConCom.getEvaluationUnit().trim() + "%");
			}

			if ((null != occConCom.getReceptName()) && (0 < occConCom.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + occConCom.getReceptName().trim() + "%");
			}

			if ((null != occConCom.getReviewName()) && (0 < occConCom.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + occConCom.getReviewName().trim() + "%");
			}

			if ((null != occConCom.getIndustryCategory()) && (0 < occConCom.getIndustryCategory().trim().length())){
				paraMap.put("industryCategory", occConCom.getIndustryCategory().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("occupationalClassification","402880fc504a196f01504b64b81500d3");
codeMap.put("industryCategory","402880fc504a196f01504b63f3fd00cf");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|occupationalClassification|industryCategory|evaluationUnit|receptName|reviewName|acceptanceExpert|createUserID|";
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
		pagination = occConComService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != occConCom)&&(null != occConCom.getId()))
		{
			occConCom = occConComService.getById(occConCom.getId());
				if(occConCom.getLinkId() == null || "".equals(occConCom.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						occConCom.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",occConCom.getLinkId());
							map.put("mkType", "occconcomplete1");
							map.put("picType","occconcompletefj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "occconcomplete2");
							map.put("picType","occconcompletefj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "occconcomplete3");
							map.put("picType","occconcompletefj3");
							picList3 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					occConCom.setLinkId(linkId);
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
		m.put("itemValue", occConCom.getAreaId());
		occConCom.setAreaName(codeService.findCodeValueByMap(m).getItemText());
	
		if ("add".equalsIgnoreCase(this.flag)){
			occConCom.setDeptId(this.getLoginUserDepartmentId());
			occConCom.setDelFlag(0);
			occConComService.save(occConCom);
		}else{
			occConComService.update(occConCom);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != occConCom)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到occConCom中去
				
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
			occConComService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != occConCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occConCom.getAreaId()) && (0 < occConCom.getAreaId().trim().length())){
				paraMap.put("areaId",  occConCom.getAreaId().trim() );
			}
			if ((null != occConCom.getAreaName()) && (0 < occConCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + occConCom.getAreaName().trim() + "%");
			}

			if ((null != occConCom.getCompanyName()) && (0 < occConCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occConCom.getCompanyName().trim() + "%");
			}

			if ((null != occConCom.getOccupationalClassification()) && (0 < occConCom.getOccupationalClassification().trim().length())){
				paraMap.put("occupationalClassification", occConCom.getOccupationalClassification().trim());
			}

			if ((null != occConCom.getAcceptanceExpert()) && (0 < occConCom.getAcceptanceExpert().trim().length())){
				paraMap.put("acceptanceExpert", "%" + occConCom.getAcceptanceExpert().trim() + "%");
			}

			if ((null != occConCom.getEvaluationUnit()) && (0 < occConCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + occConCom.getEvaluationUnit().trim() + "%");
			}

			if ((null != occConCom.getReceptName()) && (0 < occConCom.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + occConCom.getReceptName().trim() + "%");
			}

			if ((null != occConCom.getReviewName()) && (0 < occConCom.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + occConCom.getReviewName().trim() + "%");
			}

			if ((null != occConCom.getIndustryCategory()) && (0 < occConCom.getIndustryCategory().trim().length())){
				paraMap.put("industryCategory", occConCom.getIndustryCategory().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("occupationalClassification","402880fc504a196f01504b64b81500d3");
		codeMap.put("industryCategory","402880fc504a196f01504b63f3fd00cf");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|occupationalClassification|industryCategory|evaluationUnit|receptName|reviewName|acceptanceExpert|createUserID|";
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
		pagination = occConComService.findByPage(pagination, paraMap);
			
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

	public OccConCom getOccConCom(){
		return this.occConCom;
	}

	public void setOccConCom(OccConCom occConCom){
		this.occConCom = occConCom;
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
