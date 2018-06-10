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
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 领导带班情况
 * @author 费谦 2015-10-13
 */
public class GetLddbqkListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
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
				map.put("table", "LEA_CLA");
				map.put("companyId", companyId);
				map.put("lddbqkSearchName", "%"+searchName.trim()+"%");
				map.put("sqlId", "getListByTableAndCompanyId");
				map.put("start", pageNum);
				map.put("limit", pageSize);
				if("1".equals(type)){
					if(httpService.judgeRoleCode(userId, "A06")||httpService.judgeRoleCode(userId, "A05")){
						map.put("lddbqkType1", type);
					}else{
						bd.setCode("2");
						bd.setMessage("角色异常");
						return bd;
					}
				}else{
					map.put("lddbqkType2", type);
				}
				List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
				//查询总数
				Map<String,Object>  map1 = new HashMap<String,Object> ();
				map1.put("table", "LEA_CLA");
				map1.put("companyId", companyId);
				map1.put("sqlId", "getListByTableAndCompanyIdForCount");
				map1.put("lddbqkSearchName", "%"+searchName.trim()+"%");
				if("1".equals(type)){
					map1.put("lddbqkType1", type);
				}else{
					map1.put("lddbqkType2", type);
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
						json.put("areaName", null==m.get("AREA_NAME")?"":m.get("AREA_NAME"));
						json.put("companyName", null==m.get("COMPANY_NAME")?"":m.get("COMPANY_NAME"));
						json.put("planMonth",null==m.get("PLANNED_MONTH")?"":sdf.format(sdf.parse(m.get("PLANNED_MONTH").toString())));
						
						//审核记录
						Map<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("infoId", m.get("ROW_ID").toString());
						List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
						String recordStr = StringTools.checkRecordToStr(checkRecords);
						json.put("auditRecord",recordStr );
//						if(null!=m.get("LINK_ID")&&!"".equals(m.get("LINK_ID").toString())){
//							Map<String,Object> mapfj=new HashMap<String, Object>();
//							mapfj.put("picType", "jhfj");
//							mapfj.put("linkId", m.get("LINK_ID").toString());
//							mapfj.put("mkType", "lddbqk");
//							DataBean bean = httpService.getPhotoListByType(mapfj);
//							String filepath = bean.getRname();
//							json.put("filePath",null==filepath?"":filepath );
//						}else{
//							json.put("filePath","" );
//						}
						
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
		}else{
			try{
				
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("companyId", companyId);
				map.put("table", "LEA_CLA");
				map.put("sqlId", "getListByTableAndCompanyId");
				map.put("start", pageNum);
				map.put("limit", pageSize);
				List<Map<String,Object>> aqscgljgList=httpService.findListDataByMap(map);
				//查询总数
				Map<String,Object>  map1 = new HashMap<String,Object> ();
				map1.put("companyId", companyId);
				map1.put("table", "LEA_CLA");
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
						json.put("planMonth",null==m.get("PLANNED_MONTH")?"":sdf.format(sdf.parse(m.get("PLANNED_MONTH").toString())));
						json.put("companyName", null==m.get("COMPANY_NAME")?"":m.get("COMPANY_NAME"));
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
