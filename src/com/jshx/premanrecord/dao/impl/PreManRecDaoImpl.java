package com.jshx.premanrecord.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.premanrecord.entity.PreManRec;
import com.jshx.premanrecord.dao.PreManRecDao;

@Component("preManRecDao")
public class PreManRecDaoImpl extends BaseDaoImpl implements PreManRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPreManRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPreManRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findPreManRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PreManRec getById(String id)
	{
		return (PreManRec)this.getObjectById(PreManRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PreManRec preManRec)
	{
		preManRec.setId(null);
		this.saveOrUpdateObject(preManRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PreManRec preManRec)
	{
		this.saveOrUpdateObject(preManRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PreManRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PreManRec preManRec = (PreManRec)this.getObjectById(PreManRec.class, id);
		preManRec.setDelFlag(1);
		this.saveObject(preManRec);
	}
}
