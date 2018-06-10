package com.jshx.log.listener;

import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.aop.AroundAdvice;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.AopException;
import com.jshx.core.utils.Constants;
import com.jshx.log.dao.DataLogDao;
import com.jshx.log.entity.DataLog;
import com.jshx.module.admin.entity.User;

/**
 * 批量查询日志
 * 
 * @author Chenjian
 *
 */
public class BatchQueryLogAdvice implements AroundAdvice{
	
	private static Logger logger = LoggerFactory.getLogger(BatchQueryLogAdvice.class);
	
	@Autowired
	@Qualifier("dataLogDao")
	private DataLogDao dataLogDao;

	@Override
	@Transactional
	public Object doAround(ProceedingJoinPoint pjp) throws AopException {
		DataLog dataLog = new DataLog();
		try{
			if (ServletActionContext.getRequest() != null) {
				User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constants.CURR_USER);
				dataLog.setCreateUserID(user.getId());
			}
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e);
		}
		dataLog.setDelFlag(0);
		dataLog.setOpType("005");
		Date createTime = new Date();
		dataLog.setCreateTime(createTime);
		Object result = null;
		try{
			result = pjp.proceed();
		}catch(Throwable e){
			logger.error(e.getLocalizedMessage(), e);
		}
		if(result !=null && (result instanceof Pagination || result instanceof List)){
			if(result instanceof Pagination){
				Pagination page = (Pagination)result;
				if(page.list!=null && page.list.size()>0){
					dataLog.setRecordNum(Long.valueOf(page.list.size()));
					dataLog.setEntityName(page.list.get(0).getClass().getCanonicalName());
					StringBuffer log = new StringBuffer("批量读取实体类");
					log.append(page.list.get(0).getClass().getCanonicalName());
					dataLog.setOpLog(log.toString());
				}else{
					dataLog = null;
					createTime = null;
					return result;
				}				
			}else if(result instanceof List){
				List<?> c = (List<?>)result;
				if(c!=null && c.size()>0){
					dataLog.setRecordNum(Long.valueOf(c.size()));
					dataLog.setEntityName(c.get(0).getClass().getCanonicalName());
					StringBuffer log = new StringBuffer("批量读取实体类");
					log.append(c.get(0).getClass().getCanonicalName());
					dataLog.setOpLog(log.toString());
				}else{
					dataLog = null;
					createTime = null;
					return result;
				}				
			}
			Date updateTime = new Date();
			dataLog.setUpdateTime(updateTime);
			long opDuration = updateTime.getTime() - createTime.getTime();
			dataLog.setOpDuration(opDuration);
			dataLogDao.save(dataLog);
		}else{
			dataLog = null;
			createTime = null;
		}
		
		return result;
	}

}
