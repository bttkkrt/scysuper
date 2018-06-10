package com.jshx.zdwhpclqkb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwhpclqkb.entity.CheRes;
import com.jshx.zdwhpclqkb.dao.CheResDao;

@Component("cheResDao")
public class CheResDaoImpl extends BaseDaoImpl implements CheResDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheResByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheRes(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheResByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheRes getById(String id)
	{
		return (CheRes)this.getObjectById(CheRes.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheRes cheRes)
	{
		cheRes.setId(null);
		this.saveOrUpdateObject(cheRes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheRes cheRes)
	{
		this.saveOrUpdateObject(cheRes);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheRes.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheRes cheRes = (CheRes)this.getObjectById(CheRes.class, id);
		cheRes.setDelFlag(1);
		this.saveObject(cheRes);
	}
}
