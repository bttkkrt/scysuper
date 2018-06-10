package com.jshx.prebuslicense.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.prebuslicense.entity.PreBusLic;
import com.jshx.prebuslicense.dao.PreBusLicDao;

@Component("preBusLicDao")
public class PreBusLicDaoImpl extends BaseDaoImpl implements PreBusLicDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPreBusLicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPreBusLic(Map<String, Object> paraMap){
		return this.findListByHqlId("findPreBusLicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PreBusLic getById(String id)
	{
		return (PreBusLic)this.getObjectById(PreBusLic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PreBusLic preBusLic)
	{
		preBusLic.setId(null);
		this.saveOrUpdateObject(preBusLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PreBusLic preBusLic)
	{
		this.saveOrUpdateObject(preBusLic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PreBusLic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PreBusLic preBusLic = (PreBusLic)this.getObjectById(PreBusLic.class, id);
		preBusLic.setDelFlag(1);
		this.saveObject(preBusLic);
	}
}
