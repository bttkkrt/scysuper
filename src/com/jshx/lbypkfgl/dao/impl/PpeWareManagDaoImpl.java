package com.jshx.lbypkfgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.lbypkfgl.entity.PpeWareManag;
import com.jshx.lbypkfgl.dao.PpeWareManagDao;

@Component("ppeWareManagDao")
public class PpeWareManagDaoImpl extends BaseDaoImpl implements PpeWareManagDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPpeWareManagByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPpeWareManag(Map<String, Object> paraMap){
		return this.findListByHqlId("findPpeWareManagByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PpeWareManag getById(String id)
	{
		return (PpeWareManag)this.getObjectById(PpeWareManag.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PpeWareManag ppeWareManag)
	{
		ppeWareManag.setId(null);
		this.saveOrUpdateObject(ppeWareManag);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PpeWareManag ppeWareManag)
	{
		this.saveOrUpdateObject(ppeWareManag);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PpeWareManag.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PpeWareManag ppeWareManag = (PpeWareManag)this.getObjectById(PpeWareManag.class, id);
		ppeWareManag.setDelFlag(1);
		this.saveObject(ppeWareManag);
	}
}
