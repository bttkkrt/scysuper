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

/**
 * 获取消防设施分布图列表接口
 * @author 周云琳 2015-10-15
 *
 */
public class GetXfssfbtListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String companyId = obj.getString("companyId");//获取用户的id
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("table", "FIR_MAP");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> firMap=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "FIR_MAP");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(firMap!=null&&!firMap.isEmpty()){
					for(Map bean:firMap){
						   JSONObject jo = new JSONObject();
						   jo.put("id",null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   jo.put("facName", null==bean.get("FACILITY_NAME")?"":bean.get("FACILITY_NAME"));//设施名称
						   Map map = new HashMap();
							map.put("linkId",bean.get("LINK_ID"));
							map.put("mkType", "xfssfbt");
							map.put("picType","xfssfbtfj");
							DataBean bean1 = httpService.getPhotoListByType(map);
							jo.put("filePath",null==bean1.getRname()?"":bean1.getRname());//附件url
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
