package com.jshx.chesafecondition.web;

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
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.chesafecondition.entity.CheSafCon;
import com.jshx.chesafecondition.service.CheSafConService;
import com.jshx.http.service.HttpService;

public class CheSafConAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheSafCon cheSafCon = new CheSafCon();

	/**
	 * 业务类
	 */
	@Autowired
	private CheSafConService cheSafConService;
	
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
	
	
	private Date queryReviewDateStart;

	private Date queryReviewDateEnd;

	private Date queryApprovalDateStart;

	private Date queryApprovalDateEnd;

	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//会审记录
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//危险化学品生产、储存企业设立和新建改建扩建项目安全审查批准书申请书
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//项目批准、核准或备案证明
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//具有法定资质的工程咨询机构出具的项目可行性研究报告（含项目安全条件论证专篇）或具有法定资质的中介机构出具的项目安全条件论证报告
	
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();//具有法定资质的安全评价机构出具的项目安全预评价报告
	
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();//项目最终产品、中间产品或储存的危险化学品的产品安全技术说明书或具有法定资质单位出具的产品理化性能指标资料
	
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();//采用的生产工艺、设备或储存方式、设施的情况说明
	
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();//拟建工厂、仓库与周边建筑物、设施的安全防护距离的情况说明
	
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();//事故应急救援措施
	
	private List<PhotoPic> picList10 = new ArrayList<PhotoPic>();//法律法规规定的其它材料
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
		    
		if(null != cheSafCon){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafCon.getAreaId()) && (0 < cheSafCon.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafCon.getAreaId().trim() );
			}

			if ((null != cheSafCon.getCompanyName()) && (0 < cheSafCon.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafCon.getCompanyName().trim() + "%");
			}

			if ((null != cheSafCon.getRatingAgenciesName()) && (0 < cheSafCon.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + cheSafCon.getRatingAgenciesName().trim() + "%");
			}

			if ((null != cheSafCon.getProjectNature()) && (0 < cheSafCon.getProjectNature().trim().length())){
				paraMap.put("projectNature", cheSafCon.getProjectNature().trim());
			}

			if ((null != cheSafCon.getIsComplete()) && (0 < cheSafCon.getIsComplete().trim().length())){
				paraMap.put("isComplete", cheSafCon.getIsComplete().trim());
			}

			if ((null != cheSafCon.getExpertReview()) && (0 < cheSafCon.getExpertReview().trim().length())){
				paraMap.put("expertReview", "%" + cheSafCon.getExpertReview().trim() + "%");
			}

			if (null != queryReviewDateStart){
				paraMap.put("startReviewDate", queryReviewDateStart);
			}

			if (null != queryReviewDateEnd){
				paraMap.put("endReviewDate", queryReviewDateEnd);
			}
			if ((null != cheSafCon.getReceptName()) && (0 < cheSafCon.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafCon.getReceptName().trim() + "%");
			}

			if ((null != cheSafCon.getReviewName()) && (0 < cheSafCon.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafCon.getReviewName().trim() + "%");
			}

			if ((null != cheSafCon.getProjectType()) && (0 < cheSafCon.getProjectType().trim().length())){
				paraMap.put("projectType", cheSafCon.getProjectType().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");
codeMap.put("isComplete","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgenciesName|projectNature|projectType|expertReview|reviewDate|review|receptName|reviewName|isComplete|createUserID|";
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
		pagination = cheSafConService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cheSafCon)&&(null != cheSafCon.getId()))
		{
			cheSafCon = cheSafConService.getById(cheSafCon.getId());
				if(cheSafCon.getLinkId() == null || "".equals(cheSafCon.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						cheSafCon.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",cheSafCon.getLinkId());
							map.put("mkType", "chesafecondition1");
							map.put("picType","chesafeconditionfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition2");
							map.put("picType","chesafeconditionfj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition3");
							map.put("picType","chesafeconditionfj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition4");
							map.put("picType","chesafeconditionfj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition5");
							map.put("picType","chesafeconditionfj5");
							picList5 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition6");
							map.put("picType","chesafeconditionfj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition7");
							map.put("picType","chesafeconditionfj7");
							picList7 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition8");
							map.put("picType","chesafeconditionfj8");
							picList8 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition9");
							map.put("picType","chesafeconditionfj9");
							picList9 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecondition10");
							map.put("picType","chesafeconditionfj10");
							picList10 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheSafCon.setLinkId(linkId);
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
		m.put("itemValue", cheSafCon.getAreaId());
		cheSafCon.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			cheSafCon.setDeptId(this.getLoginUserDepartmentId());
			cheSafCon.setDelFlag(0);
			cheSafConService.save(cheSafCon);
		}else{
			cheSafConService.update(cheSafCon);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cheSafCon)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cheSafCon中去
				
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
			cheSafConService.deleteWithFlag(ids);
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
		    
		if(null != cheSafCon){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafCon.getAreaId()) && (0 < cheSafCon.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafCon.getAreaId().trim() );
			}

			if ((null != cheSafCon.getCompanyName()) && (0 < cheSafCon.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafCon.getCompanyName().trim() + "%");
			}

			if ((null != cheSafCon.getRatingAgenciesName()) && (0 < cheSafCon.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + cheSafCon.getRatingAgenciesName().trim() + "%");
			}

			if ((null != cheSafCon.getProjectNature()) && (0 < cheSafCon.getProjectNature().trim().length())){
				paraMap.put("projectNature", cheSafCon.getProjectNature().trim());
			}

			if ((null != cheSafCon.getIsComplete()) && (0 < cheSafCon.getIsComplete().trim().length())){
				paraMap.put("isComplete", cheSafCon.getIsComplete().trim());
			}

			if ((null != cheSafCon.getExpertReview()) && (0 < cheSafCon.getExpertReview().trim().length())){
				paraMap.put("expertReview", "%" + cheSafCon.getExpertReview().trim() + "%");
			}

			if (null != queryReviewDateStart){
				paraMap.put("startReviewDate", queryReviewDateStart);
			}

			if (null != queryReviewDateEnd){
				paraMap.put("endReviewDate", queryReviewDateEnd);
			}
			if ((null != cheSafCon.getReceptName()) && (0 < cheSafCon.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafCon.getReceptName().trim() + "%");
			}

			if ((null != cheSafCon.getReviewName()) && (0 < cheSafCon.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafCon.getReviewName().trim() + "%");
			}

			if ((null != cheSafCon.getProjectType()) && (0 < cheSafCon.getProjectType().trim().length())){
				paraMap.put("projectType", cheSafCon.getProjectType().trim());
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或评价机构名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");
codeMap.put("projectType","402880fc504a196f01504b409b010021");
codeMap.put("isComplete","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgenciesName|projectNature|projectType|expertReview|reviewDate|review|receptName|reviewName|isComplete|createUserID|";
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
		pagination = cheSafConService.findByPage(pagination, paraMap);
		
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

	public CheSafCon getCheSafCon(){
		return this.cheSafCon;
	}

	public void setCheSafCon(CheSafCon cheSafCon){
		this.cheSafCon = cheSafCon;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryReviewDateStart(){
		return this.queryReviewDateStart;
	}

	public void setQueryReviewDateStart(Date queryReviewDateStart){
		this.queryReviewDateStart = queryReviewDateStart;
	}

	public Date getQueryReviewDateEnd(){
		return this.queryReviewDateEnd;
	}

	public void setQueryReviewDateEnd(Date queryReviewDateEnd){
		this.queryReviewDateEnd = queryReviewDateEnd;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
