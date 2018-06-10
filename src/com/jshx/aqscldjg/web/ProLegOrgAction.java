package com.jshx.aqscldjg.web;

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

import com.jshx.aqscgljg.entity.ProManOrg;
import com.jshx.aqscgljg.service.ProManOrgService;
import com.jshx.aqscldjg.entity.ProLegOrg;
import com.jshx.aqscldjg.service.ProLegOrgService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class ProLegOrgAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ProLegOrg proLegOrg = new ProLegOrg();

	/**
	 * 业务类
	 */
	@Autowired
	private ProLegOrgService proLegOrgService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private ProManOrgService proManOrgService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private EntBaseInfo entBaseInfo = new EntBaseInfo();
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
		private HttpService httpService;
	
	private String loginUserId;
	private String roleName;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
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
		    
		if(null != proLegOrg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proLegOrg.getAreaId()) && (0 < proLegOrg.getAreaId().trim().length())){
				paraMap.put("areaId", proLegOrg.getAreaId().trim());
			}

			if ((null != proLegOrg.getCompanyName()) && (0 < proLegOrg.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proLegOrg.getCompanyName().trim() + "%");
			}
			if ((null != proLegOrg.getCompanyId()) && (0 < proLegOrg.getCompanyId().trim().length())){
				paraMap.put("companyId",  proLegOrg.getCompanyId().trim() );
			}


			if ((null != proLegOrg.getOrgenizationName()) && (0 < proLegOrg.getOrgenizationName().trim().length())){
				paraMap.put("orgenizationName", "%" + proLegOrg.getOrgenizationName().trim() + "%");
			}
			if ((null != proLegOrg.getOrgenizationCharge()) && (0 < proLegOrg.getOrgenizationCharge().trim().length())){
				paraMap.put("orgenizationCharge", "%" + proLegOrg.getOrgenizationCharge().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|orgenizationName|orgenizationMenberCount|orgenizationCharge|";
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
		pagination = proLegOrgService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != proLegOrg)&&(null != proLegOrg.getId()))
		{
			proLegOrg = proLegOrgService.getById(proLegOrg.getId());
			if(proLegOrg.getLinkId() == null || "".equals(proLegOrg.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				proLegOrg.setLinkId(linkId);
			}
			else
			{
				try {
					Map map = new HashMap();
					map.put("linkId",proLegOrg.getLinkId());
					map.put("mkType", "aqscldjg");
					map.put("picType","aqscldjgfj");
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
			proLegOrg.setLinkId(linkId);
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
		proLegOrg.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		proLegOrg.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		proLegOrg.setCompanyId(enBaseInfo.getId());
		proLegOrg.setCompanyName(enBaseInfo.getEnterpriseName());
		
		if ("add".equalsIgnoreCase(this.flag)){
			proLegOrg.setDeptId(this.getLoginUserDepartmentId());
			proLegOrg.setDelFlag(0);
			proLegOrgService.save(proLegOrg);
		}else{
			proLegOrgService.update(proLegOrg);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != proLegOrg)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到proLegOrg中去
				
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
			proLegOrgService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	
	public String zwtInit(){
		
//		String url="http://172.25.127.9/services/authorization/validateredirect";
//    	Map<String,String> dataMap = new HashMap<String,String>();
//        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//        if("200".equals(j.get("code").toString())){
//        	String loginName=j.get("username").toString();
//        	User u=userService.findUserByLoginId(loginName);
//			setSessionAttribute("curr_user", u);
//        	ids=u.getId();
//        	String deptCode =u.getDeptCode();
//    		if(httpService.judgeRoleCode(ids, "A17")){
//    			userType="1";
//    		}else if(httpService.judgeRoleCode(ids, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
//    			userType= "2";
//    		}else if(httpService.judgeRoleCode(ids, "A11")){
//    			userType= "3";
//    		}else if(httpService.judgeRoleCode(ids, "A09")){
//    			userType= "4";
//    		}else if(httpService.judgeRoleCode(ids, "A02")){
//    			userType= "5";
//    		}else if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
//    			userType= "6";
//    		}else{
//    			userType= "7";
//    		}
//        	return SUCCESS;
//        }else{
//        	return ERROR;
//        }
		roleName = "1";
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
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
		    
		if(null != proLegOrg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proLegOrg.getAreaId()) && (0 < proLegOrg.getAreaId().trim().length())){
				paraMap.put("areaId", proLegOrg.getAreaId().trim());
			}
	
			if ((null != proLegOrg.getCompanyName()) && (0 < proLegOrg.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proLegOrg.getCompanyName().trim() + "%");
			}
	
			if ((null != proLegOrg.getOrgenizationName()) && (0 < proLegOrg.getOrgenizationName().trim().length())){
				paraMap.put("orgenizationName", "%" + proLegOrg.getOrgenizationName().trim() + "%");
			}
			if ((null != proLegOrg.getOrgenizationCharge()) && (0 < proLegOrg.getOrgenizationCharge().trim().length())){
				paraMap.put("orgenizationCharge", "%" + proLegOrg.getOrgenizationCharge().trim() + "%");
			}
	
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、机构名称或主要负责人".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
			
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|orgenizationName|orgenizationMenberCount|orgenizationCharge|";
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
		pagination = proLegOrgService.findByPage(pagination, paraMap);
		
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

	
	public String jgxx(){
		return SUCCESS;
	}
	public void zwtJgxxList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		paraMap.put("companyName", "%" + searchLike.trim() + "%");
		
		if(pagination==null){
		    pagination = new Pagination(this.getRequest());
		}
		
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		
		paraMap.put("start", pagination.getPageNumber());
		paraMap.put("limit", pagination.getPageSize());
		
		paraMap.put("sqlId", "queryJgxxList");
		
		List<Map<String,Object>> mapList= httpService.findListDataByMap(paraMap);
			
		pagination.setList(mapList);
		paraMap.put("sqlId", "queryJgxxListCount");
		paraMap.remove("start");
		paraMap.remove("limit");
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap).get(0).get("NUM").toString());
		pagination.setTotalCount(total);
		
		JSONObject json=new JSONObject();
		try {
			JSONArray ja = JSONArray.fromObject(mapList);
			json.put("result", ja);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
	
	public String ryxx(){
		return SUCCESS;
	}
	public void zwtRyxxList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		paraMap.put("companyName", "%" + searchLike.trim() + "%");
		
		if(pagination==null){
		    pagination = new Pagination(this.getRequest());
		}
		
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		
		paraMap.put("start", pagination.getPageNumber());
		paraMap.put("limit", pagination.getPageSize());
		
		paraMap.put("sqlId", "queryRyxxList");
		
		List<Map<String,Object>> mapList= httpService.findListDataByMap(paraMap);
			
		pagination.setList(mapList);
		paraMap.put("sqlId", "queryRyxxListCount");
		paraMap.remove("start");
		paraMap.remove("limit");
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap).get(0).get("NUM").toString());
		pagination.setTotalCount(total);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(mapList);
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
	
	public String jypx(){
		return SUCCESS;
	}
	public void zwtJypxList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		paraMap.put("companyName", "%" + searchLike.trim() + "%");
		
		if(pagination==null){
		    pagination = new Pagination(this.getRequest());
		}
		
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		
		paraMap.put("start", pagination.getPageNumber());
		paraMap.put("limit", pagination.getPageSize());
		
		paraMap.put("sqlId", "queryJypxList");
		
		List<Map<String,Object>> mapList= httpService.findListDataByMap(paraMap);
			
		pagination.setList(mapList);
		paraMap.put("sqlId", "queryJypxListCount");
		paraMap.remove("start");
		paraMap.remove("limit");
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap).get(0).get("NUM").toString());
		pagination.setTotalCount(total);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(mapList);
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

	public ProLegOrg getProLegOrg(){
		return this.proLegOrg;
	}

	public void setProLegOrg(ProLegOrg proLegOrg){
		this.proLegOrg = proLegOrg;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
    public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
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
	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}
	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}
    
}
