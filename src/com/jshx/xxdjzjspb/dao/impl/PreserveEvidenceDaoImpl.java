package com.jshx.xxdjzjspb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xxdjzjspb.entity.PreserveEvidence;
import com.jshx.xxdjzjspb.dao.PreserveEvidenceDao;

@Component("preserveEvidenceDao")
public class PreserveEvidenceDaoImpl extends BaseDaoImpl implements PreserveEvidenceDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPreserveEvidenceByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PreserveEvidence> findPreserveEvidence(Map<String, Object> paraMap){
		return this.findListByHqlId("findPreserveEvidenceByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PreserveEvidence getById(String id)
	{
		return (PreserveEvidence)this.getObjectById(PreserveEvidence.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PreserveEvidence preserveEvidence)
	{
		preserveEvidence.setId(null);
		this.saveOrUpdateObject(preserveEvidence);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PreserveEvidence preserveEvidence)
	{
		this.saveOrUpdateObject(preserveEvidence);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PreserveEvidence.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PreserveEvidence preserveEvidence = (PreserveEvidence)this.getObjectById(PreserveEvidence.class, id);
		preserveEvidence.setDelFlag(1);
		this.saveObject(preserveEvidence);
	}
}
