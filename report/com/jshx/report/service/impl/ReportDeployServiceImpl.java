package com.jshx.report.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.User;
import com.jshx.report.dao.ReportAttachmentDao;
import com.jshx.report.dao.ReportDao;
import com.jshx.report.entity.Report;
import com.jshx.report.entity.ReportAttachment;
import com.jshx.report.service.ReportDeployService;

@Service("reportDeployService")
public class ReportDeployServiceImpl extends BaseServiceImpl implements ReportDeployService {
	
	@Autowired() 
	@Qualifier("reportDao")
	private ReportDao reportDao;
	
	@Autowired() @Qualifier("reportAttachmentDao")
	private ReportAttachmentDao reportAttachmentDao;


	@Transactional(propagation=Propagation.NESTED) 
	public Pagination findReportPagByMap(Map<String, Object> paraMap, Pagination pagination){
		return reportDao.findReportPagByMap(paraMap, pagination);
	}
	
	@Transactional(propagation=Propagation.NESTED) 
	public void delReportById(String reportId){
		Report delReport = reportDao.getReportById(reportId);
		
		delReport.setDelFlag(new Integer(1));
		
		reportDao.saveReport(delReport);
	}
	
	@Transactional(propagation=Propagation.NESTED)
	public Report getReportById(String reportId){
		return reportDao.getReportById(reportId);
	}
	
	@Transactional(propagation=Propagation.NESTED)
	public void saveReport(Report report){
		reportDao.saveReport(report);
	}
	
	@Transactional(propagation=Propagation.NESTED)
	public void saveDeployInfo(Report report, User loginUser, String birtBackupFilePath){
		//根据reportName一一对应查找Report对象
		Report saveReport =	reportDao.getReortByReportName(report.getReportName());
		if(null == saveReport){
			saveReport = new Report();
		}
		saveReport.setReportName(report.getReportName());
		saveReport.setDeployFileName(report.getDeployFileName());
		saveReport.setSqlScript(report.getSqlScript());
		saveReport.setDelFlag(new Integer(0));
		saveReport.setDeployer(loginUser.getDisplayName());
		saveReport.setDeptId(loginUser.getDept().getId());
		
		//这里把报表访问路径访问参数定为frameset，后期可以动态修改
		String birtAppUrl = SysPropertiesUtil.getProperty("birtAppPath")+"frameset?__report="+ report.getDeployFileName();
		saveReport.setUrl(birtAppUrl);
		
		reportDao.saveReport(saveReport);
		
		//保存信息至ReportAttachment表
		Integer latestAttVersion = reportAttachmentDao.getLatestVersionByReportId(saveReport.getId());
		ReportAttachment saveAttachment = new ReportAttachment();
		if(-1 == latestAttVersion){
			//没有之前版本
			saveAttachment.setVersion(new Integer(1));
		}else{
			saveAttachment.setVersion(latestAttVersion + 1);
		}
		saveAttachment.setReportName(report.getReportName());
		saveAttachment.setFileName(report.getDeployFileName());
		saveAttachment.setUploadFilePath(birtBackupFilePath);
		saveAttachment.setReportId(saveReport.getId());
		
		reportAttachmentDao.saveReportAttachment(saveAttachment);
		
		//更新Report对象的最新版本号
		saveReport.setLatestVersion(saveAttachment.getVersion());
		reportDao.saveReport(saveReport);
	}
	
	public Pagination findHistoryVersionsByReportId(Map<String, Object> paraMap, Pagination pagination){
		return reportDao.findHistoryVersionsByReportId(paraMap, pagination);
	}
	
	public ReportAttachment	getReportAttachmentById(String attachmentId){
		return reportAttachmentDao.getReportAttachmentById(attachmentId);
	}
	
	public Report getReortByReportName(String reportName){
		return reportDao.getReortByReportName(reportName);
	}
	
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	public void setReportAttachmentDao(ReportAttachmentDao reportAttachmentDao) {
		this.reportAttachmentDao = reportAttachmentDao;
	}
}