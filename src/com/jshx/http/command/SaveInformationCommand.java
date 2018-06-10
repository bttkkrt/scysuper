package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.Hibernate;

import cn.jpush.api.JPushClient;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.JsonToObject;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentInformationsService;
/**
 * 上报信息公告
 * @author 高强  2013-09-6
 */
public class SaveInformationCommand implements Command{
	private ContentInformationsService contentInformationsService=(ContentInformationsService) SpringContextHolder.getBean("contentInformationsService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	 
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = (String)json.get("id");//获取主键id
		String deptId = (String)json.get("deptId");//获取上报人部门ID
		String loginUserId = json.getString("loginUserId");//获取上报人id
		String userName = json.getString("userName");//获取上报人名称
		String userIds = json.getString("userIds");//获取接收人id
		String userNames = json.getString("userNames");//获取接收人名称
		String infoTitle = json.getString("infoTitle");//获取信息公告标题
		String infoContent = json.getString("infoContent");//获取信息公告内容
		String infoType = json.getString("infoType");//获取接收人名称

		try {
			userNames = new String(userNames.getBytes("ISO-8859-1"),"utf-8");
			infoTitle = new String(infoTitle.getBytes("ISO-8859-1"),"utf-8");
			infoContent = new String(infoContent.getBytes("ISO-8859-1"),"utf-8");
			userName = new String(userName.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		ContentInformations contentInformations = new ContentInformations();
		try {
			String[] ids = userIds.split(",");
			String[] names = userNames.split(",");
			if(id == null || "".equals(id)){//新增
				contentInformations.setDeptId(deptId);
				contentInformations.setUserIds(userIds);
				contentInformations.setUserNames(userNames);
				contentInformations.setInfoTitle(infoTitle);
				contentInformations.setInfoType(infoType);
				contentInformations.setViewnum(ids.length);
				contentInformations.setRealnum(0);
				contentInformations.setUserId(loginUserId);
				contentInformations.setDelFlag("0");
				contentInformations.setExpireFlag("0");
				contentInformations.setInfoContent(null);
				contentInformations.setReaded("未读");
				contentInformations.setPublicDate(d);
				contentInformations.setCreateTime(d);
				contentInformations.setCreateUserID(loginUserId);
				contentInformations.setTime(sdf.format(d));
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
	  			contentInformations.setProid(linkId);
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
			}else{
				contentInformations = contentInformationsService.getById(id);
				contentInformations.setUserIds(userIds);
				contentInformations.setUserNames(userNames);
				contentInformations.setInfoTitle(infoTitle);
				contentInformations.setInfoType(infoType);
				//更新公告
				contentInformationsService.update(contentInformations);
				//删除已有阅读记录
				Map map = new HashMap();
				map.put("noticeId", contentInformations.getId());
	  			contentInformationsService.deleteNoticeBackByMap(map);
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
			Department dept = deptService.findDeptById(deptId);
			//推送消息到手机端hanxc 20150409
			try {
				JPushClient jpush = new JPushClient();
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", contentInformations.getId());
				map.put("infoTitle", StringTools.NullToStr(contentInformations.getInfoTitle(),""));
				map.put("deptName", StringTools.NullToStr(null != dept?dept.getDeptName():"",""));
				map.put("time", StringTools.NullToStr(contentInformations.getTime(),""));
				map.put("readed", StringTools.NullToStr(contentInformations.getReaded(),""));
				map.put("userName", StringTools.NullToStr(userName,""));
				map.put("entityFlag", "contentInformations");
				jpush.sendAndroidNotificationWithAlias("抚顺安监", contentInformations.getInfoTitle(), map, ids);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", contentInformations.getProid());
			bd.setContent(jn.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}
}
