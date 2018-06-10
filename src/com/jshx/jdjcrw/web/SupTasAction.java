package com.jshx.jdjcrw.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.service.CompanyInfoPlanService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.service.HttpService;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.entity.SupTasResult;
import com.jshx.jdjcrw.service.SupTasService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;
import com.jshx.xcjcjl.entity.SiteCheckRecord;
import com.jshx.xcjcjl.service.SiteCheckRecordService;
import com.jshx.xcxgl.service.PatManService;
import com.jshx.xcxlxgl.entity.PatTypMan;
import com.jshx.xcxlxgl.service.PatTypManService;

public class SupTasAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SupPla supPla = new SupPla();
	private SupTas supTas = new SupTas();
	private EntBaseInfo entBaseInfo = new EntBaseInfo();
	/**
	 * 业务类
	 */
	
	@Autowired
	private SupTasService supTasService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private SupPlaService supPlaService;
	
	@Autowired
	private PatTypManService patTypManService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private PatManService patManService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyInfoPlanService companyInfoPlanService;
	//现场检查记录
	@Autowired
	private SiteCheckRecordService siteCheckRecordService;
	private SiteCheckRecord siteCheckRecord = new SiteCheckRecord();
	@Autowired
	private InstrumentsInfoService instrumentsInfoService;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	private String userId;

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
	private List<Map> persons = new ArrayList<Map>();//人员列表
	private List<Map> checklist = new ArrayList<Map>();//检查项列表
	private List<SupTasResult> supTasResultlist = new ArrayList<SupTasResult>();//检查项结果列表
	private String roleName;
	
	private String mark;//部门标识
	
	private Date today;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
	
	List<Map> list3=new ArrayList<Map>();
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A11")) 
			{
				roleName = "11";//安监中队队长
				break;
			}if(ur.getRole().getRoleCode().equals("A09")) 
			{
				roleName = "09";//监察大队队长
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
		if(null != supTas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supTas.getAreaId()) && (0 < supTas.getAreaId().trim().length())){
				paraMap.put("areaId",  supTas.getAreaId().trim() );
			}

			if ((null != supTas.getCompanyName()) && (0 < supTas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + supTas.getCompanyName().trim() + "%");
			}

			if ((null != supTas.getTaskNum()) && (0 < supTas.getTaskNum().trim().length())){
				paraMap.put("taskNum", "%" + supTas.getTaskNum().trim() + "%");
			}

			if (null != queryCheckTimeStart){
				paraMap.put("startCheckTime", queryCheckTimeStart);
			}

			if (null != queryCheckTimeEnd){
				paraMap.put("endCheckTime", queryCheckTimeEnd);
			}
			if ((null != supTas.getCheckUsername()) && (0 < supTas.getCheckUsername().trim().length())){
				paraMap.put("checkUsername", "%" + supTas.getCheckUsername().trim() + "%");
			}

			if ((null != supTas.getTaskState()) && (0 < supTas.getTaskState().trim().length())&&(!supTas.getTaskState().equals("请选择"))){
				paraMap.put("taskState", supTas.getTaskState().trim());
			}

			if ((null != supTas.getTaskType()) && (0 < supTas.getTaskType().trim().length())&&(!supTas.getTaskType().equals("请选择"))){
				paraMap.put("taskType", supTas.getTaskType().trim());
			}
			if ((null != supTas.getPlanId()) && (0 < supTas.getPlanId().trim().length())){
				paraMap.put("planId",  supTas.getPlanId().trim());
			}
			List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight r:urs){
				if("A12".equals(r.getRole().getRoleCode())||"A13".equals(r.getRole().getRoleCode())){//中队队员和检查机构看见检查id是自己的任务
					paraMap.put("checkUserid",this.getLoginUser().getId());
					break;
				} if("A11".equals(r.getRole().getRoleCode())){//中队长看见检查部门是自己的任务
					paraMap.put("checkDeptId",this.getLoginUser().getDeptCode());
					break;
				} if("A07".equals(r.getRole().getRoleCode())||"A08".equals(r.getRole().getRoleCode())){//职业卫生处看见的是职业卫生的任务
					paraMap.put("checkDeptId","00500%");
					break;
				}
				if("A23".equals(r.getRole().getRoleCode())){//企业查看自己企业相关任务
					Map map = new HashMap();
					map.put("loginId", this.getLoginUser().getLoginId());
					EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
					paraMap.put("companyName","%" + enBaseInfo.getEnterpriseName()+"%");
					break;
				}
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|taskNum|checkTime|checkUsername|taskState|taskType|stime|ftime|createUserID|checkUserid|xbUserId|xbUserName|";
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
		pagination = supTasService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest()); 
		if(null != supTas){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supTas.getAreaId()) && (0 < supTas.getAreaId().trim().length())){
				paraMap.put("areaId", supTas.getAreaId().trim() );
			}

			if ((null != supTas.getCompanyName()) && (0 < supTas.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + supTas.getCompanyName().trim() + "%");
			}

			if ((null != supTas.getTaskNum()) && (0 < supTas.getTaskNum().trim().length())){
				paraMap.put("taskNum", "%" + supTas.getTaskNum().trim() + "%");
			}

			if (null != queryCheckTimeStart){
				paraMap.put("startCheckTime", queryCheckTimeStart);
			}

			if (null != queryCheckTimeEnd){
				paraMap.put("endCheckTime", queryCheckTimeEnd);
			}
			if ((null != supTas.getCheckUsername()) && (0 < supTas.getCheckUsername().trim().length())){
				paraMap.put("checkUsername", "%" + supTas.getCheckUsername().trim() + "%");
			}
			if ((null != supTas.getTaskType()) && (0 < supTas.getTaskType().trim().length())&&(!supTas.getTaskType().equals("请选择"))){
				paraMap.put("taskType", supTas.getTaskType().trim());
			}
			if ((null != supTas.getPlanId()) && (0 < supTas.getPlanId().trim().length())){
				paraMap.put("planId",  supTas.getPlanId().trim());
			}
			List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight r:urs){
				if("A12".equals(r.getRole().getRoleCode())){
					paraMap.put("checkUserid",this.getLoginUser().getId());
				}if("A11".equals(r.getRole().getRoleCode())){
					paraMap.put("checkDeptId","%"+this.getLoginUserDepartment().getDeptCode()+"%");
				}if("A07".equals(r.getRole().getRoleCode())||"A08".equals(r.getRole().getRoleCode())){
					paraMap.put("checkDeptId","005%");
				}
				if("A23".equals(r.getRole().getRoleCode())){//企业查看自己企业相关任务
					Map map = new HashMap();
					map.put("loginId", this.getLoginUser().getLoginId());
					EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
					paraMap.put("companyName","%" + enBaseInfo.getEnterpriseName()+"%");
				}
				break;
			}
			paraMap.put("taskState","已完成");
			
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|taskNum|checkTime|checkUsername|taskState|taskType|stime|ftime|createUserID|checkUserid|xbUserId|xbUserName|";
		
		pagination = supTasService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != supTas)&&(null != supTas.getId()))
			supTas = supTasService.getById(supTas.getId());
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		Map map = new HashMap();
		map.put("code",deptCode+"%");
		persons= supPlaService.getPersonsByMap(map);//获取人员信息
		if(supTas.getTaskState().equals("未完成")){//为上报的时候查询实时的巡查项
		Map paraMap=new HashMap();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		paraMap.put("checkItemId",supTas.getCheckItemId());
		list=httpService.findcheckName(paraMap);
		String b="";
		for(Map m:list){
			String a=m.get("checkItemName").toString();
			 b+=a+",";
		}
		if(!b.equals("")){
			b=b.substring(0,b.length()-1);
		}
		supTas.setPlanCheckName(b);
		}
		
		Map resultMap=new HashMap();
		resultMap.put("taskId", supTas.getId());
		supTasResultlist=supPlaService.findSupTasResultByMap(resultMap);
		for(SupTasResult result:supTasResultlist){
			result.setPatMan(patManService.getById(result.getXcxId()));
		}
		return VIEW;
		
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		Date date=new Date();
		today=date;
		if((null != supTas)&&(null != supTas.getId()))
			supTas = supTasService.getById(supTas.getId());
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		Map map = new HashMap();
		map.put("code",deptCode+"%");
		List<UserRight> lists =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:lists)
		{
			
			if(ur.getRole().getRoleCode().equals("A09")) 
			{
				map.put("userId",this.getLoginUser().getId());
				break;
			}
		}
		persons= supPlaService.getPersonsByMap(map);//获取人员信息
		Map paraMap=new HashMap();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		paraMap.put("checkItemId",supTas.getCheckItemId());
		list=httpService.findcheckName(paraMap);
		String b="";
		for(Map m:list){
			String a=m.get("checkItemName").toString();
			 b+=a+",";
		}
		if(!b.equals("")){
			b=b.substring(0,b.length()-1);
		}
		supTas.setPlanCheckName(b);
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		
		if ("add".equalsIgnoreCase(this.flag)){
			String checkCompanyId= supTas.getCompanyId();
			String ids[]=checkCompanyId.split(",");
			String checkCompanyName=supTas.getCompanyName();
			String names[]=checkCompanyName.split(",");
			for(int i=0;i<ids.length;i++){
				int ssnum = i+1;
				Date d = new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
				SupTas supTas2 = new SupTas();
				entBaseInfo = entBaseInfoService.getById(ids[i].trim());
				supTas2.setAreaId(entBaseInfo.getGrid());
				supTas2.setAreaName(entBaseInfo.getGridName());
				supTas2.setTaskType("临时任务");
				supTas2.setTaskState("未完成");
				supTas2.setCheckDeptId(this.getLoginUserDepartment().getDeptCode());
				supTas2.setTaskNum(supTas.getTaskName()+sdf2.format(d)+ssnum);
				supTas2.setTaskName(supTas.getTaskName());
				supTas2.setCheckItemId(supTas.getCheckItemId());
				String name="";
		            for(String citId:supTas.getCheckItemId().split(",")){
		            	PatTypMan patTypMan = new PatTypMan();
			            patTypMan=patTypManService.getById(citId.trim());
			            name+=patTypMan.getPatrolTypeName()+",";
		            }
		            if(name.endsWith(",")){
		            	name=name.substring(0, name.length()-1);
		            }
				supTas2.setCheckItemName(name);
				supTas2.setCheckUserid(supTas.getCheckUserid());
				supTas2.setCheckUsername(supTas.getCheckUsername());
				supTas2.setXbUserId(supTas.getXbUserId());
				supTas2.setXbUserName(supTas.getXbUserName());
				supTas2.setCompanyId(ids[i].trim());
				supTas2.setCompanyName(names[i].trim());
				supTas2.setStime(supTas.getStime());
				supTas2.setFtime(supTas.getFtime());
				supTas2.setDelFlag(0);
				supTasService.save(supTas2);
				pushInfo(supTas2.getId(),supTas2.getCheckUserid(),supTas2.getTaskName());
			}
			
		}else{
			supTasService.deleteWithFlag(supTas.getId());//更新的时候删除原本计划的任务
			String checkCompanyId= supTas.getCompanyId();
			String ids[]=checkCompanyId.split(",");
			String checkCompanyName=supTas.getCompanyName();
			String names[]=checkCompanyName.split(",");
			for(int i=0;i<ids.length;i++){
				int ssnum = i+1;
				Date d = new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
				SupTas supTas2 = new SupTas();
				entBaseInfo = entBaseInfoService.getById(ids[i]);
				supTas2.setAreaId(entBaseInfo.getGrid());
				supTas2.setAreaName(entBaseInfo.getGridName());
				supTas2.setTaskType("临时任务");
				supTas2.setTaskState("未完成");
				supTas2.setCheckDeptId(this.getLoginUserDepartment().getDeptCode());
				
				supTas2.setTaskNum(supTas.getTaskName()+sdf2.format(d)+ssnum);
				supTas2.setTaskName(supTas.getTaskName());
				supTas2.setCheckItemId(supTas.getCheckItemId());
				String name="";
				for(String citId:supTas.getCheckItemId().split(",")){
	            	PatTypMan patTypMan = new PatTypMan();
		            patTypMan=patTypManService.getById(citId.trim());
		            name+=patTypMan.getPatrolTypeName()+",";
	            }
	            if(name.endsWith(",")){
	            	name=name.substring(0, name.length()-1);
	            }
	            supTas2.setCheckItemName(name);
				supTas2.setCheckUserid(supTas.getCheckUserid());
				supTas2.setCheckUsername(supTas.getCheckUsername());
				supTas2.setXbUserId(supTas.getXbUserId());
				supTas2.setXbUserName(supTas.getXbUserName());
				supTas2.setCompanyId(ids[i]);
				supTas2.setCompanyName(names[i]);
				supTas2.setStime(supTas.getStime());
				supTas2.setFtime(supTas.getFtime());
				supTas2.setDelFlag(0);
				supTasService.save(supTas2);
				pushInfo(supTas2.getId(),supTas2.getCheckUserid(),supTas2.getTaskName());
			}
		}
		
		return RELOAD;
	}

	private void pushInfo(String id,String userId,String title){
		   try {
					// 信息推送
					Map send = new HashMap();

					String[] userIds = new String[]{userId};
					send.put("type", "7");
					JSONObject json = new JSONObject();
					json.put("id", id);
					send.put("content",json.toString());
						//信息推送
					JPushClient jpush = new JPushClient();
					jpush.sendAndroidNotificationWithAlias("园区安监","待检查任务："+title, send,userIds);
				
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
		if (null != supTas)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到supTas中去
				
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
			supTasService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 跳转到上报检查信息页面
	 */
	@SuppressWarnings("unchecked")
	public String supTasSbxx()throws Exception{
		supTas = supTasService.getById(supTas.getId());
		if(supTas.getTaskState() != null && "已完成".equals(supTas.getTaskState()))
		{
			
			Map resultMap=new HashMap();
			resultMap.put("taskId", supTas.getId());
			supTasResultlist=supPlaService.findSupTasResultByMap(resultMap);
			for(SupTasResult result:supTasResultlist){
				result.setPatMan(patManService.getById(result.getXcxId()));
			}
		}
		else
		{
			Map paraMap=new HashMap();
			paraMap.put("checkItemId",supTas.getCheckItemId());
			list2=httpService.findcheckName(paraMap); 
			for(Map m:list2){
				SupTasResult result=new SupTasResult();
				result.setTaskId(supTas.getId());
				result.setPatMan(patManService.getById(m.get("id").toString()));
				supTasResultlist.add(result);
			}
		}
		
		//String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
		//supTas.setLinkId(linkId);
		return SUCCESS;
		
	}
	/**
	 * 上报后更新信息
	 */
	public String supTasUpdate()throws Exception{
		SupTas supTas2 = new SupTas();
		supTas2=supTasService.getById(supTas.getId());
//		String CheckIds=supTas.getActualCheckName();
//		String ids[]=CheckIds.split(", ");
//		String checkNames="";
		
//		for(int i=0;i<ids.length;i++){
//			String checkName=patManService.getById(ids[i]).getPatrolName();
//			checkNames+=checkName+",";
//		}
//		if(!checkNames.equals("")){
//		checkNames=checkNames.substring(0,checkNames.length()-1);
//		}
		Map map=new HashMap();//上报的时候找出实时的计划巡查项，并保存
		map.put("checkItemId", supTas2.getCheckItemId());
		list3=supTasService.getcheckList(map);
		String planCheckNames="";
		for(Map mm:list3){
			planCheckNames+=mm.get("checkName").toString()+",";
		}
		if(!planCheckNames.equals("")){
		planCheckNames=planCheckNames.substring(0,planCheckNames.length()-1);
		}
		supTas2.setPlanCheckName(planCheckNames);
//		supTas2.setActualCheckName(checkNames);
		supTas2.setCheckTime(supTas.getCheckTime());
		supTas2.setCheckTimeEnd(supTas.getCheckTimeEnd());
		//supTas2.setCheckInstrumentNum(supTas.getCheckInstrumentNum());
		//supTas2.setLinkId(supTas.getLinkId());
		supTas2.setTaskState("已完成");
		supTas2.setRemark(supTas.getRemark());
		supTas2.setCheckRecord(supTas.getCheckRecord());
		supTasService.update(supTas2);
		if(null!=supTas.getPlanId()){
			supPla=supPlaService.getById(supTas.getPlanId());
			supPla.setIfywc("1");
			supPlaService.update(supPla);
		}
		for(SupTasResult r:supTasResultlist){
			r.setTaskId(supTas2.getId());
			r.setDelFlag(0);
			r.setCheckUserId(this.getLoginUserId());
			if(r.getId() != null && !"".equals(r.getId()))
			{
				supPlaService.updateSupTasResult(r);
			}
			else
			{
				supPlaService.saveSupTasResult(r);
			}
		}
		
		return RELOAD;
	}
	
	
	public String supTasXcjc(){
		if(supTas.getId() != null && !"".equals(supTas.getId()))
		{
			supTas = supTasService.getById(supTas.getId());
			//查找对应现场检查记录
			Map m = new HashMap();
			m.put("caseId", supTas.getId());
			m.put("instrumentType", "9");
			List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
			if(list.size() != 0)
			{
				m.put("relatedId", list.get(0).getId());
				siteCheckRecord = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
			}
			else
			{
				siteCheckRecord.setStartTime(supTas.getCheckTime());
				siteCheckRecord.setEndTime(supTas.getCheckTimeEnd());
				siteCheckRecord.setCheckPersonName(supTas.getCheckUsername()+"," + supTas.getXbUserName());
				siteCheckRecord.setCheckPerson(supTas.getCheckUserid()+"," + supTas.getXbUserId());
				siteCheckRecord.setCheckCondition(supTas.getCheckRecord());
			}
		}
		
		userId = this.getLoginUser().getId();
		return EDIT;
	}
	
	public String supTasXcjcSave() throws IOException
	{
		supTas = supTasService.getById(supTas.getId());
		
		SimpleDateFormat sdff =  new SimpleDateFormat("yyyyMMdd");
		String fileName = supTas.getCompanyName()+sdff.format(supTas.getCheckTime())+"现场检查记录.docx";
		
		supTas.setFileName(fileName);
		supTas.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
		supTas.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
		
		supTasService.update(supTas);
		if(siteCheckRecord.getId() != null && !"".equals(siteCheckRecord.getId()))
		{
			siteCheckRecord.setDelFlag(0);
			siteCheckRecordService.update(siteCheckRecord);
		}
		else
		{
			InstrumentsInfo instrumentsInfo = new InstrumentsInfo();
			instrumentsInfo.setCaseId(supTas.getId());
			instrumentsInfo.setDelFlag(0);
			instrumentsInfo.setDeptId(this.getLoginUserDepartment().getId());
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setFileName(fileName);
			instrumentsInfo.setCaseName("监督检查现场检查记录");
			instrumentsInfo.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
			instrumentsInfo.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
			instrumentsInfo.setIfCheck("7");
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setInstrumentType("9");
			instrumentsInfo.setJzCheck("0");
			instrumentsInfo.setNeedCheck("0");
			instrumentsInfo.setTime(new Date());
			instrumentsInfo.setCompanyName(supTas.getCompanyName());
			Map m = new HashMap();
			m.put("codeName", "文书类型");
			m.put("itemValue", "9");
			String fileNames = codeService.findCodeValueByMap(m).getItemText();
			SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyyMMdd");
			String instrumentsName = fileNames + sdf2.format(instrumentsInfo.getTime());
			instrumentsInfo.setInstrumentName(instrumentsName);
			instrumentsInfoService.save(instrumentsInfo);
			
			siteCheckRecord.setRelatedId(instrumentsInfo.getId());
			siteCheckRecord.setCreateUserID(supTas.getCreateUserID());
			siteCheckRecord.setDelFlag(0);
			siteCheckRecordService.save(siteCheckRecord);
		}
			
			//生成文书	
			FileDocUtil fileDocUtil = new FileDocUtil();
			Map<String, Object> map=new HashMap<String, Object>();
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			if(supTas.getYearPlanId() != null && !"".equals(supTas.getYearPlanId()))
			{
				supPla = supPlaService.getById(supTas.getYearPlanId());
				if(supPla.getAddType().equals("1"))
				{
					CompanyInfoPlan companyInfoPlan = companyInfoPlanService.getById(supTas.getCompanyId());
					map.put("companyName", NullToString(companyInfoPlan.getCompanyName()));
					map.put("companyAddress", NullToString(companyInfoPlan.getAddress()));
					map.put("chargePersonZw", "主要负责人");
					map.put("chargePersonTel", NullToString(companyInfoPlan.getChargerPhone()));
					map.put("chargePerson", NullToString(companyInfoPlan.getCharger()));
				}
				else
				{
					EntBaseInfo entBaseInfo = entBaseInfoService.getById(supTas.getCompanyId());
					map.put("companyName", NullToString(entBaseInfo.getEnterpriseName()));
					map.put("companyAddress", NullToString(entBaseInfo.getEnterpriseAddress()));
					map.put("chargePersonZw", NullToString(entBaseInfo.getEnterpriseLegalZw()));
					map.put("chargePersonTel", NullToString(entBaseInfo.getEnterpriseLegalPhone()));
					map.put("chargePerson", NullToString(entBaseInfo.getEnterpriseLegalName()));
				}
			}
			else
			{
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(supTas.getCompanyId());
				map.put("companyName", NullToString(entBaseInfo.getEnterpriseName()));
				map.put("companyAddress", NullToString(entBaseInfo.getEnterpriseAddress()));
				map.put("chargePersonZw", NullToString(entBaseInfo.getEnterpriseLegalZw()));
				map.put("chargePersonTel", NullToString(entBaseInfo.getEnterpriseLegalPhone()));
				map.put("chargePerson", NullToString(entBaseInfo.getEnterpriseLegalName()));
			}
			map.put("checkAddress", NullToString(siteCheckRecord.getCheckAddress()));
			map.put("startTime", "");
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			if(siteCheckRecord.getStartTime() != null)
			{
				String[] day1 = sdf.format(siteCheckRecord.getStartTime()).split("-");
				map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日" + day1[3] + "时" + day1[4] + "分");
			}
			map.put("endTime", "");
			if(siteCheckRecord.getEndTime() != null)
			{
				String[] day2 = sdf.format(siteCheckRecord.getEndTime()).split("-");
				map.put("endTime", day2[2] + "日" + day2[3] + "时" + day2[4] + "分");
			}
			map.put("time", changeTimeToZw(supTas.getCheckTimeEnd()));
			map.put("zfry1", "");
			map.put("zfry2", "");
			map.put("zfry1Zj", "");
			map.put("zfry2Zj", "");
			String[] checkPersons = siteCheckRecord.getCheckPerson().split(",");
			if(checkPersons.length >= 1)
			{
				User uu = userService.findUserById(checkPersons[0]);
				map.put("zfry1", NullToString(uu.getDisplayName()));
				map.put("zfry1Zj", NullToString(uu.getZfzh()));
			}
			if(checkPersons.length >= 2)
			{
				User uu = userService.findUserById(checkPersons[1]);
				map.put("zfry2", NullToString(uu.getDisplayName()));
				map.put("zfry2Zj", NullToString(uu.getZfzh()));
			}
			String[] sgyh = siteCheckRecord.getCheckCondition().split("\r\n");
			List<Map<String, Object>> sgyhList = new ArrayList<Map<String, Object>>();
			for(String s:sgyh)
			{
				Map<String, Object> mm = new HashMap<String, Object>();
				if(s != null && !"".equals(s))
				{
					mm.put("sgyh", s);
					sgyhList.add(mm);
				}
			}
			map.put("xccssgyhList", sgyhList);
			fileDocUtil.createDocFile(root+"现场检查记录.docx", fileName, root+"../../virtualdir/file/监督检查现场检查记录", map);
		
		return RELOAD;
	}
	
	public void exportXcjc()
	{
		try
  		{
			supTas = supTasService.getById(supTas.getId());
			String fileName = supTas.getFileName();
  			URL url = new URL(supTas.getNwurl()+"/file/"+URLEncoder.encode("监督检查现场检查记录", "utf-8")+"/"+URLEncoder.encode(fileName, "utf-8")); 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
			InputStream in = conn.getInputStream();

	        String browName = new String();
	        browName = URLEncoder.encode(fileName, "UTF-8");
	        String clientInfo = getRequest().getHeader("User-agent");
	        if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
	          if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
	            browName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
	        }
	        Struts2Util.getResponse()
	          .addHeader(
	          "Content-Disposition", 
	          "attachment;filename=" + 
	          browName);
	        OutputStream out = Struts2Util.getResponse().getOutputStream();
        try {
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) != -1)
          {
            out.write(buf, 0, len);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          in.close();
          out.close();
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
	}
	
	
	/**
	 * 判断检查项类型是否有检查项
	 */
	public void check(){
	    Map map=new HashMap();
	    map.put("checkItemId", supTas.getCheckItemId());
	    checklist=supTasService.getcheckList(map);
		try{
		if(checklist.size()!=0){      
			this.getResponse().getWriter().println("{\"result\":true}");
		}else{
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	public String changeTimeToZw(Date d)
	{
		SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy-MM-dd");
		String time = "";
		if(d != null)
		{
			String day[] = sdfFormat.format(d).split("-");
			time = day[0] + "年" + day[1] + "月" + day[2] + "日";
		}
		return time;
	}
	
	public String NullToString(String object)
	{
		String s = "";
		if(object != null && !"null".equals(object))
		{
			s = object;
		}
		return s;
	}
	
	public String jdjcrw(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A11")) 
			{
				roleName = "11";//安监中队队长
				break;
			}if(ur.getRole().getRoleCode().equals("A09")) 
			{
				roleName = "09";//监察大队队长
				break;
			}
		}
			
		return SUCCESS;
	}
		
		public void zwtList() throws Exception{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(pagination==null)
			    pagination = new Pagination(this.getRequest()); 
			if(null != supTas){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != supTas.getAreaId()) && (0 < supTas.getAreaId().trim().length())){
					paraMap.put("areaId",  supTas.getAreaId().trim() );
				}

				if ((null != supTas.getCompanyName()) && (0 < supTas.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + supTas.getCompanyName().trim() + "%");
				}

				if ((null != supTas.getTaskNum()) && (0 < supTas.getTaskNum().trim().length())){
					paraMap.put("taskNum", "%" + supTas.getTaskNum().trim() + "%");
				}

				if (null != queryCheckTimeStart){
					paraMap.put("startCheckTime", queryCheckTimeStart);
				}

				if (null != queryCheckTimeEnd){
					paraMap.put("endCheckTime", queryCheckTimeEnd);
				}
				if ((null != supTas.getCheckUsername()) && (0 < supTas.getCheckUsername().trim().length())){
					paraMap.put("checkUsername", "%" + supTas.getCheckUsername().trim() + "%");
				}

				if ((null != supTas.getTaskState()) && (0 < supTas.getTaskState().trim().length())&&(!supTas.getTaskState().equals("请选择"))){
					paraMap.put("taskState", supTas.getTaskState().trim());
				}

				if ((null != supTas.getTaskType()) && (0 < supTas.getTaskType().trim().length())&&(!supTas.getTaskType().equals("请选择"))){
					paraMap.put("taskType", supTas.getTaskType().trim());
				}
				if ((null != supTas.getPlanId()) && (0 < supTas.getPlanId().trim().length())){
					paraMap.put("planId",  supTas.getPlanId().trim());
				}
				List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
				for(UserRight r:urs){
					if("A12".equals(r.getRole().getRoleCode())||"A13".equals(r.getRole().getRoleCode())){//中队队员和检查机构看见检查id是自己的任务
						paraMap.put("checkUserid",this.getLoginUser().getId());
						break;
					} if("A11".equals(r.getRole().getRoleCode())){//中队长看见检查部门是自己的任务
						paraMap.put("checkDeptId",this.getLoginUser().getDeptCode());
						break;
					} if("A07".equals(r.getRole().getRoleCode())||"A08".equals(r.getRole().getRoleCode())){//职业卫生处看见的是职业卫生的任务
						paraMap.put("checkDeptId","00500%");
						break;
					}
					if("A23".equals(r.getRole().getRoleCode())){//企业查看自己企业相关任务
						Map map = new HashMap();
						map.put("loginId", this.getLoginUser().getLoginId());
						EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
						paraMap.put("companyName","%" + enBaseInfo.getEnterpriseName()+"%");
						break;
					}
				}

			}
			
			if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称".equals(searchLike)){
				paraMap.put("companyName", "%" + searchLike.trim() + "%");
			}
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|areaName|companyName|taskNum|checkTime|checkUsername|taskState|taskType|stime|ftime|createUserID|checkUserid|xbUserId|xbUserName|";
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
			pagination = supTasService.findByPage(pagination, paraMap);
				
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
		
		
		
		public void zwtLists() throws Exception{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(pagination==null)
			    pagination = new Pagination(this.getRequest()); 
			if(null != supTas){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != supTas.getAreaId()) && (0 < supTas.getAreaId().trim().length())){
					paraMap.put("areaId", supTas.getAreaId().trim() );
				}

				if ((null != supTas.getCompanyName()) && (0 < supTas.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + supTas.getCompanyName().trim() + "%");
				}

				if ((null != supTas.getTaskNum()) && (0 < supTas.getTaskNum().trim().length())){
					paraMap.put("taskNum", "%" + supTas.getTaskNum().trim() + "%");
				}

				if (null != queryCheckTimeStart){
					paraMap.put("startCheckTime", queryCheckTimeStart);
				}

				if (null != queryCheckTimeEnd){
					paraMap.put("endCheckTime", queryCheckTimeEnd);
				}
				if ((null != supTas.getCheckUsername()) && (0 < supTas.getCheckUsername().trim().length())){
					paraMap.put("checkUsername", "%" + supTas.getCheckUsername().trim() + "%");
				}
				if ((null != supTas.getTaskType()) && (0 < supTas.getTaskType().trim().length())&&(!supTas.getTaskType().equals("请选择"))){
					paraMap.put("taskType", supTas.getTaskType().trim());
				}
				if ((null != supTas.getPlanId()) && (0 < supTas.getPlanId().trim().length())){
					paraMap.put("planId",  supTas.getPlanId().trim());
				}
				List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
				for(UserRight r:urs){
					if("A12".equals(r.getRole().getRoleCode())){
						paraMap.put("checkUserid",this.getLoginUser().getId());
					}if("A11".equals(r.getRole().getRoleCode())){
						paraMap.put("checkDeptId","%"+this.getLoginUserDepartment().getDeptCode()+"%");
					}if("A07".equals(r.getRole().getRoleCode())||"A08".equals(r.getRole().getRoleCode())){
						paraMap.put("checkDeptId","005%");
					}
					if("A23".equals(r.getRole().getRoleCode())){//企业查看自己企业相关任务
						Map map = new HashMap();
						map.put("loginId", this.getLoginUser().getLoginId());
						EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
						paraMap.put("companyName","%" + enBaseInfo.getEnterpriseName()+"%");
					}
					break;
				}
				paraMap.put("taskState","已完成");
				
			}
			if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称".equals(searchLike)){
				paraMap.put("companyName", "%" + searchLike.trim() + "%");
			}
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|areaName|companyName|taskNum|checkTime|checkUsername|taskState|taskType|stime|ftime|createUserID|checkUserid|xbUserId|xbUserName|";
			
			
			
			pagination.setPageNumber(pageNo);
			pagination.setPageSize(pageSize);
			pagination = supTasService.findByPage(pagination, paraMap);
				
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

	public SupTas getSupTas(){
		return this.supTas;
	}

	public void setSupTas(SupTas supTas){
		this.supTas = supTas;
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

	public void setQueryCheckTimeEnd(Date queryCheckTimeEnd){
		this.queryCheckTimeEnd = queryCheckTimeEnd;
	}

	public List<Map> getPersons() {
		return persons;
	}

	public void setPersons(List<Map> persons) {
		this.persons = persons;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public List<Map<String, String>> getList2() {
		return list2;
	}

	public void setList2(List<Map<String, String>> list2) {
		this.list2 = list2;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public List<Map> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<Map> checklist) {
		this.checklist = checklist;
	}

	public List<Map> getList3() {
		return list3;
	}

	public void setList3(List<Map> list3) {
		this.list3 = list3;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public SupPla getSupPla() {
		return supPla;
	}

	public void setSupPla(SupPla supPla) {
		this.supPla = supPla;
	}

	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}

	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}

	public List<SupTasResult> getSupTasResultlist() {
		return supTasResultlist;
	}

	public void setSupTasResultlist(List<SupTasResult> supTasResultlist) {
		this.supTasResultlist = supTasResultlist;
	}

	public SiteCheckRecord getSiteCheckRecord() {
		return siteCheckRecord;
	}

	public void setSiteCheckRecord(SiteCheckRecord siteCheckRecord) {
		this.siteCheckRecord = siteCheckRecord;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
