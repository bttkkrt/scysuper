/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-7        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.entity;

/**  
 * 模块的接口  
 * 
 * @author   Chenjian
 * @version 创建时间：2011-3-7 下午02:02:57  
 * 
 */
public interface IModule {
	
	/**
	 * 获取模块ID
	 * 
	 * @return String
	 */
	public String getId();
	
	/**
	 * 获取模块的名称
	 * 
	 * @return String
	 */
	public String getModuleName();
	
	/**
	 * 获取模块的编码
	 * 
	 * @return String
	 */
	public String getModuleCode();

}
