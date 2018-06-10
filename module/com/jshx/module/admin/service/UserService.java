/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-12 下午06:35:20 类说明
 */
public interface UserService extends BaseService {

	/**
	 * 根据角色查找用户权限
	 * 
	 * @param roleId
	 * @return List<UserRight>
	 */
	public List<UserRight> findByRole(String roleId);

	/**
	 * 根据角色和部门查找用户权限
	 * 
	 * @param roleId
	 * @param deptCode
	 * @return List<UserRight>
	 */
	public List<UserRight> findByRole(String roleId, String deptCode);

	/**
	 * 根据角色删除权限
	 * 
	 * @param roleId
	 */
	public void delByRole(String roleId);

	/**
	 * 根据用户删除权限
	 * 
	 * @param userId
	 */
	public void delByUser(String userId);

	/**
	 * 保存用户权限
	 * 
	 * @param right
	 * @return UserRight
	 */
	public UserRight saveRight(UserRight right);

	/**
	 * 初始化密码，初始密码：99999
	 * 
	 * @param id
	 * @throws BaseDaoException
	 */
	public void initPassword(String id);

	/**
	 * 判断用户是否注册过
	 * 
	 * @param loginId
	 * @return
	 * @throws BaseDaoException
	 * @return Boolean
	 * @throws
	 */
	public Boolean isReg(String loginId);

	/**
	 * 根据条件分页查找用户
	 * 
	 * @param page
	 * @param paraMap
	 * @return Pagination
	 */
	public Pagination findUserByPage(Pagination page,
			Map<String, Object> paraMap);

	/**
	 * 根据条件查找用户列表
	 * 
	 * @param paraMap
	 * @return List
	 */
	public List findUsers(Map<String, Object> paraMap);

	/**
	 * 根据用户主键查找用户
	 * 
	 * @param id
	 * @return User
	 */
	public User findUserById(String id);

	/**
	 * 根据用户ID查找用户
	 * 
	 * @param loginId
	 * @return
	 * @return User
	 * @throws
	 */
	public User findUserByLoginId(String loginId);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param roleIds
	 * @return User
	 */
	public User save(User user, String[] roleIds);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @param roleIds
	 * @return User
	 */
	public User modify(User user, String[] roleIds);

	/**
	 * 禁用用户
	 * 
	 * @param id
	 */
	public User inactiveUser(String id);

	/**
	 * 激活被禁用的用户
	 * 
	 * @param id
	 */
	public User activeUser(String id);

	/**
	 * 修改个人风格
	 * 
	 * @param id
	 * @param cssId
	 *            页面CSS代码
	 * @param displayNum
	 *            列表页面每页显示数
	 */
	public void modifyStyle(String id, String cssId, Integer displayNum);

	/**
	 * 根据部门查找用户，返回列表
	 * 
	 * @param deptCode
	 * @return List<User>
	 */
	public List<User> getUsersByDept(String deptCode);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 */
	public void modifyPassword(String id, String password);

	/**
	 * 验证用户
	 * 
	 * @param loginId
	 * @param password
	 * @return Boolean
	 */
	public User checkPassword(String loginId, String password);
	
	/**
	 * 验证用户,登录名为姓名或者用户名
	 * 
	 * @param loginId
	 * @param password
	 * @return Boolean
	 */
	public User checkPasswords(String loginId, String password);

	/**
	 * 检查手机号是否唯一
	 * 
	 * @param mobile
	 * @return Boolean
	 */
	public Boolean checkMobileUnique(String mobile);

	/**
	 * 检查部门是否存在，因为有存在已选择部门，然后部门在另一线程被删除的情况，需要前台通知用户
	 * 
	 * @param deptCode
	 * @return
	 */
	public Boolean checkDeptExist(String deptCode);

	/**
	 * 手机登录用
	 * 
	 * @param mobile
	 * @param password
	 * @return Boolean
	 */
	public User checkPasswordByMobile(String mobile, String password);

	/**
	 * 根据用户手机号和密码获得用户信息，如果手机号或密码不匹配，返回null
	 * 
	 * @param mobile
	 * @return User
	 */
	public User findUserByMobile(String mobile);

	/**
	 * 根据部门获得用户（包括下属部门的用户）
	 * 
	 * @param deptCode
	 * @return List<User>
	 */
	public List<User> findAllUsersByDept(String deptCode);

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * 物理删除用户
	 * 
	 * @param userId
	 */
	public void delUser(String userId);

	/**
	 * 逻辑删除用户
	 * 
	 * @param userId
	 */
	public void logicDelUser(String userId);

	/**
	 * 批量保存用户
	 * 
	 * @param userList
	 */
	public void save(List<User> userList) throws Exception;
}
