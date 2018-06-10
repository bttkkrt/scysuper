/**
 * 
 */
package com.jshx.module.admin.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.UserRole;

/**
 * @author f_cheng
 * 
 */
@Component("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl implements
		com.jshx.module.admin.dao.UserRoleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see userrole.dao.UserRoleDao#findUserRoleById(java.lang.String)
	 */
	public UserRole findUserRoleById(String id) {
		return (UserRole) this.getObjectById(UserRole.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userrole.dao.UserRoleDao#findUserRoleByPage(com.jshx.core.base.vo.Pagination,
	 *      java.util.Map)
	 */

	public Pagination findUserRoleByPage(Pagination page,
			Map<String, Object> paraMap) {
		return this.findPageByHqlId("queryUserRole", paraMap, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userrole.dao.UserRoleDao#findUserRoleList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRoleList(Map<String, Object> paraMap) {
		return this.findListByHqlId("queryUserRole", paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userrole.dao.UserRoleDao#hasReg(java.lang.String)
	 */

	public UserRole findUserRoleByName(String roleName) {
		return (UserRole) this.getObjectByProperty(UserRole.class, "roleName",
				roleName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.dao.UserRoleDao#getMaxModuleCodeByParent(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer getMaxModuleCodeByParent(String parentRoleCode) {
		Long length = Long.valueOf(parentRoleCode.length() + 1);
		if (parentRoleCode.equals("0"))
			parentRoleCode = "";
		if (length == 1)
			length += 1;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (parentRoleCode != null) {
			paraMap.put("parentRoleCode", parentRoleCode + "%");
			paraMap.put("length", length);
		}
		List list = this.findListByHqlId("getMaxRoleCodeByParent", paraMap);
		if (list.get(0) != null) {
			return (Integer) list.get(0);
		} else
			return null;
	}

	public List<UserRole> findLeafRole(Map<String, Object> paraMap) {
		return this.findListByHqlId("findLeafRole", paraMap);
	}

	public Integer cntUsersinRole(String[] ids){
		List<String> roleIds = new ArrayList<String>();
		for(String item : ids){
			roleIds.add(item);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleIds", roleIds);
		List<?> list = this.findListByHqlId("cntUsersinRole", paraMap);
		if(list!=null && list.size()>0)
			return ((Long)list.get(0)).intValue();
		else
			return 0;
	}
}
