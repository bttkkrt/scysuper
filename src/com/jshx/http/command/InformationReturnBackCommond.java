package com.jshx.http.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.HibernateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;

public class InformationReturnBackCommond implements Command {

	private ContentInformationsService contentInformationsService = (ContentInformationsService)SpringContextHolder.getBean("contentInformationsService");
	private UserServiceImpl userService = (UserServiceImpl)SpringContextHolder.getBean("userService");
	private ContentAttachsService contentAttachsService = (ContentAttachsService)SpringContextHolder.getBean("contentAttachsService");
	 @SuppressWarnings("unchecked")
		public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String noticeId = obj.getString("noticeId").toString(); 
		String userId = obj.getString("userId").toString(); 
		 
		ContentInformations contentInformations = contentInformationsService.getById(noticeId);
 		User user = userService.findUserById(userId);
		NoticeCallback modelback=new NoticeCallback();
	    List<NoticeCallback> readed= contentInformationsService.geReadedUsersIds(noticeId,user.getId());
	    if(readed.size()<=0||null==readed)
	    {
	    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	    modelback.setNoticeId(noticeId);
	 	    modelback.setUserName(user.getDisplayName());
	 	    modelback.setUserID(user.getId());
	 	    modelback.setIsRead("已读");
	 	    modelback.setLastReadTime(sm.format(new Date()));
	 	    contentInformationsService.saveNoticeBack(modelback);
	 	    int realnum = contentInformations.getRealnum() + 1;
			contentInformations.setRealnum(realnum);
			contentInformationsService.update(contentInformations);
	    }else{
	    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	modelback = readed.get(0);
	    	modelback.setNoticeId(noticeId);
		    modelback.setUserID(user.getId());
		    modelback.setUserName(user.getDisplayName());
		    modelback.setLastReadTime(sm.format(new Date()));
		    if("未读".equals(modelback.getIsRead()))
			{
  				int realnum = contentInformations.getRealnum() + 1;
  				contentInformations.setRealnum(realnum);
  				contentInformationsService.update(contentInformations);
			}
		    modelback.setIsRead("已读");
		    HibernateUtil.getSession().clear();
		    contentInformationsService.updateNoticeBack(modelback);
		    System.out.println("_+_++++++++++该用户已阅读过此条信息且最后一次阅读时间为"+new Date());
	    }
	  //获取公告内容
	    String infoContent = "";
	    infoContent = contentInformations.getInfoContent();
//		char[] ac = new char['È'];
//		Clob contentClob = contentInformations.getInfoContent();
//		if (contentClob != null) {
//			int i;
//			Reader reader = null;
//			try
//			{
//				reader = contentClob.getCharacterStream();
//				while ((i = reader.read(ac, 0, 200)) != -1)
//				{
//					infoContent += new String(ac, 0, i);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					reader.close();
//				}
//				catch (Exception localException1) {
//				}
//			}
//		}
	    JSONObject json = new JSONObject();
	    json.put("infoTitle", contentInformations.getInfoTitle());
	    json.put("infoContent", infoContent);
	    String type = contentInformations.getInfoType();
	    if("0".equals(type))
	    {
	    	type = "内部公告";
	    }
	    else if("1".equals(type))
	    {
	    	type = "企业公告";
	    }
	    json.put("infoType", type);
	    json.put("deptName", contentInformations.getDept().getDeptName());
	    json.put("userName", contentInformations.getUser().getDisplayName());
	    json.put("time", contentInformations.getTime());
	    json.put("readed", contentInformations.getReaded());
	    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ipConfig.properties");    
 		Properties p = new Properties();    
 		try {    
 			p.load(inputStream);    
 		} catch (IOException e1) {   
 			e1.printStackTrace();    
 		}    
 		System.out.println("ip:"+p.getProperty("ip")+",port:"+p.getProperty("port"));   
		Map paraMap = new HashMap();
		paraMap.put("infoId", contentInformations.getId());
		List<ContentAttachs> attachList = contentAttachsService.findContentAttachs(paraMap);
		StringBuffer sbName=new StringBuffer();
		StringBuffer sbPath=new StringBuffer();
		String filename = "";
		String path = "";
		for(ContentAttachs contentAttachs:attachList)
		{
			sbName.append(contentAttachs.getDocName()+",");
			sbPath.append("http://"+p.getProperty("ip")+":"+p.getProperty("port")+ "/upload/attach/"+ contentAttachs.getAttachName()+",");
		}
		if(sbName.length() > 1 && sbPath.length() > 1)
		{
			filename = sbName.substring(0,sbName.length()-1);
			path = sbPath.substring(0,sbPath.length() -1);
		}
		json.put("fileName", filename);
	    json.put("path", path);
		bd.setCode("0");
		bd.setMessage("反馈成功");
		bd.setContent(json.toString());
		return bd;
	 }
}
