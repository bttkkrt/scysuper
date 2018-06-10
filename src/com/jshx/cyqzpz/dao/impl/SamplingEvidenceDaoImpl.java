package com.jshx.cyqzpz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.cyqzpz.entity.SamplingEvidence;
import com.jshx.cyqzpz.dao.SamplingEvidenceDao;

@Component("samplingEvidenceDao")
public class SamplingEvidenceDaoImpl extends BaseDaoImpl implements SamplingEvidenceDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSamplingEvidenceByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SamplingEvidence> findSamplingEvidence(Map<String, Object> paraMap){
		return this.findListByHqlId("findSamplingEvidenceByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SamplingEvidence getById(String id)
	{
		return (SamplingEvidence)this.getObjectById(SamplingEvidence.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SamplingEvidence samplingEvidence)
	{
		samplingEvidence.setId(null);
		this.saveOrUpdateObject(samplingEvidence);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SamplingEvidence samplingEvidence)
	{
		this.saveOrUpdateObject(samplingEvidence);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SamplingEvidence.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SamplingEvidence samplingEvidence = (SamplingEvidence)this.getObjectById(SamplingEvidence.class, id);
		samplingEvidence.setDelFlag(1);
		this.saveObject(samplingEvidence);
	}
}
