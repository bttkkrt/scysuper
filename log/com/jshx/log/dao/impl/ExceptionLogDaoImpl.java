package com.jshx.log.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.entity.ExceptionLog;
import com.jshx.log.dao.ExceptionLogDao;

@Component("exceptionLogDao")
public class ExceptionLogDaoImpl extends BaseDaoImpl implements ExceptionLogDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findExceptionLogByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<?> findExceptionLog(Map<String, Object> paraMap){
		return this.findListByHqlId("findExceptionLogByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ExceptionLog getById(String id)
	{
		return (ExceptionLog)this.getObjectById(ExceptionLog.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ExceptionLog exceptionLog)
	{
		exceptionLog.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(exceptionLog);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ExceptionLog exceptionLog)
	{
		this.saveOrUpdateObject(exceptionLog);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ExceptionLog.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ExceptionLog exceptionLog = (ExceptionLog)this.getObjectById(ExceptionLog.class, id);
		exceptionLog.setDelFlag(1);
		this.saveObject(exceptionLog);
	}
}
