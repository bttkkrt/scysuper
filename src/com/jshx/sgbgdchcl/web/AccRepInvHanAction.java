package com.jshx.sgbgdchcl.web;

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
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.sgbgdchcl.entity.AccRepInvHan;
import com.jshx.sgbgdchcl.service.AccRepInvHanService;

public class AccRepInvHanAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private AccRepInvHan accRepInvHan = new AccRepInvHan();

	/**
	 * 业务类
	 */
	@Autowired
	private AccRepInvHanService accRepInvHanService;
	
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
	
	
	private Date queryAccidentTimeStart;

	private Date queryAccidentTimeEnd;

	private String roleName;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	 private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//事故图片
		
		
		private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//整改后图片
	
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
		    
		if(null != accRepInvHan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != accRepInvHan.getAreaId()) && (0 < accRepInvHan.getAreaId().trim().length())){
				paraMap.put("areaId", accRepInvHan.getAreaId().trim());
			}

			if ((null != accRepInvHan.getCompanyName()) && (0 < accRepInvHan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + accRepInvHan.getCompanyName().trim() + "%");
			}

			if ((null != accRepInvHan.getAccidentId()) && (0 < accRepInvHan.getAccidentId().trim().length())){
				paraMap.put("accidentId", "%" + accRepInvHan.getAccidentId().trim() + "%");
			}

			if ((null != accRepInvHan.getAccidentName()) && (0 < accRepInvHan.getAccidentName().trim().length())){
				paraMap.put("accidentName", "%" + accRepInvHan.getAccidentName().trim() + "%");
			}

			if (null != queryAccidentTimeStart){
				paraMap.put("startAccidentTime", queryAccidentTimeStart);
			}

			if (null != queryAccidentTimeEnd){
				paraMap.put("endAccidentTime", queryAccidentTimeEnd);
			}
			if ((null != accRepInvHan.getAccidentLevel()) && (0 < accRepInvHan.getAccidentLevel().trim().length())){
				paraMap.put("accidentLevel", accRepInvHan.getAccidentLevel().trim());
			}

			if ((null != accRepInvHan.getAccidentType()) && (0 < accRepInvHan.getAccidentType().trim().length())){
				paraMap.put("accidentType", accRepInvHan.getAccidentType().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
codeMap.put("accidentLevel","402880084196a4a3014196e11ea300e3");
codeMap.put("accidentType","402880084196a4a3014196e0f91a00e1");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|accidentId|accidentName|accidentTime|accidentLevel|accidentType|";
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
		pagination = accRepInvHanService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != accRepInvHan)&&(null != accRepInvHan.getId()))
		{
			accRepInvHan = accRepInvHanService.getById(accRepInvHan.getId());
				if(accRepInvHan.getLinkId() == null || "".equals(accRepInvHan.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						accRepInvHan.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",accRepInvHan.getLinkId());
							map.put("mkType", "sgbgdchcl1");
							map.put("picType","sgbgdchclfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "sgbgdchcl2");
							map.put("picType","sgbgdchclfj2");
							picList2 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					accRepInvHan.setLinkId(linkId);
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
		accRepInvHan.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		accRepInvHan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		accRepInvHan.setCompanyId(enBaseInfo.getId());
		accRepInvHan.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			accRepInvHan.setDeptId(this.getLoginUserDepartmentId());
			accRepInvHan.setDelFlag(0);
			

			accRepInvHanService.save(accRepInvHan);
		}else{
			accRepInvHanService.update(accRepInvHan);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != accRepInvHan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到accRepInvHan中去
				
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
			accRepInvHanService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 政务通  管理制度 
	 * lj
	 * 2016-06-06
	 * @return
	 */
	public String sgbg(){
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
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != accRepInvHan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != accRepInvHan.getAreaId()) && (0 < accRepInvHan.getAreaId().trim().length())){
				paraMap.put("areaId", accRepInvHan.getAreaId().trim());
			}

			if ((null != accRepInvHan.getCompanyName()) && (0 < accRepInvHan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + accRepInvHan.getCompanyName().trim() + "%");
			}

			if ((null != accRepInvHan.getAccidentId()) && (0 < accRepInvHan.getAccidentId().trim().length())){
				paraMap.put("accidentId", "%" + accRepInvHan.getAccidentId().trim() + "%");
			}

			if ((null != accRepInvHan.getAccidentName()) && (0 < accRepInvHan.getAccidentName().trim().length())){
				paraMap.put("accidentName", "%" + accRepInvHan.getAccidentName().trim() + "%");
			}

			if (null != queryAccidentTimeStart){
				paraMap.put("startAccidentTime", queryAccidentTimeStart);
			}

			if (null != queryAccidentTimeEnd){
				paraMap.put("endAccidentTime", queryAccidentTimeEnd);
			}
			if ((null != accRepInvHan.getAccidentLevel()) && (0 < accRepInvHan.getAccidentLevel().trim().length())){
				paraMap.put("accidentLevel", accRepInvHan.getAccidentLevel().trim());
			}

			if ((null != accRepInvHan.getAccidentType()) && (0 < accRepInvHan.getAccidentType().trim().length())){
				paraMap.put("accidentType", accRepInvHan.getAccidentType().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
codeMap.put("accidentLevel","402880084196a4a3014196e11ea300e3");
codeMap.put("accidentType","402880084196a4a3014196e0f91a00e1");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|accidentId|accidentName|accidentTime|accidentLevel|accidentType|";
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
		pagination = accRepInvHanService.findByPage(pagination, paraMap);
		
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

	public AccRepInvHan getAccRepInvHan(){
		return this.accRepInvHan;
	}

	public void setAccRepInvHan(AccRepInvHan accRepInvHan){
		this.accRepInvHan = accRepInvHan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryAccidentTimeStart(){
		return this.queryAccidentTimeStart;
	}

	public void setQueryAccidentTimeStart(Date queryAccidentTimeStart){
		this.queryAccidentTimeStart = queryAccidentTimeStart;
	}

	public Date getQueryAccidentTimeEnd(){
		return this.queryAccidentTimeEnd;
	}

	public void setQueryAccidentTimeEnd(Date queryAccidentTimeEnd){
		this.queryAccidentTimeEnd = queryAccidentTimeEnd;
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
