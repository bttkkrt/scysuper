package com.jshx.module.admin.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.OnlineUser;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.LogService;
import com.jshx.module.admin.service.OnlineUserService;
import com.jshx.module.admin.service.QuicklyStartService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserLinkedDeptService;
import com.jshx.module.admin.service.UserService;

/**
 * 基于Shiro的认证/授权实现
 * 
 * @author Chenjian
 * @version 2013/05/29
 * 
 */
public class ShiroRealm extends AuthorizingRealm {

	//@Autowired
	private UserService userService;
	
	//@Autowired
	private OnlineUserService onlineUserService;
	
	//@Autowired
	//@Qualifier("userDAOIpml")
	private SecurityService securityService;
	
	//@Autowired
	private UserLinkedDeptService userLinkedDeptService;
	
	//@Autowired
	private QuicklyStartService quicklyStartService;
	
	//@Autowired
	private LogService logService;
	
	
	public void initService(){
		userService = (UserService)SpringContextHolder.getBean("userService");
		logService = (LogService)SpringContextHolder.getBean("logService");
		quicklyStartService = (QuicklyStartService)SpringContextHolder.getBean("quicklyStartService");
		userLinkedDeptService = (UserLinkedDeptService)SpringContextHolder.getBean("userLinkedDeptService");
		securityService = (SecurityService)SpringContextHolder.getBean("securityServiceImpl");
		onlineUserService = (OnlineUserService)SpringContextHolder.getBean("onlineUserService");
	}
	
	/*
	 * 授权
	 * 
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		User user = (User) principals.fromRealm(getName()).iterator().next();
		if (user != null) {
			
			if(user.getAuthorizationInfo()!=null){
				return user.getAuthorizationInfo();
			}else{
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				Map<String, List<String>> permissions = user.getPermissions();
				if(permissions!=null){
					Iterator<String> keyIt = permissions.keySet().iterator();
					while(keyIt.hasNext()){
						String roleName = keyIt.next();
						if(!roleName.equals("NO_ROLE"))
							info.addRole(roleName);
						info.addStringPermissions(permissions.get(roleName));
					}
				}
				user.setAuthorizationInfo(info);
				return info;
			}
		}
		
		return null;
	}

	/*
	 * 认证
	 * 
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		if (userName != null && !"".equals(userName)) {
			User user = userService.checkPasswords(userName, new String(token.getPassword()));
			
			if (user != null){
				try{
					if (user.getDelFlag() == 1
							|| ((Department) user.getDept()).getDelFlag() == 1) {
						throw new AuthenticationException("用户或用户的所在部门被禁用！");
					}
				}catch(Exception e){
					e.printStackTrace();
					throw new AuthenticationException(e);
				}
				
				if (checkOnlineUser(user)) {
					throw new AuthenticationException("该用户已在其他位置登陆！");					
				}		
				
				//checkQuickStart(user);

				user.setLogTime(new Date());
				user.setLinkedDepts(userLinkedDeptService.getLinkedDeptByUser(user.getId()));				
				Map<String, User> userMap = (Map<String, User>) Struts2Util
						.getServletContext().getAttribute(Constants.CURR_USERS);
				if (userMap == null)
					userMap = new HashMap<String, User>();
				userMap.put(user.getId(), user);
				Struts2Util.getServletContext().setAttribute(
						Constants.CURR_USERS, userMap);
				Struts2Util.getSession().setAttribute(Constants.CURR_USER, user);
				Struts2Util.getSession().setAttribute(Constants.LOGIN_USER_ID,
						user.getId());
				// 登录日志
				saveLog(user, "电脑");
				
				Struts2Util.getSession().setAttribute("autoDatagridHeight",
	        			SysPropertiesUtil.getProperty("autoDatagridHeight"));
				
				return new SimpleAuthenticationInfo(user,
						user.getPassword(), getName());
			}else{
				throw new AuthenticationException(Constants.LOGIN_ERROR);
			}
			
		}
		throw new AuthenticationException("未输入用户名");
	}
	
	/**
	 * 检查用户quickstart模块列表和角色权限模块列表是否不在一个集合内
	 * 
	 * @param user
	 */
	private void checkQuickStart(User user) {
		// 得到用户的所有角色对应的模块列表
		List<String> moduleCodeList = securityService.findMyModuleCode(user
				.getId());

		// 得到用户的quickstart模块列表
		List<QuicklyStart> startList = quicklyStartService
				.findQuicklyStart(user.getId());

		List<String> deleteIdsList = new ArrayList<String>();

		// 循环quickstart列表，如果有不存在于所有角色模块类别中的，则删除
		for (QuicklyStart quicklyStart : startList) {
			if (!moduleCodeList.contains(quicklyStart.getModule()
					.getModuleCode())) {
				deleteIdsList.add(quicklyStart.getId());
			}

		}
		if (deleteIdsList.size() > 0) {
			quicklyStartService.deleteQSbyIDs(deleteIdsList);
		}

	}
	
	/**
	 * 判断是否允许多处的登录
	 * 
	 * @param user
	 * @return
	 */
	private boolean checkOnlineUser(User user) {
		if (!Constants.MULTI_LOGIN) {
			return onlineUserService.isUserLogon(user.getId());
		}
		return false;
	}
	
	/**
	 * 记录登录日志
	 * 
	 * @param type
	 */
	private void saveLog(User user, String type) {
		LogonLog log = new LogonLog();
		log.setFromIp(Struts2Util.getRequest().getRemoteAddr());
		String userAgent = Struts2Util.getRequest().getHeader("user-agent");
		log.setBrowser(getBrowser(userAgent));
		log.setOs(getOS(userAgent));
		log.setUserAgent(userAgent);
		log.setVisitedDate(new Date());
		log.setVisitor(user);
		log.setLoginType(type);
		logService.saveLogonLog(log);
		setOnlineInfo(user, type);
	}
	
	/**
	 * 获取浏览器类型
	 * 
	 * @param userAgent
	 * @return
	 */
	private String getBrowser(String userAgent) {
		if (userAgent.contains("MSIE") == true)
			return "IE";
		else if (userAgent.contains("Firefox") == true)
			return "Firefox";
		else if (userAgent.contains("Chrome") == true)
			return "Chrome";
		else if (userAgent.contains("Safari") == true)
			return "Safari";
		else if (userAgent.contains("Opera") == true)
			return "Opera";
		else
			return "其他";
	}

	/**
	 * 获取OS类型
	 * 
	 * @param userAgent
	 * @return
	 */
	private String getOS(String userAgent) {
		if (userAgent.contains("Windows") == true)
			return "Windows";
		else if (userAgent.contains("Linux") == true)
			return "Linux";
		else if (userAgent.contains("Mac") == true)
			return "Mac";
		else
			return "其他";
	}
	
	/**
	 * 添加在线用户
	 * 
	 * @param user
	 * @param loginType
	 */
	private void setOnlineInfo(User user, String loginType) {
		OnlineUser onlineUser = new OnlineUser();
		if(onlineUserService.isUserLogon(user.getId())){
			/**
			 * 在配置允许同一用户多处登录的情况下，在线用户列表数据库中更新用户信息，不添加重复用户数据
			 * @author YuWeitao 2013-03-22
			 */
			onlineUser = onlineUserService.getOnlineUserByUserId(user.getId());
		}
		onlineUser.setUser(user);
		onlineUser.setSessionId(Struts2Util.getSession().getId());
		onlineUser.setLoginType(loginType);
		onlineUser.setLoginTime(new Date());
		onlineUserService.save(onlineUser);
	}

}
