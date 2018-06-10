package com.jshx.http.command;

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

public class GetJdjcrwAllListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String key=obj.getString("key");
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		map.put("pageNum", s);
		map.put("key", "%"+key+"%");
		map.put("pageSize", limit);
		
		map.put("sqlID", "queryJdjcrwAllCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取监督检查所有任务总数
			map.put("sqlID", "queryJdjcrwAllListByMap");
			List<Map> plans = httpService.getListByMap(map);//获取监督检查所有任务列表
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
}
