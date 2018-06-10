package com.jshx.whpaqtjpj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpaqtjpj.entity.CheRepFil;
import com.jshx.whpaqtjpj.dao.CheRepFilDao;

@Component("cheRepFilDao")
public class CheRepFilDaoImpl extends BaseDaoImpl implements CheRepFilDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheRepFilByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheRepFil(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheRepFilByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheRepFil getById(String id)
	{
		return (CheRepFil)this.getObjectById(CheRepFil.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheRepFil cheRepFil)
	{
		cheRepFil.setId(null);
		this.saveOrUpdateObject(cheRepFil);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheRepFil cheRepFil)
	{
		this.saveOrUpdateObject(cheRepFil);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheRepFil.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheRepFil cheRepFil = (CheRepFil)this.getObjectById(CheRepFil.class, id);
		cheRepFil.setDelFlag(1);
		this.saveObject(cheRepFil);
	}
}
