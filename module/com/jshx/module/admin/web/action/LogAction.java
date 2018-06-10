/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.StringUtil;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.OperationLog;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.LogService;

/**
 * @author Chenjian
 * @version 创建时间：2011-2-10 下午02:07:29 类说明
 */
public class LogAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Pagination pagination;

	@Autowired() 
	@Qualifier("logService")
	private LogService logService;

	private LogonLog logonLog;

	private OperationLog opLog;

	private Date beginDate;

	private Date endDate;

	private Boolean op = Constants.isLog;;

	private String browser;

	private String os;
	/**
	 * 根据是否允许记录访问地址，如果允许返回访问日志页面
	 */
	public String openOrCloseLog() {

		if (Constants.isLog) {
			Constants.isLog = false;
		} else {
			Constants.isLog = true;
		}
		op = Constants.isLog;
		return SUCCESS;
	}
	/**
	 * 分页查询登录日志，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"browser":"","fromIp":"","loginType":"","os":"","visitedDate":{"time":1399623112000},"visitor":{"dept":{"deptName":""},"displayName":"","loginId":""}}]}
	 * @return
	 */
	public void logonLog() {
		pagination = new Pagination(super.getRequest());
		if (StringUtil.isNullOrEmpty(logonLog.getVisitor().getDeptCode())) {
			User visitor = logonLog.getVisitor();
			if (visitor == null)
				visitor = new User();
			visitor.setDeptCode(this.getLoginUser().getDept().getDeptCode());
			logonLog.setVisitor(visitor);
		}

		if ((null != this.getBrowser()) && (0 < this.getBrowser().trim().length())) {
			logonLog.setBrowser(this.getBrowser());
		}
		if ((null != this.getOs()) && (0 < this.getOs().trim().length())) {
			logonLog.setOs(this.getOs());
		}

		pagination = logService.findLogByPage(pagination, logonLog, beginDate, endDate);
		outputJsonList(pagination.getTotalCount(), "time|dept|loginId|visitor|displayName|deptName|loginType|visitedDate|fromIp|browser|os|", pagination.getListOfObject());
		
	}
	/**
	 * 分页查询用户操作日志，返回查询结果的json数据
	 */
	public void opLog() {
		pagination = new Pagination(super.getRequest());
		if (StringUtil.isNullOrEmpty(opLog.getVisitor().getDeptCode())) {
			User visitor = opLog.getVisitor();
			if (visitor == null)
				visitor = new User();
			visitor.setDeptCode(this.getLoginUser().getDept().getDeptCode());
			opLog.setVisitor(visitor);
		}

		pagination = logService.findOperationLogByPage(pagination, opLog,
				beginDate, endDate);
		
		outputJsonList(pagination.getTotalCount(), "time|visitor|loginId|displayName|dept|deptName|visitedDate|module|moduleName|url|", pagination.getListOfObject());
		
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the logonLog
	 */
	public LogonLog getLogonLog() {
		return logonLog;
	}

	/**
	 * @param logonLog
	 *            the logonLog to set
	 */
	public void setLogonLog(LogonLog logonLog) {
		this.logonLog = logonLog;
	}

	/**
	 * @return the opLog
	 */
	public OperationLog getOpLog() {
		return opLog;
	}

	/**
	 * @param opLog
	 *            the opLog to set
	 */
	public void setOpLog(OperationLog opLog) {
		this.opLog = opLog;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the op
	 */
	public Boolean getOp() {
		return op;
	}

	/**
	 * @param op
	 *            the op to set
	 */
	public void setOp(Boolean op) {
		this.op = op;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
