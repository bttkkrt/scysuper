package com.jshx.report.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.FusionChartXmlUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.report.entity.Report;
import com.jshx.report.entity.ReportAttachment;
import com.jshx.report.service.ReportDeployService;

/**
 * @author		YuWeitao
 * @version		创建时间：2011-4-21 上午11:12:23
 * 类说明 		报表发布Action类
 */
public class ReportDeployAction extends BaseAction {

	private static final long serialVersionUID = 6131527988723649766L;
	
	@Autowired() 
	@Qualifier("reportDeployService")
	private ReportDeployService reportDeployService;
	
	private Pagination pagination;
	
	private Report report;
	
	private String[] id;
	
	private String reportId;
	
	private File file;
	
	private String fileName;
	
	private String deployFlag;
	
	private String attachmentId;
	
	private String xmlStr;
	
	/**
	 * 初始化报表列表
	 */
	public String initList(){
		return SUCCESS;
	}

	/**
	 * 报表列表
	 */
	public String findReportList(){
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		User loginUser = super.getLoginUser();
		List<String> childDeptIds = loginUser.getDeptIds();
		paraMap.put("childDeptIds", childDeptIds);
		
		if(null != report){
			if(null!=report.getReportName() && !"".equals(report.getReportName().trim())){
				paraMap.put("reportName", "%"+report.getReportName().trim()+"%");
			}
			if(null!=report.getDeployFileName() && !"".equals(report.getDeployFileName().trim())){
				paraMap.put("deployFileName", "%"+report.getDeployFileName().trim()+"%");
			}
			if(null!=report.getDeployer() && !"".equals(report.getDeployer().trim())){
				paraMap.put("deployFileName", "%"+report.getDeployer().trim()+"%");
			}
		}
		pagination = reportDeployService.findReportPagByMap(paraMap, pagination);
		
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject());
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try{
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 跳转至报表发布
	 */
	public String initDeploy(){
		return SUCCESS;
	}
	
	public void checkReportName(){
		Integer retFlag = 0;
		String reportName = this.getRequestParameter("param");
		Report report = reportDeployService.getReortByReportName(reportName);
		if(null!=report && 1!=report.getDelFlag()){
			retFlag = 1;
		}
		
		try{
			if(0==retFlag){
				getResponse().getWriter().println("{\"status\":\"y\"}");
			}else if(1==retFlag){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"已存在该名称的报表，请通过更新版本功能发布！\"}");
			}
		}catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	/**
	 * 将报表设计文件发布至BIRT报表引擎应用目录
	 */
	public String deploy(){
		String orignalFileName = this.fileName;
		this.report.setDeployFileName(orignalFileName);
		
		String uploadFileName = null;
		Long nowTime = new Date().getTime();
		uploadFileName = String.valueOf(nowTime)+"."+orignalFileName;
		
		/**
		 * 发布至BIRT应用服务地址
		 * 会自动覆盖之前版本（相同文件名）
		 */
		String birtAppFilePath = SysPropertiesUtil.getProperty("birtAppPhysicPath") + orignalFileName;
		
		/**
		 * 上传至平台应用服务备份地址
		 * 保留所有版本
		 */
		String birtBackupFilePath = "webResources\\reportUploadFiles\\" + uploadFileName;
		String birtBackupFilePathToCopy = Struts2Util.getServletContext().
			getRealPath("/webResources/reportUploadFiles/")+"\\"+uploadFileName;
		
		File birtAppFile = new File(birtAppFilePath);
		File birtBackupFile = new File(birtBackupFilePathToCopy);
		
		try{
			//拷贝该报表文件至相应路径
			FileUtils.copyFile(file, birtAppFile);
			FileUtils.copyFile(file, birtBackupFile);
			
			//保存报表、版本、附件信息等至数据库
			User loginUser = this.getLoginUser();
			reportDeployService.saveDeployInfo(this.report, loginUser, birtBackupFilePath);

			deployFlag = "1";
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 逻辑删除报表信息
	 * @return
	 */
	public String removeReport() {
		if(null != id){
			for(String reportId : id)
				reportDeployService.delReportById(reportId);
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
		}
		return null;
	}
	
	/**
	 * 根据reportId查找该报表对应的历史版本信息
	 * @return
	 */
	public String findHistoryVersions(){
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("reportId", this.reportId);
		
		pagination = reportDeployService.findHistoryVersionsByReportId(paraMap, pagination);
		
		return SUCCESS;
	}
	
	/**
	 * 跳转至报表信息编辑页面
	 * @return
	 */
	public String initEditReport(){
		this.report = reportDeployService.getReportById(this.reportId);
		
		return SUCCESS;
	}
	
	/**
	 * 保存修改后的Report信息
	 * @return
	 */
	public String saveReport(){
		Report saveReport = reportDeployService.getReportById(this.reportId);
		
		saveReport.setReportName(this.report.getReportName());
		reportDeployService.saveReport(saveReport);
		
		return SUCCESS;
	}
	
	/**
	 * 下载报表历史版本文件
	 * @return
	 */
	public void downloadAttatchment(){
		ReportAttachment attachment = reportDeployService.getReportAttachmentById(this.attachmentId);
		String fileName = attachment.getFileName();
		String filePath = Struts2Util.getServletContext().getRealPath("/")+attachment.getUploadFilePath();
		filePath.replace("/", File.separator);
		
		try{
			File file = new File(filePath);
			OutputStream out = this.getResponse().getOutputStream();
			this.getResponse().reset();
			String fileDisplayName = null;

			if (StringUtils.isNotEmpty(fileName)) {
				fileDisplayName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			}
			this.getResponse().addHeader("Content-Disposition", "attachment;filename="
					+ fileDisplayName);
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.close();
			
		}catch (IOException e) {
			BasalException ex = new BasalException(BasalException.ERROR, Constants.REPORT_VERSION_ERROR);
			throw ex;
		}

	}
	
	/**
	 * 跳转至版本更新页面
	 * @return
	 */
	public String initUpdateVersion(){
		this.report = reportDeployService.getReportById(this.reportId);
		return SUCCESS;
	}
	
	/**
	 * 更新报表版本文件信息
	 * @return
	 */
	public String updateVersion(){
		String orignalFileName = this.fileName;
		this.report.setDeployFileName(orignalFileName);
		
		String uploadFileName = null;
		Long nowTime = new Date().getTime();
		uploadFileName = String.valueOf(nowTime)+"."+orignalFileName;
		
		/**
		 * 发布至BIRT应用服务地址
		 * 会自动覆盖之前版本（相同文件名）
		 */
		String birtAppFilePath = SysPropertiesUtil.getProperty("birtAppPhysicPath") + orignalFileName;
		
		/**
		 * 上传至平台应用服务备份地址
		 * 保留所有版本
		 */
		String birtBackupFilePath = "webResources\\reportUploadFiles\\" + uploadFileName;
		String birtBackupFilePathToCopy = Struts2Util.getServletContext().
			getRealPath("/webResources/reportUploadFiles/")+"\\"+uploadFileName;
		
		File birtAppFile = new File(birtAppFilePath);
		File birtBackupFile = new File(birtBackupFilePathToCopy);
		
		try{
			//拷贝该报表文件至相应路径
			FileUtils.copyFile(file, birtAppFile);
			FileUtils.copyFile(file, birtBackupFile);
			
			//保存报表、版本、附件信息等至数据库
			User loginUser = super.getLoginUser();
			reportDeployService.saveDeployInfo(this.report, loginUser, birtBackupFilePath);

			deployFlag = "1";
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * BIRT标签测试
	 * @return
	 */
	public String tagTest(){
		return SUCCESS;
	}
	
	/**
	 * fusionChart Demo
	 * @return
	 */
	public String fusionChartDemo(){
		//查询得到的二维数组，Object类型为数值型或者字符串型
		Object[][] queryData = {{"Jan", "80.77"},
								{"Feb", "103.45"},
								{"Mar", "47.32"},
								{"Apr", "67"},
								{"May", "138"},
								{"Jun", "158"},
								{"Jul", "49"},
								{"Aug", "90"}};
		
		//多系列数据demo,第一行为标识定义行,不存数数据
		Object[][] queryDataForMultiSeries = {	{"xName", "A",   "B"},
												{"Jan",   "83",  "77"},
												{"Feb",   "103", "23"},
												{"Mar",   "47",  "32"},
												{"Apr",   "67",  "56"},
												{"May",   "138", "65"},
												{"Jun",   "158", "88"},
												{"Jul",   "49",  "22"},
												{"Aug",   "90",  "74"}};
		
		this.xmlStr = new FusionChartXmlUtil().convertToXML(
				queryData, "Sale Statistics", "", "Month", "Sale", "0", "2", "1", "1", "", "1", "0");
		
		return SUCCESS;
	}
	
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * set上传文件的文件名
	 * @param fileName
	 */
	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getDeployFlag() {
		return deployFlag;
	}

	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getXmlStr() {
		return xmlStr;
	}

	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}
}