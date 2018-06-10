/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.User;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-13 下午04:16:46  
 * 类说明  
 */
@Component("userDAOIpml")
public class UserDAOImpl extends BaseDaoImpl implements UserDAO {

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.UserDAO#countUser(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	
	public Long countUser(Map<String, Object> paraMap)  {
		List list = this.findListByHqlId("cntUsers", paraMap);
		Long cnt = null;
		if(list.get(0)!=null){
			cnt = (Long)list.get(0);
		}
		return cnt;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.UserDAO#findUserByList(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	
	public List<User> findUserByList(Map<String, Object> paraMap) {
		return this.findListByHqlId("queryUsers", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.UserDAO#findUserByPage(com.jshx.core.base.vo.Pagination, java.lang.String, java.util.Map)
	 */
	
	public Pagination findUserByPage(Pagination page,
			Map<String, Object> paraMap){
		return this.findPageByHqlId("queryUsers", paraMap, page);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserDAO#activeUser(java.lang.String)
	 */
	
	public void activeUser(String id) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", id);
		this.executeUpdateByHqlId("activeUser", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserDAO#inactiveUser(java.lang.String)
	 */
	
	public void inactiveUser(String id) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", id);
		this.executeUpdateByHqlId("inactiveUser", paraMap);
	}

	public User findUserById(String id) {
		return (User) this.getObjectById(User.class, id);
	}
	
	public void saveUser(User user) {
		this.saveObject(user);
	}
}
