/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-25        Chenjian          create
 * Feb 10, 2011        Chenjian          添加登录日志
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.MDC;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.common.util.Condition;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.service.HttpService;
import com.jshx.majorTrouble.service.MajorTroubleService;
import com.jshx.map.service.TbMapService;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.LogoffLog;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.OnlineUser;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.LogService;
import com.jshx.module.admin.service.LogoffLogService;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.OnlineUserService;
import com.jshx.module.admin.service.QuicklyStartService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserLinkedDeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;
import com.jshx.zcyhsb.service.JshxZcyhsbService;
import com.sun.xml.ws.rm.jaxws.runtime.Session;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-25 上午11:11:57 公共Action
 */
public class CommonAction extends BaseAction {

	@Autowired() 
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;

	@Autowired() 
	@Qualifier("quicklyStartService")
	private QuicklyStartService quicklyStartService;

	@Autowired() 
	@Qualifier("moduleService")
	private ModuleService moduleService;

	@Autowired() 
	@Qualifier("deptService")
	private DeptService deptService;

	@Autowired() 
	@Qualifier("userService")
	private UserService userService;

	@Autowired() 
	@Qualifier("logService")
	private LogService logService;

	@Autowired() 
	@Qualifier("codeService")
	private CodeService codeService;

	@Autowired() 
	@Qualifier("onlineUserService")
	private OnlineUserService onlineUserService;

	@Autowired() 
	@Qualifier("userLinkedDeptService")
	private UserLinkedDeptService userLinkedDeptService;
	
	@Autowired()
	@Qualifier("logoffLogService")
	private LogoffLogService logoffLogService;
	
	@Autowired()
	@Qualifier("httpService")
	private HttpService httpService;
	
	@Autowired()
	@Qualifier("entBaseInfoService")
	private EntBaseInfoService entBaseInfoService;
	
	@Autowired
	private ContentInformationsService contentInformationsService; 
	@Resource
	private JshxZcyhsbService jshxZcyhsbService;
	@Resource
	  private CommoTroubleService commoTroubleService;
	  @Resource
	  private MajorTroubleService majorTroubleService;
	  @Resource
	  private CompanyService companyService;
	  @Autowired
		private SafeInspectDistributeService safeInspectDistributeService;
	
	@Autowired
	private TbMapService tbMapService;
	
	private List<Module> moduleList;

	private List<User> userList;

	private Module module;

	private String codeId;

	private Boolean isChecked;

	private Boolean showCheck;

	private Code code;

	private String func;

	private String message = "";

	private String css;

	private String extendParams;

	private String test;

	private static final long serialVersionUID = 6026790061476395281L;

	// 用户登录用户名与密码
	private String loginId;
	private String password;
	private String passWord;
	private User user;
	private Department dept;

	/** ajax模式登录消息 */
	private Map<String, Object> map;

	/** 登录方式：0表示用手机号登录；1表示用用户ID登录 */
	private Integer loginType = 1;

	private String callback;

	private List<QuicklyStart> quikStarList;

	private List<Map<String, Object>> items;
	
	private List<QuicklyStart> startList;
	
	private String validateFlag;
	
	private String roleName;
	
	private Map auditInfo = new HashMap();//待审核事项
	
	private List<Map> notices = new ArrayList<Map>();
	
	private String selModule;
	
	private String entBaseInfoId;
	
	private CompanyBackUp companyBackUp;
	
