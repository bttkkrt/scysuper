package com.jshx.http.command;

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
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.swgl.entity.ReceiveInformation;
import com.jshx.swgl.service.ReceiveInformationService;

public class GetRecInfoDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ReceiveInformationService receiveInformationService = (ReceiveInformationService)SpringContextHolder.getBean("receiveInformationService");
	private ContentAttachsService contentAttachsService = (ContentAttachsService)SpringContextHolder.getBean("contentAttachsService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private UserServiceImpl userService = (UserServiceImpl)SpringContextHolder.getBean("userService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unchecked")
		public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String noticeId = obj.getString("noticeId").toString(); 
		String userId = obj.getString("userId").toString(); 
		 
		ReceiveInformation receiveInformation = receiveInformationService.getById(noticeId);
		User user = userService.findUserById(userId);
		NoticeCallback modelback=new NoticeCallback();
	    List<NoticeCallback> readed= receiveInformationService.geReadedUsersIds(noticeId,user.getId());
	    if(readed.size()<=0||null==readed)
	    {
	    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	    modelback.setNoticeId(noticeId);
	 	    modelback.setUserName(user.getDisplayName());
	 	    modelback.setUserID(user.getId());
	 	    modelback.setIsRead("已读");
	 	    modelback.setLastReadTime(sm.format(new Date()));
	 	   receiveInformationService.saveNoticeBack(modelback);
	 	    int realnum = receiveInformation.getRealnum() + 1;
	 	   receiveInformation.setRealnum(realnum);
	 	  receiveInformationService.update(receiveInformation);
	    }else{
	    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	modelback = readed.get(0);
	    	modelback.setNoticeId(noticeId);
		    modelback.setUserID(user.getId());
		    modelback.setUserName(user.getDisplayName());
		    modelback.setLastReadTime(sm.format(new Date()));
		    if("未读".equals(modelback.getIsRead()))
			{
				int realnum = receiveInformation.getRealnum() + 1;
				receiveInformation.setRealnum(realnum);
				receiveInformationService.update(receiveInformation);
			}
		    modelback.setIsRead("已读");
		    HibernateUtil.getSession().clear();
		    receiveInformationService.updateNoticeBack(modelback);
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
	    json.put("id", receiveInformation.getId());//id
		json.put("reTitle", StringTools.NullToStr(receiveInformation.getRecinfoTitle().toString(),""));//来文标题
	    json.put("reNum", StringTools.NullToStr(receiveInformation.getRecinfoNum().toString(),""));//来文编号
	    Map m = new HashMap();
		m.put("codeName", "来文类型");
		m.put("itemValue", receiveInformation.getRecinfoType());
		String retype=codeService.findCodeValueByMap(m).getItemText();
	    json.put("retype", retype);//来文类型
	    json.put("reDept", StringTools.NullToStr(receiveInformation.getRecinfoDept().toString(),""));//来文单位
	    json.put("retime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receiveInformation.getRecinfoTime()));//收文时间
	    //json.put("reContent", StringTools.NullToStr(receiveInformation.getInfoContent(),""));//内容
	    String infoContent=receiveInformation.getInfoContent();
//	    String url = SysPropertiesUtil.getProperty("httpurl");
//	    if(infoContent.contains("<img src=\"")){
//	    	infoContent=infoContent.replaceAll("<img src=\"","<img src=\""+url);
//	    }
	    json.put("reContent", infoContent);
	    /**
        审核记录
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("infoId", sendInformation.getId());
	List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
	String records="";
	for(CheckRecord cr:checkRecords){
		records+=sdf.format(cr.getCreateTime())+"，"+cr.getCheckUsername()+" "+cr.getCheckResult()+"["+null==cr.getCheckRemark()?"":cr.getCheckRemark()+"]";
	}
	json.put("auditRecord",records );
	*/
	    //json.put("readed ", StringTools.NullToStr(receiveInformation.getReaded().toString(),""));//阅读状态
	    Map map = new HashMap();
		map.put("linkId",receiveInformation.getLinkId());
		map.put("mkType", "receiveInformation");
		map.put("picType","receiveInformationfj");
		DataBean bean = httpService.getPhotoListByType(map);
		String filepath = bean.getRname();
		json.put("filePath",StringTools.NullToStr(filepath,""));//附件url
		bd.setCode("0");
		bd.setMessage("查询成功");
		bd.setContent(json.toString());
		return bd;
	 }
}
