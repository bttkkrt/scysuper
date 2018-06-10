/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Administrator          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.entity;

import java.util.List;

/**  
 * 用户模型接口
 * 
 * @author   Chenjian
 * @version 创建时间：2011-1-12 下午04:00:06  
 * 
 */
public interface IUser {
	
	/**
	 * 获得用户名
	 * 
	 * @return String   
	 */
	public String getLoginId();
	
	/**
	 * 获得用密码
	 * 
	 * @return String   
	 */
	public String getPassword();
	
	/**
	 * 获得用户权限列表
	 *  
	 * @return List  
	 */
	@SuppressWarnings("rawtypes")
	public List getUserRoles();
	
	/**
	 * 获得用户所在部门
	 * 
	 * @return IDepartment   
	 */
	public IDepartment getDept();
	
	/**
	 * 获取用户的ID
	 * 
	 * @return String 
	 */
	public String getId();
	
	/**
	 * 获取用户的姓名
	 * 
	 * @return String   
	 */
	public String getDisplayName();
}
