package com.jshx.http.command;

import java.util.ArrayList;
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
 * 获取监督检查任务详情接口
 * @author 周云琳 2015-10-31
 *
 */
public class GetJdjcrwDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//任务id
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("id", id);
		map.put("sqlID", "queryJdjcrwDetailByMap");
		try {
			Map detail = httpService.getMapByMap(map);//获取map 对象
			if(detail!=null){
				JSONObject ja = new JSONObject();
				Map paraMap=new HashMap();
				String checkItemId=detail.get("checkItemId").toString();
				paraMap.put("checkItemId",checkItemId);
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				list=httpService.findcheckName(paraMap);
				//JSONObject jo = new JSONObject();
				//for(Map m1:list){
					//String checkItemName=m1.get("checkItemName").toString();
					//String checkTermId=m1.get("id").toString();
					//jo.put("checkItemName", checkItemName);
					//jo.put("id", checkTermId);
				//}
				ja.put("id",detail.get("id"));
				ja.put("companyId", detail.get("companyId"));
				ja.put("areaName", detail.get("areaName"));
				ja.put("companyName", detail.get("companyName"));
				ja.put("taskName", detail.get("taskName"));
				ja.put("taskNum", detail.get("taskNum"));
				ja.put("state", detail.get("state"));
				ja.put("type", detail.get("type"));
				ja.put("checkType",detail.get("taskType"));
				ja.put("checkTime",detail.get("checkTime"));
				ja.put("checkTimeEnd", detail.get("checkTimeEnd"));
				ja.put("actualCheckTime", null==detail.get("actualCheckTime")?"":detail.get("actualCheckTime"));
				ja.put("checkUsername", detail.get("checkUsername"));
				ja.put("checkItemName",JSONArray.fromObject(list).toString());
				ja.put("checkType",detail.get("checkType"));
				ja.put("actualCheckName",null==detail.get("actualCheckName")?"":detail.get("actualCheckName"));
				ja.put("remark",detail.get("remark"));
				bd.setContent(ja.toString());
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
