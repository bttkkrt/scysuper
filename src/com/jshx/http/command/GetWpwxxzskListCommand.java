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
import com.jshx.module.admin.service.CodeService;

/**
 * 物品危险性知识库列表
 * @author 费谦 2015-10-13
 */
public class GetWpwxxzskListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
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
			boolean b=httpService.judgeRoleCode(userId, "A23");
			if(b){
				map.put("deptType", "QY");
				map.put("userId", userId);
			}else{
				map.put("deptType", "AJ");
			}
			map.put("sqlId", "getWpwxxzskListByMap");
			map.put("start", pageNum);
			map.put("limit", pageSize);
			List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
			//查询总数
			Map<String,Object>  map1 = new HashMap<String,Object> ();
			if(b){
				map1.put("deptType", "QY");
				map1.put("userId", userId);
			}else{
				map1.put("deptType", "AJ");
			}
			map1.put("table", "RIS_KNO_BAS");
			map1.put("sqlId", "getWpwxxzskListByMapForCount");
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
					json.put("itemName",null==m.get("ITEM_NAME")?"":m.get("ITEM_NAME"));
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
