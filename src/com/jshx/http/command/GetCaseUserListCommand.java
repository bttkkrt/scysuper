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
 * 获取案件承办人列表接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetCaseUserListCommand implements Command{
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private InstrumentsInfoService instrumentsInfoService = (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String type = obj.getString("type");//用于区分获取类型 11：出席人员 安监局所有人员 ，其余均为监察大队队员 8 主持人  9 汇报人
		
		User uu = userService.findUserById(userId);
		Map map = new HashMap();
		map.put("userId", uu.getId());
		List<User> list = new ArrayList<User>();
		if(type.equals("11") || type.equals("8") || type.equals("9"))
		{
			list = instrumentsInfoService.getAllUsersByMap(map);
		}
		else
		{
			list = caseInfoService.queryCaseUserList(map);
		}
		JSONArray ja = new JSONArray();
		try {
			for(User user:list){
				   JSONObject jo = new JSONObject();
				   jo.put("id", StringTools.NullToStr(user.getId().toString(),""));//主键
				   jo.put("name", StringTools.NullToStr(user.getDisplayName(),""));//名称
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
