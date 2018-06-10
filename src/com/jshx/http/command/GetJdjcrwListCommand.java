package com.jshx.http.command;

import java.text.SimpleDateFormat;
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
import com.jshx.http.util.StringTools;

/**
 * 获取监督检查列表接口
 * @author 周云琳 2015-10-31
 *
 */
public class GetJdjcrwListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String type = obj.getString("type");//0表示我的任务。1表示所有
		String key=obj.getString("key");
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		map.put("checkUserid", userId);
		map.put("pageNum", s);
		map.put("key", "%"+key+"%");
		map.put("pageSize", limit);
		map.put("type", type);
		try {
			
			if(type.equals("0")){
					map.put("sqlID", "queryJdjcrwCountByMap");
					int total = httpService.getListCountbyMap(map);//获取监督检查我的任务总数
					map.put("sqlID", "queryJdjcrwListByMap");
					List<Map> plans = httpService.getListByMap(map);//获取监督检查我的任务列表
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
			}else if(type.equals("1")){
					map.put("sqlID", "queryJdjcrwAllCountByMap");
					int total = httpService.getListCountbyMap(map);//获取监督检查任务所有总数
					map.put("sqlID", "queryJdjcrwAllListByMap");
					List<Map> plans = httpService.getListByMap(map);//获取监督检查任务所有列表
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
				
			}
			
			
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("查询失败!");
			e.printStackTrace();
		}
		return bd;
	}
}
