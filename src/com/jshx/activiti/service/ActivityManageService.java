package com.jshx.activiti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.TaskQuery;

import com.jshx.core.base.service.BaseService;

public interface ActivityManageService extends BaseService
{
	/**
	 * 
	 * @param 对象id  ,开启流程人员id
	 * 
	 * return  processInstanceId
	 */
	public String  StartProcessInstance(String modelId, String workflowKey, String userId,Map<String, Object> variables);
	
	
	/**
	 * 获取未签收的任务查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	public TaskQuery createUnsignedTaskQuery(String userId, String processDefKey);

	/**
	 * 获取正在处理的任务查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	public TaskQuery createTodoTaskQuery(String userId, String processDefKey);

	/**
	 * 获取未经完成的流程实例查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	public ProcessInstanceQuery createUnFinishedProcessInstanceQuery(String processDefKey);

	/**
	 * 获取已经完成的流程实例查询对象
	 * 
	 * @param userId
	 *            用户ID
	 */
	public HistoricProcessInstanceQuery createFinishedProcessInstanceQuery(String processDefKey);

	/**
	 * 审批通过
	 * 
	 * @param taskId
	 * @param m
	 */
	public void passProcess(String taskId, String remark, Map<String, Object> variables);
	/**
	 * zhangzh  
	 * 2015-12-30
	 * 获取下一步审批人
	 * 
	 * 	 * @param key
	 * @param userTaskId
	 */
	public List<String> findAllCandidates(  String workFlowModelId, String deptCode);
	
	
	public void endProcess(String processInstanceId);


	void jump(String targetTaskDefinitionKey, String _processId)
			throws Exception;


	 
}
