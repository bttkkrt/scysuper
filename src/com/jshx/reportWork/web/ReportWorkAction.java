package com.jshx.reportWork.web;

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

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jpush.api.JPushClient;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.service.ReportWorkService;
import com.jshx.reportWorkReceiver.entity.ReportWorkReceiver;
import com.jshx.reportWorkReceiver.service.ReportWorkReceiverService;

public class ReportWorkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ReportWork reportWork = new ReportWork();
	
	/**
	 * 业务类
	 */
	@Autowired
	private ReportWorkService reportWorkService;
	@Autowired
	private ReportWorkReceiverService reportWorkReceiverService;
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
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != reportWork){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != reportWork.getWorkTitle()) && (0 < reportWork.getWorkTitle().trim().length())){
				paraMap.put("workTitle", "%" + reportWork.getWorkTitle().trim() + "%");
			}

			if ((null != reportWork.getWorkType()) && (0 < reportWork.getWorkType().trim().length())){
				paraMap.put("workType", "%" + reportWork.getWorkType().trim() + "%");
			}

			if ((null != reportWork.getDeptCode()) && (0 < reportWork.getDeptCode().trim().length())){
				paraMap.put("deptCode", "%" + reportWork.getDeptCode().trim() + "%");
			}

			if ((null != reportWork.getUserName()) && (0 < reportWork.getUserName().trim().length())){
				paraMap.put("userName", "%" + reportWork.getUserName().trim() + "%");
			}

		}

		paraMap.put("listType", "1");
		paraMap.put("createUserID", this.getLoginUserId());
		pagination = reportWorkService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter()
  		{
  			String colNames = new String("id|workTitle|userName|workContent|receiverList|time|");
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
		    
		if(null != reportWork){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != reportWork.getWorkTitle()) && (0 < reportWork.getWorkTitle().trim().length())){
				paraMap.put("workTitle", "%" + reportWork.getWorkTitle().trim() + "%");
			}

			if ((null != reportWork.getWorkType()) && (0 < reportWork.getWorkType().trim().length())){
				paraMap.put("workType", "%" + reportWork.getWorkType().trim() + "%");
			}

			if ((null != reportWork.getDeptCode()) && (0 < reportWork.getDeptCode().trim().length())){
				paraMap.put("deptCode", "%" + reportWork.getDeptCode().trim() + "%");
			}

			if ((null != reportWork.getUserName()) && (0 < reportWork.getUserName().trim().length())){
				paraMap.put("userName", "%" + reportWork.getUserName().trim() + "%");
			}

		}

		paraMap.put("listType", "2");
		paraMap.put("createUserID", this.getLoginUserId());
		pagination = reportWorkService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter()
  		{
  			String colNames = new String("id|workTitle|userName|workContent|receiverList|time|");
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
		if((null != reportWork)&&(null != reportWork.getId())){
			reportWork = reportWorkService.getById(reportWork.getId());
			Map map = new HashMap();
			map.put("taskProId",reportWork.getProId());
			map.put("picType","gzsb");
		    picList = szwxPhotoService.findPicPath(map);

		    List<ReportWorkReceiver> receiverList = reportWork.getReceiverList();
		    for (ReportWorkReceiver rwr : receiverList) {
		    	if(null == userNames){
		    		userNames = rwr.getReceiveUser().getDisplayName();
		    	}else{
		    		userNames += ","+rwr.getReceiveUser().getDisplayName();
		    	}
		    	if(null == userIds){
		    		userIds = rwr.getReceiveUser().getId();
		    	}else{
		    		userIds += ","+rwr.getReceiveUser().getId();
		    	}
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			reportWork.setProId(linkId);
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
		String[] ids = reportWork.getUserIds().split(",");
		if ("add".equalsIgnoreCase(this.flag)){
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			reportWork.setDeptId(this.getLoginUserDepartmentId());
			reportWork.setDelFlag(0);
			reportWork.setUserName(getLoginUser().getDisplayName());
			reportWork.setCreateTime(new Date());
			reportWork.setCreateUserID(getLoginUser().getId());
			reportWork.setTime(sdf.format(d));
			List<ReportWorkReceiver> receiverList = new ArrayList<ReportWorkReceiver>();
			for(int i=0;i<ids.length;i++){
				ReportWorkReceiver rwr = new ReportWorkReceiver();
				User recuser = userService.findUserById(ids[i]);
				rwr.setReceiveUser(recuser);
				rwr.setReportWork(reportWork);
				rwr.setReceiveTime(new Date());
				rwr.setReceiveFlag("0");//工作阅读结果：0：未读，1：已读
				rwr.setDelFlag(0);
				receiverList.add(rwr);
			}
			reportWork.setReceiverList(receiverList);
			reportWorkService.save(reportWork);
			
		}else{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("reportWork", reportWork);
			List<ReportWorkReceiver> oldrl = reportWorkReceiverService.findReportWorkReceiver(paraMap);
		    for (ReportWorkReceiver rwr : oldrl) {
		    	reportWorkReceiverService.deleteWithFlag(rwr.getId());
			}
			List<ReportWorkReceiver> receiverList = new ArrayList<ReportWorkReceiver>();
			for(int i=0;i<ids.length;i++){
				ReportWorkReceiver rwr = new ReportWorkReceiver();
				User recuser = userService.findUserById(ids[i]);
				rwr.setReceiveUser(recuser);
				rwr.setReportWork(reportWork);
				rwr.setReceiveTime(new Date());
				rwr.setReceiveFlag("0");//工作阅读结果：0：未读，1：已读
				rwr.setDelFlag(0);
				receiverList.add(rwr);
			}
			reportWork.setReceiverList(receiverList);
			reportWorkService.update(reportWork);
		}
		//推送消息到手机端 hanxc 20150409
		try {
			JPushClient jpush = new JPushClient();
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", reportWork.getId());
			map.put("workTitle", StringTools.NullToStr(reportWork.getWorkTitle(),""));
			map.put("deptName", StringTools.NullToStr(this.getLoginUserDepartment().getDeptName(),""));
			map.put("time", StringTools.NullToStr(reportWork.getTime(),""));
			map.put("userName", StringTools.NullToStr(reportWork.getUserName(),""));
			map.put("entityFlag", "reportWork");
			jpush.sendAndroidNotificationWithAlias("抚顺安监", reportWork.getWorkTitle(), map, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			reportWorkService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 工作内容上传
	 * author：hanxc
	 * 2015-02-27
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
					taskPic.setTaskProId(reportWork.getProId());
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
	 * 工作内容删除
	 * author：hanxc
	 * 2015-02-27
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
	 * 工作内容下载
	 * author：hanxc
	 * 2015-02-27
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
  	 * author：陆婷
  	 * 2013-07-19
  	 */
  	public String selectUsers()
  	{
  		return SUCCESS;
  	}
  	
  	/**
  	 * 查询部门用户列表 author：陆婷 2013-07-19
  	 * hanxc 20150302 添加查询条件
  	 * @throws IOException 
  	 */
  	public void getDepartUser() throws IOException
  	{
  		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
  		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
  		Map map = new HashMap();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.length() == countyDeptCodeLength){//县级部门
			deptCode = deptCode.substring(0, countyDeptCodeLength-3);
			map.put("dwdz1",deptCode+ "%");
			map.put("isNotZsqy", "true");
		}else if(deptCode.length() == townDeptCodeLength){//镇级部门
			map.put("dwdz1",deptCode+ "%");
			map.put("isNotZsqy", "true");
		}else if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){
			map.put("qyid", this.getLoginUser().getLoginId());
		}
  		map.put("passTer", 1);
  		JSONArray jsonArry = new JSONArray();
  		List<User> userList = contentInformationsService.getAllUsersByMap(map);
		for(User user : userList){
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", user.getId());
	  		jsonObject.put("pId",user.getDeptCode());
	  		jsonObject.put("name", user.getDisplayName());
	  		jsonObject.put("flag", "1");
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
	  		jsonArry.add(jsonObject);
		}
		List<Dept> deptList = contentInformationsService.getAllDepartByMap(map);
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

	public ReportWork getReportWork(){
		return this.reportWork;
	}

	public void setReportWork(ReportWork reportWork){
		this.reportWork = reportWork;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public String getUserNames()
	{
		return userNames;
	}

	public void setUserNames(String userNames)
	{
		this.userNames = userNames;
	}

	public String getUserIds()
	{
		return userIds;
	}

	public void setUserIds(String userIds)
	{
		this.userIds = userIds;
	}

  	public User getUser() {
  		return user;
  	}

  	public void setUser(User user) {
  		this.user = user;
  	}
	
}
