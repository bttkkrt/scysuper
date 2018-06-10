package com.jshx.dgjl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dgjl.entity.ProRec;
import com.jshx.dgjl.dao.ProRecDao;

@Component("proRecDao")
public class ProRecDaoImpl extends BaseDaoImpl implements ProRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findProRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProRec getById(String id)
	{
		return (ProRec)this.getObjectById(ProRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProRec proRec)
	{
		proRec.setId(null);
		this.saveOrUpdateObject(proRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProRec proRec)
	{
		this.saveOrUpdateObject(proRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProRec proRec = (ProRec)this.getObjectById(ProRec.class, id);
		proRec.setDelFlag(1);
		this.saveObject(proRec);
	}
}
