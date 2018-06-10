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
import com.jshx.http.bean.Information;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.infomation.service.ContentInformationsService;

public class InformationCommand implements Command{
	private ContentInformationsService contentInformationsService = (ContentInformationsService)SpringContextHolder.getBean("contentInformationsService");

	@SuppressWarnings("unchecked")
	@Override
		public BaseResponse execute(JSONObject obj) {
		 	SummaryBean bd = new  SummaryBean();
		    String userId = obj.getString("userId").toString();
			int pageSize=Integer.parseInt(obj.getString("limit").toString());
			int PageNum=(Integer.parseInt(obj.getString("start").toString())-1)*pageSize;
			String title = obj.getString("title").toString();
	 		Map map=new HashMap();
	 		map.put("createUserID", userId);
			if(title != null && !"".equals(title))
		    {
				 	map.put("infoTitle", "%"+title+"%");
		 	}
			List<Information> notice=contentInformationsService.findAllInfo(map,PageNum,pageSize);
	 		List<Information> notices=contentInformationsService.findAllInfos(map);
			double total  = Math.ceil(notices.size()*1.0/pageSize);
	 		String totalNum = (int)total + "";
	    	bd.setPage(totalNum);//总页数
			bd.setTotal(notices.size()+"");//总条数
			JSONArray ja = new JSONArray();
			for(Information c:notice)
			{
				JSONObject json = new JSONObject();
				json.put("id", c.getId());
				json.put("infoTitle", StringTools.NullToStr(c.getInfoTitle(),""));
				json.put("deptName", StringTools.NullToStr(c.getDeptName(),""));
				json.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
				json.put("readed", StringTools.NullToStr(c.getReaded(),""));
				json.put("userName", StringTools.NullToStr(c.getUserName(),""));
				ja.add(json.toString());
			}
			bd.setContent(ja.toString());
	 		bd.setCode("0");
			bd.setMessage("查询成功");
	 		if(notice.size()<=0){
	 			bd.setCode("1");
				bd.setMessage("无查询结果");
	 		}
	 		return bd;
	 	}
}
