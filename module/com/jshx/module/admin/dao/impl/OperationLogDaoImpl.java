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
import com.jshx.module.admin.dao.OperationLogDao;
import com.jshx.module.admin.entity.OperationLog;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:11:40  
 * 类说明  
 */
@Component("operationLogDao")
public class OperationLogDaoImpl extends BaseDaoImpl implements OperationLogDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.OperationLogDao#findLogByPage(com.jshx.core.base.vo.Pagination, com.jshx.module.admin.entity.OperationLog)
	 */
	
	public Pagination findLogByPage(Pagination page, OperationLog log, Date beginDate, Date endDate) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(log!=null){
			if(log.getVisitor()!=null){
				if(log.getVisitor()!=null){
					if(log.getVisitor().getDisplayName()!=null)
						paraMap.put("userName", "%"+log.getVisitor().getDisplayName().trim()+"%");
					if(log.getVisitor().getDeptCode()!=null)
						paraMap.put("deptCode", log.getVisitor().getDeptCode().trim()+"%");
				}
			}
			if(log.getModule()!=null)
				paraMap.put("module", log.getModule());
			if(log.getUrl()!=null && !log.getUrl().trim().equals(""))
				paraMap.put("url", "%"+log.getUrl().trim()+"%");
		}
		if(beginDate!=null)
			paraMap.put("beginDate", beginDate);
		if(endDate!=null)
			paraMap.put("endDate", endDate);
		return super.findPageByHqlId("queryOperationLog", paraMap, page);
	}

}
