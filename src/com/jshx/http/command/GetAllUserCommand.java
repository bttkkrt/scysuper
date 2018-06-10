package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.service.ContentInformationsService;

/**
 * 选择人员接口
 *	hanxc 20150401
 */

public class GetAllUserCommand implements Command
{
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private ContentInformationsService contentInformationsService = (ContentInformationsService) SpringContextHolder.getBean("contentInformationsService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		JSONObject obj = new JSONObject();

		//首先获取组织部门树
		JSONArray deptArr = new JSONArray();
		Map<String, Object> paraMap = new HashMap<String, Object>(); 
		List<Dept> deptList = contentInformationsService.getAllDepartByMap(paraMap);
		for (Dept dept : deptList)
		{
			JSONObject deptJson = new JSONObject();
			deptJson.put("deptName", dept.getDeptName());
			deptJson.put("deptCode", dept.getId());
			deptArr.add(deptJson);
		}
		obj.put("deptList", deptArr);
		
		//根据执法人员角色ID查找 用户角色集合
		JSONArray userArr = new JSONArray();
		List<User> userList = userService.findAllUsersByDept("");
		for (User user: userList){
			JSONObject userJson = new JSONObject();
			userJson.put("userId", user.getId());
			userJson.put("userName", user.getDisplayName());
			userJson.put("sortSq", user.getSortSq());
			userJson.put("deptCode", user.getDeptCode());
			userArr.add(userJson);
		}
		obj.put("userList", userArr);
 		bd.setContent(obj.toString());
 		bd.setCode("0");
		bd.setMessage("查询成功");
 		return bd;
	}
}
