/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.exception;

/**  
 * AOP调用异常
 * 
 * @author   Chenjian
 * @version 创建时间：2011-2-17 下午01:57:26  
 * 
 */
public class AopException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AopException(String error){
		super(error);
	}
	
	public AopException(String error, Throwable cause){
		super(error,cause);
	}

}
