package com.jshx.lbypgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.lbypgl.entity.PpeManag;
import com.jshx.lbypgl.dao.PpeManagDao;

@Component("ppeManagDao")
public class PpeManagDaoImpl extends BaseDaoImpl implements PpeManagDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPpeManagByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPpeManag(Map<String, Object> paraMap){
		return this.findListByHqlId("findPpeManagByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PpeManag getById(String id)
	{
		return (PpeManag)this.getObjectById(PpeManag.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PpeManag ppeManag)
	{
		ppeManag.setId(null);
		this.saveOrUpdateObject(ppeManag);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PpeManag ppeManag)
	{
		this.saveOrUpdateObject(ppeManag);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PpeManag.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PpeManag ppeManag = (PpeManag)this.getObjectById(PpeManag.class, id);
		ppeManag.setDelFlag(1);
		this.saveObject(ppeManag);
	}
}
