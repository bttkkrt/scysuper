package com.jshx.module.infomation.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import cn.jpush.api.JPushClient;

import com.jshx.aqscfzry.service.SecProChaPerService;
import com.jshx.aqscgljl.service.SecProManService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;

public class ContentInformationsAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ContentInformations contentInformations = new ContentInformations();
	
	private NoticeCallback noticeCallback=new NoticeCallback();//是否阅读
	

	/**
	 * 业务类
	 */
	@Autowired
	private ContentInformationsService contentInformationsService;
	
	@Autowired
	private ContentAttachsService contentAttachsService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private SecProManService secProManService;
	@Autowired
	private SecProChaPerService secProChaPerService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String updateFlag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryPublicDateStart;

	private Date queryPublicDateEnd;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	
	/** 文件 */
	private List<File> file;

	/** 文件名 */
	private List<String> fileFileName;

	/** 文件内容类型 */
	private List<String> fileContentType;

	
	/**
	 * 从页面传递的信息内容
	 */
	private String infoContent;
	
	private User user;
	
	/*查询条件中的是否显示禁用信息*/
	private String isDelshow;
	
	/*查询条件中的是否显示过期信息*/
	private String isExpireshow;
	
	/*查询条件中的发布人*/
	private String username;
	
	private String userIds;
	
	private String userNames;
	
	private String roleName;
	

	/**
	 * 附件信息
	 * */
