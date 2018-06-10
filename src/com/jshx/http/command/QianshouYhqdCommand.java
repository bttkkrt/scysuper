package com.jshx.http.command;

import net.sf.json.JSONObject;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;

/**
 * 上报现场检查隐患清单 zhangzhouhua 2016-08-02
 */

public class QianshouYhqdCommand implements Command {

	private TaskService taskService = (TaskService) SpringContextHolder.getBean("taskService");
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new SummaryBean();
		String userId = json.getString("userId");//
		String taskId = json.getString("taskId");

		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (null != task) {
				taskService.claim(task.getId(), userId);
				bd.setCode("0");
				bd.setMessage("签收成功");
				bd.setContent("");
			} else {
				bd.setCode("1");
				bd.setMessage("签收失败");
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("签收失败");
		}
		return bd;
	}

}
