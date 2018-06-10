package com.jshx.checkResult.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.dao.CheckResultDao;

@Component("checkResultDao")
public class CheckResultDaoImpl extends BaseDaoImpl implements CheckResultDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckResultByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckResult(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckResultByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckResult getById(String id)
	{
		return (CheckResult)this.getObjectById(CheckResult.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckResult checkResult)
	{
		checkResult.setId(null);
		this.saveOrUpdateObject(checkResult);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckResult checkResult)
	{
		this.saveOrUpdateObject(checkResult);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckResult.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckResult checkResult = (CheckResult)this.getObjectById(CheckResult.class, id);
		checkResult.setDelFlag(1);
		this.saveObject(checkResult);
	}
}
