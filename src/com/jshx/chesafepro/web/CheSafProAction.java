package com.jshx.chesafepro.web;

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
import com.jshx.chesafepro.entity.CheSafPro;
import com.jshx.chesafepro.service.CheSafProService;
import com.jshx.http.service.HttpService;

public class CheSafProAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheSafPro cheSafPro = new CheSafPro();

	/**
	 * 业务类
	 */
	@Autowired
	private CheSafProService cheSafProService;
	
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
	
	
	private Date queryRecordDateStart;

	private Date queryRecordDateEnd;

	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//会审记录
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//生产（使用）备案表
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//试生产（使用）方案
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//安全设施设计落实情况及安全设施设计重大变更情况的报告
	
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();//组织设计漏项、工程质量、工程隐患的检查情况，以及整改措施的落实情况报告
	
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();//建设项目施工、监理单位资质证书和质量监督手续
	
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();//建设项目安全设施的检验检测情况
	
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();//安全生产责任制文件和安全生产规章制度、岗位操作安全规程清单
	
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();//设置安全生产管理机构和配备专职安全生产管理人员的文件复制件
	
	private List<PhotoPic> picList10 = new ArrayList<PhotoPic>();//主要负责人、分管安全负责人、安全生产管理人员的安全资格证复制件和特种作业人员名单
	
	private List<PhotoPic> picList11 = new ArrayList<PhotoPic>();//相关从业人员安全教育、培训合格的证明材料
	
	private List<PhotoPic> picList12 = new ArrayList<PhotoPic>();//劳动防护用品配备情况说明
	
	private List<PhotoPic> picList13 = new ArrayList<PhotoPic>();//设计审查意见书
	
	private List<PhotoPic> picList14 = new ArrayList<PhotoPic>();//其他应当提供的文件、资料
	
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
		    
		if(null != cheSafPro){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafPro.getAreaId()) && (0 < cheSafPro.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafPro.getAreaId().trim() );
			}
			if ((null != cheSafPro.getAreaName()) && (0 < cheSafPro.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheSafPro.getAreaName().trim() + "%");
			}

			if ((null != cheSafPro.getCompanyName()) && (0 < cheSafPro.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafPro.getCompanyName().trim() + "%");
			}

			if ((null != cheSafPro.getProjectType()) && (0 < cheSafPro.getProjectType().trim().length())){
				paraMap.put("projectType", cheSafPro.getProjectType().trim());
			}

			if ((null != cheSafPro.getFileNo()) && (0 < cheSafPro.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheSafPro.getFileNo().trim() + "%");
			}

			if ((null != cheSafPro.getReceptName()) && (0 < cheSafPro.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafPro.getReceptName().trim() + "%");
			}

			if ((null != cheSafPro.getReviewName()) && (0 < cheSafPro.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafPro.getReviewName().trim() + "%");
			}

			if ((null != cheSafPro.getProjectNature()) && (0 < cheSafPro.getProjectNature().trim().length())){
				paraMap.put("projectNature", cheSafPro.getProjectNature().trim());
			}
			if ((null != cheSafPro.getRecordNum()) && (0 < cheSafPro.getRecordNum().trim().length())){
				paraMap.put("recordNum", "%" + cheSafPro.getRecordNum().trim() + "%");
			}
			if (null != queryRecordDateStart){
				paraMap.put("startRecordDate", queryRecordDateStart);
			}

			if (null != queryRecordDateEnd){
				paraMap.put("endRecordDate", queryRecordDateEnd);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectType","402880fc504a196f01504b409b010021");
codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|projectType|projectNature|receptName|reviewName|fileNo|recordNum|recordDate|createUserID|";
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
		pagination = cheSafProService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cheSafPro)&&(null != cheSafPro.getId()))
		{
			cheSafPro = cheSafProService.getById(cheSafPro.getId());
				if(cheSafPro.getLinkId() == null || "".equals(cheSafPro.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						cheSafPro.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",cheSafPro.getLinkId());
							map.put("mkType", "chesafepro1");
							map.put("picType","chesafeprofj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro2");
							map.put("picType","chesafeprofj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro3");
							map.put("picType","chesafeprofj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro4");
							map.put("picType","chesafeprofj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro5");
							map.put("picType","chesafeprofj5");
							picList5 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro6");
							map.put("picType","chesafeprofj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro6");
							map.put("picType","chesafeprofj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro7");
							map.put("picType","chesafeprofj7");
							picList7 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro8");
							map.put("picType","chesafeprofj8");
							picList8 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro9");
							map.put("picType","chesafeprofj9");
							picList9 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro10");
							map.put("picType","chesafeprofj10");
							picList10 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro11");
							map.put("picType","chesafeprofj11");
							picList11 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro12");
							map.put("picType","chesafeprofj12");
							picList12 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro13");
							map.put("picType","chesafeprofj13");
							picList13 = photoPicService.findPicPath(map);
							map.put("mkType", "chesafepro14");
							map.put("picType","chesafeprofj14");
							picList14 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheSafPro.setLinkId(linkId);
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
		m.put("itemValue", cheSafPro.getAreaId());
		cheSafPro.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			cheSafPro.setDeptId(this.getLoginUserDepartmentId());
			cheSafPro.setDelFlag(0);
			cheSafProService.save(cheSafPro);
		}else{
			cheSafProService.update(cheSafPro);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cheSafPro)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cheSafPro中去
				
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
			cheSafProService.deleteWithFlag(ids);
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
		    
		if(null != cheSafPro){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheSafPro.getAreaId()) && (0 < cheSafPro.getAreaId().trim().length())){
				paraMap.put("areaId", cheSafPro.getAreaId().trim() );
			}
			if ((null != cheSafPro.getAreaName()) && (0 < cheSafPro.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheSafPro.getAreaName().trim() + "%");
			}

			if ((null != cheSafPro.getCompanyName()) && (0 < cheSafPro.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheSafPro.getCompanyName().trim() + "%");
			}

			if ((null != cheSafPro.getProjectType()) && (0 < cheSafPro.getProjectType().trim().length())){
				paraMap.put("projectType", cheSafPro.getProjectType().trim());
			}

			if ((null != cheSafPro.getFileNo()) && (0 < cheSafPro.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheSafPro.getFileNo().trim() + "%");
			}

			if ((null != cheSafPro.getReceptName()) && (0 < cheSafPro.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheSafPro.getReceptName().trim() + "%");
			}

			if ((null != cheSafPro.getReviewName()) && (0 < cheSafPro.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheSafPro.getReviewName().trim() + "%");
			}

			if ((null != cheSafPro.getProjectNature()) && (0 < cheSafPro.getProjectNature().trim().length())){
				paraMap.put("projectNature", cheSafPro.getProjectNature().trim());
			}
			if ((null != cheSafPro.getRecordNum()) && (0 < cheSafPro.getRecordNum().trim().length())){
				paraMap.put("recordNum", "%" + cheSafPro.getRecordNum().trim() + "%");
			}
			if (null != queryRecordDateStart){
				paraMap.put("startRecordDate", queryRecordDateStart);
			}

			if (null != queryRecordDateEnd){
				paraMap.put("endRecordDate", queryRecordDateEnd);
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或备案编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("projectType","402880fc504a196f01504b409b010021");
codeMap.put("projectNature","402880fc504a196f01504b3f7d61001c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|projectType|projectNature|receptName|reviewName|fileNo|recordNum|recordDate|createUserID|";
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
		pagination = cheSafProService.findByPage(pagination, paraMap);
		
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

	public CheSafPro getCheSafPro(){
		return this.cheSafPro;
	}

	public void setCheSafPro(CheSafPro cheSafPro){
		this.cheSafPro = cheSafPro;
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

	public List<PhotoPic> getPicList11() {
		return picList11;
	}

	public void setPicList11(List<PhotoPic> picList11) {
		this.picList11 = picList11;
	}

	public List<PhotoPic> getPicList12() {
		return picList12;
	}

	public void setPicList12(List<PhotoPic> picList12) {
		this.picList12 = picList12;
	}

	public List<PhotoPic> getPicList13() {
		return picList13;
	}

	public void setPicList13(List<PhotoPic> picList13) {
		this.picList13 = picList13;
	}

	public List<PhotoPic> getPicList14() {
		return picList14;
	}

	public void setPicList14(List<PhotoPic> picList14) {
		this.picList14 = picList14;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
