package com.jshx.docReview.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.activiti.service.ActivityManageService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.docReview.entity.DocReview;
import com.jshx.docReview.service.DocReviewService;
import com.jshx.docReviewReceiver.entity.DocReviewReceiver;
import com.jshx.docReviewReceiver.service.DocReviewReceiverService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class DocReviewAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DocReview docReview = new DocReview();

	/**
	 * 业务类
	 */
	@Autowired
	private DocReviewService docReviewService;
	@Autowired
	private DocReviewReceiverService docReviewReceiverService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private ContentInformationsService contentInformationsService;
	@Autowired
	private UserService userService; 
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private User user; //用户
	private String taskId;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 附件
	 */
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private String fileId; //附件id
	private List<PhotoPic> picList;
	
	private String userNames;
	private String userIds;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	protected RuntimeService runtimeService;
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != docReview){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != docReview.getDocTitle()) && (0 < docReview.getDocTitle().trim().length())){
				paraMap.put("docTitle", "%" + docReview.getDocTitle().trim() + "%");
			}

			if ((null != docReview.getDocType()) && (0 < docReview.getDocType().trim().length())){
				paraMap.put("docType", "%" + docReview.getDocType().trim() + "%");
			}

			if ((null != docReview.getDeptCode()) && (0 < docReview.getDeptCode().trim().length())){
				paraMap.put("deptCode", "%" + docReview.getDeptCode().trim() + "%");
			}

			if ((null != docReview.getUserName()) && (0 < docReview.getUserName().trim().length())){
				paraMap.put("userName", "%" + docReview.getUserName().trim() + "%");
			}

		}
		
		paraMap.put("listType", "1");
		paraMap.put("createUserID", this.getLoginUserId());
		pagination = docReviewService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter()
  		{
			String colNames = new String("id|docTitle|userName|docContent|receiverList|time|end|");
  			public boolean apply(Object source, String name, Object value) {
  				return colNames.indexOf(name + "|") == -1;
  			}
  		});
		convObjectToJson(pagination,config);
	}

	/**
	 * 执行查询的方法，返回json数据
	 */
	public void myList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != docReview){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != docReview.getDocTitle()) && (0 < docReview.getDocTitle().trim().length())){
				paraMap.put("docTitle", "%" + docReview.getDocTitle().trim() + "%");
			}

			if ((null != docReview.getDocType()) && (0 < docReview.getDocType().trim().length())){
				paraMap.put("docType", "%" + docReview.getDocType().trim() + "%");
			}

			if ((null != docReview.getDeptCode()) && (0 < docReview.getDeptCode().trim().length())){
				paraMap.put("deptCode", "%" + docReview.getDeptCode().trim() + "%");
			}

			if ((null != docReview.getUserName()) && (0 < docReview.getUserName().trim().length())){
				paraMap.put("userName", "%" + docReview.getUserName().trim() + "%");
			}

		}
		
		paraMap.put("listType", "2");
		paraMap.put("createUserID", this.getLoginUserId());
		pagination = docReviewService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter()
  		{
			String colNames = new String("id|docTitle|userName|docContent|receiverList|time|end|");
  			public boolean apply(Object source, String name, Object value) {
  				return colNames.indexOf(name + "|") == -1;
  			}
  		});
		convObjectToJson(pagination,config);
	}
	
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != docReview)&&(null != docReview.getId())){
			docReview = docReviewService.getById(docReview.getId());
			Map map = new HashMap();
			map.put("taskProId",docReview.getProId());
			map.put("picType","gzsb");
		    picList = szwxPhotoService.findPicPath(map);

		    List<DocReviewReceiver> receiverList = docReview.getReceiverList();
		    for (DocReviewReceiver drr : receiverList) {
		    	if(null == userNames){
		    		userNames = drr.getReceiveUser().getDisplayName();
		    	}else{
		    		userNames += ","+drr.getReceiveUser().getDisplayName();
		    	}
		    	if(null == userIds){
		    		userIds = drr.getReceiveUser().getId();
		    	}else{
		    		userIds += ","+drr.getReceiveUser().getId();
		    	}
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			docReview.setProId(linkId);
		}
		if(taskId!=null){
			return "AUDITVIEW";
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		user = getLoginUser();
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
  		//获取接收人
		String[] ids = docReview.getUserIds().split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		User startUser =  contentInformationsService.getMaxDeptCodeUser(map);
		Map<String,Object> variables = new HashMap<String,Object> ();
		variables.put("moveStatus", "usertask1");
		variables.put("deptCode", startUser.getDeptCode());
		variables.put("applyUserId", getLoginUserId());
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		docReview.setDeptId(this.getLoginUserDepartmentId());
		docReview.setDelFlag(0);
		docReview.setUserName(getLoginUser().getDisplayName());
		docReview.setTime(sdf.format(d));
		if ("add".equalsIgnoreCase(this.flag)){
			docReview.setCreateUserID(getLoginUser().getId());
			docReview.setCreateTime(new Date());
		}else{
			//删除
			DocReview t = docReviewService.getById(docReview.getId());
			t.setDelFlag(1);
			docReviewService.update(t);
			docReview.setId("");
		}
		
		List<DocReviewReceiver> receiverList = new ArrayList<DocReviewReceiver>();
		for(int i=0;i<ids.length;i++){
			DocReviewReceiver drr = new DocReviewReceiver();
			User recuser = userService.findUserById(ids[i]);
			drr.setReceiveUser(recuser);
			drr.setDocReview(docReview); 
			drr.setReceiveTime(new Date());
			drr.setReceiveFlag("0");//工作阅读结果：0：未读，1：已读
			drr.setDelFlag(0);
			receiverList.add(drr);
		}
		docReview.setReceiverList(receiverList);
		docReviewService.save(docReview);
		variables.put("workFlowModelId",  docReview.getId());
		String defId = activityManageService.StartProcessInstance(docReview.getId(), "doReview", getLoginUserId(), variables) ;
		docReview.setDefId(defId);
		docReview.setAuditStatus(0);
		docReview.setEnd(0);
		docReviewService.update(docReview);
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			docReviewService.deleteWithFlag(ids);
			 String[] idArray = ids.split("\\|");
				if(null != idArray)
				{
					for(String id : idArray)
					{
					    DocReview tdocReview = docReviewService.getById(id);
					    activityManageService.endProcess(tdocReview.getDefId());
					}
				}
			
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 附件上传
	 * author：zhangzh
	 * 2015-12-28
	 */
	public void fileUpload()
	{
		try
  		{
  			String filename = "";
  			String extName = "";
  			if ((Filedata != null) && (!Filedata.isEmpty())) {
  				for (int i = 0; i < Filedata.size(); i++) {
  			  		//获取文件对象
  					File file = (File)Filedata.get(i);
  					UploadFile uploadFile = new UploadFile();
  					filename = (String)FiledataFileName.get(i);
  					uploadFile.setFileName(filename);
  					
  					uploadFile.setUploadFile(file);
  					String rename = getDatedFName(FiledataFileName.get(i));
  					String path = Struts2Util.getServletContext().getRealPath(
  					"/");
  					uploadFile.setId(rename);
  					uploadFile.setFilePath(path + 
  					"../../virtualdir/upload/file/gzsb/"
  					);
  			  		//附件上传
  					uploadFile.uploadToServer();
  					
  					PhotoPic taskPic = new PhotoPic();
  					taskPic.setCreateTime(new Date());
  					taskPic.setPicName("gzsb/" + rename);
					taskPic.setPicType("gzsb");
					taskPic.setTaskProId(docReview.getProId());
  					taskPic.setDelFlag(0);
  					taskPic.setFileName(filename);//保存原文件的名称
  					szwxPhotoService.save(taskPic);//在此处调用图片的保存
  	  				extName = taskPic.getId();
  				}
  				HttpServletResponse response = ServletActionContext.getResponse();
  			    response.getWriter().write(filename + ";" + extName);
  			}
  		}
  		catch (Exception e) {
  			e.printStackTrace();
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
			List<Task> ttasks =taskToClaimQuery.list();
			List<Task> taskListToClaim = taskToClaimQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(Task task : taskListToClaim){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	String docReviewId = "";
	        	if(null != processInstance.getBusinessKey()){
	        		docReviewId = processInstance.getBusinessKey();
	        	}
	        	docReview = docReviewService.getById(docReviewId);
	        	map.put("id", docReviewId);
	        	map.put("task_id", task.getId());
	        	map.put("name", docReview.getDocTitle());
	        	map.put("createTime", task.getCreateTime());
	        	map.put("description", docReview.getDocContent());
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
	 * zhangzh 2016-1-7
	 * 审核公文
	 * 1  通过审核  ，-1  不通过
	 * 进入下一阶段审核流程
	 * @throws IOException 
	 */
	public void doAudit() throws IOException{
		try {
			if(docReview == null){
				this.getResponse().getWriter().println("{\"result\":false}");
			}
			int option = docReview.getAuditStatus();
				Map<String,Object> variables = new HashMap<String, Object>();
				variables.put("workFlowModelId",  docReview.getId());
				variables.put("loginUserId",  getLoginUserId());
				String backReason = docReview.getBackReason();
				docReview = docReviewService.getById(docReview.getId());
				if(option ==1){  //不通过，结束审核
					variables.put("moveStatus", "endHandler");
					docReview.setBackReason(backReason);
					docReview.setAuditStatus(-1);
					docReviewService.update(docReview);
				}else if(option==2){ //进入下一阶段
					String deptCode = this.getLoginUserDepartment().getDeptCode();
					if(deptCode.length() > 3){
						deptCode = deptCode.substring(0,deptCode.length()-3);
						variables.put("deptCode", deptCode);
//						variables.put("taskId", "usertask1");
						variables.put("workFlowModelId",  docReview.getId());
						variables.put("moveStatus", "nextStep");
					}else{
						variables.put("moveStatus", "endHandler");
						docReview.setAuditStatus(1);
						docReviewService.update(docReview);
					}
				}else if (option ==3){ //通过，结束审核
					variables.put("moveStatus", "endHandler");
					docReview.setAuditStatus(1);
					docReviewService.update(docReview);
				}
				activityManageService.passProcess(taskId, null, variables);	
				this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}	
		
		
		
		
		

		
	}
	
	public void endProcess() throws IOException {
		try {
			docReview = docReviewService.getById(docReview.getId());
			activityManageService.endProcess(docReview.getDefId());
			docReview.setEnd(-1);
			docReview.setUpdateTime(new Date());
			docReview.setUpdateUserID(getLoginUserId());
			docReviewService.update(docReview);
			this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
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
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	String docReviewId = "";
	        	if(null != processInstance.getBusinessKey()){
	        		docReviewId = processInstance.getBusinessKey();
	        	}
	        	docReview = docReviewService.getById(docReviewId);
	        	map.put("id", docReviewId);
	        	map.put("task_id", task.getId());
	        	map.put("name", docReview.getDocTitle());
	        	map.put("createTime", task.getCreateTime());
	        	map.put("description", docReview.getDocContent());
	        	map.put("claimTime", claimTime);
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
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @return
	 */
	public String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}
	/**
	 * 附件删除
	 * author：zhangzh
	 * 2015-12-28
	 */
	public void deleteFile() throws IOException
	{
		try{
	    	szwxPhotoService.deleteImageWithFlag(fileId);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
	}
	/**
	 * 附件下载
	 * author：zhangzh
	 * 2015-12-28
	 */
	public void download()
	{
		try
  		{
  		  	//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(fileId);
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			File fis = new File(path + "../../virtualdir/upload/file/" + photoPic.getPicName());
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

  				String browName = new String();
  				browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
  				String clientInfo = getRequest().getHeader("User-agent");
  				if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
  					if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
  						browName = new String(photoPic.getFileName().getBytes("GBK"), "ISO-8859-1");
  				}

  				Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
  				OutputStream out = Struts2Util.getResponse().getOutputStream();
  				try {
  					byte[] buf = new byte[1024];
  					int len;
  					while ((len = in.read(buf)) != -1)
  					{
  						out.write(buf, 0, len);
  					}
  				} catch (Exception e) {
  					e.printStackTrace();
  				} finally {
  					in.close();
  					out.close();
  				}
  			}
  		}
  		catch (Exception e) {
  			e.printStackTrace();
  		}
	}

  	/**
  	 * 查询部门用户列表
	 * author：zhangzh
	 * 2015-12-28
  	 */
  	public String selectUsers()
  	{
  		return SUCCESS;
  	}

  	public void getDepartUser() throws IOException
  	{
  		List<User> userList = new ArrayList<User>();
  		List<Dept> deptList =  new ArrayList<Dept>();
  		
  		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
  		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
  		Map<String,Object> map = new HashMap<String,Object> ();
  		String deptCode = this.getLoginUserDepartment().getDeptCode();
  		map.put("loginUserId", getLoginUserId());
  		 if(deptCode.length() >= townDeptCodeLength){//镇级及以下部门  12位及以上
 			 String townDeptCode = deptCode.substring(0, townDeptCodeLength-3);
 			map.put("deptCode", townDeptCode);
 			map.put("townDeptCode", townDeptCode);
 			Dept townDept = contentInformationsService.getDepartByMap(map).get(0);
 			if (townDept.getDeptName().indexOf("乡镇") == -1) {  //是否属于乡镇,不属于则去掉乡镇code
 				map.remove("townDeptCode");
			}
 		}
  		 if(deptCode.length() >= countyDeptCodeLength){//县级及以下部门  9位及以上
 			 String countyDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
 			map.put("deptCode", countyDeptCode);
 			map.put("countyDeptCode", countyDeptCode);
 			Dept countyDept = contentInformationsService.getDepartByMap(map).get(0);
 			if (countyDept.getDeptName().indexOf("县") != -1) {  //是否属于县,不属于则去掉县code
 				deptList.add(countyDept);
 				List<User> users =  contentInformationsService.findUsersByMap(map);
 				userList.addAll(users);
			} 
				map.remove("countyDeptCode");
 		}
  		if(deptCode.length()>=3){
  			map.put("deptCode", deptCode.substring(0,3));
  			List<User> users =  contentInformationsService.findUsersByMap(map);
  			userList.addAll(users);
  			List <Dept> depts =  contentInformationsService.getDepartByMap(map);
  			deptList.addAll(depts);
  		}
  		deptCode = deptCode.substring(0,deptCode.length()-3);
  		while (deptCode.length()>=3) {
  			map.put("deptCode", deptCode+"___");
  			List<User> users =  contentInformationsService.findUsersByMap(map);
  			userList.addAll(users);
  			List <Dept> depts =  contentInformationsService.getDepartByMap(map);
  			deptList.addAll(depts);
  			deptCode = deptCode.substring(0,deptCode.length()-3);
		}
  		JSONArray jsonArry = new JSONArray();
		for(User user : userList){
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", user.getId());
	  		jsonObject.put("pId",user.getDeptCode());
	  		jsonObject.put("name", user.getDisplayName());
	  		jsonObject.put("flag", "1");
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
	  		jsonArry.add(jsonObject);
		}
		for (Dept dept : deptList) {
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", dept.getId());
	  		if(dept.getParentId() == null || "".equals(dept.getParentId()))
	  		{
	  			jsonObject.put("pId", "-1");
	  			jsonObject.put("open", true);
	  		}
	  		else
	  		{
	  			jsonObject.put("pId", dept.getParentId());
	  		}
	  		jsonObject.put("name", dept.getDeptName());
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
	  		jsonArry.add(jsonObject);
		}
			
		getResponse().getWriter().write(jsonArry.toString());
  	}
  	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public DocReview getDocReview(){
		return this.docReview;
	}

	public void setDocReview(DocReview docReview){
		this.docReview = docReview;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}
