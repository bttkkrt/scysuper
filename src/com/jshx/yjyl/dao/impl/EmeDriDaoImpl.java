package com.jshx.yjyl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yjyl.entity.EmeDri;
import com.jshx.yjyl.dao.EmeDriDao;

@Component("emeDriDao")
public class EmeDriDaoImpl extends BaseDaoImpl implements EmeDriDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEmeDriByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEmeDri(Map<String, Object> paraMap){
		return this.findListByHqlId("findEmeDriByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EmeDri getById(String id)
	{
		return (EmeDri)this.getObjectById(EmeDri.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EmeDri emeDri)
	{
		emeDri.setId(null);
		this.saveOrUpdateObject(emeDri);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EmeDri emeDri)
	{
		this.saveOrUpdateObject(emeDri);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EmeDri.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EmeDri emeDri = (EmeDri)this.getObjectById(EmeDri.class, id);
		emeDri.setDelFlag(1);
		this.saveObject(emeDri);
	}
}
