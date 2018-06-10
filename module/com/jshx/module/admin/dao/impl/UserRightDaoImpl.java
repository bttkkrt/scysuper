/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-20        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.UserRightDao;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;


/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-20 下午04:08:15  
 * 类说明  
 */
@Component("userRightDao")
public class UserRightDaoImpl extends BaseDaoImpl implements UserRightDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserRightDao#findRightByRole(com.jshx.module.admin.entity.UserRole)
	 */
	@SuppressWarnings("unchecked")
	
	public List<UserRight> findRightByRole(UserRole role) {
		return findListBy(UserRight.class, "role", role);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserRightDao#findRightByUser(com.jshx.module.admin.entity.User)
	 */
	@SuppressWarnings("unchecked")
	
	public List<UserRight> findRightByUser(User user) {
		return findListBy(UserRight.class, "user", user);
	}

	public List<UserRole> findUserRole(Map paraMap) {
		return this.findListByHqlId("findDataAccessRole", paraMap);
	}
}
