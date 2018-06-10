package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.Information;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.service.ReportWorkService;
/**
 * 获取工作上报列表
 * @author hanxc 20150403
 */
public class GetReportWorkListCommand implements Command{
	private ReportWorkService reportWorkService = (ReportWorkService)SpringContextHolder.getBean("reportWorkService");
	 
	@SuppressWarnings("unchecked")
	@Override
		public BaseResponse execute(JSONObject obj) {
		 	SummaryBean bd = new  SummaryBean();
		    String userId = obj.getString("userId").toString();
			int pageSize=Integer.parseInt(obj.getString("limit").toString());
			int PageNum=(Integer.parseInt(obj.getString("start").toString())-1)*pageSize;
			String workTitle = obj.getString("workTitle").toString();
	 		Map map=new HashMap();
	 		map.put("listType", "1");
	 		map.put("createUserID", userId);
			if(workTitle != null && !"".equals(workTitle))
				 map.put("workTitle", "%"+workTitle+"%");
			Pagination pagination = new Pagination(PageNum, pageSize);
			pagination=reportWorkService.findByPage(pagination, map);
	 		List<ReportWork> reportWorkList=pagination.getListOfObject();
			bd.setTotal(new Integer(pagination.getTotalCount()).toString());//总条数
			JSONArray ja = new JSONArray();
			for(ReportWork rw:reportWorkList){
				JSONObject json = new JSONObject();
				json.put("id", rw.getId());
				json.put("workTitle", StringTools.NullToStr(rw.getWorkTitle(),""));
				json.put("time", StringTools.NullToStr(rw.getTime(),""));
				json.put("userName", StringTools.NullToStr(rw.getUserName(),""));
				ja.add(json.toString());
			}
			bd.setContent(ja.toString());
	 		bd.setCode("0");
			bd.setMessage("查询成功");
	 		if(reportWorkList.size()<=0){
	 			bd.setCode("1");
				bd.setMessage("无查询结果");
	 		}
	 		return bd;
	 	}
}
