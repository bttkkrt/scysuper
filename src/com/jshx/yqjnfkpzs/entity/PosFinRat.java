package com.jshx.yqjnfkpzs.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="POS_FIN_RAT")
public class PosFinRat extends BaseModel
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
	 * 延期方式
	 */
	private String postponeMethod;

	/**
	 * 延期年
	 */
	private String postponeYear;

	/**
	 * 延期月
	 */
	private String postponeMonth;

	/**
	 * 延期日
	 */
	private String postponeDate;

	/**
	 * 分期长度
	 */
	private String stageLength;

	/**
	 * 还款期限大写
	 */
	private String repayPeriod;

	/**
	 * 尚未缴纳罚款大写
	 */
	private String noPay;
	
	/**
	 * 缴纳罚款
	 */
	private String pay;
	

	/**
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 罚款大写
	 */
	private String fineUppercase;
	

	
	public PosFinRat(){
	}
	
	public PosFinRat(String id){
this.id = id;
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

	
	@Column(name="POSTPONE_METHOD")
	public String getPostponeMethod()
	{
		return this.postponeMethod;
	}

	public void setPostponeMethod(String postponeMethod)
	{
		this.postponeMethod = postponeMethod;
	}

	@Column(name="POSTPONE_YEAR")
	public String getPostponeYear()
	{
		return this.postponeYear;
	}

	public void setPostponeYear(String postponeYear)
	{
		this.postponeYear = postponeYear;
	}

	@Column(name="POSTPONE_MONTH")
	public String getPostponeMonth()
	{
		return this.postponeMonth;
	}

	public void setPostponeMonth(String postponeMonth)
	{
		this.postponeMonth = postponeMonth;
	}

	@Column(name="POSTPONE_DATE")
	public String getPostponeDate()
	{
		return this.postponeDate;
	}

	public void setPostponeDate(String postponeDate)
	{
		this.postponeDate = postponeDate;
	}

	@Column(name="STAGE_LENGTH")
	public String getStageLength()
	{
		return this.stageLength;
	}

	public void setStageLength(String stageLength)
	{
		this.stageLength = stageLength;
	}

	@Column(name="REPAY_PERIOD")
	public String getRepayPeriod()
	{
		return this.repayPeriod;
	}

	public void setRepayPeriod(String repayPeriod)
	{
		this.repayPeriod = repayPeriod;
	}

	@Column(name="NO_PAY")
	public String getNoPay()
	{
		return this.noPay;
	}

	public void setNoPay(String noPay)
	{
		this.noPay = noPay;
	}

	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="FINE_UPPERCASE")
	public String getFineUppercase()
	{
		return this.fineUppercase;
	}

	public void setFineUppercase(String fineUppercase)
	{
		this.fineUppercase = fineUppercase;
	}
	@Column(name="PAY")
	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

}
