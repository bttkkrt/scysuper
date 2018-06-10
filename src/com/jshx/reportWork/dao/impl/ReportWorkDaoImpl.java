package com.jshx.reportWork.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.dao.ReportWorkDao;

@Component("reportWorkDao")
public class ReportWorkDaoImpl extends BaseDaoImpl implements ReportWorkDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findReportWorkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findReportWork(Map<String, Object> paraMap){
		return this.findListByHqlId("findReportWorkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReportWork getById(String id)
	{
		return (ReportWork)this.getObjectById(ReportWork.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReportWork reportWork)
	{
		reportWork.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(reportWork);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReportWork reportWork)
	{
		this.saveOrUpdateObject(reportWork);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ReportWork.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ReportWork reportWork = (ReportWork)this.getObjectById(ReportWork.class, id);
		reportWork.setDelFlag(1);
		this.saveObject(reportWork);
	}
}
