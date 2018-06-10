/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.UserService;



/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-11 上午10:04:15  
 * 判断当前用户是不是管理员  
 */
public class IsAdminTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private String admin;
	
	private String userId;
	
	@SuppressWarnings("rawtypes")
	public int doStartTag() throws JspException {
        request = (HttpServletRequest)this.pageContext.getRequest();
		if(!admin.equals("super")&&!admin.equals("admin")&&!admin.equals("normal")&&!admin.equals("notsuper")&&!admin.equals("notadmin"))
			throw new JspException("admin属性赋值错误，只能是super、notadmin");
		User user = null;
		UserService userService = (UserService)SpringContextHolder.getBean("userService");
		
		if(userId!=null && !userId.trim().equals("")){
			user = userService.findUserById(userId);
			List rightList = user.getUserRoles();
    		if(rightList!=null && rightList.size()>0){
    			String[] roleIds = new String[rightList.size()];
    			for(int i = 0; i<rightList.size(); i++){
    				UserRight right = (UserRight)rightList.get(i);
    				roleIds[i] = right.getRole().getId();
    			}
    			user.setRoleIds(roleIds);
    		}
		}else
			user = (User)request.getSession().getAttribute(Constants.CURR_USER);
		Boolean isAdmin = false;
		List rightList = user.getUserRoles();
		if (rightList != null && rightList.size() > 0) {
			String[] roleIds = new String[rightList.size()];
			
			for (int i = 0; i < rightList.size(); i++) {
				UserRight right = (UserRight) rightList.get(i);
				roleIds[i] = right.getRole().getId();
				if (right.getRole().getIsSupAdmin()!=null && right.getRole().getIsSupAdmin()==1) {
					isAdmin = true;
					break;
				}
			}
		}
		if(admin.equals("super")){
			if(isAdmin)
				return EVAL_BODY_INCLUDE;
			else
				return SKIP_BODY;
		}
		if(admin.equals("notadmin")){
			if(isAdmin)
				return SKIP_BODY;
			else
				return EVAL_BODY_INCLUDE;
		}
		
		/**
		String[] roleIds = user.getRoleIds();
		if(roleIds==null){
			if(admin.equals("normal")||admin.equals("notsuper"))
				return EVAL_BODY_INCLUDE;
			else if(admin.equals("super")||admin.equals("admin"))
				return SKIP_BODY;
		}
		if(admin.equals("normal"))
			return EVAL_BODY_INCLUDE;
		
		for(String roleId:roleIds){
			if(admin.equals("super")){
				if(roleId.equals(Constants.SUPER_ADMIN))
					return EVAL_BODY_INCLUDE;
			}else if(admin.equals("admin")){
				if(roleId.equals(Constants.SUPER_ADMIN)||roleId.equals(Constants.DEPT_ADMIN))
					return EVAL_BODY_INCLUDE;
			}
		}
		
		if(admin.equals("notsuper")){
			int i = 0;
			for(String roleId:roleIds){
				if(roleId.equals(Constants.SUPER_ADMIN))
					break;
				i++;
			}
			if(i==roleIds.length)
				return EVAL_BODY_INCLUDE;
		}
		
		if(admin.equals("notadmin")){
			int i = 0;
			for(String roleId:roleIds){
				if(roleId.equals(Constants.SUPER_ADMIN))
					break;
				if(roleId.equals(Constants.SUPER_ADMIN)||roleId.equals(Constants.DEPT_ADMIN))
					break;
				i++;
			}
			if(i==roleIds.length)
				return EVAL_BODY_INCLUDE;
		}
		*/
		return SKIP_BODY;
	}

	/**
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
