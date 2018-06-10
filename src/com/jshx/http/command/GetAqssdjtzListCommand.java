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
import com.jshx.module.admin.service.CodeService;
/**
 * 获取安全设施登记台账列表接口
 * @author 周云琳 2015-10-15
 *
 */
public class GetAqssdjtzListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String companyId = obj.getString("companyId");//获取用户的id
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("table", "SAF_LED");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> safLed=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "SAF_LED");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(safLed!=null&&!safLed.isEmpty()){
					for(Map bean:safLed){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   Map m = new HashMap();
							m.put("codeName", "安全设施登记台账类别");
							m.put("itemValue", bean.get("ACCOUNT_TYPE").toString());
							String accountType=codeService.findCodeValueByMap(m).getItemText();
						   jo.put("accountType", accountType);//台账类型
						   Map m2 = new HashMap();
						   if(accountType.equals("预防事故设施台帐")){
							m2.put("codeName", "预防事故设施台帐");
							m2.put("itemValue", bean.get("CATEGORY").toString());
						   }else if(accountType.equals("控制事故设施台帐")){
							   m2.put("codeName", "控制事故设施台帐");
							   m2.put("itemValue", bean.get("CATEGORY").toString());
							   
						   }else if(accountType.equals("减少与消除事故影响设施台帐")){
							   m2.put("codeName", "减少与消除事故影响设施台帐");
							   m2.put("itemValue", bean.get("CATEGORY").toString());
						   }
						   String category=codeService.findCodeValueByMap(m2).getItemText();
						   jo.put("category", category);//类别
						   Map map = new HashMap();
							map.put("linkId",bean.get("LINK_ID"));
							map.put("mkType", "aqssdjtz");
							map.put("picType","aqssdjtzfj");
							DataBean beans = httpService.getPhotoListByType(map);
							String filepath = beans.getRname();
							jo.put("filePath", StringTools.NullToStr(filepath,""));//附件url
						   ja.add(jo);
					}
					br.setCode("0");
					br.setMessage("成功");
					br.setTotal(total+"");
					br.setPage(page+"");
					br.setContent(ja.toString());
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
