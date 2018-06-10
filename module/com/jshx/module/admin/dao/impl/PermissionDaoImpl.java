package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.PermissionDao;
import com.jshx.module.admin.entity.Permission;

@Component("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {


	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PermissionDao#savePermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	public Permission savePermission(Permission permission) {
		saveBaseModelObject(permission);
		return permission;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PermissionDao#updatePermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	public Permission updatePermission(Permission permission) {
		updateBaseModelObject(permission);
		return permission;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PermissionDao#delPermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	public void delPermission(Permission permission) {
		this.removeObjectById(Permission.class, permission.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PermissionDao#findPermissionByUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findPermission(String userId, String roleId, Integer permissionType, String permissionExpression) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(userId!=null && !userId.trim().equals(""))
			paraMap.put("userId", userId);
		if(roleId!=null && !roleId.trim().equals(""))
			paraMap.put("roleId", roleId);
		if(permissionType!=null && permissionType > 0)
			paraMap.put("permissionType", permissionType);
		if(permissionExpression!=null && !permissionExpression.trim().equals(""))
			paraMap.put("permissionExpression", "%"+permissionExpression+"%");
		return findListByHqlId("findPermission", paraMap);
	}

	@Override
	public List<Permission> findStrictPermission(String userId, String roleId, Integer permissionType, String permissionExpression) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(userId!=null && !userId.trim().equals(""))
			paraMap.put("userId", userId);
		if(roleId!=null && !roleId.trim().equals(""))
			paraMap.put("roleId", roleId);
		if(permissionType!=null && permissionType > 0)
			paraMap.put("permissionType", permissionType);
		if(permissionExpression!=null && !permissionExpression.trim().equals(""))
			paraMap.put("stricPermissionExpression", permissionExpression);
		return findListByHqlId("findPermission", paraMap);
	}

	@Override
	public void removePermission(String userId, String roleId,
			Integer permissionType, String permissionExpression) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(userId!=null && !userId.trim().equals(""))
			paraMap.put("userId", userId);
		if(roleId!=null && !roleId.trim().equals(""))
			paraMap.put("roleId", roleId);
		if(permissionType!=null && permissionType > 0)
			paraMap.put("permissionType", permissionType);
		if(permissionExpression!=null && !permissionExpression.trim().equals(""))
			paraMap.put("permissionExpression", "%"+permissionExpression+"%");
		executeUpdateByHqlId("removePermission", paraMap);
	}

}
