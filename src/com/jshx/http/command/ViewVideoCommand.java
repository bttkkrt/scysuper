package com.jshx.http.command;


import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.spgl.entity.Spgl;
import com.jshx.spgl.service.SpglService;

/**
 * 保存远程视频浏览记录接口
 * @author 陆婷 2016-6-27
 *
 */
public class ViewVideoCommand implements Command{
	private SpglService spglService = (SpglService) SpringContextHolder.getBean("spglService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String detailName	= obj.getString("detailName");//通道名称
		String puid	= obj.getString("puid");//通道编号
		User user = userService.findUserById(userId);

		try {
			String[] ss = puid.split("-");
			Spgl spgl = new Spgl();
			spgl.setCreateTime(new Date());
			spgl.setCreateUserID(userId);
			spgl.setDelFlag(0);
			spgl.setGuid(ss[0]);
			spgl.setLoginName(user.getDisplayName());
			spgl.setLoginTime(new Date());
			spgl.setVideoName(detailName);
			spglService.save(spgl);
			br.setCode("0");
			br.setMessage("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		} 
		return br;
	}
}
