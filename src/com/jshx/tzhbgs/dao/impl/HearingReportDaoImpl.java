package com.jshx.tzhbgs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzhbgs.entity.HearingReport;
import com.jshx.tzhbgs.dao.HearingReportDao;

@Component("hearingReportDao")
public class HearingReportDaoImpl extends BaseDaoImpl implements HearingReportDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHearingReportByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<HearingReport> findHearingReport(Map<String, Object> paraMap){
		return this.findListByHqlId("findHearingReportByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HearingReport getById(String id)
	{
		return (HearingReport)this.getObjectById(HearingReport.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HearingReport hearingReport)
	{
		hearingReport.setId(null);
		this.saveOrUpdateObject(hearingReport);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HearingReport hearingReport)
	{
		this.saveOrUpdateObject(hearingReport);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HearingReport.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HearingReport hearingReport = (HearingReport)this.getObjectById(HearingReport.class, id);
		hearingReport.setDelFlag(1);
		this.saveObject(hearingReport);
	}
}
