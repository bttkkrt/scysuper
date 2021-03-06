package com.jshx.qytzrypx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qytzrypx.entity.SpeJobTra;
import com.jshx.qytzrypx.dao.SpeJobTraDao;

@Component("speJobTraDao")
public class SpeJobTraDaoImpl extends BaseDaoImpl implements SpeJobTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSpeJobTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSpeJobTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findSpeJobTraByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SpeJobTra getById(String id)
	{
		return (SpeJobTra)this.getObjectById(SpeJobTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SpeJobTra speJobTra)
	{
		speJobTra.setId(null);
		this.saveOrUpdateObject(speJobTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SpeJobTra speJobTra)
	{
		this.saveOrUpdateObject(speJobTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SpeJobTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SpeJobTra speJobTra = (SpeJobTra)this.getObjectById(SpeJobTra.class, id);
		speJobTra.setDelFlag(1);
		this.saveObject(speJobTra);
	}
}
