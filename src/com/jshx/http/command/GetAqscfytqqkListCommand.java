package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscfytqqk.entity.SecProFeeExt;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.KeyBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
/**
 * 获取安全生产费用提取情况列表接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetAqscfytqqkListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String companyId = obj.getString("companyId");//获取企业id
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("table", "SEC_PRO_FEE_EXT");
		paraMap.put("sqlId", "getListByTableAndCompanyId");
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		paraMap.put("companyId", companyId);

		List<Map<String, Object>> secProFeeExt=httpService.findListDataByMap(paraMap);
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("table", "SEC_PRO_FEE_EXT");
		paraMap2.put("sqlId", "getListByTableAndCompanyIdForCount");
		paraMap2.put("companyId", companyId);
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap2).get(0).get("TOTAL").toString());
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(secProFeeExt!=null&&!secProFeeExt.isEmpty()){
					for(Map bean:secProFeeExt){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.get("ROW_ID")?"":bean.get("ROW_ID"));//主键
						   jo.put("fee", null==bean.get("FEE_EXTRACT_FEE")?"":bean.get("FEE_EXTRACT_FEE"));//提取费用
						   jo.put("time",null==bean.get("FEE_EXTRACT_TIME")?"": new SimpleDateFormat("yyyy-MM-dd").format(bean.get("FEE_EXTRACT_TIME")));//提取时间
						   jo.put("remark",null==bean.get("FEE_EXTRACT_REMARK")?"":bean.get("FEE_EXTRACT_REMARK"));//备注
						   jo.put("fph",null==bean.get("FP_NUM")?"":bean.get("FP_NUM"));//发票号
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
