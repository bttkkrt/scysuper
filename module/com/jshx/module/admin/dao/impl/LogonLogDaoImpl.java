/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.LogonLogDao;
import com.jshx.module.admin.entity.LogonLog;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午10:04:09  
 * 类说明  
 */
@Component("logonLogDao")
public class LogonLogDaoImpl extends BaseDaoImpl implements LogonLogDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.LogonLogDao#findLogByPage(com.jshx.core.base.vo.Pagination, com.jshx.module.admin.entity.LogonLog, java.util.Date, java.util.Date)
	 */
	
	public Pagination findLogByPage(Pagination page, LogonLog log,
			Date beginDate, Date endDate) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(log!=null){
			if(log.getVisitor()!=null){
				if(log.getVisitor().getDisplayName()!=null)
					paraMap.put("userName", "%"+log.getVisitor().getDisplayName().trim()+"%");
				if(log.getVisitor().getDeptCode()!=null)
					paraMap.put("deptCode", log.getVisitor().getDeptCode().trim()+"%");
			}	
			if ((null != log.getBrowser()) && (0 < log.getBrowser().trim().length())){
				paraMap.put("browser", log.getBrowser().trim());
			}
			if ((null != log.getOs()) && (0 < log.getOs().trim().length())){
				paraMap.put("os", log.getOs().trim());
			}
			if ((null != log.getLoginType()) && (0 < log.getLoginType().trim().length())){
				paraMap.put("loginType", log.getLoginType().trim());
			}
		}
		if(beginDate!=null)
			paraMap.put("beginDate", beginDate);
		if(endDate!=null)
			paraMap.put("endDate", endDate);
		return super.findPageByHqlId("queryLogonLog", paraMap, page);
	}

}
