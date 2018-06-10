/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.http;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-17 下午01:57:26  
 * AOP调用异常
 */
public class NetServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NetServiceException(String error){
		super(error);
	}
	
	public NetServiceException(String error, Throwable cause){
		super(error,cause);
	}

}
