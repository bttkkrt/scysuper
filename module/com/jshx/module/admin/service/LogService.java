/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.Date;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.OperationLog;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:28:19  
 * 类说明  
 */
public interface LogService extends BaseService {
	
	/**
	 * 查看访问日志
	 * 
	 * @param pagination
	 * @param log
	 * @param beginDate
	 * @param endDate
	 * @return Pagination 
	 */
	public Pagination findOperationLogByPage(Pagination pagination, OperationLog log, Date beginDate, Date endDate);
	
	/**
	 * 保存访问记录
	 * 
	 * @param log
	 * @return OperationLog   
	 */
	public OperationLog saveLog(OperationLog log);
	
	/**
	 * 查找登录日志
	 *  
	 * @param page
	 * @param log
	 * @param beginDate
	 * @param endDate
	 * @return Pagination 
	 */
	public Pagination findLogByPage(Pagination page, LogonLog log,
			Date beginDate, Date endDate);
	
	/**
	 * 保存登录日志
	 * 
	 * @param log
	 * @return LogonLog 
	 */
	public LogonLog saveLogonLog(LogonLog log);

}
