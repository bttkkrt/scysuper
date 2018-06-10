package com.jshx.activiti.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.TaskQuery;
import org.openwebflow.util.ProcessDefinitionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.activiti.service.ActivityManageService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.docReview.entity.DocReview;
import com.jshx.docReview.service.DocReviewService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.service.ContentInformationsService;

@Service("activityManageService")
public class ActivityManageServiceImpl extends BaseServiceImpl implements ActivityManageService
{
	/**
	 * Dao类
	 */
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
	@Autowired
	protected TaskService taskService;	
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	protected IdentityService identityService;

	@Autowired
	private ProcessEngine _processEngine;
	
	@Autowired
	private DocReviewService docReviewService; 
	
	@Autowired
	private ContentInformationsService contentInformationsService;

	@Transactional(readOnly = true)
	@Override
	public String StartProcessInstance(String modelId, String workflowKey,String userId,Map<String, Object> variables ) {
		if(null != modelId){
		    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		    identityService.setAuthenticatedUserId(userId);
		    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workflowKey,modelId, variables);			
		return processInstance.getProcessInstanceId();
		}
		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public TaskQuery createUnsignedTaskQuery(String userId, String processDefKey) {
		TaskQuery taskCandidateUserQuery = taskService.createTaskQuery().processDefinitionKey(processDefKey)
				.taskCandidateUser(userId).orderByTaskCreateTime().desc();
		return taskCandidateUserQuery;
	}
	@Transactional(readOnly = true)
	@Override
	public TaskQuery createTodoTaskQuery(String userId, String processDefKey) {
		TaskQuery taskAssigneeQuery = taskService.createTaskQuery().processDefinitionKey(processDefKey)
				.taskAssignee(userId).orderByTaskCreateTime().desc();
		return taskAssigneeQuery;
		}
	@Transactional(readOnly = true)
	@Override
	public ProcessInstanceQuery createUnFinishedProcessInstanceQuery(
			String processDefKey) {
		ProcessInstanceQuery unfinishedQuery = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey(processDefKey).active().orderByProcessInstanceId().desc();
		return unfinishedQuery;
	}
	
	/**
	 * 跳转至指定活动节点
	 * 
	 * @param targetTaskDefinitionKey
	 * @throws Exception
	 */
	@Override
	public void jump(String targetTaskDefinitionKey, String _processId) throws Exception {
		TaskEntity currentTask = (TaskEntity) _processEngine.getTaskService().createTaskQuery()
				.processInstanceId(_processId).singleResult();
		jump(currentTask, targetTaskDefinitionKey);
	}
	
	/**
	 * 
	 * @param currentTaskEntity
	 *            当前任务节点
	 * @param targetTaskDefinitionKey
	 *            目标任务节点（在模型定义里面的节点名称）
	 * @throws Exception
	 */
	private void jump(final TaskEntity currentTaskEntity, String targetTaskDefinitionKey) throws Exception {
		final ActivityImpl activity = ProcessDefinitionUtils.getActivity(_processEngine,
				currentTaskEntity.getProcessDefinitionId(), targetTaskDefinitionKey);

		final ExecutionEntity execution = (ExecutionEntity) _processEngine.getRuntimeService().createExecutionQuery()
				.executionId(currentTaskEntity.getExecutionId()).singleResult();

		final TaskService taskService = _processEngine.getTaskService();

		// 包装一个Command对象
		((RuntimeServiceImpl) _processEngine.getRuntimeService()).getCommandExecutor()
				.execute(new Command<java.lang.Void>() {
					@Override
					public Void execute(CommandContext commandContext) {
						// 创建新任务
						execution.setActivity(activity);
						execution.executeActivity(activity);

						// 删除当前的任务
						// 不能删除当前正在执行的任务，所以要先清除掉关联
						currentTaskEntity.setExecutionId(null);
						taskService.saveTask(currentTaskEntity);
						taskService.deleteTask(currentTaskEntity.getId(), true);

						return null;
					}
				});
	}
	@Transactional(readOnly = true)
	@Override
	public HistoricProcessInstanceQuery createFinishedProcessInstanceQuery(
			String processDefKey) {
		HistoricProcessInstanceQuery finishedQuery = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processDefKey).finished().orderByProcessInstanceId().desc();
		return finishedQuery;
	}
	@Transactional(readOnly = true)
	@Override
	public void passProcess(String taskId, String remark,
			Map<String, Object> variables) {
		// 跳转节点为空，默认提交操作
				if (null != remark && !remark.isEmpty())
					taskService.addComment(taskId, null, remark);// remark为批注内容
				taskService.complete(taskId, variables);

	}
	@Transactional(readOnly = true)
	@Override
	public List<String> findAllCandidates(  String workFlowModelId,
			String deptCode) {
		Map <String,Object>paraMap = new HashMap<String,Object>();
		List<String> users  = new ArrayList<String>();
		DocReview docReview = docReviewService.getById(workFlowModelId);
		while (users.size()<=0 &&deptCode.length()>=3) {
			paraMap.put("deptCode", deptCode);
			paraMap.put("ids", docReview.getUserIds().split(","));
			  users =  contentInformationsService.findAllCandidates(paraMap);
			  deptCode = deptCode.substring(0,deptCode.length()-3);
		}
		return users;
	}
	@Transactional(readOnly = true)
	@Override
	public void endProcess(String processInstanceId) {
		System.err.println("endprocess！！！！！！！！————————————————————————————");
		// 获取流程定义的所有节点
				try {
					jump("endevent", processInstanceId);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	 
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	
}
