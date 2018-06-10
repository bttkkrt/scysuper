package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;

/**
 * <终端手机登陆平台>
 * @author gq
 *
 */

public class ChangePasswordCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		
		String userId = obj.getString("userId");
		String oldPassword = obj.getString("oldPassword");
		String newPassword = obj.getString("newPassword");
		
		try {
			User user=userService.findUserById(userId);
			String oldPass = CodeUtil.encode(oldPassword, "MD5");//获取加密后的密码
			if(user.getPassword().equals(oldPass)){
				userService.modifyPassword(userId, newPassword);
				bd.setCode("0");
				bd.setMessage("修改密码成功");
			}else{
				bd.setCode("1");
				bd.setMessage("原始密码错误");
			}
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("修改密码错误");
			e.printStackTrace();
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
