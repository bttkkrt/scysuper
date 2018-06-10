package com.jshx.report.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.report.dao.ReportDao;
import com.jshx.report.entity.Report;

/**
 * @author	YuWeitao
 * @version 创建时间：2011-4-21 上午9:51:36 
 * 类说明		报表管理Dao接口实现类 
 */
@Component("reportDao")
public class ReportDaoImpl extends BaseDaoImpl implements ReportDao {
	
	public Pagination findReportPagByMap(Map<String, Object> paraMap, Pagination pagination){
		return this.findPageByHqlId("findReportPagByMap", paraMap, pagination);
	}
	
	public Report getReportById(String reportId){
		return (Report) this.getObjectById(Report.class, reportId);
	}
	
	public void saveReport(Report report){
		this.saveObject(report);
	}
	
	public Report getReortByReportName(String reportName){
		return (Report) this.getObjectByProperty(Report.class, "reportName", reportName);
	}
	
	public Pagination findHistoryVersionsByReportId(Map<String, Object> paraMap, Pagination pagination){
		//与getLatestVersionByReportId公用Hql
		return this.findPageByHqlId("getLatestVersionByReportId", paraMap, pagination);
	}
}