package com.jshx.http.command;

import java.text.SimpleDateFormat;
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
 * 节假日开停工列表
 * @author 费谦 2015-10-13
 */
public class GetJjrktgListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String pageNum = obj.getString("pageNum");
		String pageSize = obj.getString("pageSize");
		String companyId = obj.getString("companyId");
//		String iType = obj.getString("iType");
		if(!httpService.judgeRoleCode(userId, "A23")){//接口类型 安监
			try{
				String type = obj.getString("type");//1:待审核，2：不用审核
				String searchName = obj.getString("searchName");//匹配字段
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("table", "SHU_HOL");
				map.put("companyId", companyId);
				map.put("jjrktgSearchName", "%"+searchName.trim()+"%");
				map.put("sqlId", "getListByTableAndCompanyId");
				map.put("start", pageNum);
				map.put("limit", pageSize);
				if("1".equals(type)){
					if(httpService.judgeRoleCode(userId, "A06")||httpService.judgeRoleCode(userId, "A05")){
						map.put("jjrktgType1", type);
					}else{
						bd.setCode("2");
						bd.setMessage("角色异常");
						return bd;
					}
				}else{
					map.put("jjrktgType2", type);
				}
				List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
				//查询总数
				Map<String,Object>  map1 = new HashMap<String,Object> ();
				map1.put("table", "SHU_HOL");
				map1.put("companyId", companyId);
				map1.put("sqlId", "getListByTableAndCompanyIdForCount");
				map1.put("jjrktgSearchName", "%"+searchName.trim()+"%");
				if("1".equals(type)){
					map1.put("jjrktgType1", type);
				}else{
					map1.put("jjrktgType2", type);
				}
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
						String start=(null==m.get("HOLIDAY_TIME_START")?"":sdf.format(sdf.parse(m.get("HOLIDAY_TIME_START").toString())));
						String end=(null==m.get("HOLIDAY_TIME_END")?"":sdf.format(sdf.parse(m.get("HOLIDAY_TIME_END").toString())));
						json.put("holidayTime",start+"~"+end);
						json.put("companyName", null==m.get("COMPANY_NAME")?"":m.get("COMPANY_NAME"));
						json.put("areaName", null==m.get("AREA_NAME")?"":m.get("AREA_NAME"));
						//是否开工
						Map m1 = new HashMap();
						m1.put("codeName", "是或否");
						m1.put("itemValue", m.get("IF_START").toString());
						json.put("ifStart", null==codeService.findCodeValueByMap(m1).getItemText()?"":codeService.findCodeValueByMap(m1).getItemText() );
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
		}else{//接口类型 企业
			try{
				
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("companyId", companyId);
				map.put("table", "SHU_HOL");
				map.put("sqlId", "getListByTableAndCompanyId");
				map.put("start", pageNum);
				map.put("limit", pageSize);
				List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
				//查询总数
				Map<String,Object>  map1 = new HashMap<String,Object> ();
				map1.put("companyId", companyId);
				map1.put("table", "SHU_HOL");
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
						String start=(null==m.get("HOLIDAY_TIME_START")?"":sdf.format(sdf.parse(m.get("HOLIDAY_TIME_START").toString())));
						String end=(null==m.get("HOLIDAY_TIME_END")?"":sdf.format(sdf.parse(m.get("HOLIDAY_TIME_END").toString())));
						json.put("holidayTime",start+"~"+end);
						json.put("companyName", null==m.get("COMPANY_NAME")?"":m.get("COMPANY_NAME"));
						json.put("areaName", null==m.get("AREA_NAME")?"":m.get("AREA_NAME"));
						//是否开工
						Map m1 = new HashMap();
						m1.put("codeName", "是或否");
						m1.put("itemValue", m.get("IF_START").toString());
						json.put("ifStart", null==codeService.findCodeValueByMap(m1).getItemText()?"":codeService.findCodeValueByMap(m1).getItemText() );
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
		}
		System.out.println(bd.toString());
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
