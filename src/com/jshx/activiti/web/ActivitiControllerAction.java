/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-5-10          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipInputStream;

import net.sf.json.JSONArray;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.activiti.service.BusinessEntityService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;

/**  
 * Activiti工作流控制流转通用类
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-5-10 上午11:21:00 
 * 
 */
public class ActivitiControllerAction extends BaseAction {
	private static final long serialVersionUID = -7400720794719784874L;
	
/*	@Autowired
	SpringProcessEngineConfiguration processEngine;*/
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
	@Autowired
	protected TaskService taskService;	
	
	@Autowired
	protected FormService formService;
	
	@Autowired
	protected IdentityService identityService;
	
	@Autowired
	private HistoryService historyService;
	
 
	
	@Autowired
	private BusinessEntityService businessEntityService;

	
	private Pagination pagination;
	
	private File file;
	
	private String fileFileName;
	
	private File[] formkeyFile;
	
	private String[] formkeyFileFileName;
	
	private String taskId;
	
	private Object formContent;
	
	private String processDefinitionId;
	
	private String modelName;
	
	private String modelKey;
	
	private String modelDesc;

	
	private String processDefKey;
	
	private String processDefName;
	
	private String[] ids;

	private String modelId;
	
	private String[] formkeyArray;
	
	private String businessEntityClassName;
	
	private String businessEntityPrefix;
	
	/**
	 * Activiti流程定义列表
	 * 
	 * @author YuWeitao
	 * @return
	 */	
	public void findProcessDefList(){
		try{
			pagination = new Pagination(super.getRequest());
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().desc();
			if(null != this.processDefKey && !"".equalsIgnoreCase(this.processDefKey)){
				processDefinitionQuery = processDefinitionQuery.processDefinitionKeyLike("%"+this.processDefKey+"%");
			}
			if(null != this.processDefName && !"".equalsIgnoreCase(this.processDefName)){
				processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike("%"+this.processDefName+"%");
			}
			List<ProcessDefinition> processDefList = processDefinitionQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(ProcessDefinition processDefinition : processDefList){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
	        	map.put("id", processDefinition.getId());
	        	map.put("deploymentId", processDefinition.getDeploymentId());
	        	map.put("deploymentTime", deployment.getDeploymentTime());
	        	map.put("key", processDefinition.getKey());
	        	map.put("name", processDefinition.getName());
	        	map.put("category", processDefinition.getCategory());
	        	map.put("resourceName", processDefinition.getResourceName());
	        	map.put("version", processDefinition.getVersion());
	        	map.put("description", processDefinition.getDescription());
	        	map.put("hasStartFormKey", processDefinition.hasStartFormKey());
	        	map.put("isSuspended", processDefinition.isSuspended());
	        	map.put("diagramResourceName", processDefinition.getDiagramResourceName());
	        	
	        	mapList.add(map);
	        }
	        
			StringBuffer data = new StringBuffer("{\n");
			data.append("  \"total\":").append(processDefinitionQuery.count()).append(",\n");
			data.append("  \"rows\":\n");
			
			JSONArray json = JSONArray.fromObject(mapList);
			data.append(json.toString());
			data.append("  \n").append("}");

			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
	}
	
	/**
	 * Activiti流程定义部署
	 * 
	 * @author YuWeitao
	 * @return
	 */
	public String deploy(){
	    try {
			FileInputStream fileInputStream = new FileInputStream(this.file);
			String extension = FilenameUtils.getExtension(this.fileFileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				repositoryService.createDeployment().addZipInputStream(zip).deploy();
			}else {
				repositoryService.createDeployment().addInputStream(this.fileFileName, fileInputStream).deploy();
			}
	    } catch (Exception e) {
	    	logger.error("error on deploy process, because of file input stream", e);
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
	    }
	    
		return RELOAD;
	}
	
	/**
	 * 删除已部署的流程，级联删除流程实例
	 * 
	 * @author YuWeitao
	 */
	public void deleteProcessDef(){
		if(null != ids){
			try{
				for(String deploymentId : ids)
					//Deletes the given deployment and cascade deletion to process instances, history process instances and jobs.
					repositoryService.deleteDeployment(deploymentId, true);	
				getResponse().getWriter().println("{\"result\":true}");
			}catch(Exception e){
		    	e.printStackTrace();
				BasalException be = new BasalException(BasalException.ERROR, e);
				throw be;
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":false}");
			}catch(Exception e){
		    	e.printStackTrace();
				BasalException be = new BasalException(BasalException.ERROR, e);
				throw be;
			}
		}
	}
	
