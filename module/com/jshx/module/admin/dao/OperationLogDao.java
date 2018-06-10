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
import com.jshx.module.admin.entity.OperationLog;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:08:58  
 * 类说明  
 */
public interface OperationLogDao extends BaseDao {
	
	/**
	 * 查找访问记录
	 * 
	 * @Title: findLogByPage 
	 * @Description: 
	 * @param page
	 * @param log
	 * @param beginDate
	 * @param endDate
	 * @return Pagination   
	 */
	public Pagination findLogByPage(Pagination page, OperationLog log, Date beginDate, Date endDate);

}
