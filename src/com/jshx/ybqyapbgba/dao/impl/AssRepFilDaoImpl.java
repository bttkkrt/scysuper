package com.jshx.ybqyapbgba.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ybqyapbgba.entity.AssRepFil;
import com.jshx.ybqyapbgba.dao.AssRepFilDao;

@Component("assRepFilDao")
public class AssRepFilDaoImpl extends BaseDaoImpl implements AssRepFilDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAssRepFilByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAssRepFil(Map<String, Object> paraMap){
		return this.findListByHqlId("findAssRepFilByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public AssRepFil getById(String id)
	{
		return (AssRepFil)this.getObjectById(AssRepFil.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(AssRepFil assRepFil)
	{
		assRepFil.setId(null);
		this.saveOrUpdateObject(assRepFil);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(AssRepFil assRepFil)
	{
		this.saveOrUpdateObject(assRepFil);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(AssRepFil.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		AssRepFil assRepFil = (AssRepFil)this.getObjectById(AssRepFil.class, id);
		assRepFil.setDelFlag(1);
		this.saveObject(assRepFil);
	}
}
