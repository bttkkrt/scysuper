package com.jshx.zdwxybasq.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxybasq.entity.Zdwxybasq;
import com.jshx.zdwxybasq.dao.ZdwxybasqDao;

@Component("zdwxybasqDao")
public class ZdwxybasqDaoImpl extends BaseDaoImpl implements ZdwxybasqDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZdwxybasqByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZdwxybasq(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxybasqByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxybasq getById(String id)
	{
		return (Zdwxybasq)this.getObjectById(Zdwxybasq.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxybasq zdwxybasq)
	{
		zdwxybasq.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxybasq);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxybasq zdwxybasq)
	{
		this.saveOrUpdateObject(zdwxybasq);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxybasq.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxybasq zdwxybasq = (Zdwxybasq)this.getObjectById(Zdwxybasq.class, id);
		zdwxybasq.setDelFlag(1);
		this.saveObject(zdwxybasq);
	}
}
