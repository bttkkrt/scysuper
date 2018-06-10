package com.jshx.module.admin.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.User;

/**
 * 自定义权限过滤器，访问某个资源时只要其中有一个允许的角色即可
 * 
 * @author Chenjian
 * @version 2013/06/06
 *
 */
public class RolesOrAuthorizationFilter extends RolesAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		HttpServletRequest req = (HttpServletRequest)request;
		User user = (User)req.getSession().getAttribute(Constants.CURR_USER);
		if(user==null)
			return true;
		if(user.getIsSuperAdmin()!=null && user.getIsSuperAdmin())
			return true;
		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}
		boolean perm = false;
		for(String role : rolesArray){
			if(subject.hasRole(role)){
				perm = true;
				break;
			}
		}
		return perm;
	}

}
