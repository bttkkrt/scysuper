package com.jshx.patrolUser.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.patrolUser.entity.PatrolUser;
import com.jshx.patrolUser.dao.PatrolUserDao;

@Component("patrolUserDao")
public class PatrolUserDaoImpl extends BaseDaoImpl implements PatrolUserDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPatrolUserByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPatrolUser(Map<String, Object> paraMap){
		return this.findListByHqlId("findPatrolUserByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PatrolUser getById(String id)
	{
		return (PatrolUser)this.getObjectById(PatrolUser.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PatrolUser patrolUser)
	{
		patrolUser.setId(null);
		this.saveOrUpdateObject(patrolUser);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PatrolUser patrolUser)
	{
		this.saveOrUpdateObject(patrolUser);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PatrolUser.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PatrolUser patrolUser = (PatrolUser)this.getObjectById(PatrolUser.class, id);
		patrolUser.setDelFlag(1);
		this.saveObject(patrolUser);
	}
}
