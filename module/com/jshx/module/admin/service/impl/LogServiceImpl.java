/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.LogonLogDao;
import com.jshx.module.admin.dao.ModuleDao;
import com.jshx.module.admin.dao.OperationLogDao;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.OperationLog;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.LogService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:31:12  
 * 类说明  
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl implements
		LogService {
	
	@Autowired() 
	@Qualifier("operationLogDao")
	private OperationLogDao operationLogDao;
	
	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@Autowired() 
	@Qualifier("moduleDao")
	private ModuleDao moduleDao;
	
	@Autowired() 
	@Qualifier("logonLogDao")
	private LogonLogDao logonLogDao;

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.OperationLogService#findOperationLogByPage(com.jshx.core.base.vo.Pagination, com.jshx.module.admin.entity.OperationLog, java.util.Date, java.util.Date)
	 */

	public Pagination findOperationLogByPage(Pagination pagination,
			OperationLog log, Date beginDate, Date endDate) {
				
		if(log!=null && log.getModule()!=null && log.getModule().getModuleCode()!=null){
			Module module = (Module)moduleDao.getObjectByProperty(Module.class, "moduleCode", log.getModule().getModuleCode().trim());
			log.setModule(module);
		}
		return operationLogDao.findLogByPage(pagination, log, beginDate, endDate);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.OperationLogService#saveLog(com.jshx.module.admin.entity.OperationLog)
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public OperationLog saveLog(OperationLog log) {
		log.setVisitedDate(new Date());
		User user = (User)userDAO.getObjectById(User.class, log.getVisitor().getId());
		log.setVisitor(user);
		if(log.getModule()!=null && log.getModule().getModuleCode()!=null){
			Module module = (Module)moduleDao.getObjectByProperty(Module.class, "moduleCode", log.getModule().getModuleCode());
			log.setModule(module);
		}
		operationLogDao.saveBaseModelObject(log);
		return log;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.LogService#findLogByPage(com.jshx.core.base.vo.Pagination, com.jshx.module.admin.entity.LogonLog, java.util.Date, java.util.Date)
	 */

	public Pagination findLogByPage(Pagination page, LogonLog log,
			Date beginDate, Date endDate) {
		return logonLogDao.findLogByPage(page, log, beginDate, endDate);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.LogService#saveLogonLog(com.jshx.module.admin.entity.LogonLog)
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public LogonLog saveLogonLog(LogonLog log) {
		log.setVisitedDate(new Date());
		User user = (User)userDAO.getObjectById(User.class, log.getVisitor().getId());
		log.setVisitor(user);
		logonLogDao.saveBaseModelObject(log);
		return log;
	}

}
