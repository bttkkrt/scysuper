package com.jshx.jdjcrw.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.xcxgl.entity.PatMan;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SUP_TAS_RESULT")
public class SupTasResult extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	
	/**
	 * 实际巡查项id
	 */
	private String xcxId;
	
	/**
	 * 巡查项结果（合格，不合格）
	 */
	private String xcxResult;
	
	/**
	 * 巡查项备注
	 */
	private String remark;


	/**
	 * 任务ID
	 */
	private String taskId;
	
	/**
	 * 巡查人id
	 */
	private String checkUserId;
	
	/**
	 * 巡查项
	 */
	private PatMan patMan;
	  
	public SupTasResult(){
	}
	
	public SupTasResult(String id, String xcxId, String xcxResult, String remark, Date taskId, String checkUserId){
this.id = id;
 
 
}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getXcxId() {
		return xcxId;
	}

	public void setXcxId(String xcxId) {
		this.xcxId = xcxId;
	}

	public String getXcxResult() {
		return xcxResult;
	}

	public void setXcxResult(String xcxResult) {
		this.xcxResult = xcxResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	 
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=PatMan.class)
	@JoinColumn(name = "xcxId",updatable=false,insertable=false)
	public PatMan getPatMan() {
		return patMan;
	}

	public void setPatMan(PatMan patMan) {
		this.patMan = patMan;
	}
 
	
}
