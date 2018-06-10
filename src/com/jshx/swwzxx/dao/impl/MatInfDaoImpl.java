package com.jshx.swwzxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.swwzxx.entity.MatInf;
import com.jshx.swwzxx.dao.MatInfDao;

@Component("matInfDao")
public class MatInfDaoImpl extends BaseDaoImpl implements MatInfDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMatInfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMatInf(Map<String, Object> paraMap){
		return this.findListByHqlId("findMatInfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MatInf getById(String id)
	{
		return (MatInf)this.getObjectById(MatInf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MatInf matInf)
	{
		matInf.setId(null);
		this.saveOrUpdateObject(matInf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MatInf matInf)
	{
		this.saveOrUpdateObject(matInf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MatInf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MatInf matInf = (MatInf)this.getObjectById(MatInf.class, id);
		matInf.setDelFlag(1);
		this.saveObject(matInf);
	}
}
