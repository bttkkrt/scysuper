package com.jshx.companyInfoPlan.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.dao.CompanyInfoPlanDao;

@Component("companyInfoPlanDao")
public class CompanyInfoPlanDaoImpl extends BaseDaoImpl implements CompanyInfoPlanDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCompanyInfoPlanByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<CompanyInfoPlan> findCompanyInfoPlan(Map<String, Object> paraMap){
		return this.findListByHqlId("findCompanyInfoPlanByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CompanyInfoPlan getById(String id)
	{
		return (CompanyInfoPlan)this.getObjectById(CompanyInfoPlan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CompanyInfoPlan companyInfoPlan)
	{
		companyInfoPlan.setId(null);
		this.saveOrUpdateObject(companyInfoPlan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CompanyInfoPlan companyInfoPlan)
	{
		this.saveOrUpdateObject(companyInfoPlan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CompanyInfoPlan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CompanyInfoPlan companyInfoPlan = (CompanyInfoPlan)this.getObjectById(CompanyInfoPlan.class, id);
		companyInfoPlan.setDelFlag(1);
		this.saveObject(companyInfoPlan);
	}
}
