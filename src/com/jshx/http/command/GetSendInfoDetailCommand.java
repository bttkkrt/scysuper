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
import com.jshx.fwgl.entity.SendInformation;
import com.jshx.fwgl.service.SendInformationService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.swgl.entity.ReceiveInformation;
import com.jshx.swgl.service.ReceiveInformationService;

public class GetSendInfoDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SendInformationService sendInformationService = (SendInformationService)SpringContextHolder.getBean("sendInformationService");
	private ContentAttachsService contentAttachsService = (ContentAttachsService)SpringContextHolder.getBean("contentAttachsService");
	private UserServiceImpl userService = (UserServiceImpl)SpringContextHolder.getBean("userService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unchecked")
	public BaseResponse execute(JSONObject obj) {
	SummaryBean bd = new  SummaryBean();
	String noticeId = obj.getString("noticeId").toString(); 
	String userId = obj.getString("userId").toString(); 
	 
	SendInformation sendInformation = sendInformationService.getById(noticeId);
	User user = userService.findUserById(userId);
	NoticeCallback modelback=new NoticeCallback();
    List<NoticeCallback> readed= sendInformationService.geReadedUsersIds(noticeId,user.getId());
    if(readed.size()<=0||null==readed)
    {
    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	    modelback.setNoticeId(noticeId);
 	    modelback.setUserName(user.getDisplayName());
 	    modelback.setUserID(user.getId());
 	    modelback.setIsRead("已读");
 	    modelback.setLastReadTime(sm.format(new Date()));
 	   sendInformationService.saveNoticeBack(modelback);
 	    int realnum = sendInformation.getRealnum() + 1;
 	   sendInformation.setRealnum(realnum);
 	  sendInformationService.update(sendInformation);
    }else{
    	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	modelback = readed.get(0);
    	modelback.setNoticeId(noticeId);
	    modelback.setUserID(user.getId());
	    modelback.setUserName(user.getDisplayName());
	    modelback.setLastReadTime(sm.format(new Date()));
	    if("未读".equals(modelback.getIsRead()))
		{
			int realnum = sendInformation.getRealnum() + 1;
			sendInformation.setRealnum(realnum);
			sendInformationService.update(sendInformation);
		}
	    modelback.setIsRead("已读");
	    HibernateUtil.getSession().clear();
	    sendInformationService.updateNoticeBack(modelback);
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
    json.put("id", sendInformation.getId());//id
	json.put("sendTitle", StringTools.NullToStr(sendInformation.getSendinfoTitle().toString(),""));//发文标题
    json.put("sendNum", StringTools.NullToStr(sendInformation.getSendinfoNum().toString(),""));//发文字号
    Map m = new HashMap();
	m.put("codeName", "发文类型");
	m.put("itemValue", sendInformation.getSendinfoType());
	String sendType=codeService.findCodeValueByMap(m).getItemText();
    json.put("sendType", sendType);//发文类型
    json.put("signName", null==sendInformation.getSendinfoSignerName()?"":sendInformation.getSendinfoSignerName());//签发人姓名
    json.put("signTime", null==sendInformation.getSendinfoSignTime()?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sendInformation.getSendinfoSignTime()));//签发时间
    json.put("sendTime", null==sendInformation.getSendinfoDraftTime()?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sendInformation.getSendinfoDraftTime()));//发文时间
    //json.put("sendContent", StringTools.NullToStr(sendInformation.getInfoContent().toString(),""));//内容
    String infoContent=sendInformation.getInfoContent();
//    String url = SysPropertiesUtil.getProperty("httpurl");
//    if(infoContent.contains("<img src=\"")){
//    	infoContent=infoContent.replaceAll("<img src=\"","<img src=\""+url);
//    }
    json.put("sendContent", infoContent);
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
    
    Map map = new HashMap();
	map.put("linkId",sendInformation.getLinkId());
	map.put("mkType", "sendInformation");
	map.put("picType","sendInformationfj");
	DataBean bean = httpService.getPhotoListByType(map);
	String filepath = bean.getRname();
	json.put("filePath",StringTools.NullToStr(filepath,""));//附件url
	bd.setCode("0");
	bd.setMessage("查询成功");
	bd.setContent(json.toString());
	return bd;
 }
}
