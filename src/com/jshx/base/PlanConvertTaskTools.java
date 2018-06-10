package com.jshx.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.jpush.api.JPushClient;

import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.Information;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.wxyxcjhgl.entity.DanPlaMan;
import com.jshx.wxyxcjhgl.service.DanPlaManService;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.service.DanTasManService;
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.service.ComDanIdeService;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.service.IntSitService;

public class PlanConvertTaskTools {
	DanPlaManService danPlaManService = (DanPlaManService) SpringContextHolder.getBean("danPlaManService");
	DanTasManService danTasManService = (DanTasManService) SpringContextHolder.getBean("danTasManService");
	IntSitService intSitService = (IntSitService)SpringContextHolder.getBean("intSitService");
	CodeService codeService = (CodeService)SpringContextHolder.getBean("codeService");
	ComDanIdeService comDanIdeService = (ComDanIdeService)SpringContextHolder.getBean("comDanIdeService");
	ContentInformationsService contentInformationsService = (ContentInformationsService) SpringContextHolder.getBean("contentInformationsService");
	@SuppressWarnings("unchecked")
	public void notice() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmSS");
		
		Date d = new Date();
		try {
			//获取所有过期任务
			Map map = new HashMap();
			map.put("month",sdf.format(d));
			List<DanTasMan> tasks = danTasManService.getNoDealTasks(map);
			for(DanTasMan m:tasks){
				map.put("id", m.getId());
				map.put("result", "过期任务");
				danTasManService.updateTaskResult(map);
			}
			/*for(DanPlaMan dpm:plans){
				String frequency = dpm.getCheckFrequency();//week month year
				DanTasMan danTasMan = new DanTasMan();
				danTasMan.setTaskName(dpm.getPlanName()+sdff.format(new Date()));
				danTasMan.setAreaId(dpm.getAreaId());
				danTasMan.setAreaName(dpm.getAreaName());
				danTasMan.setCompanyId(dpm.getCompanyId());
				danTasMan.setCompanyName(dpm.getCompanyName());
				danTasMan.setCheckPeopleId(dpm.getCheckPeopleId());
				danTasMan.setCheckPeopleName(dpm.getCheckPeopleName());
				danTasMan.setAssPlanNo(dpm.getId());
				danTasMan.setDangerId(dpm.getDangerId());
				danTasMan.setDangerName(dpm.getDangerName());
				danTasMan.setCheckType(dpm.getCheckTypeId());
				danTasMan.setCheckTime(sdf.parse(sdf.format(new Date())));
				danTasMan.setDelFlag(0);
				danTasMan.setResult("待巡查");
				Calendar c = Calendar.getInstance();
				if("week".equals(frequency)){//每周的生成的任务
					c.add(Calendar.DAY_OF_WEEK, 6);
					
				}else if("month".equals(frequency)){//每月生成的任务
					int end=c.getActualMaximum(c.DAY_OF_MONTH);
					c.set(Calendar.DAY_OF_MONTH, end);
					
				}else if("year".equals(frequency)){//每年生成的任务
					c.add(Calendar.YEAR, 1);
				}
				danTasMan.setCheckTimeEnd(sdf.parse(sdf.format(c.getTime())));
				danTasManService.save(danTasMan);
					}*/
			
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
						System.out.println("------------------年计划-----------"+newDanPlaMan.getCompanyName());
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
					System.out.println("------------------危险源年计划-----------"+com.getCompanyName());
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
						System.out.println("------------------月计划-----------"+newDanPlaMan.getCompanyName());
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
						System.out.println("------------------危险源月计划-----------"+com.getCompanyName());
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
						System.out.println("------------------周计划-----------"+newDanPlaMan.getCompanyName());
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
						System.out.println("------------------危险源周计划-----------"+com.getCompanyName());
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
						System.out.println("------------------日计划-----------"+newDanPlaMan.getCompanyName());
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
						System.out.println("------------------危险源日计划-----------"+com.getCompanyName());
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
			    
			
				//资质情况提醒
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.DAY_OF_MONTH, 1);
				map.put("intelligenceValidityEnd", c.getTime());
				List<IntSit> zzqkList = intSitService.findIntSit(map);
				for(IntSit intSit:zzqkList)
				{
					try {
						if(intSit.getIntelligenceType() != null && !"".equals(intSit.getIntelligenceType()))
						{
							//信息推送
							JPushClient jpush = new JPushClient();
							jpush.sendAndroidNotificationWithAlias("行政许可及资质到期提醒","您单位的"+intSit.getIntelligenceType()+"将于"+sdf.format(intSit.getIntelligenceValidityEnd())+"到期", null,intSit.getCreateUserID());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
	
	

	
	public void info() {
		Date d=new Date();
		if(d.getHours() >= 9 && d.getHours() <= 17)
		{
			Map map=new HashMap();
			map.put("time", d);
			List<Information> unread=contentInformationsService.findUnread(map);//查询过期公告
			for(Information mm:unread){
				Map map2=new HashMap();
				map2.put("noticeId", mm.getId());
				List<NoticeCallback> unreadUser=contentInformationsService.findUnreadUser(map2);//查询过期公告人
				if(unreadUser.size()!=0){
					String ids="";
					for(NoticeCallback n:unreadUser){
						 ids+=n.getUserID()+",";
					}
					String[] userIds=ids.split(",");
					try {
						//抢单后的审核 信息推送
						Map send = new HashMap();

						send.put("type", "1");
						JSONObject json = new JSONObject();
						json.put("id", mm.getId());
						send.put("content",json.toString());
						//信息推送
						JPushClient jpush = new JPushClient();
						jpush.sendAndroidNotificationWithAlias("通知公告过期提醒","您有公告["+ mm.getInfoTitle() + "]已到期，请尽快阅读", send,userIds);
						
						//信息推送
						JPushClient jpush2 = new JPushClient();
						jpush2.sendAndroidNotificationWithAlias("公告阅读反馈","您发送的公告["+mm.getInfoTitle()+"]有"+unreadUser.size()+"人逾期未读", null,mm.getCreateUserId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}
	}
}
