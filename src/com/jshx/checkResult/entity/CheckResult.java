package com.jshx.checkResult.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkContent.entity.CheckContent;
import com.jshx.checkTable.entity.CheckTable;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.log.entity.UserBehaviorLog;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_RESULT")
public class CheckResult extends BaseModel
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
	 * 检查表id
	 */
	private String checkTableId;

	/**
	 * 内容序号
	 */
	private Long contentOrder;

	/**
	 * 检查内容
	 */
	private String checkContent;

	/**
	 * 检查情况
	 */
	private String checkResult;

	/**
	 * 是否合格
	 */
	private String ifOk;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 安全生产执法检查内容表ID
	 */
	private CheckContent content;
	
	/**
	 * 安全生产执法检查基本信息表ID
	 */
	private CheckBasic basic;
	

	
	public CheckResult(){
	}
	
	public CheckResult(String id, String checkContent, String checkResult, String ifOk,String remark){
this.id = id;

this.checkContent = checkContent;

this.checkResult = checkResult;

this.ifOk = ifOk;
this.remark=remark;
}


	@Column
	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	
	@Column(name="CHECK_TABLE_ID")
	public String getCheckTableId()
	{
		return this.checkTableId;
	}

	public void setCheckTableId(String checkTableId)
	{
		this.checkTableId = checkTableId;
	}

	@Column(name="CONTENT_ORDER")
	public Long getContentOrder()
	{
		return this.contentOrder;
	}

	public void setContentOrder(Long contentOrder)
	{
		this.contentOrder = contentOrder;
	}

	@Column(name="CHECK_CONTENT")
	public String getCheckContent()
	{
		return this.checkContent;
	}

	public void setCheckContent(String checkContent)
	{
		this.checkContent = checkContent;
	}

	@Column(name="CHECK_RESULT")
	public String getCheckResult()
	{
		return this.checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
	}

	@Column(name="IF_OK")
	public String getIfOk()
	{
		return this.ifOk;
	}

	public void setIfOk(String ifOk)
	{
		this.ifOk = ifOk;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CheckContent.class)
    @JoinColumn(name="CONTENT_ID")
	public CheckContent getContent()
	{
		return content;
	}

	public void setContent(CheckContent content)
	{
		this.content = content;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CheckBasic.class)
    @JoinColumn(name="BASIC_ID")
	public CheckBasic getBasic()
	{
		return basic;
	}

	public void setBasic(CheckBasic basic)
	{
		this.basic = basic;
	}

}
