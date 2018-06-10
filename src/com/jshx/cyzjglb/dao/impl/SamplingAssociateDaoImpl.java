package com.jshx.cyzjglb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.cyzjglb.entity.SamplingAssociate;
import com.jshx.cyzjglb.dao.SamplingAssociateDao;

@Component("samplingAssociateDao")
public class SamplingAssociateDaoImpl extends BaseDaoImpl implements SamplingAssociateDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSamplingAssociateByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SamplingAssociate> findSamplingAssociate(Map<String, Object> paraMap){
		return this.findListByHqlId("findSamplingAssociateByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SamplingAssociate getById(String id)
	{
		return (SamplingAssociate)this.getObjectById(SamplingAssociate.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SamplingAssociate samplingAssociate)
	{
		samplingAssociate.setId(null);
		this.saveOrUpdateObject(samplingAssociate);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SamplingAssociate samplingAssociate)
	{
		this.saveOrUpdateObject(samplingAssociate);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SamplingAssociate.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SamplingAssociate samplingAssociate = (SamplingAssociate)this.getObjectById(SamplingAssociate.class, id);
		samplingAssociate.setDelFlag(1);
		this.saveObject(samplingAssociate);
	}
}
