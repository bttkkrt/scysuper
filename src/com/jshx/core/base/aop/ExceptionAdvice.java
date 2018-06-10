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
 * @version 创建时间：2011-2-18 上午08:48:21  
 * 异常处理接口 
 */
public interface ExceptionAdvice {
	
	/**
	 * 异常处理
	 * 
	 * @param jp
	 * @param ex
	 * @return void  
	 * @throws com.jshx.core.exception.AopException
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) throws AopException;

}
