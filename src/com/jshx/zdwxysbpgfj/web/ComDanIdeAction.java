package com.jshx.zdwxysbpgfj.web;

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
import com.jshx.map.service.TbMapService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.patrolUser.service.PatrolUserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.SaveOrUpdateDxx;
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.service.ComDanIdeService;

public class ComDanIdeAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ComDanIde comDanIde = new ComDanIde();

	/**
	 * 业务类
	 */
	@Autowired
	private ComDanIdeService comDanIdeService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private TbMapService tbMapService;
	@Autowired
	private PatrolUserService patrolUserService;
   
	private String companyCode;//企业的组织机构代码 lj 2015-11-17

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private boolean canCheck=false;
	
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
	
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public String init(){
		loginUserId = this.getLoginUser().getId();
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			if("A09".equals(r.getRole().getRoleCode())||"A10".equals(r.getRole().getRoleCode())){
				canCheck=true;
			}
			if(r.getRole().getRoleCode().equals("A23")) 
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
		else
		{
			paraMap.put("state", "待提交");
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != comDanIde){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != comDanIde.getAreaId()) && (0 < comDanIde.getAreaId().trim().length())){
				paraMap.put("areaId",comDanIde.getAreaId().trim());
			}

			if ((null != comDanIde.getCompanyName()) && (0 < comDanIde.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + comDanIde.getCompanyName().trim() + "%");
			}
			if ((null != comDanIde.getCompanyId()) && (0 < comDanIde.getCompanyId().trim().length())){
				paraMap.put("companyId",  comDanIde.getCompanyId().trim()  );
			}
			
			if ((null != comDanIde.getDangerName()) && (0 < comDanIde.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + comDanIde.getDangerName().trim() + "%");
			}

			if ((null != comDanIde.getAuditResult()) && (0 < comDanIde.getAuditResult().trim().length())){
				paraMap.put("auditResult", "%" + comDanIde.getAuditResult().trim() + "%");
			}

			if ((null != comDanIde.getDangerLevel()) && (0 < comDanIde.getDangerLevel().trim().length())){
				paraMap.put("dangerLevel", comDanIde.getDangerLevel().trim());
			}

			if ((null != comDanIde.getSafePerson()) && (0 < comDanIde.getSafePerson().trim().length())){
				paraMap.put("safePerson", "%" + comDanIde.getSafePerson().trim() + "%");
			}

			if ((null != comDanIde.getDangerType()) && (0 < comDanIde.getDangerType().trim().length())){
				paraMap.put("dangerType", comDanIde.getDangerType().trim());
			}
			
			if ((null != comDanIde.getAuditState()) && (0 < comDanIde.getAuditState().trim().length())){
				paraMap.put("auditState", comDanIde.getAuditState().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|dangerName|dangerType|dangerLevel|safePerson|auditResult|auditState|";
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
		try {
			pagination = comDanIdeService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != comDanIde)&&(null != comDanIde.getId())){
			comDanIde = comDanIdeService.getById(comDanIde.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", comDanIde.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			if(comDanIde.getMapkey() == null || "".equals(comDanIde.getMapkey()))
			{
				String mapKey = java.util.UUID.randomUUID().toString();
				comDanIde.setMapkey(mapKey);
			}
		}else{
			//获取企业区域
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			comDanIde.setAreaId(enBaseInfo.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			comDanIde.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			comDanIde.setCompanyId(enBaseInfo.getId());
			comDanIde.setCompanyName(enBaseInfo.getEnterpriseName());
			comDanIde.setCreateUserID(this.getLoginUser().getId());
			String mapKey = java.util.UUID.randomUUID().toString();
			comDanIde.setMapkey(mapKey);
		}
		//获取企业的组织机构代码 地图需要
		Map map = new HashMap();
		map.put("id", this.getLoginUser().getLoginId());
		Map detail = tbMapService.getMapDetailByMap("get_company_detail_map", map);
		if(detail!=null&&!detail.isEmpty()){
			companyCode = (String) detail.get("enterpriseCode");
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
		comDanIde.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		comDanIde.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		comDanIde.setCompanyId(enBaseInfo.getId());
		comDanIde.setCompanyName(enBaseInfo.getEnterpriseName());
		
		String checkPeopleNames="";//获取巡查人名称
	    String checkPeopleIds=comDanIde.getCheckPeopleId();
	    String ids[]=checkPeopleIds.split(", ");
	    for(int i=0;i<ids.length;i++){
	    	String checkPeopleName=patrolUserService.getById(ids[i]).getUserName();
	    	checkPeopleNames+=checkPeopleName+",";
		}
	    if(!checkPeopleNames.equals("")){
	    	checkPeopleNames=checkPeopleNames.substring(0,checkPeopleNames.length()-1);
		}
	    comDanIde.setCheckPeopleName(checkPeopleNames);
		if ("add".equalsIgnoreCase(this.flag)){
			comDanIde.setDeptId(this.getLoginUserDepartmentId());
			comDanIde.setDelFlag(0);
			comDanIdeService.save(comDanIde);
		}else{
			comDanIdeService.update(comDanIde);
		}
		SaveOrUpdateDxx dot = new SaveOrUpdateDxx();
		//删除点位信息
		dot.saveDot("4", comDanIde.getMapkey(),comDanIde.getLongitude(),comDanIde.getLatitude());
		//保存点位信息
		dot.saveDot("1", comDanIde.getMapkey(), comDanIde.getLongitude(),comDanIde.getLatitude());
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != comDanIde)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到comDanIde中去
				
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
			comDanIdeService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 审核信息
	 */
	public String check() throws Exception{
		view();
	    return "check";
	}
	
	/**
	 * 保存审核信息
	 */
	public String checkSave() throws Exception{
		comDanIde=comDanIdeService.getById(comDanIde.getId());
		comDanIde.setAuditState(checkRecord.getCheckResult());
		comDanIde.setAuditResult(checkRecord.getCheckResult());
		comDanIdeService.update(comDanIde);
		checkRecord .setCheckUserid(this.getLoginUser().getId());
		checkRecord.setDelFlag(0);
		checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
		checkRecordService.save(checkRecord);
	    return RELOAD;
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
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			roleName = "0";
		}else if(httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A10")){//登录人为监察大队角色 才可以审核
			canCheck=true;
		}
		return SUCCESS;
		
	}

	public String zdwxysbpgfj(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			roleName = "0";
		}else if(httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A10")){//登录人为监察大队角色 才可以审核
			canCheck=true;
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
		else
		{
			paraMap.put("state", "待提交");
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != comDanIde){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != comDanIde.getAreaId()) && (0 < comDanIde.getAreaId().trim().length())){
				paraMap.put("areaId", comDanIde.getAreaId().trim() );
			}

			if ((null != comDanIde.getCompanyName()) && (0 < comDanIde.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + comDanIde.getCompanyName().trim() + "%");
			}

			if ((null != comDanIde.getDangerName()) && (0 < comDanIde.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + comDanIde.getDangerName().trim() + "%");
			}

			if ((null != comDanIde.getAuditResult()) && (0 < comDanIde.getAuditResult().trim().length())){
				paraMap.put("auditResult", "%" + comDanIde.getAuditResult().trim() + "%");
			}

			if ((null != comDanIde.getDangerLevel()) && (0 < comDanIde.getDangerLevel().trim().length())){
				paraMap.put("dangerLevel", comDanIde.getDangerLevel().trim());
			}

			if ((null != comDanIde.getSafePerson()) && (0 < comDanIde.getSafePerson().trim().length())){
				paraMap.put("safePerson", "%" + comDanIde.getSafePerson().trim() + "%");
			}

			if ((null != comDanIde.getDangerType()) && (0 < comDanIde.getDangerType().trim().length())){
				paraMap.put("dangerType", comDanIde.getDangerType().trim());
			}
			
			if ((null != comDanIde.getAuditState()) && (0 < comDanIde.getAuditState().trim().length())){
				paraMap.put("auditState", comDanIde.getAuditState().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|dangerName|dangerType|dangerLevel|safePerson|auditResult|auditState|";
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
		pagination = comDanIdeService.findByPage(pagination, paraMap);
		
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

	public ComDanIde getComDanIde(){
		return this.comDanIde;
	}

	public void setComDanIde(ComDanIde comDanIde){
		this.comDanIde = comDanIde;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}

	public CheckRecord getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(CheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
    
}
