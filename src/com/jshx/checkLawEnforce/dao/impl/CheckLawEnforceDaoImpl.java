package com.jshx.checkLawEnforce.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.checkLawEnforce.entity.CheckLawEnforce;
import com.jshx.checkLawEnforce.dao.CheckLawEnforceDao;

@Component("checkLawEnforceDao")
public class CheckLawEnforceDaoImpl extends BaseDaoImpl implements CheckLawEnforceDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckLawEnforceByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckLawEnforce(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckLawEnforceByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckLawEnforce getById(String id)
	{
		return (CheckLawEnforce)this.getObjectById(CheckLawEnforce.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckLawEnforce checkLawEnforce)
	{
		checkLawEnforce.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(checkLawEnforce);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckLawEnforce checkLawEnforce)
	{
		this.saveOrUpdateObject(checkLawEnforce);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckLawEnforce.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckLawEnforce checkLawEnforce = (CheckLawEnforce)this.getObjectById(CheckLawEnforce.class, id);
		checkLawEnforce.setDelFlag(1);
		this.saveObject(checkLawEnforce);
	}
}
