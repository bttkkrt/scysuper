package com.jshx.gzzd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gzzd.entity.Gzzd;
import com.jshx.gzzd.dao.GzzdDao;

@Component("gzzdDao")
public class GzzdDaoImpl extends BaseDaoImpl implements GzzdDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findGzzdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGzzd(Map<String, Object> paraMap){
		return this.findListByHqlId("findGzzdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gzzd getById(String id)
	{
		return (Gzzd)this.getObjectById(Gzzd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gzzd gzzd)
	{
		gzzd.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(gzzd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gzzd gzzd)
	{
		this.saveOrUpdateObject(gzzd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gzzd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gzzd gzzd = (Gzzd)this.getObjectById(Gzzd.class, id);
		gzzd.setDelFlag(1);
		this.saveObject(gzzd);
	}
}
