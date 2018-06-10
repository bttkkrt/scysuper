package com.jshx.qyfjfl.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyfjfl.dao.EnterpriseGradeDao;
import com.jshx.qyfjfl.entity.EnterpriseGrade;

@Component("enterpriseGradeDao")
public class EnterpriseGradeDaoImpl extends BaseDaoImpl implements EnterpriseGradeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEnterpriseGradeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEnterpriseGrade(Map<String, Object> paraMap){
		return this.findListByHqlId("findEnterpriseGradeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EnterpriseGrade getById(String id)
	{
		return (EnterpriseGrade)this.getObjectById(EnterpriseGrade.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EnterpriseGrade companyScore)
	{
		companyScore.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(companyScore);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EnterpriseGrade companyScore)
	{
		this.saveOrUpdateObject(companyScore);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EnterpriseGrade.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EnterpriseGrade companyScore = (EnterpriseGrade)this.getObjectById(EnterpriseGrade.class, id);
		companyScore.setDelFlag(1);
		this.saveObject(companyScore);
	}
}
