package com.jshx.rybzxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.rybzxx.entity.HonRec;
import com.jshx.rybzxx.dao.HonRecDao;

@Component("honRecDao")
public class HonRecDaoImpl extends BaseDaoImpl implements HonRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHonRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findHonRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findHonRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HonRec getById(String id)
	{
		return (HonRec)this.getObjectById(HonRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HonRec honRec)
	{
		honRec.setId(null);
		this.saveOrUpdateObject(honRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HonRec honRec)
	{
		this.saveOrUpdateObject(honRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HonRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HonRec honRec = (HonRec)this.getObjectById(HonRec.class, id);
		honRec.setDelFlag(1);
		this.saveObject(honRec);
	}
}
