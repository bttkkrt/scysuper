/**
 * Class Name : SafetyEvaluationReportDaoImpl
 * Class Description：安全现状评价报告备案事项通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.safetyEvaluationReport.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safetyEvaluationReport.entity.SafetyEvaluationReport;
import com.jshx.safetyEvaluationReport.dao.SafetyEvaluationReportDao;

@Component("safetyEvaluationReportDao")
public class SafetyEvaluationReportDaoImpl extends BaseDaoImpl implements SafetyEvaluationReportDao
{
	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafetyEvaluationReportByMap", paraMap, page);
	}
	
	/**
	 * Function Name: findSafetyEvaluationReport
	 * Function Description：查询所有记录
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List<SafetyEvaluationReport> findSafetyEvaluationReport(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafetyEvaluationReportByMap", paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public SafetyEvaluationReport getById(String id)
	{
		return (SafetyEvaluationReport)this.getObjectById(SafetyEvaluationReport.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(SafetyEvaluationReport safetyEvaluationReport)
	{
		safetyEvaluationReport.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(safetyEvaluationReport);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(SafetyEvaluationReport safetyEvaluationReport)
	{
		this.saveOrUpdateObject(safetyEvaluationReport);
	}

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafetyEvaluationReport.class, id);
	}

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void deleteWithFlag(String id)
	{
		SafetyEvaluationReport safetyEvaluationReport = (SafetyEvaluationReport)this.getObjectById(SafetyEvaluationReport.class, id);
		safetyEvaluationReport.setDelFlag(1);
		this.saveObject(safetyEvaluationReport);
	}
}
