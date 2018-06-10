package com.jshx.chesafecomplete.web;

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
import com.jshx.chesafecomplete.entity.CheSafCom;
import com.jshx.chesafecomplete.service.CheSafComService;
import com.jshx.http.service.HttpService;

public class CheSafComAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheSafCom cheSafCom = new CheSafCom();

	/**
	 * 业务类
	 */
	@Autowired
	private CheSafComService cheSafComService;
	
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

	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//会审记录
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//危险化学品建设项目安全设施竣工验收申请表
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//建设项目安全设施施工、监理情况报告
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//建设项目安全设施竣工验收评价报告
	
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();//为从业人员缴纳工伤保险费的证明材料复制件
	
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();//危险化学品事故应急预案备案登记表复制件
	
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();//试生产情况报告
	
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();//建设项目安全条件审查意见书、安全设施设计审查意见书和试生产（使用）方案备案文件复制件
	
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();//构成危险化学品重大危险源的，还应当提交危险化学品重大危险源备案证明文件复制件
	
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
		    
		if(null != cheSafCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafCom.getAreaId()) && (0 < cheSafCom.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafCom.getAreaId().trim() );
			}
			if ((null != cheSafCom.getAreaName()) && (0 < cheSafCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheSafCom.getAreaName().trim() + "%");
			}

			if ((null != cheSafCom.getCompanyName()) && (0 < cheSafCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafCom.getCompanyName().trim() + "%");
			}

			if ((null != cheSafCom.getEvaluationUnit()) && (0 < cheSafCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + cheSafCom.getEvaluationUnit().trim() + "%");
			}

			if ((null != cheSafCom.getAcceptanceExpert()) && (0 < cheSafCom.getAcceptanceExpert().trim().length())){
				paraMap.put("acceptanceExpert", "%" + cheSafCom.getAcceptanceExpert().trim() + "%");
			}

			if ((null != cheSafCom.getFileNo()) && (0 < cheSafCom.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheSafCom.getFileNo().trim() + "%");
			}

			if (null != queryApprovalDateStart){
				paraMap.put("startApprovalDate", queryApprovalDateStart);
			}

			if (null != queryApprovalDateEnd){
				paraMap.put("endApprovalDate", queryApprovalDateEnd);
			}
			if ((null != cheSafCom.getReceptName()) && (0 < cheSafCom.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafCom.getReceptName().trim() + "%");
			}

			if ((null != cheSafCom.getReviewName()) && (0 < cheSafCom.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafCom.getReviewName().trim() + "%");
			}

			if (null != queryAcceptanceDateStart){
				paraMap.put("startAcceptanceDate", queryAcceptanceDateStart);
			}

			if (null != queryAcceptanceDateEnd){
				paraMap.put("endAcceptanceDate", queryAcceptanceDateEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|evaluationUnit|acceptanceExpert|acceptanceDate|approvalDate|receptName|reviewName|fileNo|signLeader|createUserID|";
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
		pagination = cheSafComService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cheSafCom)&&(null != cheSafCom.getId()))
		{
			cheSafCom = cheSafComService.getById(cheSafCom.getId());
				if(cheSafCom.getLinkId() == null || "".equals(cheSafCom.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						cheSafCom.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",cheSafCom.getLinkId());
							map.put("mkType", "chesafecomplete1");
							map.put("picType","chesafecompletefj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete2");
							map.put("picType","chesafecompletefj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete3");
							map.put("picType","chesafecompletefj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete4");
							map.put("picType","chesafecompletefj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete5");
							map.put("picType","chesafecompletefj5");
							picList5 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete6");
							map.put("picType","chesafecompletefj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete7");
							map.put("picType","chesafecompletefj7");
							picList7 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete8");
							map.put("picType","chesafecompletefj8");
							picList8 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete9");
							map.put("picType","chesafecompletefj9");
							picList9 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafecomplete10");
							map.put("picType","chesafecompletefj10");
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
					cheSafCom.setLinkId(linkId);
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
		m.put("itemValue", cheSafCom.getAreaId());
		cheSafCom.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			cheSafCom.setDeptId(this.getLoginUserDepartmentId());
			cheSafCom.setDelFlag(0);
			cheSafComService.save(cheSafCom);
		}else{
			cheSafComService.update(cheSafCom);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cheSafCom)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cheSafCom中去
				
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
			cheSafComService.deleteWithFlag(ids);
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
		    
		if(null != cheSafCom){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafCom.getAreaId()) && (0 < cheSafCom.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafCom.getAreaId().trim() );
			}
			if ((null != cheSafCom.getAreaName()) && (0 < cheSafCom.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheSafCom.getAreaName().trim() + "%");
			}

			if ((null != cheSafCom.getCompanyName()) && (0 < cheSafCom.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafCom.getCompanyName().trim() + "%");
			}

			if ((null != cheSafCom.getEvaluationUnit()) && (0 < cheSafCom.getEvaluationUnit().trim().length())){
				paraMap.put("evaluationUnit", "%" + cheSafCom.getEvaluationUnit().trim() + "%");
			}

			if ((null != cheSafCom.getAcceptanceExpert()) && (0 < cheSafCom.getAcceptanceExpert().trim().length())){
				paraMap.put("acceptanceExpert", "%" + cheSafCom.getAcceptanceExpert().trim() + "%");
			}

			if ((null != cheSafCom.getFileNo()) && (0 < cheSafCom.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheSafCom.getFileNo().trim() + "%");
			}

			if (null != queryApprovalDateStart){
				paraMap.put("startApprovalDate", queryApprovalDateStart);
			}

			if (null != queryApprovalDateEnd){
				paraMap.put("endApprovalDate", queryApprovalDateEnd);
			}
			if ((null != cheSafCom.getReceptName()) && (0 < cheSafCom.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafCom.getReceptName().trim() + "%");
			}

			if ((null != cheSafCom.getReviewName()) && (0 < cheSafCom.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafCom.getReviewName().trim() + "%");
			}

			if (null != queryAcceptanceDateStart){
				paraMap.put("startAcceptanceDate", queryAcceptanceDateStart);
			}

			if (null != queryAcceptanceDateEnd){
				paraMap.put("endAcceptanceDate", queryAcceptanceDateEnd);
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或评价单位".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|evaluationUnit|acceptanceExpert|acceptanceDate|approvalDate|receptName|reviewName|fileNo|signLeader|createUserID|";
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
		pagination = cheSafComService.findByPage(pagination, paraMap);
		
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

	public CheSafCom getCheSafCom(){
		return this.cheSafCom;
	}

	public void setCheSafCom(CheSafCom cheSafCom){
		this.cheSafCom = cheSafCom;
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
