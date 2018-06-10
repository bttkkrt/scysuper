package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.wxyxcjhgl.entity.DanPlaMan;
import com.jshx.wxyxcjhgl.service.DanPlaManService;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.service.DanTasManService;

/**
 * 危险源计划审核
 * @author lj
 *
 */

public class GetWxyxcjhReviewCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private  DanPlaManService danPlaManService = (DanPlaManService) SpringContextHolder.getBean("danPlaManService");
	private CheckRecordService checkRecordService  = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private DanTasManService danTasManService  = (DanTasManService) SpringContextHolder.getBean("danTasManService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//计划id
		String result = obj.getString("result");//审核结果
		String remark = obj.getString("remark");//备注
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("id", id);
		if("0".equals(result)){
			map.put("result", "审核通过");
		}else if("1".equals(result)){
			map.put("result", "审核未通过");
		}else{
			map.put("result", "待审核");
		}
		
		map.put("remark", remark);
		map.put("sqlID", "AuditPlanslByMap");
		try {	
			bd.setCode("0");
			bd.setMessage("审核成功!");
			if("0".equals(result)){//审核通过生成任务
					DanPlaMan danPlaMan = danPlaManService.getById(id);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");
					String frequency = danPlaMan.getCheckFrequency();//week month year
					
					Calendar c = Calendar.getInstance();
					Date startTime  = danPlaMan.getPlanStartTime();//计划开始时间
					Date endTime = danPlaMan.getPlanEndTime();//计划结束时间
					
					if("week".equals(danPlaMan.getCheckFrequency())){//每周
						List<Map> weeks = weekDate(sdf.format(startTime),sdf.format(endTime));
						if(weeks!=null&&!weeks.isEmpty()){
							for(Map m:weeks){
								DanTasMan danTasMan = newTask(danPlaMan);
								danTasMan.setTaskName(danPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(danPlaMan.getCreateUserID());
							}
						}
						
					}else if("month".equals(danPlaMan.getCheckFrequency())){//每月
						List<Map> months = monthDate(sdf.format(startTime),sdf.format(endTime));
						if(months!=null&&!months.isEmpty()){
							for(Map m:months){
								DanTasMan danTasMan = newTask(danPlaMan);
								danTasMan.setTaskName(danPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(danPlaMan.getCreateUserID());
							}
						}
					}else if("year".equals(danPlaMan.getCheckFrequency())){//每年
						List<Map> years = yearDate(sdf.format(startTime),sdf.format(endTime));
						if(years!=null&&!years.isEmpty()){
							for(Map m:years){
								DanTasMan danTasMan = newTask(danPlaMan);
								danTasMan.setTaskName(danPlaMan.getPlanName()+m.get("startTime").toString().replace("-", ""));
								danTasMan.setCheckTime(sdf.parse(m.get("startTime")+""));
								danTasMan.setCheckTimeEnd(sdf.parse(m.get("endTime")+""));
								danTasManService.save(danTasMan);
								danTasMan.setCreateUserID(danPlaMan.getCreateUserID());
							}
						}
					}
			}
			//保存审核记录 lj 2015-10-30
			CheckRecord checkRecord=new CheckRecord();
			checkRecord.setInfoId(id);
			if("0".equals(result)){
				checkRecord.setCheckResult("审核通过");
			}else{
				checkRecord.setCheckResult("审核未通过");
			}
			checkRecord.setCheckRemark(remark);
			checkRecord .setCheckUserid(userId);
			checkRecord.setDelFlag(0);
			User user = userService.findUserById(userId);
			if(user!=null){
				checkRecord.setCheckUsername(user.getDisplayName());
			}
			
			checkRecordService.save(checkRecord);
			
			httpService.updateMapByMap(map);//更新map 对象
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("审核失败!");
			e.printStackTrace();
		}
		return bd;
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
