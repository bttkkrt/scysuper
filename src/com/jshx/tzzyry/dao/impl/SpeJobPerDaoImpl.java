package com.jshx.tzzyry.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzzyry.entity.SpeJobPer;
import com.jshx.tzzyry.dao.SpeJobPerDao;

@Component("speJobPerDao")
public class SpeJobPerDaoImpl extends BaseDaoImpl implements SpeJobPerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSpeJobPerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SpeJobPer> findSpeJobPer(Map<String, Object> paraMap){
		return this.findListByHqlId("findSpeJobPerByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SpeJobPer getById(String id)
	{
		return (SpeJobPer)this.getObjectById(SpeJobPer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SpeJobPer speJobPer)
	{
		speJobPer.setId(null);
		this.saveOrUpdateObject(speJobPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SpeJobPer speJobPer)
	{
		this.saveOrUpdateObject(speJobPer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SpeJobPer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SpeJobPer speJobPer = (SpeJobPer)this.getObjectById(SpeJobPer.class, id);
		speJobPer.setDelFlag(1);
		this.saveObject(speJobPer);
	}
}
