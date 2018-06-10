/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-20        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.List;
import java.util.Map;

import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.core.base.dao.BaseDao;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-20 下午03:05:13 类说明
 */
public interface UserRightDao extends BaseDao {

	/**
	 * 查找某用户的权限
	 * 
	 * @Title: findRightByUser
	 * @Description:
	 * @param user
	 * @return List<UserRight>
	 */
	public List<UserRight> findRightByUser(User user);

	/**
	 * 查找某角色的用户
	 * 
	 * @Title: findRightByRole
	 * @Description:
	 * @param role
	 * @return List<UserRight>
	 */
	public List<UserRight> findRightByRole(UserRole role);

	/**
	 * @Title: findUserRole
	 * @Description: 获得用户角色
	 * @param paraMap
	 * @return List
	 * @throws
	 */
	List<UserRole> findUserRole(Map paraMap);
}
