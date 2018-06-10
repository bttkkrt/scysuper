package com.jshx.ajsp.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ajsp.entity.CloseApproval;
import com.jshx.ajsp.dao.CloseApprovalDao;

@Component("closeApprovalDao")
public class CloseApprovalDaoImpl extends BaseDaoImpl implements CloseApprovalDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCloseApprovalByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<CloseApproval> findCloseApproval(Map<String, Object> paraMap){
		return this.findListByHqlId("findCloseApprovalByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CloseApproval getById(String id)
	{
		return (CloseApproval)this.getObjectById(CloseApproval.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CloseApproval closeApproval)
	{
		closeApproval.setId(null);
		this.saveOrUpdateObject(closeApproval);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CloseApproval closeApproval)
	{
		this.saveOrUpdateObject(closeApproval);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CloseApproval.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CloseApproval closeApproval = (CloseApproval)this.getObjectById(CloseApproval.class, id);
		closeApproval.setDelFlag(1);
		this.saveObject(closeApproval);
	}
}
