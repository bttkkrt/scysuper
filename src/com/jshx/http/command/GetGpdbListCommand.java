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
import com.jshx.module.admin.service.UserService;

/**
 * 挂牌督办列表
 * @author 费谦 2015-10-13
 */
public class GetGpdbListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		//String companyId = obj.getString("companyId");
		String pageNum = obj.getString("pageNum");
		String pageSize = obj.getString("pageSize");
		String searchName=obj.getString("searchName");
		String state=obj.getString("state");//状态 0：所有，1：待整改（包括 审核未过、重新整改），2：待审核，3：完成，4：我新增的挂牌督办
		try{
			String deptCode=userService.findUserById(userId).getDeptCode();
			
			Map<String,Object> map = new HashMap<String,Object> ();
			Map<String,Object>  map1 = new HashMap<String,Object> ();
			if(httpService.judgeRoleCode(userId, "A04")){//综合处科员
				if(state.equals("0")){
					map.put("ky0", "ky0");
					map1.put("ky0", "ky0");
				}else if(state.equals("1")){
					map.put("ky1", "待整改");
					map1.put("ky1", "待整改");
				}else if(state.equals("4")){
					map.put("ky4", userId);
					map1.put("ky4", userId);
				}else{
					map.put("otherNone", "otherNone");
					map1.put("otherNone", "otherNone");
				}
			}else if(httpService.judgeRoleCode(userId, "A03")){//综合处处长
				if(state.equals("0")){
					map.put("cz0", "cz0");
					map1.put("cz0", "cz0");
				}else if(state.equals("2")){
					map.put("cz2", "待审核");
					map1.put("cz2", "待审核");
				}else{
					map.put("otherNone", "otherNone");
					map1.put("otherNone", "otherNone");
				}
			}else if(httpService.judgeRoleCode(userId, "A02")){//局领导
				if(state.equals("0")){
					map.put("ld0", "ld0");
					map1.put("ld0", "ld0");
				}else if(state.equals("2")){
					map.put("ld2", "待审批");
					map1.put("ld2", "待审批");
				}else{
					map.put("otherNone", "otherNone");
					map1.put("otherNone", "otherNone");
				}
			}else{
				if(state.equals("0")){
					map.put("other0", "other0");
					map1.put("other0", "other0");
				}else{
					map.put("otherNone", "otherNone");
					map1.put("otherNone", "otherNone");
				}
			}
			//map.put("companyId", companyId);
//			map.put("table", "SUPERVICE");
			map.put("sqlId", "queryGpdbListByMap");
			map.put("start", pageNum);
			map.put("limit", pageSize);
			map.put("searchName", "%"+searchName+"%");
//			map.put("state", state);
			List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
			//查询总数
			//map1.put("companyId", companyId);
//			map1.put("table", "SUPERVICE");
			map1.put("sqlId", "queryGpdbListByMapForCount");
			map1.put("searchName", "%"+searchName+"%");
//			map1.put("state", state);
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
					json.put("dangerName",null==m.get("DANGER_NAME")?"":m.get("DANGER_NAME"));
					json.put("companyName", null==m.get("COMPANY_NAME")?"":m.get("COMPANY_NAME") );
					json.put("listingTime",null==m.get("LISTING_TIME")?"":m.get("LISTING_TIME").toString().substring(0, 10));
					json.put("sort", null==m.get("SORT")?"":m.get("SORT") );
					json.put("level", null==m.get("LEVELS")?"":m.get("LEVELS") );
					json.put("state", null==m.get("RECTIFICATION_STATE")?"":m.get("RECTIFICATION_STATE") );
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
