package com.jshx.http.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 获取询问笔录被询问人列表接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetXwblUserListCommand implements Command{
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private InstrumentsInfoService instrumentsInfoService = (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String caseId = obj.getString("caseId");//案件编号
		
		Map map = new HashMap();
		map.put("caseId", caseId);
		List<String> list = instrumentsInfoService.queryXwblUser(map);
		JSONArray ja = new JSONArray();
		try {
			for(String s:list){
				   JSONObject jo = new JSONObject();
				   jo.put("id", StringTools.NullToStr(s,""));//主键
				   jo.put("name", StringTools.NullToStr(s,""));//名称
				   ja.add(jo);
			}
			br.setCode("0");
			br.setMessage("成功");
			br.setContent(ja.toString());
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
