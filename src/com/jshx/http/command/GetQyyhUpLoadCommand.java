package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.service.CompanyInfoPlanService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.patrolUser.entity.PatrolUser;
import com.jshx.patrolUser.service.PatrolUserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.sendSms.SendSms;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

/**
 * 隐患信息上报
 * @author lj
 *
 */

public class GetQyyhUpLoadCommand implements Command
{
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private PatrolUserService patrolUserService = (PatrolUserService) SpringContextHolder.getBean("patrolUserService");
	private CompanyInfoPlanService companyInfoPlanService = (CompanyInfoPlanService) SpringContextHolder.getBean("companyInfoPlanService");
	private EntBaseInfoService entBaseInfoService = (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String troubleName = obj.getString("troubleName");//隐患名称
		String troubleSource = obj.getString("troubleSource");//隐患来源
		String troubleAdd = obj.getString("troubleAdd");//隐患地址
		String checkItem = obj.getString("checkItem");//检查项
		String troubleLevel = obj.getString("troubleLevel");//隐患级别
		String troubleSort = obj.getString("troubleSort");//隐患类别
		String recTerm = obj.getString("recTerm");//整改期限
		//String isCompanyUpload = obj.getString("isCompanyUpload");// 0：企业 1:安监上报
		String isSafeUpload = obj.getString("isSafeUpload");//是否上报安委会 0：否 1：是
		String companyId = obj.getString("companyId");//企业id
		String taskId = obj.getString("taskId");//关联任务id
		
		String introduce = obj.getString("introduce");//隐患描述
		String rectDept = obj.getString("rectDept");//整改责任部门
		String rectPerson = obj.getString("rectPerson");//整改责任人
		String rectTel = obj.getString("rectTel");//整改责任人联系方式
		try {	
			
			TroMan troMan = new TroMan();
			troMan.setIntroduce(introduce);
			troMan.setRectDept(rectDept);
			troMan.setRectPerson(rectPerson);
			troMan.setIfReportAwh(isSafeUpload);
			troMan.setRectTel(rectTel);
			if("0".equals(isSafeUpload)&&!"企业".equals(troubleSource)){//直接安监局处理的
				troMan.setDealDeptName("安监局");
				troMan.setDealDeptId("002001");
			}
			
			troMan.setTroubleAdd(troubleAdd);
			troMan.setTroubleLevel(troubleLevel);
			troMan.setTroubleName(troubleName);
			troMan.setTroubleSort(troubleSort);
			troMan.setTroubleSource(troubleSource);
			troMan.setDelFlag(0);
			troMan.setCheckItem(checkItem);
			troMan.setRectificationTerm(new SimpleDateFormat("yyyy-MM-dd").parse(recTerm));
			troMan.setUserId(userId);
			troMan.setIfCorrected("0");
			troMan.setTaskId(taskId);
			troMan.setCreateUserID(userId);
			//设置企业整改状态
			if("企业".equals(troubleSource)){//企业上报
				if("1".equals(isSafeUpload)){//安委会上报
					troMan.setRectificationState("3");
				}else{
					troMan.setRectificationState("11");
				}
			}else{//安监上报
				if("1".equals(isSafeUpload)){//安委会上报
					troMan.setRectificationState("20");
				}else{
					troMan.setRectificationState("2");
					if("1".equals(troubleLevel)){//一般隐患 直接整改 不需审核
						troMan.setRectificationState("6");
					}
				}
			}
			
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			//根据获取一样用户的区域信息
			
				Map map = new HashMap();
				if(companyId==null||"".equals(companyId)){//标示企业上报
					map.put("id",userId);
				}else{
					map.put("id",companyId);
				}
				map.put("sqlID", "queryCompanyInfoHttp");
				Map company = httpService.getMapByMap(map);
				if(company!=null){
					troMan.setCompanyId(company.get("companyId")+"");
					troMan.setCompanyName(company.get("companyName")+"");
					troMan.setAreaId(company.get("areaId")+"");
					troMan.setAreaName(company.get("areaName")+"");
				}
				else
				{
					CompanyInfoPlan companyInfoPlan = companyInfoPlanService.getById(companyId);
					map.put("enterpriseName", companyInfoPlan.getCompanyName());
					EntBaseInfo ent = entBaseInfoService.findEntBaseInfoByMap(map);
					if(ent != null && ent.getId() != null)
					{
						troMan.setAreaId(ent.getEnterprisePossession());
						Map m = new HashMap();
						m.put("codeName", "企业属地");
						m.put("itemValue", ent.getEnterprisePossession());
						troMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
						troMan.setCompanyId(ent.getId());
						troMan.setCompanyName(ent.getEnterpriseName());
					}
					else if(companyInfoPlan != null)
					{
						troMan.setCompanyId(companyInfoPlan.getId());
						troMan.setCompanyName(companyInfoPlan.getCompanyName());
					}
				}
			User u = userService.findUserById(userId);
			PatrolUser patrolUser=patrolUserService.getById(userId);
			
			if(u!=null){
				troMan.setUserId(userId);
				troMan.setUserName(u.getDisplayName());
				troMan.setReportTime(new Date());
			}if(patrolUser!=null){
				troMan.setUserId(userId);
				troMan.setUserName(patrolUser.getUserName());
				troMan.setReportTime(new Date());
			}
			
			troMan.setLinkId(linkId);
			troManService.save(troMan);
			
			SendSms sendSms =new SendSms();
			String result = sendSms.send(troMan.getRectTel(),"您有一个待整改隐患:"+troMan.getTroubleName()+",请尽快整改！");
			System.out.print(result);
			
			bd.setCode("0");
			bd.setMessage("隐患信息上报成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", linkId);
			bd.setContent(json.toString());
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("隐患信息上报失败!");
			e.printStackTrace();
		}
		return bd;
	}
	/**
	 * 判断当前时间是否在有效期内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean vailTime(String startTime,String endTime){
		boolean flag = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			long stime = c.getTimeInMillis();
			c.setTime(new Date());
			long ntime = c.getTimeInMillis();
			c.setTime(sdf.parse(endTime));
			long etime = c.getTimeInMillis();
			
			if(ntime>=stime&&ntime<=etime){
				flag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
