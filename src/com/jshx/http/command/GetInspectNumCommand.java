package com.jshx.http.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.UserService;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class GetInspectNumCommand  implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private SafeInspectDistributeService safeInspectDistributeService = (SafeInspectDistributeService) SpringContextHolder.getBean("safeInspectDistributeService");
	

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String taskTime = json.getString("taskTime");//检查时间
		String dateflag = json.getString("dateflag");//日期查询状态 year查询年份 month查询月份
		User user = userService.findUserById(userId);
		String deptCode = user.getDeptCode();
		
		JSONObject contentObj = new JSONObject();
		Map map = new HashMap();
		
		//安全任务检查
		map.put("deptCode", deptCode+"%");
//		if (null != deptCode && deptCode.length() >= 9)
//		{
//		}
		map.put("taskTime", taskTime);
		map.put("dateflag", dateflag);
		
		//如果用户仅为“岗位人员”角色
		List<UserRight> userRightList = user.getUserRoles();
		if(null != userRightList && userRightList.size() == 1){
			String rolecode = userRightList.get(0).getRole().getRoleCode();
			if(rolecode.equals("A37")){
				map.put("personnel", user.getId());
			}
		}
		
		List<Map<String,String>> jcList = new ArrayList<Map<String,String>>();
		if ("month".equals(dateflag))
		{
			jcList = httpService.getInspectTaskNumByMonth(map);//按月获取安全检查任务待办和总数
		}
		else if ("year".equals(dateflag))
		{
			jcList = httpService.getInspectTaskNumByYear(map);//按年获取安全检查任务待办和总数
		}
		
		if(jcList!=null&&!jcList.isEmpty()){
			JSONArray jcArr = new JSONArray();
			for(Map<String, String> tempMap : jcList){
				JSONObject jo = new JSONObject();
				jo.put("sj",tempMap.get("SJ"));
				jo.put("dbnum",tempMap.get("DB"));
				jo.put("ybnum",tempMap.get("YB"));
				jcArr.add(jo);
			}
			contentObj.put("aqjc", jcArr.toArray());
		}
		
		//隐患查询
		map.clear();
		map.put("jcsj", taskTime.trim() + "%");
		map.put("dateflag", dateflag);
		if (deptCode.startsWith("002006"))
		{
			// 乡镇安监中队
			if (deptCode.length() == 9)
			{
				map.put("deptCode", "%" + deptCode + "%");
			}
			else
			{
				map.put("szc", deptCode);
			}
		}
		else if (deptCode.startsWith("002001"))
		{// 部门如果是危化科
			map.put("whp", "1");
		}
		else if (deptCode.startsWith("002002"))
		{// 部门如果是职业健康科
			map.put("zyjk", "1");
		}
		else if (deptCode.startsWith("002004"))
		{// 部门是综合科
			map.put("yhbz", "1");
		}
		else if (deptCode.startsWith("002009"))
		{
			map.put("creatorDeptCode", deptCode + "%");
		}
		//隐患处理
		map.put("mqzt", "0");
		List<Map<String,String>> yhclList = httpService.getZcyhNumByMap(map);
		JSONArray yhclArr = new JSONArray();
		if(yhclList!=null&&!yhclList.isEmpty()){
			for(Map<String, String> tempMap : yhclList){
				JSONObject jo = new JSONObject();
				jo.put("sj",tempMap.get("SBSJ"));
				jo.put("sjnum",tempMap.get("SJNUM"));
				yhclArr.add(jo);
			}
			contentObj.put("yhcl", yhclArr.toArray());
		}
		
		//隐患上报
		map.put("mqzt", "1");
		List<Map<String,String>> yhsbList = httpService.getZcyhNumByMap(map);
		JSONArray yhsbArr = new JSONArray();
		if(yhsbList!=null&&!yhsbList.isEmpty()){
			for(Map<String, String> tempMap : yhsbList){
				JSONObject jo = new JSONObject();
				jo.put("sj",tempMap.get("SBSJ"));
				jo.put("sjnum",tempMap.get("SJNUM"));
				yhsbArr.add(jo);
			}
			contentObj.put("yhsb", yhsbArr.toArray());
		}
		
		bd.setCode("0");
		bd.setMessage("查询成功");
		bd.setContent(contentObj.toString());
		return bd; 
	}
}
