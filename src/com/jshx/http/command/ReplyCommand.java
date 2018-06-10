package com.jshx.http.command;


import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.feedback.entity.Feedback;
import com.jshx.feedback.service.FeedbackService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;

/**
 * <终端手机登陆平台>
 * @author gq
 *
 */

public class ReplyCommand implements Command
{
	private FeedbackService feedbackService = (FeedbackService) SpringContextHolder.getBean("feedbackService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		
		String userId = obj.getString("userId");
		String content = obj.getString("replyContent");
		
		try {
			User user=userService.findUserById(userId);
			Feedback fb=new Feedback();
			fb.setContent(content);
			fb.setUserName(user.getDisplayName());
			fb.setCreateUserID(userId);
			fb.setDelFlag(0);
			feedbackService.save(fb);
			JSONObject json = new JSONObject();
			json.put("id", fb.getId());
			bd.setContent(json.toString());
			bd.setCode("0");
			bd.setMessage("保存成功");
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("保存失败");
			e.printStackTrace();
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
