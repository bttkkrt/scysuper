package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
/**
 * 获取监督检查当月任务列表接口
 * @author 周云琳 2015-10-31
 *
 */
public class GetJdjcrwDyListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		Date date=new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String stime=sf.format(date);
		map.put("checkUserid", userId);
		map.put("pageNum", s);
		map.put("pageSize", limit);
		map.put("stime",stime);
		map.put("sqlID", "queryJdjcrwDyCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取监督检查当月任务总数
			map.put("sqlID", "queryJdjcrwDyywcCountByMap");
			int ywc=httpService.getListCountbyMap(map);//获取监督检查当月已完成任务数
			int wwc=total-ywc;
			map.put("sqlID", "queryJdjcrwDyListByMap");
			List<Map> plans = httpService.getListByMap(map);//获取监督检查当月任务列表
			if(plans!=null&&!plans.isEmpty()){
				bd.setTotal(total+"");
				JSONObject jo = new JSONObject();
				jo.put("ywc", ywc);
				jo.put("wwc", wwc);
				jo.put("list",JSONArray.fromObject(plans).toString());
				int page = total%limit==0?total/limit:(total/limit+1);
				bd.setContent(jo.toString());
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
}

