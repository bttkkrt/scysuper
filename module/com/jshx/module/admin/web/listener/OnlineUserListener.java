/**
 * 
 */
package com.jshx.module.admin.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.OnlineUser;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.OnlineUserService;

/**
 * @author f_cheng
 * 用户Session过期后，删除在线列表用户
 */
public class OnlineUserListener implements HttpSessionListener{

	OnlineUserService onlineUserService;
	
	public void sessionCreated(HttpSessionEvent event) {
		//System.out.println("新建session:"+event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		User user = (User)event.getSession().getAttribute(Constants.CURR_USER);
		if(user!= null){
			onlineUserService = (OnlineUserService)getBean("onlineUserService");
			onlineUserService.del(event.getSession().getId());
		}
		
	}

	private Object getBean(String name){
		return SpringContextHolder.getBean(name);
	}
}
