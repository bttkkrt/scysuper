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
 * 方法运行后执行的操作接口
 * 
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午08:46:47  
 * 
 */
public interface AfterAdvice {
	
	/**
	 * 方法运行后执行的操作
	 * 
	 * @param jp 切点
	 * @param retVal 方法返回值
	 * @return 
	 * @throws com.jshx.core.exception.AopException  
	 */
	public void doAfter(JoinPoint jp, Object retVal) throws AopException;

}
