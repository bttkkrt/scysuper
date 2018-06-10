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
 * 危险源任务反馈
 * @author lj
 *
 */

public class GetWxyxcrwFeedbackCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String taskId = obj.getString("taskId");//任务id
		String checkTerm = obj.getString("checkTerm");//实际巡查项
		String remark = obj.getString("remark");//备注
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("taskId", taskId);
		map.put("checkTerm", checkTerm);
		map.put("remark", remark);
		map.put("result", "已巡查");
		map.put("sqlID", "TaskFeedBackByMap");
		try {	
			map.put("reportTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			bd.setCode("0");
			bd.setMessage("巡查反馈成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", taskId);
			bd.setContent(json.toString());
			httpService.updateMapByMap(map);//更新map 对象
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("巡查反馈失败!");
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
