/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Administrator          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.entity;

/**  
 * 部门的接口
 * 
 * @author   Chenjian
 * @version 创建时间：2011-1-12 下午03:56:36  
 * 
 */
public interface IDepartment {
	
	/**
	 * 获得部门编号
	 * 
	 * @return String  
	 */
	public String getDeptCode();
	
	/**
	 * 获得部门ID
	 * 
	 * @return String   
	 */
	public String getId();
	
	/**
	 * 获得部门名称
	 * 
	 * @return String 
	 */
	public String getDeptName();

}
