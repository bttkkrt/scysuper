package com.jshx.czrypxzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.czrypxzs.entity.TraCer;
import com.jshx.czrypxzs.dao.TraCerDao;

@Component("traCerDao")
public class TraCerDaoImpl extends BaseDaoImpl implements TraCerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findTraCerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTraCer(Map<String, Object> paraMap){
		return this.findListByHqlId("findTraCerByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TraCer getById(String id)
	{
		return (TraCer)this.getObjectById(TraCer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TraCer traCer)
	{
		traCer.setId(null);
		this.saveOrUpdateObject(traCer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TraCer traCer)
	{
		this.saveOrUpdateObject(traCer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(TraCer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		TraCer traCer = (TraCer)this.getObjectById(TraCer.class, id);
		traCer.setDelFlag(1);
		this.saveObject(traCer);
	}
}
