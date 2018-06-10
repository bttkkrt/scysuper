package com.jshx.checkCategory.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.checkCategory.dao.CheckCategoryDao;

@Component("checkCategoryDao")
public class CheckCategoryDaoImpl extends BaseDaoImpl implements CheckCategoryDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckCategoryByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckCategory(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckCategoryByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckCategory getById(String id)
	{
		return (CheckCategory)this.getObjectById(CheckCategory.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckCategory checkCategory)
	{
		checkCategory.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(checkCategory);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckCategory checkCategory)
	{
		this.saveOrUpdateObject(checkCategory);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckCategory.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckCategory checkCategory = (CheckCategory)this.getObjectById(CheckCategory.class, id);
		checkCategory.setDelFlag(1);
		this.saveObject(checkCategory);
	}
}
