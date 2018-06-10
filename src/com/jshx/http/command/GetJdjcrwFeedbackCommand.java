package com.jshx.http.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.service.CompanyInfoPlanService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.entity.SupTasResult;
import com.jshx.jdjcrw.service.SupTasService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class GetJdjcrwFeedbackCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SupTasService supTasService = (SupTasService) SpringContextHolder.getBean("supTasService");
	private SupPlaService supPlaService = (SupPlaService) SpringContextHolder.getBean("supPlaService");
	private CompanyInfoPlanService companyInfoPlanService= (CompanyInfoPlanService) SpringContextHolder.getBean("companyInfoPlanService");
	private EntBaseInfoService entBaseInfoService = (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private SupPla supPla = new SupPla();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	List<Map> list3=new ArrayList<Map>();
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String taskId = obj.getString("taskId");//任务id
		String remark = obj.getString("remark");//备注
		Date startTime=new Date();
		Date endTime=new Date();
		try {
			startTime=sdf.parse(obj.getString("startTime"));
			endTime=sdf.parse(obj.getString("endTime"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String record= obj.getString("record");//检查记录
		String result=obj.getString("result");//巡查结果
		
		try {	
			SupTas supTas2 = new SupTas();
			supTas2=supTasService.getById(taskId);
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
			supTas2.setCheckTime(startTime);
			supTas2.setCheckTimeEnd(endTime);
			supTas2.setTaskState("已完成");
			supTas2.setRemark(remark);
			supTas2.setCheckRecord(record);
			supTasService.update(supTas2);
			if(null!=supTas2.getPlanId()){
				supPla=supPlaService.getById(supTas2.getPlanId());
				supPla.setIfywc("1");
				supPlaService.update(supPla);
			}
			JSONArray ja=JSONArray.fromObject(result);
			for(int i=0;i<ja.size();i++){
				JSONObject j=ja.getJSONObject(i);
				SupTasResult r=new SupTasResult();
				r.setTaskId(taskId);
				r.setDelFlag(0);
				r.setCheckUserId(userId);
				r.setXcxId(j.get("id").toString());
				r.setRemark(j.get("remark").toString());
				if("1".equals(j.get("result").toString())){
					r.setXcxResult("不合格");
				}else{
					r.setXcxResult("合格");
				}
				
				supPlaService.saveSupTasResult(r);
			}
			
			bd.setCode("0");
			bd.setMessage("成功");
			JSONObject json = new JSONObject();
			json.put("linkId", taskId);
			bd.setContent(json.toString());
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("失败");
			e.printStackTrace();
		}
		return bd;
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
	public List<Map> getList3() {
		return list3;
	}
	public void setList3(List<Map> list3) {
		this.list3 = list3;
	}
}
