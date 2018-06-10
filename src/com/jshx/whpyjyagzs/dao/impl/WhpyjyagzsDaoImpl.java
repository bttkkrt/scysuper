package com.jshx.whpyjyagzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpyjyagzs.entity.Whpyjyagzs;
import com.jshx.whpyjyagzs.dao.WhpyjyagzsDao;

@Component("whpyjyagzsDao")
public class WhpyjyagzsDaoImpl extends BaseDaoImpl implements WhpyjyagzsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWhpyjyagzsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWhpyjyagzs(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhpyjyagzsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpyjyagzs getById(String id)
	{
		return (Whpyjyagzs)this.getObjectById(Whpyjyagzs.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whpyjyagzs whpyjyagzs)
	{
		whpyjyagzs.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(whpyjyagzs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whpyjyagzs whpyjyagzs)
	{
		this.saveOrUpdateObject(whpyjyagzs);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Whpyjyagzs.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Whpyjyagzs whpyjyagzs = (Whpyjyagzs)this.getObjectById(Whpyjyagzs.class, id);
		whpyjyagzs.setDelFlag(1);
		this.saveObject(whpyjyagzs);
	}
}
