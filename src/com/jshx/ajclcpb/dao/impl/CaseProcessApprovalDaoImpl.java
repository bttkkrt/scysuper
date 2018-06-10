package com.jshx.ajclcpb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ajclcpb.entity.CaseProcessApproval;
import com.jshx.ajclcpb.dao.CaseProcessApprovalDao;

@Component("caseProcessApprovalDao")
public class CaseProcessApprovalDaoImpl extends BaseDaoImpl implements CaseProcessApprovalDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCaseProcessApprovalByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<CaseProcessApproval> findCaseProcessApproval(Map<String, Object> paraMap){
		return this.findListByHqlId("findCaseProcessApprovalByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseProcessApproval getById(String id)
	{
		return (CaseProcessApproval)this.getObjectById(CaseProcessApproval.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CaseProcessApproval caseProcessApproval)
	{
		caseProcessApproval.setId(null);
		this.saveOrUpdateObject(caseProcessApproval);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CaseProcessApproval caseProcessApproval)
	{
		this.saveOrUpdateObject(caseProcessApproval);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CaseProcessApproval.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CaseProcessApproval caseProcessApproval = (CaseProcessApproval)this.getObjectById(CaseProcessApproval.class, id);
		caseProcessApproval.setDelFlag(1);
		this.saveObject(caseProcessApproval);
	}
}
