package com.jshx.yjzbwzyp.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yjzbwzyp.entity.Yjzbwzyp;
import com.jshx.yjzbwzyp.dao.YjzbwzypDao;

@Component("yjzbwzypDao")
public class YjzbwzypDaoImpl extends BaseDaoImpl implements YjzbwzypDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findYjzbwzypByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findYjzbwzyp(Map<String, Object> paraMap){
		return this.findListByHqlId("findYjzbwzypByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Yjzbwzyp getById(String id)
	{
		return (Yjzbwzyp)this.getObjectById(Yjzbwzyp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Yjzbwzyp yjzbwzyp)
	{
		yjzbwzyp.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(yjzbwzyp);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Yjzbwzyp yjzbwzyp)
	{
		this.saveOrUpdateObject(yjzbwzyp);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Yjzbwzyp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Yjzbwzyp yjzbwzyp = (Yjzbwzyp)this.getObjectById(Yjzbwzyp.class, id);
		yjzbwzyp.setDelFlag(1);
		this.saveObject(yjzbwzyp);
	}
}
