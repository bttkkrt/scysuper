package com.jshx.module.admin.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.LogoffLog;
import com.jshx.module.admin.dao.LogoffLogDao;

@Component("logoffLogDao")
public class LogoffLogDaoImpl extends BaseDaoImpl implements LogoffLogDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLogoffLogByMap", paraMap, page);
	}
	/**
	 * 分页查询
	 * @param pagination 分页信息
	 * @param logoffLog 登出日志的信息
	 * @param queryLogoffDateStart 开始时间
	 * @param queryLogoffDateEnd 结束时间
	 * @return 分页信息
	 */
	public Pagination findLogByPage(Pagination pagination, LogoffLog logoffLog, Date queryLogoffDateStart, Date queryLogoffDateEnd){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(logoffLog!=null){
			if(logoffLog.getUser()!=null){
				if(logoffLog.getUser().getDisplayName()!=null)
					paraMap.put("userName", "%"+logoffLog.getUser().getDisplayName().trim()+"%");
				if(logoffLog.getUser().getDeptCode()!=null)
					paraMap.put("deptCode", logoffLog.getUser().getDeptCode().trim()+"%");
			}	
			if ((null != logoffLog.getBrowser()) && (0 < logoffLog.getBrowser().trim().length())){
				paraMap.put("browser", logoffLog.getBrowser().trim());
			}
			if ((null != logoffLog.getOperationsystem()) && (0 < logoffLog.getOperationsystem().trim().length())){
				paraMap.put("os", logoffLog.getOperationsystem().trim());
			}
			if ((null != logoffLog.getLogoffType()) && (0 < logoffLog.getLogoffType().trim().length())){
				paraMap.put("logoffType", logoffLog.getLogoffType().trim());
			}
		}
		if(queryLogoffDateStart!=null)
			paraMap.put("beginDate", queryLogoffDateStart);
		if(queryLogoffDateEnd!=null)
			paraMap.put("endDate", queryLogoffDateEnd);
		return super.findPageByHqlId("queryLogoffLog", paraMap, pagination);		
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLogoffLog(Map<String, Object> paraMap){
		return this.findListByHqlId("findLogoffLogByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LogoffLog getById(String id)
	{
		return (LogoffLog)this.getObjectById(LogoffLog.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LogoffLog logoffLog)
	{
		logoffLog.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(logoffLog);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LogoffLog logoffLog)
	{
		this.saveOrUpdateObject(logoffLog);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LogoffLog.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LogoffLog logoffLog = (LogoffLog)this.getObjectById(LogoffLog.class, id);
		logoffLog.setDelFlag(1);
		this.saveObject(logoffLog);
	}
}
