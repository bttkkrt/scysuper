/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-12 下午06:06:10
 * 
 */
public interface UserDAO extends BaseDao{

	/**
	 * 根据查询条件搜索用户，返回列表
	 * 
	 * @Title: getUserList
	 * @Description:
	 * @param paraMap
	 * @throws BaseDaoException
	 * @return List<User>
	 */
	public List<User> findUserByList(Map<String, Object> paraMap);

	/**
	 * 根据查询条件搜索用户，返回分页对象
	 * 
	 * @Title: findUserByPage
	 * @Description:
	 * @param page
	 * @param paraMap
	 * @throws BaseDaoException
	 * @return Pagination
	 */
	public Pagination findUserByPage(Pagination page,
			Map<String, Object> paraMap);

	/**
	 * 根据查询条件统计用户数
	 * 
	 * @Title: countUser
	 * @Description:
	 * @param paraMap
	 * @throws BaseDaoException
	 * @return Long
	 */
	public Long countUser(Map<String, Object> paraMap);
	
	/**
	 * 激活被禁用的用户
	 * 
	 * @Title: activeUser 
	 * @Description: 
	 * @param id
	 * @return void   
	 */
	public void activeUser(String id);
	
	/**
	 * 禁用用户
	 * 
	 * @Title: inactiveUser 
	 * @Description: 
	 * @param id
	 * @return void   
	 */
	public void inactiveUser(String id);
	/**
	 * @Title: 通过id获得user 
	 * @Description: 
	 *  @param id
	 *  @return
	 * @return User   
	 * @throws
	 */
	public User findUserById(String id);
	
	/**
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}
