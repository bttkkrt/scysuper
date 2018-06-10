package com.jshx.ylwsjggl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ylwsjggl.entity.MedIns;
import com.jshx.ylwsjggl.dao.MedInsDao;

@Component("medInsDao")
public class MedInsDaoImpl extends BaseDaoImpl implements MedInsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMedInsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMedIns(Map<String, Object> paraMap){
		return this.findListByHqlId("findMedInsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MedIns getById(String id)
	{
		return (MedIns)this.getObjectById(MedIns.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MedIns medIns)
	{
		medIns.setId(null);
		this.saveOrUpdateObject(medIns);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MedIns medIns)
	{
		this.saveOrUpdateObject(medIns);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MedIns.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MedIns medIns = (MedIns)this.getObjectById(MedIns.class, id);
		medIns.setDelFlag(1);
		this.saveObject(medIns);
	}
}
