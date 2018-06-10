package com.jshx.ajyssp.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ajyssp.entity.CaseRefer;
import com.jshx.ajyssp.dao.CaseReferDao;

@Component("caseReferDao")
public class CaseReferDaoImpl extends BaseDaoImpl implements CaseReferDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCaseReferByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<CaseRefer> findCaseRefer(Map<String, Object> paraMap){
		return this.findListByHqlId("findCaseReferByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseRefer getById(String id)
	{
		return (CaseRefer)this.getObjectById(CaseRefer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CaseRefer caseRefer)
	{
		caseRefer.setId(null);
		this.saveOrUpdateObject(caseRefer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CaseRefer caseRefer)
	{
		this.saveOrUpdateObject(caseRefer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CaseRefer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CaseRefer caseRefer = (CaseRefer)this.getObjectById(CaseRefer.class, id);
		caseRefer.setDelFlag(1);
		this.saveObject(caseRefer);
	}
}
