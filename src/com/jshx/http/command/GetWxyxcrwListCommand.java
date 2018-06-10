package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 危险源任务列表
 * @author lj
 *
 */

public class GetWxyxcrwListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String companyId = obj.getString("companyId");//企业id
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String type = obj.getString("type");// 0 :待办  1：所有已巡查  2：所有
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		//当登录人是巡查人员的时候只查询自己的计划（2016-2-26 fq）
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "queryCountForLogin");
		paraMap.put("id", userId);
		List<Map<String,Object>> l=httpService.findListDataByMap(paraMap);
		if(l.size()==1){
			map.put("userId", userId);
		}
		map.put("companyId", companyId);
		map.put("pageNum", s);
		map.put("pageSize", limit);
		map.put("type",type);
		map.put("sqlID", "queryTasksCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取任务列表总数
			map.put("sqlID", "queryTasksByMap");
			List<Map> plans = httpService.getListByMap(map);//获取任务列表
			JSONArray ja = new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			if(plans!=null&&!plans.isEmpty()){
				for(Map bean:plans){
					 JSONObject jo = new JSONObject();
					 jo.put("id", null==bean.get("id")?"":bean.get("id"));//主键
					 jo.put("taskName", null==bean.get("taskName")?"":bean.get("taskName"));//任务名称
					 jo.put("checkId", null==bean.get("checkId")?"":bean.get("checkId"));//巡查id
					 jo.put("checkName", null==bean.get("checkId")?"":bean.get("checkName"));//巡查名称
					 jo.put("checkTime", null==bean.get("checkTime")?"":bean.get("checkTime"));//巡查开始时间
					 jo.put("checkTimeEnd", null==bean.get("checkTimeEnd")?"":bean.get("checkTimeEnd"));//巡查结束时间
					 String checkTimeEnd=bean.get("checkTimeEnd").toString();
					 if (checkTimeEnd.equals(sdf.format(d))){
						 jo.put("type", "1");//标识结束时间为今天必须做完
					 }else{
						 jo.put("type", "0");//标识其他
					 }
					 ja.add(jo);
				}
				
				bd.setTotal(total+"");
				bd.setContent(ja.toString());
				int page = total%limit==0?total/limit:(total/limit+1);
				bd.setPage(page+"");
				bd.setCode("0");
				bd.setMessage("查询成功!");
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败!");
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
