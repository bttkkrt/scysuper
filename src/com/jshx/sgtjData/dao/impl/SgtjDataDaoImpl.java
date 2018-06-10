package com.jshx.sgtjData.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sgtjData.entity.SgtjData;
import com.jshx.sgtjData.dao.SgtjDataDao;

@Component("sgtjDataDao")
public class SgtjDataDaoImpl extends BaseDaoImpl implements SgtjDataDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSgtjDataByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSgtjData(Map<String, Object> paraMap){
		return this.findListByHqlId("findSgtjDataByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SgtjData getById(String id)
	{
		return (SgtjData)this.getObjectById(SgtjData.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SgtjData sgtjData)
	{
		sgtjData.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(sgtjData);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SgtjData sgtjData)
	{
		this.saveOrUpdateObject(sgtjData);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SgtjData.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SgtjData sgtjData = (SgtjData)this.getObjectById(SgtjData.class, id);
		sgtjData.setDelFlag(1);
		this.saveObject(sgtjData);
	}
}