	/**
	 * 手机端用户登录，参数：loginType（默认loginId为用户名，当值为0时loginId为手机号码）、loginId、password<br>
	 * 返回json字符串：{"loginMessage":"","loginCode":""}
	 * @return String
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("unchecked")
	public void mobileLogin() {
		Map<String, String> result = new HashMap<String, String>();

		if (loginType == 0) {
			user = userService.checkPasswordByMobile(loginId, password);
		} else
			user = userService.checkPassword(loginId, CodeUtil.encode(password, CodeUtil.MD5));

		if (null != user) {

			if (user.getDelFlag() == 1
					|| ((Department) user.getDept()).getDelFlag() == 1) {
				result.put("loginCode", "0");
				result.put("loginMessage", "用户或用户的所在部门被禁用！");
			} else {
				List<String> deptIds = setUserDeptIds(user.getId(),
						((Department) user.getDept()).getId());
				user.setDeptIds(deptIds);

				List rightList = user.getUserRoles();
				if (rightList != null && rightList.size() > 0) {
					String[] roleIds = new String[rightList.size()];

					for (int i = 0; i < rightList.size(); i++) {
						UserRight right = (UserRight) rightList.get(i);
						roleIds[i] = right.getRole().getId();
						if (right.getRole().getIsSupAdmin() == 1) {
							user.setIsSuperAdmin(true);
						}
					}
					user.setRoleIds(roleIds);
				}
				user.setLogTime(new Date());
				Map<String, User> userMap = (Map<String, User>) Struts2Util
						.getServletContext().getAttribute(Constants.CURR_USERS);
				if (userMap == null)
					userMap = new HashMap<String, User>();
				userMap.put(user.getId(), user);
				Struts2Util.getServletContext().setAttribute(
						Constants.CURR_USERS, userMap);
				this.getSession().setAttribute(Constants.CURR_USER, user);
				this.getSession().setAttribute(Constants.LOGIN_USER_ID,
						user.getId());
				user.setLinkedDepts(userLinkedDeptService
						.getLinkedDeptByUser(user.getId()));

				// 在线用户
				setOnlineInfo(user, "手机");
				MDC.put("userId", user.getId());
				result.put("loginCode", "1");
				result.put("loginMessage", "登录成功");
			}
		} else {
			result.put("loginCode", "0");
			result.put("loginMessage", Constants.LOGIN_ERROR);
		}

		try {
			JSONObject json = JSONObject.fromObject(result);
			this.getResponse().getWriter().println(
					callback + "(" + json.toString() + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkSession(){
		map = new HashMap<String, Object>();
		try{
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser==null|| !currentUser.isAuthenticated()){
				map.put("result", false);
			}else{
				map.put("result", true);
			}
		}catch(Exception e){
			map.put("result", false);
			map.put("error", e.getLocalizedMessage());
		}
		try{
			JSONObject json = JSONObject.fromObject(map);
			this.getResponse().getWriter().println(json);
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Ajax登录，参数：loginId、password<br>
	 * 返回json字符串：{"info":""}
	 * 
	 * @throws java.io.IOException
	 */
	public void ajaxLogin() {
		map = new HashMap<String, Object>();
		try {
			
			//政务通验证
//			DwtLogin zwtLogin = new DwtLogin();
//			Map mm = zwtLogin.login("sipac", loginId, password);
//			
//			String code = mm.get("code").toString();
//			if(code.equals("1"))
//			{
//				map.put("result", false);
//				map.put("error", mm.get("message").toString());
//			}
//			else
//			{
//				User users = userService.findUserByLoginId(loginId);
//				users.setGuid(mm.get("guid").toString());
//				users.setDisplayName(mm.get("displayName").toString());
//				users.setDuty(mm.get("duty").toString());
//				users.setMobile(mm.get("mobile").toString());
//				users.setTel(mm.get("tel").toString());
//				users.setEmail(mm.get("email").toString());
//				users.setPassword(CodeUtil.encode(password, CodeUtil.MD5));
//				entBaseInfoService.updateUser(users);
				
				setSessionAttribute("noMd5Password", "");
				Subject currentUser = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(loginId, CodeUtil.encode(password, CodeUtil.MD5));
				token.setRememberMe(false);
				try {
					currentUser.login(token);
				} catch (AuthenticationException e) {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, e.getLocalizedMessage());
					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
					map.put("result", false);
					map.put("error", e.getLocalizedMessage());
				}
				if (currentUser.isAuthenticated()) {
					user = getLoginUser();
					String deptCode = user.getDeptCode();
//					if(!deptCode.startsWith("009") && !deptCode.startsWith("001"))
//					{
//						this.getSession().setAttribute(Constants.LOGIN_MESSAGE, "您没有权限访问！");
//						this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
//						map.put("result", false);
//						map.put("error", "您没有权限访问！");
//					}
//					else
//					{
						List<?> rightList = user.getUserRoles();
						Map<String, List<String>> permissions = user.getPermissions();
						if(permissions==null)
							permissions = new HashMap<String, List<String>>();
						
						// 角色增加2个字段用于配置 超级管理员和部门管理员
						if (rightList != null && rightList.size() > 0) {
							String[] roleIds = new String[rightList.size()];
							for (int i = 0; i < rightList.size(); i++) {
								UserRight right = (UserRight) rightList.get(i);
								roleIds[i] = right.getRole().getId();
								if (null!= (right.getRole().getIsSupAdmin())&& 1 == (right.getRole().getIsSupAdmin())) {
									user.setIsSuperAdmin(true);
								}
								List<String> pm = permissions.get(right.getRole().getRoleName());
								if(pm==null)
									pm = new ArrayList<String>();
								List<Permission> list = securityService.findPermission(null, right.getRole().getId(), null, null);
								if(list!=null && list.size()>0){
									for(Permission permission : list){
										pm.add(permission.getPermissionExpression());
									}
								}
								
								permissions.put(right.getRole().getRoleName(), pm);
								
							}
							
							user.setRoleIds(roleIds);
							
						}
						
						List<String> pm = new ArrayList<String>();
						List<Permission> list = securityService.findPermission(user.getId(), null, null, null);
						if(list!=null && list.size()>0){
							for(Permission permission : list){
								pm.add(permission.getPermissionExpression());
							}
						}
						permissions.put("NO_ROLE", pm);
						user.setPermissions(permissions);	
						
						
						saveExtendVarToSession();
						map.put("result", true);
						// 查看用户在登录前是否有需要查看的页面，如果有，转到该页面
						String requestUrl = (String) this.getSessionAttribute(Constants.USER_REQUEST_URL);
						if (requestUrl != null && !requestUrl.trim().equals("")
								&& requestUrl.indexOf("logout.action") == -1) {
							map.put("requestUrl", requestUrl);
						}
						setSessionAttribute("noMd5Password", password);
//					}
				} else {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.LOGIN_ERROR);
					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
					map.put("result", false);
					map.put("error", Constants.LOGIN_ERROR);
				}
//			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("error", e.getLocalizedMessage());
		}
		try{
			JSONObject json = JSONObject.fromObject(map);
			this.getResponse().getWriter().println(json);
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * 用户名+md5密码直接登录
	 */
	public void ajaxLoginMd5() {
		map = new HashMap<String, Object>();
		try {
			
			//政务通验证
//			DwtLogin zwtLogin = new DwtLogin();
//			Map mm = zwtLogin.login("sipac", loginId, password);
//			
//			String code = mm.get("code").toString();
//			if(code.equals("1"))
//			{
//				map.put("result", false);
//				map.put("error", mm.get("message").toString());
//			}
//			else
//			{
//				User users = userService.findUserByLoginId(loginId);
//				users.setGuid(mm.get("guid").toString());
//				users.setDisplayName(mm.get("displayName").toString());
//				users.setDuty(mm.get("duty").toString());
//				users.setMobile(mm.get("mobile").toString());
//				users.setTel(mm.get("tel").toString());
//				users.setEmail(mm.get("email").toString());
//				users.setPassword(CodeUtil.encode(password, CodeUtil.MD5));
//				entBaseInfoService.updateUser(users);
				
				Subject currentUser = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(loginId, password);
				token.setRememberMe(false);
				try {
					currentUser.login(token);
				} catch (AuthenticationException e) {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, e.getLocalizedMessage());
					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
					map.put("result", false);
					map.put("error", e.getLocalizedMessage());
				}
				if (currentUser.isAuthenticated()) {
					user = getLoginUser();
					String deptCode = user.getDeptCode();
//					if(!deptCode.startsWith("009") && !deptCode.startsWith("001"))
//					{
//						this.getSession().setAttribute(Constants.LOGIN_MESSAGE, "您没有权限访问！");
//						this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
//						map.put("result", false);
//						map.put("error", "您没有权限访问！");
//					}
//					else
//					{
						List<?> rightList = user.getUserRoles();
						Map<String, List<String>> permissions = user.getPermissions();
						if(permissions==null)
							permissions = new HashMap<String, List<String>>();
						
						// 角色增加2个字段用于配置 超级管理员和部门管理员
						if (rightList != null && rightList.size() > 0) {
							String[] roleIds = new String[rightList.size()];
							for (int i = 0; i < rightList.size(); i++) {
								UserRight right = (UserRight) rightList.get(i);
								roleIds[i] = right.getRole().getId();
								if (null!= (right.getRole().getIsSupAdmin())&& 1 == (right.getRole().getIsSupAdmin())) {
									user.setIsSuperAdmin(true);
								}
								List<String> pm = permissions.get(right.getRole().getRoleName());
								if(pm==null)
									pm = new ArrayList<String>();
								List<Permission> list = securityService.findPermission(null, right.getRole().getId(), null, null);
								if(list!=null && list.size()>0){
									for(Permission permission : list){
										pm.add(permission.getPermissionExpression());
									}
								}
								
								permissions.put(right.getRole().getRoleName(), pm);
								
							}
							
							user.setRoleIds(roleIds);
							
						}
						
						List<String> pm = new ArrayList<String>();
						List<Permission> list = securityService.findPermission(user.getId(), null, null, null);
						if(list!=null && list.size()>0){
							for(Permission permission : list){
								pm.add(permission.getPermissionExpression());
							}
						}
						permissions.put("NO_ROLE", pm);
						user.setPermissions(permissions);	
						
						
						saveExtendVarToSession();
						map.put("result", true);
						// 查看用户在登录前是否有需要查看的页面，如果有，转到该页面
						String requestUrl = (String) this.getSessionAttribute(Constants.USER_REQUEST_URL);
						if (requestUrl != null && !requestUrl.trim().equals("")
								&& requestUrl.indexOf("logout.action") == -1) {
							map.put("requestUrl", requestUrl);
						}
//					}
				} else {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.LOGIN_ERROR);
					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
					map.put("result", false);
					map.put("error", Constants.LOGIN_ERROR);
				}
//			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("error", e.getLocalizedMessage());
		}
		try{
			JSONObject json = JSONObject.fromObject(map);
			this.getResponse().getWriter().println(json);
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e);
		}
	}

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
	 * 添加/修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String saveUser(User user) {

		if (user.getDelFlag() == 1
				|| ((Department) user.getDept()).getDelFlag() == 1) {
			this.getSession().setAttribute(Constants.LOGIN_MESSAGE,
					"用户或用户的所在部门被禁用！");
			return INPUT;
		}
		// 数据访问权限
		List<String> deptIds = setUserDeptIds(user.getId(), ((Department) user
				.getDept()).getId());
		user.setDeptIds(deptIds);
		List<?> rightList = user.getUserRoles();

		if (rightList != null && rightList.size() > 0) {
			String[] roleIds = new String[rightList.size()];
			for (int i = 0; i < rightList.size(); i++) {
				UserRight right = (UserRight) rightList.get(i);
				roleIds[i] = right.getRole().getId();
				if (right.getRole().getIsSupAdmin() == 1) {
					user.setIsSuperAdmin(true);
				}
			}
			user.setRoleIds(roleIds);
		}

		checkQuickStart(user);

		user.setLogTime(new Date());
		List<String> childDeptIds = deptService.findChildDeptIds(user
				.getDeptCode());
		dept = deptService.findDeptByDeptCode(user.getDeptCode());
		dept.setChildDeptIds(childDeptIds);
		user.setDept(dept);

		Map<String, User> userMap = (Map<String, User>) Struts2Util
				.getServletContext().getAttribute(Constants.CURR_USERS);
		if (userMap == null)
			userMap = new HashMap<String, User>();
		userMap.put(user.getId(), user);
		Struts2Util.getServletContext().setAttribute(Constants.CURR_USERS,
				userMap);
		this.getSession().setAttribute(Constants.CURR_USER, user);
		this.getSession().setAttribute(Constants.LOGIN_USER_ID, user.getId());

		LogonLog log = new LogonLog();
		log.setFromIp(super.getRequest().getRemoteAddr());
		String userAgent = super.getRequest().getHeader("user-agent");
		log.setBrowser(this.getBrowser(userAgent));
		log.setOs(this.getOS(userAgent));
		log.setUserAgent(userAgent);
		log.setVisitedDate(new Date());
		log.setVisitor(user);
		log.setLoginType("电脑");
		logService.saveLogonLog(log);

		// 查看用户在登录前是否有需要查看的页面，如果有，转到该页面
		String requestUrl = (String) this
				.getSessionAttribute(Constants.USER_REQUEST_URL);
		if (requestUrl != null && !requestUrl.trim().equals("")
				&& requestUrl.indexOf("logout.action") == -1) {

			try {
				this.getResponse().sendRedirect(requestUrl);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 验证用户登录，根据系统配置需要进行验证码校验
	 * needCaptcha : 0, 登录页始终不显示验证码校验，后台不做验证
	 * needCaptcha : 1, 登录页始终显示验证码校验，后台做验证
	 * needCaptcha : 2, 登录页开始不显示验证码校验，当用户输入用户名密码错误超过3次以后显示验证码校验，后台开始做验证
	 * 
	 * @return String
	 * @throws java.io.IOException
	 */
	public String userLogin() throws IOException {

		try {
			/**
			 * 根据系统配置需要进行验证码校验
			 * needCaptcha : 0, 登录页始终不显示验证码校验，后台不做验证
			 * needCaptcha : 1, 登录页始终显示验证码校验，后台做验证
			 * needCaptcha : 2, 登录页开始不显示验证码校验，当用户输入用户名密码错误超过3次以后显示验证码校验，后台开始做验证
			 * 
			 * @author YuWeitao 2013-11-04
			 * 
			 */
			setSessionAttribute("noMd5Password", "");
			if ("1".equals(SysPropertiesUtil.getProperty("needCaptcha"))) {
				if (!checkValidateNumber()) {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.CAPTCHA_ERROR);
					return INPUT;
				}
			}else if ("2".equals(SysPropertiesUtil.getProperty("needCaptcha"))) {
				if("true".equalsIgnoreCase(this.validateFlag) && !checkValidateNumber()){
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.CAPTCHA_ERROR);
					return INPUT;
				}
			}
			
			//政务通验证
//			DwtLogin zwtLogin = new DwtLogin();
//			Map map = zwtLogin.login("sipac", loginId, password);
//			
//			String code = map.get("code").toString();
//			if(code.equals("1"))
//			{
//				this.getSession().setAttribute(Constants.LOGIN_MESSAGE, map.get("message").toString());
//				this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
//				return INPUT;
//			}
//			
//			User users = userService.findUserByLoginId(loginId);
//			users.setGuid(map.get("guid").toString());
//			users.setDisplayName(map.get("displayName").toString());
//			users.setDuty(map.get("duty").toString());
//			users.setMobile(map.get("mobile").toString());
//			users.setTel(map.get("tel").toString());
//			users.setEmail(map.get("email").toString());
//			users.setPassword(CodeUtil.encode(password, CodeUtil.MD5));
//			entBaseInfoService.updateUser(users);
			
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(loginId, CodeUtil.encode(password, CodeUtil.MD5));
			token.setRememberMe(false);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				this.getSession().setAttribute(Constants.LOGIN_MESSAGE, e.getLocalizedMessage());
				this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
				return INPUT;
			}
			if (currentUser.isAuthenticated()) {
				user = getLoginUser();
//				String deptCode = user.getDeptCode();
//				if(!deptCode.startsWith("009") && !deptCode.startsWith("001"))
//				{
//					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, "您没有权限访问！");
//					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
//					return INPUT;
//				}
//				else
//				{
					//更新用户信息
					List<?> rightList = user.getUserRoles();
					Map<String, List<String>> permissions = user.getPermissions();
					if(permissions==null)
						permissions = new HashMap<String, List<String>>();
					
					// 角色增加2个字段用于配置 超级管理员和部门管理员
					if (rightList != null && rightList.size() > 0) {
						String[] roleIds = new String[rightList.size()];
						for (int i = 0; i < rightList.size(); i++) {
							UserRight right = (UserRight) rightList.get(i);
							roleIds[i] = right.getRole().getId();
							if (null!= (right.getRole().getIsSupAdmin())&& 1 == (right.getRole().getIsSupAdmin())) {
								user.setIsSuperAdmin(true);
							}
							List<String> pm = permissions.get(right.getRole().getRoleName());
							if(pm==null)
								pm = new ArrayList<String>();
							List<Permission> list = securityService.findPermission(null, right.getRole().getId(), null, null);
							if(list!=null && list.size()>0){
								for(Permission permission : list){
									pm.add(permission.getPermissionExpression());
								}
							}
							
							permissions.put(right.getRole().getRoleName(), pm);
							
						}
						
						user.setRoleIds(roleIds);
						
					}
					
					List<String> pm = new ArrayList<String>();
					List<Permission> list = securityService.findPermission(user.getId(), null, null, null);
					if(list!=null && list.size()>0){
						for(Permission permission : list){
							pm.add(permission.getPermissionExpression());
						}
					}
					permissions.put("NO_ROLE", pm);
					user.setPermissions(permissions);	
					
					
					saveExtendVarToSession();
					// 查看用户在登录前是否有需要查看的页面，如果有，转到该页面
					String requestUrl = (String) this.getSessionAttribute(Constants.USER_REQUEST_URL);
					if (requestUrl != null && !requestUrl.trim().equals("")
							&& requestUrl.indexOf("logout.action") == -1) {
						try {
							this.getResponse().sendRedirect(requestUrl);
							return null;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					setSessionAttribute("noMd5Password", password);
//				}
			} else {
				this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.LOGIN_ERROR);
				this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	
	/**
	 * 企业通登录
	 * 验证用户登录，根据系统配置需要进行验证码校验
	 * needCaptcha : 0, 登录页始终不显示验证码校验，后台不做验证
	 * needCaptcha : 1, 登录页始终显示验证码校验，后台做验证
	 * needCaptcha : 2, 登录页开始不显示验证码校验，当用户输入用户名密码错误超过3次以后显示验证码校验，后台开始做验证
	 * 
	 * @return String
	 * @throws java.io.IOException
	 */
	public String qytLogin() throws IOException {

		try {
			/**
			 * 根据系统配置需要进行验证码校验
			 * needCaptcha : 0, 登录页始终不显示验证码校验，后台不做验证
			 * needCaptcha : 1, 登录页始终显示验证码校验，后台做验证
			 * needCaptcha : 2, 登录页开始不显示验证码校验，当用户输入用户名密码错误超过3次以后显示验证码校验，后台开始做验证
			 * 
			 * @author YuWeitao 2013-11-04
			 * 
			 */
			setSessionAttribute("noMd5Password", "");
			if ("1".equals(SysPropertiesUtil.getProperty("needCaptcha"))) {
				if (!checkValidateNumber()) {
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.CAPTCHA_ERROR);
					return INPUT;
				}
			}else if ("2".equals(SysPropertiesUtil.getProperty("needCaptcha"))) {
				if("true".equalsIgnoreCase(this.validateFlag) && !checkValidateNumber()){
					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.CAPTCHA_ERROR);
					return INPUT;
				}
			}
			
			
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(loginId, passWord);
			token.setRememberMe(false);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				this.getSession().setAttribute(Constants.LOGIN_MESSAGE, e.getLocalizedMessage());
				this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
				return INPUT;
			}
			if (currentUser.isAuthenticated()) {
				user = getLoginUser();
//				String deptCode = user.getDeptCode();
//				if(!deptCode.startsWith("009") && !deptCode.startsWith("001"))
//				{
//					this.getSession().setAttribute(Constants.LOGIN_MESSAGE, "您没有权限访问！");
//					this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
//					return INPUT;
//				}
//				else
//				{
					//更新用户信息
					List<?> rightList = user.getUserRoles();
					Map<String, List<String>> permissions = user.getPermissions();
					if(permissions==null)
						permissions = new HashMap<String, List<String>>();
					
					// 角色增加2个字段用于配置 超级管理员和部门管理员
					if (rightList != null && rightList.size() > 0) {
						String[] roleIds = new String[rightList.size()];
						for (int i = 0; i < rightList.size(); i++) {
							UserRight right = (UserRight) rightList.get(i);
							roleIds[i] = right.getRole().getId();
							if (null!= (right.getRole().getIsSupAdmin())&& 1 == (right.getRole().getIsSupAdmin())) {
								user.setIsSuperAdmin(true);
							}
							List<String> pm = permissions.get(right.getRole().getRoleName());
							if(pm==null)
								pm = new ArrayList<String>();
							List<Permission> list = securityService.findPermission(null, right.getRole().getId(), null, null);
							if(list!=null && list.size()>0){
								for(Permission permission : list){
									pm.add(permission.getPermissionExpression());
								}
							}
							
							permissions.put(right.getRole().getRoleName(), pm);
							
						}
						
						user.setRoleIds(roleIds);
						
					}
					
					List<String> pm = new ArrayList<String>();
					List<Permission> list = securityService.findPermission(user.getId(), null, null, null);
					if(list!=null && list.size()>0){
						for(Permission permission : list){
							pm.add(permission.getPermissionExpression());
						}
					}
					permissions.put("NO_ROLE", pm);
					user.setPermissions(permissions);	
					
					
					saveExtendVarToSession();
					// 查看用户在登录前是否有需要查看的页面，如果有，转到该页面
					String requestUrl = (String) this.getSessionAttribute(Constants.USER_REQUEST_URL);
					if (requestUrl != null && !requestUrl.trim().equals("")
							&& requestUrl.indexOf("logout.action") == -1) {
						try {
							this.getResponse().sendRedirect(requestUrl);
							return null;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					setSessionAttribute("noMd5Password", passWord);
//				}
			} else {
				this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.LOGIN_ERROR);
				this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 登陆页除用户名、密码、验证码以外的任何input输入框(type=text)的变量值都放入session,input标签的ID对应放入Session的KEY
	 * 
	 * @author YuWeitao 2013-03-08
	 */
	@SuppressWarnings("unchecked")
	private void saveExtendVarToSession() {
		Enumeration<String> enu = getRequest().getAttributeNames();
		while (enu.hasMoreElements()) {
			String param = enu.nextElement();
			String[] values = getRequest().getParameterValues(param);
			if (values != null) {
				if (values.length > 1) {
					setSessionAttribute(param, values);
				} else {
					setSessionAttribute(param, values[0]);
				}
			}
		}
	}

	/**
	 * 设置用户的数据权限所能访问的部门
	 * @return String[] 所能访问的部门ID数组
	 * @throws
	 */
	private List<String> setUserDeptIds(String userId, String departmentId) {
		List<String> deptIds = new ArrayList<String>();
		deptIds.add(departmentId);
		// List<Department> deptList =
		// securityService.findDataAccessAuth(userId);
		// if (deptList != null && deptList.size() > 0) {
		// for (int i = 0; i < deptList.size(); i++) {
		// deptIds.add(deptList.get(i).getId());
		// }
		// }
		return deptIds;
	}

	/**
	 * 网站退出登录，记录登出日志
	 * 
	 * @Title: logout
	 * @Description:
	 * @return String
	 */
	public String logout() {
		User user = this.getLoginUser();
//		saveLog(user,"电脑");
		HttpSession session = getSession();
		session.invalidate();
		return SUCCESS;
	}
	/**
	 * ajax退出登录
	 * 
	 * @Title: logout
	 * @Description:
	 * @return String
	 */
	public void ajaxLogout() {
		HttpSession session = getSession();
		session.invalidate();
	}
	
	/**
	 * 记录登出日志
	 * 
	 * @param type
	 */
	private void saveLog(User user, String type) {
		LogoffLog log = new LogoffLog();
		log.setFromIp(Struts2Util.getRequest().getRemoteAddr());
		String userAgent = Struts2Util.getRequest().getHeader("user-agent");
		log.setBrowser(getBrowser(userAgent));
		log.setOperationsystem(getOS(userAgent));
		log.setUserAgent(userAgent);
		log.setLogoffDate(new Date());
		log.setUser(user);
		log.setLogoffType(type);
		logoffLogService.save(log);
	}

	/**
	 * 查找在线用户
	 * 
	 * @Title: queryOnlineUsers
	 * @Description:
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String queryOnlineUsers() {
		Map<String, User> userMap = (Map<String, User>) Struts2Util
				.getServletContext().getAttribute(Constants.CURR_USERS);
		userList = new ArrayList<User>();
		if (userMap != null) {
			Iterator<String> keyIt = userMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				User user = userMap.get(key);
				if (loginId == null || loginId.equals(""))
					userList.add(user);
				else {
					if (user.getLoginId().indexOf(loginId) != -1
							|| user.getDisplayName().indexOf(loginId) != -1)
						userList.add(user);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 检查用户quickstart模块列表和角色权限模块列表是否不在一个集合内
	 * 
	 */
	private void checkQuickStart(User user) {
		// 得到用户的所有角色对应的模块列表
		// userService.

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

	private static Random random = new Random();

	private Color getRandColor(int fc, int bc) {
		return getRandColor(fc, bc, fc);
	}

	private Color getRandColor(int fc, int bc, int interval) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - interval);
		int g = fc + random.nextInt(bc - interval);
		int b = fc + random.nextInt(bc - interval);
		return new Color(r, g, b);
	}

	/**
	 * 生成验证码
	 * 
	 * @throws Exception
	 */
	public void exec() throws Exception {

		HttpServletResponse response = Struts2Util.getResponse();
		int codeLength = 4;
		int mixTimes = 50;
		Color bgColor = getRandColor(200, 250);
		Color bfColor = new Color(0, 0, 0);
		boolean ifRandomColor = true;
		boolean ifMixColor = false;

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		int width = 13 * codeLength + 6, height = 24;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", Font.BOLD, 17));

		g.setColor(new Color(33, 66, 99));
		g.drawRect(0, 0, width - 1, height - 1);

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < mixTimes * codeLength / 10; i++) {
			if (ifMixColor) {
				g.setColor(getRandColor(160, 200));
			}
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < codeLength; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;

			if (ifRandomColor)
				g.setColor(getRandColor(20, 110, 0));
			else
				g.setColor(bfColor);

			g.drawString(rand, 13 * i + 6, 16);
		}

		Struts2Util.getRequest().getSession().setAttribute("rand", sRand);

		g.dispose();

		ImageIO.write(image, "PNG", response.getOutputStream());

	}

	/**
	 * 校验验证码是否正确
	 */
	public boolean checkValidateNumber() throws IOException {
		HttpServletRequest request = Struts2Util.getRequest();
		String code = (String) request.getSession().getAttribute("rand");
		String validateCode = request.getParameter("validateCode");
		if (validateCode == null || !validateCode.equals(code)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获得可以访问的模块
	 * 
	 * @Description:
	 * @return String
	 */
	public String findMyModule() {
		User user = (User) this.getSessionAttribute(Constants.CURR_USER);
		moduleList = securityService.findMyModule(user);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		quikStarList = securityService.findQuicklyStart(paraMap);
		return SUCCESS;
	}

	/**
	 * 初始化用户信息，模块信息，快捷菜单，并返回首页
	 * 
	 * @Description:
	 * @return String
	 */
	public String index() {
		/*User user = this.getLoginUser();
		String modulecode = "M";
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", user.getId());
		paraMap.put("moduleCode", modulecode.trim() + "%");
		paraMap.put("length", modulecode.length() + 2);
		if (user.getIsSuperAdmin() != null && user.getIsSuperAdmin()) {
			moduleList = moduleService
					.findModuleForAdmin(paraMap);
		} else {
			moduleList = moduleService.findModuleForLeft(paraMap);
		}*/
		User user = this.getLoginUser();
		String deptCode = user.getDeptCode();
		String modulecode = "M";
		//判断是否是企业登录
//		if(deptCode.startsWith("033"))
//		{
//			modulecode = "M06";
//			roleName = "0";
////			return EDIT;
//		}
		items = getChildrenModule(modulecode, user);
		startList = quicklyStartService.findQuicklyStart(user.getId());
		return SUCCESS;
	}
	
	/**
	 * 查看企业信息
	 * @return
	 */
	public String indexEnt(){
		User user = this.getLoginUser();
		items = getChildrenModule("M06", user);
		return SUCCESS;
	}
	
	/**
	 * 左侧菜单树的显示
	 * @param modulecode
	 * @return
	 * @throws IOException 
	 */
	public void findModuleTree()   {
 		  
		writerJSONArray(getChildrenForLeft(selModule));
	}
	
	private List<Map<String,Object>>    getChildrenForLeft(String modulecode) {
		//取得当前用户
		User user=this.getLoginUser();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", user.getId());
		paraMap.put("moduleCode", modulecode.trim() + "%");
		paraMap.put("length", modulecode.length()+2);
		List<Map<String,Object>>    treeNodes =findChidrenNode(paraMap);
		return treeNodes;
	}
	
	private List<Map<String,Object>>   findChidrenNode(Map<String, Object> paraMap) {
		List<Module> moduleList = new ArrayList<Module>();
		User user = this.getLoginUser();
		if (user.getIsSuperAdmin() != null && user.getIsSuperAdmin()) {
			moduleList = moduleService
					.findModuleForAdmin(paraMap);
		} else {
			moduleList = moduleService.findModuleForLeft(paraMap);
		}
		List<Map<String,Object>>  treeNodes = new ArrayList<Map<String,Object>>();
		for (Module m : moduleList) {
			Map<String,Object> node = new HashMap<String,Object>() ;
			node.put("moduleCode",m.getModuleCode());
			node.put("name",m.getModuleName());
			node.put("hasChild", m.getHasChild());
			node.put("smallIconAddr", m.getSmallIconAddr());
			node.put("moduleAddr", m.getModuleAddr());
			treeNodes.add(node);
		}
		return treeNodes;
	}

	
	
	/**
	 * 初始化用户信息，模块信息，快捷菜单，并返回首页
	 * 
	 * @Description:
	 * @return String
	 */
	public String indexQyt() {
		User user = this.getLoginUser();
		items = getChildrenModule("M",user);
		List<Map<String, Object>> l=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> m:items){
			if(!m.get("text").toString().contains("监督业务管理")&&!m.get("text").toString().contains("系统管理")){
				l.add(m);
			}
		}
		items=l;
		startList = quicklyStartService.findQuicklyStart(user.getId());
		return SUCCESS;
	}

	private List<Map<String, Object>> getChildrenModule(String modulecode, User user) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//企业登录只显示企业管理和通知公告
		if(user.getDeptCode().startsWith("033") && modulecode.length()==1){
			paraMap.put("otherProperty", "1");
		}
		paraMap.put("userId", user.getId());
		paraMap.put("moduleCode", modulecode.trim() + "%");
		paraMap.put("length", modulecode.length() + 2);
		if (user.getIsSuperAdmin() != null && user.getIsSuperAdmin()) {
			moduleList = moduleService.findModuleForAdmin(paraMap);
		} else {
			moduleList = moduleService.findModuleForLeft(paraMap);
		}
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (Module m : moduleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", m.getModuleCode());
			item.put("text", m.getModuleName());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			map.put("moduleCode", m.getModuleCode().trim() + "%");
			map.put("length", m.getModuleCode().length() + 2);
			List<Module> mlist = new ArrayList<Module>();
			if (user.getIsSuperAdmin() != null && user.getIsSuperAdmin()) {
				mlist = moduleService.findModuleForAdmin(map);
			} else {
				mlist = moduleService.findModuleForLeft(map);
			}
			if (mlist.size() > 0) {
				item.put("children", getChildrenModule(m.getModuleCode(), user));
			} else {
				String add = m.getModuleAddr();
				item.put("attributes", add);
			}
			if (m.getSmallIconAddr() != null
					&& m.getSmallIconAddr().length() > 0)
				item.put("iconCls",m.getSmallIconAddr().trim());
			if (m.getTarget() == null || m.getTarget().equals("框架"))
				item.put("data", "frm");
			else
				item.put("data", "_blank");

			item.put("isAutoExpand", m.getIsAutoExpand()); // 2013.3.6 李淮如 修改
			
			items.add(item);
		}
		return items;
	}

	/**
	 * 一维代码树形菜单
	 * 
	 * @Title: codeTree
	 * @Description:
	 * @return String
	 */
	public String codeTree() {
		if (isChecked == null)
			isChecked = false;
		if (showCheck == null)
			showCheck = false;

		code = codeService.findCodeById(codeId);
		return SUCCESS;
	}

	/**
	 * 一维代码树形菜单
	 * 
	 * @return void
	 */
	public void findQuicklyStartTree() {
		User user = (User) this.getSessionAttribute(Constants.CURR_USER);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		List<QuicklyStart> startList = securityService
				.findQuicklyStart(paraMap);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (QuicklyStart q : startList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", q.getModule().getModuleCode());
			item.put("text", q.getModule().getModuleName());
			item.put("iconCls", "icon-module");
			item.put("attributes", q.getModule().getModuleAddr());
			if (q.getModule().getTarget() == null
					|| q.getModule().getTarget().equals("框架"))
				item.put("data", "frm");
			else
				item.put("data", "_blank");
			items.add(item);
		}
		writerJSONArray(items);
	}

	private void setOnlineInfo(User user, String loginType) {
		OnlineUser onlineUser = new OnlineUser();
		if (onlineUserService.isUserLogon(user.getId())) {
			/**
			 * 在配置允许同一用户多处登录的情况下，在线用户列表数据库中更新用户信息，不添加重复用户数据
			 * 
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
	
	//跳转总控台
	public String mapConsole(){
		return SUCCESS;
	}
	
	//2016-6-22  首页数据 zhangzh  
	  @SuppressWarnings("unchecked")
	public String indexContent()  {
		  try{
			System.out.println("获取首页数据");
			Map<String, Object> paraMap = new HashMap<String, Object>();
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			
			//公告数量统计 
			paraMap.put("delFlag", "0");
			paraMap.put("expireFlag", "0");
			paraMap.put("listType", "1");
			paraMap.put("createUserID", this.getLoginUserId());
			int infoTotals = contentInformationsService.findAllInfos(paraMap).size();	
			setRequestAttribute("infoTotals", infoTotals);
			
			//自查隐患上报数量统计
			paraMap.clear();
			String deptRole = this.getLoginUser().getDeptRole();
			List<?> rightList = this.getLoginUser().getUserRoles();
			String[] roleNames = new String[rightList.size()];
			if (rightList != null && rightList.size() > 0) {
				for (int i = 0; i < rightList.size(); i++) {
					UserRight right = (UserRight) rightList.get(i);
					roleNames[i] = right.getRole().getRoleName();
				}
			}
			List listRoles = Arrays.asList(roleNames);  
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)&&!listRoles.contains("系统管理员")){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), listRoles.toString());
			}
			int zcyhsbTotals = jshxZcyhsbService.findJshxZcyhsb(paraMap).size();
			setRequestAttribute("zcyhsbTotals", zcyhsbTotals);
			
			//待处理自查隐患上报
			paraMap.put("mqzt", "1");
			int zcyhsbDzg = jshxZcyhsbService.findJshxZcyhsb(paraMap).size();
			setRequestAttribute("zcyhsbDzg", zcyhsbDzg);
			
			//待整改一般隐患
			paraMap.put("shzt", "0");
			int yibanDzg = commoTroubleService.findList(paraMap).size();
			setRequestAttribute("yibanDzg", yibanDzg);
			
			//待整改重大隐患
			int zhongdaDzg = majorTroubleService.findList(paraMap).size();
			setRequestAttribute("zhongdaDzg", zhongdaDzg);
			 roleName="";
			  List<UserRight> list = (List<UserRight>) getLoginUser().getUserRoles();//判断登录角色
				for(UserRight u:list){
					String code = u.getRole().getRoleCode();
					if("A01".equals(code)){			//管理员
						roleName=code;		
					}else if("A47".equals(code)){//县区管理员
						roleName=code;
					}else if(code.contains("A4207")){ //综合处长
						roleName=code;//企业人员
					}
					else if("A4210".equals(code)){//监管二处处长
						roleName=code;
					}
					else if("A4212".equals(code)){//监管三处处长
						roleName="A4212";
					}
					else if("A4216".equals(code)){//监察大队大队长
						roleName="A4216";
					}else if("A4213".equals(code)){//监管四处处长
						roleName="A4213";
					}else if("A4403".equals(code)){//安监中队中队长
						roleName="A4403";
					} 
				}
			//待审核一般隐患
			paraMap.put("shzt", "3");
			//paraMap.put("deptId", getLoginUser().getDept().getId());
			int yibanDsh = commoTroubleService.findList(paraMap).size();
			if(StringUtil.isNotNullAndNotEmpty(roleName)){
				setRequestAttribute("yibanDsh", yibanDsh);
			}
			
			//待审核重大隐患
			int zhongdaDsh = majorTroubleService.findList(paraMap).size();
			if(StringUtil.isNotNullAndNotEmpty(roleName)){
				setRequestAttribute("zhongdaDsh", zhongdaDsh);
			}
			
			  roleName="";
			  List<UserRight> tlist = (List<UserRight>) getLoginUser().getUserRoles();//判断登录角色
				for(UserRight u:tlist){
					String code = u.getRole().getRoleCode();
					if(code.contains("A45")){
						roleName=code;//企业人员
					}
				}
			if(roleName.contains("A45")){//企业人员 ,显示自己地图所在位置
				CompanyBackUp companyBackUp = companyService.getCompanyByLoginId(getLoginUser().getLoginId());
				setRequestAttribute("myCompany", companyBackUp);
				
				//安全检查数量统计
				paraMap.clear();
				//如果该用户角色仅为“岗位人员”
				List<UserRight> userRightList = this.getLoginUser().getUserRoles();
				if(null != userRightList && userRightList.size() == 1){
					String rolecode = userRightList.get(0).getRole().getRoleCode();
					if(rolecode.equals("A37")){
						paraMap.put("personnel", this.getLoginUser().getId());
					}
				}
				paraMap.put("inspectNum","1");
				paraMap.put("personnelDeptCode", deptCode+"%");
				paraMap.put("taskStatusNotZero", "%0%");
				paraMap.put("companyFlag", companyBackUp.getId());
				//临时企业
				if("A4508".equals(roleName)){
					setRequestAttribute("tempCompany", 1);
				}else{
					int safeInspectDistributeDzg = safeInspectDistributeService.findSafeInspectDistribute(paraMap).size();
					setRequestAttribute("safeInspectDistributeDzg", safeInspectDistributeDzg);
					
				}
				
				
				//企业隐患上报本月统计	 
				paraMap.clear();
				paraMap.put("cMonth", DateUtil.getDate(new Date(),"yyyyMM")+"01");
				deptCode = companyBackUp.getDwdz1();
				paraMap.put("deptCode", deptCode+"%");
				paraMap.put("qyid", companyBackUp.getId());
				List  shangbaoData  =jshxZcyhsbService.findTjyhsb(paraMap);
				setRequestAttribute("shangbaoData", shangbaoData);
				
				//企业隐患整改统计
				paraMap.put("mqzt", 0);
				List  zhenggaiData  =jshxZcyhsbService.findTjyhsb(paraMap);
				setRequestAttribute("zhenggaiData", zhenggaiData); 
				
				paraMap.put("qymcId", companyBackUp.getCompanyId());
				zhongdaDzg = majorTroubleService.findList(paraMap).size();
				setRequestAttribute("zhongdaDzg", zhongdaDzg);
			}else{
				//企业统计	
				paraMap.clear();	
				if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)&&!listRoles.contains("系统管理员")){//非管理员登录
					paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), listRoles.toString());
				}	
				paraMap.put("basePass", 3);	
				int companyTotal = companyService.findCompany(paraMap).size();
				setRequestAttribute("companyTotal", companyTotal);
				
				/* roleName="";
				  List<UserRight> ttlist = (List<UserRight>) getLoginUser().getUserRoles();//判断登录角色
					for(UserRight u:ttlist){
						String code = u.getRole().getRoleCode();
						if("A01".equals(code)){			//管理员
							roleName=code;		
						}else if("A47".equals(code)){//县区管理员
							roleName=code;
						}else if(code.contains("A4207")){ //综合处长
							roleName=code;//企业人员
						}else if("A4208".equals(code)){//监管一处处长
							roleName=code;
						}else if("A4210".equals(code)){//监管二处处长
							roleName=code;
						}else if("A4212".equals(code)){//监管三处处长
							roleName="A4212";
						}else if("A4213".equals(code)){//监管四处处长
							roleName="A4213";
						}else if("A4209".equals(code)){//监管一处办事员
							roleName=code;
						}else if("A4211".equals(code)){//监管二处办事员
							roleName=code;
						}else if("A4215".equals(code)){//监管三处办事员
							roleName=code;
						}else if("A4214".equals(code)){//监管四处办事员
							roleName=code;
						}else if("A4216".equals(code)){//监察大队大队长
							roleName=code;
						}else if("A4219".equals(code)){//监察大队副队长
							roleName=code;
						}else if("A4403".equals(code)){//安监中队中队长
							roleName=code;
						}else if("A4217".equals(code)){//监察大队办事员
							roleName=code;
						}else if("A4205".equals(code)){//综合办事员
							roleName=code;
						}else if("A4204".equals(code)){//办公室主任
							roleName=code;
						}else if("A4206".equals(code)){//办公室办事员
							roleName=code;
						}else if("A4203".equals(code)){//党组书记
							roleName=code;
						}else if("A4220".equals(code)){//危化科办事员
							roleName=code;
						} 
					}*/
					
				
				//待审核企业	
				paraMap.put("basePass", 0);	
				int companyDSH = companyService.findCompany(paraMap).size();	
//				if(StringUtil.isNotNullAndNotEmpty(roleName)){
//					setRequestAttribute("companyDSH", companyDSH);
//				}
				 
				 
				if(this.getLoginUser().getLoginId().equals("admin")){
					setRequestAttribute("companyDSH", companyDSH);
				}else{
					if(null != deptRole && 
							!deptRole.equals("21") && //非企业人员
							(deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("d") || deptRole.contains("e"))){//如果为一二三四处人员登录
						setRequestAttribute("companyDSH", companyDSH);
					} 
				}
					
				//本月统计
				//企业隐患上报统计	
				paraMap.clear();
				paraMap.put("cMonth", DateUtil.getDate(new Date(),"yyyyMM")+"01");
				if(!getLoginUserDepartment().getDeptName().contains("镇")&&!getLoginUserDepartment().getDeptName().contains("县")){
					deptCode=deptCode.substring(0,deptCode.length()-3);
				}
				paraMap.put("deptCode", deptCode+"%");
				List  shangbaoData  =jshxZcyhsbService.findTjyhsb(paraMap);
				setRequestAttribute("shangbaoData", shangbaoData);
				
				//企业隐患整改统计
				paraMap.put("mqzt", 0);
				List  zhenggaiData  =jshxZcyhsbService.findTjyhsb(paraMap);
				setRequestAttribute("zhenggaiData", zhenggaiData); 
				
				//企业一般隐患上报统计
				List  yibanData  =commoTroubleService.findTjcomm(paraMap);
				setRequestAttribute("yibanData", yibanData);  
				//企业一般隐患处理统计
				paraMap.put("zgwcsj", "shijian");
				List  yibanzhenggaiData  =commoTroubleService.findTjcomm(paraMap);
				setRequestAttribute("yibanzhenggaiData", yibanzhenggaiData); 
				
				//企业重大隐患上报统计
				List  zhongdaData  =majorTroubleService.findTjmaj(paraMap);
				setRequestAttribute("zhongdaData", zhongdaData);  
				
				//企业重大隐患处理统计
				paraMap.put("zgwcrq", "shijian");
				List  zhongdazhenggaiData  =majorTroubleService.findTjmaj(paraMap);
				setRequestAttribute("zhongdazhenggaiData", zhongdazhenggaiData); 
			
			}
		  }catch(Exception e){
			  e.printStackTrace();
		  }
			
		  return SUCCESS;
	  }
	  
	  public String searchCompany() throws IOException{
			Map paraMap = new HashMap<String, String>();
			String deptCode = getLoginUser().getDeptCode();
			if(StringUtil.isNotNullAndNotEmpty(deptCode) && deptCode.length()>3){
				paraMap.put("dwdz1", deptCode.substring(0,deptCode.length()-3)+"%");
			}
		 	if (null != companyBackUp) {
				// 设置查询条件，开发人员可以在此增加过滤条件
				if ((null != companyBackUp.getCompanyname())
						&& (0 < companyBackUp.getCompanyname().trim().length())) {
					paraMap.put("companyname", "%"
							+ companyBackUp.getCompanyname().trim() + "%");
				}

				if ((null != companyBackUp.getQylx())
						&& (0 < companyBackUp.getQylx().trim().length())) {
					paraMap.put("qylx", companyBackUp.getQylx().trim());
				}

				if ((null != companyBackUp.getDwdz1())
						&& (0 < companyBackUp.getDwdz1().trim().length())) {
					paraMap.put("dwdz1", companyBackUp.getDwdz1());
				}  
				if ((null != companyBackUp.getHyfl())
						&& (0 < companyBackUp.getHyfl().trim().length())) {
					paraMap.put("hyfl", companyBackUp.getHyflOneLevel());
				}
				if ((null != companyBackUp.getCounty())
						&& (0 < companyBackUp.getCounty().trim().length())) {
					paraMap.put("county", companyBackUp.getCounty());
				}
			}
//			paraMap.put("basePass", 1);
			
			// hanxc 20141223 修改 start
			String deptRole = this.getLoginUser().getDeptRole();//用户部门角色职责			
			if(null != deptRole && SysPropertiesUtil.getProperty("qiyeCode").equals(deptRole)){//企业角色
				paraMap.put("loginname", this.getLoginUser().getLoginId());
			}else if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//非管理员登录
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			// hanxc 20141223 修改 end
			
			List<CompanyBackUp> list = companyService.findCompanyBackUpList(paraMap);
			 
			JSONArray companyBeans = new JSONArray();
			for (CompanyBackUp companyBackUp : list) {
				JSONObject obj = new JSONObject();
				obj.put("companyname", companyBackUp.getCompanyname());
				obj.put("longitude", companyBackUp.getLongitude());
				obj.put("id", companyBackUp.getId());
				obj.put("dwdz2", companyBackUp.getDwdz2());
				obj.put("latitude", companyBackUp.getLatitude());
				obj.put("fddbr", companyBackUp.getFddbr());
				obj.put("fddbrlxhm", companyBackUp.getFddbrlxhm());
				obj.put("companyId", companyBackUp.getCompanyId());
				companyBeans.add(obj);
			}
			getResponse().getWriter().print(companyBeans.toString());
			//setRequestAttribute("companyBeans", companyBeans);
			return null;
		}
	/**
	 * 登录首页 根据角色判断
	 * 地图的展示形式
	 * lj 
	 * 2015-11-11
	 */
	@SuppressWarnings("unchecked")
	public String welcome(){
		//企业只能看到自己的 其他角色看到所有
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			//登录人为企业 只可以查看自己添加信息
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName="0";
				Map map = new HashMap();
				map.put("id",this.getLoginUser().getId() );
				map.put("sqlID", "queryCompanyInfoHttp");
				Map company = httpService.getMapByMap(map);
				if(company!=null&&!company.isEmpty())
				codeId  = (String) company.get("code");//获取企业的组织结构代码 用于打点
				//查询企业待整改的隐患 
				auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A23", map);
				break;
				
			}
		}
		//显示登录人未读的通知公告信息 lj 2015-12-23
		Map map = new HashMap();
		map.put("userId",this.getLoginUser().getId());
		map.put("needCheckUser", ","+this.getLoginUser().getId()+",");
		notices = tbMapService.queryMapListByMap("query_noRead_notices_index", map);
		
		//查询不同角色的待审核事项
		String userId=getLoginUser().getId();
		String deptCode=userService.findUserById(userId).getDeptCode();
		String flag = "";
		List<UserRight> ll =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:ll)
		{
			flag += ur.getRole().getRoleCode()+ ",";
		}
		if(flag.contains("A17")){//安委会办公室
			roleName =  "1";
			//待处理的 隐患
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A17", map);
		}
		else if(flag.contains("A18")){//安委会职能部门
			roleName =  "8";
			//待审核 节假日开停工 、领导带班情况 
			map.put("deptCode", deptCode);
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A18", map);
		}
		else if(flag.contains("A02")){//局领导
			roleName =  "5";
			//待审核的 隐患、收文、发文、案件、文书数、失信行为
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A02", map);
		}
		else if(flag.contains("A03")){//综合处处长
			roleName =  "6";
			//待审核挂牌督办 文书数
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A03", map);
		}
		else if(flag.contains("A05")){//危化处处长
			roleName =  "7";
			//待审核 节假日开停工 、领导带班情况 文书数
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A05", map);
		}
		
		else if(flag.contains("A04")){//综合处科员 整改挂牌督办
			roleName =  "9";
			//待审核挂牌督办 文书数
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A04", map);
		}
		else if(flag.contains("A09")){//大队长
			roleName =  "4";
			//待审核的 隐患、危险源、企业检查计划、案件、文书数、失信行为
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A09", map);
		}
		else if(flag.contains("A10")){//监察大队队员 
			if(flag.contains("A30"))//法务
			{
				if(flag.contains("A11")){//中队长
					roleName =  "1011";
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
					paraMapEnt.put("deptCode",deptCode);
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					if(ents.size()>0){
						for(int i=0;i< ents.size();i++){
							companmyIds+=ents.get(i).get("row_id")+",";
						}
						map.put("companyIds", companmyIds);
					}else{
						map.put("companyIds", "-");
					}
					//待审核的隐患、挂牌督办
					map.put("ajzddz2", deptCode);
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A3011", map);
				}
				else if(flag.contains("A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
					roleName =  "1012";
					//待整改的隐患信息、待审核的企业信息、待整改的挂牌督办、待上报的监督检查 
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
					paraMapEnt.put("userId",this.getLoginUser().getId());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					if(ents.size()>0){
						for(int i=0;i< ents.size();i++){
							companmyIds+=ents.get(i).get("row_id")+",";
						}
						map.put("companyIds", companmyIds);
					}else{
						map.put("companyIds", "-");
					}
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A3012", map);
				}
				else
				{
					roleName =  "10";
					//待审核案件 文书数
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A30", map);
				}
			}
			else
			{
				if(flag.contains("A11")){//中队长
					roleName =  "1011";
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
					paraMapEnt.put("deptCode",deptCode);
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					if(ents.size()>0){
						for(int i=0;i< ents.size();i++){
							companmyIds+=ents.get(i).get("row_id")+",";
						}
						map.put("companyIds", companmyIds);
					}else{
						map.put("companyIds", "-");
					}
					//待审核的隐患、挂牌督办
					map.put("ajzddz2", deptCode);
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A1011", map);
				}
				else if(flag.contains("A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
					roleName =  "1012";
					//待整改的隐患信息、待审核的企业信息、待上报的监督检查 
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
					paraMapEnt.put("userId",this.getLoginUser().getId());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					if(ents.size()>0){
						for(int i=0;i< ents.size();i++){
							companmyIds+=ents.get(i).get("row_id")+",";
						}
						map.put("companyIds", companmyIds);
					}else{
						map.put("companyIds", "-");
					}
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A1012", map);
				}
				else
				{
					roleName =  "10";
					//待审核案件 文书数
					auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A10", map);
				}
			}
		}
		else if(flag.contains("A11")){//中队长
			roleName =  "3";
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
			paraMapEnt.put("deptCode",deptCode);
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			if(ents.size()>0){
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				map.put("companyIds", companmyIds);
			}else{
				map.put("companyIds", "-");
			}
			//待审核的隐患、挂牌督办
			map.put("ajzddz2", deptCode);
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A11", map);
		}
		else if(flag.contains("A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
			roleName =  "2";
			//待整改的隐患信息、待审核的企业信息、待整改的挂牌督办、待上报的监督检查 
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
			paraMapEnt.put("userId",this.getLoginUser().getId());
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			if(ents.size()>0){
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				map.put("companyIds", companmyIds);
			}else{
				map.put("companyIds", "-");
			}
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_A12", map);
		}
		else if(this.getLoginUser().getDeptCode().startsWith("002001"))
		{
			roleName =  "11";
			//待审核文书数
			auditInfo = tbMapService.getMapDetailByMap("query_audit_info_ALL", map);
		}
		
		return SUCCESS;
	}
	
	public String welcomeEnt(){
		String loginId = this.getLoginUser().getLoginId();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("loginId", loginId);
		List<EntBaseInfo> list = entBaseInfoService.findEntBaseInfo(paraMap);
		if(list != null && list.size()>0){
			entBaseInfoId = list.get(0).getId();
		}
		return SUCCESS;
	}

	/**
	 * @return the moduleList
	 */
	public List<Module> getModuleList() {
		return moduleList;
	}

	/**
	 * @param moduleList
	 *            the moduleList to set
	 */
	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId
	 *            the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * @return the isChecked
	 */
	public Boolean getIsChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked
	 *            the isChecked to set
	 */
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @return the showCheck
	 */
	public Boolean getShowCheck() {
		return showCheck;
	}

	/**
	 * @param showCheck
	 *            the showCheck to set
	 */
	public void setShowCheck(Boolean showCheck) {
		this.showCheck = showCheck;
	}

	/**
	 * @return the code
	 */
	public Code getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Code code) {
		this.code = code;
	}

	/**
	 * @return the func
	 */
	public String getFunc() {
		return func;
	}

	/**
	 * @param func
	 *            the func to set
	 */
	public void setFunc(String func) {
		this.func = func;
	}

	/**
	 * @return the loginType
	 */
	public Integer getLoginType() {
		return loginType;
	}

	/**
	 * @param loginType
	 *            the loginType to set
	 */
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getExtendParams() {
		return extendParams;
	}

	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Map<String, Object>> getItems() {
		return items;
	}

	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}

	public List<QuicklyStart> getStartList() {
		return startList;
	}

	public void setStartList(List<QuicklyStart> startList) {
		this.startList = startList;
	}
	
	public String getValidateFlag() {
		return validateFlag;
	}

	public void setValidateFlag(String validateFlag) {
		this.validateFlag = validateFlag;
	}

	public List<QuicklyStart> getQuikStarList() {
		return quikStarList;
	}

	public void setQuikStarList(List<QuicklyStart> quikStarList) {
		this.quikStarList = quikStarList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Map> getNotices() {
		return notices;
	}

	public void setNotices(List<Map> notices) {
		this.notices = notices;
	}

	public Map getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(Map auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSelModule() {
		return selModule;
	}

	public void setSelModule(String selModule) {
		this.selModule = selModule;
	}

	public String getEntBaseInfoId() {
		return entBaseInfoId;
	}

	public void setEntBaseInfoId(String entBaseInfoId) {
		this.entBaseInfoId = entBaseInfoId;
	}

	public CompanyBackUp getCompanyBackUp() {
		return companyBackUp;
	}

	public void setCompanyBackUp(CompanyBackUp companyBackUp) {
		this.companyBackUp = companyBackUp;
	}
	
}
