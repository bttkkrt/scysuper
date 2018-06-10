package com.jshx.pfglb.entity;

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
@Table(name="SCO_ASS_LIS")
public class ScoAssLis extends BaseModel
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
	 * 关联评分表编号
	 */
	private String proId;

	/**
	 * 考评类目
	 */
	private String evaluationCategory;

	/**
	 * 标准条款
	 */
	private String provision;

	/**
	 * 标准分值
	 */
	private String score;

	/**
	 * 评分方式
	 */
	private String grading;

	/**
	 * 扣分
	 */
	private String mark;

	/**
	 * 实际得分
	 */
	private String actualScore;

	
	public ScoAssLis(){
	}
	
	public ScoAssLis(String id, String evaluationCategory, String provision, String score, String grading, String mark, String actualScore){
this.id = id;

this.evaluationCategory = evaluationCategory;

this.provision = provision;

this.score = score;

this.grading = grading;

this.mark = mark;

this.actualScore = actualScore;
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

	
	@Column(name="PRO_ID")
	public String getProId()
	{
		return this.proId;
	}

	public void setProId(String proId)
	{
		this.proId = proId;
	}

	@Column(name="EVALUATION_CATEGORY")
	public String getEvaluationCategory()
	{
		return this.evaluationCategory;
	}

	public void setEvaluationCategory(String evaluationCategory)
	{
		this.evaluationCategory = evaluationCategory;
	}

	@Column(name="PROVISION")
	public String getProvision()
	{
		return this.provision;
	}

	public void setProvision(String provision)
	{
		this.provision = provision;
	}

	@Column(name="SCORE")
	public String getScore()
	{
		return this.score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	@Column(name="GRADING")
	public String getGrading()
	{
		return this.grading;
	}

	public void setGrading(String grading)
	{
		this.grading = grading;
	}

	@Column(name="MARK")
	public String getMark()
	{
		return this.mark;
	}

	public void setMark(String mark)
	{
		this.mark = mark;
	}

	@Column(name="ACTUAL_SCORE")
	public String getActualScore()
	{
		return this.actualScore;
	}

	public void setActualScore(String actualScore)
	{
		this.actualScore = actualScore;
	}

}
