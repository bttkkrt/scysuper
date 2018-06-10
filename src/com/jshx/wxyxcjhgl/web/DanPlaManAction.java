package com.jshx.wxyxcjhgl.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.wxyxcjhgl.entity.DanPlaMan;
import com.jshx.wxyxcjhgl.service.DanPlaManService;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.service.DanTasManService;
import com.jshx.xcxlxgl.service.PatTypManService;
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.service.ComDanIdeService;

public class DanPlaManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DanPlaMan danPlaMan = new DanPlaMan();

	/**
	 * 业务类
	 */
	@Autowired
	private DanPlaManService danPlaManService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private DanTasManService danTasManService;
	@Autowired
	private PatTypManService patTypManService;
	@Autowired
	private DotService dotService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private ComDanIdeService comDanIdeService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryPlanStartTimeStart;

	private Date queryPlanStartTimeEnd;

	private Date queryPlanEndTimeStart;

	private Date queryPlanEndTimeEnd;

	private String loginUserId;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	String roleName = "-1";//角色
	
	private String startDate="";
	
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
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	/**
	 * 初始化 用于判断审核角色
	 */
	@SuppressWarnings("unchecked")
	public String init(){
		
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
	@SuppressWarnings("unchecked")
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != danPlaMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != danPlaMan.getAreaId()) && (0 < danPlaMan.getAreaId().trim().length())){
				paraMap.put("areaId",  danPlaMan.getAreaId().trim() );
			}

			if ((null != danPlaMan.getAreaName()) && (0 < danPlaMan.getAreaName().trim().length())){
				paraMap.put("areaName", danPlaMan.getAreaName().trim());
			}

			if ((null != danPlaMan.getCompanyName()) && (0 < danPlaMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + danPlaMan.getCompanyName().trim() + "%");
			}
			if ((null != danPlaMan.getCompanyId()) && (0 < danPlaMan.getCompanyId().trim().length())){
				paraMap.put("companyId", danPlaMan.getCompanyId().trim() );
			}

			if ((null != danPlaMan.getPlanName()) && (0 < danPlaMan.getPlanName().trim().length())){
				paraMap.put("planName", "%" + danPlaMan.getPlanName().trim() + "%");
			}

			if ((null != danPlaMan.getDangerName()) && (0 < danPlaMan.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + danPlaMan.getDangerName().trim() + "%");
			}

			if ((null != danPlaMan.getCheckFrequency()) && (0 < danPlaMan.getCheckFrequency().trim().length())){
				paraMap.put("checkFrequency", danPlaMan.getCheckFrequency().trim());
			}

			if ((null != danPlaMan.getCheckPeopleName()) && (0 < danPlaMan.getCheckPeopleName().trim().length())){
				paraMap.put("checkPeopleName", "%" + danPlaMan.getCheckPeopleName().trim() + "%");
			}

			if ((null != danPlaMan.getCheckTypeId()) && (0 < danPlaMan.getCheckTypeId().trim().length())){
				Map m = new HashMap();
				m.put("name","%"+ danPlaMan.getCheckTypeId()+"%");
				List<String> ids = patTypManService.getPatTypeIds(m);
				paraMap.put("checkTypeId", ids);
			}

			if ((null != danPlaMan.getAuditResult()) && (0 < danPlaMan.getAuditResult().trim().length())){
				if("执行中".equals(danPlaMan.getAuditResult().trim())){
					paraMap.put("auditResult", "%审核通过%");
				}else{
					paraMap.put("auditResult", "%" + danPlaMan.getAuditResult().trim() + "%");
				}
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("checkFrequency","4028800550461444015046a50a5a0115");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|companyName|checkKind|planName|dangerName|checkFrequency|checkPeopleName|checkTypeId|auditResult|isAudit|";
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
		
		pagination = danPlaManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != danPlaMan)&&(null != danPlaMan.getId())){
			danPlaMan = danPlaManService.getById(danPlaMan.getId());
		}else{
			//获取企业区域
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			danPlaMan.setAreaId(enBaseInfo.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			danPlaMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			danPlaMan.setCompanyId(enBaseInfo.getId());
			danPlaMan.setCompanyName(enBaseInfo.getEnterpriseName());
			danPlaMan.setCreateUserID(this.getLoginUser().getId());
		}
		//审核记录
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", danPlaMan.getId());
		checkRecords=checkRecordService.findCheckRecord(paraMap);
		
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
	 * 初始化审核信息
	 */
	public String initAudit() throws Exception{
		view();
		return  SUCCESS;
	}
	/**
	 * 保存审核结果
	 */
	public String saveAudit() throws Exception{
		if(danPlaMan.getId()!=null){
			String auditResult = danPlaMan.getAuditResult();
			String remark = danPlaMan.getRemark();
			DanPlaMan newDanPlaMan = danPlaManService.getById(danPlaMan.getId());
			newDanPlaMan.setAuditPersonId(this.getLoginUser().getId());
			newDanPlaMan.setAuditPersonName(this.getLoginUser().getDisplayName());
			newDanPlaMan.setAuditResult(auditResult);
			newDanPlaMan.setRemark(remark);
			danPlaManService.update(newDanPlaMan);
			//如果审核通过 直接根据巡查频率生成任务  暂时不开发 lj 2015-10-15
			
			//此处修改 直接生成任务 lj 2015-12-02
			
			
			if("审核通过".equals(auditResult)){
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");
					String frequency = newDanPlaMan.getCheckFrequency();//week month year
					
					Calendar c = Calendar.getInstance();
					Date startTime  = newDanPlaMan.getPlanStartTime();//计划开始时间
					Date endTime = newDanPlaMan.getPlanEndTime();//计划结束时间
					/**
					if("week".equals(newDanPlaMan.getCheckFrequency())){//每周
						List<Map> weeks = weekDate(sdf.format(startTime),sdf.format(endTime));
						if(weeks!=null&&!weeks.isEmpty()){
							for(Map m:weeks){
								DanTasMan danTasMan = newTask(newDanPlaMan);
								danTasMan.setTaskName(newDanPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							}
						}
						
					}else if("month".equals(newDanPlaMan.getCheckFrequency())){//每月
						List<Map> months = monthDate(sdf.format(startTime),sdf.format(endTime));
						if(months!=null&&!months.isEmpty()){
							for(Map m:months){
								DanTasMan danTasMan = newTask(newDanPlaMan);
								danTasMan.setTaskName(newDanPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							}
						}
					}else if("year".equals(newDanPlaMan.getCheckFrequency())){//每年
						List<Map> years = yearDate(sdf.format(startTime),sdf.format(endTime));
						if(years!=null&&!years.isEmpty()){
							for(Map m:years){
								DanTasMan danTasMan = newTask(newDanPlaMan);
								danTasMan.setTaskName(newDanPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							}
						}
					}
					*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//保存审核记录 lj 2015-10-30
			checkRecord.setInfoId(danPlaMan.getId());
			checkRecord.setCheckResult(auditResult);
			checkRecord.setCheckRemark(remark);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
			
		return RELOAD;
	}
	
	public DanTasMan newTask(DanPlaMan newDanPlaMan){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmSS");
		DanTasMan danTasMan = new DanTasMan();
		try {
			danTasMan.setChenkNo(sdff.format(new Date()));
			danTasMan.setCreateTime(newDanPlaMan.getCreateTime());
			danTasMan.setAreaId(newDanPlaMan.getAreaId());
			danTasMan.setAreaName(newDanPlaMan.getAreaName());
			danTasMan.setCompanyId(newDanPlaMan.getCompanyId());
			danTasMan.setCompanyName(newDanPlaMan.getCompanyName());
			danTasMan.setCheckPeopleId(newDanPlaMan.getCheckPeopleId());
			danTasMan.setCheckPeopleName(newDanPlaMan.getCheckPeopleName());
			danTasMan.setAssPlanNo(newDanPlaMan.getId());
			danTasMan.setDangerId(newDanPlaMan.getDangerId());
			danTasMan.setDangerName(newDanPlaMan.getDangerName());
			danTasMan.setCheckType(newDanPlaMan.getCheckTypeId());
			danTasMan.setCheckTime(sdf.parse(sdf.format(new Date())));
			danTasMan.setDelFlag(0);
			danTasMan.setResult("待巡查");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return danTasMan;
	}
	
	//根据时间段生成周任务的遍历
	public  List weekDate(String startTime,String endTime){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map =new HashMap<String,String>();
		//String startTime = "2015-10-30";
		//String endTime  = "2015-11-31";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			int w = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DAY_OF_WEEK, 8-w);
			String ee= sdf.format(c.getTime());
			if(sdf.parse(endTime).getTime()<c.getTime().getTime()){
				ee = endTime;
				
			}
			map.put("startTime",startTime);
			map.put("endTime",ee);
			list.add(map);
			while(c.getTime().getTime()<sdf.parse(endTime).getTime()){
				Map<String,String> map2 =new HashMap<String,String>();
				c.add(Calendar.DAY_OF_WEEK, 1);
				String s = sdf.format(c.getTime());
				c.add(Calendar.DAY_OF_WEEK, 6);
				String e = sdf.format(c.getTime());
				if(sdf.parse(endTime).getTime()<c.getTime().getTime()){
					e = endTime;
				}
				map2.put("startTime",s);
				map2.put("endTime",e);
				list.add(map2);
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	//根据时间段生成月任务的遍历
	public  List monthDate(String startTime,String endTime){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map =new HashMap<String,String>();
		//String startTime = "2015-10-30";
		//String endTime  = "2015-11-31";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			int w = c.get(Calendar.DAY_OF_MONTH);
			int end=c.getActualMaximum(Calendar.DAY_OF_MONTH);//每月最后一天
			c.set(Calendar.DAY_OF_MONTH, end);//设置时间为当月的月底
			String ee= sdf.format(c.getTime());
			if(sdf.parse(endTime).getTime()<c.getTime().getTime()){//如果当月最后的一天的时间大于结束时间
				ee = endTime;
				
			}
			map.put("startTime",startTime);
			map.put("endTime",ee);
			list.add(map);
			while(c.getTime().getTime()<sdf.parse(endTime).getTime()){//结束时间大于每月的最后一天
				Map<String,String> map2 =new HashMap<String,String>();
				c.add(Calendar.DAY_OF_MONTH, 1);
				String s = sdf.format(c.getTime());
				 end=c.getActualMaximum(Calendar.DAY_OF_MONTH);
				 c.set(Calendar.DAY_OF_MONTH, end);//设置时间为当月的月底
				String e = sdf.format(c.getTime());
				if(sdf.parse(endTime).getTime()<c.getTime().getTime()){
					e = endTime;
				}
				map2.put("startTime",s);
				map2.put("endTime",e);
				list.add(map2);
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	//根据时间段生成月任务的遍历
	public  List yearDate(String startTime,String endTime){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map =new HashMap<String,String>();
		//String startTime = "2015-10-30";
		//String endTime  = "2015-11-31";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			int w = c.get(Calendar.DAY_OF_YEAR);
			c.set(Calendar.DAY_OF_YEAR, w);//设置时间为当月的月底
			String ee= sdf.format(c.getTime());
			if(sdf.parse(endTime).getTime()<c.getTime().getTime()){//如果当月最后的一天的时间大于结束时间
				ee = endTime;
				
			}
			map.put("startTime",startTime);
			map.put("endTime",ee);
			list.add(map);
			while(c.getTime().getTime()<sdf.parse(endTime).getTime()){//结束时间大于每月的最后一天
				Map<String,String> map2 =new HashMap<String,String>();
				c.add(Calendar.DAY_OF_YEAR, 1);
				String s = sdf.format(c.getTime());
				 int end=c.getActualMaximum(Calendar.DAY_OF_YEAR);
				 c.set(Calendar.DAY_OF_YEAR, end);//设置时间为当月的月底
				String e = sdf.format(c.getTime());
				if(sdf.parse(endTime).getTime()<c.getTime().getTime()){
					e = endTime;
				}
				map2.put("startTime",s);
				map2.put("endTime",e);
				list.add(map2);
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
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
		String checkNames="";
	    String checkIds=danPlaMan.getCheckId();
	    String ids[]=checkIds.split(", ");
	    for(int i=0;i<ids.length;i++){
	    	String checkName=dotService.getById(ids[i]).getDotName();
	    	checkNames+=checkName+",";
		}
	    if(!checkNames.equals("")){
	    	checkNames=checkNames.substring(0,checkNames.length()-1);
		}
	    danPlaMan.setCheckName(checkNames);//获取巡检点名称
	    String isAudit = danPlaMan.getIsAudit();
		if("1".equals(isAudit)){//不需要大队审核 直接生成巡查任务
			try {
				danPlaMan.setAuditResult("审核通过");//默认审核通过
				danPlaMan.setAuditPersonId(this.getLoginUser().getId());
				danPlaMan.setAuditPersonName(this.getLoginUser().getDisplayName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("add".equalsIgnoreCase(this.flag)){
			danPlaMan.setDeptId(this.getLoginUserDepartmentId());
			danPlaMan.setDelFlag(0);
			danPlaManService.save(danPlaMan);
		}else{
			danPlaManService.update(danPlaMan);
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
		if (null != danPlaMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到danPlaMan中去
				
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
			danPlaManService.deleteWithFlag(ids);
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
		roleName = "-1";
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

	public String wxyxcjhgl(){
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
		    
		if(null != danPlaMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != danPlaMan.getAreaId()) && (0 < danPlaMan.getAreaId().trim().length())){
				paraMap.put("areaId",  danPlaMan.getAreaId().trim() );
			}

			if ((null != danPlaMan.getAreaName()) && (0 < danPlaMan.getAreaName().trim().length())){
				paraMap.put("areaName", danPlaMan.getAreaName().trim());
			}

			if ((null != danPlaMan.getCompanyName()) && (0 < danPlaMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + danPlaMan.getCompanyName().trim() + "%");
			}

			if ((null != danPlaMan.getPlanName()) && (0 < danPlaMan.getPlanName().trim().length())){
				paraMap.put("planName", "%" + danPlaMan.getPlanName().trim() + "%");
			}

			if ((null != danPlaMan.getDangerName()) && (0 < danPlaMan.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + danPlaMan.getDangerName().trim() + "%");
			}

			if ((null != danPlaMan.getCheckFrequency()) && (0 < danPlaMan.getCheckFrequency().trim().length())){
				paraMap.put("checkFrequency", danPlaMan.getCheckFrequency().trim());
			}

			if ((null != danPlaMan.getCheckPeopleName()) && (0 < danPlaMan.getCheckPeopleName().trim().length())){
				paraMap.put("checkPeopleName", "%" + danPlaMan.getCheckPeopleName().trim() + "%");
			}

			if ((null != danPlaMan.getCheckTypeId()) && (0 < danPlaMan.getCheckTypeId().trim().length())){
				Map m = new HashMap();
				m.put("name","%"+ danPlaMan.getCheckTypeId()+"%");
				List<String> ids = patTypManService.getPatTypeIds(m);
				paraMap.put("checkTypeId", ids);
			}

			if ((null != danPlaMan.getAuditResult()) && (0 < danPlaMan.getAuditResult().trim().length())){
				if("执行中".equals(danPlaMan.getAuditResult().trim())){
					paraMap.put("auditResult", "%审核通过%");
				}else{
					paraMap.put("auditResult", "%" + danPlaMan.getAuditResult().trim() + "%");
				}
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("checkFrequency","4028800550461444015046a50a5a0115");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|companyName|checkKind|planName|dangerName|checkFrequency|checkPeopleName|checkTypeId|auditResult|isAudit|";
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
		pagination = danPlaManService.findByPage(pagination, paraMap);
		
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
	
	public String danPlaManCreate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmSS");
		
		Date d = new Date();
		//定时生成任务
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d);
		int week=c2.get(Calendar.DAY_OF_WEEK)-1;//获取今天周几
		int datenum=c2.get(Calendar.DATE);//获取今天几号
		int month=c2.get(Calendar.MONTH) + 1;//获取当前月份
		int currentYear = c2.get(Calendar.YEAR);  
		Map map2 = new HashMap();
		map2.put("starttime",sdf.format(d));
		if(month==1&&datenum==1){ //1月1号生成年任务
			map2.put("checkFrequency", "year");
			map2.put("endtime",currentYear+"-12-31");
			List<DanPlaMan> planYear=danPlaManService.getDanPlans(map2);//获取所有日常年计划
			for(DanPlaMan newDanPlaMan:planYear){
				try {
					String checkIds=newDanPlaMan.getCheckId();//找出巡查点的id
					if(checkIds != null && !"".equals(checkIds))
					{
						String ids[]=checkIds.replaceAll(" ", "").split(",");
						String names[]=newDanPlaMan.getCheckName().replaceAll(" ", "").split(",");
						for(int i=0;i<ids.length;i++){   //每个巡查点遍历生成一个任务
							DanTasMan danTasMan = new DanTasMan();
							String id = java.util.UUID.randomUUID().toString().replace("-", "");
							danTasMan.setId(id);
							danTasMan.setChenkNo(sdff.format(new Date()));
							danTasMan.setCreateTime(d);
							danTasMan.setAreaId(newDanPlaMan.getAreaId());
							danTasMan.setAreaName(newDanPlaMan.getAreaName());
							danTasMan.setCompanyId(newDanPlaMan.getCompanyId());
							danTasMan.setCompanyName(newDanPlaMan.getCompanyName());
							danTasMan.setCheckPeopleId(newDanPlaMan.getCheckPeopleId());
							danTasMan.setCheckPeopleName(newDanPlaMan.getCheckPeopleName());
							danTasMan.setAssPlanNo(newDanPlaMan.getId());
							danTasMan.setDelFlag(0);
							danTasMan.setTaskName(sdff.format(new Date())+"-"+names[i]);
							danTasMan.setCheckKind("日常巡查");
							danTasMan.setResult("待巡查");
							danTasMan.setCheckTime(d);
							danTasMan.setCheckTimeEnd(sdf.parse(currentYear+"-12-31"));
							danTasMan.setCheckId(ids[i]);
							danTasMan.setCheckName(names[i]);
							danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							danTasManService.saveDanTask(danTasMan);
						 }
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<ComDanIde> WxyplanYear=comDanIdeService.getWxyPlans(map2);//获取所有危险源年计划
			for(ComDanIde com:WxyplanYear){
				try {
					DanTasMan dan = new DanTasMan();
					String id = java.util.UUID.randomUUID().toString().replace("-", "");
					dan.setId(id);
					dan.setChenkNo(sdff.format(new Date()));
					dan.setCreateTime(d);
					dan.setAreaId(com.getAreaId());
					dan.setAreaName(com.getAreaName());
					dan.setCompanyId(com.getCompanyId());
					dan.setCompanyName(com.getCompanyName());
					dan.setCheckPeopleId(com.getCheckPeopleId());
					dan.setCheckPeopleName(com.getCheckPeopleName());
					dan.setDelFlag(0);
					dan.setTaskName(sdff.format(new Date())+"-"+com.getDangerName());
					dan.setCheckKind("危险源巡查");
					dan.setResult("待巡查");
					dan.setCheckTime(d);
					dan.setCheckTimeEnd(sdf.parse(currentYear+"-12-31"));
					dan.setCheckId(com.getId());
					dan.setCheckName(com.getDangerName());
					dan.setCreateUserID(com.getCreateUserID());
					danTasManService.saveDanTask(dan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(datenum==1){
			Calendar c4 = Calendar.getInstance();
			c4.set(Calendar.DAY_OF_MONTH, c4.getActualMaximum(Calendar.DAY_OF_MONTH));  
			map2.put("checkFrequency", "month");
			map2.put("endtime",sdf.format(c4.getTime()));
			List<DanPlaMan> planMonth=danPlaManService.getDanPlans(map2);//获取所有日常月计划
			for(DanPlaMan newDanPlaMan:planMonth){
				try {
					String checkIds=newDanPlaMan.getCheckId();//找出巡查点的id
					if(checkIds != null && !"".equals(checkIds))
					{
						String ids[]=checkIds.replaceAll(" ", "").split(",");
						String names[]=newDanPlaMan.getCheckName().replaceAll(" ", "").split(",");
						for(int i=0;i<ids.length;i++){   //每个巡查点遍历生成一个任务
							DanTasMan danTasMan = new DanTasMan();
							String id = java.util.UUID.randomUUID().toString().replace("-", "");
							danTasMan.setId(id);
							danTasMan.setChenkNo(sdff.format(new Date()));
							danTasMan.setCreateTime(d);
							danTasMan.setAreaId(newDanPlaMan.getAreaId());
							danTasMan.setAreaName(newDanPlaMan.getAreaName());
							danTasMan.setCompanyId(newDanPlaMan.getCompanyId());
							danTasMan.setCompanyName(newDanPlaMan.getCompanyName());
							danTasMan.setCheckPeopleId(newDanPlaMan.getCheckPeopleId());
							danTasMan.setCheckPeopleName(newDanPlaMan.getCheckPeopleName());
							danTasMan.setAssPlanNo(newDanPlaMan.getId());
							danTasMan.setDelFlag(0);
							danTasMan.setTaskName(sdff.format(new Date())+"-"+names[i]);
							danTasMan.setCheckKind("日常巡查");
							danTasMan.setResult("待巡查");
							danTasMan.setCheckTime(d);
							danTasMan.setCheckTimeEnd(c4.getTime());
							danTasMan.setCheckId(ids[i]);
							danTasMan.setCheckName(names[i]);
							danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							danTasManService.saveDanTask(danTasMan);
						 }
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			List<ComDanIde> WxyplanMonth=comDanIdeService.getWxyPlans(map2);//获取所有危险源月计划
			for(ComDanIde com:WxyplanMonth){
				try {
					DanTasMan dan = new DanTasMan();
					String id = java.util.UUID.randomUUID().toString().replace("-", "");
					dan.setId(id);
					dan.setChenkNo(sdff.format(new Date()));
					dan.setCreateTime(d);
					dan.setAreaId(com.getAreaId());
					dan.setAreaName(com.getAreaName());
					dan.setCompanyId(com.getCompanyId());
					dan.setCompanyName(com.getCompanyName());
					dan.setCheckPeopleId(com.getCheckPeopleId());
					dan.setCheckPeopleName(com.getCheckPeopleName());
					dan.setDelFlag(0);
					dan.setTaskName(sdff.format(new Date())+"-"+com.getDangerName());
					dan.setCheckKind("危险源巡查");
					dan.setResult("待巡查");
					dan.setCheckTime(d);
					dan.setCheckTimeEnd(c4.getTime());
					dan.setCheckId(com.getId());
					dan.setCheckName(com.getDangerName());
					dan.setCreateUserID(com.getCreateUserID());
					danTasManService.saveDanTask(dan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} 
		if(week==1){
			Calendar c5 = Calendar.getInstance();
			c5.setTime(d);
			c5.add(Calendar.DATE, 6);
			map2.put("checkFrequency", "week");
			map2.put("endtime",sdf.format(c5.getTime()));
			List<DanPlaMan> planWeek=danPlaManService.getDanPlans(map2);//获取所有日常周计划
			for(DanPlaMan newDanPlaMan:planWeek){
				try {
					String checkIds=newDanPlaMan.getCheckId();//找出巡查点的id
					if(checkIds != null && !"".equals(checkIds))
					{
						String ids[]=checkIds.replaceAll(" ", "").split(",");
						String names[]=newDanPlaMan.getCheckName().replaceAll(" ", "").split(",");
						for(int i=0;i<ids.length;i++){   //每个巡查点遍历生成一个任务
							DanTasMan danTasMan = new DanTasMan();
							String id = java.util.UUID.randomUUID().toString().replace("-", "");
							danTasMan.setId(id);
							danTasMan.setChenkNo(sdff.format(new Date()));
							danTasMan.setCreateTime(d);
							danTasMan.setAreaId(newDanPlaMan.getAreaId());
							danTasMan.setAreaName(newDanPlaMan.getAreaName());
							danTasMan.setCompanyId(newDanPlaMan.getCompanyId());
							danTasMan.setCompanyName(newDanPlaMan.getCompanyName());
							danTasMan.setCheckPeopleId(newDanPlaMan.getCheckPeopleId());
							danTasMan.setCheckPeopleName(newDanPlaMan.getCheckPeopleName());
							danTasMan.setAssPlanNo(newDanPlaMan.getId());
							danTasMan.setDelFlag(0);
							danTasMan.setTaskName(sdff.format(new Date())+"-"+names[i]);
							danTasMan.setCheckKind("日常巡查");
							danTasMan.setResult("待巡查");
							danTasMan.setCheckTime(d);
							danTasMan.setCheckTimeEnd(c5.getTime());
							danTasMan.setCheckId(ids[i]);
							danTasMan.setCheckName(names[i]);
							danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							danTasManService.saveDanTask(danTasMan);
						 }
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			List<ComDanIde> WxyplanWeek=comDanIdeService.getWxyPlans(map2);//获取所有危险源周计划
			for(ComDanIde com:WxyplanWeek){
				try {
					DanTasMan dan = new DanTasMan();
					String id = java.util.UUID.randomUUID().toString().replace("-", "");
					dan.setId(id);
					dan.setChenkNo(sdff.format(new Date()));
					dan.setCreateTime(d);
					dan.setAreaId(com.getAreaId());
					dan.setAreaName(com.getAreaName());
					dan.setCompanyId(com.getCompanyId());
					dan.setCompanyName(com.getCompanyName());
					dan.setCheckPeopleId(com.getCheckPeopleId());
					dan.setCheckPeopleName(com.getCheckPeopleName());
					dan.setDelFlag(0);
					dan.setTaskName(sdff.format(new Date())+"-"+com.getDangerName());
					dan.setCheckKind("危险源巡查");
					dan.setResult("待巡查");
					dan.setCheckTime(d);
					dan.setCheckTimeEnd(c5.getTime());
					dan.setCheckId(com.getId());
					dan.setCheckName(com.getDangerName());
					dan.setCreateUserID(com.getCreateUserID());
					danTasManService.saveDanTask(dan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			map2.put("checkFrequency", "day");
			map2.put("endtime",sdf.format(d));
			List<DanPlaMan> planDay=danPlaManService.getDanPlans(map2);//获取所有日常日计划
			for(DanPlaMan newDanPlaMan:planDay){
				try {
					String checkIds=newDanPlaMan.getCheckId();//找出巡查点的id
					if(checkIds != null && !"".equals(checkIds))
					{
						String ids[]=checkIds.replaceAll(" ", "").split(",");
						String names[]=newDanPlaMan.getCheckName().replaceAll(" ", "").split(",");
						for(int i=0;i<ids.length;i++){   //每个巡查点遍历生成一个任务
							DanTasMan danTasMan = new DanTasMan();
							String id = java.util.UUID.randomUUID().toString().replace("-", "");
							danTasMan.setId(id);
							danTasMan.setChenkNo(sdff.format(new Date()));
							danTasMan.setCreateTime(d);
							danTasMan.setAreaId(newDanPlaMan.getAreaId());
							danTasMan.setAreaName(newDanPlaMan.getAreaName());
							danTasMan.setCompanyId(newDanPlaMan.getCompanyId());
							danTasMan.setCompanyName(newDanPlaMan.getCompanyName());
							danTasMan.setCheckPeopleId(newDanPlaMan.getCheckPeopleId());
							danTasMan.setCheckPeopleName(newDanPlaMan.getCheckPeopleName());
							danTasMan.setAssPlanNo(newDanPlaMan.getId());
							danTasMan.setDelFlag(0);
							danTasMan.setTaskName(sdff.format(new Date())+"-"+names[i]);
							danTasMan.setCheckKind("日常巡查");
							danTasMan.setResult("待巡查");
							danTasMan.setCheckTime(d);
							danTasMan.setCheckTimeEnd(d);
							danTasMan.setCheckId(ids[i]);
							danTasMan.setCheckName(names[i]);
							danTasMan.setCreateUserID(newDanPlaMan.getCreateUserID());
							danTasManService.saveDanTask(danTasMan);
						 }
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			List<ComDanIde> WxyplanDay=comDanIdeService.getWxyPlans(map2);//获取所有危险源日计划
			for(ComDanIde com:WxyplanDay){
				try {
					DanTasMan dan = new DanTasMan();
					String id = java.util.UUID.randomUUID().toString().replace("-", "");
					dan.setId(id);
					dan.setChenkNo(sdff.format(new Date()));
					dan.setCreateTime(d);
					dan.setAreaId(com.getAreaId());
					dan.setAreaName(com.getAreaName());
					dan.setCompanyId(com.getCompanyId());
					dan.setCompanyName(com.getCompanyName());
					dan.setCheckPeopleId(com.getCheckPeopleId());
					dan.setCheckPeopleName(com.getCheckPeopleName());
					dan.setDelFlag(0);
					dan.setTaskName(sdff.format(new Date())+"-"+com.getDangerName());
					dan.setCheckKind("危险源巡查");
					dan.setResult("待巡查");
					dan.setCheckTime(d);
					dan.setCheckTimeEnd(d);
					dan.setCheckId(com.getId());
					dan.setCheckName(com.getDangerName());
					dan.setCreateUserID(com.getCreateUserID());
					danTasManService.saveDanTask(dan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return RELOAD;
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

	public DanPlaMan getDanPlaMan(){
		return this.danPlaMan;
	}

	public void setDanPlaMan(DanPlaMan danPlaMan){
		this.danPlaMan = danPlaMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPlanStartTimeStart(){
		return this.queryPlanStartTimeStart;
	}

	public void setQueryPlanStartTimeStart(Date queryPlanStartTimeStart){
		this.queryPlanStartTimeStart = queryPlanStartTimeStart;
	}

	public Date getQueryPlanStartTimeEnd(){
		return this.queryPlanStartTimeEnd;
	}

	public void setQueryPlanStartTimeEnd(Date queryPlanStartTimeEnd){
		this.queryPlanStartTimeEnd = queryPlanStartTimeEnd;
	}

	public Date getQueryPlanEndTimeStart(){
		return this.queryPlanEndTimeStart;
	}

	public void setQueryPlanEndTimeStart(Date queryPlanEndTimeStart){
		this.queryPlanEndTimeStart = queryPlanEndTimeStart;
	}

	public Date getQueryPlanEndTimeEnd(){
		return this.queryPlanEndTimeEnd;
	}

	public void setQueryPlanEndTimeEnd(Date queryPlanEndTimeEnd){
		this.queryPlanEndTimeEnd = queryPlanEndTimeEnd;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
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
