package com.jshx.lbypkfgl.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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
import com.jshx.lbypgl.service.PpeManagService;
import com.jshx.lbypkfgl.entity.PpeWareManag;
import com.jshx.lbypkfgl.service.PpeWareManagService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class PpeWareManagAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PpeWareManag ppeWareManag = new PpeWareManag();

	/**
	 * 业务类
	 */
	@Autowired
	private PpeWareManagService ppeWareManagService;
	@Autowired
	private PpeManagService ppeManagService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryPpeWareTimeStart;

	private Date queryPpeWareTimeEnd;

	
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
		    
		if(null != ppeWareManag){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ppeWareManag.getAreaId()) && (0 < ppeWareManag.getAreaId().trim().length())){
				paraMap.put("areaId",  ppeWareManag.getAreaId() );
			}

			if ((null != ppeWareManag.getCompanyName()) && (0 < ppeWareManag.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + ppeWareManag.getCompanyName().trim() + "%");
			}

			if ((null != ppeWareManag.getPpeWarePeople()) && (0 < ppeWareManag.getPpeWarePeople().trim().length())){
				paraMap.put("ppeWarePeople", "%" + ppeWareManag.getPpeWarePeople().trim() + "%");
			}

			if ((null != ppeWareManag.getPpeWareType()) && (0 < ppeWareManag.getPpeWareType().trim().length())){
				paraMap.put("ppeWareType", ppeWareManag.getPpeWareType().trim());
			}

			if ((null != ppeWareManag.getPpeName()) && (0 < ppeWareManag.getPpeName().trim().length())){
				paraMap.put("ppeName", "%" + ppeWareManag.getPpeName().trim() + "%");
			}
			if ((null != ppeWareManag.getPpeId()) && (0 < ppeWareManag.getPpeId().trim().length())){
				paraMap.put("ppeId", ppeWareManag.getPpeId().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("ppeWareType","402880fc501c2be401501d7e17810369");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|ppeName|ppeWareType|ppeWareNum|ppeWarePeople|";
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
		pagination = ppeWareManagService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != ppeWareManag)&&(null != ppeWareManag.getId())){
			ppeWareManag = ppeWareManagService.getById(ppeWareManag.getId());
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			ppeWareManag.setAreaId(enBaseInfo.getEnterprisePossession());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			ppeWareManag.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			ppeWareManag.setCompanyId(enBaseInfo.getId());
			ppeWareManag.setCompanyName(enBaseInfo.getEnterpriseName());
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
		if(null!=ppeWareManag.getPpeId()&&!"".equals(ppeWareManag.getPpeId())){
			ppeWareManag.setPpeName(ppeManagService.getById(ppeWareManag.getPpeId()).getPpeName());
		}
	
		if ("add".equalsIgnoreCase(this.flag)){
			ppeWareManag.setDeptId(this.getLoginUserDepartmentId());
			ppeWareManag.setDelFlag(0);
			ppeWareManagService.save(ppeWareManag);
		}else{
			ppeWareManagService.update(ppeWareManag);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != ppeWareManag)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到ppeWareManag中去
				
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
			ppeWareManagService.deleteWithFlag(ids);
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

	 public String lbypkf(){
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
		    
		if(null != ppeWareManag){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ppeWareManag.getAreaId()) && (0 < ppeWareManag.getAreaId().trim().length())){
				paraMap.put("areaId",  ppeWareManag.getAreaId() );
			}

			if ((null != ppeWareManag.getCompanyName()) && (0 < ppeWareManag.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + ppeWareManag.getCompanyName().trim() + "%");
			}

			if ((null != ppeWareManag.getPpeWarePeople()) && (0 < ppeWareManag.getPpeWarePeople().trim().length())){
				paraMap.put("ppeWarePeople", "%" + ppeWareManag.getPpeWarePeople().trim() + "%");
			}

			if ((null != ppeWareManag.getPpeWareType()) && (0 < ppeWareManag.getPpeWareType().trim().length())){
				paraMap.put("ppeWareType", ppeWareManag.getPpeWareType().trim());
			}

			if ((null != ppeWareManag.getPpeName()) && (0 < ppeWareManag.getPpeName().trim().length())){
				paraMap.put("ppeName", "%" + ppeWareManag.getPpeName().trim() + "%");
			}
			if ((null != ppeWareManag.getPpeId()) && (0 < ppeWareManag.getPpeId().trim().length())){
				paraMap.put("ppeId", ppeWareManag.getPpeId().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("ppeWareType","402880fc501c2be401501d7e17810369");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|ppeName|ppeWareType|ppeWareNum|ppeWarePeople|";
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
		pagination = ppeWareManagService.findByPage(pagination, paraMap);
		
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

	public PpeWareManag getPpeWareManag(){
		return this.ppeWareManag;
	}

	public void setPpeWareManag(PpeWareManag ppeWareManag){
		this.ppeWareManag = ppeWareManag;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPpeWareTimeStart(){
		return this.queryPpeWareTimeStart;
	}

	public void setQueryPpeWareTimeStart(Date queryPpeWareTimeStart){
		this.queryPpeWareTimeStart = queryPpeWareTimeStart;
	}

	public Date getQueryPpeWareTimeEnd(){
		return this.queryPpeWareTimeEnd;
	}

	public void setQueryPpeWareTimeEnd(Date queryPpeWareTimeEnd){
		this.queryPpeWareTimeEnd = queryPpeWareTimeEnd;
	}

}
