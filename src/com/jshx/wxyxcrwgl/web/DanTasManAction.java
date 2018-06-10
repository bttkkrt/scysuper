package com.jshx.wxyxcrwgl.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import cn.jpush.api.JPushClient;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.dot.service.DotService;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.service.DanTasManService;
import com.jshx.xcxlxgl.service.PatTypManService;

public class DanTasManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DanTasMan danTasMan = new DanTasMan();

	/**
	 * 业务类
	 */
	@Autowired
	private DanTasManService danTasManService;
	
	@Autowired
	private PhotoPicService photoPicService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private PatTypManService patTypManService;
	@Autowired
	private DotService dotService;
	@Autowired
	private HttpService httpService;
	
	private String startDate="";
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}


	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryCheckTimeStart;

	private Date queryCheckTimeEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
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
	
	
	/**
	 * 初始化 用于判断审核角色
	 */
	@SuppressWarnings("unchecked")
	public String init(){
		danTasMan.setResult("");
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色  
		this.getLoginUser().getId();
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		
		for(UserRight ur:list)
		{
			//登录人为监察大队角色 才可以审核
			if(ur.getRole().getRoleCode().equals("A09")||ur.getRole().getRoleCode().equals("A10")) 
			{
				roleName = "1";
				break;
			}
			//企业角色
			if(ur.getRole().getRoleCode().equals("A23")){
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
		    
		if(null != danTasMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != danTasMan.getAreaId()) && (0 < danTasMan.getAreaId().trim().length())){
				paraMap.put("areaId",  danTasMan.getAreaId().trim() );
			}

			if ((null != danTasMan.getCompanyName()) && (0 < danTasMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + danTasMan.getCompanyName().trim() + "%");
			}
			if ((null != danTasMan.getCompanyId()) && (0 < danTasMan.getCompanyId().trim().length())){
				paraMap.put("companyId", danTasMan.getCompanyId().trim()  );
			}

			if ((null != danTasMan.getTaskName()) && (0 < danTasMan.getTaskName().trim().length())){
				paraMap.put("taskName", "%" + danTasMan.getTaskName().trim() + "%");
			}

			if ((null != danTasMan.getDangerName()) && (0 < danTasMan.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + danTasMan.getDangerName().trim() + "%");
			}
			if (null != danTasMan.getResult()&&0<danTasMan.getResult().trim().length()){
				paraMap.put("result", danTasMan.getResult());
			}

			if (null != queryCheckTimeStart){
				paraMap.put("startCheckTime", queryCheckTimeStart);
			}
			if (null != danTasMan.getAssPlanNo() && (0 < danTasMan.getAssPlanNo().trim().length())){
				paraMap.put("assPlanNo",  danTasMan.getAssPlanNo());
			}
			if (null != queryCheckTimeEnd){
				paraMap.put("endCheckTime", queryCheckTimeEnd);
			}
			if ((null != danTasMan.getCheckPeopleName()) && (0 < danTasMan.getCheckPeopleName().trim().length())){
				paraMap.put("checkPeopleName", "%" + danTasMan.getCheckPeopleName().trim() + "%");
			}

			if ((null != danTasMan.getCheckType()) && (0 < danTasMan.getCheckType().trim().length())){
				Map m = new HashMap();
				m.put("name","%"+ danTasMan.getCheckType()+"%");
				List<String> ids = patTypManService.getPatTypeIds(m);
				paraMap.put("checkType", ids);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		//codeMap.put("areaName","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|dangerName|checkTime|checkPeopleName|areaName|companyName|taskName|checkType|checkTimeEnd|result|assPlanNo|";
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
		
		//企业只能看到自己的 其他角色看到所有
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			//登录人为企业 只可以查看自己添加信息
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				paraMap.put("createUserId", this.getLoginUser().getId());
				break;
			}
		}
		
		pagination = danTasManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != danTasMan)&&(null != danTasMan.getId())){
			danTasMan = danTasManService.getById(danTasMan.getId());
			Map map = new HashMap();
			map.put("linkId",danTasMan.getId());
		    try {
				picList = photoPicService.findPicPath(map);//获取执法文书
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				//设置巡查单号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
				danTasMan.setChenkNo(sdf.format(new Date()));
				//获取企业区域
				Map map = new HashMap();
				map.put("loginId", this.getLoginUser().getLoginId());
				EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				danTasMan.setAreaId(enBaseInfo.getEnterprisePossession());
				Map m = new HashMap();
				m.put("codeName", "企业属地");
				m.put("itemValue", enBaseInfo.getEnterprisePossession());
				danTasMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
				danTasMan.setCompanyId(enBaseInfo.getId());
				danTasMan.setCompanyName(enBaseInfo.getEnterpriseName());
				danTasMan.setCreateUserID(this.getLoginUser().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.format(new Date());
		
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
		
		SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmSS");
		if ("add".equalsIgnoreCase(this.flag)){
			String checkIds=danTasMan.getCheckId();//找出巡查点的id
			String ids[]=checkIds.split(", ");
			for(int i=0;i<ids.length;i++){   //每个巡查点遍历生成一个任务
				DanTasMan dan= new DanTasMan();
				dan.setChenkNo(sdff.format(new Date()));
				dan.setCreateTime(danTasMan.getCreateTime());
				dan.setAreaId(danTasMan.getAreaId());
				dan.setAreaName(danTasMan.getAreaName());
				dan.setCompanyId(danTasMan.getCompanyId());
				dan.setCompanyName(danTasMan.getCompanyName());
				dan.setCheckPeopleId(danTasMan.getCheckPeopleId());
				dan.setCheckPeopleName(danTasMan.getCheckPeopleName());
				dan.setTaskName(danTasMan.getTaskName()+"-"+dotService.getById(ids[i]).getDotName());
				dan.setCheckKind("日常巡查");
				dan.setDelFlag(0);
				dan.setResult("待巡查");
				dan.setCheckTime(danTasMan.getCheckTime());
				dan.setCheckTimeEnd(danTasMan.getCheckTimeEnd());
				dan.setCheckId(ids[i]);
				dan.setCheckName(dotService.getById(ids[i]).getDotName());
				dan.setCreateUserID(this.getLoginUser().getId());
				danTasManService.save(dan);
				pushInfo(dan.getId(),dan.getCreateUserID(),dan.getTaskName());
			}
		
			
		}else{
			danTasManService.update(danTasMan);
		}
		
		return RELOAD;
	}

	private void pushInfo(String id,String userId,String title){
		   try {
					// 信息推送
					Map send = new HashMap();

					String[] userIds = new String[]{userId};
					send.put("type", "10");
					JSONObject json = new JSONObject();
					json.put("id", id);
					send.put("content",json.toString());
					//信息推送
					JPushClient jpush = new JPushClient();
					jpush.sendAndroidNotificationWithAlias("园区安监","待巡查的危险源任务："+title, send,userIds);
				
				} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != danTasMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到danTasMan中去
				
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
			danTasManService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 初始化修改信息
	 */
	public String upload() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String saveUpload() throws Exception{
		String remark = danTasMan.getRemark();
		danTasMan = danTasManService.getById(danTasMan.getId());
		danTasMan.setRealCheckTerm(danTasMan.getCheckName());
		danTasMan.setReportTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		danTasMan.setResult("已巡查");
		danTasMan.setRemark(remark);
		danTasManService.update(danTasMan);
		return RELOAD;
	}
	

	public String zwtInit(){
		
		danTasMan.setResult("");
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			roleName = "0";
		}else if(httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A10")){//登录人为监察大队角色 才可以审核
			roleName = "1";
		}
		return SUCCESS;
	}

    public String wxyxcrwgl(){
    	danTasMan.setResult("");
    	String userId=this.getLoginUser().getId();
    	if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			roleName = "0";
		}else if(httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A10")){//登录人为监察大队角色 才可以审核
			roleName = "1";
		}
    	
    	return SUCCESS;
    }
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != danTasMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != danTasMan.getAssPlanNo() && (0 < danTasMan.getAssPlanNo().trim().length())){
				paraMap.put("assPlanNo",  danTasMan.getAssPlanNo());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|dangerName|checkTime|checkPeopleName|areaName|companyName|taskName|checkType|checkTimeEnd|result|assPlanNo|";
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
		pagination = danTasManService.findByPage(pagination, paraMap);
		
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

	public DanTasMan getDanTasMan(){
		return this.danTasMan;
	}

	public void setDanTasMan(DanTasMan danTasMan){
		this.danTasMan = danTasMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryCheckTimeStart(){
		return this.queryCheckTimeStart;
	}

	public void setQueryCheckTimeStart(Date queryCheckTimeStart){
		this.queryCheckTimeStart = queryCheckTimeStart;
	}

	public Date getQueryCheckTimeEnd(){
		return this.queryCheckTimeEnd;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public void setQueryCheckTimeEnd(Date queryCheckTimeEnd){
		this.queryCheckTimeEnd = queryCheckTimeEnd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
