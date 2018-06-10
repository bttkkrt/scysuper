package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.jpush.api.JPushClient;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.service.ReportWorkService;
import com.jshx.reportWorkReceiver.entity.ReportWorkReceiver;
/**
 * 保存工作上报
 * @author hanxc 20150403
 */
public class SaveReportWorkCommand implements Command{
	private ReportWorkService reportWorkService=(ReportWorkService) SpringContextHolder.getBean("reportWorkService");
	private UserService userService=(UserService) SpringContextHolder.getBean("userService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	 
	@SuppressWarnings("unchecked")  
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String reportWorkId = (String)json.get("id");//获取主键id
		String deptId = (String)json.get("deptId");//获取上报人部门ID
		String loginUserId = json.getString("loginUserId");//获取上报人id
		String userName = json.getString("userName");//获取上报人名称
		String userIds = json.getString("userIds");//获取接收人id
		String userNames = json.getString("userNames");//获取接收人名称
		String workTitle = json.getString("workTitle");//获取工作上报标题
		String workContent = json.getString("workContent");//获取工作上报内容

		try {
			userNames = new String(userNames.getBytes("ISO-8859-1"),"utf-8");
			workTitle = new String(workTitle.getBytes("ISO-8859-1"),"utf-8");
			workContent = new String(workContent.getBytes("ISO-8859-1"),"utf-8");
			userName = new String(userName.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String[] ids = userIds.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try {
			ReportWork reportWork = new ReportWork();
			if(reportWorkId == null || "".equals(reportWorkId)){//新增
				reportWork.setDeptId(deptId);
				reportWork.setDelFlag(0);
				reportWork.setUserName(userName);
				reportWork.setCreateTime(new Date());
				reportWork.setCreateUserID(loginUserId);
				reportWork.setTime(sdf.format(d));
				reportWork.setUserNames(userNames);
				reportWork.setUserIds(userIds);
				reportWork.setWorkTitle(workTitle);
				reportWork.setWorkContent(workContent);
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
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				reportWork.setProId(linkId);
				reportWorkService.save(reportWork);
			}else{
				reportWork = reportWorkService.getById(reportWorkId);
				reportWork.setUserNames(userNames);
				reportWork.setUserIds(userIds);
				reportWork.setWorkTitle(workTitle);
				reportWork.setWorkContent(workContent);
				//先删除接收人 
				
				//再新增接收人
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
			Department dept = deptService.findDeptById(deptId);
			//推送消息到手机端 hanxc 20150409
			try {
				JPushClient jpush = new JPushClient();
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", reportWork.getId());
				map.put("workTitle", StringTools.NullToStr(reportWork.getWorkTitle(),""));
				map.put("deptName", StringTools.NullToStr(null != dept?dept.getDeptName():"",""));
				map.put("time", StringTools.NullToStr(reportWork.getTime(),""));
				map.put("userName", StringTools.NullToStr(reportWork.getUserName(),""));
				map.put("entityFlag", "reportWork");
				jpush.sendAndroidNotificationWithAlias("抚顺安监", reportWork.getWorkTitle(), map, ids);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", reportWork.getProId());
			bd.setContent(jn.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}
}
