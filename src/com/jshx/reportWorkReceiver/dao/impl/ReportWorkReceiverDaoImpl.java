package com.jshx.reportWorkReceiver.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.reportWorkReceiver.entity.ReportWorkReceiver;
import com.jshx.reportWorkReceiver.dao.ReportWorkReceiverDao;

@Component("reportWorkReceiverDao")
public class ReportWorkReceiverDaoImpl extends BaseDaoImpl implements ReportWorkReceiverDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findReportWorkReceiverByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findReportWorkReceiver(Map<String, Object> paraMap){
		return this.findListByHqlId("findReportWorkReceiverByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReportWorkReceiver getById(String id)
	{
		return (ReportWorkReceiver)this.getObjectById(ReportWorkReceiver.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReportWorkReceiver reportWorkReceiver)
	{
		reportWorkReceiver.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(reportWorkReceiver);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReportWorkReceiver reportWorkReceiver)
	{
		this.saveOrUpdateObject(reportWorkReceiver);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ReportWorkReceiver.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ReportWorkReceiver reportWorkReceiver = (ReportWorkReceiver)this.getObjectById(ReportWorkReceiver.class, id);
		reportWorkReceiver.setDelFlag(1);
		this.saveObject(reportWorkReceiver);
	}
}
