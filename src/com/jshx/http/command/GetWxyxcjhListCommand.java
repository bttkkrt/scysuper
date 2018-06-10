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
 * 危险源计划列表
 * @author lj
 *
 */

public class GetWxyxcjhListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String companyName = obj.getString("companyName");//企业名称
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String type = obj.getString("type");//1 待审核 2:审核通过 审核未通过 
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
		map.put("companyName", "%"+companyName+"%");
		map.put("pageNum", s);
		map.put("pageSize", limit);
		map.put("type", type);
		map.put("sqlID", "queryPlansCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取列表总数
			map.put("sqlID", "queryPlansByMap");
			List<Map> plans = httpService.getListByMap(map);//获取列表
			if(plans!=null&&!plans.isEmpty()){
				bd.setTotal(total+"");
				bd.setContent(JSONArray.fromObject(plans).toString());
				int page = total%limit==0?total/limit:(total/limit+1);
				bd.setPage(page+"");
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
