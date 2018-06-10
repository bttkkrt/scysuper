package com.jshx.log.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.entity.DataLog;
import com.jshx.log.dao.DataLogDao;

@Component("dataLogDao")
public class DataLogDaoImpl extends BaseDaoImpl implements DataLogDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDataLogByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * 
	 * @param paraMap 查询条件信息
	 * @return List
	 */
	public List findDataLog(Map<String, Object> paraMap){
		return this.findListByHqlId("findDataLogByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return DataLog
	 */
	public DataLog getById(String id)
	{
		return (DataLog)this.getObjectById(DataLog.class, id);
	}

	/**
	 * 保存信息
	 * 
	 * @param dataLog 信息
	 */
	public void save(DataLog dataLog)
	{
		dataLog.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dataLog);
	}

	/**
	 * 修改信息
	 * 
	 * @param dataLog 信息
	 */
	public void update(DataLog dataLog)
	{
		this.saveOrUpdateObject(dataLog);
	}

	/**
	 * 物理删除信息
	 * @param id 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DataLog.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param id 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DataLog dataLog = (DataLog)this.getObjectById(DataLog.class, id);
		dataLog.setDelFlag(1);
		this.saveObject(dataLog);
	}
}
