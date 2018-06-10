package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
/**
 * 获取安全生产费用使用台账列表接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetAqscfysytzListCommand implements Command{
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String companyId = obj.getString("companyId");//获取用户的id
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int l = Integer.parseInt(pageSize);
		
		paraMap.put("companyId", companyId);
		
		List<SecProFeeAcc> list1 = httpService.getSecProFeeAccListByMap(paraMap);
		
		paraMap.put("start", pageNum);
		paraMap.put("limit", pageSize);
		
		List<SecProFeeAcc> list2 = httpService.getSecProFeeAccListByMap(paraMap);

		int total=list1.size();
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list2!=null&&!list2.isEmpty()){
					for(SecProFeeAcc bean:list2){
						   JSONObject jo = new JSONObject();
						   jo.put("id", null==bean.getId()?"":bean.getId());//主键
						   jo.put("amount", null==bean.getFeeAccountAmount()?"":bean.getFeeAccountAmount());//支出金额
						   jo.put("month", null==bean.getFeeAccountMonth()?"":bean.getFeeAccountMonth());//使用月份
						   jo.put("remark",null==bean.getFeeAccountRemark()?"":bean.getFeeAccountRemark());//备注
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
