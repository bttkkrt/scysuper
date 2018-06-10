package com.jshx.aqscldjg.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscldjg.entity.ProLegOrg;
import com.jshx.aqscldjg.dao.ProLegOrgDao;

@Component("proLegOrgDao")
public class ProLegOrgDaoImpl extends BaseDaoImpl implements ProLegOrgDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProLegOrgByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProLegOrg(Map<String, Object> paraMap){
		return this.findListByHqlId("findProLegOrgByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProLegOrg getById(String id)
	{
		return (ProLegOrg)this.getObjectById(ProLegOrg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProLegOrg proLegOrg)
	{
		proLegOrg.setId(null);
		this.saveOrUpdateObject(proLegOrg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProLegOrg proLegOrg)
	{
		this.saveOrUpdateObject(proLegOrg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProLegOrg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProLegOrg proLegOrg = (ProLegOrg)this.getObjectById(ProLegOrg.class, id);
		proLegOrg.setDelFlag(1);
		this.saveObject(proLegOrg);
	}
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findJgxxByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJgxxByMap", paraMap, page);
	}
}
