package com.jshx.http.command;

import java.io.Reader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.HibernateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;

public class GetNoticeFeedBackCommand implements Command{
	private ContentInformationsService contentInformationsService = (ContentInformationsService)SpringContextHolder.getBean("contentInformationsService");
	private UserServiceImpl userService = (UserServiceImpl)SpringContextHolder.getBean("userService");
	private ContentAttachsService contentAttachsService = (ContentAttachsService)SpringContextHolder.getBean("contentAttachsService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
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
	 /**
	    String infoContent = "";
		char[] ac = new char['È'];
		Clob contentClob = contentInformations.getInfoContent();
		if (contentClob != null) {
			int i;
			Reader reader = null;
			try
			{
				reader = contentClob.getCharacterStream();
				while ((i = reader.read(ac, 0, 200)) != -1)
				{
					infoContent += new String(ac, 0, i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				}
				catch (Exception localException1) {
				}
			}
		}
		*/
	    JSONObject json = new JSONObject();
	    json.put("infoTitle", contentInformations.getInfoTitle());
	    String infoContent=contentInformations.getInfoContent();
//	    String url = SysPropertiesUtil.getProperty("httpurl");
//	    if(infoContent.contains("<img src=\"")){
//	    	infoContent=infoContent.replaceAll("<img src=\"","<img src=\""+url);
//	    }
	    json.put("infoContent", infoContent);
	    Map m = new HashMap();
		m.put("codeName", "信息类型");
		m.put("itemValue", contentInformations.getInfoType());
		String type=codeService.findCodeValueByMap(m).getItemText();
	    json.put("infoType", type);
	    json.put("deptName", contentInformations.getDept().getDeptName());
	    json.put("userName", contentInformations.getUser().getDisplayName());
	    json.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contentInformations.getPublicDate()));
	    Map map = new HashMap();
		map.put("linkId",contentInformations.getLinkId());
		map.put("mkType", "contentInformations");
		map.put("picType","contentInformationsfj");
		DataBean bean = httpService.getPhotoListByType(map);
		String filepath = bean.getRname();
		json.put("filePath",StringTools.NullToStr(filepath,""));//附件url
		bd.setCode("0");
		bd.setMessage("反馈成功");
		bd.setContent(json.toString());
		return bd;
	 }
}
