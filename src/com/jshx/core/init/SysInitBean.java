/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-14        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.log.service.UserBehaviorService;
import com.jshx.module.admin.security.ShiroRealm;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean.SpringShiroFilter;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.OnlineUserService;
import com.jshx.module.admin.service.SecurityService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-14 上午10:20:37  
 * 系统初始化Bean，装载system.properties的值到内存中 
 */
public class SysInitBean implements InitializingBean {
	
	@Autowired
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;
	
	@Autowired
	private OnlineUserService onlineUserService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private UserBehaviorService userBehaviorService;

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		
		Constants.isLog = Boolean.valueOf(SysPropertiesUtil.getProperty("OPEN_LOG"));
		Constants.MOBILE_NO_UNIQUE = Boolean.valueOf(SysPropertiesUtil.getProperty("MOBILE_NO_UNIQUE"));
		Constants.MULTI_LOGIN = Boolean.valueOf(SysPropertiesUtil.getProperty("MULTI_LOGIN"));
		Constants.AJAXLOGIN = Boolean.valueOf(SysPropertiesUtil.getProperty("AJAXLOGIN"));
		Constants.USER_TAB = Boolean.valueOf(SysPropertiesUtil.getProperty("USER_TAB"));
		
		if(SysPropertiesUtil.getBoolean("NEED_URL_AUTH", true))
			securityService.loadUrlAuth();
		onlineUserService.clear();
		
		
		SpringShiroFilter filter = (SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
		filter.setSecurityService(securityService);
		filter.updateFilterChainDefinitionMap();
		
		
		ShiroRealm shiroRealm = (ShiroRealm)SpringContextHolder.getBean("shiroRealm");
		shiroRealm.initService();
		
		
		// 装载一维代码
		codeService.updateCodeMap();
		//装载用户行为
		userBehaviorService.updateUserBehaviors();
	}

}
