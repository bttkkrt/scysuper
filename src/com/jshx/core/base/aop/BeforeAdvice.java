/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.jshx.core.exception.AopException;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午08:45:55  
 * 方法运行后执行前的操作接口
 */
public interface BeforeAdvice {
	
	/**
	 * 方法运行后执行前的操作
	 * 
	 * @param jp 切点
	 * @return void   
	 * @throws com.jshx.core.exception.AopException
	 */
	public void doBefore(JoinPoint jp) throws AopException;

}
