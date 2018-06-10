package com.jshx.aqscgljg.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscgljg.entity.ProManOrg;
import com.jshx.aqscgljg.dao.ProManOrgDao;

@Component("proManOrgDao")
public class ProManOrgDaoImpl extends BaseDaoImpl implements ProManOrgDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProManOrgByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProManOrg(Map<String, Object> paraMap){
		return this.findListByHqlId("findProManOrgByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProManOrg getById(String id)
	{
		return (ProManOrg)this.getObjectById(ProManOrg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProManOrg proManOrg)
	{
		proManOrg.setId(null);
		this.saveOrUpdateObject(proManOrg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProManOrg proManOrg)
	{
		this.saveOrUpdateObject(proManOrg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProManOrg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProManOrg proManOrg = (ProManOrg)this.getObjectById(ProManOrg.class, id);
		proManOrg.setDelFlag(1);
		this.saveObject(proManOrg);
	}
}
