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
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;

/**
 * 获取粉尘作业场所管理列表接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetFczycsListCommand implements Command{
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
		paraMap.put("table", "DUS_WOR_MAN");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> dusWorMan=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "DUS_WOR_MAN");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(dusWorMan!=null&&!dusWorMan.isEmpty()){
					for(Map bean:dusWorMan){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   jo.put("workName", null==bean.get("WORKPLACE_NAME")?"":bean.get("WORKPLACE_NAME"));//作业场所名称
						   Map m = new HashMap();
						   m.put("codeName", "粉尘行业");
						   m.put("itemValue", bean.get("AGENCY_RESPONSIBLE"));
						   String agenResponse=codeService.findCodeValueByMap(m).getItemText();
						   jo.put("agenResponse", agenResponse);//所属行业
						   Map m1 = new HashMap();
						   m1.put("codeName", "粉尘种类");
						   m1.put("itemValue", bean.get("INDUSTRY_TYPE"));
						   String indTyoe=codeService.findCodeValueByMap(m1).getItemText();
						   jo.put("indTyoe", indTyoe);//粉尘种类
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
