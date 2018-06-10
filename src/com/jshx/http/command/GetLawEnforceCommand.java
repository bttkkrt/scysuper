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
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;

/**
 * 选择执法人员接口
 *	GY-UPDATE 2015-02-11
 */

public class GetLawEnforceCommand implements Command
{
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		JSONObject obj = new JSONObject();

		//首先获取组织部门树
		JSONArray deptArr = new JSONArray();
		Map<String, Object> paraMap = new HashMap<String, Object>(); 
		List<Department> deptList = deptService.findAllDept(paraMap);
		for (Department dept : deptList)
		{
			JSONObject deptJson = new JSONObject();
			deptJson.put("deptName", dept.getDeptName());
			deptJson.put("deptCode", dept.getDeptCode());
			deptArr.add(deptJson);
		}
		obj.put("deptList", deptArr);
		
		//根据执法人员角色ID查找 用户角色集合
		JSONArray userArr = new JSONArray();
		List<UserRight> userRightList = userService.findByRole("8a8184ca4b78635a014b787b97a00005");
		for (UserRight userRight : userRightList)
		{
			User user = userRight.getUser();
			JSONObject userJson = new JSONObject();
			userJson.put("userId", user.getId());
			userJson.put("userName", user.getDisplayName());
			userJson.put("sortSq", user.getSortSq());
			userJson.put("deptCode", user.getDeptCode());
			userJson.put("idNum", user.getIdNum() != null ? user.getIdNum() : "");
			userArr.add(userJson);
		}
		obj.put("userList", userArr);
 		bd.setContent(obj.toString());
 		bd.setCode("0");
		bd.setMessage("查询成功");
 		return bd;
	}
}
