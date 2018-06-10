package com.jshx.report.dao;

import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.report.entity.Report;

/**
 * @author	YuWeitao
 * @version 创建时间：2011-4-21 上午9:48:33 
 * 类说明		报表管理Dao接口 
 */
public interface ReportDao extends BaseDao {
	/**
	 * 根据paraMap中的键值查找report分页对象
	 * @param paraMap
	 * @param pagination
	 * @return
	 */
	public Pagination findReportPagByMap(Map<String, Object> paraMap, Pagination pagination);
	
	/**
	 * 根据Report主键reportId查找Report对象
	 * @param 	reportId
	 * @return	Report
	 */
	public Report getReportById(String reportId);
	
	/**
	 * 保存Report对象
	 * @param report
	 */
	public void saveReport(Report report);
	
	/**
	 * 根据reportName查找Report对象，reportName与id一一对应
	 * @param reportName
	 * @return
	 */
	public Report getReortByReportName(String reportName);
	
	/**
	 * 根据reportId查找历史版本信息列表分页对象
	 * @param 	paraMap：reportId
	 * @param 	pagination
	 * @return	Pagination
	 */
	public Pagination findHistoryVersionsByReportId(Map<String, Object> paraMap, Pagination pagination);
	
}