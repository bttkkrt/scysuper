package com.jshx.qzcsjds.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qzcsjds.entity.EnforenceDecision;
import com.jshx.qzcsjds.dao.EnforenceDecisionDao;

@Component("enforenceDecisionDao")
public class EnforenceDecisionDaoImpl extends BaseDaoImpl implements EnforenceDecisionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEnforenceDecisionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<EnforenceDecision> findEnforenceDecision(Map<String, Object> paraMap){
		return this.findListByHqlId("findEnforenceDecisionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EnforenceDecision getById(String id)
	{
		return (EnforenceDecision)this.getObjectById(EnforenceDecision.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EnforenceDecision enforenceDecision)
	{
		enforenceDecision.setId(null);
		this.saveOrUpdateObject(enforenceDecision);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EnforenceDecision enforenceDecision)
	{
		this.saveOrUpdateObject(enforenceDecision);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EnforenceDecision.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EnforenceDecision enforenceDecision = (EnforenceDecision)this.getObjectById(EnforenceDecision.class, id);
		enforenceDecision.setDelFlag(1);
		this.saveObject(enforenceDecision);
	}
}
