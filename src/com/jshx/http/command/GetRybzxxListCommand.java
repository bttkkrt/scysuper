package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 荣誉表彰信息管理列表
 * @author 陆妍婷
 */
public class GetRybzxxListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String companyId = obj.getString("companyId");
		String pageNum = obj.getString("pageNum");
		String pageSize = obj.getString("pageSize");
		try{
			Map<String,Object> map = new HashMap<String,Object> ();
			map.put("companyId", companyId);
			map.put("table", "HON_REC");
			map.put("sqlId", "getListByTableAndCompanyId");
			map.put("start", pageNum);
			map.put("limit", pageSize);
			List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
			//查询总数
			Map<String,Object>  map1 = new HashMap<String,Object> ();
			map1.put("companyId", companyId);
			map1.put("table", "HON_REC");
			map1.put("sqlId", "getListByTableAndCompanyIdForCount");
			//设置总条数
			int total=Integer.parseInt(httpService.findListDataByMap(map1).get(0).get("TOTAL").toString());
			bd.setTotal(String.valueOf(total));
			int ps=Integer.parseInt(pageSize);
			if(total%ps==0){
				bd.setPage(String.valueOf(total/ps));
			}else{
				bd.setPage(String.valueOf(total/ps+1));
			}
			//查询成功
			if(aqscgljgList.size()>0){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONArray jArray=new JSONArray();
				for(Map<String,Object> m:aqscgljgList){
					JSONObject json = new JSONObject();
					json.put("id", null==m.get("ROW_ID")?"":m.get("ROW_ID"));
					json.put("approvalNum",null==m.get("APPROVAL_NUMBER")?"":m.get("APPROVAL_NUMBER"));
					json.put("honor",null==m.get("HONOR")?"":m.get("HONOR"));
					json.put("recogDept",null==m.get("RECOGNITION_DEPT")?"":m.get("RECOGNITION_DEPT"));
					json.put("recogDate",null==m.get("RECOGNITION_DATE")?"":m.get("RECOGNITION_DATE"));
					jArray.add(json);
				}
				System.out.println(jArray.toString());
				bd.setContent(jArray.toString());
			}else{
				//无数据
				bd.setCode("1");
				bd.setMessage("无数据");
			}
		}catch(Exception e){
			bd.setCode("2");
			bd.setMessage("异常");
			e.printStackTrace();
		}
		System.out.println(bd.toString());
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
