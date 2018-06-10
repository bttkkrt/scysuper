package com.jshx.heflRelation.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.heflRelation.entity.HyflRelation;
import com.jshx.heflRelation.dao.HyflRelationDao;

@Component("hyflRelationDao")
public class HyflRelationDaoImpl extends BaseDaoImpl implements HyflRelationDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHyflRelationByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findHyflRelation(Map<String, Object> paraMap){
		return this.findListByHqlId("findHyflRelationByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HyflRelation getById(String id)
	{
		return (HyflRelation)this.getObjectById(HyflRelation.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HyflRelation hyflRelation)
	{
		hyflRelation.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(hyflRelation);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HyflRelation hyflRelation)
	{
		this.saveOrUpdateObject(hyflRelation);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HyflRelation.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HyflRelation hyflRelation = (HyflRelation)this.getObjectById(HyflRelation.class, id);
		hyflRelation.setDelFlag(1);
		this.saveObject(hyflRelation);
	}
}
