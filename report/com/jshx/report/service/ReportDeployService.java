package com.jshx.report.service;

import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.report.entity.Report;
import com.jshx.report.entity.ReportAttachment;

/**
 * @author		YuWeitao
 * @version		创建时间：2011-4-21 上午10:19:21
 * 类说明
 */
public interface ReportDeployService extends BaseService {
	/**
	 * 根据paraMap中的键值查找report分页对象
	 * @param 	paraMap
	 * @param 	pagination
	 * @return	Pagination
	 */
	public Pagination findReportPagByMap(Map<String, Object> paraMap, Pagination pagination);
	
	/**
	 * 根据Report主键reportId逻辑删除Report信息
	 * @param reportId
	 */
	public void delReportById(String reportId);
	
	/**
	 * 根据Report主键reportId查找Report对象
	 * @param 	reportId
	 * @return	Report
	 */
	public Report getReportById(String reportId);
	
	/**
	 * 保存更新Report对象
	 * @param report
	 */
	public void saveReport(Report report);
	
	/**
	 * 保存报表、版本、附件信息等至数据库
	 * @param 	report
	 * @param 	birtBackupFilePath
	 */
	public void saveDeployInfo(Report report, User loginUser, String birtBackupFilePath);
	
	/**
	 * 根据reportId查找历史版本信息列表分页对象
	 * @param 	paraMap：reportId
	 * @param 	pagination
	 * @return	Pagination
	 */
	public Pagination findHistoryVersionsByReportId(Map<String, Object> paraMap, Pagination pagination);
	
	/**
	 * 根据ReportAttachment主键查找该对象
	 * @param attachmentId
	 * @return
	 */
	public ReportAttachment	getReportAttachmentById(String attachmentId);
	
	/**
	 * 根据reportName查找Report对象，reportName与id一一对应
	 * @param reportName
	 * @return
	 */
	public Report getReortByReportName(String reportName);
	
}