package com.jshx.jjrktg.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jjrktg.entity.ShuHol;
import com.jshx.jjrktg.dao.ShuHolDao;

@Component("shuHolDao")
public class ShuHolDaoImpl extends BaseDaoImpl implements ShuHolDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findShuHolByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findShuHol(Map<String, Object> paraMap){
		return this.findListByHqlId("findShuHolByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ShuHol getById(String id)
	{
		return (ShuHol)this.getObjectById(ShuHol.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ShuHol shuHol)
	{
		shuHol.setId(null);
		this.saveOrUpdateObject(shuHol);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ShuHol shuHol)
	{
		this.saveOrUpdateObject(shuHol);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ShuHol.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ShuHol shuHol = (ShuHol)this.getObjectById(ShuHol.class, id);
		shuHol.setDelFlag(1);
		this.saveObject(shuHol);
	}
}
