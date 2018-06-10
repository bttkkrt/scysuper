package com.jshx.xgfgl.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.sccjgl.service.WorkshopService;
import com.jshx.xgfgl.entity.PartyManage;
import com.jshx.xgfgl.service.PartyManageService;

public class PartyManageAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PartyManage partyManage = new PartyManage();

	/**
	 * 业务类
	 */
	@Autowired
	private PartyManageService partyManageService;
	@Autowired
	private WorkshopService workshopService;
	@Autowired
	private PhotoPicService photoPicService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	
	private String loginUserId;
	private String roleName;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	
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
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String init(){
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "1";
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
		    
		if(null != partyManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != partyManage.getAreaId()) && (0 < partyManage.getAreaId().trim().length())){
				paraMap.put("areaId",  partyManage.getAreaId() );
			}

			if ((null != partyManage.getCompanyName()) && (0 < partyManage.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + partyManage.getCompanyName().trim() + "%");
			}

			if ((null != partyManage.getPartyName()) && (0 < partyManage.getPartyName().trim().length())){
				paraMap.put("partyName", "%" + partyManage.getPartyName().trim() + "%");
			}

			if ((null != partyManage.getWokrshopId()) && (0 < partyManage.getWokrshopId().trim().length())){
				paraMap.put("wokrshopId",  partyManage.getWokrshopId().trim());
			}
			
			if ((null != partyManage.getWokrshopName()) && (0 < partyManage.getWokrshopName().trim().length())){
				paraMap.put("wokrshopName",  "%" + partyManage.getWokrshopName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|partyName|wokrshopName|";
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
		pagination = partyManageService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != partyManage)&&(null != partyManage.getId())){
			partyManage = partyManageService.getById(partyManage.getId());
			if(partyManage.getLinkId() == null || "".equals(partyManage.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				partyManage.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",partyManage.getLinkId());
				map.put("mkType", "xgfgl");
				map.put("picType","zzzmfj");
				picList1 = photoPicService.findPicPath(map); 
				map.put("picType","aqxyfj");
				picList2 = photoPicService.findPicPath(map); 
				map.put("picType","xcjcfj");
				picList3 = photoPicService.findPicPath(map); 
			}
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			partyManage.setAreaId(enBaseInfo.getEnterprisePossession());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			partyManage.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			partyManage.setCompanyId(enBaseInfo.getId());
			partyManage.setCompanyName(enBaseInfo.getEnterpriseName());
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			partyManage.setLinkId(linkId);
		}
		
		if((null != partyManage)&&(null != partyManage.getId()))
			partyManage = partyManageService.getById(partyManage.getId());
		
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
		if(null!=partyManage.getWokrshopId()&&!"".equals(partyManage.getWokrshopId())){
			partyManage.setWokrshopName(workshopService.getById(partyManage.getWokrshopId()).getWorkshopName());
		}
	
		if ("add".equalsIgnoreCase(this.flag)){
			partyManage.setDeptId(this.getLoginUserDepartmentId());
			partyManage.setDelFlag(0);
			partyManageService.save(partyManage);
		}else{
			partyManageService.update(partyManage);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != partyManage)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到partyManage中去
				
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
			partyManageService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	

	public String zwtInit(){
			
//			String url="http://172.25.127.9/services/authorization/validateredirect";
//	    	Map<String,String> dataMap = new HashMap<String,String>();
//	        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//	        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//	        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//	        if("200".equals(j.get("code").toString())){
//	        	String loginName=j.get("username").toString();
//	        	User u=userService.findUserByLoginId(loginName);
//				setSessionAttribute("curr_user", u);
//	        	ids=u.getId();
//	        	String deptCode =u.getDeptCode();
//	    		if(httpService.judgeRoleCode(ids, "A17")){
//	    			userType="1";
//	    		}else if(httpService.judgeRoleCode(ids, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
//	    			userType= "2";
//	    		}else if(httpService.judgeRoleCode(ids, "A11")){
//	    			userType= "3";
//	    		}else if(httpService.judgeRoleCode(ids, "A09")){
//	    			userType= "4";
//	    		}else if(httpService.judgeRoleCode(ids, "A02")){
//	    			userType= "5";
//	    		}else if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
//	    			userType= "6";
//	    		}else{
//	    			userType= "7";
//	    		}
//	        	return SUCCESS;
//	        }else{
//	        	return ERROR;
//	        }
			User user=userService.findUserByLoginId("zdz1");
			setSessionAttribute("curr_user", user);
			String userId=user.getId();
			if(httpService.judgeRoleCode(userId, "A23")){
				roleName = "0";
			}
			return SUCCESS;
		}

	   public String xgfgl(){
		   return SUCCESS;
	   }

		public void zwtList() throws Exception{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
			String userId=this.getLoginUser().getId();
			if(httpService.judgeRoleCode(userId, "A23")){
				paraMap.put("createUserId", userId);
			}
			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			    
			if(null != partyManage){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != partyManage.getAreaId()) && (0 < partyManage.getAreaId().trim().length())){
					paraMap.put("areaId",  partyManage.getAreaId() );
				}

				if ((null != partyManage.getCompanyName()) && (0 < partyManage.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + partyManage.getCompanyName().trim() + "%");
				}

				if ((null != partyManage.getPartyName()) && (0 < partyManage.getPartyName().trim().length())){
					paraMap.put("partyName", "%" + partyManage.getPartyName().trim() + "%");
				}

				if ((null != partyManage.getWokrshopId()) && (0 < partyManage.getWokrshopId().trim().length())){
					paraMap.put("wokrshopId",  partyManage.getWokrshopId().trim());
				}
				
				if ((null != partyManage.getWokrshopName()) && (0 < partyManage.getWokrshopName().trim().length())){
					paraMap.put("wokrshopName",  "%" + partyManage.getWokrshopName().trim() + "%");
				}

			}
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|areaId|companyName|partyName|wokrshopName|";
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
			pagination = partyManageService.findByPage(pagination, paraMap);
			
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

	public PartyManage getPartyManage(){
		return this.partyManage;
	}

	public void setPartyManage(PartyManage partyManage){
		this.partyManage = partyManage;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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
    
}
