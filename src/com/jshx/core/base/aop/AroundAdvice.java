/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.jshx.core.exception.AopException;

/**  
 * 包围通知接口  
 * 
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午08:47:53  
 * 
 */
public interface AroundAdvice {
	
	/**
	 * 实现包围通知
	 * 
	 * @param pjp 切点
	 * @throws com.jshx.core.exception.AopException
	 * @return   
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws AopException;

}
