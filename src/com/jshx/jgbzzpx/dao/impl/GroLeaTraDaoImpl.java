package com.jshx.jgbzzpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jgbzzpx.entity.GroLeaTra;
import com.jshx.jgbzzpx.dao.GroLeaTraDao;

@Component("groLeaTraDao")
public class GroLeaTraDaoImpl extends BaseDaoImpl implements GroLeaTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findGroLeaTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGroLeaTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findGroLeaTraByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public GroLeaTra getById(String id)
	{
		return (GroLeaTra)this.getObjectById(GroLeaTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(GroLeaTra groLeaTra)
	{
		groLeaTra.setId(null);
		this.saveOrUpdateObject(groLeaTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(GroLeaTra groLeaTra)
	{
		this.saveOrUpdateObject(groLeaTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(GroLeaTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		GroLeaTra groLeaTra = (GroLeaTra)this.getObjectById(GroLeaTra.class, id);
		groLeaTra.setDelFlag(1);
		this.saveObject(groLeaTra);
	}
}
