package com.jshx.safetywarining.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safetywarining.entity.Safetywarining;
import com.jshx.safetywarining.dao.SafetywariningDao;

@Component("safetywariningDao")
public class SafetywariningDaoImpl extends BaseDaoImpl implements SafetywariningDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafetywariningByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafetywarining(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafetywariningByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Safetywarining getById(String id)
	{
		return (Safetywarining)this.getObjectById(Safetywarining.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Safetywarining safetywarining)
	{
		safetywarining.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(safetywarining);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Safetywarining safetywarining)
	{
		this.saveOrUpdateObject(safetywarining);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Safetywarining.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Safetywarining safetywarining = (Safetywarining)this.getObjectById(Safetywarining.class, id);
		safetywarining.setDelFlag(1);
		this.saveObject(safetywarining);
	}
}
