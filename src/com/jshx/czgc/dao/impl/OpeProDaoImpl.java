package com.jshx.czgc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.czgc.entity.OpePro;
import com.jshx.czgc.dao.OpeProDao;

@Component("opeProDao")
public class OpeProDaoImpl extends BaseDaoImpl implements OpeProDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOpeProByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOpePro(Map<String, Object> paraMap){
		return this.findListByHqlId("findOpeProByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OpePro getById(String id)
	{
		return (OpePro)this.getObjectById(OpePro.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OpePro opePro)
	{
		opePro.setId(null);
		this.saveOrUpdateObject(opePro);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OpePro opePro)
	{
		this.saveOrUpdateObject(opePro);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OpePro.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OpePro opePro = (OpePro)this.getObjectById(OpePro.class, id);
		opePro.setDelFlag(1);
		this.saveObject(opePro);
	}
}