//	private List<File> upload;
//	
//	private List<String> uploadFileName;
//
//	private List<String> uploadContentType;
	
	/**附件类型*/
	private List<String> attachType;
	
	/**查看详情和修改中的附件列表*/
	private List<ContentAttachs> attachList;
	
	/**查看详情中的附件个数*/
	private int attachCount;
	
	/**用来删除和下载附件*/
	private String attachId;
	//公告发布者查看阅读记录标识
	private String readable;
	private List<User> users;
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public String getReadable() {
		return readable;
	}

	public void setReadable(String readable) {
		this.readable = readable;
	}

	private List<NoticeCallback> noticelist=new ArrayList<NoticeCallback>(); //阅读人数
	
	public List<ContentAttachs> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<ContentAttachs> attachList) {
		this.attachList = attachList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String initList(){
		
		if(this.getLoginUser().getDeptCode().startsWith("002001")) 
		{
			roleName = "1";
		}
		return SUCCESS;
	}
	
	/**
  	 * 我发布的公告列表
  	 * @author 陆婷
  	 * 2013-07-18
  	 *
  	 */
  	public String initLists() {
	    return "success";
	}
  	
	/**
	 * 执行查询的方法，返回json数据<br>
	 * json包含的属性："infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|"
	 */
	public String list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != contentInformations){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != contentInformations.getInfoTitle()) && (0 < contentInformations.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + contentInformations.getInfoTitle().trim() + "%");
			}

			if ((null != contentInformations.getInfoType()) && (0 < contentInformations.getInfoType().trim().length())){
				paraMap.put("infoType", contentInformations.getInfoType().trim());
			}
            
			if((null == this.getIsDelshow())||(this.getIsDelshow().trim().equalsIgnoreCase("0"))){
				paraMap.put("delFlag", "0");
			}
			
			if((null == this.getIsExpireshow())||(this.getIsExpireshow().trim().equalsIgnoreCase("0"))){
				paraMap.put("expireFlag", "0");
			}

			if ((null != this.getUsername()) && (0 < this.getUsername().trim().length())){
				paraMap.put("username", "%" + this.getUsername().trim() + "%");
			}

			if (null != queryPublicDateStart){
				paraMap.put("startPublicDate", queryPublicDateStart);
			}

			if (null != queryPublicDateEnd){
				paraMap.put("endPublicDate", queryPublicDateEnd);
			}
  			paraMap.put("reciverUserID", this.getLoginUser().getId());
		}
		
		try {
			pagination = contentInformationsService.findByPage(pagination, paraMap);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//将查询结果转为json数据给datagrid展现
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		final String colNames = new String(
				"infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|createUserID|");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (colNames.indexOf(name + "|") != -1)
					return false;
				else
					return true;
			}
		});
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try {
			this.getResponse().getWriter().println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
  	 * 我发布的公告列表查询
  	 * @author 陆婷
  	 * 2013-07-18
  	 *
  	 */
  	public String lists() throws Exception
  	{
  		Map<String, Object> paraMap = new HashMap<String, Object>();
  		if (pagination == null) {
  			pagination = new Pagination(getRequest());
  		}
  		if (contentInformations != null)
  		{
  			if ((contentInformations.getInfoTitle() != null) && (contentInformations.getInfoTitle().trim().length() > 0)) {
  				paraMap.put("infoTitle", "%" + contentInformations.getInfoTitle().trim() + "%");
  			}

  			if ((contentInformations.getInfoType() != null) && (contentInformations.getInfoType().trim().length() > 0)) {
  				paraMap.put("infoType", contentInformations.getInfoType().trim());
  			}

  			if ((getIsDelshow() == null) || (getIsDelshow().trim().equalsIgnoreCase("0"))) {
  				paraMap.put("delFlag", "0");
  			}

  			if ((getIsExpireshow() == null) || (getIsExpireshow().trim().equalsIgnoreCase("0"))) {
  				paraMap.put("expireFlag", "0");
  			}

  			if (queryPublicDateStart != null) {
  				paraMap.put("startPublicDate", queryPublicDateStart);
  			}

  			if (queryPublicDateEnd != null) {
  				paraMap.put("endPublicDate", queryPublicDateEnd);
  			}
//  			paraMap.put("listType", "2");
  			paraMap.put("createUserID", this.getLoginUserId());
  		}
  		//查询公告列表
  		pagination = contentInformationsService.findByPage(pagination, paraMap);

  		StringBuffer data = new StringBuffer("{\n");
  		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
  		data.append("  \"rows\":\n");

  		JsonConfig config = new JsonConfig();
  		config.setJsonPropertyFilter(new PropertyFilter()
  		{
  			final String colNames = new String(
  					"infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|createUserID|viewnum|realnum|");
  			public boolean apply(Object source, String name, Object value) {
  				return colNames.indexOf(name + "|") == -1;
  			}
  		});
  		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(), config);
  		data.append(json.toString());
  		data.append("  \n").append("}");
  		try
  		{
  			getResponse().getWriter().println(data);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  		return null;
  	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != contentInformations)&&(null != contentInformations.getId()))
		{
			contentInformations = contentInformationsService.getById(contentInformations.getId());
			/*if(contentInformations.getLinkId() == null || "".equals(contentInformations.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					contentInformations.setLinkId(linkId);
			}else{
					try {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("infoId",contentInformations.getId());
//						map.put("mkType", "contentInformations");
//						map.put("picType","contentInformationsfj");
						attachList = contentAttachsService.findContentAttachs(map);
//						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}*/
			
			if(null != contentInformations){
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("infoId",contentInformations.getId());
//					map.put("mkType", "contentInformations");
//					map.put("picType","contentInformationsfj");
					attachList = contentAttachsService.findContentAttachs(map);
//					picList = photoPicService.findPicPath(map);//获取执法文书材料
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			infoContent = contentInformations.getInfoContent();
			user = this.getLoginUser();
  			//获取所有有阅读权限的人的id和名称
  			String userIds = contentInformations.getUserIds();
  			//公告发布者可以查看阅读记录
  			if(contentInformations.getCreateUserID().equals(user.getId())){
  				readable = "1";
  			}
  			//如果当前登录人有阅读权限
  			if(null != userIds && userIds.contains(user.getId()))
  			{
  	  			//查询当前登录人阅读记录，若有，则更新，若没有，则新增
  				List<NoticeCallback> readed= contentInformationsService.geReadedUsersIds(contentInformations.getId(),user.getId());
  	  			if(readed.size()<=0||null==readed)
  	  			{
  	  				SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	  				noticeCallback.setNoticeId(contentInformations.getId());
  	  				noticeCallback.setUserName(user.getDisplayName());
  	  				noticeCallback.setUserID(user.getId());
  	  				noticeCallback.setIsRead("已读");
  	  				noticeCallback.setLastReadTime(sm.format(new Date()));
  	  				noticeCallback.setUpdateUserID(user.getId());
  	  				contentInformationsService.saveNoticeBack(noticeCallback);
  	  				int realnum = contentInformations.getRealnum() + 1;
  	  				contentInformations.setRealnum(realnum);
  	  				contentInformationsService.update(contentInformations);
  	  			}else
  	  			{
  	  				SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	  				noticeCallback = readed.get(0);
  	  				if("未读".equals(noticeCallback.getIsRead()))
  	  				{
	  	  				int realnum = contentInformations.getRealnum() + 1;
	  	  				contentInformations.setRealnum(realnum);
	  	  				contentInformationsService.update(contentInformations);
  	  				}
  	  				noticeCallback.setIsRead("已读");
  	  				noticeCallback.setLastReadTime(sm.format(new Date()));
  	  				contentInformationsService.updateNoticeBack(noticeCallback);
  	  			}
  			}
  			//获取该公告阅读记录
  			Map map=new HashMap();
  			map.put("id", contentInformations.getId());
  			try {
				noticelist= contentInformationsService.geBackById(map);
			   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		  }else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				contentInformations.setLinkId(linkId);
			}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		user=this.getLoginUser();
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		//String JUMP = "";
  		//获取有阅读权限人数
		String userIds = contentInformations.getUserIds();
		String[] ids = userIds.split(",");
		String userNames = contentInformations.getUserNames();
		String[] names = userNames.split(",");
		contentInformations.setViewnum(ids.length);
		contentInformations.setRealnum(0);
		if ("add".equalsIgnoreCase(this.flag)){
			Date d = new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);
			contentInformations.setUserId(this.getLoginUser().getId());
			contentInformations.setDeptId(this.getLoginUserDepartmentId());
			contentInformations.setDelFlag("0");
			contentInformations.setExpireFlag("0");
			contentInformations.setInfoContent(null);
			contentInformations.setReaded("未读");
			contentInformations.setPublicDate(d);
			contentInformations.setCreateTime(d);
			contentInformations.setCreateUserID(getLoginUser().getId());
			contentInformations.setTime(dateStr);
			contentInformationsService.save(contentInformations);
			contentInformations.setInfoContent(infoContent);
			contentInformationsService.update(contentInformations);
			//有阅读权限但没有阅读记录的新增
  			for(int i=0;i<ids.length;i++){
				NoticeCallback back=new NoticeCallback();
				back.setNoticeId(contentInformations.getId());
				back.setUserName(names[i]);
				back.setUserID(ids[i]);
				back.setIsRead("未读");
				contentInformationsService.saveNoticeBack(back);
  			}
  			//JUMP= "reload";
		}else{
			//更新公告
			Date d = new Date();
			contentInformations.setCreateTime(d);
			contentInformations.setPublicDate(d);
			contentInformations.setInfoContent(infoContent);
		    contentInformationsService.update(contentInformations);
		
			//删除已有阅读记录
			Map map = new HashMap();
			map.put("noticeId", contentInformations.getId());
  			try {
				contentInformationsService.deleteNoticeBackByMap(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			//有阅读权限但没有阅读记录的新增
  			for(int i=0;i<ids.length;i++){
				NoticeCallback back=new NoticeCallback();
				back.setNoticeId(contentInformations.getId());
				back.setUserName(names[i]);
				back.setUserID(ids[i]);
				back.setIsRead("未读");
				contentInformationsService.saveNoticeBack(back);
  			}
		}
		//上传附件
		uploadAttach();
		updateFlag = "1";
//		try {
//			//抢单后的审核 信息推送
//			Map send = new HashMap();
//
//			send.put("type", "1");
//			JSONObject json = new JSONObject();
//			json.put("id", contentInformations.getId());
//			send.put("content",json.toString());
//				//信息推送
//			JPushClient jpush = new JPushClient();
////				jpush.sendAndroidNotificationWithAlias("通知公告",contentInformations.getInfoTitle(), send,ids);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//发送短信
		/*for(int j=0;j<ids.length;j++){
			Map mm=new HashMap();
			mm.put("createUserId", ids[j]);
			String s="";
			String ss="";
		    List<SecProMan> list1=secProManService.getTelephone(mm);//安全生产管理经理号码
		    if(list1.size()!=0){
			    for(SecProMan s1:list1){
			        s+=s1.getManagerPhone()+";";
			    }
		    }
		   
			List<SecProChaPer> list2=secProChaPerService.getTelephone(mm);//安全生产管理人员号码
			 if(list2.size()!=0){
				 for(SecProChaPer s2:list2){
				        ss+=s2.getChargePhone()+";";
				    }
			 }
			String all=s+ss;
			if(!all.equals("")){
				all=all.substring(0,all.length()-1);
				SendSms sendSms =new SendSms();
				String result = sendSms.send(all,"您有一条未读公告:"+ contentInformations.getInfoTitle()+",请尽快登录智慧安监平台阅读！");
				System.out.print(result);
			}
		}*/
		
		 return RELOAD;
	
		
	}
	
	/**
	 * 更新附件
	 */
	private boolean uploadAttach() {

		try {
			if (file != null && file.size() > 0) {
				for (int i = 0; i < file.size(); i++) {
					File tempFile = file.get(i);
					String fileName = fileFileName.get(i);
					
					UploadFile uploadFile = new UploadFile();
					uploadFile.setFileName(fileName);
					uploadFile.setFileType(getFileSuffix(fileName));
					uploadFile.setUploadFile(tempFile);
					String rename = UUID.randomUUID().toString() + "."
							+ getFileSuffix(fileName);
					String path = Struts2Util.getServletContext().getRealPath("/");
					uploadFile.setId(rename);
//					uploadFile.setFilePath(path
//							+ SysPropertiesUtil.getProperty("uploadFile")
//							+ File.separator
//							+ DateUtil.convertDateToString(
//									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
//									+ File.separator);
					String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
					root = root.substring(0,root.indexOf("webapps")+8);
					root = root.replaceAll("\\\\", "/");
					root = root.replace("webapps","virtualdir/upload");
					StringBuffer destFName = new StringBuffer();
					destFName.append(root).append("attach/");
					uploadFile.setFilePath(destFName.toString());
					System.out.println("Attach filepath: "+uploadFile.getFilePath());
					uploadFile.uploadToServer();
//					uploadFile.setFilePath(
//							SysPropertiesUtil.getProperty("uploadFile")
//							+ File.separator
//							+ DateUtil.convertDateToString(
//									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
//									+ File.separator);
					/** **将附件存入数据库**** */
					ContentAttachs contentAttaFile = new ContentAttachs();
					contentAttaFile.setDeptId(this.getLoginUserDepartmentId());
					contentAttaFile.setDelFlag(0);
					contentAttaFile.setInfoId(contentInformations.getId());
					contentAttaFile.setAttachName(rename);
					contentAttaFile.setDocName(uploadFile.getFileName());
					//contentAttaFile.setDocType(this.getAttachType().get(i));
					contentAttaFile.setDocUrl("/upload/attach/");
					String url = SysPropertiesUtil.getProperty("httpurl");
					contentAttaFile.setHttpUrl(uploadFile.getFilePath());
					contentAttaFile.setNwUrl(SysPropertiesUtil.getProperty("nwurl"));
					Long filesize=uploadFile.getUploadFile().length();
					contentAttaFile.setFileSize(Long.toString(filesize));
					contentAttachsService.save(contentAttaFile);	
				}
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 删除信息附件信息
	 */
	public void deleteInfoAttach() throws Exception {
		contentAttachsService.deleteAttachById(attachId);
	}
    
	/**
	 * 下载附件
	 */
	public void download() {
		try {
			ContentAttachs contentAttach = contentAttachsService.getById(attachId);
//			String path = Struts2Util.getServletContext().getRealPath("/");
			String filePath =contentAttach.getHttpUrl() + contentAttach.getAttachName();
			File fis = new File(filePath);
			if (fis.exists()) {
				InputStream in = new FileInputStream(fis);
				/*****处理花字符*************************/
				String browName=contentAttach.getDocName();
				browName = new String(browName.getBytes("GBK"), "ISO-8859-1");
				String clientInfo = this.getRequest().getHeader("User-agent");
			    if(clientInfo != null && clientInfo.indexOf("MSIE") > 0 ){//IE采用URLEncoder方式处理
				    if(clientInfo.indexOf("MSIE 6") > 0 || clientInfo.indexOf("MSIE 5") > 0){//IE6，用GBK，此处实现由局限性
				      browName = new String(contentAttach.getDocName().getBytes("GBK"),"ISO-8859-1");
				    }else{//ie7+用URLEncoder方式
				     browName = java.net.URLEncoder.encode(contentAttach.getDocName(), "UTF-8");
				    }
			    }
               /*************************************/
				Struts2Util.getResponse().addHeader("Content-Disposition","attachment;filename="+ browName);
				OutputStream out = Struts2Util.getResponse().getOutputStream();
				try {
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					in.close();
					out.close();
				}
			}

		} catch (Exception e) {
		}

	}

	/**
	 * 删除信息,返回json{result:true/false}
	 */
	public String delete() throws Exception{
	    try{
			contentInformationsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 获取文件后缀字符串
	 */
	public String getFileSuffix(String fileName) {
		String filesuffix = null;
		StringTokenizer fx = new StringTokenizer(fileName, ". ");
		while (fx.hasMoreTokens()) {
			filesuffix = fx.nextToken();
		}
		return filesuffix;
	}
	/**
	 * 上传附件
	 */
	/*public void uploadFile() {
		try {
			if (file != null && file.isFile() == true) {
				String path = Struts2Util.getServletContext().getRealPath("/")
						+ "upload\\"
						+ DateUtil.convertDateToString(DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
						+ "\\";
				String rename = UUID.randomUUID().toString() + "."+ getFileSuffix(fileFileName);
				ContentAttachs attach = new ContentAttachs();
				attach.setDocType(getFileSuffix(fileFileName));
				attach.setDocUrl(path);
				attach.setDocName(fileFileName);
				attach.setAttachName(rename);
				attach.setDelFlag(0);
				contentAttachsService.save(attach);
				
//				UploadFile uploadFile = new UploadFile();
//				uploadFile.setFileName(fileFileName);
//				uploadFile.setFileType(getFileSuffix(fileFileName));
//				uploadFile.setUploadFile(file);
////				String rename = UUID.randomUUID().toString() + "."+ getFileSuffix(fileFileName);
//				uploadFile.setId(rename);
//				uploadFile.setFilePath(path);
//				uploadFile.uploadToServer();
				//multipartFile.transferTo(tempFile);
				for (int i = 0; i < Filedata.size(); i++) {
					File file = Filedata.get(i);
					UploadFile uploadFile = new UploadFile();
					uploadFile.setFileName(FiledataFileName.get(i));
					uploadFile.setFileType(FiledataContentType.get(i));
					uploadFile.setUploadFile(file);
					String rename = UUID.randomUUID().toString() + "."
							+ getFileSuffix(FiledataFileName.get(i));
					String path = Struts2Util.getServletContext().getRealPath(
							"/");
					uploadFile.setId(rename);
					uploadFile.setFilePath(path
							+ "upload\\"
							+ DateUtil.convertDateToString(
									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
							+ "\\");
					uploadFile.uploadToServer();
					uploadFiles.add(uploadFile);
				}
				this.getResponse().getWriter().println("{\"result\":\"y\"}");
			}
		} catch (Exception e) {
			try {
				this.getResponse().getWriter().println("{\"result\":\"n\"}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}*/

	/**
	 * 激活被禁用的信息,返回json{result:true/false}
	 * @return
	 */
	public String activeInfo(){
		if(contentInformations!=null && contentInformations.getId()!=null){
			contentInformationsService.activeInfo(contentInformations.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.INFORMATION_NULL_EXCEPIION);
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
			throw ex;
		}
		return null;
	}
	
	/**
	 * 禁用信息,返回json{result:true/false}
	 * @return
	 */
	public String inactiveInfo(){
		if(contentInformations!=null && contentInformations.getId()!=null){
			contentInformationsService.inactiveInfo(contentInformations.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.INFORMATION_NULL_EXCEPIION);
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
			throw ex;
		}
		return null;
	}
	
	/**
	 * 设置信息过期,返回json{result:true/false}
	 * @return
	 */
	public String expireInfo(){
		if(contentInformations!=null && contentInformations.getId()!=null){
			contentInformationsService.expireInfo(contentInformations.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.INFORMATION_NULL_EXCEPIION);
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
			throw ex;
		}
		return null;
	}
	
	/**
	 * 设置信息使用,返回json{result:true/false}
	 * @return
	 */
	public String inexpireInfo(){
		if(contentInformations!=null && contentInformations.getId()!=null){
			contentInformationsService.inexpireInfo(contentInformations.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.INFORMATION_NULL_EXCEPIION);
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
			throw ex;
		}
		return null;
	}
	
	
	/**
  	 * 跳转部门用户列表
  	 */
  	public String selectUsers()
  	{
  		users=userService.findAllUsersByDept("");
  		return SUCCESS;
  	}
  	
  	/**
  	 * 查询部门用户列表
  	 */
  	public void getDepartUser() throws IOException
  	{
  		Map map = new HashMap();
  		JSONArray jsonArry = new JSONArray();
  		List<User> userList = contentInformationsService.getAllUsersByMap(map);
		for(User user : userList){
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", user.getId());
	  		jsonObject.put("pId",user.getDeptCode());
	  		jsonObject.put("name", user.getDisplayName());
	  		jsonObject.put("flag", "1");
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
	  		if(userIds.contains(user.getId())){
	  			jsonObject.put("checked",true);
	  		}
	  		jsonArry.add(jsonObject);
		}
		List<Dept> deptList = contentInformationsService.getAllDepartByMap(map);
		for (Dept dept : deptList) {
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", dept.getId());
	  		Map map1 = new HashMap();
	  		map1.put("sqlID", "findsUserIds");
	  		map1.put("deptcode", dept.getId());
	  		List<Map> list=contentInformationsService.getUserIds(map1);
	  		String ids="";
	  		if(list!=null&&list.size()!=0){
	  		for(Map mm:list){
	  			String id=mm.get("id").toString();
	  			ids+=id+",";
	  		}
	  		}
	  		if(compare(ids,userIds)&&(userIds!="")){
	  			jsonObject.put("checked",true);
	  		}
	  		if(dept.getParentId() == null || "".equals(dept.getParentId()))
	  		{
	  			jsonObject.put("pId", "-1");
	  		
	  		}
	  		else
	  		{
	  			jsonObject.put("pId", dept.getParentId());
	  		}
	  		jsonObject.put("name", dept.getDeptName());
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
	  		jsonArry.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
  		jsonObject2.put("id", "-1");
  		jsonObject2.put("pId","1");
  		jsonObject2.put("open", true);
  		String[] id = userIds.split(",");
  		if(id.length==(userList.size())){
  			jsonObject2.put("checked",true);
  		}
  		jsonObject2.put("name", "全选");
  		jsonObject2.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
  		jsonArry.add(jsonObject2);
		getResponse().getWriter().write(jsonArry.toString());
  	}
	
  	public boolean compare(String s1,String s2){
  		boolean a=false;
  		if(!s1.equals("")){
  		String ids1[]=s1.split(",");//S1表示部门ids拼接字符串，S2表示传入的字符串
  		for(int i=0;i<ids1.length;i++){
  			if(s2.contains(ids1[i])){
  				a=true;
  			}else{
  				a=false;
  				break;
  			}
  		}
  		}
  		return a;
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

	public ContentInformations getContentInformations(){
		return this.contentInformations;
	}

	public void setContentInformations(ContentInformations contentInformations){
		this.contentInformations = contentInformations;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPublicDateStart(){
		return this.queryPublicDateStart;
	}

	public void setQueryPublicDateStart(Date queryPublicDateStart){
		this.queryPublicDateStart = queryPublicDateStart;
	}

	public Date getQueryPublicDateEnd(){
		return this.queryPublicDateEnd;
	}

	public void setQueryPublicDateEnd(Date queryPublicDateEnd){
		this.queryPublicDateEnd = queryPublicDateEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIsDelshow() {
		return isDelshow;
	}

	public void setIsDelshow(String isDelshow) {
		this.isDelshow = isDelshow;
	}

	public String getIsExpireshow() {
		return isExpireshow;
	}

	public void setIsExpireshow(String isExpireshow) {
		this.isExpireshow = isExpireshow;
	}

//	public List<File> getUpload() {
//		return upload;
//	}
//
//	public void setUpload(List<File> upload) {
//		this.upload = upload;
//	}
//
//	public List<String> getUploadFileName() {
//		return uploadFileName;
//	}
//
//	public void setUploadFileName(List<String> uploadFileName) {
//		this.uploadFileName = uploadFileName;
//	}
//
//	public List<String> getUploadContentType() {
//		return uploadContentType;
//	}
//
//	public void setUploadContentType(List<String> uploadContentType) {
//		this.uploadContentType = uploadContentType;
//	}

	public List<String> getAttachType() {
		return attachType;
	}

	public void setAttachType(List<String> attachType) {
		this.attachType = attachType;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	public List<NoticeCallback> getNoticelist() {
		return noticelist;
	}

	public void setNoticelist(List<NoticeCallback> noticelist) {
		this.noticelist = noticelist;
	}

	public NoticeCallback getNoticeCallback() {
		return noticeCallback;
	}

	public void setNoticeCallback(NoticeCallback noticeCallback) {
		this.noticeCallback = noticeCallback;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	/*public File getFile() {
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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}*/

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}


}
