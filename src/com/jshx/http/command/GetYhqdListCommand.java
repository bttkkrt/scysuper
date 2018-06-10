package com.jshx.http.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

/**
 * 隐患清单、待签收隐患、待处理隐患列表接口
 * GY-UPDATE 2015-02-11
 *
 */

public class GetYhqdListCommand implements Command
{
	private YhqdService  yhqdService= (YhqdService) SpringContextHolder.getBean("yhqdService");
	private UserService  userService= (UserService) SpringContextHolder.getBean("userService");
	private CompanyService  companyService= (CompanyService) SpringContextHolder.getBean("companyService");
	private TaskService taskService = (TaskService) SpringContextHolder.getBean("taskService");
	protected RuntimeService runtimeService = (RuntimeService) SpringContextHolder.getBean("runtimeService");
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//  
		String listType = json.getString("listType");//  
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Pagination pagination = new Pagination(s, l);
		User user = userService.findUserById(userId);
		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		try
		{
		if(listType.equals("list")){  //隐患列表
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(user.getDeptCode().startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
				CompanyBackUp company = companyService.getCompanyByLoginId(user.getLoginId());
				paraMap.put("qyId", company.getCompanyId());
			}
			
			pagination = yhqdService.findByPage(pagination, paraMap);
			List<Yhqd> data =pagination.getListOfObject();
			for (Yhqd yhqd : data) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("companyname", yhqd.getBasic().getCompany().getCompanyname());
				map.put("yhContent", yhqd.getYhContent());
				map.put("yhId", yhqd.getId());
				map.put("checkTime", DateUtil.getDate(yhqd.getChecktime(), DateUtil.DATE_FORMAT_YYYYMMDD));
				map.put("task_id", "");
				map.put("task_type", "");
				dataList.add(map);
			}
		}else if(listType.equals("list_qianshou")){
			TaskQuery taskToClaimQuery = taskService.createTaskQuery().processDefinitionKey("checkTrouble").taskCandidateUser(user.getId()).active().orderByTaskId().desc();
			List<Task> taskListToClaim = taskToClaimQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
	        for(Task task : taskListToClaim){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	if(null != processInstance.getBusinessKey()){
	        		String	moduleId = processInstance.getBusinessKey();
	        		Yhqd yhqd = yhqdService.getById(moduleId);
	        		if(yhqd!=null){
	    				map.put("companyname", yhqd.getBasic().getCompany().getCompanyname());
	    				map.put("yhContent", yhqd.getYhContent());
	    				map.put("yhId", yhqd.getId());
	    				map.put("checkTime", DateUtil.getDate(yhqd.getChecktime(), DateUtil.DATE_FORMAT_YYYYMMDD));
	    				map.put("task_id", task.getId());
	    				map.put("task_type", "待签收");
	    				dataList.add(map);
	        		}
	        	}
	        }
		}else if(listType.equals("list_chuli")){
			TaskQuery claimedTaskQuery = taskService.createTaskQuery().processDefinitionKey("checkTrouble").taskAssignee(user.getId()).active().orderByTaskId().desc();
			List<Task> claimedTaskList = claimedTaskQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
	        for(Task task : claimedTaskList){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	if(null != processInstance.getBusinessKey()){
	        		String	moduleId = processInstance.getBusinessKey();
	        		Yhqd yhqd = yhqdService.getById(moduleId);
	        		if(yhqd!=null){
	    				map.put("companyname", yhqd.getBasic().getCompany().getCompanyname());
	    				map.put("yhContent", yhqd.getYhContent());
	    				map.put("yhId", yhqd.getId());
	    				map.put("checkTime", DateUtil.getDate(yhqd.getChecktime(), DateUtil.DATE_FORMAT_YYYYMMDD));
	    				map.put("task_id", task.getId());
	    				map.put("task_type", task.getName());
	    				dataList.add(map);
	        		}
	        	}
	        }
		}
		 pagination.setList(dataList);
			if(null != pagination.getList() && pagination.getList().size() > 0){
				bd.setCode("0");
				bd.setMessage("查询成功");
				bd.setTotal(pagination.getTotalCount()+"");
				bd.setPage(pagination.getPageNumber()+"");
				bd.setContent(JSONArray.fromObject(pagination.getList()).toString());
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	
}
