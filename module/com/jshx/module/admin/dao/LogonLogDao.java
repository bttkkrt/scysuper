/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.Date;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.LogonLog;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午10:03:01  
 * 类说明  
 */
public interface LogonLogDao extends BaseDao {
	
	/**
	 * 查找登录信息
	 * 
	 * @Title: findLogByPage 
	 * @Description: 
	 * @param page
	 * @param log
	 * @param beginDate
	 * @param endDate
	 * @return Pagination  
	 */
	public Pagination findLogByPage(Pagination page, LogonLog log, Date beginDate, Date endDate);

}
