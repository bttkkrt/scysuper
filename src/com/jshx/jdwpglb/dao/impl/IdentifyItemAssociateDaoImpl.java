package com.jshx.jdwpglb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdwpglb.entity.IdentifyItemAssociate;
import com.jshx.jdwpglb.dao.IdentifyItemAssociateDao;

@Component("identifyItemAssociateDao")
public class IdentifyItemAssociateDaoImpl extends BaseDaoImpl implements IdentifyItemAssociateDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findIdentifyItemAssociateByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<IdentifyItemAssociate> findIdentifyItemAssociate(Map<String, Object> paraMap){
		return this.findListByHqlId("findIdentifyItemAssociateByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public IdentifyItemAssociate getById(String id)
	{
		return (IdentifyItemAssociate)this.getObjectById(IdentifyItemAssociate.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(IdentifyItemAssociate identifyItemAssociate)
	{
		identifyItemAssociate.setId(null);
		this.saveOrUpdateObject(identifyItemAssociate);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(IdentifyItemAssociate identifyItemAssociate)
	{
		this.saveOrUpdateObject(identifyItemAssociate);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(IdentifyItemAssociate.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		IdentifyItemAssociate identifyItemAssociate = (IdentifyItemAssociate)this.getObjectById(IdentifyItemAssociate.class, id);
		identifyItemAssociate.setDelFlag(1);
		this.saveObject(identifyItemAssociate);
	}
}
