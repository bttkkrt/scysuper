package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.jcjl.entity.InsRec;
import com.jshx.jcjl.service.InsRecService;

/**
 * 检查记录列表接口
 *@author 陆婷 2015-11-5
 *
 */
public class GetJcjlListCommand implements Command{
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private InsRecService insRecService = (InsRecService) SpringContextHolder.getBean("insRecService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String caseId = obj.getString("caseId");//案件id
		String instrumentType = obj.getString("instrumentType");//文书类型
		
		Map map = new HashMap();
		CaseInfo ca = caseInfoService.getById(caseId);
		map.put("caseSource", ca.getCaseSource());
		map.put("instrumentType", instrumentType);
		
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		List<InsRec> list = insRecService.getInsRecListByUserAndType(map, s, l);
		int total= insRecService.getInsRecListSizeByUserAndType(map);
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(InsRec bean:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(bean.getId(),""));//主键
						   jo.put("inspectionRecord", StringTools.NullToStr(bean.getInspectionRecord(),""));//检查记录
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
