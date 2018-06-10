package com.jshx.xygsgpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xygsgpx.entity.NewWorTra;
import com.jshx.xygsgpx.dao.NewWorTraDao;

@Component("newWorTraDao")
public class NewWorTraDaoImpl extends BaseDaoImpl implements NewWorTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findNewWorTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findNewWorTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findNewWorTraByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public NewWorTra getById(String id)
	{
		return (NewWorTra)this.getObjectById(NewWorTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(NewWorTra newWorTra)
	{
		newWorTra.setId(null);
		this.saveOrUpdateObject(newWorTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(NewWorTra newWorTra)
	{
		this.saveOrUpdateObject(newWorTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(NewWorTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		NewWorTra newWorTra = (NewWorTra)this.getObjectById(NewWorTra.class, id);
		newWorTra.setDelFlag(1);
		this.saveObject(newWorTra);
	}
}
