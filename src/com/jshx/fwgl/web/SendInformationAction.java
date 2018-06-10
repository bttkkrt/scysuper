package com.jshx.fwgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import cn.jpush.api.JPushClient;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.fwgl.entity.SendInformation;
import com.jshx.fwgl.service.SendInformationService;

public class SendInformationAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SendInformation sendInformation = new SendInformation();
	
    private CheckRecord checkRecord=new CheckRecord();
	
	private NoticeCallback noticeCallback=new NoticeCallback();//是否阅读

	/**
	 * 业务类
	 */
	@Autowired
	private SendInformationService sendInformationService;
	
	@Autowired
	private CheckRecordService checkRecordService;
	
	@Autowired
	private ContentAttachsService contentAttachsService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private ContentInformationsService contentInformationsService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date querySendinfoSignTimeStart;

	private Date querySendinfoSignTimeEnd;

	private Date querySendinfoDraftTimeStart;

	private Date querySendinfoDraftTimeEnd;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	/** 文件 */
	private List<File> Filedata;

	/** 文件名 */
	private List<String> FiledataFileName;

	/** 文件内容类型 */
	private List<String> FiledataContentType;
	
	/**
	 * 从页面传递的信息内容
	 */
	private String infoContent;
	
	private User user;
    
	private  String roleName;
	
	/**
	 * 附件信息
	 * */
	private List<File> upload;
	
	private List<String> uploadFileName;

	private List<String> uploadContentType;
	
	/**附件类型*/
	private List<String> attachType;
	
	/**查看详情和修改中的附件列表*/
	private List<ContentAttachs> attachList;
	
	/**查看详情中的附件个数*/
	private int attachCount;
	
	/**用来删除和下载附件*/
	private String attachId;
	
	private String userIds;
	
	private String userNames;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	
	private List<NoticeCallback> noticelist=new ArrayList<NoticeCallback>(); //阅读人数

	private boolean canCheck=false;
	
	public String init(){
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			if("A02".equals(r.getRole().getRoleCode())){
				canCheck=true;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != sendInformation){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != sendInformation.getSendinfoTitle()) && (0 < sendInformation.getSendinfoTitle().trim().length())){
				paraMap.put("sendinfoTitle", "%" + sendInformation.getSendinfoTitle().trim() + "%");
			}
			if ((null != sendInformation.getSendinfoNum()) && (0 < sendInformation.getSendinfoNum().trim().length())){
				paraMap.put("sendinfoNum", "%" + sendInformation.getSendinfoNum().trim() + "%");
			}
			if ((null != sendInformation.getSendinfoDraftUsername()) && (0 < sendInformation.getSendinfoDraftUsername().trim().length())){
				paraMap.put("sendinfoDraftUsername", "%" + sendInformation.getSendinfoDraftUsername().trim() + "%");
			}
			if ((null != sendInformation.getSendinfoCheckUserid()) && (0 < sendInformation.getSendinfoCheckUserid().trim().length())){
				paraMap.put("sendinfoCheckUserid", "%" + sendInformation.getSendinfoCheckUserid().trim() + "%");
			}

			if ((null != sendInformation.getSendinfoUsernames()) && (0 < sendInformation.getSendinfoUsernames().trim().length())){
				paraMap.put("sendinfoUsernames", "%" + sendInformation.getSendinfoUsernames().trim() + "%");
			}
			if ((null != sendInformation.getAuditState()) && (0 < sendInformation.getAuditState().trim().length())){
				paraMap.put("auditState", sendInformation.getAuditState().trim());
			}
			List<UserRight> list = (List<UserRight>) this.getLoginUser().getUserRoles();//判断登录角色
			for(UserRight u:list){
			String code = u.getRole().getRoleCode();
			if("A02".equals(code)){
		        roleName="A02";
			  }
			}
			if(roleName!="A02"){
				paraMap.put("createUserID", this.getLoginUser().getId());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|sendinfoTitle|sendinfoUsernames|auditState|sendinfoNum|sendinfoDraftUsername|createUserID|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = sendInformationService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != sendInformation)&&(null != sendInformation.getId())){
			sendInformation = sendInformationService.getById(sendInformation.getId());
			if(sendInformation.getLinkId() == null || "".equals(sendInformation.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					sendInformation.setLinkId(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",sendInformation.getLinkId());
						map.put("mkType", "sendInformation");
						map.put("picType","sendInformationfj");
						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			infoContent = sendInformation.getInfoContent();
			
		
		//审核记录
		Map<String, Object> paraMap1 = new HashMap<String, Object>();
		paraMap1.put("infoId", sendInformation.getId());
		checkRecords=checkRecordService.findCheckRecord(paraMap1);
		
		user = this.getLoginUser();
			//获取所有有阅读权限的人的id和名称
			String userIds = sendInformation.getUserIds();
			//如果当前登录人有阅读权限
			if(userIds.contains(user.getId()))
			{
	  			//查询当前登录人阅读记录，若有，则更新，若没有，则新增
				List<NoticeCallback> readed= sendInformationService.geReadedUsersIds(sendInformation.getId(),user.getId());
	  			if(readed.size()<=0||null==readed)
	  			{
	  				SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  				noticeCallback.setNoticeId(sendInformation.getId());
	  				noticeCallback.setUserName(user.getDisplayName());
	  				noticeCallback.setUserID(user.getId());
	  				noticeCallback.setIsRead("已读");
	  				noticeCallback.setLastReadTime(sm.format(new Date()));
	  				noticeCallback.setUpdateUserID(user.getId());
	  				sendInformationService.saveNoticeBack(noticeCallback);
	  				int realnum = sendInformation.getRealnum() + 1;
	  				sendInformation.setRealnum(realnum);
	  				sendInformationService.update(sendInformation);
	  			}else
	  			{
	  				SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  				noticeCallback = readed.get(0);
	  				if("未读".equals(noticeCallback.getIsRead()))
	  				{
  	  				int realnum = sendInformation.getRealnum() + 1;
  	  			sendInformation.setRealnum(realnum);
  	  		    sendInformationService.update(sendInformation);
	  				}
	  				noticeCallback.setIsRead("已读");
	  				noticeCallback.setLastReadTime(sm.format(new Date()));
	  				sendInformationService.updateNoticeBack(noticeCallback);
	  			}
			}
			Map map=new HashMap();
  			map.put("id", sendInformation.getId());
  			try {
				noticelist= sendInformationService.geBackById(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			sendInformation.setLinkId(linkId);
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
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		//获取有阅读权限人数
		Map<String, Object> paraMap = new HashMap<String, Object>();//把能审核的安监局领导阅读记录加进去
		paraMap.put("sqlId", "queryAjjldByMap");
		List<Map<String, Object>>list =sendInformationService.findAJJldListByMap(paraMap); //获取安监局领导角色的Id和name
		String ajjldids="";
		String ajjldnames="";
		String userIdsAll="";
		String userNamesAll="";
		for(Map m: list){
				String ajjldname= m.get("name").toString();
				 ajjldnames+=ajjldname+",";
				String ajjldid= m.get("id").toString();
			    ajjldids+=ajjldid+",";
			}//ajjldnames为拼接后的名字，ajjldids为拼接后的id
		String userIds = sendInformation.getUserIds();
		userIdsAll=links(userIds,ajjldids);
		sendInformation.setUserIds(userIdsAll);
		String[] ids = userIdsAll.split(",");
		String userNames = sendInformation.getUserNames()+",";
		userNamesAll=links(userNames,ajjldnames);
		sendInformation.setUserNames(userNamesAll);
		String[] names = userNamesAll.split(",");
		sendInformation.setViewnum(ids.length);
		sendInformation.setRealnum(0);
		sendInformation.setAuditState("待审核");
		User user=userService.findUserByLoginId(this.getLoginUser().getLoginId());
		sendInformation.setUserName(user.getDisplayName());
		if ("add".equalsIgnoreCase(this.flag)){
			Date d = new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			sendInformation.setUserId(this.getLoginUser().getId());
			sendInformation.setDeptId(this.getLoginUserDepartmentId());
			sendInformation.setDelFlag(0);
			sendInformation.setExpireFlag("0");
			sendInformation.setReaded("未读");
			sendInformation.setCreateTime(d);
			sendInformation.setCreateUserID(getLoginUser().getId());
			sendInformation.setTime(dateStr);
			sendInformation.setSendinfoDraftTime(d);
			sendInformationService.save(sendInformation);
			sendInformation.setInfoContent(infoContent);
			sendInformationService.update(sendInformation);
			//有阅读权限但没有阅读记录的新增
  			for(int i=0;i<ids.length;i++){
				NoticeCallback back=new NoticeCallback();
				back.setNoticeId(sendInformation.getId());
				back.setUserName(names[i]);
				back.setUserID(ids[i]);
				back.setIsRead("未读");
				sendInformationService.saveNoticeBack(back);
  			}
  			
		}else{
			Date d = new Date();
			sendInformation.setSendinfoDraftTime(d);
			sendInformation.setInfoContent(infoContent);
			sendInformationService.update(sendInformation);
			//删除已有阅读记录
			Map map = new HashMap();
			map.put("noticeId", sendInformation.getId());
			sendInformationService.deleteNoticeBackByMap(map);
  			//有阅读权限但没有阅读记录的新增
  			for(int i=0;i<ids.length;i++){
				NoticeCallback back=new NoticeCallback();
				back.setNoticeId(sendInformation.getId());
				back.setUserName(names[i]);
				back.setUserID(ids[i]);
				back.setIsRead("未读");
				sendInformationService.saveNoticeBack(back);
		      }
  			
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != sendInformation)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到sendInformation中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			sendInformationService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
  	 * 跳转部门用户列表
  	 */
  	public void getDepartUser() throws IOException
  	{
  		Map map = new HashMap();
  		JSONArray jsonArry = new JSONArray();
  		List<User> userList = sendInformationService.getAllUsersByMap(map);
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
		List<Dept> deptList = sendInformationService.getAllDepartByMap(map);
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
	
	/**
  	 * 查询部门用户列表
  	 */
  	public String selectUsers()
  	{
  		return SUCCESS;
  	}
  	
  	/**
	 * 审核信息
	 */
	public String check() throws Exception{
		view();
	    return "check";
	}
	
	/**
	 * 保存审核信息
	 */
	public String checkSave() throws Exception{
		sendInformation=sendInformationService.getById(sendInformation.getId());
		sendInformation.setAuditState(checkRecord.getCheckResult());
		Date date=new Date();
		sendInformation.setSendinfoSignTime(date);
		sendInformation.setSendinfoSignerName(this.getLoginUser().getDisplayName());
		sendInformationService.update(sendInformation);
		checkRecord .setCheckUserid(this.getLoginUser().getId());
		checkRecord.setDelFlag(0);
		checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
		checkRecordService.save(checkRecord);
		
		try {
			// 审核通过 信息推送
			if("审核通过".equals(checkRecord.getCheckResult())){
				Map send = new HashMap();
                String[] userIds = sendInformation.getUserIds().split(",");
				send.put("type", "2.2");
				JSONObject json = new JSONObject();
				json.put("id", sendInformation.getId());
				send.put("content",json.toString());
					//信息推送
				JPushClient jpush = new JPushClient();
				jpush.sendAndroidNotificationWithAlias("园区安监发文",sendInformation.getSendinfoTitle(), send,userIds);
		
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	    return RELOAD;
	}
	
	/**
	 * 更新附件
	 */
	private boolean uploadAttach() {

		try {
			if (upload != null && upload.isEmpty() == false) {
				for (int i = 0; i < upload.size(); i++) {
					ContentAttachs contentAttaFile = new ContentAttachs();
					File file = upload.get(i);
					UploadFile uploadFile = new UploadFile();
					uploadFile.setFileName(uploadFileName.get(i));
					//uploadFile.setFileType(uploadContentType.get(i));
					uploadFile.setUploadFile(file);
					String rename = UUID.randomUUID().toString() + "."
							+ getFileSuffix(uploadFileName.get(i));
					String path = Struts2Util.getServletContext().getRealPath(
							"/");
					uploadFile.setId(rename);
					uploadFile.setFilePath(path
							+ SysPropertiesUtil.getProperty("uploadFile")
							+ "\\"
							+ DateUtil.convertDateToString(
									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
							+ "\\");
					uploadFile.uploadToServer();
					uploadFile.setFilePath(SysPropertiesUtil
							.getProperty("uploadFile")
							+ "\\"
							+ DateUtil.convertDateToString(
									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
							+ "\\");
					/** **将附件存入数据库**** */
					//contentAttaFile.setDeptId(this.getLoginUserDepartmentId());
					contentAttaFile.setDelFlag(0);
					contentAttaFile.setInfoId(sendInformation.getId());
					contentAttaFile.setAttachName(rename);
					contentAttaFile.setDocName(uploadFile.getFileName());
					//contentAttaFile.setDocType(this.getAttachType().get(i));
					contentAttaFile.setDocUrl(uploadFile.getFilePath());
					String url = SysPropertiesUtil.getProperty("httpurl");
					contentAttaFile.setHttpUrl(url);
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
			String filePath = new String();
			ContentAttachs contentAttach = contentAttachsService.getById(attachId);
			filePath = contentAttach.getDocUrl();
			String path = Struts2Util.getServletContext().getRealPath("/");
			File fis = new File(path + filePath + contentAttach.getAttachName());
			if (fis.exists()) {
				InputStream in = new FileInputStream(fis);
				/*****处理花字符*************************/
				String browName=new String();
				String clientInfo = this.getRequest().getHeader("User-agent");
			    if(clientInfo != null && clientInfo.indexOf("MSIE") > 0 ){//IE采用URLEncoder方式处理
			    if(clientInfo.indexOf("MSIE 6") > 0 || clientInfo.indexOf("MSIE 5") > 0){//IE6，用GBK，此处实现由局限性
			      browName = new String(contentAttach.getDocName().getBytes("GBK"),"ISO-8859-1");
			       }else{//ie7+用URLEncoder方式
			     browName = java.net.URLEncoder.encode(contentAttach.getDocName(), "UTF-8");
			     }
			    }
               /*************************************/
				Struts2Util.getResponse()
						.addHeader(
								"Content-Disposition",
								"attachment;filename="
										+ browName);
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
			e.printStackTrace();

		}

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
	public List<UploadFile> uploadFile() {
		try {
			List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
			if (Filedata != null && Filedata.isEmpty() == false) {
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
			}
			return uploadFiles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	
	public String links(String s1,String s2){//去除拼接字符串中重复的元素
		String d="";
		String c=s1+s2;
		 String str[]=c.split(",");
		  List<String> slist=new ArrayList<String>();
		  for(int i=0;i<str.length;i++){
			  if(!slist.contains(str[i])){
				  slist.add(str[i]);
			  }
		  }
		  for(int i=0;i<slist.size();i++){
			  d+=slist.get(i)+",";
		  }
		return d;
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

	public SendInformation getSendInformation(){
		return this.sendInformation;
	}

	public void setSendInformation(SendInformation sendInformation){
		this.sendInformation = sendInformation;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQuerySendinfoSignTimeStart(){
		return this.querySendinfoSignTimeStart;
	}

	public void setQuerySendinfoSignTimeStart(Date querySendinfoSignTimeStart){
		this.querySendinfoSignTimeStart = querySendinfoSignTimeStart;
	}

	public Date getQuerySendinfoSignTimeEnd(){
		return this.querySendinfoSignTimeEnd;
	}

	public void setQuerySendinfoSignTimeEnd(Date querySendinfoSignTimeEnd){
		this.querySendinfoSignTimeEnd = querySendinfoSignTimeEnd;
	}

	public Date getQuerySendinfoDraftTimeStart(){
		return this.querySendinfoDraftTimeStart;
	}

	public void setQuerySendinfoDraftTimeStart(Date querySendinfoDraftTimeStart){
		this.querySendinfoDraftTimeStart = querySendinfoDraftTimeStart;
	}

	public Date getQuerySendinfoDraftTimeEnd(){
		return this.querySendinfoDraftTimeEnd;
	}

	public void setQuerySendinfoDraftTimeEnd(Date querySendinfoDraftTimeEnd){
		this.querySendinfoDraftTimeEnd = querySendinfoDraftTimeEnd;
	}

	public CheckRecord getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(CheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}

	public NoticeCallback getNoticeCallback() {
		return noticeCallback;
	}

	public void setNoticeCallback(NoticeCallback noticeCallback) {
		this.noticeCallback = noticeCallback;
	}

	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}

	public List<NoticeCallback> getNoticelist() {
		return noticelist;
	}

	public void setNoticelist(List<NoticeCallback> noticelist) {
		this.noticelist = noticelist;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
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

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getAttachType() {
		return attachType;
	}

	public void setAttachType(List<String> attachType) {
		this.attachType = attachType;
	}

	public List<ContentAttachs> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<ContentAttachs> attachList) {
		this.attachList = attachList;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
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

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

}
