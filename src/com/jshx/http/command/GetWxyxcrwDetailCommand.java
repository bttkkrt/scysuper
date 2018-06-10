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
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.dot.service.DotService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 危险源任务详情
 * @author lj
 *
 */

public class GetWxyxcrwDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private DotService dotService = (DotService) SpringContextHolder.getBean("dotService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//计划id
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("id", id);
		map.put("sqlID", "queryTasksDetailByMap");
		try {
			Map detail = httpService.getMapByMap(map);//获取map 对象
			if(detail!=null){
				
				JSONObject json = new JSONObject();
				json.put("id", detail.get("id"));
				json.put("checkNo", detail.get("checkNo"));
				json.put("taskName", detail.get("taskName"));
				json.put("checkKind", detail.get("checkKind"));
				json.put("checkId", detail.get("checkId"));
				json.put("checkName", detail.get("checkName"));
				json.put("checkTime", detail.get("checkTime"));
				json.put("checkTimeEnd", detail.get("checkTimeEnd"));
				json.put("checkPeopleName", detail.get("checkPeopleName"));
				json.put("actualCheckName", detail.get("actualCheckName")==null?"":detail.get("actualCheckName"));
				json.put("actualCheckTime", detail.get("actualCheckTime")==null?"":detail.get("actualCheckTime"));
				json.put("remark", detail.get("remark")==null?"":detail.get("remark"));
				bd.setContent(json.toString());
				bd.setCode("0");
				bd.setMessage("查询成功!");
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果!");
			}
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("查询失败!");
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
