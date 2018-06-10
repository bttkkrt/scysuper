package com.jshx.report.dao;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.report.entity.ReportAttachment;

/**
 * @author	YuWeitao
 * @version 创建时间：2011-4-21 上午10:00:12 
 * 类说明		报表附件管理Dao接口 
 */
public interface ReportAttachmentDao extends BaseDao {
	/**
	 * 根据reportId查找ReportAttachment中对应的最新版本号
	 * @param reportId
	 * @return
	 */
	public Integer getLatestVersionByReportId(String reportId);
	
	/**
	 * 保存ReportAttachment对象
	 * @param reportAttachment
	 */
	public void saveReportAttachment(ReportAttachment reportAttachment);
	
	/**
	 * 根据ReportAttachment主键查找该对象
	 * @param attachmentId
	 * @return
	 */
	public ReportAttachment	getReportAttachmentById(String attachmentId);
}