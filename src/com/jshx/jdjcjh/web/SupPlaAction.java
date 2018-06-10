package com.jshx.jdjcjh.web;

import java.io.File;
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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jpush.api.JPushClient;

import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.service.CompanyInfoPlanService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.http.service.HttpService;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.entity.SupPlaXccs;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.service.SupTasService;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.service.ComGriManService;
import com.jshx.xcxlxgl.entity.PatTypMan;
import com.jshx.xcxlxgl.service.PatTypManService;

public class SupPlaAction extends BaseAction
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
	private SupPlaXccs supPlaXccs = new SupPlaXccs();
	/**
	 * 业务类
	 */
	@Autowired
	private SupPlaService supPlaService;
	
	@Autowired
	private SupTasService supTasService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private PatTypManService patTypManService;
	@Autowired
	private CompanyInfoPlanService companyInfoPlanService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private UserService userService;
	@Autowired
	private ComGriManService comGriManService;
	

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
	
	private List<Map> depts = new ArrayList<Map>();//部门列表
	
	private List<Map> persons = new ArrayList<Map>();//人员列表

	private List<Map> planlist=new ArrayList<Map>();//计划名称信息列表
	
	private List<Map> checklist = new ArrayList<Map>();//检查项列表
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private String mark;//部门标识
	
	private String planId;
	
	private String roleName;
	
	private Date today;
	
	private String checkDeptId;
	
	private String companyIds="";
	
	private String companyNames="";
	
	private String checkUserId;
	
	private String companyName="";
	private String companyType="";
	private File userFile;
	private String message;
	private String deptCode;
	private List<CompanyInfoPlan> cipList=new ArrayList<CompanyInfoPlan>();
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A09")){
				roleName = "09";//可以添加计划和导入年计划的角色为监察大队队长
				break;
			}else if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A07")||ur.getRole().getRoleCode().equals("A08")||ur.getRole().getRoleCode().equals("A11")) 
			{
				roleName = "1";//可以添加计划的角色为中队长、综合处处长、危化处处长、职业健康处人员
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
		    
		if(null != supPla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supPla.getPlanName()) && (0 < supPla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + supPla.getPlanName().trim() + "%");
			}

			if ((null != supPla.getPlanType()) && (0 < supPla.getPlanType().trim().length())){
				paraMap.put("planType", supPla.getPlanType().trim());
			}

			if ((null != supPla.getCheckItemType()) && (0 < supPla.getCheckItemType().trim().length())){
				paraMap.put("checkItemType", supPla.getCheckItemType().trim());
			}

			if ((null != supPla.getCheckCompanyName()) && (0 < supPla.getCheckCompanyName().trim().length())){
				paraMap.put("checkCompanyName", "%" + supPla.getCheckCompanyName().trim() + "%");
			}
			List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight r:urs){//中队长登录查询该中队的年计划和自己中队自己制定的计划
				if("A11".equals(r.getRole().getRoleCode())){
					paraMap.put("createUserId",this.getLoginUser().getId());
					break;
				} if("A08".equals(r.getRole().getRoleCode())){
					paraMap.put("planType","day");
					break;
				}
				
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("planType","402880084196a4a30141973ad5cd01b5");
		codeMap.put("checkItemType","402880084196a4a301419737ca0401a9");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|checkCompanyName|planName|planType|checkItemType|createUserID|ifywc|";
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
			pagination = supPlaService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		Map map = new HashMap();
		List<Object[]> list = pagination.getList();//判断计划是否有已完成任务
		for(Object o:list){
			SupPla sup=(SupPla)o;
			map.put("planId", sup.getId());
			map.put("state", "已完成");
			List<Map> tasklist=supPlaService.findTask(map);
			if(tasklist.size()!=0){
				sup.setIfywc("1");
			}
		}
		*/
		convObjectToJson(pagination, config);
		
		
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != supPla)&&(null != supPla.getId()))
			supPla = supPlaService.getById(supPla.getId());
		if(supPla.getPlanType().endsWith("year")){//查看年计划
			mark = "0";
		}else if(supPla.getPlanType().endsWith("week")){//查看周计划
			mark = "1";
		}else if(supPla.getPlanType().endsWith("day")){//查看职业卫生监督监测计划
			mark = "2";
		}else if(supPla.getPlanType().endsWith("interim")){
			mark = "4";//查看临时计划
		}
		if("1".equals(supPla.getAddType())){
			if(supPla.getPlanType().equals("week")){////周计划
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("checkCompanyIds", supPla.getCheckCompanyId());
				cipList=companyInfoPlanService.findCompanyInfoPlan(paraMap);
			}
			if(supPla.getPlanType().equals("year")){////年计划
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("planbId", supPla.getId());
				cipList=companyInfoPlanService.findCompanyInfoPlan(paraMap);
			}
			for(CompanyInfoPlan cip:cipList){
				String cType=this.getCompanyTypeByTypeDetail(cip.getTypeDetail());
				cip.setTypeDetail(cType);
				
			}
		}
		
		String finishState=calFinishState(supPla);
		supPla.setFinishState(finishState);
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		Date date=new Date();
		today=date;
		if((null != supPla)&&(null != supPla.getId()))
		{
			supPla = supPlaService.getById(supPla.getId());
			if(supPla.getPlanType().endsWith("year")){//查看年计划
				mark = "0";
			}else if(supPla.getPlanType().endsWith("week")){//查看周计划
				mark = "1";
			}else if(supPla.getPlanType().endsWith("day")){//查看职业卫生监督监测计划
				mark = "2";
			}else if(supPla.getPlanType().endsWith("interim")){
				mark = "0";//查看临时计划
			}
		}
		else
		{
			List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight ur:list)
			{
				if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A07")||ur.getRole().getRoleCode().equals("A09")) //3大处处长和监察大队队长添加年计划
				{
	                  mark = "0";
					break;
				}else if(ur.getRole().getRoleCode().equals("A11")){ //中队长添加周计划
					mark = "1";
					break;
				}else if(ur.getRole().getRoleCode().equals("A08")){//职业健康处办事员添加职业健康计划
					mark = "2";
					break;
				}
			}
		}
		Map map = new HashMap();
		deptCode = this.getLoginUserDepartment().getDeptCode();
		if(mark.equals("0"))
		{
			map.put("code","002001005%");
			depts = supPlaService.getDeptsByMap(map);//获取部门
		}
		else if(mark.equals("1"))
		{
			map.put("code",deptCode+"%");
			persons= supPlaService.getPersonsByMap(map);//获取人员信息
			Map map2 = new HashMap();
			map2.put("checkDeptId", deptCode);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			map2.put("todaytime",sdf.format(date));
			planlist=supPlaService.getPlanNameByMap(map2);//获取计划名称
		}
		else if(mark.equals("2"))
		{
			map.put("code","00500%");
			depts = supPlaService.getDeptsByMap(map);//获取部门
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if(supPla.getPlanType().equals("day")){
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("deptcode", supPla.getCheckDeptId());
			paraMap.put("sqlId", "queryCheckByMap");
			List<Map<String, Object>>list =supTasService.findCheckListByMap(paraMap); 
			supPla.setCheckUserId(list.get(0).get("checkUserid").toString());
			supPla.setCheckUserName(list.get(0).get("checkUserName").toString());
			}

	if ("add".equalsIgnoreCase(this.flag)){
			supPla.setDeptId(this.getLoginUserDepartmentId());
			supPla.setDelFlag(0);
			supPla.setIfywc("0");
			supPla.setAddType("0");
			if(supPla.getPlanType().equals("week")){//周计划的名称自动生成
				SupPla yearSp=supPlaService.getById(supPla.getPlanId());
				supPla.setAddType(yearSp.getAddType());
				//部门名称
				String depyName=this.getLoginUserDepartment().getDeptName();
				//第几周
				Calendar cal=Calendar.getInstance(); 
				cal.setTime(supPla.getPlanStartTime());
				int weekNum=cal.get(Calendar.WEEK_OF_YEAR);
				String n=depyName+"第"+weekNum+"周计划";
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("planName","%"+ n+"%");
				paraMap.put("planType", "week");
				List<SupPla> supPlaList=supPlaService.findSupPla(paraMap);
				if(supPlaList.size()==0){
					supPla.setPlanName(n);
				}else if(supPlaList.size()==1){
					SupPla sp=supPlaService.getById(supPlaList.get(0).getId());
					sp.setPlanName(n+"-1");
					supPlaService.update(sp);
					supPla.setPlanName(n+"-2");
				}else{
					int num=supPlaList.size()+1;
					supPla.setPlanName(n+"-"+num);
				}
				
			}
			supPlaService.save(supPla);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
			if(supPla.getPlanType().equals("week")){////周计划
			String checkCompanyId= supPla.getCheckCompanyId();
			String ids[]=checkCompanyId.split(",");
			String checkCompanyName=supPla.getCheckCompanyName();
			String names[]=checkCompanyName.split(",");
			int j=1;//初始化生成任务编号后加的数字
			for(int i=0;i<ids.length;i++){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startTime=sf.format(supPla.getPlanStartTime());
				String endTime=sf.format(supPla.getPlanEndTime());
			
		     
		            SupTas supTas = new SupTas();
		            supTas.setStime(supPla.getPlanStartTime());
		            supTas.setFtime(supPla.getPlanEndTime());
		            supTas.setCompanyId(ids[i]);
		            supTas.setCompanyName(names[i]);
		            supTas.setCheckUserid(supPla.getCheckUserId());
		            supTas.setCheckUsername(supPla.getCheckUserName());
		            supTas.setCheckItemId(supPla.getCheckItemType());
		            supTas.setXbUserId(supPla.getXbUserId());
		            supTas.setXbUserName(supPla.getXbUserName());
		            if(supPla.getAddType().equals("1"))//导入
		            {
		            	CompanyInfoPlan companyInfoPlan = companyInfoPlanService.getById(supTas.getCompanyId());
		            	supTas.setAreaId(companyInfoPlan.getAreaId());
						supTas.setAreaName(companyInfoPlan.getArea());
		            }
		            else//添加
		            {
		            	entBaseInfo = entBaseInfoService.getById(ids[i].trim());
		            	supTas.setAreaId(entBaseInfo.getGrid());
		            	supTas.setAreaName(entBaseInfo.getGridName());
		            }
		            String name="";
		            for(String citId:supPla.getCheckItemType().split(",")){
		            	PatTypMan patTypMan = new PatTypMan();
			            patTypMan=patTypManService.getById(citId.trim());
			            name+=patTypMan.getPatrolTypeName()+",";
		            }
		            if(name.endsWith(",")){
		            	name=name.substring(0, name.length()-1);
		            }
		            supTas.setCheckItemName(name);
		            supTas.setTaskName(supPla.getPlanName()+startTime.replaceAll("-", ""));
		            supTas.setDelFlag(0);
                    supTas.setCheckDeptId(this.getLoginUserDepartment().getDeptCode());
		            supTas.setTaskNum(sdf2.format(d)+j);
					supTas.setTaskType("计划任务");
					supTas.setTaskState("未完成");
					supTas.setPlanId(supPla.getId());
					supTas.setYearPlanId(supPla.getPlanId());
					supTasService.save(supTas);
					pushInfo(supTas.getId(),supTas.getCheckUserid(),supTas.getTaskName());
					j++;
				
				
			   }
			}else if(supPla.getPlanType().equals("day")){
				SupTas supTas = new SupTas();
				supTas.setCompanyId(supPla.getCheckCompanyId());
				supTas.setCompanyName(supPla.getCheckCompanyName());
				Map map = new HashMap();
				map.put("ids", supPla.getCheckCompanyId());
				EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				supTas.setAreaId(enBaseInfo.getGrid());
				supTas.setAreaName(enBaseInfo.getGridName());
				supTas.setDelFlag(0);
				supTas.setTaskNum(sdf2.format(d));
				supTas.setTaskType("计划任务");
				supTas.setTaskState("未完成");
                supTas.setCheckItemId(supPla.getCheckItemType());
				supTas.setCheckDeptId(supPla.getCheckDeptId());
				String name="";
	            for(String citId:supPla.getCheckItemType().split(",")){
	            	PatTypMan patTypMan = new PatTypMan();
		            patTypMan=patTypManService.getById(citId.trim());
		            name+=patTypMan.getPatrolTypeName()+",";
	            }
	            if(name.endsWith(",")){
	            	name=name.substring(0, name.length()-1);
	            }
	            supTas.setCheckItemName(name);
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("deptcode", supPla.getCheckDeptId());
				paraMap.put("sqlId", "queryCheckByMap");
				List<Map<String, Object>>list =supTasService.findCheckListByMap(paraMap); 
				supTas.setCheckUserid(list.get(0).get("checkUserid").toString());
				supTas.setCheckUsername(list.get(0).get("checkUserName").toString());
				supTas.setTaskName(supPla.getPlanName());
				supTas.setStime(supPla.getPlanStartTime());
				supTas.setFtime(supPla.getPlanEndTime());
				supTas.setPlanId(supPla.getId());
				supTasService.save(supTas);
				pushInfo(supTas.getId(),supTas.getCheckUserid(),supTas.getTaskName());
			}
		}else{
			supPlaService.update(supPla);
			Map maps=new HashMap();
			maps.put("planId", supPla.getId());
			maps.put("state", "未完成");
			List<Map> tasklist=supPlaService.findTask(maps);//查找该计划生成的任务
			String taskIds="";
			for(Map tas:tasklist){
				String id=tas.get("id").toString();
				taskIds+=id+"|";
			}
			supTasService.deleteWithFlag(taskIds);//更新的时候删除原本计划的任务
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
			if(supPla.getPlanType().equals("week")){////周计划
			String checkCompanyId= supPla.getCheckCompanyId();
			String ids[]=checkCompanyId.split(",");
			String checkCompanyName=supPla.getCheckCompanyName();
			String names[]=checkCompanyName.split(",");
			int j=1;//初始化生成任务编号后加的数字
			for(int i=0;i<ids.length;i++){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startTime=sf.format(supPla.getPlanStartTime());
				String endTime=sf.format(supPla.getPlanEndTime());
			
				SupTas supTas = new SupTas();
	            supTas.setStime(supPla.getPlanStartTime());
	            supTas.setFtime(supPla.getPlanEndTime());
		            supTas.setCompanyId(ids[i]);
		            supTas.setCompanyName(names[i]);
		            supTas.setCheckUserid(supPla.getCheckUserId());
		            supTas.setCheckUsername(supPla.getCheckUserName());
		            supTas.setXbUserId(supPla.getXbUserId());
		            supTas.setXbUserName(supPla.getXbUserName());
		            supTas.setCheckItemId(supPla.getCheckItemType());
		            if(supPla.getAddType().equals("1"))//导入
		            {
		            	CompanyInfoPlan companyInfoPlan = companyInfoPlanService.getById(supTas.getCompanyId());
		            	supTas.setAreaId(companyInfoPlan.getAreaId());
						supTas.setAreaName(companyInfoPlan.getArea());
		            }
		            else//添加
		            {
		            	entBaseInfo = entBaseInfoService.getById(ids[i].trim());
		            	supTas.setAreaId(entBaseInfo.getGrid());
		            	supTas.setAreaName(entBaseInfo.getGridName());
		            }
		            String name="";
		            for(String citId:supPla.getCheckItemType().split(",")){
		            	PatTypMan patTypMan = new PatTypMan();
			            patTypMan=patTypManService.getById(citId.trim());
			            name+=patTypMan.getPatrolTypeName()+",";
		            }
		            if(name.endsWith(",")){
		            	name=name.substring(0, name.length()-1);
		            }
		            supTas.setCheckItemName(name);
		            supTas.setTaskName(supPla.getPlanName()+startTime.replaceAll("-", ""));
		            supTas.setDelFlag(0);
                    supTas.setCheckDeptId(this.getLoginUserDepartment().getDeptCode());
		            supTas.setTaskNum(sdf2.format(d)+j);
					supTas.setTaskType("计划任务");
					supTas.setTaskState("未完成");
					supTas.setPlanId(supPla.getId());
					supTas.setYearPlanId(supPla.getPlanId());
					supTasService.save(supTas);
					pushInfo(supTas.getId(),supTas.getCheckUserid(),supTas.getTaskName());
					j++;
				
				
			   }
			}else if(supPla.getPlanType().equals("day")){
				SupTas supTas = new SupTas();
				supTas.setCompanyId(supPla.getCheckCompanyId());
				supTas.setCompanyName(supPla.getCheckCompanyName());
				Map map = new HashMap();
				map.put("ids", supPla.getCheckCompanyId());
				EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				supTas.setAreaId(enBaseInfo.getGrid());
				supTas.setAreaName(enBaseInfo.getGridName());
				supTas.setDelFlag(0);
				supTas.setTaskNum(sdf2.format(d));
				supTas.setTaskType("计划任务");
				supTas.setTaskState("未完成");
                supTas.setCheckItemId(supPla.getCheckItemType());
				supTas.setCheckDeptId(supPla.getCheckDeptId());
				String name="";
	            for(String citId:supPla.getCheckItemType().split(",")){
	            	PatTypMan patTypMan = new PatTypMan();
		            patTypMan=patTypManService.getById(citId.trim());
		            name+=patTypMan.getPatrolTypeName()+",";
	            }
	            if(name.endsWith(",")){
	            	name=name.substring(0, name.length()-1);
	            }
	            supTas.setCheckItemName(name);
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("deptcode", supPla.getCheckDeptId());
				paraMap.put("sqlId", "queryCheckByMap");
				List<Map<String, Object>>list =supTasService.findCheckListByMap(paraMap); 
				supTas.setCheckUserid(list.get(0).get("checkUserid").toString());
				supTas.setCheckUsername(list.get(0).get("checkUserName").toString());
				supTas.setTaskName(supPla.getPlanName());
				supTas.setStime(supPla.getPlanStartTime());
				supTas.setFtime(supPla.getPlanEndTime());
				supTas.setPlanId(supPla.getId());
				supTasService.save(supTas);
				pushInfo(supTas.getId(),supTas.getCheckUserid(),supTas.getTaskName());
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
		if (null != supPla)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到supPla中去
				
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
			supPlaService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public  List testDate(String startTime,String endTime){
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
	/**
	 * 跳转到查询关联任务信息
	 */
	public String supPlaLink(){
		planId=supPla.getId();
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
	 * 判断检查项类型是否有检查项
	 */
	public void checks(){
	    Map map=new HashMap();
	    map.put("checkItemId", supPla.getCheckItemType());
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
	
	/**
  	 * 跳转年计划企业用户列表
  	 */
  	public String selectCompanys()
  	{
  		return SUCCESS;
  	}
	
  	/**
  	 * 查询年计划企业列表
  	 */
  	public void getDepartUser() throws IOException
  	{
  		Map map = new HashMap();
		if ((null != entBaseInfo.getEnterpriseNature()) && (0 < entBaseInfo.getEnterpriseNature().trim().length())){
			map.put("enterpriseNature",entBaseInfo.getEnterpriseNature() );
  		}
  		if ((null != entBaseInfo.getEnterpriseType()) && (0 < entBaseInfo.getEnterpriseType().trim().length())){
  			map.put("enterpriseType",entBaseInfo.getEnterpriseType() );
  		}
  		if ((null != entBaseInfo.getEnterpriseScale()) && (0 < entBaseInfo.getEnterpriseScale().trim().length())){
  			map.put("enterpriseScale",entBaseInfo.getEnterpriseScale() );
  		}
  		if ((null != entBaseInfo.getEnterpriseName()) && (0 < entBaseInfo.getEnterpriseName().trim().length())){
  			map.put("enterpriseName","%" + entBaseInfo.getEnterpriseName()+"%" );
  		}
  		JSONArray jsonArry = new JSONArray();
  		List<ComGriMan> wgListAll=supPlaService.findWglistAll(null);
  		for(ComGriMan ww:wgListAll){//遍历网格
  			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", ww.getId());
	  		jsonObject.put("pId","-1");
	  		jsonObject.put("name", ww.getGridName());
	  		jsonObject.put("flag", "0");
	  		jsonObject.put("open", true);
	  		map.put("grid", ww.getId());
	  		List<EntBaseInfo> list=supPlaService.findCompanyList(map);
	  		
	  		boolean ifqx = false;
	  		if(list!=null&&list.size()!=0){//修改的时候判断是否全选
		  		for(EntBaseInfo mm:list){
		  			JSONObject jo = new JSONObject();
		  			jo.put("id", mm.getId());
		  			jo.put("pId",mm.getGrid());
		  			jo.put("name", mm.getEnterpriseName());
		  			jo.put("flag", "1");
		  			jo.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
			  		if(companyIds.contains(mm.getId())){//修改的时候判断是否被选中
			  			jo.put("checked",true);
			  			ifqx = true;
			  		}
			  		jsonArry.add(jo);
		  		}
	  		}
	  		if(ifqx==true){
	  			jsonObject.put("checked",true);
	  		}
	  		
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
	  		jsonArry.add(jsonObject);
  		}
  		
  		JSONObject jsonObject2 = new JSONObject();//添加全选按钮
  		jsonObject2.put("id", "-1");
  		jsonObject2.put("pId","1");
  		jsonObject2.put("open", true);
  			if(companyIds.length()!=0){
  	  			jsonObject2.put("checked",true);
  	  			}
  		
  		jsonObject2.put("name", "全选");
  		jsonObject2.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
  		jsonArry.add(jsonObject2);
		getResponse().getWriter().write(jsonArry.toString());
  	}
  	
  	/**
  	 * 跳转周计划企业用户列表
  	 */
  	public String selectCompanys2()
  	{
  		checkUserId=supPla.getCheckUserId();
  		planId=supPla.getPlanId();
  		String addtype = "0";
  		if(planId != null && !"".equals(planId))
  		{
  			SupPla year =  supPlaService.getById(planId);
  			addtype = year.getAddType();
  		}
  		
  		if("0".equals(addtype)){
  			return "success1";
  		}else{
  			return "success2";
  		}
  	}
	
  	/**
  	 * 查询周计划企业列表
  	 */
  	public void getDepartUser2() throws IOException
  	{
  		
  		try {
			JSONArray jsonArry = new JSONArray();
			checkUserId=supPla.getCheckUserId();
			planId=supPla.getPlanId();
			SupPla supPla2 = new SupPla();
			if(planId != null && !"".equals(planId))
			{
				supPla2=supPlaService.getById(planId);
				ids = supPla2.getCheckCompanyId();
			}
			Map map = new HashMap();
			map.put("checkUserId", checkUserId);
			List<String> wglist=supPlaService.findwglist(map);
			String wgIds="";
			for(String l:wglist){
				wgIds+=l+",";
			}
			if(!wgIds.equals("")){
				wgIds=wgIds.substring(0,wgIds.length()-1);
			}
			Map map2 = new HashMap();
			if(null!=planId && !"".equals(planId)){
			map2.put("planbId", planId);
			}
			if(wglist.size()!=0){
			map2.put("wgIds", "('" + wgIds.trim().replaceAll(" ", "").replaceAll(",", "','") + "')");
			}
			if(ids != null && !"".equals(ids))
			{
				map2.put("ids", "('" + ids.trim().replaceAll(" ", "").replaceAll(",", "','") + "')");
			}
			Date date=new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			String startTime=sf.format(date);
			map2.put("stime", startTime);
			int listSize=0;
			if(supPla2.getAddType() != null && "1".equals(supPla2.getAddType())){
				supPlaXccs = supPlaService.getXccs(null);
				String xccs = supPlaXccs.getXccs();
				String[] cs = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"};
				if(xccs != null && !"".equals(xccs))
				{
					cs = xccs.split(",");
				}
				map2.put("sqlId", "findCompanyList22");
				map2.put("checkUserId", checkUserId);
				List<Map<String,Object>> companyList=httpService.findListDataByMap(map2);
				listSize=companyList.size();
				for(Map ee: companyList){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", ee.get("id").toString());
					jsonObject.put("pId","-1");
					String str =ee.get("level").toString();
					int ss = 0;
					for(int i=0;i<12;i++)
					{
						String s = str.substring(i,i+1);
						if(s.equals("1"))
						{
							if(cs[i] != null && !"".equals(cs[i]))
							{
								ss += Integer.parseInt(cs[i]);
							}
						}
					}
					String cType=this.getCompanyTypeByTypeDetail(str);
					jsonObject.put("name", ee.get("enterpriseName").toString()+"   "+cType+"-需检查："+ ss + "-已检查："+ee.get("count").toString());
					jsonObject.put("flag", "1");
					jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
					if(companyIds.contains(ee.get("id").toString())){
						jsonObject.put("checked",true);
					}
					jsonArry.add(jsonObject);
				}
				
			}else{
				List<Map> companyList=supPlaService.findCompanyList2(map2);
				listSize=companyList.size();
				for(Map ee: companyList){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", ee.get("id").toString());
					jsonObject.put("pId","-1");
					jsonObject.put("name", ee.get("enterpriseName").toString()+"-已分配任务次数："+ee.get("count").toString());
					jsonObject.put("flag", "1");
					jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
					if(companyIds != null && companyIds.contains(ee.get("id").toString())){
						jsonObject.put("checked",true);
					}
					jsonArry.add(jsonObject);
				}
			}

			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("id", "-1");
			jsonObject2.put("pId","1");
			jsonObject2.put("open", true);
				if(companyIds.length()!=0){
					jsonObject2.put("checked",true);
					}
			
			jsonObject2.put("name", "全选");
			jsonObject2.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
			jsonArry.add(jsonObject2);
			getResponse().getWriter().write(jsonArry.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
	/**
  	 * 判断是否某网格的企业全选方法
  	 */
  	public boolean compare(String s1,String s2){
  		boolean a=false;
  		if(!s1.equals("")){
  		String ids1[]=s1.split(",");//S1表示部门ids拼接字符串，S2表示传入的字符串
  		for(int i=0;i<ids1.length;i++){
  			if(s2.contains(ids1[i])){
  				a=true;
  			}else{
  				a=false;
  				break;
  			}
  		}
  		}
  		return a;
  	}
  	
  	/**
  	 * 跳转到导入页面
  	 * fq 2016-4-11
  	 */
  	public String initImportPlan(){
  		return SUCCESS;
  	}
  	
  	/**
  	 * 导入年计划
  	 * fq 2016-4-11
  	 */
  	public String importPlan(){
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(userFile);
			} catch (Exception e) {
				e.printStackTrace();
				message = "导入失败，请使用系统模板！";
			}
			Sheet sheet = workbook.getSheet(0);
			
				int column = sheet.getColumns();
				int row = sheet.getRows();
				List<CompanyInfoPlan> companyInfoPlanList = new ArrayList<CompanyInfoPlan>();
				for (int i = 3; i < row; i++) {
					
					try {
						Cell[] cells = new Cell[column];
						Cell[] cellsTmp = sheet.getRow(i);
						for (int j = 0; j < cellsTmp.length; j++) {
							cells[j] = cellsTmp[j];
						}
						
						if ((cells[1]==null ||cells[1].getContents().equals(""))&&(cells[2]==null ||cells[2].getContents().equals("")) ){
							workbook.close();
							break;
						}
						CompanyInfoPlan cip = new CompanyInfoPlan();
						cip.setDeptId(this.getLoginUserDepartmentId());
						cip.setDelFlag(0);
						
						StringBuffer colError = null;
						//企业名称
						if (cells[1]==null ||cells[1].getContents().equals("")) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：企业名称输入有误，不能为空，请检查!</span><br>");
						}else if (cells[1].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：企业名称长度不能超过127，请检查!</span><br>");
						}else{
							cip.setCompanyName(cells[1].getContents().trim());
						}
						//所属区域
						if (cells[2]==null || cells[2].getContents().equals("")) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：所属区域输入有误，不能为空，请检查!</span><br>");
						}else if (cells[2].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：所属区域长度不能超过127，请检查!</span><br>");
						}else{
							String area = cells[2].getContents().trim();
							cip.setArea(area);
							Map m = new HashMap();
							m.put("gridName", area);
							ComGriMan c = comGriManService.getComGriManByMap(m);
							cip.setAreaId(c.getId());
						}
						
						//地  址
						if (cells[3] != null && cells[3].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：地址长度不能超过127，请检查!</span><br>");
						}else{
							cip.setAddress(cells[3].getContents().trim());
						}
						
						//主要负责人
						if (cells[4] != null && cells[4].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：主要负责人长度不能超过127，请检查!</span><br>");
						}else{
							cip.setCharger(cells[4].getContents().trim());
						}
						
						//主要负责人联系电话
						if (cells[5] != null && cells[5].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：主要负责人联系电话长度不能超过127，请检查!</span><br>");
						}else{
							cip.setChargerPhone(cells[5].getContents().trim());
						}
						
						//联系人
						if (cells[6] != null && cells[6].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：联系人长度不能超过127，请检查!</span><br>");
						}else{
							cip.setContact(cells[6].getContents().trim());
						}
						
						//联系人联系电话
						if (cells[7] != null && cells[7].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：联系人联系电话长度不能超过127，请检查!</span><br>");
						}else{
							cip.setContactPhone(cells[7].getContents().trim());
						}
						
						//邮箱
						if (cells[8] != null && cells[8].getContents().length()>127) {
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：邮箱长度不能超过127，请检查!</span><br>");
						}else{
							cip.setEmail(cells[8].getContents().trim());
						}
						
						//类型typeDetail
						String t="";
						if ((cells[9] != null)&&(cells[10] != null)&&(cells[11] != null)&&(cells[12] != null)&&(cells[13] != null)&&(cells[14] != null)&&(cells[15] != null)&&(cells[16] != null)&&(cells[17] != null)&&(cells[18] != null)&&(cells[19] != null)&&(cells[20] != null)){
							for(int ii=9;ii<21;ii++){
								if("√".equals(cells[ii].getContents().trim())){
									t+="1";
								}else{
									t+="0";
								}
							}
							
						}
						cip.setTypeDetail(t);
						
						if(colError==null){
							companyInfoPlanList.add(cip);
							errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
						}else{
							errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
							errorInfo.append(colError).append("<br>");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				message = errorInfo.toString();
				supPla.setDeptId(this.getLoginUserDepartmentId());
				supPla.setDelFlag(0);
				supPla.setIfywc("0");
				supPla.setAddType("1");
				supPla.setPlanType("year"); 
				supPlaService.save(supPla);
				String companyIds="";
				String companyNames="";
				try {
					for (int i = 0; i < companyInfoPlanList.size(); i++) {
						CompanyInfoPlan companyInfoPlan = companyInfoPlanList.get(i);
						companyInfoPlan.setPlanbId(supPla.getId());
						companyInfoPlanService.save(companyInfoPlan);
						if(i==companyInfoPlanList.size()-1){
							companyIds+=companyInfoPlan.getId();
							companyNames+=companyInfoPlan.getCompanyName();
						}else{
							companyIds+=companyInfoPlan.getId()+",";
							companyNames+=companyInfoPlan.getCompanyName()+",";
						}
						supPla.setCheckCompanyId(companyIds);
						supPla.setCheckCompanyName(companyNames);
						supPlaService.update(supPla);
					}
				} catch (Exception e) {
					workbook.close();
					BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
					message = "导入失败！";
					throw ex;
				}
				workbook.close();
				if(message.equals("") && companyInfoPlanList.size() == 0)
				{
					message = "导入失败，未读取到数据，请使用系统模板！";
				}
			if(message.contains("失败")){
				return ERROR;
			}else{
				message="";
				return SUCCESS;
			}
		
  	}
  	
  	public String initXccsEdit()
  	{
  		supPlaXccs = supPlaService.getXccs(null);
  		if(supPlaXccs.getId() == null || "".equals(supPlaXccs.getId()))
  		{
  			supPlaXccs.setXccs(",,,,,,,,,,,,");
  		}
  		return EDIT;
  	}
  	
  	public String saveXccs()
  	{
  		supPlaXccs.setXccs(supPlaXccs.getXccs().replaceAll(" ", ""));
  		if(supPlaXccs.getId() != null && !"".equals(supPlaXccs.getId()))
  		{
  			supPlaService.updateXccs(supPlaXccs);
  		}
  		else
  		{
  			supPlaService.saveXccs(supPlaXccs);
  		}
  		return RELOAD;
  	}
  	
  	public String getCompanyTypeByTypeDetail(String detail){
  		String cType="";
		String type=detail;
		if("1".equals(String.valueOf(type.charAt(0)))){
			cType+="危化企业,";
		}
		if("1".equals(String.valueOf(type.charAt(1)))){
			cType+="职业卫生企业C,";
		}
		if("1".equals(String.valueOf(type.charAt(2)))){
			cType+="职业卫生企业B,";
		}
		if("1".equals(String.valueOf(type.charAt(3)))){
			cType+="职业卫生企业A,";
		}
		if("1".equals(String.valueOf(type.charAt(4)))){
			cType+="涉爆粉尘企业,";
		}
		if("1".equals(String.valueOf(type.charAt(5)))){
			cType+="涉氨制冷企业,";
		}
		if("1".equals(String.valueOf(type.charAt(6)))){
			cType+="小微标准化企业,";
		}
		if("1".equals(String.valueOf(type.charAt(7)))){
			cType+="推总监制度企业,";
		}
		if("1".equals(String.valueOf(type.charAt(8)))){
			cType+="涉有限空间企业,";
		}
		if("1".equals(String.valueOf(type.charAt(9)))){
			cType+="冶金企业,";
		}
		if("1".equals(String.valueOf(type.charAt(10)))){
			cType+="工业用燃气企业,";
		}
		if("1".equals(String.valueOf(type.charAt(11)))){
			cType+="工伤事故多发企业 ";
		}
		String[] typeArr=cType.split(",");
		String str="";
		for(int i=0;i<typeArr.length;i++){
			int j=i+1;
			str+=j+":"+typeArr[i]+"<br/>";
		}
  		return str;
  	}
  	
  	/**
  	 * 查询周计划企业列表
  	 */
  	@SuppressWarnings("unchecked")
	public void selectCompanyForImportPlan() throws IOException
  	{
  		
  		try {
  			Map notFinishTaskMap = new HashMap();
  			notFinishTaskMap.put("sqlId", "findNotFinishCompanyId");
  			List<Map<String,Object>> notFinish=httpService.findListDataByMap(notFinishTaskMap);
  			String notFin="";
  			for(Map<String,Object> notMap:notFinish){
  				notFin+=notMap.get("companyId").toString()+",";
  			}
			JSONArray jsonArry = new JSONArray();
			checkUserId=supPla.getCheckUserId();
			planId=supPla.getPlanId();
			SupPla supPla2 = new SupPla();
			supPla2=supPlaService.getById(planId);
			if(supPla.getId() != null && !"".equals(supPla.getId()))
	  		{
	  			supPla = supPlaService.getById(supPla.getId());
				companyIds=supPla.getCheckCompanyId();
	  		}
			Map map = new HashMap();
			map.put("checkUserId", checkUserId);
			List<String> wglist=supPlaService.findwglist(map);
			String wgIds="";
			for(String l:wglist){
				wgIds+=l+",";
			}
			if(!wgIds.equals("")){
				wgIds=wgIds.substring(0,wgIds.length()-1);
			}
			Map map2 = new HashMap();
			if(null!=planId && !"".equals(planId)){
			map2.put("planbId", planId);
			}
			if(wglist.size()!=0){
			map2.put("wgIds", "('" + wgIds.trim().replaceAll(" ", "").replaceAll(",", "','") + "')");
			}
			Date date=new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			String startTime=sf.format(date);
			map2.put("stime", startTime);
			int listSize=0;
			if(pagination==null){
			    pagination = new Pagination(this.getRequest());
			}
			supPlaXccs = supPlaService.getXccs(null);
			String xccs = supPlaXccs.getXccs();
			String[] cs = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"};
			if(xccs != null && !"".equals(xccs))
			{
				cs = xccs.split(",");
			}
			if(null!=companyName&&!"".equals(companyName)){
				map2.put("companyName", "%"+companyName+"%");
			}
			if(null!=companyType&&!"".equals(companyType)){
				map2.put("companyType", Integer.parseInt(companyType));
			}
			map2.put("sqlId", "findCompanyList22");
			map2.put("checkUserId", checkUserId);
			List<Map<String,Object>> companyList=httpService.findListDataByMap(map2);
			listSize=companyList.size();
			List<CompanyInfoPlan> cipList=new ArrayList<CompanyInfoPlan>();
			List<CompanyInfoPlan> cipList0=new ArrayList<CompanyInfoPlan>();
			List<CompanyInfoPlan> cipList1=new ArrayList<CompanyInfoPlan>();
			for(Map ee: companyList){
				CompanyInfoPlan cip=new CompanyInfoPlan();
				cip.setId(ee.get("id").toString());
				cip.setAddress(ee.get("ADDRESS").toString());
				cip.setArea(ee.get("AREA").toString());
				cip.setCharger(ee.get("CHARGER").toString());
				cip.setChargerPhone(ee.get("CHARGER_PHONE").toString());
				cip.setCompanyName(ee.get("enterpriseName").toString());
				cip.setContact(ee.get("CONTACT").toString());
				cip.setContactPhone(ee.get("CONTACT_PHONE").toString());
				cip.setEmail(ee.get("EMAIL").toString());
				cip.setTypeDetail(getCompanyTypeByTypeDetail(ee.get("level").toString()));
				
				String str =ee.get("level").toString();
				int ss = 0;
				for(int i=0;i<12;i++)
				{
					String s = str.substring(i,i+1);
					if(s.equals("1"))
					{
						if(cs[i] != null && !"".equals(cs[i]))
						{
							ss += Integer.parseInt(cs[i]);
						}
					}
				}
				cip.setRemark("需检查："+ ss + "-已检查："+ee.get("count").toString());
				if(notFin.contains(ee.get("id").toString())){
					cip.setIfFinish("1");
						cipList1.add(cip);
				}else{
					cip.setIfFinish("0");
					cipList0.add(cip);
				}
			}
			cipList.addAll(cipList1);
			cipList.addAll(cipList0);
			pagination.setList(cipList);
			pagination.setTotalCount(cipList.size());
			
				
			
			convObjectToJson(pagination, null);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	
  	
  	public String calFinishState(SupPla sp){
  		String state="";
  		//周计划完成度
  		if("week".equals(sp.getPlanType())){
  			Map<String,Object> map1 = new HashMap<String,Object>();
  			map1.put("sqlId", "calFinishStateForWeekPlan");
  			map1.put("planId", sp.getId());
  			state=httpService.findListDataByMap(map1).get(0).get("finishState").toString()+"%";
  		}else if("year".equals(sp.getPlanType())&&"1".equals(sp.getAddType())){
  			Map<String,Object> map2 = new HashMap<String,Object>();
  			map2.put("sqlId", "findCompanyListForYearPlan");
			map2.put("planbId", sp.getId());
			List<Map<String,Object>> companyList=httpService.findListDataByMap(map2);
			supPlaXccs = supPlaService.getXccs(null);
			String xccs = supPlaXccs.getXccs();
			String[] cs = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"};
			if(xccs != null && !"".equals(xccs))
			{
				cs = xccs.split(",");
			}
			int total=0;
			for(Map ee: companyList){
				String str =ee.get("level").toString();
				int ss = 0;
				for(int i=0;i<12;i++)
				{
					String s = str.substring(i,i+1);
					if(s.equals("1"))
					{
						if(cs[i] != null && !"".equals(cs[i]))
						{
							ss += Integer.parseInt(cs[i]);
						}
					}
				}
				total+=ss;
			}
			map2.put("sqlId", "findFinishTaskCountForYearPlan");
			map2.put("yearPlanbId", sp.getId());
			String finishCount=httpService.findListDataByMap(map2).get(0).get("finishCount").toString();
			if(total == 0 || Integer.parseInt(finishCount)==0)
			{
				state = "0%";
			}
			else
			{
				double d=(Integer.parseInt(finishCount)+0.0)/total*100;
				state=new java.text.DecimalFormat("#.00").format(d)+"%";
			}
  		}
  		return state;
  		
  	}
  	
	
  	public String jdjcjh(){
  		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A09")){
				roleName = "09";//可以添加计划和导入年计划的角色为监察大队队长
				break;
			}else if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A07")||ur.getRole().getRoleCode().equals("A08")||ur.getRole().getRoleCode().equals("A11")) 
			{
				roleName = "1";//可以添加计划的角色为中队长、综合处处长、危化处处长、职业健康处人员
				break;
			}
		}
		
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != supPla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supPla.getPlanName()) && (0 < supPla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + supPla.getPlanName().trim() + "%");
			}

			if ((null != supPla.getPlanType()) && (0 < supPla.getPlanType().trim().length())){
				paraMap.put("planType", supPla.getPlanType().trim());
			}

			if ((null != supPla.getCheckItemType()) && (0 < supPla.getCheckItemType().trim().length())){
				paraMap.put("checkItemType", supPla.getCheckItemType().trim());
			}

			if ((null != supPla.getCheckCompanyName()) && (0 < supPla.getCheckCompanyName().trim().length())){
				paraMap.put("checkCompanyName", "%" + supPla.getCheckCompanyName().trim() + "%");
			}
			List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight r:urs){//中队长登录查询该中队的年计划和自己中队自己制定的计划
				if("A11".equals(r.getRole().getRoleCode())){
					paraMap.put("createUserId",this.getLoginUser().getId());
					break;
				} if("A08".equals(r.getRole().getRoleCode())){
					paraMap.put("planType","day");
					break;
				}
				
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("planType","402880084196a4a30141973ad5cd01b5");
		codeMap.put("checkItemType","402880084196a4a301419737ca0401a9");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|checkCompanyName|planName|planType|checkItemType|createUserID|ifywc|";
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
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索计划名称".equals(searchLike)){
			paraMap.put("planName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = supPlaService.findByPage(pagination, paraMap);
			
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

	public SupPla getSupPla(){
		return this.supPla;
	}

	public void setSupPla(SupPla supPla){
		this.supPla = supPla;
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public List<Map> getDepts() {
		return depts;
	}

	public void setDepts(List<Map> depts) {
		this.depts = depts;
	}

	public List<Map> getPersons() {
		return persons;
	}

	public void setPersons(List<Map> persons) {
		this.persons = persons;
	}

	public List<Map> getPlanlist() {
		return planlist;
	}

	public void setPlanlist(List<Map> planlist) {
		this.planlist = planlist;
	}

	public SupTas getSupTas() {
		return supTas;
	}

	public void setSupTas(SupTas supTas) {
		this.supTas = supTas;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Map> getChecklist() {
		return checklist;
	}
	public void setChecklist(List<Map> checklist) {
		this.checklist = checklist;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public String getCheckDeptId() {
		return checkDeptId;
	}
	public void setCheckDeptId(String checkDeptId) {
		this.checkDeptId = checkDeptId;
	}
	public String getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(String companyIds) {
		this.companyIds = companyIds;
	}
	public String getCompanyNames() {
		return companyNames;
	}
	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}
	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}
	public File getUserFile() {
		return userFile;
	}
	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SupPlaXccs getSupPlaXccs() {
		return supPlaXccs;
	}
	public void setSupPlaXccs(SupPlaXccs supPlaXccs) {
		this.supPlaXccs = supPlaXccs;
	}
	public List<CompanyInfoPlan> getCipList() {
		return cipList;
	}
	public void setCipList(List<CompanyInfoPlan> cipList) {
		this.cipList = cipList;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
