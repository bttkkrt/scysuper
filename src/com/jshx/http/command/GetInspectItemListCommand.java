package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;

public class GetInspectItemListCommand  implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String distributeId = json.getString("distributeId");//获取所属任务id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		map.put("userId", userId);
		map.put("distributeId", distributeId);
		
		List<Map<String,String>> list = httpService.GetInspectItemList(map);//获取安全检查项列表 hanxc 2014-11-13

		int total = httpService.GetInspectItemListCountByMap(map);//获取安全检查项数 hanxc 2014-11-13
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(Map<String, String> tempMap : list){
						   JSONObject jo = new JSONObject();
						   jo.put("id",tempMap.get("id"));
						   jo.put("distributeId",tempMap.get("distributeId"));
						   jo.put("item",tempMap.get("item"));
						   jo.put("requirement",tempMap.get("requirement"));
						   jo.put("isinspect",tempMap.get("isinspect"));
						   jo.put("count",tempMap.get("count"));
						   jo.put("remark",tempMap.get("remark"));
						   jo.put("userId",tempMap.get("userId"));
						   jo.put("reportTime",tempMap.get("reportTime"));
						   jo.put("taskTime",tempMap.get("taskTime"));
						   jo.put("describe",tempMap.get("describe"));//描述
						   
						   Map m = new HashMap();
						   m.put("type", "aqjc");
						   m.put("linkId", tempMap.get("id"));
						   DataBean bean01 = httpService.getPhotoListByType(m);
						   jo.put("image",StringTools.NullToStr(bean01.getRname(),""));
						   
						   String reviewResult = "";
						   if(null != tempMap.get("reviewResult"))
							   reviewResult = tempMap.get("reviewResult");
						   jo.put("reviewResult",reviewResult);
						   String reviewRemark = "";
						   if(null != tempMap.get("reviewRemark"))
							   reviewRemark = tempMap.get("reviewRemark");
						   jo.put("reviewRemark",reviewRemark);
						   String reviewLatitude = "";
						   if(null != tempMap.get("reviewLatitude"))
							   reviewLatitude = tempMap.get("reviewLatitude");
						   jo.put("reviewLatitude",reviewLatitude);
						   String reviewLongitude = "";
						   if(null != tempMap.get("reviewLongitude"))
							   reviewLongitude = tempMap.get("reviewLongitude");
						   jo.put("reviewLongitude",reviewLongitude);
						   String reviewUserId = "";
						   String reviewImage = "";
						   if(null != tempMap.get("reviewUserId")){
							   reviewUserId = tempMap.get("reviewUserId");
							   Map rm = new HashMap();
							   rm.put("type", "aqjc");
							   rm.put("linkId", tempMap.get("id")+reviewUserId);
							   DataBean bean02 = httpService.getPhotoListByType(rm);
							   reviewImage = StringTools.NullToStr(bean02.getRname(),"");
						   }
						   jo.put("reviewUserId",reviewUserId);
						   String reviewTime = "";
						   if(null != tempMap.get("reviewTime"))
							   reviewTime = tempMap.get("reviewTime");
						   jo.put("reviewTime",reviewTime);
						   
						   jo.put("reviewImage",reviewImage);
						   
						   ja.add(jo);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
				}else{
					bd.setCode("1");
					bd.setMessage("无查询结果");
				}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}

}
