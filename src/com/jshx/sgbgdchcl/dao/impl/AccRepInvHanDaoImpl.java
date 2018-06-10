package com.jshx.sgbgdchcl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sgbgdchcl.entity.AccRepInvHan;
import com.jshx.sgbgdchcl.dao.AccRepInvHanDao;

@Component("accRepInvHanDao")
public class AccRepInvHanDaoImpl extends BaseDaoImpl implements AccRepInvHanDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAccRepInvHanByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAccRepInvHan(Map<String, Object> paraMap){
		return this.findListByHqlId("findAccRepInvHanByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public AccRepInvHan getById(String id)
	{
		return (AccRepInvHan)this.getObjectById(AccRepInvHan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(AccRepInvHan accRepInvHan)
	{
		accRepInvHan.setId(null);
		this.saveOrUpdateObject(accRepInvHan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(AccRepInvHan accRepInvHan)
	{
		this.saveOrUpdateObject(accRepInvHan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(AccRepInvHan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		AccRepInvHan accRepInvHan = (AccRepInvHan)this.getObjectById(AccRepInvHan.class, id);
		accRepInvHan.setDelFlag(1);
		this.saveObject(accRepInvHan);
	}
}
