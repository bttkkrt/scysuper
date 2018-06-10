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
 * 获取操作规程列表接口
 * @author 周云琳 2015-10-15
 *
 */
public class GetCzgcListCommand implements Command{
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
		paraMap.put("table", "OPE_PRO");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> opePro=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "OPE_PRO");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(opePro!=null&&!opePro.isEmpty()){
					for(Map bean:opePro){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   jo.put("workName", null==bean.get("OPERATION_WORKSHOP_NAME")?"":bean.get("OPERATION_WORKSHOP_NAME"));//车间名称
						   jo.put("postName", null==bean.get("OPERATION_POSTNAME")?"":bean.get("OPERATION_POSTNAME"));//岗位名称
						   jo.put("code", null==bean.get("OPERATION_CODE")?"":bean.get("OPERATION_CODE"));//操作规程编号
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
