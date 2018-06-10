package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class GetUndoneInspectNumCommand  implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private SafeInspectDistributeService safeInspectDistributeService = (SafeInspectDistributeService) SpringContextHolder.getBean("safeInspectDistributeService");
	

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		String taskStatus = json.getString("taskStatus");//获取检查状态 0=初始化任务，1=某天某人未检查任务  2:安全检查项全部符合  3：安全检查项存在不符合项
		String inspectNum = json.getString("inspectNum");//获取检查次数
		String rootId = json.getString("rootId");//源任务ID
		String distributeId = json.getString("distributeId");//
		String listFlag = json.getString("listFlag");//
		String serialNum = json.getString("serialNum");//任务流水号
		
		//新增查询条件 GY-UPDATE 2014-12-19
		String taskTime = json.getString("taskTime");//检查时间
		String checkName = json.getString("checkName");//检查人名称
		String status = json.getString("status");//检查状态
		
		try {
			checkName = new String(checkName.getBytes("ISO-8859-1"),"utf-8");  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map map = new HashMap();
		//map.put("start", s);
		//map.put("limit", l);
		
		User user = userService.findUserById(userId);
		boolean gwFlag = false;//岗位人员标识
		//如果用户仅为“岗位人员”角色
		List<UserRight> userRightList = user.getUserRoles();
		if(null != userRightList && userRightList.size() == 1){
			String rolecode = userRightList.get(0).getRole().getRoleCode();
			if(rolecode.equals("A37")){
				gwFlag=true;
			}
		}
		if(gwFlag){//获取该人员安全检查列表
			map.put("userId", userId);
			map.put("personnelDeptCode", user.getDeptCode()+"%");
		}else{//获取该用户所在部门下所有安全检查列表
			map.put("personnelDeptCode", user.getDeptCode()+"%");
		}
		
		if("1".equals(taskStatus)){//获取未检查任务列表
			map.put("taskStatus", taskStatus);
		}
		if("2".equals(taskStatus)){//获取已检查任务列表
			map.put("historyFlag", "true");//获取检查历史查询标识
		}
		if(!"".equals(serialNum)){//获取流水号查询条件
			map.put("serialNum", serialNum);
		}
		if("1".equals(inspectNum)){//初始化总检查任务列表
			map.put("inspectNum", inspectNum);
			map.put("totalFlag", "true");
		}
		/*
		 * 根据源任务ID和任务日期来唯一标识一组检查任务（一个任务检查多次）
		 */
		if(!"".equals(rootId)){//源任务ID
			map.put("rootId", rootId);
		}
		if(null != distributeId && !"".equals(distributeId)){//任务日期
			SafeInspectDistribute sd = safeInspectDistributeService.getById(distributeId);
			map.put("taskTime", sd.getTaskTime());
		}
		
		
		//默认查询当天的数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date(); 
		taskTime = sdf.format(now);
		
		//新增查询条件 
		if(null != taskTime && !"".equals(taskTime)){
			map.put("taskTime", taskTime.trim() + "%");
		}
		if(null != checkName && !"".equals(checkName)){
			map.put("checkName", "%" + checkName + "%");
		}
		if(null != status && !"".equals(status)){
			map.put("status", status);
		}
		//GY-UPDATE 2014-12-19
		
		//添加一个参数：inspectNum=1 
		//添加一个参数:taskStatus=1未检查 taskStatus>1已检查
		
		List<Map<String,String>> list = httpService.getInspectTaskList(map);//获取安全检查任务列表 hanxc 2014-11-13

		int total = httpService.getInspectTaskListCountByMap(map);//获取安全检查任务数 hanxc 2014-11-13
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					int undoneNum = 0;
					JSONObject jo = new JSONObject();
					for(Map<String, String> tempMap : list){
					   
					   if("1".equals(inspectNum)){//初始化总检查任务列表
						   //获取任务总次数
						   SafeInspectDistribute safeInspectDistribute = safeInspectDistributeService.getById(tempMap.get("rootId"));
						   //获取已检查次数
						   Map tempmap = new HashMap();
						   SafeInspectDistribute sd = safeInspectDistributeService.getById(tempMap.get("distributeId"));
						   tempmap.put("rootId", sd.getRootId());
						   tempmap.put("taskTime", sd.getTaskTime());
						   tempmap.put("start", s);
						   tempmap.put("limit", l);
						   if(gwFlag){
							   tempmap.put("userId", userId);
						   }
						   tempmap.put("personnelDeptCode", userService.findUserById(userId).getDeptCode()+"%");
						   tempmap.put("historyFlag", "true");
						   tempmap.put("serialNum", sd.getSerialNum());
						   int hisyoryNum = httpService.getInspectTaskListCountByMap(tempmap);//获取安全检查任务数 hanxc 2014-11-13
						   
						   //待办处理 GY-UPDATE 2015-01-06 
						   int totalNum = 0;
						   try
							{
							   totalNum = Integer.parseInt(safeInspectDistribute.getInspectNum());
							}
							catch (Exception e)
							{
								bd.setCode("1");
								bd.setMessage("totalNum数据转换出错！");
								e.printStackTrace();
							}
							//待办数目自加
							if(hisyoryNum < totalNum)
							{
								undoneNum++;
							}
					   }
					   
					   
					}
					jo.put("undoneNum",undoneNum);
					ja.add(jo);
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
				}else{
					bd.setCode("1");
					bd.setMessage("无查询结果");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
}
