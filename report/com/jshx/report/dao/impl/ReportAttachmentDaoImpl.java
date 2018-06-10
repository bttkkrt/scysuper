package com.jshx.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.report.dao.ReportAttachmentDao;
import com.jshx.report.entity.ReportAttachment;

/**
 * @author	YuWeitao
 * @version 创建时间：2011-4-21 上午10:01:47 
 * 类说明		报表附件管理Dao接口实现类 
 */
@Component("reportAttachmentDao")
public class ReportAttachmentDaoImpl extends BaseDaoImpl implements ReportAttachmentDao {
	
	public Integer getLatestVersionByReportId(String reportId){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("reportId", reportId);
		
		List<ReportAttachment> retList = this.findListByHqlId("getLatestVersionByReportId", paraMap);
		
		if(null != retList && 0 != retList.size()){
			return (Integer) retList.get(0).getVersion();
		}else{
			return -1;
		}
	} 
	
	public void saveReportAttachment(ReportAttachment reportAttachment){
		this.saveObject(reportAttachment);
	}
	
	public ReportAttachment	getReportAttachmentById(String attachmentId){
		return (ReportAttachment) this.getObjectById(ReportAttachment.class, attachmentId);
	}
}