	/**
	 * 挂起流程；被挂起的流程不能再创建流程实例
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 */
	public void suspendProcessDefinition() throws IOException{
		try {
			repositoryService.suspendProcessDefinitionById(this.processDefinitionId, false, null);
			this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
	}
	
	/**
	 * 激活流程
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 */
	public void activateProcessDefinition() throws IOException{
		try {
			repositoryService.activateProcessDefinitionById(this.processDefinitionId, false, null);
			this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
	}	
	
	/**
	 * 待签收任务列表
	 * 
	 * @author YuWeitao
	 * @return
	 */
	public void findToClaimTaskList(){
		try{
			pagination = new Pagination(super.getRequest());
			TaskQuery taskToClaimQuery = taskService.createTaskQuery().processDefinitionKey("doReview").taskCandidateUser(getLoginUserId()).active().orderByTaskId().desc();
			List<Task> taskListToClaim = taskToClaimQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(Task task : taskListToClaim){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	map.put("id", task.getId());
	        	map.put("name", task.getName());
	        	map.put("createTime", task.getCreateTime());
	        	map.put("description", task.getDescription());
	        	map.put("dueDate", task.getDueDate());
	        	map.put("owner", task.getOwner());
	        	
	        	mapList.add(map);
	        }
	        
			StringBuffer data = new StringBuffer("{\n");
			data.append("  \"total\":").append(taskToClaimQuery.count()).append(",\n");
			data.append("  \"rows\":\n");
			
			JSONArray json = JSONArray.fromObject(mapList);
			data.append(json.toString());
			data.append("  \n").append("}");	
			
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
	}
	
	/**
	 * 签收任务
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 */
	public void claimTask() throws IOException{
		try {
			Task task = taskService.createTaskQuery().taskId(this.taskId).singleResult();
			if(null != task){
				taskService.claim(task.getId(), this.getLoginUserId());
				this.getResponse().getWriter().println("{\"result\":true}");
			}else{
				this.getResponse().getWriter().println("{\"result\":false}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}			
	}
	
	/**
	 * 办理中任务列表
	 * 
	 * @author YuWeitao
	 * @return
	 */
	public void findClaimedTaskList(){
		try{
			pagination = new Pagination(super.getRequest());
			TaskQuery claimedTaskQuery = taskService.createTaskQuery().processDefinitionKey("doReview").taskAssignee(getLoginUserId()).active().orderByTaskId().desc();
			List<Task> claimedTaskList = claimedTaskQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(Task task : claimedTaskList){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	Date claimTime = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult().getClaimTime();
	        	map.put("id", task.getId());
	        	map.put("name", task.getName());
	        	map.put("createTime", task.getCreateTime());
	        	map.put("claimTime", claimTime);
	        	map.put("description", task.getDescription());
	        	map.put("dueDate", task.getDueDate());
	        	map.put("owner", task.getOwner());
	        	
	        	mapList.add(map);
	        }
	        
			StringBuffer data = new StringBuffer("{\n");
			data.append("  \"total\":").append(claimedTaskQuery.count()).append(",\n");
			data.append("  \"rows\":\n");
			
			JSONArray json = JSONArray.fromObject(mapList);
			data.append(json.toString());
			data.append("  \n").append("}");		
			
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;			
		}
	}
	
	/**
	 * 外部表单流程待办任务列表
	 * 
	 * @author YuWeitao
	 */
	public void findTodoTaskListFormKey(){
		pagination = new Pagination(super.getRequest());
	    List<Task> tasks = new ArrayList<Task>();
	    long itemCount = 0l;
	    
	    List<ProcessDefinition> processDefList = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().desc().list();
	    for(ProcessDefinition def : processDefList){
	    	if(def.hasStartFormKey()){//外部表单启动
	    		//待签收
	    		TaskQuery taskToClaimQuery = taskService.createTaskQuery().processDefinitionId(def.getId()).taskCandidateUser(this.getLoginUserId())
														.active().orderByTaskId().desc();
	    		List<Task> toClaimTaskList = taskToClaimQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());	
	    		tasks.addAll(toClaimTaskList);
	    		itemCount += taskToClaimQuery.count();
	    		
	    		//待办理
	    		TaskQuery taskTodoQuery = taskService.createTaskQuery().processDefinitionId(def.getId()).taskAssignee(this.getLoginUserId())
	    											 .active().orderByTaskId().desc();
	    		List<Task> todoTaskList = taskTodoQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());
	    		tasks.addAll(todoTaskList);
	    		itemCount += taskTodoQuery.count();
	    	}
	    }
		
        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
        for(Task task : tasks){
        	HashMap<String,Object> map = new HashMap<String,Object>();
        	
        	String processInstanceId = task.getProcessInstanceId();
        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
        	String leaveInfoId = "";
        	if(null != processInstance.getBusinessKey()){
        		leaveInfoId = processInstance.getBusinessKey();
        	}
        	
        	//获取签收时间
        	Date claimTime = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult().getClaimTime();
        	
        	map.put("taskDefinitionKey", task.getTaskDefinitionKey());
        	map.put("assignee", task.getAssignee());
        	map.put("leaveInfoId", leaveInfoId);
        	map.put("id", task.getId());
        	map.put("name", task.getName());
        	map.put("createTime", task.getCreateTime());
        	map.put("description", task.getDescription());
        	map.put("dueDate", task.getDueDate());
        	map.put("claimTime", claimTime);
        	map.put("owner", task.getOwner());
        	
        	mapList.add(map);
        }
        
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(itemCount).append(",\n");
		data.append("  \"rows\":\n");
		
		JSONArray json = JSONArray.fromObject(mapList);
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try{
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	/**
	 * 初始化外部表单流程启动表单页面
	 * 
	 * @author YuWeitao
	 * @return
	 */
	public String initStartProcessInstance(){
		try {
			this.formContent = formService.getRenderedStartForm(this.processDefinitionId);
			
		} catch (Exception e) {
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
		if(null != this.formContent){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	/**
	 * 外部表单，启动一个流程实例，并且通过.form文件中的表单值保存业务实体
	 * 
	 * @author YuWeitao
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String startProcessInstanceFormKey(){
		try{
			//保存流程业务实体类
			if(businessEntityClassName!=null && !businessEntityClassName.trim().equals("")){
				Class<?> clazz = Class.forName(businessEntityClassName);
				Object obj = clazz.newInstance();
				Enumeration<String> parameters = getRequest().getParameterNames();
				
				//注册converter
				ConvertUtilsBean convertUtils = new ConvertUtilsBean();
			    DateConverter dateConverter = new DateConverter();
			    convertUtils.register(dateConverter,Date.class);
			    //因为要注册converter,所以不能再使用BeanUtils的静态方法了，必须创建BeanUtilsBean实例
			    BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils,new PropertyUtilsBean());
			    
				while(parameters.hasMoreElements()){
					String param = parameters.nextElement();
					if(param.startsWith(businessEntityPrefix)){
						String property = param.substring(param.indexOf(businessEntityPrefix)+businessEntityPrefix.length()+1, param.length());
						String value = getRequestParameter(param);

					    beanUtils.setProperty(obj, property, value);
					}
				}
				beanUtils.setProperty(obj, "delFlag", new Integer(0));
				beanUtils.setProperty(obj, "deptId", this.getLoginUser().getDept().getId());
				
				BaseModel businessEntity = (BaseModel) obj;
				
				businessEntityService.saveBusinessEntity(obj);
				
			    Map<String, String> formProperties = new HashMap<String, String>();
			    formProperties.put("id", businessEntity.getId());
			    
			    // 从request中读取参数然后转换
			    Map<String, String[]> parameterMap = this.getRequest().getParameterMap();
			    Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			    for (Entry<String, String[]> entry : entrySet) {
		    		String key = entry.getKey();
		    		if(-1!=key.indexOf(".")){
		    			formProperties.put(key.split("\\.")[1], entry.getValue()[0]);
		    		}else{
		    			formProperties.put(key, entry.getValue()[0]);
		    		}
			    }
			    
			    identityService.setAuthenticatedUserId(this.getLoginUserId());
			    
			    ProcessInstance processInstance = formService.submitStartFormData(this.processDefinitionId, businessEntity.getId(), formProperties);
			    
			    
			    //保存流程实例ID至业务表，方便关联检索
			    beanUtils.setProperty(obj, "processInstanceId", processInstance.getId());
			    businessEntityService.saveBusinessEntity(obj);			
			    
			}
		}catch(Exception e){
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;				
		}
		
		return RELOAD;
	}
	
	/**
	 * 读取userTask结点绑定的.form文件表单内容，动态生成任务处理页面
	 * 
	 * @author YuWeitao
	 * @return
	 */
	public String initCompleteTaskFormKey(){
		try {
/*			Task task = taskService.createTaskQuery().taskId(this.taskId).singleResult();
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			String businessKey = processInstance.getBusinessKey();
			this.leaveInfo = leaveInfoService.getById(businessKey);*/
			
			this.formContent = formService.getRenderedTaskForm(this.taskId);
		} catch (Exception e) {
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
		}		
		
		return SUCCESS;
	}
	
	
//	public String initCompleteTask() {
//		if ((null != epQysq) && (null != epQysq.getId()))
//			epQysq = (EpQysq) genericManager.findClassById(EpQysq.class, epQysq.getId());
//
//		btnList = genericManager.getOutGoingTransNames(taskId);
//
//		// 显示每一步流程
//		list = genericManager.getinstanceDiagram(epQysq.getDefId());
//
//		return SUCCESS;
//	}
	
	
	/**
	 * 动态读取.form文件中提交的表单信息保存至流程变量，并且完成该任务
	 * 
	 * @author YuWeitao
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public String completeTaskFormKey(){
	    try {
	    	if(businessEntityClassName!=null && !businessEntityClassName.trim().equals("")){
	    		//更新保存业务实体对象
				Class<?> clazz = Class.forName(businessEntityClassName);
				Object obj = clazz.newInstance();
				String businessEntityId = this.getRequestParameter("leaveInfo.id");
				if(null != businessEntityId && !"".endsWith(businessEntityId)){
					obj = businessEntityService.getBusinessEntity(clazz, businessEntityId);
				}
				
				Enumeration<String> parameters = getRequest().getParameterNames();
				
				//注册converter
				ConvertUtilsBean convertUtils = new ConvertUtilsBean();
			    DateConverter dateConverter = new DateConverter();
			    convertUtils.register(dateConverter,Date.class);
			    //因为要注册converter,所以不能再使用BeanUtils的静态方法了，必须创建BeanUtilsBean实例
			    BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils, new PropertyUtilsBean());
			    
				while(parameters.hasMoreElements()){
					String param = parameters.nextElement();
					if(param.startsWith(businessEntityPrefix)){
						String property = param.substring(param.indexOf(businessEntityPrefix)+businessEntityPrefix.length()+1, param.length());
						String value = getRequestParameter(param);

					    beanUtils.setProperty(obj, property, value);
					}
				}
				businessEntityService.saveBusinessEntity(obj);	    		
	    		
	    		Map<String, String> formProperties = new HashMap<String, String>();
			    //formProperties.put("leaveInfo", this.leaveInfo);
			    
			    // 从request中读取参数然后转换
			    Map<String, String[]> parameterMap = this.getRequest().getParameterMap();
			    Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			    for (Entry<String, String[]> entry : entrySet) {
		    		String key = entry.getKey();
		    		if(-1!=key.indexOf(".")){
		    			formProperties.put(key.split("\\.")[1], entry.getValue()[0]);
		    		}else{
		    			formProperties.put(key, entry.getValue()[0]);
		    		}
			    }
		    	
			    identityService.setAuthenticatedUserId(this.getLoginUserId());
			    
			    formService.submitTaskFormData(this.taskId, formProperties);	    		
	    	}
		    			
		} catch (Exception e) {
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
		}
	    
		return RELOAD;
	}
	
	/**
	 * Model列表
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public void findModelList() throws JsonProcessingException, IOException{
		pagination = new Pagination(super.getRequest());
		ModelQuery modelQuery = repositoryService.createModelQuery().orderByCreateTime().desc();
		if(null != this.modelKey && !"".equalsIgnoreCase(this.modelKey)){
			modelQuery = modelQuery.modelKey(this.modelKey);
		}
		if(null != this.modelName && !"".equalsIgnoreCase(this.modelName)){
			modelQuery = modelQuery.modelNameLike("%"+this.modelName+"%");
		}		
		List<Model> modelList = modelQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());
		
        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
        for(Model model : modelList){
        	HashMap<String,Object> map = new HashMap<String,Object>();
        	map.put("isFormkey", false);
        	//解析Model里的formkey属性值
	        JsonNode modelNode = (JsonNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
	        List<JsonNode> formkeyList = modelNode.findValues("formkeydefinition");
	        String formkeyArrayStr = "";
	        for(JsonNode node : formkeyList){
	        	if(null!=node.getTextValue() && !"".equalsIgnoreCase(node.getTextValue())){
	        		formkeyArrayStr += node.getTextValue() + "|";
	        		
	        		map.put("isFormkey", true);
	        	}
	        }    
	        map.put("formkeyArrayStr", formkeyArrayStr);
        	
        	map.put("id", model.getId());
        	map.put("key", model.getKey());
        	map.put("category", model.getCategory());
        	map.put("createTime", model.getCreateTime());
        	map.put("deploymentId", model.getDeploymentId());
        	map.put("lastUpdateTime", model.getLastUpdateTime());
        	map.put("metaInfo", model.getMetaInfo());
        	map.put("name", model.getName());
        	map.put("version", model.getVersion());
        	
        	mapList.add(map);
        }
        
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(modelQuery.count()).append(",\n");
		data.append("  \"rows\":\n");
		
		JSONArray json = JSONArray.fromObject(mapList);
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try{
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
		}
	}
	
	/**
	 * 创建Model
	 * 
	 * @author YuWeitao
	 */
	public void addModel(){
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode editorNode = objectMapper.createObjectNode();
	        editorNode.put("id", "canvas");
	        editorNode.put("resourceId", "canvas");
	        ObjectNode stencilSetNode = objectMapper.createObjectNode();
	        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	        editorNode.put("stencilset", stencilSetNode);
	        Model modelData = repositoryService.newModel();

	        ObjectNode modelObjectNode = objectMapper.createObjectNode();
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, this.modelName);
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, this.modelDesc);
	        modelData.setMetaInfo(modelObjectNode.toString());
	        modelData.setName(this.modelName);
	        modelData.setKey(this.modelKey);

	        repositoryService.saveModel(modelData);
	        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

	        this.getResponse().sendRedirect(this.getRequest().getContextPath() + "/activiti-modeler/service/editor?id=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
		}
	}
	
	/**
	 * 初始化外部表单部署前上传.form文件页面
	 * @author YuWeitao
	 * @return
	 */
	public String initDeployModelForFormkey(){
		return SUCCESS;
	}
	
	
	/**
	 * 部署Model
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 */
	public void deployModel() throws IOException{
	    try {
	        Model modelData = repositoryService.getModel(modelId);
	        JsonNode modelNode = (JsonNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
	        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
	        //byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
	        String processName = modelData.getName() + ".bpmn20.xml";
	        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name(modelData.getName()).addBpmnModel(processName, bpmnModel);
	        
	        List<JsonNode> list = modelNode.findValues("formkeydefinition");
	        for(JsonNode node : list){
	        	if(null!=node.getTextValue() && !"".equalsIgnoreCase(node.getTextValue())){
	        		deploymentBuilder.addClasspathResource(node.getTextValue());
	        	}
	        }
	        deploymentBuilder.deploy();
	        
	        this.getResponse().getWriter().println("{\"result\":true}");
	    } catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
	    }		
	}
	
	/**
	 * 含有外部表单文件上传的Model部署
	 * @author YuWeitao
	 * @return
	 */
	public String deployModelForFormkey(){
	    try {
	        Model modelData = repositoryService.getModel(modelId);
	        JsonNode modelNode = (JsonNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
	        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
	        String processName = modelData.getName() + ".bpmn20.xml";
	        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name(modelData.getName()).addBpmnModel(processName, bpmnModel);
	        
	        for(int i=0;i<this.formkeyFile.length;i++){
	        	deploymentBuilder.addInputStream(this.formkeyFileFileName[i], new FileInputStream(formkeyFile[i]));
	        }
	       
	        deploymentBuilder.deploy();
	        
	    } catch (Exception e) {
	    	logger.error("error on deploy process, because of file input stream", e);
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
	    }
	    
		return RELOAD;
	}
	
	/**
	 * 删除Model
	 * 
	 * @author YuWeitao
	 * @throws IOException 
	 */
	public void deleteModel() throws IOException{
		if(null != ids){
			try{
				for(String modelId : ids)
					repositoryService.deleteModel(modelId);
				getResponse().getWriter().println("{\"result\":true}");
			}catch(Exception e){
		    	e.printStackTrace();
				BasalException be = new BasalException(BasalException.ERROR, e);
				throw be;
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":false}");
			}catch(Exception e){
		    	e.printStackTrace();
				BasalException be = new BasalException(BasalException.ERROR, e);
				throw be;
			}
		}		
	}
	
	/**
	 * 导出模型XML文件
	 * 
	 * @author YuWeitao
	 */
	public void exportModelXml(){
	    try {
	        Model modelData = repositoryService.getModel(modelId);
	        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
	        JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
	        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
	        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
	        byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

	        ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
	        this.getResponse().setBufferSize(1024*32);//设置缓冲区大小，解决大字节流XML头丢失的问题  add by YuWeitao 2013-06-25
	        IOUtils.copy(in, this.getResponse().getOutputStream());
	        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
	        this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + filename);
	        this.getResponse().flushBuffer();
	      } catch (Exception e) {
	        logger.error("导出model的xml文件失败：modelId={}", modelId, e);
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;	
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

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File[] getFormkeyFile() {
		return formkeyFile;
	}

	public void setFormkeyFile(File[] formkeyFile) {
		this.formkeyFile = formkeyFile;
	}

	public String[] getFormkeyFileFileName() {
		return formkeyFileFileName;
	}

	public void setFormkeyFileFileName(String[] formkeyFileFileName) {
		this.formkeyFileFileName = formkeyFileFileName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public Object getFormContent() {
		return formContent;
	}

	public void setFormContent(Object formContent) {
		this.formContent = formContent;
	}
	
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	
	 

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelKey() {
		return modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}

	public String getProcessDefKey() {
		return processDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		this.processDefKey = processDefKey;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public String[] getIds() {
		return ids;
	}

	public void setId(String[] ids) {
		this.ids = ids;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String[] getFormkeyArray() {
		return formkeyArray;
	}

	public void setFormkeyArray(String[] formkeyArray) {
		this.formkeyArray = formkeyArray;
	}

	public String getBusinessEntityClassName() {
		return businessEntityClassName;
	}

	public void setBusinessEntityClassName(String businessEntityClassName) {
		this.businessEntityClassName = businessEntityClassName;
	}

	public String getBusinessEntityPrefix() {
		return businessEntityPrefix;
	}

	public void setBusinessEntityPrefix(String businessEntityPrefix) {
		this.businessEntityPrefix = businessEntityPrefix;
	}

}