package com.jshx.http.command;

import java.text.SimpleDateFormat;
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
 * 获取调岗记录列表接口
 * @author 周云琳 2015-10-14
 *
 */
public class GetTgjlListCommand implements Command{
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
		paraMap.put("table", "PRO_REC");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> proRec=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "PRO_REC");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(proRec!=null&&!proRec.isEmpty()){
					for(Map bean:proRec){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   jo.put("companyName", null==bean.get("COMPANY_NAME")?"":bean.get("COMPANY_NAME"));//企业名称
						   jo.put("time", null==bean.get("JSHX_TIME")?"":new SimpleDateFormat("yyyy-MM-dd").format(bean.get("JSHX_TIME")));//调岗时间
						   jo.put("cause", null==bean.get("CAUSING")?"":bean.get("CAUSING"));//事由
						   jo.put("name", null==bean.get("JSHX_NAME")?"":bean.get("JSHX_NAME"));//姓名
						   jo.put("identification", null==bean.get("IDENTIFICATION")?"":bean.get("IDENTIFICATION"));//主键
						   jo.put("remark", null==bean.get("REMARK")?"":bean.get("REMARK"));//备注
						   Map map = new HashMap();
							map.put("linkId",bean.get("LINK_ID"));
							map.put("mkType", "dgjl");
							map.put("picType","dgjlfj");
							DataBean bean1 = httpService.getPhotoListByType(map);
							jo.put("filePath",null==bean1.getRname()?"":bean1.getRname());
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
